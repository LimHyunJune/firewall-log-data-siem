package org.example.service;

import org.example.dto.LogDataDTO;
import org.example.entity.LogData;
import org.example.repository.LogDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogDataService {
    private  final LogDataRepository logDataRepository;

    public LogDataService(LogDataRepository logDataRepository)
    {
        this.logDataRepository = logDataRepository;
    }

    public LogData save(LogDataDTO logDataDTO)
    {
        LogData logData = LogData
                .builder()
                .srcIp(logDataDTO.getSrcIp())
                .dstIp(logDataDTO.getDstIp())
                .port(logDataDTO.getPort())
                .action(logDataDTO.getAction())
                .protocol(logDataDTO.getProtocol())
                .timestamp(logDataDTO.getTimestamp())
                .build();
        return logDataRepository.save(logData);
    }

    public Optional<LogData> findById(int id)
    {
        return logDataRepository.findById(id);
    }

    public List<LogData> findAll()
    {
        return logDataRepository.findAll();
    }

    public void deleteById(int id)
    {
        logDataRepository.deleteById(id);
    }
}
