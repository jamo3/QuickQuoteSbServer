package com.perfatech.qq.controller;

import com.perfatech.qq.domain.Rate;
import com.perfatech.qq.service.RateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(RateController.QQ_V1_RATE)
public class RateController {

    static final String QQ_V1_RATE = "/qq/v1/rate";
    private final RateService rateService;

    @GetMapping("/{unitId}")
    public ResponseEntity<List<Rate>> findByUnit(@PathVariable String unitId) {
        return ResponseEntity.ok(rateService.findByUnit(Long.valueOf(unitId)));
    }

    @GetMapping("/{unitId}/{rentalDate}")
    public ResponseEntity<Rate> findById(
        @PathVariable String unitId, @PathVariable String rentalDate) {

        Optional<Rate> unitOptional = rateService.getRateForDate(
            Long.valueOf(unitId), LocalDate.parse(rentalDate));

        if (unitOptional.isPresent()) {
            return ResponseEntity.ok(unitOptional.get());
        } else {
            log.error("Rate not found for unit " + unitId + " on " + rentalDate);
            return ResponseEntity.badRequest().build();
        }
    }
}
