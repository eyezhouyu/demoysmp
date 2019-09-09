package com.wonders.sys.authorize;

import com.wonders.business.user.model.User;
import com.wonders.business.user.service.UserService;
import com.wonders.sys.enums.AccountStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * @author : 子路
 * @date : 2019/3/15
 * @description :
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    /**
     * get role and permission by user
     * @param principals principals
     * @return AuthorizationInfo AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.debug("权限配置-->AuthorizingRealm.doGetAuthorizationInfo()");
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     * @param token token
     * @return AuthenticationInfo AuthenticationInfo
     * @throws AuthenticationException AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        log.debug("UserRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        log.debug(token.getCredentials().toString());
        //通过username从数据库中查找 User对象
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = new User();
        user.setUserName(username);
        User userInfo = userService.findByModel(user).get(0);
        if (userInfo == null) {
            return null;
        }
        if (userInfo.getStatus() == AccountStatus.LOCKED) {
            throw new LockedAccountException();
        }else if (userInfo.getStatus() == AccountStatus.CANCEL){
            throw new UnknownAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo,
                userInfo.getPassword(),
                getName()
        );
        return authenticationInfo;
    }

}