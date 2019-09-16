package com.wonders.business.user.controller;

import com.github.pagehelper.PageInfo;
import com.wonders.business.user.basic.BaseResult;
import com.wonders.business.user.model.YsMpUserPatient;
import com.wonders.business.user.param.PatientParam;
import com.wonders.business.user.service.YsMpUserPatientServie;
import com.wonders.common.utils.Constants;
import com.wonders.sys.utils.ResultUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userPatient")
@Api(value = "/userPatient", description = "我的患者",tags = {"我的患者"})
public class YsMpUserPatientController {


    @Autowired
    private YsMpUserPatientServie ysMpUserPatientServie;


    /**
     *订单查询
     * @param map
     * @return
     */
    @ApiOperation(
            value = "我的患者查询接口",
            notes = "我的患者查询接口",
            response = BaseResult.class)
    @ApiResponse(code = 200, message = "后台返回成功")
    @ApiImplicitParams({@ApiImplicitParam(name = "map",value = "医生工号",required = true)})
    @PostMapping("/findYsMpUserPatientList")
    public BaseResult<List<YsMpUserPatient>> findYsMpUserPatientList(@ApiParam(required = true)@RequestBody Map map){
        PatientParam patientParam  =new PatientParam();
        patientParam.setDoctorId(map.get("doctorId")+"");
        List<YsMpUserPatient>  list= ysMpUserPatientServie.findYsMpUserPatientList(patientParam);
         
        PageInfo<YsMpUserPatient> pageInfo = new PageInfo<>(list);
        return ResultUtil.getDataResult(pageInfo,null);
    }


    @RequestMapping(value = "save")
    @ResponseBody
    public Map<String, Object> save(@Valid @RequestBody YsMpUserPatient ysMpUserPatient, HttpServletRequest request, HttpServletResponse response  ) {
        Map<String, Object> resultMap = new HashMap();
        try{

            ysMpUserPatientServie.save(ysMpUserPatient);
            resultMap.put("code", Constants.SUCCESS_CODE);
            resultMap.put("message", "操作成功！");
        } catch (Exception e) {
            resultMap.put("code", Integer.valueOf(500));
            resultMap.put("message", "SQL异常或数据库服务器连接失败");
            resultMap.put("errors", e.getMessage());
        }
        return resultMap;
    }

}
