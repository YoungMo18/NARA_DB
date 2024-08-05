package com.NARAHeritageLab.Server_side_NHRL_DB.Repository;

import com.NARAHeritageLab.Server_side_NHRL_DB.Projection.InstrumentProjection;
import com.NARAHeritageLab.Server_side_NHRL_DB.Entity.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, Integer> {

    // Find an instrument by its name
    Instrument findByInstrumentName(String instrumentName);

    // Custom query to retrieve a list of instruments with associated tests in a condensed form
    @Query("SELECT i FROM Instrument i LEFT JOIN FETCH i.tests")
    List<InstrumentProjection> findAllInstrumentsCondense();

    // Custom query to retrieve a specific instrument by its ID with associated tests
    @Query("SELECT i FROM Instrument i LEFT JOIN FETCH i.tests t WHERE i.id = :id")
    Optional<InstrumentProjection> findInstrumentWithTestsById(int id);
}
