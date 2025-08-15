package com.example.__2021142_2022002.bootstrap;

import com.example.__2021142_2022002.domain.Project;
import com.example.__2021142_2022002.domain.ProjectStatus;
import com.example.__2021142_2022002.repo.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component @RequiredArgsConstructor
public class SeedData implements CommandLineRunner {
    private final ProjectRepository projects;

    @Override public void run(String... args) {
        if (projects.count() > 0) return;

        projects.save(make("Smart Bottle", "Tracks hydration", "5000", "0",    ProjectStatus.PENDING));
        projects.save(make("E-paper Badge","Hackable badge",  "3000", "1200", ProjectStatus.FUNDING));
        projects.save(make("DIY Drone",    "Tiny FPV",        "2000", "2000", ProjectStatus.COMPLETED));
        projects.save(make("Weird Idea",   "Nope",            "1000", "0",    ProjectStatus.REJECTED));
    }

    private Project make(String t,String d,String target,String collected, ProjectStatus s){
        Project p=new Project();
        p.setTitle(t); p.setDescription(d);
        p.setTargetAmount(new BigDecimal(target));
        p.setCollectedAmount(new BigDecimal(collected));
        p.setStatus(s);
        return p;
    }
}