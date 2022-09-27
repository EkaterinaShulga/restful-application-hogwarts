package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InfoService implements InfoServiceInterface{

    @Value("${server.port}")
    private Integer port;

    @Override
    public ResponseEntity<Integer> getPort(){
        return ResponseEntity.ok(port);
    }


}
