package com.perfatech.qq.repository;

import com.perfatech.qq.domain.Rate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RateRepoTest
{
    @Autowired
    private RateRepo rateRepo;

    @Test
    public void findByUnitName()
    {
        // test data added via changelog file  qq-db-V0.0.1.xml
        List<Rate> rates = rateRepo.findByUnitNameOrderByBeginDate("unit-1");
        assertFalse(rates.isEmpty());
    }

    @Test
    public void getPriceForDate()
    {
        // test data added via changelog file  qq-db-V0.0.1.xml
        String date = "2019-02-17";  //default, ISO_LOCAL_DATE
        LocalDate rentalDate = LocalDate.parse(date);

        Optional<Rate> optRate = rateRepo.findByBeginDateBeforeAndEndDateAfterAndUnitName(
            rentalDate, rentalDate, "unit-1");
        assertTrue(optRate.isPresent());
        Rate rate = optRate.get();
        assertEquals("99.95", rate.getPrice().toString());
        assertEquals(5, (int) rate.getMinimumStay());
    }
}