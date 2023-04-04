package com.testcp.test.book;

import java.util.Map;

public interface BookService {

	String create(Map<String, Object> map);

	Map<String, Object> detail(Map<String, Object> map);

	boolean update(Map<String, Object> map);

}
