package model;/******************************************************************************* * javaBeans * tbl_student --> TblStudent  * <table explanation> * @author 2016-03-11 10:35:27 *  */	public class TblStudent implements java.io.Serializable {	//field	/**  **/	private int id;	/**  **/	private int stuno;	/**  **/	private String name;	/**  **/	private String sex;	/**  **/	private int classid;	/**  **/	private String phone;	/**  **/	private int createdAt;	/**  **/	private int updatedAt;	/**  **/	private int deletedAt;	/**  **/	private int status;	//method	public int getId() {		return id;	}	public void setId(int id) {		this.id = id;	}	public int getStuno() {		return stuno;	}	public void setStuno(int stuno) {		this.stuno = stuno;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getSex() {		return sex;	}	public void setSex(String sex) {		this.sex = sex;	}	public int getClassid() {		return classid;	}	public void setClassid(int classid) {		this.classid = classid;	}	public String getPhone() {		return phone;	}	public void setPhone(String phone) {		this.phone = phone;	}	public int getCreatedAt() {		return createdAt;	}	public void setCreatedAt(int createdAt) {		this.createdAt = createdAt;	}	public int getUpdatedAt() {		return updatedAt;	}	public void setUpdatedAt(int updatedAt) {		this.updatedAt = updatedAt;	}	public int getDeletedAt() {		return deletedAt;	}	public void setDeletedAt(int deletedAt) {		this.deletedAt = deletedAt;	}	public int getStatus() {		return status;	}	public void setStatus(int status) {		this.status = status;	}	//return String[] filed; 	public String[] getField() {		return new String[]{"id","stuno","name","sex","classid","phone","createdAt","updatedAt","deletedAt","status"};	}}