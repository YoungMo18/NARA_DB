package com.NARAHeritageLab.Server_side_NHRL_DB.Service;

import com.NARAHeritageLab.Server_side_NHRL_DB.Entity.Material;
import com.NARAHeritageLab.Server_side_NHRL_DB.Projection.MaterialProjection;
import com.NARAHeritageLab.Server_side_NHRL_DB.Repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository; // Repository layer dependency injection

    // Retrieve all materials from the database
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    // Retrieve a specific material by its ID
    public MaterialProjection getMaterialById(int id) {
        return materialRepository.findMaterialById(id);
    }

    // Save a new or updated material to the database
    public Material saveMaterial(Material material) {
        return materialRepository.save(material);
    }

    // Delete a material from the database by its ID
    public void deleteMaterial(int id) {
        materialRepository.deleteById(id);
    }

    // Save a list of materials to the database
    public List<Material> saveAllMaterials(List<Material> materials) {
        return materialRepository.saveAll(materials);
    }

    // Update an existing material in the database
    public Material updateMaterial(Material material) {
        return materialRepository.save(material);
    }

    // Retrieve the ID of a material by its name
    public Integer getMaterialIdByMaterialName(String materialName) {
        Material material = materialRepository.findByMaterialName(materialName);
        return (material != null) ? material.getId() : null;
    }

    // Retrieve a condensed list of materials from the database
    public List<MaterialProjection> getAllMaterialsCondense() {
        return materialRepository.findAllMaterialsCondense();
    }
}
