package bean;

import java.io.Serializable;

public class ReviewBean implements Serializable {

	private int reviewCode;
	private int mediaCode;
	private int accountCode;
	private String nickName;
	private String reviewContent;
	private String reviewDate; //ここはString型にし、SQL側でDBの設定型に自動変換させた方が使いやすい
	private int netabare;
	private int reviewGhost;

	public int getReviewCode() {
		return reviewCode;
	}
	public void setReviewCode(int reviewCode) {
		this.reviewCode = reviewCode;
	}
	public int getMediaCode() {
		return mediaCode;
	}
	public void setMediaCode(int mediaCode) {
		this.mediaCode = mediaCode;
	}
	public int getAccountCode() {
		return accountCode;
	}
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