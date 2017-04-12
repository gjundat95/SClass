package com.jundat95.gpsgroup.repository;

import com.jundat95.gpsgroup.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by tinhngo on 2/6/17.
 */
public interface UserRepository extends MongoRepository<UserModel,String> {

    public List<UserModel> findByFirstName(String userName);
    public List<UserModel> findByEmail(String userName);
    public List<UserModel> findByEmailAndPassword(String email, String password);

}
