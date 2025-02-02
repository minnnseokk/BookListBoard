package bookBoard.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import bookBoard.dto.BookBoardDto;
import bookBoard.dto.BookDetailImgDto;

public interface BookBoardService {
	// DTO에서 리스트 정보를 가져올 메서드
	List<BookBoardDto> selectBookBoardList();

	void insertBook(BookBoardDto bookDto, String imageUrl);

	BookBoardDto openBookDetail(int bookId);

	void updateBook(BookBoardDto bookDto, MultipartHttpServletRequest request);

	void deleteBook(int bookId);

	List<BookDetailImgDto> selectBookImageList(long bookId);
}
