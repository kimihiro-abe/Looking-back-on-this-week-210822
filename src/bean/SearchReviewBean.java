package bean;

import java.io.Serializable;

//adminでのみ使用するBean
public class SearchReviewBean implements Serializable {

	private int mediaCode;
	private String mediaTitle;
	private String mediaInfo;
	private int reviewCode;
	private int accountCode;
	//private String loginID;
	private String nickName;
	private String reviewContent;
	private String reviewDate; //ここはString型にし、SQL側でDBの設定型に自動変換させた方が使いやすい
	private int netabare;
	private int reviewGhost;

	public int getMediaCode() {
		return mediaCode;
	}
	public void setMediaCode(int mediaCode) {
		this.mediaCode = mediaCode;
	}
	public String getMediaTitle() {
		return mediaTitle;
	}
	public void setMediaTitle(String mediaTitle) {
		this.mediaTitle = mediaTitle;
	}
	public String getMediaInfo() {
		return mediaInfo;
	}
	public void setMediaInfo(String mediaInfo) {
		this.mediaInfo = mediaInfo;
	}
	public int getReviewCode() {
		return reviewCode;
	}
	public void setReviewCode(int reviewCode) {
		this.reviewCode = reviewCode;
	}
	public int getAccountCode() {
		return accountCode;
	}
//	public String getLoginID() {
//		return loginID;
//	}
//	public void setLoginID(String loginID) {
//		this.loginID = loginID;
//	}
	public void setAccountCode(int accountCode) {
		this.accountCode = accountCode;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public int getNetabare() {
		return netabare;
	}
	public void setNetabare(int netabare) {
		this.netabare = netabare;
	}
	public int getReviewGhost() {
		return reviewGhost;
	}
	public void setReviewGhost(int ghost) {
		this.reviewGhost = ghost;
	}
}
