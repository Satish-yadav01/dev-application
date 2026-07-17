package com.example.demo.controller;


import com.example.demo.db.InMemoryDB;
import com.example.demo.entity.Developer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/developer")
public class DeveloperController {

    private final InMemoryDB db;

    public DeveloperController() {
        this.db = InMemoryDB.getInstance();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDeveloper(@RequestBody Developer developer){
        Developer savedDeveloper = db.save(developer);

        log.info("Developer created: {}", savedDeveloper);
        return ResponseEntity.ok(Map.of("developer", savedDeveloper, "message", "Record inserted successfully"));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateDeveloper(@RequestBody Developer developer){
        Developer updatedDeveloper = db.save(developer);
        log.info("Developer updated: {}", updatedDeveloper.getName());
        return ResponseEntity.ok(Map.of("developer", updatedDeveloper, "message", "Record updated successfully"));
    }

    @GetMapping("/fetch/all")
    public ResponseEntity<?> getAllDevelopers(){
        List<Developer> allDevelopers = db.getAllDevelopers();

        List<Developer> genAiDevelopers = allDevelopers.stream()
                .filter(dev -> dev.getStacks().contains("Gen AI")).toList();


        List<Developer> backendDeveloperList = allDevelopers.stream()
                .filter(dev -> dev.getDepartment().equalsIgnoreCase("Backend"))
                .toList();

        Map<String, List<Developer>> collect =
                allDevelopers.stream().collect(Collectors.groupingBy(Developer::getDepartment));

        log.info("genAiDevelopers : {}", genAiDevelopers);
        log.info("backendDeveloperList : {}", backendDeveloperList);
        log.info("collect : {}", collect);

        return ResponseEntity.ok(genAiDevelopers);
    }
}
