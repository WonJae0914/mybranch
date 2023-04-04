package com.testcp.test.book;

import java.util.List;
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
		ModelAndView mv = new ModelAndView();
		String bookId = this.bookService.create(map);
		if (bookId == null) {
			mv.setViewName("redirect:/create");
		} else {
			mv.setViewName("redirect:/detail?bookId=" + bookId);
		}
		return mv;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam Map<String, Object> map) {
		Map<String, Object> detailMap = this.bookService.detail(map);
		ModelAndView mv = new ModelAndView();
		mv.addObject("data", detailMap);
		String bookId = map.get("bookId").toString();
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

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ModelAndView delete(@RequestParam Map<String, Object> map){
		System.out.println("delMap:"+map);
		ModelAndView mv = new ModelAndView();
		boolean delete = this.bookService.delete(map);
		System.out.println("resultDel : " + delete);
		if (delete){
			mv.setViewName("redirect:/list");
		} else{
			String bookId = map.get("bookId").toString();
			mv.setViewName("redirect:/detail?bookId="+bookId);
		}
		return mv;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam Map<String, Object>map){
		ModelAndView mv = new ModelAndView();
		List<Map<String, Object>> list = this.bookService.list(map);
		mv.addObject("data", list);
		if (map.containsKey("keyword")){
			mv.addObject("keyword",map.get("keyword"));
		}
		mv.setViewName("/book/list");
		return mv;
	}

}







