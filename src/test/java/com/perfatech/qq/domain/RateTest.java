package com.perfatech.qq.domain;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RateTest
{
    @Test
    public void testConstructorAndGetters() {

        BigDecimal taxRate = new BigDecimal(123.456);
        BigDecimal cleaningFee = new BigDecimal(0.123);
        Unit unit = new Unit("testUnitName",taxRate, cleaningFee, "65432");

        LocalDate begin = LocalDate.now();
        LocalDate end = LocalDate.now().minusMonths(1);
        BigDecimal price = new BigDecimal(123.456);

        Rate rate = new Rate(begin, end, 4, price, "testRateName", unit);
        assertThat(rate.getBeginDate(), Matchers.comparesEqualTo(begin));
        assertThat(rate.getEndDate(), Matchers.comparesEqualTo(end));
        assertThat(rate.getMinimumStay(), Matchers.comparesEqualTo(4));
        assertThat(rate.getPrice(), Matchers.comparesEqualTo(price));
        assertThat(rate.getName(), is("testRateName"));
        assertThat(rate.getUnit().getName(), is(unit.getName()));
        assertThat(rate.getUnit().getCleaningFee(), Matchers.comparesEqualTo(cleaningFee));
    }
}