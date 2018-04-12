package com.imooc.mapper;

import com.imooc.pojo.SysUser;

import java.util.List;

public interface SysUserMapperCustom {
	
	List<SysUser> queryUserSimplyInfoById(String id);
}