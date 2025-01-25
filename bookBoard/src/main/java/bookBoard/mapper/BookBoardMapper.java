package bookBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import bookBoard.dto.BookBoardDto;

@Mapper
public interface BookBoardMapper {
	List<BookBoardDto> selectBookBoardList();
}
