package bean;

import java.io.Serializable;

public class AccountBean implements Serializable {

	private int accountCode;
	private String loginID;
	private String password;
	private String nickName;
	private String registerDate; //ここはString型にし、SQL側でDBの設定型に自動変換させた方が使いやすい
	private int admin;
	private int ghost;

	public int getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(int accountCode) {
		this.accountCode = accountCode;
	}
	public String getLoginID() {
		return loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public int getGhost() {
		return ghost;
	}
	public void setGhost(int ghost) {
		this.ghost = ghost;
	}




}
