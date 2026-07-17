package com.example.demo.db;

import com.example.demo.entity.Developer;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDB {
    private final Map<Long,Developer> db = new ConcurrentHashMap<>();
    private static InMemoryDB obj = null;

    private InMemoryDB(){
    }

    public static InMemoryDB getInstance(){
        if(obj == null) obj = new InMemoryDB();
        return obj;
    }

    public Developer save(Developer developer){
        db.put(developer.getId(), developer);
        return db.get(developer.getId());
    }

    public List<Developer> getAllDevelopers(){
        return new ArrayList<>(db.values());
    }
}
