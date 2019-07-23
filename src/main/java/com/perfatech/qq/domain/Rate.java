package com.perfatech.qq.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * the rental rate for a place at a given period of time
 */
@Data
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name="rental_rate")
public class Rate implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column
    private LocalDate beginDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column
    private LocalDate endDate;

    @Column
    private Integer minimumStay;

    @Column
    private BigDecimal price;

    @Column
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="rental_unit_id")
    private Unit unit;


    public Rate() {
    }

    Rate(LocalDate beginDate, LocalDate endDate, Integer minimumStay, BigDecimal price, String name, Unit rentalUnit)
    {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.minimumStay = minimumStay;
        this.price = price;
        this.name = name;
        this.unit = rentalUnit;
     }

}
