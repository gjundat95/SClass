package com.jundat95.sclass.controller;

import com.jundat95.sclass.model.LoginModel;
import com.jundat95.sclass.model.ResponseModel;
import com.jundat95.sclass.model.UserModel;
import com.jundat95.sclass.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tinhngo on 2/6/17.
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/get-users")
    public List<UserModel> getAllUsers(){
        List<UserModel> users = new ArrayList<>();
        for (UserModel item : userRepository.findAll()){
            item.setPassword("");
            users.add(item);
        }
        return users;
    }

    @RequestMapping(value = "/get-user/{id}")
    public UserModel getUser(@PathVariable String id){
        return userRepository.findOne(id);
    }

    @RequestMapping(value = "/update-user",method = RequestMethod.PUT)
    public ResponseModel updateUser(@RequestBody UserModel user){
        try {
            UserModel userModel = userRepository.findOne(user.getUserId());
            UserModel temp = user;
            temp.setPassword(userModel.getPassword());
            userRepository.save(user);
            return new ResponseModel(
                    "1",
                    "",
                    "update complete"
            );

        }catch (Exception ex){
            return new ResponseModel(
                    "0",
                    "",
                    "update fail"
            );
        }
    }

    @RequestMapping(value = "/delete-user/{id}",method = RequestMethod.DELETE)
    public ResponseModel deleteUser(@PathVariable String id){
        try{
            userRepository.delete(id);
            return new ResponseModel(
                    "1",
                    "",
                    "delete complete"
            );

        }catch (Exception e){
            return new ResponseModel(
                    "0",
                    "",
                    "delete fail"
            );
        }
    }

    @RequestMapping(value = "/find-username/{username}")
    public List<UserModel> findByUserName(@PathVariable String email){
        List<UserModel> users = new ArrayList<>();
        userRepository.findByEmail(email).forEach(users::add);
        return users;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseModel registerUser(@RequestBody UserModel user){
        try {
            if(userRepository.findByEmail(user.getEmail()).size() <= 0){
                userRepository.save(user);
                return new ResponseModel(
                        "1",
                        "",
                        "register complete"
                );
            }
            return new ResponseModel(
                    "0",
                    "",
                    "email invalid"
            );

        }catch (Exception ex){
            return new ResponseModel(
                    "0",
                    "",
                    "register fail: "+ex
            );
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseModel findByUserNameAndPassword(@RequestBody LoginModel loginModel){
        List<UserModel> users = new ArrayList<>();
        userRepository.findByEmailAndPassword(loginModel.getEmail(),loginModel.getPassword()).forEach(users::add);
        if(users.size() > 0){
            return new ResponseModel(
                    "1",
                    "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9",
                    "login success"
            );
        }
        return new ResponseModel(
                "0",
                "",
                "login fail, please check user name and password."
        );
    }


}
