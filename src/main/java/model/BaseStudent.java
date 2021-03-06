package model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseStudent<M extends BaseStudent<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setStuno(java.lang.Integer stuno) {
		set("stuno", stuno);
	}

	public java.lang.Integer getStuno() {
		return get("stuno");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setSex(java.lang.String sex) {
		set("sex", sex);
	}

	public java.lang.String getSex() {
		return get("sex");
	}

	public void setClassid(java.lang.Integer classid) {
		set("classid", classid);
	}

	public java.lang.Integer getClassid() {
		return get("classid");
	}

	public void setPhone(java.lang.String phone) {
		set("phone", phone);
	}

	public java.lang.String getPhone() {
		return get("phone");
	}

	public void setCreatedAt(java.lang.Integer createdAt) {
		set("created_at", createdAt);
	}

	public java.lang.Integer getCreatedAt() {
		return get("created_at");
	}

	public void setUpdatedAt(java.lang.Integer updatedAt) {
		set("updated_at", updatedAt);
	}

	public java.lang.Integer getUpdatedAt() {
		return get("updated_at");
	}

	public void setDeletedAt(java.lang.Integer deletedAt) {
		set("deleted_at", deletedAt);
	}

	public java.lang.Integer getDeletedAt() {
		return get("deleted_at");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}

	public java.lang.Integer getStatus() {
		return get("status");
	}

}
