package com.NARAHeritageLab.Server_side_NHRL_DB.Projection;

import java.util.List;

public interface InstrumentProjection {
    Integer getId();
    String getInstrumentName();
    String getDescriptions();
    List<SimpleTestProjection> getTests();
}

