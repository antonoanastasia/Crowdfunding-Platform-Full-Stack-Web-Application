package com.example.__2021142_2022002.web;


import com.example.__2021142_2022002.domain.Project;
import com.example.__2021142_2022002.service.ProjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/projects") @RequiredArgsConstructor
@Tag(name="Projects")
public class ProjectController {
    private final ProjectService service;

    /** POST /projects (creator) */
    @PostMapping
    @PreAuthorize("hasRole('CREATOR')")
    public Project create(@RequestBody Project p) { return service.create(p); }

    /** GET /projects (όλοι) */
    @GetMapping
    public List<Project> list() { return service.publicList(); }
}