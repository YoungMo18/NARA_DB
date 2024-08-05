package com.NARAHeritageLab.Server_side_NHRL_DB.Projection;

public interface SimpleTestProjection {
    Integer getId();
    String getTestName();
    SimpleInstrumentProjection getInstrument();
}
