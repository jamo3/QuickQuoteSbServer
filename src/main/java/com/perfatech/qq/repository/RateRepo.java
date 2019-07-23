package com.perfatech.qq.repository;

import com.perfatech.qq.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequestMapping("/qq/v1/unit")
public interface RateRepo extends JpaRepository<Rate, Long> {

    List<Rate> findByUnitIdOrderByBeginDate(Long unitId);

    Optional<Rate> findByBeginDateBeforeAndEndDateAfterAndUnitId(
        LocalDate date1, LocalDate date2, Long unitId);
}
