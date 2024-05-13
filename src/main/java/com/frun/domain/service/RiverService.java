package com.frun.domain.service;

import com.frun.domain.interfaces.RiverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RiverService {
    private final RiverRepository riverRepository;

    public boolean isInRiver() {
        return true;
    }
}
