package com.anykey.service.impl;

import com.anykey.model.domain.Requests;
import com.anykey.model.domain.User;
import com.anykey.model.rep.RequestsRepository;
import com.anykey.model.rep.UserRepository;
import com.anykey.service.RequestsService;
import com.anykey.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestsServiceImpl implements RequestsService {

    private final RequestsRepository requestsRepository;
    @Override
    public List<Requests> findAll() {
        return requestsRepository.findAll();
    }

    @Override
    public Requests save(Requests requests) {
        return requestsRepository.save(requests);
    }
}
