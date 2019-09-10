package com.wonders.business.user.service;

import com.wonders.business.user.model.DoctorUser;
import com.wonders.sys.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhouyu
 * @date 2019/9/9
 */
@Transactional(readOnly = true)
@Service
public class DoctorUserService extends BaseService<DoctorUser> {

}
