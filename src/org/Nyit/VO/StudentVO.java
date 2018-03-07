package org.Nyit.VO;

import org.json.JSONObject;

public class StudentVO {

	private int studentId;
	private int studentInfoId;
	private int studentNyitId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String address;
	private String city;
	private String state;
	private long zipcode;
	private long homePhone;
	private long workPhone;
	private long cellPhone;
	private String academicProgram;
	private String term[];
	private String termName;
	private String subjectName;
	private String subject[];
	private String campus[];
	private String campusName;

	
	public String getCampusName() {
		return campusName;
	}

	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}

	public int getStudentInfoId() {
		return studentInfoId;
	}

	public void setStudentInfoId(int studentInfoId) {
		this.studentInfoId = studentInfoId;
	}

	public long getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(long cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getAcademicProgram() {
		return academicProgram;
	}

	public void setAcademicProgram(String academicProgram) {
		this.academicProgram = academicProgram;
	}

	public String[] getTerm() {
		return term;
	}

	public void setTerm(String[] term) {
		this.term = term;
	}

	public String[] getSubject() {
		return subject;
	}

	public void setSubject(String[] strs) {
		this.subject = strs;
	}

	public String[] getCampus() {
		return campus;
	}

	public void setCampus(String[] campus) {
		this.campus = campus;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getStudentNyitId() {
		return studentNyitId;
	}

	public void setStudentNyitId(int studentNyitId) {
		this.studentNyitId = studentNyitId;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getZipcode() {
		return zipcode;
	}

	public void setZipcode(long zipcode) {
		this.zipcode = zipcode;
	}

	public long getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(long homePhone) {
		this.homePhone = homePhone;
	}

	public long getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(long workPhone) {
		this.workPhone = workPhone;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public JSONObject getJSONObject() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("student_Id", this.studentId);
		jsonObject.put("student_nyit_Id", this.studentNyitId);
		jsonObject.put("first_Name", this.firstName);
		jsonObject.put("last_Name", this.lastName);
		jsonObject.put("email_Id", this.emailId);
		jsonObject.put("address", this.address);
		jsonObject.put("city", this.city);
		jsonObject.put("state", this.state);
		jsonObject.put("zipcode", this.zipcode);
		jsonObject.put("home_phoneNo", this.homePhone);
		jsonObject.put("work_phoneNo", this.workPhone);
		jsonObject.put("cell_phoneNo", this.cellPhone);
		jsonObject.put("academic", this.academicProgram);
		jsonObject.put("Term", this.term);
		jsonObject.put("Subject", this.subject);
		jsonObject.put("campus", this.campus);
		jsonObject.put("campusName", this.campusName);
		jsonObject.put("term_Name", this.termName);
		jsonObject.put("subject_Name", this.subjectName);
		return jsonObject;
	}

}