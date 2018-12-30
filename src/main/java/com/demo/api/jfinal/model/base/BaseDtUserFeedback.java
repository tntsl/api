package com.demo.api.jfinal.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseDtUserFeedback<M extends BaseDtUserFeedback<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setRate(java.lang.Integer rate) {
		set("rate", rate);
	}
	
	public java.lang.Integer getRate() {
		return getInt("rate");
	}

	public void setTag(java.lang.String tag) {
		set("tag", tag);
	}
	
	public java.lang.String getTag() {
		return getStr("tag");
	}

	public void setComment(java.lang.String comment) {
		set("comment", comment);
	}
	
	public java.lang.String getComment() {
		return getStr("comment");
	}

	public void setCity(java.lang.Integer city) {
		set("city", city);
	}
	
	public java.lang.Integer getCity() {
		return getInt("city");
	}

	public void setBelongCompany(java.lang.String belongCompany) {
		set("belong_company", belongCompany);
	}
	
	public java.lang.String getBelongCompany() {
		return getStr("belong_company");
	}

	public void setProject(java.lang.String project) {
		set("project", project);
	}
	
	public java.lang.String getProject() {
		return getStr("project");
	}

	public void setBelongAccount(java.lang.Integer belongAccount) {
		set("belong_account", belongAccount);
	}
	
	public java.lang.Integer getBelongAccount() {
		return getInt("belong_account");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}
	
	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setType(java.lang.Integer type) {
		set("type", type);
	}
	
	public java.lang.Integer getType() {
		return getInt("type");
	}

	public void setOrderId(java.lang.String orderId) {
		set("order_id", orderId);
	}
	
	public java.lang.String getOrderId() {
		return getStr("order_id");
	}

}
