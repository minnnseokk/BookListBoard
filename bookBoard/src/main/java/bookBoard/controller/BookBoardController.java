package bookBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import bookBoard.dto.BookBoardDto;
import bookBoard.service.BookBoardService;

@Controller
public class BookBoardController {
	@Autowired
	private BookBoardService bookBoardService;
	
	// 해당 페이지로 bookBoardList api를 호출한다.
	@GetMapping("/bookBoard/openBookList.do")
	public ModelAndView openBookBoardList() throws Exception{ 
		ModelAndView mv = new ModelAndView("/bookBoard/bookList");
		// Dto를 list에 담아서 불러오는 형태
		List<BookBoardDto> list = bookBoardService.selectBookBoardList();
		mv.addObject("list", list);
		return mv;
	}
	
	// 책 추가 화면을 보여주는 메서드
	@GetMapping("/bookBoard/openBookWrite.do")
	public String openBoardWrite() throws Exception{
		return "/bookBoard/bookWrite";
	}
	
    // 책 추가 로직을 연결해줄 메서드
    @PostMapping("/bookBoard/insertBook.do")
    public String insertBook(
        BookBoardDto bookDto,  
        @RequestParam(value = "imageUrl") String imageUrl) throws Exception {
        
        // 서비스 호출
        bookBoardService.insertBook(bookDto, imageUrl);
        
        return "redirect:/bookBoard/openBookList.do";
    }
	
}

