package com.NARAHeritageLab.Server_side_NHRL_DB.Entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Product_Name", nullable = false)
    private String productName;

    @Column(name = "Company_Name")
    private String companyName;

    @Column(name = "Product_Id_C")
    private String productIdC;

    @Column(name = "Hazardous")
    private Boolean hazardous;

    @Column(name = "Approve")
    private String approve;

    @Column(name = "Date_Created")
    private Date dateCreated;

    @Column(name = "Date_Reviewed")
    private Date dateReviewed;

    @Column(name = "Purpose")
    private String purpose;

    @Column(name = "Photo_URL")
    private String photoUrl;

    @Column(name = "Descriptions", length = 1500)
    private String descriptions;

    @Column(name = "Quantity_Metric")
    private String quantityMetric;

    @Column(name = "Quantity_Numeric")
    private String quantityNumeric;

    @Column(name = "Position")
    private String position;

    @Column(name = "Room_Number")
    private Integer roomNumber;

    @ManyToMany
    @JoinTable(
            name = "ProductMaterial",
            joinColumns = @JoinColumn(name = "Product_Id"),
            inverseJoinColumns = @JoinColumn(name = "Material_Id")
    )
    private List<Material> materials;

    @ManyToMany
    @JoinTable(
            name = "ProductTest",
            joinColumns = @JoinColumn(name = "Product_Id"),
            inverseJoinColumns = @JoinColumn(name = "Test_Id")
    )
    private List<Test> tests;
}


