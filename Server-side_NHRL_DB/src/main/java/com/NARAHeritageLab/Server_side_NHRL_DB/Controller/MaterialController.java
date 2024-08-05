package com.NARAHeritageLab.Server_side_NHRL_DB.Controller;

import com.NARAHeritageLab.Server_side_NHRL_DB.Entity.Material;
import com.NARAHeritageLab.Server_side_NHRL_DB.Projection.MaterialProjection;
import com.NARAHeritageLab.Server_side_NHRL_DB.Service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    // Endpoint to get all materials
    @GetMapping
    public ResponseEntity<List<MaterialProjection>> getAllMaterialsCondense() {
        List<MaterialProjection> materials = materialService.getAllMaterialsCondense();
        if (materials == null || materials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 if no materials found
        }
        return new ResponseEntity<>(materials, HttpStatus.OK); // Return 200 with the list of materials
    }

    // Endpoint to get a specific material by its ID
    @GetMapping("/{id}")
    public ResponseEntity<MaterialProjection> getMaterialById(@PathVariable int id) {
        MaterialProjection material = materialService.getMaterialById(id);
        if (material == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if the material is not found
        }
        return new ResponseEntity<>(material, HttpStatus.OK); // Return 200 with the material data
    }

    // Endpoint to get the ID of a material by its name
    @GetMapping("/id")
    public ResponseEntity<Integer> getMaterialIdByMaterialName(@RequestParam String materialName) {
        Integer materialId = materialService.getMaterialIdByMaterialName(materialName);
        if (materialId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if the material name is not found
        }
        return new ResponseEntity<>(materialId, HttpStatus.OK); // Return 200 with the material ID
    }

    // Endpoint to create a new material
    @PostMapping
    public ResponseEntity<?> createMaterial(@RequestBody Material material) {
        try {
            Material savedMaterial = materialService.saveMaterial(material);
            return new ResponseEntity<>(savedMaterial, HttpStatus.CREATED); // Return 201 with the saved material
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while creating the material: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 with an error message
        }
    }

    // Endpoint to update an existing material by its ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMaterial(@PathVariable int id, @RequestBody Material material) {
        try {
            material.setId(id); // Set the ID to ensure the correct material is updated
            Material updatedMaterial = materialService.updateMaterial(material);
            return new ResponseEntity<>(updatedMaterial, HttpStatus.OK); // Return 200 with the updated material
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while updating the material: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 with an error message
        }
    }

    // Endpoint to delete a material by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMaterial(@PathVariable int id) {
        try {
            materialService.deleteMaterial(id);
            return new ResponseEntity<>("Material successfully deleted.", HttpStatus.OK); // Return 200 when the material is deleted
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>("Material not found with ID: " + id, HttpStatus.NOT_FOUND); // Return 404 if the material is not found
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while deleting the material: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 with an error message
        }
    }
}
