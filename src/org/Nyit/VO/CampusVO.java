package org.Nyit.VO;

import java.sql.Date;

import org.json.JSONObject;

public class CampusVO {

	private int campusId;
	private String campusName;
	private Date createdDate;
	private String createdBy;
	private String status;

	public int getCampusId() {
		return campusId;
	}

	public void setCampusId(int campusId) {
		this.campusId = campusId;
	}

	public String getCampusName() {
		return campusName;
	}

	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date date) {
		this.createdDate = date;
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
		jsonObject.put("campus_Id",this.campusId);
		jsonObject.put("campus_Name", this.campusName);
		jsonObject.put("created_Date",this.createdDate);
		jsonObject.put("created_By", this.createdBy);
		jsonObject.put("status",this.status);
		return jsonObject;
	}
}
