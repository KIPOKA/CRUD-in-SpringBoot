package com.katabe.crudspringboot.controllers;


import com.katabe.crudspringboot.beans.Country;
import com.katabe.crudspringboot.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CountryController {
   @Autowired
    CountryService countryService;
    @GetMapping("/getcountries")
    public ResponseEntity<List<Country>> getCountries(){

        try {
            List<Country> countries =  countryService.getAllCountries();
            return new ResponseEntity<List<Country>>(countries, HttpStatus.FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getcountries/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable(value = "id") int id){
        try {
            Country country =  countryService.getCountryById(id);
            return new ResponseEntity<Country>(country, HttpStatus.FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/getcountries/countryname")
    public ResponseEntity<Country> getCountryByName(@RequestParam(value = "name") String countryName){
        try {
            Country country =  countryService.getCountryByName(countryName);
            return new ResponseEntity<Country>(country, HttpStatus.FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/updatecountry/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable(value = "id") int id, @RequestBody Country country){
        try {


            Country exist = countryService.getCountryById(id);
            exist.setCountryName(country.getCountryName());
            exist.setCountryCapital(country.getCountryCapital());
            Country updated_country = countryService.updateCountry(exist);
            return new ResponseEntity<Country>(updated_country, HttpStatus.ACCEPTED);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/addcountry")
    public ResponseEntity<Country >addCountry(@RequestBody Country country){
        try {
            countryService.addCountry(country);
            return new ResponseEntity<>( HttpStatus.CREATED);
        }catch (NoSuchElementException ex){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }
    @DeleteMapping("/deletecountry/{id}")
    public ResponseEntity deleteCountry(@PathVariable(value = "id") int id){
        try {
            Country country = countryService.getCountryById(id);
            countryService.deleteCountry(id);
            return new ResponseEntity<>( HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
