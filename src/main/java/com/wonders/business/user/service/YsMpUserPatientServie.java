package com.wonders.business.user.service;


import com.wonders.business.user.dao.YsMpUserPatientDao;
import com.wonders.business.user.model.YsMpUserPatient;
import com.wonders.business.user.param.PatientParam;
import com.wonders.sys.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class YsMpUserPatientServie extends BaseService<YsMpUserPatient> {


    @Autowired
    private YsMpUserPatientDao  ysMpUserPatientDao;


 public List<YsMpUserPatient> findYsMpUserPatientList(PatientParam patientParam){
      return  ysMpUserPatientDao.findYsMpUserPatientList(patientParam);
  }

    public  int save(YsMpUserPatient ysMpUserPatient){
         int num=   ysMpUserPatientDao.insert(ysMpUserPatient);

         return num;
    }
}