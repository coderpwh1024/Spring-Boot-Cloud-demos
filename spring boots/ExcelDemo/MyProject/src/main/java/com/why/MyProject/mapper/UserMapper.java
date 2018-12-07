package com.why.MyProject.mapper;

import com.why.MyProject.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void addUser(User sysUser);

    int updateUserByName(User sysUser);

    int selectByName(String name);

    void insertBatch(List<User> list);

}