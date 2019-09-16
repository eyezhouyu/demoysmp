package com.wonders.sys.utils;

import com.wonders.business.user.model.User;
import com.wonders.common.utils.JacksonUtil;
import org.apache.shiro.SecurityUtils;

/**
 * @author : zhouyu
 * @date : 2019/9/9 15:35
 * @description : 用户信息获取工具类
 */
public class UserUtil {

    /**
     * getUserInfo
     * @date 2019/9/9
     * 
     **/
    public static User getUserInfo(boolean ...isFresh) {
        if (isFresh.length<=0||isFresh[0]==false){
            return JacksonUtil.readValue(JacksonUtil.toJSon(SecurityUtils.getSubject().getPrincipal()),User.class);
        }else {
            return BeanFactoryFixed.getBaseService(User.class).findById(getUserId());
        }
    }

    /**
     * getUserId
     * @date 2019/9/9
     * @return java.lang.String
     **/
    public static String getUserId() {
		if (!SecurityUtils.getSubject().isAuthenticated()){
            return "SYSTEM";
        }
        return getUserInfo().getId();
    }
}
