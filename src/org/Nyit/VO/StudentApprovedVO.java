package org.Nyit.VO;

import org.json.JSONObject;

public class StudentApprovedVO {

	private int student_approved_Id;
	private int student_registration_request_Id;
	private int student_Id;
	private int schedule_Id;
	private String status;
	private String ApprovedDeclined_by;

	public int getStudent_approved_Id() {
		return student_approved_Id;
	}

	public void setStudent_approved_Id(int student_approved_Id) {
		this.student_approved_Id = student_approved_Id;
	}

	public int getStudent_registration_request_Id() {
		return student_registration_request_Id;
	}

	public void setStudent_registration_request_Id(int student_registration_request_Id) {
		this.student_registration_request_Id = student_registration_request_Id;
	}

	public int getStudent_Id() {
		return student_Id;
	}

	public void setStudent_Id(int student_Id) {
		this.student_Id = student_Id;
	}

	public int getSchedule_Id() {
		return schedule_Id;
	}

	public void setSchedule_Id(int schedule_Id) {
		this.schedule_Id = schedule_Id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApprovedDeclined_by() {
		return ApprovedDeclined_by;
	}

	public void setApprovedDeclined_by(String approvedDeclined_by) {
		ApprovedDeclined_by = approvedDeclined_by;
	}
	public JSONObject getJSONObject() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("student_approved_Id", this.student_approved_Id);
		jsonObject.put("student_Id", this.student_Id);
		jsonObject.put("student_registration_request_Id", this.student_registration_request_Id);
		jsonObject.put("schedule_Id", this.schedule_Id);
		jsonObject.put("status", this.status);
		jsonObject.put("ApprovedDeclined_by", this.ApprovedDeclined_by);
		return jsonObject;
	}
}
