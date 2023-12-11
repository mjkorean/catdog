package com.kosta.catdog.service;

import java.util.List;

import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.Review;
import com.kosta.catdog.entity.Shop;

public interface ShopService {
	// 샵 등록
	void addShop(Shop shop) throws Exception;
	// 디자이너 등록
	// void addDesigner(String id, String position) throws Exception;
	// 디자이너 삭제
	void deleteDesigner() throws Exception;
	// 샵 기본정보 등록
	void addShopInfo(Shop shop) throws Exception;
	// 공지사항 등록
	void addShopNotice(String notice) throws Exception;
	// 소속 디자이너 모아 보여주기
	List<Designer> designerListByShop(Integer num) throws Exception;
	
}
