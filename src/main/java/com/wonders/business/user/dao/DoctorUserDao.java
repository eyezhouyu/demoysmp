package com.wonders.business.user.dao;

import com.wonders.business.user.model.DoctorUser;
import com.wonders.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author zhouyu
 * @date 2019/9/9
 */
@Mapper
public interface DoctorUserDao extends BaseDao<DoctorUser> {

}