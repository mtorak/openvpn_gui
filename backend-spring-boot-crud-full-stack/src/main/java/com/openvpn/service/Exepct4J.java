package com.openvpn.service;

import expectj.ExpectJ;
import expectj.ExpectJException;
import expectj.Spawn;
import expectj.TimeoutException;

import java.io.IOException;

public class Exepct4J {

    public static void main(String[] args) throws IOException, TimeoutException, ExpectJException {
        try {
            // Create a new ExpectJ object with a timeout of 5s
            ExpectJ expectinator = new ExpectJ(2);

            // Fork the process
            Spawn shell = expectinator.spawn("/home/bentegopersonel/workspace2/openvpn_gui/backend-spring-boot-crud-full-stack/ovn.sh\n");

            Thread.sleep(3);
            String stdOut = shell.getCurrentStandardOutContents();
            System.out.println(stdOut);

            // Talk to it
            shell.send("1\n");
            Thread.sleep(3);
            stdOut = shell.getCurrentStandardOutContents();
            System.out.println(stdOut);

            shell.send("myclient\n");
            Thread.sleep(3);
            stdOut = shell.getCurrentStandardOutContents();
            System.out.println(stdOut);

            shell.send("243\n");
            Thread.sleep(3);
            stdOut = shell.getCurrentStandardOutContents();
            System.out.println(stdOut);

            // send ctrl + c
            shell.send("0x03\n");
            Thread.sleep(3);
            stdOut = shell.getCurrentStandardOutContents();
            System.out.println(stdOut);

            shell.stop();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
