package com.example.__2021142_2022002.web;

import com.example.__2021142_2022002.domain.Project;
import com.example.__2021142_2022002.service.SupportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;

@RestController @RequestMapping("/support") @RequiredArgsConstructor
@Tag(name="Support")
public class SupportController {
    private final SupportService svc;

    public record SupportReq(BigDecimal amount){}

    /** POST /support/{projectId} (backer) */
    @PostMapping("/{projectId}")
    @PreAuthorize("hasRole('BACKER')")
    public Project pledge(@PathVariable Long projectId, @RequestBody SupportReq req, Principal me){
        return svc.support(projectId, req.amount(), me.getName());
    }
}