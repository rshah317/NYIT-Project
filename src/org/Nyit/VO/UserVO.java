package org.Nyit.VO;

import org.json.JSONException;
import org.json.JSONObject;

public class UserVO {
	
	private int userId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String password;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public JSONObject getJSONObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("user_Id",this.userId);
		jsonObject.put("firstName",this.firstName);
		jsonObject.put("lastName",this.lastName);
		jsonObject.put("email_Id",this.emailId);
		jsonObject.put("password",this.password);
		return jsonObject;
	}
}
