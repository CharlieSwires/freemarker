package com.mongodb;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */
public interface MongoBeanRepository4 extends MongoRepository<MongoBean4, Long>{

    public List<MongoBean4> findAll();
    public List<MongoBean4> findAllOrderByDateRequested();

	@SuppressWarnings("unchecked")
    public MongoBean4 save(MongoBean4 template);
	
	public MongoBean4 findByWho(String who);
	
	public MongoBean4 findById(String id);

	public void deleteById(String id);
	
	public List<MongoBean4> findByWhoLikeOrderByWhoAsc(String part);
	
//	@Query("{ 'name' : { $regex: '?0.*?1.*' } }")
//	public List<MongoBean> findBeansByRegexpFirstSecond(String first, String second);
}
