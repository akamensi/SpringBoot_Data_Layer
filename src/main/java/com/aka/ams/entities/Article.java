package com.aka.ams.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Article {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
    @NotBlank(message = "Label is mandatory")
    @Column(name = "label")
	private String label;
    
   
    @Column(name = "price")
	private float price;
    
    @Column(name = "picture")
    private String picture;

	


	public Article(@NotBlank(message = "Label is mandatory") String label, float price, String picture,
			Provider provider) {
		super();
		this.label = label;
		this.price = price;
		this.picture = picture;
		this.provider = provider;
	}

	public Article() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", label=" + label + ", price=" + price + "]";
	}
	
	/**** Many To One ****/
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "provider_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	//@NotNull(message = "Provider is mandatory")
    private Provider provider;
	
	
	public Provider getProvider() {
    	return provider;
    }
    
    public void setProvider(Provider provider) {
    	this.provider=provider;
    } 
	
	
	

}
