package com.wonders.sys.authorize;

import com.wonders.business.user.basic.BaseResult;
import com.wonders.common.utils.JacksonUtil;
import com.wonders.sys.enums.ResultMessage;
import com.wonders.sys.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : zhouyu
 * @date : 2019/9/9
 * @description : 验证异常捕获
 * */
@Slf4j
public class AuthorizeExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        log.error(ex.getLocalizedMessage());
        ModelAndView mv = new ModelAndView();
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        BaseResult baseResult;
        if (ex instanceof UnauthenticatedException) {
            baseResult = ResultUtil.getCodeNonData(HttpStatus.REQUEST_TIMEOUT, ResultMessage.TOKEN_INVALID.getMessage());
        } else if (ex instanceof UnauthorizedException) {
            baseResult = ResultUtil.getCodeNonData(HttpStatus.NON_AUTHORITATIVE_INFORMATION,ResultMessage.NOT_AUTHORIZE.getMessage());
        }else {
            baseResult = ResultUtil.getCodeNonData(HttpStatus.NON_AUTHORITATIVE_INFORMATION, ResultMessage.AUTHORIZE_ERROR.getMessage());
        }
        view.setAttributesCSV(JacksonUtil.toJSon(baseResult));
        mv.setView(view);
        return mv;
    }
}
