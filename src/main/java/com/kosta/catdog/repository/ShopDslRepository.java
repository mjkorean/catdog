package com.kosta.catdog.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.catdog.entity.QDesigner;
import com.kosta.catdog.entity.QShop;
import com.kosta.catdog.entity.QShopFileVO;
import com.kosta.catdog.entity.Shop;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;


@Repository
public class ShopDslRepository {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    EntityManager entityManager;

    // Shop
    public List<Shop> findById(String id){
        QShop shop = QShop.shop;
        return jpaQueryFactory.selectFrom(shop)
                .where(shop.id.eq(id)).fetch();
    }

    public Shop fidnByNum(Integer num) {
        QShop shop = QShop.shop;
        return jpaQueryFactory.selectFrom(shop)
                .where(shop.num.eq(num)).fetchOne();
    }
    
    @Transactional
    public void deleteDesignerSid(String sId) {
    	QDesigner designer = QDesigner.designer;
    	jpaQueryFactory.update(designer)
        .set(designer.sId, "")
        .where(designer.sId.eq(sId))
        .execute();    	
    	entityManager.flush();
        entityManager.clear();
    }
          
    @Transactional
    public void deleteShopfile(String sId) {
    	QShop shop = QShop.shop;
    	QShopFileVO shopFileVO = QShopFileVO.shopFileVO;
    	List<String> bgImgList = jpaQueryFactory.select(shop.bgImg)
                .from(shop)
                .where(shop.sId.eq(sId))
                .fetch();

        List<Integer> numList = jpaQueryFactory.select(shopFileVO.num)
                .from(shopFileVO)
                .where(shopFileVO.num.stringValue().in(bgImgList))
                .fetch();

        jpaQueryFactory.delete(shopFileVO)
                .where(shopFileVO.num.in(numList))
                .execute();
    }
    
    @Transactional
    public void deleteShop(String sId) {
    	QShop shop = QShop.shop;
    	jpaQueryFactory.delete(shop)
    		.where(shop.sId.eq(sId))
    		.execute();    			
    }
    
}
