package com.perfatech.qq.repository;

import com.perfatech.qq.domain.Unit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitRepoTest
{
    @Autowired
    private UnitRepo unitRepo;

    @Test
    public void findByName()
    {
        // test data added via changelog file  qq-db-V0.0.1.xml
        Optional<Unit> unit = unitRepo.findByName("unit-1");
        assertTrue(unit.isPresent());

        unit = unitRepo.findByName("does-not-exist");
        assertFalse(unit.isPresent());
    }
}