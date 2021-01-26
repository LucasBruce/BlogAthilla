package com.spring.blog.controller;

import com.spring.blog.model.Post;
import com.spring.blog.repository.BlogRepository;
import com.spring.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    BlogRepository blogRepository;
    
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    
    @RequestMapping(value="/about", method=RequestMethod.GET)
    public String about() {
    	return "about";
    }
    
    @RequestMapping(value="/contact", method=RequestMethod.GET)
    public String contact(){
    	return "contact";
    }
 
	 @RequestMapping(value="/post", method=RequestMethod.GET)   
	 public ModelAndView getPost() {
		 ModelAndView mv = new ModelAndView("post");
		 List<Post> postes = blogService.findAll();
		 mv.addObject("post", postes);
		 return mv;
	 }
    
    
    
    
//    @RequestMapping(value="/posts", method=RequestMethod.GET)
//    public ModelAndView getPosts(){
//        ModelAndView mv = new ModelAndView("posts");
//        List<Post> posts = blogService.findAll();
//        mv.addObject("posts", posts);
//        return mv;
//    }

    @RequestMapping(value="/post/{id}", method=RequestMethod.GET)
    public ModelAndView getPostDetails(@PathVariable("id") long id){
        ModelAndView mv = new ModelAndView("postDetails");
        Post post = blogService.findById(id);
        mv.addObject("post", post);
        return mv;
    }
    
    @RequestMapping(value="/post/detailEditar{id}", method=RequestMethod.GET)
    public ModelAndView getDetailEditar(@PathVariable("id") long id){
        ModelAndView mv = new ModelAndView("detailEditar");
        Post post = blogService.findById(id);
        mv.addObject("post", post);
        return mv;
    }
    
    @RequestMapping(value="/newpost", method=RequestMethod.GET)
    public String getPostForm(){
        return "postForm";
    }

    @RequestMapping(value="/newpost", method=RequestMethod.POST)
    public String savePost(@Valid Post post, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
            return "redirect:/newpost";
        }
        post.setData(LocalDate.now());
        blogService.save(post);
        return "redirect:/post";
    }
    
    @RequestMapping("/deleter/{id}")
    public String deletePost(long id) {
    	blogService.delete(id);
    	return "redirect:/post";
    }
    
    @RequestMapping(value="/pesquisarArtigo", method=RequestMethod.POST)
    public ModelAndView pesquisar(@RequestParam("artigopesquisa") String artigopesquisa) {
    	ModelAndView mv = new ModelAndView("post");
    	mv.addObject("post", blogRepository.findPostByName(artigopesquisa));
    	return mv;
    }
    
}
