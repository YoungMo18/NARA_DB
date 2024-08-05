package com.NARAHeritageLab.Server_side_NHRL_DB.Service;

import com.NARAHeritageLab.Server_side_NHRL_DB.Entity.Test;
import com.NARAHeritageLab.Server_side_NHRL_DB.Projection.TestProjection;
import com.NARAHeritageLab.Server_side_NHRL_DB.Repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository; // Repository layer dependency injection

    // Retrieve all tests from the database
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    // Retrieve a specific test by its ID with projections
    public TestProjection getTestById(int id) {
        return testRepository.findTestById(id).orElse(null);
    }

    // Save a new or updated test to the database
    public Test saveTest(Test test) {
        return testRepository.save(test);
    }

    // Delete a test from the database by its ID
    public void deleteTest(int id) {
        testRepository.deleteById(id);
    }

    // Save a list of tests to the database (batch operation)
    public List<Test> saveAllTests(List<Test> tests) {
        return testRepository.saveAll(tests);
    }

    // Update an existing test in the database
    public Test updateTest(Test test) {
        return testRepository.save(test);
    }

    // Retrieve the ID of a test by its name
    public Integer getTestIdByTestName(String testName) {
        Test test = testRepository.findByTestName(testName);
        return (test != null) ? test.getId() : null;
    }

    // Retrieve a condensed list of tests from the database
    public List<TestProjection> getAllTestsCondense() {
        return testRepository.findAllTestsCondense();
    }
}
