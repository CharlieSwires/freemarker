package com.mongodb;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */

public interface MongoBeanRepository2 extends MongoRepository<MongoBean2, Long>{

    public List<MongoBean2> findAll();
    public List<MongoBean2> findAllOrderByDateRequested();

	@SuppressWarnings("unchecked")
    public MongoBean2 save(MongoBean2 template);
	
	public MongoBean2 findByWho(String who);
	
	public MongoBean2 findById(String id);

	public void deleteById(String id);
	
	public List<MongoBean2> findByWhoLikeOrderByWhoAsc(String part);
	
//	@Query("{ 'name' : { $regex: '?0.*?1.*' } }")
//	public List<MongoBean> findBeansByRegexpFirstSecond(String first, String second);
}
