package com.example.una.appointment.service;

import com.example.una.appointment.Entity.Appointment;
import com.example.una.appointment.dto.AppointmentDTO;
import com.example.una.appointment.dto.UpdateAppointmentStatusRequestDTO;
import com.example.una.appointment.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // 모든 예약 목록을 가져오는 메서드
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // 주어진 ID에 해당하는 예약을 가져옴
    public Optional<Appointment> getAppointmentById(int teacherKakaoId) {
        return appointmentRepository.findById(teacherKakaoId);
    }

    // 특정 강사(teacherId)에 해당하는 예약 목록을 가져옴
    public List<Appointment> getAppointmentsByTeacherKakaoId(int teacherKakaoId) {
        return appointmentRepository.findByTeacherKakaoId(teacherKakaoId);
    }

    // 새로운 예약을 생성
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }


    // 대기 중인 상담 예약을 승인
    public Appointment approveAppointment(int appointmentId) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            if (appointment.getStatus() == 0) { // 대기중 상태인지 확인
                appointment.setStatus(1); // 승인 상태로 변경
                return appointmentRepository.save(appointment);
            } else {
                throw new RuntimeException("Appointment is not in pending state.");
            }
        } else {
            throw new RuntimeException("Appointment not found with id: " + appointmentId);
        }
    }

    // 대기 중인 상담 예약을 거절
    public Appointment rejectAppointment(int appointmentId) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            if (appointment.getStatus() == 0) { // 대기중 상태인지 확인
                appointment.setStatus(2); // 거절 상태로 변경
                return appointmentRepository.save(appointment);
            } else {
                throw new RuntimeException("Appointment is not in pending state.");
            }
        } else {
            throw new RuntimeException("Appointment not found with id: " + appointmentId);
        }
    }

    // 특정 날짜에 해당하는 예약 조회
    public List<Appointment> getAppointmentsByDate(LocalDate appointmentDate) {
        return appointmentRepository.findByAppointmentDate(appointmentDate);
    }

    //오늘 날짜에 해당하는 일정 조회
    public List<Appointment> getAppointmentsForToday() {
        LocalDate today = LocalDate.now();
        return appointmentRepository.findByAppointmentDate(today);
    }

    // 주어진 ID에 해당하는 예약의 상태를 업데이트
    public Appointment updateAppointmentStatus(int appointmentId, UpdateAppointmentStatusRequestDTO updateRequest) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            appointment.setStatus(updateRequest.getStatus());
            return appointmentRepository.save(appointment);
        }
        throw new RuntimeException("Appointment not found with id: " + appointmentId);
    }

    public Appointment updateAppointment(int appointmentId, AppointmentDTO appointmentDTO) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
            appointment.setRequestedDate(appointmentDTO.getRequestedDate());
            appointment.setStatus(appointmentDTO.getStatus());
            appointment.setParentKakaoId(appointmentDTO.getParentKakaoId());
            appointment.setTeacherKakaoId(appointmentDTO.getTeacherKakaoId());
            return appointmentRepository.save(appointment);
        } else {
            throw new RuntimeException("Appointment not found with id: " + appointmentId);
        }
    }

    public void deleteAppointment(int appointmentId) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (optionalAppointment.isPresent()) {
            appointmentRepository.deleteById(appointmentId);
        } else {
            throw new RuntimeException("Appointment not found with id: " + appointmentId);
        }
    }
}
