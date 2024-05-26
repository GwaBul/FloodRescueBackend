package com.frun.domain.interfaces;

import com.frun.domain.model.River;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RiverRepository extends JpaRepository<River, Long> {
    @Query("SELECT r FROM River r JOIN r.city c WHERE c.name = :cityName")
    List<River> findAllByCityName(@Param("cityName") String cityName);

    River findByName(String name);
}
