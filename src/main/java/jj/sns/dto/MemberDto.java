package jj.sns.dto;

import java.sql.Date;

	
public class MemberDto {
	private int uid;
	private String id;
	private String pw;
	private String session_id;
	private Date session_limit;
	
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public Date getSession_limit() {
		return session_limit;
	}
	public void setSession_limit(Date session_limit) {
		this.session_limit = session_limit;
	}
	
	
	
	
}
