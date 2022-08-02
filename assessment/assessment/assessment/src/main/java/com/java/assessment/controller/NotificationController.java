package com.java.assessment.controller;

import com.java.assessment.model.Supervisor;
import com.java.assessment.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class NotificationController {

    @Autowired
    private SupervisorService supervisorService;

    @GetMapping("api/supervisors")
    public List<String> getSupervisors() throws URISyntaxException {
        return supervisorService.supervisors();
    }
    @PostMapping("api/submit")
    public void submit(@RequestBody Supervisor supervisor){
        supervisorService.save(supervisor);
        System.out.println(supervisor);
    }
}
