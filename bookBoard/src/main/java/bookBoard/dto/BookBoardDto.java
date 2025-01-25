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
}
