package org.Nyit.VO;

import java.sql.Date;

import org.json.JSONObject;

public class MajorVO {

	private int majorId;
	private String majorName;
	private String majorCode;
	private Date createdDate;
	private String createdBy;
	private String status;

	public int getMajorId() {
		return majorId;
	}

	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
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

	public String getMajorCode() {
		return majorCode;
	}

	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}

	public JSONObject getJSONObject() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("major_Id", this.majorId);
		jsonObject.put("major_Name", this.majorName);
		jsonObject.put("major_Code", this.majorCode);
		jsonObject.put("created_Date", this.createdDate);
		jsonObject.put("created_By", this.createdBy);
		jsonObject.put("status", this.status);
		return jsonObject;
	}

}
