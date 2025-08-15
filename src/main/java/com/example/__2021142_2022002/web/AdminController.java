package com.example.__2021142_2022002.web;

import com.example.__2021142_2022002.domain.Project;
import com.example.__2021142_2022002.service.ProjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/admin") @RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')") @Tag(name="Admin")
public class AdminController {
    private final ProjectService projects;

    /** GET /admin/pending-projects */
    @GetMapping("/pending-projects")
    public List<Project> pending(){ return projects.pending(); }

    /** PUT /admin/approve/{id} */
    @PutMapping("/approve/{id}")
    public Project approve(@PathVariable Long id){ return projects.approve(id); }

    /** PUT /admin/reject/{id} */
    @PutMapping("/reject/{id}")
    public Project reject(@PathVariable Long id){ return projects.reject(id); }
}