package com.openvpn.service;

import com.openvpn.model.OpenVpnResponse;
import expectj.ExpectJ;
import expectj.Spawn;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class OpenVpnExecutor {

    // Create a new ExpectJ object with a timeout
    private ExpectJ expectinator = new ExpectJ(120);
    private Spawn shell;
    private ByteArrayInputStream bais = new ByteArrayInputStream(new byte[5 * 1024 * 1024]);

    private static final String STOP_OPENVPN_OUTPUT = "OpenVpn session ended!";

    public OpenVpnResponse executeStart() {
        try {

            shell = expectinator.spawn("/home/bentegopersonel/workspace2/backend-spring-boot-crud-full-stack/ovn.sh\n");
            Thread.sleep(4);
            String stdOut = shell.getCurrentStandardOutContents();

            return new OpenVpnResponse(stdOut, true);

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            return new OpenVpnResponse(e.getMessage(), false);
        }
    }

    // todo check whether shell script Exit is called before.
    public OpenVpnResponse executeSelection(String input) {

        try {
            // send selection
            shell.send(input + "\n");
            Thread.sleep(3);
            String stdOut = shell.getCurrentStandardOutContents();

            return new OpenVpnResponse(stdOut, true);
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            return new OpenVpnResponse(e.getMessage(), false);
        }
    }

    public OpenVpnResponse executeStop() {

        try {
            shell.send("0x03\n");
            shell.stop();
            Thread.sleep(4);
            return new OpenVpnResponse(STOP_OPENVPN_OUTPUT, true);
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            return new OpenVpnResponse(e.getMessage(), false);
        }
    }

}
