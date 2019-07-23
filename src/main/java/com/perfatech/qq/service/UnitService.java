package com.perfatech.qq.service;

import com.perfatech.qq.domain.Unit;
import com.perfatech.qq.repository.UnitRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UnitService {

    private final UnitRepo unitRepo;

    public List<Unit> findAll() {
        return unitRepo.findAll();
    }

    public Optional<Unit> findById(String id) {
        return unitRepo.findById(Long.valueOf(id));
    }

    public Unit save(Unit unit) {
        return unitRepo.save(unit);
    }

    public void delete(String id) {
        unitRepo.deleteById(Long.valueOf(id));
    }
}
