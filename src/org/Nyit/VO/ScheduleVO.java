package org.Nyit.VO;

import java.sql.Date;
import org.json.JSONObject;

public class ScheduleVO {

	private int scheduleId;
	private int campus_subject_seat_evaluation_Id;
	private String day;
	private String time;
	private String sectionName;
	private String subjectName;
	private String subjectCode;
	private String majorCode;
	private Date createdDate;
	private String createdBy;
	private String status;
	private int subjectId;
	private int sectionId;
	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public int getCampus_subject_seat_evaluation_Id() {
		return campus_subject_seat_evaluation_Id;
	}

	public void setCampus_subject_seat_evaluation_Id(int campus_subject_seat_evaluation_Id) {
		this.campus_subject_seat_evaluation_Id = campus_subject_seat_evaluation_Id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
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

	public String getMajorCode() {
		return majorCode;
	}

	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}

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

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public JSONObject getJSONObject() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("schedule_Id", this.scheduleId);
		jsonObject.put("campus_subject_seat_evaluation_Id", this.campus_subject_seat_evaluation_Id);
		jsonObject.put("day", this.day);
		jsonObject.put("time", this.time);
		jsonObject.put("section_Id", this.sectionId);
		jsonObject.put("subject_Id", this.subjectId);
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
