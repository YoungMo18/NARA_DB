package com.NARAHeritageLab.Server_side_NHRL_DB.Entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Material_Name", nullable = false)
    private String materialName;

    @ManyToMany(mappedBy = "materials")
    private List<Product> products;
}
