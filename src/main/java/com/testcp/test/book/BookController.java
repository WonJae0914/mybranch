package com.testcp.test.book;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {

	@Autowired
	BookService bookService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("book/create");
		return mv;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createPost(@RequestParam Map<String, Object> map) {
		System.out.println(map);
		ModelAndView mv = new ModelAndView();
		String bookId = this.bookService.create(map);
		System.out.println("bookdId : " + bookId);
		if (bookId == null) {
			mv.setViewName("redirect:/create");
		} else {
			mv.setViewName("redirect:/detail?bookId=" + bookId);
		}
		return mv;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam Map<String, Object> map) {
		System.out.println("map : " + map);
		Map<String, Object> detailMap = this.bookService.detail(map);
		System.out.println("detailMap : " + detailMap);
		ModelAndView mv = new ModelAndView();
		mv.addObject("data", detailMap);
		String bookId = map.get("bookId").toString();
		System.out.println("bookId : " + bookId);
		mv.addObject("bookId", bookId);
		mv.setViewName("/book/detail");
		return mv;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam Map<String, Object> map) {
		Map<String, Object> detailMap = this.bookService.detail(map);
		ModelAndView mv = new ModelAndView();
		mv.addObject("data", detailMap);
		mv.setViewName("/book/update");
		return mv;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ModelAndView updatePost(@RequestParam Map<String, Object> map) {

		ModelAndView mv = new ModelAndView();
		boolean isUpdateSuccess = this.bookService.update(map);
		if (isUpdateSuccess) {
			String bookId = map.get("bookId").toString();
			mv.setViewName("redirect:/detail?bookId=" + bookId);
		}else {
			mv = this.update(map);
		}

		return mv;
	}

	@RequestMapping(value = "/delete/{bookId}", method = RequestMethod.DELETE)
	public ModelAndView delete(){@PathVariable String )

	}
}







