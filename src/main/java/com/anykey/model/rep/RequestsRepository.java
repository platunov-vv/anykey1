package com.anykey.model.rep;

import com.anykey.model.domain.Requests;
import com.anykey.model.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestsRepository extends CrudRepository<Requests, String> {

    List<Requests> findAll();


    Requests save(Requests requests);
}
