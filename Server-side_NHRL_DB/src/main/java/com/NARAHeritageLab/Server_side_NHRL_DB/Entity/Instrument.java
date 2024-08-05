package com.NARAHeritageLab.Server_side_NHRL_DB.Entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
public class Instrument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Instrument_Name", nullable = false)
    private String instrumentName;

    @Column(name = "Descriptions", length = 1500)
    private String descriptions;

    @OneToMany(mappedBy = "instrument")
    private List<Test> tests;
}
