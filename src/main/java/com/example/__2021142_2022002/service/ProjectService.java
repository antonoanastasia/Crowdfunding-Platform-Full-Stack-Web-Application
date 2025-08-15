package com.example.__2021142_2022002.service;

import com.example.__2021142_2022002.domain.*;
import com.example.__2021142_2022002.repo.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projects;

    @Transactional
    public Project create(Project p) {
        p.setId(null);
        p.setStatus(ProjectStatus.PENDING);
        if (p.getCollectedAmount()==null) p.setCollectedAmount(java.math.BigDecimal.ZERO);
        return projects.save(p);
    }

    /** επιστρέφει μόνο FUNDING/COMPLETED για δημόσια λίστα */
    public List<Project> publicList() {
        return projects.findAll().stream()
                .filter(pr -> pr.getStatus()==ProjectStatus.FUNDING || pr.getStatus()==ProjectStatus.COMPLETED)
                .toList();
    }

    public List<Project> pending() { return projects.findByStatus(ProjectStatus.PENDING); }

    @Transactional public Project approve(Long id) {
        Project p = projects.findById(id).orElseThrow();
        if (p.getStatus()!=ProjectStatus.PENDING) throw new IllegalStateException("Not pending");
        p.setStatus(ProjectStatus.FUNDING); return p;
    }

    @Transactional public Project reject(Long id) {
        Project p = projects.findById(id).orElseThrow();
        if (p.getStatus()!=ProjectStatus.PENDING) throw new IllegalStateException("Not pending");
        p.setStatus(ProjectStatus.REJECTED); return p;
    }
}