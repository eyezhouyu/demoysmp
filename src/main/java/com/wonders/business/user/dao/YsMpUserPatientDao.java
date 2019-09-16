package com.wonders.business.user.dao;


import com.wonders.business.user.model.YsMpUserPatient;
import com.wonders.business.user.param.PatientParam;
import com.wonders.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface YsMpUserPatientDao extends BaseDao<YsMpUserPatient> {

    List<YsMpUserPatient> findYsMpUserPatientList(PatientParam patientParam);


    int insert(YsMpUserPatient ysMpUserPatient);

}