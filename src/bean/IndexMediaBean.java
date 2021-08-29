package bean;

import java.io.Serializable;

//index.jspで画像pathを含めた情報を表示するのに使用
public class IndexMediaBean implements Serializable {
	//念のためにgenreも引っ張ってこれるようにする
	private int mediaCode;
	private String mediaTitle;
	private String mediaInfo;
	private String genre1;
	private String genre2;
	private String genre3;
	private String genre4;
	private String genre5;
	private String fileName;
	private String filePath;

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
	public String getGenre1() {
		return genre1;
	}
	public void setGenre1(String genre1) {
		this.genre1 = genre1;
	}
	public String getGenre2() {
		return genre2;
	}
	public void setGenre2(String genre2) {
		this.genre2 = genre2;
	}
	public String getGenre3() {
		return genre3;
	}
	public void setGenre3(String genre3) {
		this.genre3 = genre3;
	}
	public String getGenre4() {
		return genre4;
	}
	public void setGenre4(String genre4) {
		this.genre4 = genre4;
	}
	public String getGenre5() {
		return genre5;
	}
	public void setGenre5(String genre5) {
		this.genre5 = genre5;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}

/*private int mediaCode;
private String mediaTitle;
private String mediaInfo;
private String mediaType;
private String releaseDate; //ここはString型にし、SQL側でDBの設定型に自動変換させた方が使いやすい
private String director;
private String leadingActor;
private String music;
private String productionYear; //ここはString型にし、SQL側でDBの設定型に自動変換させた方が使いやすい
private String deploymentDate; //ここはString型にし、SQL側でDBの設定型に自動変換させた方が使いやすい
private int ghost;
private String genre1;
private String genre2;
private String genre3;
private String genre4;
private String genre5;
private String fileName;
private String filePath;

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
public String getMediaType() {
	return mediaType;
}
public void setMediaType(String mediaType) {
	this.mediaType = mediaType;
}
public String getReleaseDate() {
	return releaseDate;
}
public void setReleaseDate(String releaseDate) {
	this.releaseDate = releaseDate;
}
public String getDirector() {
	return director;
}
public void setDirector(String director) {
	this.director = director;
}
public String getLeadingActor() {
	return leadingActor;
}
public void setLeadingActor(String leadingActor) {
	this.leadingActor = leadingActor;
}
public String getMusic() {
	return music;
}
public void setMusic(String music) {
	this.music = music;
}
public String getProductionYear() {
	return productionYear;
}
public void setProductionYear(String productionYear) {
	this.productionYear = productionYear;
}
public String getDeploymentDate() {
	return deploymentDate;
}
public void setDeploymentDate(String deploymentDate) {
	this.deploymentDate = deploymentDate;
}
public int getGhost() {
	return ghost;
}
public void setGhost(int ghost) {
	this.ghost = ghost;
}
public String getGenre1() {
	return genre1;
}
public void setGenre1(String genre1) {
	this.genre1 = genre1;
}
public String getGenre2() {
	return genre2;
}
public void setGenre2(String genre2) {
	this.genre2 = genre2;
}
public String getGenre3() {
	return genre3;
}
public void setGenre3(String genre3) {
	this.genre3 = genre3;
}
public String getGenre4() {
	return genre4;
}
public void setGenre4(String genre4) {
	this.genre4 = genre4;
}
public String getGenre5() {
	return genre5;
}
public void setGenre5(String genre5) {
	this.genre5 = genre5;
}
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public String getFilePath() {
	return filePath;
}
public void setFilePath(String filePath) {
	this.filePath = filePath;
}*/
