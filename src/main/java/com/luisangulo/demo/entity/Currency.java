package com.luisangulo.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_currency")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long currency_id;
    private String currency;
    private String currency_desc;
    @Temporal(TemporalType.TIMESTAMP)
    private Date currency_register;
}
