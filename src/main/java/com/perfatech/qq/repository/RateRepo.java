package com.perfatech.qq.repository;

import com.perfatech.qq.domain.Rate;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RateRepo extends CrudRepository<Rate, Long>
{
    List<Rate> findByUnitNameOrderByBeginDate(String unitName);

    Optional<Rate> findByBeginDateBeforeAndEndDateAfterAndUnitName(
        LocalDate date1, LocalDate date2, String unitName);
}
