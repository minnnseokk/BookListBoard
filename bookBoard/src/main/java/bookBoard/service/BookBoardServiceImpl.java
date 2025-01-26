package bookBoard.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import bookBoard.dto.BookBoardDto;
import bookBoard.dto.BookBoardImgDto;
import bookBoard.mapper.BookBoardMapper;

@Service
public class BookBoardServiceImpl implements BookBoardService{
	// db를 관리해줄 매퍼
	@Autowired
	private BookBoardMapper boardMapper; 
	// 책 조회 메서드
	@Override
	public List<BookBoardDto> selectBookBoardList(){
		return boardMapper.selectBookBoardList();
	}	
	// 책 추가 메서드
	public void insertBook(BookBoardDto bookDto, String imageUrl) {
	        // 책 정보를 DB에 저장
	        boardMapper.insertBook(bookDto);
	        // 책 ID를 가져옴
	        long bookId = bookDto.getBookId(); // insert 후, bookId가 자동 생성된 경우
	        // 사용자가 입력한 이미지 URL 저장
	        if (imageUrl != null && !imageUrl.isEmpty()) {
	            boardMapper.insertBookImage(bookId, imageUrl); // 사용자가 입력한 URL 저장
	        }
	}
}


