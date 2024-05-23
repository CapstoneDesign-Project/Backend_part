package com.example.una.appointment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
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
public class AppointmentDTO {
    private int appointmentId;
    private LocalDate appointmentDate;
    private Date requestedDate;
    private int status;

    @NotNull
    private Long parentKakaoId;

    @NotNull
    private Long teacherKakaoId;
}
