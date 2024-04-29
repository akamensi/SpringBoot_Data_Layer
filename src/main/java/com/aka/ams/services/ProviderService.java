package com.aka.ams.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aka.ams.repositories.ProviderRepository;
import com.aka.ams.entities.Provider;

@Service
public class ProviderService {
	
	@Autowired  // Spring va se charger de créer un objet qui implémente cette interface
	ProviderRepository providerRepository;
	
	
	public Provider addProvider(Provider provider)
	{
		return providerRepository.save(provider);  // sauvegarder dans la base
		
	}
	
	public List<Provider> listProvider()
	{
		return (List<Provider>) providerRepository.findAll();	 // lister tous les providers de la base
	}
	
	public void deleteProvider(long id) {
	     providerRepository.deleteById(id);
	}
	
	
	public Optional<Provider> findProviderById(long id)
	{
		return providerRepository.findById(id);
	}





}
