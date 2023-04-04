package com.testcp.test.book;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testcp.test.bookDao.BookDao;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookDao bookDao;

	@Override
	public String create(Map<String, Object> map) {
		System.out.println("service" + map);
		int affectRowCount = bookDao.insert(map);
		System.out.println("affectRowCount : " + affectRowCount);
		if (affectRowCount == 1) {
			return map.get("book_id").toString();
		}
		return null;
	}

	@Override
	public Map<String, Object> detail(Map<String, Object> map) {
		Map<String, Object> selectDetail = this.bookDao.selectDetail(map);
		return selectDetail;
	}

	@Override
	public boolean update(Map<String, Object> map) {
		int affectRowCount = this.bookDao.update(map);
		return affectRowCount == 1;
	}

}
