package bean;

import java.io.Serializable;

//adminでのみ使用するBean
public class ReviewBean2 implements Serializable {

	//accountのghostだけ欲しい。
	private int ghost;

	//↓review beanと一緒。
	private int reviewCode;
	private int mediaCode;
	private int accountCode;
	private String nickName;
	private String reviewContent;
	private String reviewDate; //ここはString型にし、SQL側でDBの設定型に自動変換させた方が使いやすい
	private int netabare;
	private int reviewGhost;

	public int getGhost() {
		return ghost;
	}
	public void setGhost(int ghost) {
		this.ghost = ghost;
	}
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
	public void setReviewGhost(int reviewGhost) {
		this.reviewGhost = reviewGhost;
	}

}
