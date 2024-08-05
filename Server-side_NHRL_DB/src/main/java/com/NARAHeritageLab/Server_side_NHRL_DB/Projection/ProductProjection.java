package com.NARAHeritageLab.Server_side_NHRL_DB.Projection;

import java.util.Date;
import java.util.List;

public interface ProductProjection {
    Integer getId();
    String getProductName();
    String getCompanyName();
    String getProductIdC();
    Boolean getHazardous();
    String getApprove();
    Date getDateCreated();
    Date getDateReviewed();
    String getPurpose();
    String getPhotoUrl();
    String getDescriptions();
    String getQuantityMetric();
    String getQuantityNumeric();
    String getPosition();
    Integer getRoomNumber();
    List<MaterialProjection> getMaterials();
    List<SimpleTestProjection> getTests();
}

