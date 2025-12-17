package com.ridexpress.repository;

import com.ridexpress.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

   

    User findByEmail(String email);
}
