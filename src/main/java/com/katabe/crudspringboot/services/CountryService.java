package com.katabe.crudspringboot.services;

import com.katabe.crudspringboot.beans.Country;
import com.katabe.crudspringboot.controllers.AddResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CountryService {
    static HashMap<Integer, Country> countryIdMap;

    public CountryService(){
        countryIdMap = new HashMap<Integer, Country>();
        Country drc = new Country(1, "DRC", "Kinshasa");
        Country rsa = new Country(1, "RSA", "Pretoria");
        Country senegal = new Country(1, "Senegal", "Dakar");
        Country usa = new Country(1, "United State of America", "Washington DC");
        countryIdMap.put(1, drc);
        countryIdMap.put(2, rsa);
        countryIdMap.put(3, senegal);
        countryIdMap.put(4,usa);
    }
    public List getAllCountries(){
        List countries = new ArrayList(countryIdMap.values());
        return countries;
    }
    public Country getCountryById(int id){
        Country country = countryIdMap.get(id);
        return country;
    }
    public Country getCountryByName(String countryName){
        Country country =null;
        for (int i : countryIdMap.keySet()){
            if (countryIdMap.get(i).getCountryName().equals(countryName)){
                country = countryIdMap.get(countryName);
            }
        }
        return country;
    }
    public Country addCountry( Country country){
        country.setId(getMaxID());
        countryIdMap.put(country.getId(), country);
        return country;
    }
    public  static int getMaxID(){
        int max=0;
        for (int id:countryIdMap.keySet()){
            if (max <=id){
                max= id;
            }
        }
        return max +1;
    }

    public Country updateCountry(Country country){
            if (country.getId()>0){
                countryIdMap.put(country.getId(), country);
        }
        return country;
    }
    public AddResponse deleteCountry(int id){
        countryIdMap.remove(id);
        AddResponse res = new AddResponse();
        res.setMessage("Country deleted");
        res.setId(id);
        return res;
    }

}
