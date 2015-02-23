package com.epam.ticketsmanagement.service;

import com.epam.ticketsmanagement.service.impl.Action;

public interface MappingService {
    Object map(Action action, Object fromObj);
}
