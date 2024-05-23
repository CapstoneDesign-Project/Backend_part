package com.example.una.appointment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateAppointmentRequestDTO {
    @NotNull
    private int appointmentId;
    @NotNull
    private LocalDate appointmentDate;
    @NotNull
    private Date requestedDate;
    @NotNull
    private int status;
    @NotNull
    private Long parentKakaoId;
    @NotNull
    private Long teacherKakaoId;
    private String description;
}
