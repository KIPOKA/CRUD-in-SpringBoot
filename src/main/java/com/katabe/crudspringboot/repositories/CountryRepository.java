package com.katabe.crudspringboot.repositories;

import com.katabe.crudspringboot.beans.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
