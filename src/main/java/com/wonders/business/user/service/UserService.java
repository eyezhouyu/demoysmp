package com.wonders.business.user.service;

import com.wonders.business.user.basic.BaseResult;
import com.wonders.business.user.dao.UserDao;
import com.wonders.business.user.model.User;
import com.wonders.sys.enums.ResultMessage;
import com.wonders.sys.service.BaseService;
import com.wonders.sys.utils.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


/**
 * @author zhouyu
 * @date 2019/9/9
 */
@Service
public class UserService extends BaseService<User> {
    private static final long serialVersionUID = -1371998901452889235L;

    /**
     * login
     * @date 2019/4/10
     * @param username username
     * @param password password
     * @return top.ywhome.framework.basic.result.BaseResult
     **/
    public BaseResult login(String username, String password, boolean isRememberMe){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        token.setRememberMe(!ObjectUtils.isEmpty(isRememberMe) && isRememberMe);
        BaseResult baseResult;
        try {
            subject.login(token);
            baseResult = ResultUtil.getDataResult(subject.getSession().getId(),ResultMessage.LOGIN_SUCCESS.getMessage());
        } catch (IncorrectCredentialsException e) {
            baseResult = ResultUtil.getFailNonData(ResultMessage.PASSWORD_ERROR.getMessage());
        } catch (LockedAccountException e) {
            baseResult = ResultUtil.getFailNonData(ResultMessage.ACCOUNT_LOCKED.getMessage());
        } catch (AuthenticationException e) {
            baseResult = ResultUtil.getFailNonData(ResultMessage.ACCOUNT_NOEXIST.getMessage());
        } catch (Exception e) {
            baseResult = ResultUtil.getFailNonData(ResultMessage.LOGIN_ERROR.getMessage());
        }
        return baseResult;
    }


    /**
     * getUserInfoDao
     * @date 2019/7/13
     * 
     * @return top.ywhome.business.user.dao.UserInfoDao
     **/
    protected UserDao getUserInfoDao(){
        return (UserDao)getBaseDao();
    }
}
