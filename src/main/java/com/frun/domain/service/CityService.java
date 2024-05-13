package com.frun.domain.service;

import com.frun.domain.interfaces.CityRepository;
import com.frun.domain.model.City;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    @Transactional
    public void createCity(String name){
        City city = City.builder().name(name).build();
        cityRepository.save(city);
    }
}
