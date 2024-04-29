package com.aka.ams.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aka.ams.entities.Provider;
import com.aka.ams.services.ProviderService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/provider")
public class ProviderController {

	@Autowired
	ProviderService service; // = new ProviderService();

	
	@GetMapping("/addGet")
	public String addGet(Model model)
	{
		Provider provider = new Provider();
		model.addAttribute("provider", provider);// object dont la valeur des attributs par defaut
		//Provider p1 = new Provider("Samsung", "Koré", "samsung@gmail.com" );
		//Provider p2 = new Provider("Nikon", "Japon", "nikon@gmail.com" );
		//Provider res = service.addProvider(p1);
		//Provider res = service.addProvider(p2);
		return "provider/addProvider";
	}
	
	@PostMapping("/addPost")
	public String addPost(@Valid Provider provider, BindingResult result)
	{
		if(result.hasErrors())
		{
			return "provider/addProvider";
		}
		service.addProvider(provider);
        return "redirect:list";
	}
	
	@RequestMapping("/list")
	public String list(Model model)
	{
		List<Provider> res = service.listProvider();
		if(res.size()==0)
			res = null;
		model.addAttribute("providers", res);
		return "provider/listProvider";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProvider(@PathVariable("id") long id)
	{
     
		service.deleteProvider(id);
       	return "redirect:../list";
	}
	
	@GetMapping("/editGet/{id}")
	public String editGet(@PathVariable("id")long id, Model model)
	{
		Provider provider = null;
		try {
		     provider = service.findProviderById(id)
				.orElseThrow(()->new IllegalArgumentException("Ivalid Provider id "+id));
		}
		catch(IllegalArgumentException ex)
		{
			return "provider/500";
		}
		model.addAttribute("provider", provider);
		return"provider/editProvider";
	}
	
	@PostMapping("/editPost")
	public String editPost(@Valid Provider provider, BindingResult result,Model model)
	{
		service.addProvider(provider);
		return"redirect:list";
	}
	
	
	
	
	
	
	
	
	
	
	
}
