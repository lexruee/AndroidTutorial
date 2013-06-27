package com.example.androidtutorial_3.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Model {
	private List<Country> countries = new ArrayList<Country>();
	private Random rand = new Random();
	private Country current;

	public Model() {
		this.intializeCountries();
	}

	private void intializeCountries() {
		this.countries.add(new Country("Switzerland", "CH"));
		this.countries.add(new Country("Germany", "DE"));
		this.countries.add(new Country("Austria", "AT"));
		this.countries.add(new Country("UNITED STATES", "US"));
		this.countries.add(new Country("CANADA", "CA"));
		Collections.sort(this.countries, new Comparator<Country>() {

			@Override
			public int compare(Country c1, Country c2) {
				return c1.name.compareTo(c2.name);
			}
		});

	}

	public List<String> getCountries() {
		List<String> list = new ArrayList<String>();
		for(Country c : this.countries){
			list.add(c.name);
		}
		return list;
	}

	public Country chooseRandom() {
		this.current = this.countries.get(rand.nextInt(this.countries.size()));
		return this.current;
	}

	public void choose(String country) {
		for (Country c : this.countries) {
			if (c.name.equals(country)) {
				this.current = c;
			}
		}
	}

	public String getCurrentCountryCode() {
		// TODO Auto-generated method stub
		return this.current.code;
	}
}
