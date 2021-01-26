package com.spring.blog.service.serviceImpl;

import com.spring.blog.model.Post;
import com.spring.blog.repository.BlogRepository;
import com.spring.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogRepository blogRepository;

    
    @Override
    public List<Post> findAll() {
        return blogRepository.findAll(sortByIdAsc());
    }

    @Override
    public Post findById(long id) {
        return blogRepository.findById(id).get();
    }

    @Override
    public Post save(Post post) {
        return blogRepository.save(post);
    }

    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.DESC, "id");
    }
    
    @Override
    public void delete(long id) {
    	blogRepository.deleteById(id);
    }
    
//    @Override
//    public List<Post> findPostByName(String nome) {
//        return blogRepository.findPostByName(nome);
//    }
	
}
