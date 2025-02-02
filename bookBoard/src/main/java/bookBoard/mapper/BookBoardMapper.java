package bookBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import bookBoard.dto.BookBoardDto;
import bookBoard.dto.BookDetailImgDto;

@Mapper
public interface BookBoardMapper {
	List<BookBoardDto> selectBookBoardList();

	void insertBook(BookBoardDto bookDto);

	void insertBookImage(@Param("bookId") long bookId, @Param("imageUrl") String imageUrl);

	BookBoardDto openBookDetail(int bookId);

	void updateBook(BookBoardDto bookDto);

	void deleteBook(BookBoardDto bookDto);

	void insertBookImageList(List<BookDetailImgDto> fileInfoList);

	List<BookDetailImgDto> selectBookImageList(@Param("bookId") long bookId);
}
