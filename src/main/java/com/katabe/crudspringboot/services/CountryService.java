package com.katabe.crudspringboot.services;

import com.katabe.crudspringboot.beans.Country;
import com.katabe.crudspringboot.controllers.AddResponse;
import com.katabe.crudspringboot.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Component
@Service
public class CountryService {

    @Autowired
    CountryRepository countryRep;
    public List<Country> getAllCountries(){
        return countryRep.findAll();
    }
    public Country getCountryById(int id){
        return countryRep.findById(id).get();
    }
    public Country getCountryByName(String countryName){
        List<Country> countryList = countryRep.findAll();
        Country country =null;
        for (Country coun: countryList){
            if (coun.getCountryName().equalsIgnoreCase(countryName)){
                country = coun;
            }
        }
        return country;

    }
    public Country addCountry( Country country){
        country.setId(getMax());
        countryRep.save(country);
        return country;
    }
    public  int getMax(){
        return countryRep.findAll().size() + 1;
    }
    public Country updateCountry(Country country){
        countryRep.save(country);
        return country;
    }
    public AddResponse deleteCountry(int id){
        countryRep.deleteById(id);
        AddResponse response = new AddResponse();
        response.setMessage("Country deleted successfully !!");
        response.setMessage("The ID of country deleted is: "+id);
        return response;
    }

}
