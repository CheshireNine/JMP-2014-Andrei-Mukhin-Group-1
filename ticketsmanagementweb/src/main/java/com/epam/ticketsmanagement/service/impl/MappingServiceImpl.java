package com.epam.ticketsmanagement.service.impl;

import org.springframework.stereotype.Service;

import com.epam.ticketsmanagement.service.MappingService;

@Service
public class MappingServiceImpl implements MappingService {

    public MappingServiceImpl() {
    }

    @Override
    public Object map(Action action, Object fromObj) {
        return action.doConversion(fromObj);
    }

}
