package com.demo.api.jfinal.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseDtMaterials<M extends BaseDtMaterials<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setMaterialType(java.lang.Integer materialType) {
		set("material_type", materialType);
	}
	
	public java.lang.Integer getMaterialType() {
		return getInt("material_type");
	}

	public void setOrderBy(java.lang.Integer orderBy) {
		set("order_by", orderBy);
	}
	
	public java.lang.Integer getOrderBy() {
		return getInt("order_by");
	}

	public void setBelongContract(java.lang.Integer belongContract) {
		set("belong_contract", belongContract);
	}
	
	public java.lang.Integer getBelongContract() {
		return getInt("belong_contract");
	}

	public void setUploadTime(java.util.Date uploadTime) {
		set("upload_time", uploadTime);
	}
	
	public java.util.Date getUploadTime() {
		return get("upload_time");
	}

	public void setDenialReason(java.lang.String denialReason) {
		set("denial_reason", denialReason);
	}
	
	public java.lang.String getDenialReason() {
		return getStr("denial_reason");
	}

	public void setAuditorId(java.lang.String auditorId) {
		set("auditor_id", auditorId);
	}
	
	public java.lang.String getAuditorId() {
		return getStr("auditor_id");
	}

	public void setAuditorName(java.lang.String auditorName) {
		set("auditor_name", auditorName);
	}
	
	public java.lang.String getAuditorName() {
		return getStr("auditor_name");
	}

	public void setCheckResult(java.lang.Boolean checkResult) {
		set("check_result", checkResult);
	}
	
	public java.lang.Boolean getCheckResult() {
		return get("check_result");
	}

	public void setCheckRemark(java.lang.String checkRemark) {
		set("check_remark", checkRemark);
	}
	
	public java.lang.String getCheckRemark() {
		return getStr("check_remark");
	}

	public void setCheckTime(java.util.Date checkTime) {
		set("check_time", checkTime);
	}
	
	public java.util.Date getCheckTime() {
		return get("check_time");
	}

	public void setUserMessageId(java.lang.Integer userMessageId) {
		set("user_message_id", userMessageId);
	}
	
	public java.lang.Integer getUserMessageId() {
		return getInt("user_message_id");
	}

	public void setDelFlag(java.lang.Boolean delFlag) {
		set("del_flag", delFlag);
	}
	
	public java.lang.Boolean getDelFlag() {
		return get("del_flag");
	}

	public void setTimeReview(java.util.Date timeReview) {
		set("time_review", timeReview);
	}
	
	public java.util.Date getTimeReview() {
		return get("time_review");
	}

}