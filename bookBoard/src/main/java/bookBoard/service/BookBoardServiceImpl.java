package bookBoard.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import bookBoard.common.FileUtils;
import bookBoard.dto.BookBoardDto;
import bookBoard.dto.BookDetailImgDto;
import bookBoard.mapper.BookBoardMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookBoardServiceImpl implements BookBoardService{
	// db를 관리해줄 매퍼
	@Autowired
	private BookBoardMapper bookMapper;
	
	// 파일 디렉토리 세팅을 받아줄 인스턴스 생성
	@Autowired
	private FileUtils fileUtils;
	
	// 책 조회 메서드
	@Override
	public List<BookBoardDto> selectBookBoardList(){
		return bookMapper.selectBookBoardList();
	}	
	// 책 추가 메서드
	public void insertBook(BookBoardDto bookDto, String imageUrl) {
	        // 책 정보를 DB에 저장
		bookMapper.insertBook(bookDto);
	        // 책 ID를 가져옴
	        long bookId = bookDto.getBookId(); // insert 후, bookId가 자동 생성된 경우
	        // 사용자가 입력한 이미지 URL 저장
	        if (imageUrl != null && !imageUrl.isEmpty()) {
	        	bookMapper.insertBookImage(bookId, imageUrl); // 사용자가 입력한 URL 저장
	        }
	}
	
	// 책 상세정보 페이지 메서드
	@Override
	public BookBoardDto openBookDetail(int bookId) {
		return bookMapper.openBookDetail(bookId);
	}
	
	// 책 수정 메서드
	@Override
	public void updateBook(BookBoardDto bookDto, MultipartHttpServletRequest request ) {
		bookMapper.updateBook(bookDto);

        try {
        	// 첨부 파일을 디스크에 저장하고, 점부 파일 정보를 반환
            List<BookDetailImgDto> fileInfoList = fileUtils.parseFileInfo(bookDto.getBookId(), request);
            // 첨부 파일 정보를 DB에 저장
            if(!CollectionUtils.isEmpty(fileInfoList)) {
            	bookMapper.insertBookImageList(fileInfoList);
            }
        } catch(Exception e) {
        	log.error("Error in updateBook: ", e); // 예외의 전체 스택 추적 로그
        }
		/*
		if (!ObjectUtils.isEmpty(request)) {
            // <input type="file" name="이 속성의 값" />
            Iterator<String> fileTagNames = request.getFileNames();
            while(fileTagNames.hasNext()) {
                String fileTagName = fileTagNames.next();
                // 하나의 <input type="file" multiple="multiple"> 태그를 통해서 전달된 파일들을 가져옮
                List<MultipartFile> files = request.getFiles(fileTagName);
                for (MultipartFile file : files) {
                    log.debug("File Information");
                    log.debug("- file name: " + file.getOriginalFilename());
                    log.debug("- file size: " + file.getSize());
                    log.debug("- content type: " + file.getContentType());
                }
            }
        }
        */
	}
	
	// 책 삭제 메서드
	@Override
	public void deleteBook(int bookId) {
		BookBoardDto bookDto = new BookBoardDto();
		bookDto.setBookId(bookId);
		bookMapper.deleteBook(bookDto);
	}
	
	// 책 이미지 조회 메서드
	@Override
	public List<BookDetailImgDto> selectBookImageList(long bookId){
		return bookMapper.selectBookImageList(bookId);
	}
}


