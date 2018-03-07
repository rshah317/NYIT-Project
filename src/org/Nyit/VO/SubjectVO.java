package org.Nyit.VO;

import java.sql.Date;
import org.json.JSONObject;

public class SubjectVO {
	
	private int subjectId;
	private String subjectCode;
	private String subjectName;
	private Date createdDate;
	private String createdBy;
	private String status;
	
	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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
		jsonObject.put("subject_Id", this.subjectId);
		jsonObject.put("subject_Code", this.subjectCode);
		jsonObject.put("subject_Name", this.subjectName);
		jsonObject.put("created_Date", this.createdDate);
		jsonObject.put("created_By", this.createdBy);
		jsonObject.put("status", this.status);
		return jsonObject;

	}

}
