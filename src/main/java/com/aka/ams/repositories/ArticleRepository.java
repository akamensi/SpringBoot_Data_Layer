package com.aka.ams.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aka.ams.entities.Article;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
	
	

}
