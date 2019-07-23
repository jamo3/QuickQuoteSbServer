package com.perfatech.qq.service;

import com.perfatech.qq.domain.Rate;
import com.perfatech.qq.repository.RateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RateService {

    private final RateRepo rateRepo;

    public List<Rate> findByUnit(Long unitId) {
        return rateRepo.findByUnitIdOrderByBeginDate(unitId);
    }

    public Optional<Rate> getRateForDate(
        Long unitId, LocalDate rentalDate) {
        return rateRepo.findByBeginDateBeforeAndEndDateAfterAndUnitId(
            rentalDate, rentalDate, unitId);
    }

    public Rate save(Rate rate) {
        return rateRepo.save(rate);
    }

    public void delete(Long id) {
        rateRepo.deleteById(id);
    }

}
