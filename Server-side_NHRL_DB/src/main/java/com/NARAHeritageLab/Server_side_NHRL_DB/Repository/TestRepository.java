package com.NARAHeritageLab.Server_side_NHRL_DB.Repository;

import com.NARAHeritageLab.Server_side_NHRL_DB.Entity.Test;
import com.NARAHeritageLab.Server_side_NHRL_DB.Projection.TestProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {

    // Find a test by its name
    Test findByTestName(String testName);

    // Custom query to retrieve all tests with their associated instruments in a condensed form
    @Query("SELECT t FROM Test t LEFT JOIN FETCH t.instrument")
    List<TestProjection> findAllTestsCondense();

    // Custom query to retrieve a specific test by its ID with associated instrument
    @Query("SELECT t FROM Test t LEFT JOIN FETCH t.instrument WHERE t.id = :id")
    Optional<TestProjection> findTestById(int id);
}
