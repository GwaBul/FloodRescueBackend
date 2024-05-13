package com.frun.domain.interfaces;

import com.frun.domain.model.River;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiverRepository extends JpaRepository<River, Long> {
    River findByName(String name);
}
