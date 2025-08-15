package com.example.__2021142_2022002.service;

import com.example.__2021142_2022002.domain.*;
import com.example.__2021142_2022002.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service @RequiredArgsConstructor
public class SupportService {
    private final ProjectRepository projects;
    private final SupportRepository supports;

    @Transactional
    public Project support(Long projectId, BigDecimal amount, String backerUsername){
        if (amount==null || amount.signum()<=0) throw new IllegalArgumentException("amount > 0");
        Project p = projects.findWithLock(projectId).orElseThrow();

        if (p.getStatus()!= ProjectStatus.FUNDING)
            throw new IllegalStateException("Project not open for funding");

        BigDecimal remaining = p.getTargetAmount().subtract(p.getCollectedAmount());
        if (amount.compareTo(remaining) > 0)
            throw new IllegalArgumentException("Amount exceeds remaining: " + remaining);

        supports.save(new Support(null, p, backerUsername, amount, null));

        p.setCollectedAmount(p.getCollectedAmount().add(amount));
        if (p.getCollectedAmount().compareTo(p.getTargetAmount())==0) {
            p.setStatus(ProjectStatus.COMPLETED);
        }
        return p;
    }
}