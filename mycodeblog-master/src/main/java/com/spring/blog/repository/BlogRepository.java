package com.spring.blog.repository;

import com.spring.blog.model.Post;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BlogRepository extends JpaRepository<Post, Long> {

	@Query("select p from tb_post p where p.titulo like %?1%")
	List<Post> findPostByName(String nome);
	
}
