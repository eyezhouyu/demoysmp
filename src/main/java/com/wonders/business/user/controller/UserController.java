package com.wonders.business.user.controller;


import com.wonders.business.user.basic.BaseResult;
import com.wonders.business.user.model.DoctorUser;
import com.wonders.business.user.model.PatientUser;
import com.wonders.business.user.model.User;
import com.wonders.business.user.service.DoctorUserService;
import com.wonders.business.user.service.PatientUserService;
import com.wonders.business.user.service.UserService;
import com.wonders.business.user.utils.WechatQrCodeUtils;
import com.wonders.common.utils.JacksonUtil;
import com.wonders.common.utils.RequestUtil;
import com.wonders.common.utils.UUIDUtil;
import com.wonders.common.wechat.decode.WxCryptUtils;
import com.wonders.sys.controller.BaseController;
import com.wonders.sys.enums.AccountStatus;
import com.wonders.sys.enums.AccountType;
import com.wonders.sys.utils.ResultUtil;
import com.wonders.sys.utils.WechatTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/usr")
@Api(value = "/usr", description = "用户管理",tags = {"用户管理"})
public class UserController implements BaseController<User>{



    @Autowired
    private DoctorUserService doctorService;
    @Autowired
    private PatientUserService patientService;

    @ApiOperation(
            value = "微信登录接口",
            notes = "微信登录接口",
            response = BaseResult.class)
    @PostMapping("/wxlogin")
    public BaseResult wxlogin(@RequestBody Map<String,String> map, HttpServletResponse response){
        try{
            ResponseEntity<String> responseEntity = RequestUtil.httpRequest("https://api.weixin.qq.com/sns/jscode2session?appid=wxdc9953f125fd61af&secret=699503d825266333ff0addb7ab106c70&js_code="+map.get("code")+"&grant_type=authorization_code",
                                                                            "",null, HttpMethod.GET);

            Map<String,String> wxloginParam = JacksonUtil.readValue(responseEntity.getBody(),Map.class);

            Map<String,String> wxUsrInfo = JacksonUtil.readValue(WxCryptUtils.decrypt(map.get("encryptedData"),map.get("iv"),wxloginParam.get("session_key")),Map.class);

            User usr = new User();
            usr.setOpenId(wxUsrInfo.get("openId"));
            usr.setUnionId(wxUsrInfo.get("unionId"));

            List<User> usrList = getUserService().findByModel(usr);

            String userId = UUIDUtil.getUUID(usr);


            /**
             * 用户不存在
             * 保存用户数据
             */
            if(usrList.size()<1){

                try{
                    createQrcode(userId,response);
                }catch (Exception e){
                    e.printStackTrace();
                }
                usr.setId(userId);
                usr.setUserName(wxUsrInfo.get("nickName")+new Random().nextInt(10000));
                usr.setPassword(new Md5Hash(usr.getUserName(),"",2).toString());
                usr.setGender(String.valueOf(wxUsrInfo.get("gender")));
                usr.setStatus(AccountStatus.OK);
                usr.setNickName(wxUsrInfo.get("nickName"));
                usr.setUserIcon(wxUsrInfo.get("avatarUrl"));
                usr.setUserAddress(wxUsrInfo.get("country")+wxUsrInfo.get("province")+wxUsrInfo.get("city"));
                getService().insert(usr);
                wxUsrInfo.put("userId",userId);
            }else{
                usr = usrList.get(0);
                wxUsrInfo.put("userId",usr.getId());
            }
            BaseResult baseResult = getUserService().login(usr.getUserName(),usr.getUserName(),true);
            wxUsrInfo.put("token",baseResult.getData().toString());
            baseResult.setData(wxUsrInfo);
            return baseResult;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(
            value = "根据类型保存用户信息",
            notes = "根据类型保存用户信息",
            response = BaseResult.class)
    @PostMapping("/addByUsrType")
    public BaseResult addByUsrType(@RequestBody Map<String,String> map){

        Map<String,String> params = JacksonUtil.readValue(map.get("saveParam"),Map.class);
        String userId = params.get("userId");

        User usr = new User();
        usr.setId(userId);
        List<User> usrList = getUserService().findByModel(usr);

        if(usrList.size()>0){
            User user_ = usrList.get(0);
            user_.setUserType(params.get("usrType").toString());
            getService().updateByPrimaryKeySelective(user_);
        }



        //用户类型为医生
        if(AccountType.DOCTOR.getType().equals(params.get("usrType").toString())){
            DoctorUser du = new DoctorUser();
            du.setId(UUIDUtil.getUUID(du));
            du.setHospital(params.get("hospital"));
            du.setPosition(params.get("position"));
            du.setLabel(params.get("label"));
            du.setUserId(userId);

            doctorService.insert(du);
        }
        if(AccountType.PATIENT.equals(params.get("usrType").toString())){
            PatientUser pu = new PatientUser();
        }



        return ResultUtil.getNonDate("ok");
    }


    @ApiOperation(
            value = "根据USERID获取用户类型信息",
            notes = "根据USERID获取用户类型信息",
            response = BaseResult.class)
    @PostMapping("/findUserInfoByUserId")
    public BaseResult findUserInfoByUserId(@RequestBody Map<String,String> map){
        BaseResult baseResult = new BaseResult();
        baseResult.setStatusCode(500);
        User usr = new User();
        usr.setId(map.get("userId").toString());
        List<User> usrList = getUserService().findByModel(usr);
        if(usrList.size()>0){
            User user_ = usrList.get(0);

            //用户类型为医生
            if(AccountType.DOCTOR.getType().equals(user_.getUserType())){
                DoctorUser du = new DoctorUser();
                du.setUserId(map.get("userId").toString());
                List<DoctorUser> doctorusersList = doctorService.findByModel(du);
                if(doctorusersList.size()>0){
                    baseResult.setStatusCode(200);
                    baseResult.setData(doctorusersList.get(0));
                }
            }

            if(AccountType.PATIENT.getType().equals(user_.getUserType())){
                PatientUser pu = new PatientUser();
                pu.setUserId(map.get("userId").toString());
                List<PatientUser> patientusersList = patientService.findByModel(pu);
                if(patientusersList.size()>0){
                    baseResult.setStatusCode(200);
                    baseResult.setData(patientusersList.get(0));
                }
            }
        }
        return baseResult;
    }


    public void createQrcode(String userId,HttpServletResponse response) {
       try{
           String accessToken = WechatTokenUtils.getWxAccessToken();
           WechatQrCodeUtils.createForeverTicket(accessToken,userId,response);
       }catch (Exception e){
           e.printStackTrace();
       }
    }


        /**
         * getUserInfoService
         * @date 2019/4/10
         *
         * @return top.ywhome.business.user.service.UserInfoService
         **/
    private UserService getUserService() {
        return (UserService)getService();
    }
}
