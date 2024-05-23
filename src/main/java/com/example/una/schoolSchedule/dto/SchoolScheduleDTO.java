package com.example.una.schoolSchedule.dto;

import com.example.una.schoolSchedule.domain.SchoolSchedule;
import jakarta.persistence.Id;

public class SchoolScheduleDTO extends SchoolSchedule{
    private String ATPT_OFCDC_SC_CODE;

    @Id
    private String SD_SCHUL_CODE;
    private String DGHT_CRSE_SC_NM;
    private String SCHUL_CRSE_SC_NM;
    private String AA_YMD;
    private String AA_FROM_YMD;
    private String AA_TO_YMD;

    public SchoolScheduleDTO(String ATPT_OFCDC_SC_CODE, String SD_SCHUL_CODE, String DGHT_CRSE_SC_NM, String SCHUL_CRSE_SC_NM, String AA_YMD, String AA_FROM_YMD, String AA_TO_YMD) {
        this.ATPT_OFCDC_SC_CODE = ATPT_OFCDC_SC_CODE;
        this.SD_SCHUL_CODE = SD_SCHUL_CODE;
        this.DGHT_CRSE_SC_NM = DGHT_CRSE_SC_NM;
        this.SCHUL_CRSE_SC_NM = SCHUL_CRSE_SC_NM;
        this.AA_YMD = AA_YMD;
        this.AA_FROM_YMD = AA_FROM_YMD;
        this.AA_TO_YMD = AA_TO_YMD;
    }

    public String getATPT_OFCDC_SC_CODE() {
        return ATPT_OFCDC_SC_CODE;
    }

    public void setATPT_OFCDC_SC_CODE(String ATPT_OFCDC_SC_CODE) {
        this.ATPT_OFCDC_SC_CODE = ATPT_OFCDC_SC_CODE;
    }

    public String getSD_SCHUL_CODE() {
        return SD_SCHUL_CODE;
    }

    public void setSD_SCHUL_CODE(String SD_SCHUL_CODE) {
        this.SD_SCHUL_CODE = SD_SCHUL_CODE;
    }

    public String getDGHT_CRSE_SC_NM() {
        return DGHT_CRSE_SC_NM;
    }

    public void setDGHT_CRSE_SC_NM(String DGHT_CRSE_SC_NM) {
        this.DGHT_CRSE_SC_NM = DGHT_CRSE_SC_NM;
    }

    public String getSCHUL_CRSE_SC_NM() {
        return SCHUL_CRSE_SC_NM;
    }

    public void setSCHUL_CRSE_SC_NM(String SCHUL_CRSE_SC_NM) {
        this.SCHUL_CRSE_SC_NM = SCHUL_CRSE_SC_NM;
    }

    public String getAA_YMD() {
        return AA_YMD;
    }

    public void setAA_YMD(String AA_YMD) {
        this.AA_YMD = AA_YMD;
    }

    public String getAA_FROM_YMD() {
        return AA_FROM_YMD;
    }

    public void setAA_FROM_YMD(String AA_FROM_YMD) {
        this.AA_FROM_YMD = AA_FROM_YMD;
    }

    public String getAA_TO_YMD() {
        return AA_TO_YMD;
    }

    public void setAA_TO_YMD(String AA_TO_YMD) {
        this.AA_TO_YMD = AA_TO_YMD;
    }

    public SchoolSchedule toEntity(){
        return SchoolSchedule.builder()
                .atptOfcdcScCode(ATPT_OFCDC_SC_CODE)
                .sdSchulCode(SD_SCHUL_CODE)
                .dghtCrseScNm(DGHT_CRSE_SC_NM)
                .schulCrseScNm(SCHUL_CRSE_SC_NM)
                .aaYmd(AA_YMD)
                .build();
    }
}