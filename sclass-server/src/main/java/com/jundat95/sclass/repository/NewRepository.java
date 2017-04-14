package com.jundat95.sclass.repository;

import com.jundat95.sclass.model.NewModel;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by tinhngo on 13/04/2017.
 */
public interface NewRepository extends MongoRepository<NewModel,String> {

}
