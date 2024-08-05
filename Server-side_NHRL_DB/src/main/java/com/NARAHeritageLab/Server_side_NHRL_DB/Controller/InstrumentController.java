package com.NARAHeritageLab.Server_side_NHRL_DB.Controller;

import com.NARAHeritageLab.Server_side_NHRL_DB.Entity.Instrument;
import com.NARAHeritageLab.Server_side_NHRL_DB.Service.InstrumentService;
import com.NARAHeritageLab.Server_side_NHRL_DB.Projection.InstrumentProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instruments")
public class InstrumentController {

    @Autowired
    private InstrumentService instrumentService;

    // Endpoint to get all instruments
    @GetMapping
    public ResponseEntity<List<InstrumentProjection>> getAllInstrumentsCondense() {
        List<InstrumentProjection> instruments = instrumentService.getAllInstrumentsCondense();
        if (instruments == null || instruments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 if no instruments found
        }
        return new ResponseEntity<>(instruments, HttpStatus.OK); // Return 200 with the list of instruments
    }

    // Endpoint to get a specific instrument by its ID
    @GetMapping("/{id}")
    public ResponseEntity<InstrumentProjection> getInstrumentById(@PathVariable int id) {
        InstrumentProjection instrument = instrumentService.getInstrumentById(id);
        if (instrument == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if the instrument is not found
        }
        return new ResponseEntity<>(instrument, HttpStatus.OK); // Return 200 with the instrument data
    }

    // Endpoint to get the ID of an instrument by its name
    @GetMapping("/id")
    public ResponseEntity<Integer> getInstrumentIdByInstrumentName(@RequestParam String instrumentName) {
        Integer instrumentId = instrumentService.getInstrumentIdByInstrumentName(instrumentName);
        if (instrumentId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if the instrument name is not found
        }
        return new ResponseEntity<>(instrumentId, HttpStatus.OK); // Return 200 with the instrument ID
    }

    // Endpoint to create a new instrument
    @PostMapping
    public ResponseEntity<?> createInstrument(@RequestBody Instrument instrument) {
        try {
            Instrument savedInstrument = instrumentService.saveInstrument(instrument);
            return new ResponseEntity<>(savedInstrument, HttpStatus.CREATED); // Return 201 with the saved instrument
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while creating the instrument: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 with an error message
        }
    }

    // Endpoint to update an existing instrument by its ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateInstrument(@PathVariable int id, @RequestBody Instrument instrument) {
        try {
            instrument.setId(id); // Set the ID to ensure the correct instrument is updated
            Instrument updatedInstrument = instrumentService.updateInstrument(instrument);
            return new ResponseEntity<>(updatedInstrument, HttpStatus.OK); // Return 200 with the updated instrument
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while updating the instrument: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 with an error message
        }
    }

    // Endpoint to delete an instrument by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInstrument(@PathVariable int id) {
        try {
            instrumentService.deleteInstrument(id);
            return new ResponseEntity<>("Instrument successfully deleted.", HttpStatus.OK); // Return 200 when the instrument is deleted
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>("Instrument not found with ID: " + id, HttpStatus.NOT_FOUND); // Return 404 if the instrument is not found
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while deleting the instrument: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 with an error message
        }
    }
}
