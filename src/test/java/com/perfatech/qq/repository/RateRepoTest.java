package com.perfatech.qq.repository;

import com.perfatech.qq.domain.Rate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
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
public class RateRepoTest
{
    @Autowired
    private RateRepo rateRepo;

    @Test
    public void findByUnitName()
    {
        // test data added via changelog file  qq-db-V0.0.1.xml
        List<Rate> ratesForUnit1 = rateRepo.findByUnitNameOrderByBeginDate("unit-1");
        assertFalse(ratesForUnit1.isEmpty());

        Rate rate = ratesForUnit1.get(0);
        assertEquals("rate-1.1", rate.getName());
        assertEquals(Integer.valueOf(1), rate.getId());

        assertEquals(LocalDate.parse("2019-01-01"), rate.getBeginDate());
        assertEquals( LocalDate.parse("2019-02-28"), rate.getEndDate());
        assertEquals(Integer.valueOf(5), rate.getMinimumStay());

        BigDecimal price = new BigDecimal(99.95);
        BigDecimal priceDelta = new BigDecimal(0.001);
        assertThat(rate.getPrice(), is(closeTo(price, priceDelta)));
    }

    @Test
    public void getPriceForDate()
    {
        // test data added via changelog file  qq-db-V0.0.1.xml
        String date = "2019-07-17";  //default, ISO_LOCAL_DATE
        LocalDate rentalDate = LocalDate.parse(date);

        Optional<Rate> optRate = rateRepo.findByBeginDateBeforeAndEndDateAfterAndUnitName(
            rentalDate, rentalDate, "unit-1");
        assertTrue(optRate.isPresent());
        Rate rate = optRate.get();
        assertEquals("400.00", rate.getPrice().toString());
        assertEquals(4, (int) rate.getMinimumStay());
    }
}