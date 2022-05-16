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
            // Spawn shell = expectinator.spawn("/bin/bash");

            Spawn shell = expectinator.spawn("/home/bentegopersonel/workspace2/backend-spring-boot-crud-full-stack/ovn.sh\n");
            String stdOUt  = shell.getCurrentStandardOutContents();
            String errOut = shell.getCurrentStandardErrContents();

            Thread.sleep(2);

            // Talk to it
            // shell.send("/home/bentegopersonel/workspace2/backend-spring-boot-crud-full-stack/ovn.sh\n");
            // String outContents = shell.getCurrentStandardOutContents();
            // System.out.println("outContents: " + outContents);

            shell.send("1\n");

            shell.send("myclient\n");

            // send ctrl + c
            shell.send("0x03\n");


            Thread.sleep(3);

            shell.send("exit\n");
            shell.stop();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        shell.send("\\010\n");
//
//        shell.send("1\n");
//
//        shell.send("exit\n");

//        shell.expectClose();



    }

}
