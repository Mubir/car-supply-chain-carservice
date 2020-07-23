package com.mubir.carservice.web.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CarPagedList  extends PageImpl {
    public CarPagedList(List content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public CarPagedList(List content) {
        super(content);
    }
}
