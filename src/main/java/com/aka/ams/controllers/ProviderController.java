package com.aka.ams.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aka.ams.entities.Provider;
import com.aka.ams.services.ProviderService;

@Controller
@RequestMapping("/provider")
public class ProviderController {

	@Autowired
	ProviderService service; // = new ProviderService();

	
	@RequestMapping("/add")
	@ResponseBody
	public String add()
	{
		//Provider p1 = new Provider("Samsung", "Koré", "samsung@gmail.com" );
		Provider p2 = new Provider("Nikon", "Japon", "nikon@gmail.com" );
		//Provider res = service.addProvider(p1);
		Provider res = service.addProvider(p2);
		return res.toString();
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public String list()
	{
		List<Provider> res = service.listProvider();
		return res.toString();
	}
}
