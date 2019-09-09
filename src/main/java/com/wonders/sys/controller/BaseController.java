package com.wonders.sys.controller;

import com.wonders.business.user.basic.BaseResult;
import com.wonders.sys.enums.ResultMessage;
import com.wonders.sys.model.BaseModel;
import com.wonders.sys.service.BaseService;
import com.wonders.sys.utils.BeanFactoryFixed;
import com.wonders.sys.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : zhouyu
 * @date : 2019/9/9
 * @description : Controller 全局增删改查
 * @param <E>
 */
public interface BaseController<E extends BaseModel> {

    /**
     * 全局新增
     * @param model model
     * @return int
     */
    @ApiOperation(
            value = "数据保存",
            notes = "根据ID信息新增或更新数据",
            response = BaseResult.class)
    @ApiResponse(code = 200, message = "后台返回成功")
    @PostMapping("/save")
    default BaseResult save(@RequestBody E model){
        int i;
        if (StringUtils.isEmpty(model.getId())){
            i = getService().insert(model);
        }else {
            i = getService().updateByPrimaryKeySelective(model);
        }
        return ResultUtil.getCodeNonData(i>0?HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR,i>0?ResultMessage.SAVE_SUCCESS.getMessage():ResultMessage.SAVE_ERROR.getMessage());

    }

    /**
     * 全局删除
     * @param id id
     * @return int
     */
    @ApiOperation(
            value = "数据删除",
            notes = "根据ID信息删除数据",
            response = BaseResult.class)
    @ApiResponse(code = 200, message = "后台返回成功")
    @PostMapping("/delete")
    default BaseResult delete(@ApiParam(required = true)@RequestBody String id, HttpServletRequest request){
        if (StringUtils.isEmpty(id)||id.matches("\\{\\}")){
            id = request.getParameter("id");
        }
        int i = getService().deleteById(id);
        return ResultUtil.getCodeNonData(i>0? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR,i>0? ResultMessage.DELETE_SUCCESS.getMessage():ResultMessage.DELETE_ERROR.getMessage());
    }

    /**
     * query
     * @date 2019/3/19
     * @param model model
     * @return top.ywhome.framework.basic.result.BaseResult<java.util.List < E>>
     **/
    @ApiOperation(
            value = "数据查询",
            notes = "根据数据对象信息查询数据",
            response = BaseResult.class)
    @ApiResponse(code = 200, message = "后台返回成功")
    @PostMapping("/findByModel")
    default BaseResult query(@RequestBody @ApiParam(required = true) E model){
        List<E> list = getService().findByModel(model);
        return ResultUtil.getDataResult(list,ResultMessage.QUERY_SUCCESS.getMessage());
    }


    /**
     * 获取service
     * @return BaseService
     */
    default BaseService<E> getService() {
        return BeanFactoryFixed.getBaseService(getModelClass());
    }

    /**
     * 获取model class
     * @return Class
     */
    default Class<E> getModelClass(){
        return BeanFactoryFixed.getModelClass(this.getClass());
    }


}
