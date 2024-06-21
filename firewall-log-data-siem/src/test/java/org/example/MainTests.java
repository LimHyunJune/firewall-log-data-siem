package org.example;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Null;
import org.example.dto.LogDataDTO;
import org.example.entity.LogData;
import org.example.service.LogDataService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@SpringBootTest
public class MainTests {

    @Autowired
    LogDataService logDataService;

    @Test
    public void CrdTest()
    {

        // 1. create log data
        LogDataDTO logDataDTO = LogDataDTO.builder()
                .srcIp("0.0.0.0")
                .dstIp("0.0.0.0")
                .action("Accept")
                .port(9999)
                .protocol("HTTP")
                .timestamp(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .build();
        LogData logData = logDataService.save(logDataDTO);

        // 2. find log data by id
        Optional<LogData> findLogData = logDataService.findById(logData.getId());

        Assertions.assertEquals(logData, findLogData.get());

        // 3. delete log data

        logDataService.deleteById(logData.getId());

        Optional<LogData> deleteLogData = logDataService.findById(logData.getId());
        Assertions.assertFalse(deleteLogData.isPresent());

    }
}
