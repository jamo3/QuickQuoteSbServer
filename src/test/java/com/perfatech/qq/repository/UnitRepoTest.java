package com.perfatech.qq.repository;

import com.perfatech.qq.domain.Unit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitRepoTest
{
    @Autowired
    private UnitRepo unitRepo;

    @Test
    public void findById()  {
        // test data added via changelog file  qq-db-V0.0.1.xml
        Optional<Unit> unit = unitRepo.findById(1L);
        assertTrue(unit.isPresent());

        Unit unit1 = unit.get();
        assertEquals("unit-1", unit1.getName());

        BigDecimal taxRate = new BigDecimal(0.2345);
        BigDecimal taxRateDelta = new BigDecimal(0.00001);
        assertThat(unit1.getTaxRate(), is(closeTo(taxRate, taxRateDelta)));

        BigDecimal cleaningFee = new BigDecimal(123.45);
        BigDecimal cleaningFeeDelta = new BigDecimal(0.001);
        assertThat(unit1.getCleaningFee(), is(closeTo(cleaningFee, cleaningFeeDelta)));

        assertEquals("3456", unit1.getCalendarId());

        unit = unitRepo.findById(45L);
        assertFalse(unit.isPresent());
    }

    @Test
    public void findAll() {
        // test data added via changelog file  qq-db-V0.0.1.xml
        List<Unit> units = unitRepo.findAll();
        assertFalse(units.isEmpty());
        Assert.assertEquals(2, units.size());
        Assert.assertTrue(units.stream().anyMatch(s -> "unit-1".equals(s.getName())));
        Assert.assertTrue(units.stream().anyMatch(s -> "unit-2".equals(s.getName())));
    }
}