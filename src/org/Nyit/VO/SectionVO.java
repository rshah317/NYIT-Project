package org.Nyit.VO;

import java.sql.Date;

import org.json.JSONObject;

public class SectionVO {
	
	private int sectionId;
	private String sectionName;
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

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public JSONObject getJSONObject() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("section_Id",this.sectionId);
		jsonObject.put("section_Name", this.sectionName);
		jsonObject.put("created_By",this.createdBy);
		jsonObject.put("created_Date",this.createdDate);
		jsonObject.put("status",this.status);
		return jsonObject;
	}

}
