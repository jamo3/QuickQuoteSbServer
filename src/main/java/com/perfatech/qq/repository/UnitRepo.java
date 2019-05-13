package com.perfatech.qq.repository;

import com.perfatech.qq.domain.Unit;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitRepo extends CrudRepository<Unit, Long>
{
    Optional<Unit> findByName(String name);
}
