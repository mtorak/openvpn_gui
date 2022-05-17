package com.openvpn.service;

import com.openvpn.exception.OpenVpnExecutionException;
import com.openvpn.model.ExecutionConversation;
import com.openvpn.model.OpenVpnResponse;
import expectj.ExpectJ;
import expectj.Spawn;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class OpenVpnExecutor {

    // Create a new ExpectJ object with a timeout
    private static final String OPENVPN_COMMAND = "/home/bentegopersonel/workspace2/openvpn_gui/backend-spring-boot-crud-full-stack/ovn.sh\n";
    private ExpectJ expectinator = new ExpectJ(120);
    private Map<String, ExecutionConversation> executionsMap = new HashMap<>();
    private static final Integer SHELL_MAX_LIFE_TIME_MS = 4 * 60 * 1000;


    private Spawn globalShell;

    private static final String STOP_OPENVPN_OUTPUT = "OpenVpn session ended!";

    public OpenVpnResponse executeStart(String conversationId) {

        log.info("Called executeStart() with conversationId: {}", conversationId);

        try {
            // find previous execution shell for conversationId
            ExecutionConversation conversation = executionsMap.get(conversationId);
            if (conversation != null) {
                Spawn shell = conversation.getShell();
                stopShell(shell);
                executionsMap.remove(conversationId);
            }

            Spawn shell = expectinator.spawn(OPENVPN_COMMAND);

            Thread.sleep(4);
            String stdOut = shell.getCurrentStandardOutContents();

            // update executions map
            List<String> executedCommands = new ArrayList<>();
            executedCommands.add(OPENVPN_COMMAND);

            ExecutionConversation executionConversation = ExecutionConversation.builder()
              .shell(shell)
              .executedCommands(executedCommands)
              .lastUsed(LocalDateTime.now())
              .build();

            // update execution map
            executionsMap.put(conversationId, executionConversation);

            return new OpenVpnResponse(stdOut, true);

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            return new OpenVpnResponse(e.getMessage(), false);
        }
    }

    // todo check whether shell script Exit is called before.
    public OpenVpnResponse executeSelection(String conversationId, String input) {

        log.info("Called executeSelection() with conversationId: {}", conversationId);

        try {
            // find previous execution shell for conversationId
            ExecutionConversation conversation = executionsMap.get(conversationId);
            if (conversation == null)
                throw new OpenVpnExecutionException("No previous execution found!");

            // send selection
            Spawn shell = conversation.getShell();
            String command = input + "\n";
            shell.send(command);
            Thread.sleep(3);
            String stdOut = shell.getCurrentStandardOutContents();

            // update execution map
            conversation.getExecutedCommands().add(command);
            conversation.setLastUsed(LocalDateTime.now());
            executionsMap.put(conversationId, conversation);

            return new OpenVpnResponse(stdOut, true);

        } catch (IOException | InterruptedException | OpenVpnExecutionException e) {
            System.err.println("Exception: " + e.getMessage());
            return new OpenVpnResponse(e.getMessage(), false);
        }
    }

    public OpenVpnResponse executeStop(String conversationId) {

        log.info("Called executeStop() with conversationId: {}", conversationId);

        try {
            // find previous execution shell for conversationId
            ExecutionConversation conversation = executionsMap.get(conversationId);
            if (conversation == null)
                throw new OpenVpnExecutionException("No previous execution found!");

            Spawn shell = conversation.getShell();
            stopShell(shell);
            executionsMap.remove(conversationId);

            Thread.sleep(4);
            return new OpenVpnResponse(STOP_OPENVPN_OUTPUT, true);

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            return new OpenVpnResponse(e.getMessage(), false);
        }
    }

    private void stopShell(Spawn shell) {
        try {
            shell.send("0x03\n");
            shell.stop();
            Thread.sleep(4);
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    @Scheduled(fixedDelay = 5 * 1000)
    private void scheduleMapMaintanance() {
        System.out.println("Running maintanance on executionMap..");

        List<String> conversationsToRemove = new ArrayList<>();
        executionsMap.forEach((key, value) -> {
              System.out.println("key: " + key + ", value: " + value.toString());

              long milliseconds = ChronoUnit.MILLIS.between(value.getLastUsed(), LocalDateTime.now());
              if (milliseconds > SHELL_MAX_LIFE_TIME_MS)
                  conversationsToRemove.add(key);
          }
        );
        conversationsToRemove.forEach((item) -> {
            executionsMap.remove(item);
        });
    }

}

