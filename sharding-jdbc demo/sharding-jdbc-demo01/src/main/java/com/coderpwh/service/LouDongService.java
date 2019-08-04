package com.coderpwh.service;

import com.coderpwh.po.LouDong;

import java.util.List;

public interface LouDongService {

	List<LouDong> list();
	
	Long addLouDong(LouDong louDong);

}
