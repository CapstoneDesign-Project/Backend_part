package com.example.una.appointment.controller;

import com.example.una.appointment.Entity.Appointment;
import com.example.una.appointment.dto.AppointmentDTO;
import com.example.una.appointment.dto.UpdateAppointmentRequestDTO;
import com.example.una.appointment.dto.UpdateAppointmentStatusRequestDTO;
import com.example.una.appointment.service.AppointmentService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
@NoArgsConstructor
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Appointment 엔티티를 AppointmentDTO로 변환하는 메서드
    private AppointmentDTO convertToDTO(Appointment appointment) {
        return AppointmentDTO.builder()
                .appointmentId(appointment.getAppointmentId())
                .appointmentDate(appointment.getAppointmentDate())
                .requestedDate(appointment.getRequestedDate())
                .status(appointment.getStatus())
                .parentKakaoId(appointment.getParentKakaoId())
                .teacherKakaoId(appointment.getTeacherKakaoId())
                .description(appointment.getDescription())
                .build();
    }

    // AppointmentDTO를 Appointment 엔티티로 변환하는 메서드
    private Appointment convertToEntity(AppointmentDTO appointmentDTO) {
        return Appointment.builder()
                .appointmentId(appointmentDTO.getAppointmentId())
                .appointmentDate(appointmentDTO.getAppointmentDate())
                .requestedDate(appointmentDTO.getRequestedDate())
                .status(appointmentDTO.getStatus())
                .parentKakaoId(appointmentDTO.getTeacherKakaoId())
                .teacherKakaoId(appointmentDTO.getTeacherKakaoId())
                .description(appointmentDTO.getDescription())
                .build();
    }

    // 컨트롤러의 createAppointment 메서드  - 상담 예약 신청
    @PostMapping("/createAppointments")
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        if (appointmentDTO == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("예약 내용이 없습니다.");

        // AppointmentDTO를 Appointment 엔티티로 변환하여 서비스에 전달
        Appointment appointment = convertToEntity(appointmentDTO);
        appointment.setStatus(0); // 예약 생성 시 상태를 대기중(0)으로 설정
        Appointment createdAppointment = appointmentService.createAppointment(appointment);

        // Appointment 엔티티를 다시 AppointmentDTO로 변환하여 반환
        AppointmentDTO createdAppointmentDTO = convertToDTO(createdAppointment);
        if (createdAppointmentDTO != null)
            return ResponseEntity.status(HttpStatus.CREATED).body("예약 완료되었습니다");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("예약에 실패했습니다.");
    }

    // 모든 예약 목록 조회
    @GetMapping("/appointments")
    public ResponseEntity<?> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();

        if (appointments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("예약 내용이 없습니다!");
        }

        // 예약 엔티티를 예약 DTO로 변환
        List<AppointmentDTO> appointmentDTOs = appointments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(appointmentDTOs);
    }

    // 해당 교사 ID에 따른 일정 조회
    @GetMapping("/getAppointmentsId/{teacherKakaoId}")
    public ResponseEntity<?> getAppointmentsByTeacherId(@PathVariable("teacherKakaoId") int teacherKakaoId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByTeacherKakaoId(teacherKakaoId);

        if (appointments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("예약 내용이 없습니다!");
        }

        // 예약 엔티티를 예약 DTO로 변환
        List<AppointmentDTO> appointmentDTOs = appointments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(appointmentDTOs);
    }

    // 대기 중인 상담 예약을 승인하는 컨트롤러
    @PostMapping("/appointments/approve/{appointmentId}")
    public ResponseEntity<?> approveAppointment(@PathVariable("appointmentId") int appointmentId) {
        try {
            Appointment approvedAppointment = appointmentService.approveAppointment(appointmentId);
            return ResponseEntity.ok("Appointment approved successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Appointment approval failed!");
        }
    }

    // 대기 중인 상담 예약을 거절하는 컨트롤러
    @PostMapping("/appointments/reject/{appointmentId}")
    public ResponseEntity<?> rejectAppointment(@PathVariable("appointmentId") int appointmentId) {
        try {
            Appointment rejectedAppointment = appointmentService.rejectAppointment(appointmentId);
            return ResponseEntity.ok("Appointment rejected successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Appointment rejection failed!");
        }
    }

    // 주어진 ID에 해당하는 예약의 상태를 업데이트
    @PutMapping("/appointments/{appointmentId}/status")
    public ResponseEntity<?> updateAppointmentStatus(@PathVariable("appointmentId") int appointmentId, @RequestBody UpdateAppointmentStatusRequestDTO updateRequest) {
        try {
            Appointment updatedAppointment = appointmentService.updateAppointmentStatus(appointmentId, updateRequest);
            return ResponseEntity.ok(convertToDTO(updatedAppointment));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 상담 예약 수정
    @PutMapping("/appointments/update/{appointmentId}")
    public ResponseEntity<?> updateAppointment(@PathVariable("appointmentId") int appointmentId, @RequestBody AppointmentDTO appointmentDTO) {
        try {
            Appointment updatedAppointment = appointmentService.updateAppointment(appointmentId, appointmentDTO);
            return ResponseEntity.ok("Appointment updated successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Appointment update failed");
        }
    }

    // 상담 예약을 삭제하는 컨트롤러
    @DeleteMapping("/appointments/delete/{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable("appointmentId") int appointmentId) {
        try {
            appointmentService.deleteAppointment(appointmentId);
            return ResponseEntity.ok("Appointment deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Appointment delete failed!");
        }
    }

    // 특정 날짜의 예약 목록 조회
    @GetMapping("/appointments/date")
    public ResponseEntity<?> getAppointmentsByDate(@RequestParam("appointmentDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate appointmentDate) {
        List<Appointment> appointments = appointmentService.getAppointmentsByDate(appointmentDate);

        if (appointments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("해당 날짜에 예약이 없습니다!");
        }

        // 예약 엔티티를 예약 DTO로 변환
        List<AppointmentDTO> appointmentDTOs = appointments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(appointmentDTOs);
    }

    // 오늘 날짜의 예약 목록 조회
    @GetMapping("/appointments/today")
    public ResponseEntity<?> getAppointmentsForToday() {
        List<Appointment> appointments = appointmentService.getAppointmentsForToday();

        if (appointments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("오늘 날짜에 예약이 없습니다!");
        }

        // 예약 엔티티를 예약 DTO로 변환
        List<AppointmentDTO> appointmentDTOs = appointments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(appointmentDTOs);
    }

}

