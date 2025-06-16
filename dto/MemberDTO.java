package academyboard.dto;

import java.sql.Date;

public class MemberDTO {
	private int mno;
	private String mname;
	private String id;
	private String pw;
	private String email;
	private Date regidate;
	
	
	
	
	public int getMno() {
		return mno;
	}
	public String getMname() {
		return mname;
	}
	public String getId() {
		return id;
	}
	public String getPw() {
		return pw;
	}
	public String getEmail() {
		return email;
	}
	public Date getRegidate() {
		return regidate;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}
	

}
