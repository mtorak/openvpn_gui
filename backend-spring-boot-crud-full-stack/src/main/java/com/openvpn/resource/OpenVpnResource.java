package com.openvpn.resource;

import com.openvpn.model.OpenVpnResponse;
import com.openvpn.service.OpenVpnExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200", "http://localhost:8081"})
@RestController
public class OpenVpnResource {

    @Autowired
    private OpenVpnExecutor openVpnExecutor;

    @PostMapping("/startOpenVpn/{conversationId}")
    public ResponseEntity<OpenVpnResponse> startOpenVpn(@PathVariable String conversationId) {
        OpenVpnResponse openVpnResponse = openVpnExecutor.executeStart(conversationId);
        return new ResponseEntity<>(openVpnResponse, HttpStatus.OK);
    }

    @PostMapping("/executeSelection/{conversationId}/{input}")
    public ResponseEntity<OpenVpnResponse> executeSelection(@PathVariable String conversationId, @PathVariable String input) {
        OpenVpnResponse openVpnResponse = openVpnExecutor.executeSelection(conversationId, input);
        return new ResponseEntity<>(openVpnResponse, HttpStatus.OK);
    }

    @PostMapping("/stopOpenVpn/{conversationId}")
    public ResponseEntity<OpenVpnResponse> stopOpenVpn(@PathVariable String conversationId) {
        OpenVpnResponse openVpnResponse = openVpnExecutor.executeStop(conversationId);
        return new ResponseEntity<>(openVpnResponse, HttpStatus.OK);
    }

}