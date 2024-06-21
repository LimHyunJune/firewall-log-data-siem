package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogData {

    String SrcIp;
    String DstIp;
    Integer port;
    String protocol;
    String action;
    LocalDateTime timestamp;

}
