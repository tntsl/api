package com.demo.api.jfinal.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseDtReadmeFiles<M extends BaseDtReadmeFiles<M>> extends Model<M> implements IBean {

	public void setReadmeId(java.lang.Integer readmeId) {
		set("readme_id", readmeId);
	}
	
	public java.lang.Integer getReadmeId() {
		return getInt("readme_id");
	}

	public void setFileId(java.lang.Integer fileId) {
		set("file_id", fileId);
	}
	
	public java.lang.Integer getFileId() {
		return getInt("file_id");
	}

	public void setDelFlag(java.lang.Boolean delFlag) {
		set("del_flag", delFlag);
	}
	
	public java.lang.Boolean getDelFlag() {
		return get("del_flag");
	}

}
