package com.demo.api.jfinal.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseDtReadme<M extends BaseDtReadme<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setCompanyId(java.lang.String companyId) {
		set("company_id", companyId);
	}
	
	public java.lang.String getCompanyId() {
		return getStr("company_id");
	}

	public void setStageId(java.lang.String stageId) {
		set("stage_id", stageId);
	}
	
	public java.lang.String getStageId() {
		return getStr("stage_id");
	}

	public void setReadmeType(java.lang.String readmeType) {
		set("readme_type", readmeType);
	}
	
	public java.lang.String getReadmeType() {
		return getStr("readme_type");
	}

	public void setContent(java.lang.String content) {
		set("content", content);
	}
	
	public java.lang.String getContent() {
		return getStr("content");
	}

	public void setRemarks(java.lang.String remarks) {
		set("remarks", remarks);
	}
	
	public java.lang.String getRemarks() {
		return getStr("remarks");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}
	
	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
	}
	
	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

	public void setDelFlag(java.lang.Boolean delFlag) {
		set("del_flag", delFlag);
	}
	
	public java.lang.Boolean getDelFlag() {
		return get("del_flag");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public void setDescription(java.lang.String description) {
		set("description", description);
	}
	
	public java.lang.String getDescription() {
		return getStr("description");
	}

	public void setCertificateType(java.lang.Integer certificateType) {
		set("certificate_type", certificateType);
	}
	
	public java.lang.Integer getCertificateType() {
		return getInt("certificate_type");
	}

	public void setBuilding(java.lang.String building) {
		set("building", building);
	}
	
	public java.lang.String getBuilding() {
		return getStr("building");
	}

}
