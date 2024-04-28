package com.aka.ams.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import  com.aka.ams.entities.Provider;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Long> {

}
