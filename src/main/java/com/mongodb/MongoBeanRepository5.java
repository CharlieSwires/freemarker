package com.mongodb;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */
public interface MongoBeanRepository5 extends MongoRepository<MongoBean5, Long>{

    public List<MongoBean5> findAll();
    public List<MongoBean5> findAllOrderByDateRequested();

	@SuppressWarnings("unchecked")
    public MongoBean5 save(MongoBean5 template);
	
	public MongoBean5 findByWho(String who);
	
	public MongoBean5 findById(String id);

	public void deleteById(String id);
	
	public List<MongoBean5> findByWhoLikeOrderByWhoAsc(String part);
	
//	@Query("{ 'name' : { $regex: '?0.*?1.*' } }")
//	public List<MongoBean> findBeansByRegexpFirstSecond(String first, String second);
}
