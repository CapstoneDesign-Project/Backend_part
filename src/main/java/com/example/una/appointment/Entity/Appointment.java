package com.example.una.appointment.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;

    @Column(nullable = false)
    private LocalDate appointmentDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date requestedDate;

    @Column(name = "status")
    private int status;

    @Column(name = "parent_kakao_id")
    private Long parentKakaoId;

    @Column(name = "teacher_kakao_id")
    private Long teacherKakaoId;

    @Column(length = 255)
    private String description;
}
