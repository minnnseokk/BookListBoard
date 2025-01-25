package bookBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import bookBoard.dto.BookBoardDto;
import bookBoard.service.BookBoardService;

@Controller
public class BookBoardController {
	@Autowired
	private BookBoardService bookBoardService;
	// 해당 페이지로 bookBoardList api를 호출한다.
	@GetMapping("/bookBoard/openBoardList.do")
	public ModelAndView openBookBoardList() throws Exception{ 
		ModelAndView mv = new ModelAndView("/bookBoard/bookBoardList");
		// Dto를 list에 담아서 불러오는 형태
		List<BookBoardDto> list = bookBoardService.selectBookBoardList();
		mv.addObject("list", list);
		return mv;
	}
}
