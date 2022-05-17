package com.openvpn.model;

import expectj.Spawn;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class ExecutionConversation {

    private Spawn shell;
    private LocalDateTime lastUsed;
    private List<String> executedCommands;

}
