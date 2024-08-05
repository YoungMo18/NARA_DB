package com.NARAHeritageLab.Server_side_NHRL_DB.Projection;

import java.util.List;

public interface MaterialProjection {
    Integer getId();
    String getMaterialName();

    List<SimpleProductProjection> getProducts();
}
