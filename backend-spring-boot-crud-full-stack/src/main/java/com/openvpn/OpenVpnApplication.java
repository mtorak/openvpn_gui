package com.openvpn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OpenVpnApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenVpnApplication.class, args);
    }

}
