package bookBoard.dto;

import lombok.Data;

@Data
public class BookBoardDto {
	private long bookId;
	private String title;
	private String author;
	private String publisher;
	private String publishedDate;
	private String isbn;
	private String description;
	// 이미지 정보를 조인해서 가져온 데이터
	private String imageUrl;
}
