package org.Nyit.VO;

import java.sql.Date;

import org.json.JSONObject;

public class SectionSubjectMappingVO {

	private int sectionSubjectScheduleId;
	private int campusSubjectId;
	private int[] sectionId;
	private String sectionName;
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

	public int getSectionSubjectScheduleId() {
		return sectionSubjectScheduleId;
	}

	public void setSectionSubjectScheduleId(int sectionSubjectScheduleId) {
		this.sectionSubjectScheduleId = sectionSubjectScheduleId;
	}

	public int getCampusSubjectId() {
		return campusSubjectId;
	}

	public void setCampusSubjectId(int campusSubjectId) {
		this.campusSubjectId = campusSubjectId;
	}

	public int[] getSectionId() {
		return sectionId;
	}

	public void setSectionId(int[] sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
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
		jsonObject.put("sectionSubjectSchedule_Id", this.sectionSubjectScheduleId);
		jsonObject.put("campusSubject_Id", this.campusSubjectId);
		jsonObject.put("section_Id", this.sectionId);
		jsonObject.put("section_Name", this.sectionName);
		jsonObject.put("subject_Name", this.subjectName);
		jsonObject.put("subject_Code", this.subjectCode);
		jsonObject.put("major_Code", this.majorCode);
		jsonObject.put("created_Date", this.createdDate);
		jsonObject.put("created_By", this.createdBy);
		jsonObject.put("status", this.status);
		return jsonObject;
	}

}
