package com.coderpwh.repository;

import com.coderpwh.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserRepository {
	
	Long addUser(User user);
	
	List<User> list();
	
	User findById(Long id);
	
	User findByName(String name);
}
