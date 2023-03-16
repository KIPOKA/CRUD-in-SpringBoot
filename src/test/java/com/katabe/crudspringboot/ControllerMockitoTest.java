package com.katabe.crudspringboot;


import com.katabe.crudspringboot.beans.Country;
import com.katabe.crudspringboot.controllers.CountryController;
import com.katabe.crudspringboot.services.CountryService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ControllerMockitoTest.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControllerMockitoTest {



    @Mock
    CountryService countryService;

    @InjectMocks
    CountryController countryController;
    List<Country> mycountries ;
    Country country;

    @Test
    @Order(1)
    public void test_getAllCountries(){
        List<Country> mycountries = new ArrayList<Country>();
        mycountries.add(new Country(1, "India", "Delhi"));
        mycountries.add(new Country(2, "Egypt", "Cairo"));
        mycountries.add(new Country(3, "RSA", "Pretoria"));
        when(countryService.getAllCountries()).thenReturn(mycountries);
        ResponseEntity<List<Country>> response = countryController.getCountries();
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(3,response.getBody().size());
    }
    @Test
    @Order(2)
    public void test_getCountryByiD(){
        country = new Country(2, "France", "Paris");
        int countryId =2;
        when(countryService.getCountryById(countryId)).thenReturn(country);
        ResponseEntity<Country> response = countryController.getCountryById(countryId);
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(countryId, response.getBody().getId());
    }

    @Test
    @Order(3)
    public void test_getCountryByName(){
        country = new Country(2, "France", "Paris");
        String countryName="France";
        when(countryService.getCountryByName(countryName)).thenReturn(country);
        ResponseEntity<Country> response = countryController.getCountryByName(countryName);
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(countryName, response.getBody().getCountryName());
    }
    @Test
    @Order(4)
    public void test_addCountry(){
        country = new Country(2, "United Kingdom", "London");
        when(countryService.addCountry(country)).thenReturn(country);
        ResponseEntity<Country> response = countryController.addCountry(country);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(country, response.getBody());
    }
    @Test
    @Order(5)
    public void test_updateCountry(){
       Country existCountry = new Country(3, "UK", "London");
       int countryId =3;
        when(countryService.getCountryById(countryId)).thenReturn(existCountry);
        when(countryService.updateCountry(existCountry)).thenReturn(existCountry);
        ResponseEntity<Country> response = countryController.updateCountry(countryId, existCountry);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("UK", response.getBody().getCountryName());
        assertEquals("London", response.getBody().getCountryCapital());
    }

    @Test
    @Order(5)
    public void test_deleteCountry(){
        Country deletedCountry = new Country(3, "UK", "London");
        int countryId =3;
        when(countryService.getCountryById(countryId)).thenReturn(deletedCountry);
        ResponseEntity<Country> response = countryController.deleteCountry(countryId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
