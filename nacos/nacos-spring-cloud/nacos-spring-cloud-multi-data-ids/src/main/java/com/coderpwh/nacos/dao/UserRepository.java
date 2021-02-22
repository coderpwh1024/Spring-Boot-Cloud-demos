package com.coderpwh.nacos.dao;

import com.coderpwh.nacos.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}