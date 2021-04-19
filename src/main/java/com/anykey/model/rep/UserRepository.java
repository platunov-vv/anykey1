package com.anykey.model.rep;

import com.anykey.model.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    List<User> findAll();


    User save(User user);
}
