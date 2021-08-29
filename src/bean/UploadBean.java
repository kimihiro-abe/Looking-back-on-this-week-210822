package bean;

import java.io.Serializable;

public class UploadBean implements Serializable {
	private int id;
	private int mediaCode;
	private String mediaTitle;
	private String filename;
	private String filePath;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
