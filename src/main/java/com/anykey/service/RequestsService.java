package com.anykey.service;

import com.anykey.model.domain.Requests;
import com.anykey.model.domain.User;

import java.util.List;

public interface RequestsService {
    List<Requests> findAll();

    Requests save(Requests requests);
}
