package com.demo.api.jfinal.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseScOrder<M extends BaseScOrder<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setOrderId(java.lang.String orderId) {
		set("order_id", orderId);
	}
	
	public java.lang.String getOrderId() {
		return getStr("order_id");
	}

	public void setCompanyId(java.lang.String companyId) {
		set("company_id", companyId);
	}
	
	public java.lang.String getCompanyId() {
		return getStr("company_id");
	}

	public void setCode(java.lang.String code) {
		set("code", code);
	}
	
	public java.lang.String getCode() {
		return getStr("code");
	}

	public void setCodeType(java.lang.Integer codeType) {
		set("code_type", codeType);
	}
	
	public java.lang.Integer getCodeType() {
		return getInt("code_type");
	}

	public void setBelongAccount(java.lang.Integer belongAccount) {
		set("belong_account", belongAccount);
	}
	
	public java.lang.Integer getBelongAccount() {
		return getInt("belong_account");
	}

	public void setCreator(java.lang.String creator) {
		set("creator", creator);
	}
	
	public java.lang.String getCreator() {
		return getStr("creator");
	}

	public void setUseCondition(java.lang.String useCondition) {
		set("use_condition", useCondition);
	}
	
	public java.lang.String getUseCondition() {
		return getStr("use_condition");
	}

	public void setConditionDate(java.util.Date conditionDate) {
		set("condition_date", conditionDate);
	}
	
	public java.util.Date getConditionDate() {
		return get("condition_date");
	}

	public void setTitle(java.lang.String title) {
		set("title", title);
	}
	
	public java.lang.String getTitle() {
		return getStr("title");
	}

	public void setQrCode(java.lang.String qrCode) {
		set("QR_code", qrCode);
	}
	
	public java.lang.String getQrCode() {
		return getStr("QR_code");
	}

	public void setUserMessageId(java.lang.Integer userMessageId) {
		set("user_message_id", userMessageId);
	}
	
	public java.lang.Integer getUserMessageId() {
		return getInt("user_message_id");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}
	
	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setBelongProject(java.lang.String belongProject) {
		set("belong_project", belongProject);
	}
	
	public java.lang.String getBelongProject() {
		return getStr("belong_project");
	}

	public void setDelFlag(java.lang.Boolean delFlag) {
		set("del_flag", delFlag);
	}
	
	public java.lang.Boolean getDelFlag() {
		return get("del_flag");
	}

	public void setDelComent(java.lang.String delComent) {
		set("del_coment", delComent);
	}
	
	public java.lang.String getDelComent() {
		return getStr("del_coment");
	}

	public void setConditionType(java.lang.Integer conditionType) {
		set("condition_type", conditionType);
	}
	
	public java.lang.Integer getConditionType() {
		return getInt("condition_type");
	}

	public void setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
	}
	
	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

}
