package com.demo.api.jfinal.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseDtMetaValue<M extends BaseDtMetaValue<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setMetaId(java.lang.Integer metaId) {
		set("meta_id", metaId);
	}
	
	public java.lang.Integer getMetaId() {
		return getInt("meta_id");
	}

	public void setMetaName(java.lang.String metaName) {
		set("meta_name", metaName);
	}
	
	public java.lang.String getMetaName() {
		return getStr("meta_name");
	}

	public void setMetaValue(java.lang.String metaValue) {
		set("meta_value", metaValue);
	}
	
	public java.lang.String getMetaValue() {
		return getStr("meta_value");
	}

	public void setTemplateId(java.lang.Integer templateId) {
		set("template_id", templateId);
	}
	
	public java.lang.Integer getTemplateId() {
		return getInt("template_id");
	}

	public void setIndex(java.lang.String index) {
		set("index", index);
	}
	
	public java.lang.String getIndex() {
		return getStr("index");
	}

	public void setOrderId(java.lang.Integer orderId) {
		set("order_id", orderId);
	}
	
	public java.lang.Integer getOrderId() {
		return getInt("order_id");
	}

}
