package com.mongodb;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */
public interface MongoBeanRepository6 extends MongoRepository<MongoBean6, Long>{

    public List<MongoBean6> findAll();
    public List<MongoBean6> findAllOrderByDateRequested();

	@SuppressWarnings("unchecked")
    public MongoBean6 save(MongoBean6 template);
	
	public List<MongoBean6> findAllByWho(String who);
	
    public MongoBean6 findById(String id);
    public MongoBean6 findByName(String name);

    public void deleteById(String id);
    public void deleteByName(String name);
	
	public List<MongoBean6> findByWhoLikeOrderByWhoAsc(String part);
	
//	@Query("{ 'name' : { $regex: '?0.*?1.*' } }")
//	public List<MongoBean> findBeansByRegexpFirstSecond(String first, String second);
}
