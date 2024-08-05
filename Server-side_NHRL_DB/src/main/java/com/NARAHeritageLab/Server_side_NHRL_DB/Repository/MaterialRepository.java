package com.NARAHeritageLab.Server_side_NHRL_DB.Repository;

import com.NARAHeritageLab.Server_side_NHRL_DB.Entity.Material;
import com.NARAHeritageLab.Server_side_NHRL_DB.Projection.MaterialProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {

    // Find a material by its name
    Material findByMaterialName(String materialName);

    // Custom query to retrieve a condensed list of materials
    @Query("SELECT m FROM Material m")
    List<MaterialProjection> findAllMaterialsCondense();

    // Custom query to retrieve a specific material by its ID
    @Query("SELECT m FROM Material m WHERE m.id = :id")
    MaterialProjection findMaterialById(@Param("id") Integer id);
}
