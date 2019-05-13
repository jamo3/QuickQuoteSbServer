package com.perfatech.qq.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * a place that can be rented
 */
@Data
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name="rental_unit")
public class Unit implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private BigDecimal taxRate;

    @Column
    private BigDecimal cleaningFee;

    @Column(length = 256)
    private String calendarId;

    public Unit() {
        this.taxRate = BigDecimal.ZERO;
        this.cleaningFee = BigDecimal.ZERO;
    }

    public Unit(String name, BigDecimal taxRate, BigDecimal cleaningFee, String calendarId)
    {
        this.name = name;
        this.taxRate = taxRate;
        this.cleaningFee = cleaningFee;
        this.calendarId = calendarId;
    }
}
