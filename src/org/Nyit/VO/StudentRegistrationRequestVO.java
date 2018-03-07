package org.Nyit.VO;

import org.json.JSONObject;

public class StudentRegistrationRequestVO {

	private int studentRegistrationRequestId;
	private int student_nyit_Id;
	private String firstName;
	private String lastName;
	private String email_Id;
	private String academicProgram;
	private String campus;
	private String section;
	private String subjectName;
	private int student_Id;
	private int[] schedule_Id;
	private String status;
	private String approveDeclineBy;
	private int student_approved_Id;
	
	public int getStudent_nyit_Id() {
		return student_nyit_Id;
	}
	
	public void setStudent_nyit_Id(int student_nyit_Id) {
		this.student_nyit_Id = student_nyit_Id;
	}

	public int getStudentRegistrationRequestId() {
		return studentRegistrationRequestId;
	}

	public void setStudentRegistrationRequestId(int studentRegistrationRequestId) {
		this.studentRegistrationRequestId = studentRegistrationRequestId;
	}

	public int getStudent_Id() {
		return student_Id;
	}

	public void setStudent_Id(int student_Id) {
		this.student_Id = student_Id;
	}

	public int[] getSchedule_Id() {
		return schedule_Id;
	}

	public void setSchedule_Id(int[] schedule_Id) {
		this.schedule_Id = schedule_Id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApproveDeclineBy() {
		return approveDeclineBy;
	}

	public void setApproveDeclineBy(String approveDeclineBy) {
		this.approveDeclineBy = approveDeclineBy;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail_Id() {
		return email_Id;
	}

	public void setEmail_Id(String email_Id) {
		this.email_Id = email_Id;
	}

	public String getAcademicProgram() {
		return academicProgram;
	}

	public void setAcademicProgram(String academicProgram) {
		this.academicProgram = academicProgram;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getStudent_approved_Id() {
		return student_approved_Id;
	}

	public void setStudent_approved_Id(int student_approved_Id) {
		this.student_approved_Id = student_approved_Id;
	}

	public JSONObject getJSONObject() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("student_Request", this.studentRegistrationRequestId);
		jsonObject.put("student_Id", this.student_Id);
		jsonObject.put("schedule_Id", this.schedule_Id);
		jsonObject.put("status", this.status);
		jsonObject.put("approve/Decline_by", this.approveDeclineBy);
		jsonObject.put("student_nyit_Id", this.student_nyit_Id);
		jsonObject.put("first_Name", this.firstName);
		jsonObject.put("last_Name", this.lastName);
		jsonObject.put("email_Id", this.email_Id);
		jsonObject.put("academic", this.academicProgram);
		jsonObject.put("Subject", this.subjectName);
		jsonObject.put("campus", this.campus);
		jsonObject.put("section", this.section);
		jsonObject.put("student_approved_Id", this.student_approved_Id);
		return jsonObject;
	}

}
