package com.aka.ams.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aka.ams.entities.Article;
import  com.aka.ams.entities.Provider;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Long> {
	
	@Query("FROM Article a WHERE a.provider.id = ?1")
	List<Article> findArticlesByProvider(long id);


}
