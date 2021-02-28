package com.mongodb;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */
public interface MongoBeanRepository extends MongoRepository<MongoBean, Long>{

    public List<MongoBean> findAll();
    public List<MongoBean> findAllOrderByDateRequested();

	@SuppressWarnings("unchecked")
    public MongoBean save(MongoBean template);
	
	public MongoBean findByWho(String who);
	
	public MongoBean findById(String id);

	public void deleteById(String id);
	
	public List<MongoBean> findByWhoLikeOrderByWhoAsc(String part);
	
//	@Query("{ 'name' : { $regex: '?0.*?1.*' } }")
//	public List<MongoBean> findBeansByRegexpFirstSecond(String first, String second);
}
