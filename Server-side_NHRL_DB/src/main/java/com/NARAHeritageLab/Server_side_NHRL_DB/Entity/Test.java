package com.NARAHeritageLab.Server_side_NHRL_DB.Entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Test_Name", nullable = false)
    private String testName;

    @Column(name = "Test_Date")
    private Date testDate;

    @Column(name = "Descriptions", length = 1500)
    private String descriptions;

    @Column(name = "Result")
    private String result;

    @Column(name = "Test_Sheet")
    private String testSheet;

    @ManyToOne
    @JoinColumn(name = "Instrument_Id", nullable = false)
    private Instrument instrument;

    @ManyToMany(mappedBy = "tests")
    private List<Product> products;
}



