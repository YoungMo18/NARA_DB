package com.NARAHeritageLab.Server_side_NHRL_DB.Service;

import com.NARAHeritageLab.Server_side_NHRL_DB.Entity.Instrument;
import com.NARAHeritageLab.Server_side_NHRL_DB.Repository.InstrumentRepository;
import com.NARAHeritageLab.Server_side_NHRL_DB.Projection.InstrumentProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrumentService {

    @Autowired
    private InstrumentRepository instrumentRepository; // Repository layer dependency injection

    // Retrieve all instruments from the database
    public List<Instrument> getAllInstruments() {
        return instrumentRepository.findAll();
    }

    // Retrieve a specific instrument by its ID with associated tests
    public InstrumentProjection getInstrumentById(int id) {
        return instrumentRepository.findInstrumentWithTestsById(id).orElse(null);
    }

    // Save a new or updated instrument to the database
    public Instrument saveInstrument(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    // Delete an instrument from the database by its ID
    public void deleteInstrument(int id) {
        instrumentRepository.deleteById(id);
    }

    // Save a list of instruments to the database
    public List<Instrument> saveAllInstruments(List<Instrument> instruments) {
        return instrumentRepository.saveAll(instruments);
    }

    // Update an existing instrument in the database
    public Instrument updateInstrument(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    // Retrieve the ID of an instrument by its name
    public Integer getInstrumentIdByInstrumentName(String instrumentName) {
        Instrument instrument = instrumentRepository.findByInstrumentName(instrumentName);
        return (instrument != null) ? instrument.getId() : null;
    }

    // Retrieve a condensed list of instruments from the database
    public List<InstrumentProjection> getAllInstrumentsCondense() {
        return instrumentRepository.findAllInstrumentsCondense();
    }
}
