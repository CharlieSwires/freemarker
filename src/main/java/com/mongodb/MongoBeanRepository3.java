package com.mongodb;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */
public interface MongoBeanRepository3 extends MongoRepository<MongoBean3, Long>{

    public List<MongoBean3> findAll();
    public List<MongoBean3> findAllOrderByDateRequested();

	@SuppressWarnings("unchecked")
    public MongoBean3 save(MongoBean3 template);
	
	public MongoBean3 findByWho(String who);
	
	public MongoBean3 findById(String id);

	public void deleteById(String id);
	
	public List<MongoBean3> findByWhoLikeOrderByWhoAsc(String part);
	
//	@Query("{ 'name' : { $regex: '?0.*?1.*' } }")
//	public List<MongoBean> findBeansByRegexpFirstSecond(String first, String second);
}
