package com.katabe.crudspringboot;

import com.katabe.crudspringboot.beans.Country;
import com.katabe.crudspringboot.repositories.CountryRepository;
import com.katabe.crudspringboot.services.CountryService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {ServiceMockitoTest.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceMockitoTest {
    @Mock
    CountryRepository countryRepository;

    @InjectMocks
    CountryService countryService;

    public List<Country>mycountries;

    @Test
    @Order(1)
    public void test_getAllCountries(){
        List<Country> mycountries = new ArrayList<Country>();
        mycountries.add(new Country(1, "India", "Delhi"));
        mycountries.add(new Country(2, "Egypt", "Cairo"));
        mycountries.add(new Country(3, "RSA", "Pretoria"));
        when(countryRepository.findAll()).thenReturn(mycountries);
        assertEquals(3,countryService.getAllCountries().size());
    }
    @Test
    @Order(2)
    public void test_getCountryById(){
        List<Country> mycountries = new ArrayList<Country>();
        mycountries.add(new Country(1, "India", "Delhi"));
        mycountries.add(new Country(2, "Egypt", "Cairo"));
        mycountries.add(new Country(3, "RSA", "Pretoria"));
        int countryId =1;
        when(countryRepository.findAll()).thenReturn(mycountries);
        assertEquals(countryId,countryService.getCountryById(countryId).getId());
    }
    @Test
    @Order(3)
    public void test_getCountryByName(){
        List<Country> mycountries = new ArrayList<Country>();
        mycountries.add(new Country(1, "India", "Delhi"));
        mycountries.add(new Country(2, "Egypt", "Cairo"));
        mycountries.add(new Country(3, "RSA", "Pretoria"));
        String countryName ="India";
        when(countryRepository.findAll()).thenReturn(mycountries);
        assertEquals(countryName,countryService.getCountryByName(countryName).getCountryName());
    }
    @Test
    @Order(4)
    public void test_addCountry(){
        Country country = new Country(4, "Germany", "Berlin");
        when(countryRepository.save(country)).thenReturn(country);
        assertEquals(country,countryService.addCountry(country));
    }

    @Test
    @Order(5)
    public void test_updateCountry(){
        Country country = new Country(4, "Congo", "Kin");
        when(countryRepository.save(country)).thenReturn(country);
        assertEquals(country,countryService.updateCountry(country));
    }
    @Test
    @Order(6)
    public void test_deleteCountry(){
        int countryId =1;
        countryService.deleteCountry(countryId);
        verify(countryRepository, times(1)).deleteById(countryId);
    }
}
