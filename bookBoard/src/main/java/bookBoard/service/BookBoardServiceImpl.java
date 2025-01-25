package bookBoard.service;

import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bookBoard.dto.BookBoardDto;
import bookBoard.mapper.BookBoardMapper;

@Service
public class BookBoardServiceImpl implements BookBoardService{
	// db를 관리해줄 매퍼
	@Autowired
	private BookBoardMapper boardMapper; 
	@Override
	public List<BookBoardDto> selectBookBoardList(){
		return boardMapper.selectBookBoardList();
	}
}
