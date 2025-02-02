package bookBoard.dto;

public class BookDetailImgDto {
	private long imageId;	
	private long bookId;
	private String originalFileName;
	private String storedFilePath;
	private String fileSize;
	public long getBookId() {
		return bookId;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public String getStoredFilePath() {
		return storedFilePath;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public void setStoredFilePath(String storedFilePath) {
		this.storedFilePath = storedFilePath;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
}
