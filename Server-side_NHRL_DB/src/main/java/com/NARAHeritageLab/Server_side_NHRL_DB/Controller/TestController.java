package com.NARAHeritageLab.Server_side_NHRL_DB.Controller;

import com.NARAHeritageLab.Server_side_NHRL_DB.Entity.Test;
import com.NARAHeritageLab.Server_side_NHRL_DB.Projection.TestProjection;
import com.NARAHeritageLab.Server_side_NHRL_DB.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    @Autowired
    private TestService testService;

    // Endpoint to get all tests
    @GetMapping
    public ResponseEntity<List<TestProjection>> getAllTestsCondense() {
        List<TestProjection> tests = testService.getAllTestsCondense();
        if (tests == null || tests.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 if no tests found
        }
        return new ResponseEntity<>(tests, HttpStatus.OK); // Return 200 with the list of tests
    }

    // Endpoint to get a specific test by its ID
    @GetMapping("/{id}")
    public ResponseEntity<TestProjection> getTestById(@PathVariable int id) {
        TestProjection test = testService.getTestById(id);
        if (test == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if the test is not found
        }
        return new ResponseEntity<>(test, HttpStatus.OK); // Return 200 with the test data
    }

    // Endpoint to get the ID of a test by its name
    @GetMapping("/id")
    public ResponseEntity<Integer> getTestIdByTestName(@RequestParam String testName) {
        Integer testId = testService.getTestIdByTestName(testName);
        if (testId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if the test name is not found
        }
        return new ResponseEntity<>(testId, HttpStatus.OK); // Return 200 with the test ID
    }

    // Endpoint to create a new test
    @PostMapping
    public ResponseEntity<?> createTest(@RequestBody Test test) {
        try {
            Test savedTest = testService.saveTest(test);
            return new ResponseEntity<>(savedTest, HttpStatus.CREATED); // Return 201 with the saved test
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while creating the test: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 with an error message
        }
    }

    // Endpoint to update an existing test by its ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTest(@PathVariable int id, @RequestBody Test test) {
        try {
            test.setId(id); // Set the ID to ensure the correct test is updated
            Test updatedTest = testService.updateTest(test);
            return new ResponseEntity<>(updatedTest, HttpStatus.OK); // Return 200 with the updated test
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while updating the test: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 with an error message
        }
    }

    // Endpoint to delete a test by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTest(@PathVariable int id) {
        try {
            testService.deleteTest(id);
            return new ResponseEntity<>("Test successfully deleted.", HttpStatus.OK); // Return 200 when the test is deleted
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>("Test not found with ID: " + id, HttpStatus.NOT_FOUND); // Return 404 if the test is not found
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while deleting the test: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 with an error message
        }
    }
}
