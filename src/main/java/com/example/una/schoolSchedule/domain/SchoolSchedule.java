package com.example.una.schoolSchedule.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "school_schedule")
public class SchoolSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ATPT_OFCDC_SC_CODE")
    @JsonProperty("ATPT_OFCDC_SC_CODE")
    private String atptOfcdcScCode;

    @Column(name = "ATPT_OFCDC_SC_NM")
    @JsonProperty("ATPT_OFCDC_SC_NM")
    private String atptOfcdcScNm;

    @Column(name = "SD_SCHUL_CODE")
    @JsonProperty("SD_SCHUL_CODE")
    private String sdSchulCode;

    @Column(name = "SCHUL_NM")
    @JsonProperty("SCHUL_NM")
    private String schulNm;

    @Column(name = "AY")
    @JsonProperty("AY")
    private String ay;

    @Column(name = "DGHT_CRSE_SC_NM")
    @JsonProperty("DGHT_CRSE_SC_NM")
    private String dghtCrseScNm;

    @Column(name = "SCHUL_CRSE_SC_NM")
    @JsonProperty("SCHUL_CRSE_SC_NM")
    private String schulCrseScNm;

    @Column(name = "SBTR_DD_SC_NM")
    @JsonProperty("SBTR_DD_SC_NM")
    private String sbtrDdScNm;

    @Column(name = "AA_YMD")
    @JsonProperty("AA_YMD")
    private String aaYmd;

    @Column(name = "EVENT_NM")
    @JsonProperty("EVENT_NM")
    private String eventNm;

    @Column(name = "EVENT_CNTNT")
    @JsonProperty("EVENT_CNTNT")
    private String eventCntnt;

    @Column(name = "ONE_GRADE_EVENT_YN")
    @JsonProperty("ONE_GRADE_EVENT_YN")
    private String oneGradeEventYn;

    @Column(name = "TW_GRADE_EVENT_YN")
    @JsonProperty("TW_GRADE_EVENT_YN")
    private String twGradeEventYn;

    @Column(name = "THREE_GRADE_EVENT_YN")
    @JsonProperty("THREE_GRADE_EVENT_YN")
    private String threeGradeEventYn;

    @Column(name = "FR_GRADE_EVENT_YN")
    @JsonProperty("FR_GRADE_EVENT_YN")
    private String frGradeEventYn;

    @Column(name = "FIV_GRADE_EVENT_YN")
    @JsonProperty("FIV_GRADE_EVENT_YN")
    private String fivGradeEventYn;

    @Column(name = "SIX_GRADE_EVENT_YN")
    @JsonProperty("SIX_GRADE_EVENT_YN")
    private String sixGradeEventYn;

    @Column(name = "LOAD_DTM")
    @JsonProperty("LOAD_DTM")
    private String loadDtm;

    public String getAtptOfcdcScCode() {
        return atptOfcdcScCode;
    }

    public void setAtptOfcdcScCode(String atptOfcdcScCode) {
        this.atptOfcdcScCode = atptOfcdcScCode;
    }

    public String getAtptOfcdcScNm() {
        return atptOfcdcScNm;
    }

    public void setAtptOfcdcScNm(String atptOfcdcScNm) {
        this.atptOfcdcScNm = atptOfcdcScNm;
    }

    public String getSdSchulCode() {
        return sdSchulCode;
    }

    public void setSdSchulCode(String sdSchulCode) {
        this.sdSchulCode = sdSchulCode;
    }

    public String getSchulNm() {
        return schulNm;
    }

    public void setSchulNm(String schulNm) {
        this.schulNm = schulNm;
    }

    public String getAy() {
        return ay;
    }

    public void setAy(String ay) {
        this.ay = ay;
    }

    public String getDghtCrseScNm() {
        return dghtCrseScNm;
    }

    public void setDghtCrseScNm(String dghtCrseScNm) {
        this.dghtCrseScNm = dghtCrseScNm;
    }

    public String getSchulCrseScNm() {
        return schulCrseScNm;
    }

    public void setSchulCrseScNm(String schulCrseScNm) {
        this.schulCrseScNm = schulCrseScNm;
    }

    public String getSbtrDdScNm() {
        return sbtrDdScNm;
    }

    public void setSbtrDdScNm(String sbtrDdScNm) {
        this.sbtrDdScNm = sbtrDdScNm;
    }

    public String getAaYmd() {
        return aaYmd;
    }

    public void setAaYmd(String aaYmd) {
        this.aaYmd = aaYmd;
    }

    public String getEventNm() {
        return eventNm;
    }

    public void setEventNm(String eventNm) {
        this.eventNm = eventNm;
    }

    public String getEventCntnt() {
        return eventCntnt;
    }

    public void setEventCntnt(String eventCntnt) {
        this.eventCntnt = eventCntnt;
    }

    public String getOneGradeEventYn() {
        return oneGradeEventYn;
    }

    public void setOneGradeEventYn(String oneGradeEventYn) {
        this.oneGradeEventYn = oneGradeEventYn;
    }

    public String getTwGradeEventYn() {
        return twGradeEventYn;
    }

    public void setTwGradeEventYn(String twGradeEventYn) {
        this.twGradeEventYn = twGradeEventYn;
    }

    public String getThreeGradeEventYn() {
        return threeGradeEventYn;
    }

    public void setThreeGradeEventYn(String threeGradeEventYn) {
        this.threeGradeEventYn = threeGradeEventYn;
    }

    public String getFrGradeEventYn() {
        return frGradeEventYn;
    }

    public void setFrGradeEventYn(String frGradeEventYn) {
        this.frGradeEventYn = frGradeEventYn;
    }

    public String getFivGradeEventYn() {
        return fivGradeEventYn;
    }

    public void setFivGradeEventYn(String fivGradeEventYn) {
        this.fivGradeEventYn = fivGradeEventYn;
    }

    public String getSixGradeEventYn() {
        return sixGradeEventYn;
    }

    public void setSixGradeEventYn(String sixGradeEventYn) {
        this.sixGradeEventYn = sixGradeEventYn;
    }

    public String getLoadDtm() {
        return loadDtm;
    }

    public void setLoadDtm(String loadDtm) {
        this.loadDtm = loadDtm;
    }
}
