package com.example.androidtutorial_3.model;

public class Country {
	public String code;
	public String name;

	public Country(String name, String code){
		this.name = name;
		this.code = code;
	}
	
	public String toString(){
		return this.name;
	}
	

}
