package bookBoard.dto;

public class BookDetailImgDto {
	private long imageId;	
	private long bookId;
	private String imageUrl;
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
