package ru.hogwarts.school.controller;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.service.InfoServiceInterface;

@RestController
@RequestMapping("/profile")
public class InfoController {

    private InfoServiceInterface infoServiceInterface;

    public InfoController(InfoServiceInterface infoServiceInterface){
        this.infoServiceInterface = infoServiceInterface;
    }

    @GetMapping
    public ResponseEntity<Integer> getPort() {
        return ResponseEntity.ok(infoServiceInterface.getPort());
    }


}
