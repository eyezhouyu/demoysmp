package com.wonders.business.user.model;

import com.wonders.sys.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;


/**
 * @ClassName:请填写当前类名
 * @Description:请填写类说明 (ys_mp_user_patient)
 * @author:  请填写作者姓名
 * @date: 2019年09月12日  10:13:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value="请填写说明")
public class YsMpUserPatient extends BaseModel {
    /**
     * 
     */
    @ApiModelProperty(value="")
    private String id;

    /**
     * 
     */
    @ApiModelProperty(value="")
    private String userId;

    /**
     * 
     */
    @ApiModelProperty(value="")
    private String hzgx;

    /**
     * 
     */
    @ApiModelProperty(value="")
    private String hbsc;

    /**
     * 
     */
    @ApiModelProperty(value="")
    private String sfjz;

    /**
     * 
     */
    @ApiModelProperty(value="")
    private String jzyymc;

    /**
     * 
     */
    @ApiModelProperty(value="")
    private String jzksmc;

    /**
     * 
     */
    @ApiModelProperty(value="")
    private String sfzzsyyw;

    /**
     * 
     */
    @ApiModelProperty(value="")
    private String ywmc;

    /**
     * 
     */
    @ApiModelProperty(value="")
    private String sfyzdjbs;

    /**
     * 
     */
    @ApiModelProperty(value="")
    private String zdgwbsms;

    /**
     * 
     */
    @ApiModelProperty(value="")
    private String sfygms;

    /**
     * 
     */
    @ApiModelProperty(value="")
    private String gmyw;

    /**
     * 
     */
    @ApiModelProperty(value="")
    private String bqms;

    /**
     * 
     */
    @ApiModelProperty(value="")
    private String examine;

    /**
     * 
     */
    @ApiModelProperty(value="")
    private LocalDateTime createTime;

    /**
     * 
     */
    @ApiModelProperty(value="")
    private LocalDateTime updateTime;

    /**
     * 
     */
    @ApiModelProperty(value="")
    private String creator;

    /**
     * 
     */
    @ApiModelProperty(value="")
    private String updateUser;

    /**
     * 
     * @return id 
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return user_id 
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId 
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 
     * @return hzgx 
     */
    public String getHzgx() {
        return hzgx;
    }

    /**
     * 
     * @param hzgx 
     */
    public void setHzgx(String hzgx) {
        this.hzgx = hzgx;
    }

    /**
     * 
     * @return hbsc 
     */
    public String getHbsc() {
        return hbsc;
    }

    /**
     * 
     * @param hbsc 
     */
    public void setHbsc(String hbsc) {
        this.hbsc = hbsc;
    }

    /**
     * 
     * @return sfjz 
     */
    public String getSfjz() {
        return sfjz;
    }

    /**
     * 
     * @param sfjz 
     */
    public void setSfjz(String sfjz) {
        this.sfjz = sfjz;
    }

    /**
     * 
     * @return jzyymc 
     */
    public String getJzyymc() {
        return jzyymc;
    }

    /**
     * 
     * @param jzyymc 
     */
    public void setJzyymc(String jzyymc) {
        this.jzyymc = jzyymc;
    }

    /**
     * 
     * @return jzksmc 
     */
    public String getJzksmc() {
        return jzksmc;
    }

    /**
     * 
     * @param jzksmc 
     */
    public void setJzksmc(String jzksmc) {
        this.jzksmc = jzksmc;
    }

    /**
     * 
     * @return sfzzsyyw 
     */
    public String getSfzzsyyw() {
        return sfzzsyyw;
    }

    /**
     * 
     * @param sfzzsyyw 
     */
    public void setSfzzsyyw(String sfzzsyyw) {
        this.sfzzsyyw = sfzzsyyw;
    }

    /**
     * 
     * @return ywmc 
     */
    public String getYwmc() {
        return ywmc;
    }

    /**
     * 
     * @param ywmc 
     */
    public void setYwmc(String ywmc) {
        this.ywmc = ywmc;
    }

    /**
     * 
     * @return sfyzdjbs 
     */
    public String getSfyzdjbs() {
        return sfyzdjbs;
    }

    /**
     * 
     * @param sfyzdjbs 
     */
    public void setSfyzdjbs(String sfyzdjbs) {
        this.sfyzdjbs = sfyzdjbs;
    }

    /**
     * 
     * @return zdgwbsms 
     */
    public String getZdgwbsms() {
        return zdgwbsms;
    }

    /**
     * 
     * @param zdgwbsms 
     */
    public void setZdgwbsms(String zdgwbsms) {
        this.zdgwbsms = zdgwbsms;
    }

    /**
     * 
     * @return sfygms 
     */
    public String getSfygms() {
        return sfygms;
    }

    /**
     * 
     * @param sfygms 
     */
    public void setSfygms(String sfygms) {
        this.sfygms = sfygms;
    }

    /**
     * 
     * @return gmyw 
     */
    public String getGmyw() {
        return gmyw;
    }

    /**
     * 
     * @param gmyw 
     */
    public void setGmyw(String gmyw) {
        this.gmyw = gmyw;
    }

    /**
     * 
     * @return bqms 
     */
    public String getBqms() {
        return bqms;
    }

    /**
     * 
     * @param bqms 
     */
    public void setBqms(String bqms) {
        this.bqms = bqms;
    }

    /**
     * 
     * @return examine 
     */
    public String getExamine() {
        return examine;
    }

    /**
     * 
     * @param examine 
     */
    public void setExamine(String examine) {
        this.examine = examine;
    }

    /**
     * 
     * @return create_time 
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime 
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     * @return update_time 
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     * @param updateTime 
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 
     * @return creator 
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 
     * @param creator 
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 
     * @return update_user 
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 
     * @param updateUser 
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}