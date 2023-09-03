package com.luisangulo.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="tbl_exchange")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exchange_id;

    private Long currency_change_from;

    private Long currency_change_to;

    private BigDecimal exchange_rate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date exchange_register;

    @Temporal(TemporalType.TIMESTAMP)
    private Date exchange_update;
}
