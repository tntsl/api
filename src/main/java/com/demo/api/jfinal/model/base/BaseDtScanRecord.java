package com.demo.api.jfinal.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseDtScanRecord<M extends BaseDtScanRecord<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setAccount(java.lang.String account) {
		set("account", account);
	}
	
	public java.lang.String getAccount() {
		return getStr("account");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public void setOrderId(java.lang.String orderId) {
		set("order_id", orderId);
	}
	
	public java.lang.String getOrderId() {
		return getStr("order_id");
	}

	public void setType(java.lang.Integer type) {
		set("type", type);
	}
	
	public java.lang.Integer getType() {
		return getInt("type");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}
	
	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setProjectName(java.lang.String projectName) {
		set("project_name", projectName);
	}
	
	public java.lang.String getProjectName() {
		return getStr("project_name");
	}

	public void setStageName(java.lang.String stageName) {
		set("stage_name", stageName);
	}
	
	public java.lang.String getStageName() {
		return getStr("stage_name");
	}

	public void setBuilding(java.lang.String building) {
		set("building", building);
	}
	
	public java.lang.String getBuilding() {
		return getStr("building");
	}

	public void setProjectId(java.lang.String projectId) {
		set("project_id", projectId);
	}
	
	public java.lang.String getProjectId() {
		return getStr("project_id");
	}

	public void setStageId(java.lang.String stageId) {
		set("stage_id", stageId);
	}
	
	public java.lang.String getStageId() {
		return getStr("stage_id");
	}

	public void setBuildingId(java.lang.String buildingId) {
		set("building_id", buildingId);
	}
	
	public java.lang.String getBuildingId() {
		return getStr("building_id");
	}

}
