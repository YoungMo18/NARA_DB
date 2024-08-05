package com.NARAHeritageLab.Server_side_NHRL_DB.Projection;

import java.util.Date;
import java.util.List;

public interface TestProjection {
    Integer getId();
    String getTestName();
    Date getTestDate();
    String getDescriptions();
    String getResult();
    String getTestSheet();
    SimpleInstrumentProjection getInstrument();
}
