package com.aka.ams.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aka.ams.entities.Article;
import com.aka.ams.entities.Provider;
import com.aka.ams.services.ArticleService;
import com.aka.ams.services.ProviderService;


import jakarta.validation.Valid;

@Controller
@RequestMapping("/article/")
public class ArticleController {
	
	private final ArticleService articleService;
	private final ProviderService providerService;
	public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/uploads";

	public ArticleController(ArticleService articleService, ProviderService providerService) {
		super();
		this.articleService = articleService;
		this.providerService = providerService;
	}
	
    @GetMapping("list")
    public String listProviders(Model model) {
    	List<Article> articales = articleService.listArticle();
    	if(articales.size() == 0)
    		articales = null;
        model.addAttribute("articles", articales);
        return "article/listArticle";
    }
    
    @GetMapping("addGet")
    public String showAddArticleForm(Article article, Model model) {
    	
    	model.addAttribute("providers", providerService.listProvider());
    	model.addAttribute("article", new Article());
        return "article/addArticle";
    }
    
    @PostMapping("addPost")
    public String addArticle(@Valid Article article, BindingResult result, @RequestParam(name = "providerId", required = false) Long p,
    		@RequestParam("files") MultipartFile[] files)
    {	
    	Provider provider = providerService.findProviderById(p)
                .orElseThrow(()-> new IllegalArgumentException("Invalid provider Id:" + p));
    	article.setProvider(provider);
    	//upload image
    	StringBuilder fileName = new StringBuilder();
    	MultipartFile file = files[0];
    	Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
    	
    	fileName.append(file.getOriginalFilename());
		  try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		  //fin upload
    	
		 article.setPicture(fileName.toString());
		//fin upload
    	 articleService.addArticle(article);
    	 
    	 return "redirect:list";
    }
    
    @GetMapping("delete/{id}")
    public String deleteArticle(@PathVariable("id") long id, Model model)
    {
        articleService.findArticleById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid article Id:" + id));
        articleService.deleteArticle(id);
        model.addAttribute("articles", articleService.listArticle());
        return "redirect:../list";
    }
    
    @GetMapping("editGet/{id}")
    public String showArticleFormToUpdate(@PathVariable("id") long id, Model model)
    {
    	try {
    	Article article = articleService.findArticleById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid articles Id:" + id));
    	 model.addAttribute("article", article);
        model.addAttribute("providers", providerService.listProvider());
        model.addAttribute("idProvider", article.getProvider().getId());
    	}catch(IllegalArgumentException ex)
		{
			return "provider/500";
		}
        
        return "article/editArticle";
    }
    
    
    @PostMapping("editPost")
    public String updateArticle(@Valid Article article, BindingResult result,
         @RequestParam(name = "providerId", required = false) Long p) {
        if (result.hasErrors()) {
        	
            return "article/editArticle";
        }
        
        Provider provider = providerService.findProviderById(p)
                .orElseThrow(()-> new IllegalArgumentException("Invalid provider Id:" + p));
    	article.setProvider(provider);
    	
        articleService.addArticle(article);
        return "redirect:list";
    }
    
    @GetMapping("show/{id}")
    public String showArticleDetails(@PathVariable("id") long id, Model model) {
    	Article article = articleService.findArticleById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid provider Id:" + id));
    	
        model.addAttribute("article", article);
        
        return "article/showArticle";
    }




    




}
