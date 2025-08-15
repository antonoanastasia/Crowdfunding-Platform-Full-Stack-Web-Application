package com.example.__2021142_2022002.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter; import lombok.Setter;
import java.math.BigDecimal;

@Entity @Getter @Setter
public class Project {
    @Id @GeneratedValue private Long id;

    @NotBlank private String title;

    @NotBlank @Column(length = 4000)
    private String description;

    @NotNull @Positive @Column(precision=19, scale=2)
    private BigDecimal targetAmount;

    @NotNull @Column(precision=19, scale=2)
    private BigDecimal collectedAmount = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status = ProjectStatus.PENDING;

    @Version private Long version; // optimistic locking για ταυτόχρονες στηρίξεις
}