package bookBoard.service;

import java.util.List;

import bookBoard.dto.BookBoardDto;

public interface BookBoardService {
	// DTO에서 리스트 정보를 가져올 메서드
	List<BookBoardDto> selectBookBoardList();
}
