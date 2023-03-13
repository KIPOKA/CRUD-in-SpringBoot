package com.katabe.crudspringboot.controllers;


import com.katabe.crudspringboot.beans.Country;
import com.katabe.crudspringboot.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {
   @Autowired
    CountryService countryService;
    @GetMapping("/getcountries")
    public List getCountries(){
        return countryService.getAllCountries();
    }

    @GetMapping("/getcountries/{id}")
    public Country getCountryById(@PathVariable(value = "id") int id){
        return countryService.getCountryById(id);
    }
    @GetMapping("/getcountries/countryname")
    public Country getCountryByName(@RequestParam(value = "name") String countryName){
        return countryService.getCountryByName(countryName);
    }

    @PostMapping("/addcountry")
    public Country updateCountry(@RequestBody Country country){
        return countryService.addCountry(country);
    }

    @PutMapping("/updatecountry")
    public Country addCountry(@RequestBody Country country){
        return countryService.updateCountry(country);
    }
    @DeleteMapping("/deletecountry/{id}")
    public AddResponse deleteCountry(@PathVariable(value = "id") int id){
         return countryService.deleteCountry(id);
    }
}
