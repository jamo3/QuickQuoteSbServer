package com.perfatech.qq.controller;

import com.perfatech.qq.domain.Unit;
import com.perfatech.qq.service.UnitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(UnitController.QQ_V1_UNIT)
public class UnitController {

    static final String QQ_V1_UNIT = "/qq/v1/unit";
    private final UnitService unitService;

    @GetMapping
    public ResponseEntity<List<Unit>> findAll() {
        return ResponseEntity.ok(unitService.findAll());
    }

    @GetMapping("/{unitId}")
    public ResponseEntity<Unit> findById(@PathVariable String unitId) {

        Optional<Unit> unitOptional = unitService.findById(unitId);

        if (unitOptional.isPresent()) {
            return ResponseEntity.ok(unitOptional.get());
        } else {
            log.error("Unit with Id " + unitId + " does not exist");
            return ResponseEntity.badRequest().build();
        }
    }
}
