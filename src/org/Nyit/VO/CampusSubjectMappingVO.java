package org.Nyit.VO;

import java.sql.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class CampusSubjectMappingVO {

	private int campusSubjectId;
	private int campusId;
	private int subjectId;
	private int majorId;
	private String majorName;
	private String majorCode;
	private String campusName;
	private String subjectName;
	private String subjectCode;
	private Date createdDate;
	private String createdBy;
	private String status;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCampusSubjectId() {
		return campusSubjectId;
	}

	public void setCampusSubjectId(int campusSubjectId) {
		this.campusSubjectId = campusSubjectId;
	}

	public int getCampusId() {
		return campusId;
	}

	public void setCampusId(int campusId) {
		this.campusId = campusId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getMajorId() {
		return majorId;
	}

	public void setMajorId(int value) {
		this.majorId = value;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getMajorCode() {
		return majorCode;
	}

	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}

	public String getCampusName() {
		return campusName;
	}

	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public JSONObject getJSONObject() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("campusSubject_Id", this.campusSubjectId);
		jsonObject.put("campus_Id", this.campusId);
		jsonObject.put("campus_Name", this.campusName);
		jsonObject.put("major_Name", this.majorName);
		jsonObject.put("major_Code", this.majorCode);
		jsonObject.put("subject_Id", this.subjectId);
		jsonObject.put("subject_Code", this.subjectCode);
		jsonObject.put("subject_Name", this.subjectName);
		jsonObject.put("created_Date", this.createdDate);
		jsonObject.put("created_By", this.createdBy);
		jsonObject.put("status", this.status);
		jsonObject.put("major_Id", this.majorId);
		return jsonObject;
	}
	public JSONObject getCampusSubjectJSONObject() throws Exception {
		JSONObject object = new JSONObject();
		object.put("campusSubject_Id", this.campusSubjectId);
		object.put("campus_Id", this.campusId);
		object.put("campus_Name", this.campusName);
		object.put("major_Name", this.majorName);
		object.put("major_Code", this.majorCode);
		object.put("subject_Id", this.subjectId);
		object.put("subject_Code", this.subjectCode);
		object.put("subject_Name", this.subjectName);
		object.put("created_Date", this.createdDate);
		object.put("created_By", this.createdBy);
		object.put("status", this.status);
		object.put("major_Id", this.majorId);
		return object;
	}
}