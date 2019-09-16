package com.wonders.business.user.param;


import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(value="我的患者-接口入参类", description="我的患者入参对象")
public class PatientParam implements Serializable {
    private String userId;  // 用户Id
    private String  patientId; // 患者Id
    private String  doctorId; //医生Id

    public String getUserId() {
        return userId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
}
