package org.Nyit.VO;

import java.sql.Date;

import org.json.JSONObject;

public class CampusSubjectSeatEvaluationVO {

	private int campusSubjectSeatEvaluationId;
	private int sectionSubjectScheduleId;
	private int totalSeats;
	private int seatsAvailable;
	private String startDate;
	private String endDate;
	private String sectionName;
	private String subjectName;
	private String subjectCode;
	private String majorCode;
	private Date createdDate;
	private String createdBy;
	private String status;


	public int getCampusSubjectSeatEvaluationId() {
		return campusSubjectSeatEvaluationId;
	}

	public void setCampusSubjectSeatEvaluationId(int campusSubjectSeatEvaluationId) {
		this.campusSubjectSeatEvaluationId = campusSubjectSeatEvaluationId;
	}

	public int getSectionSubjectScheduleId() {
		return sectionSubjectScheduleId;
	}

	public void setSectionSubjectScheduleId(int sectionSubjectScheduleId) {
		this.sectionSubjectScheduleId = sectionSubjectScheduleId;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public JSONObject getJSONObject() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("campus_subject_seat_evaluation_Id", this.campusSubjectSeatEvaluationId);
		jsonObject.put("section_subject_schedule_Id", this.sectionSubjectScheduleId);
		jsonObject.put("total_seats", this.totalSeats);
		jsonObject.put("seats_available", this.seatsAvailable);
		jsonObject.put("start_Date", this.startDate);
		jsonObject.put("end_Date", this.endDate);
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
