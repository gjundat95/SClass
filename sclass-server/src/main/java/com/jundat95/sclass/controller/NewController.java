package com.jundat95.sclass.controller;

import com.jundat95.sclass.model.NewModel;
import com.jundat95.sclass.model.ResponseModel;
import com.jundat95.sclass.repository.NewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tinhngo on 13/04/2017.
 */
@RestController
public class NewController {

    @Autowired
    private NewRepository newRepository;

    @RequestMapping(value = "/post-new",method = RequestMethod.POST)
    public ResponseModel crateNew(@RequestBody NewModel newModel){
        try {
            newRepository.save(newModel);
            return new ResponseModel(
                    "1",
                    "",
                    "post new success"
            );
        }catch (Exception ex){
            return new ResponseModel(
                    "0",
                    "",
                    "post new fail"
            );
        }
    }

    @RequestMapping(value = "/edit-new",method = RequestMethod.PUT)
    public ResponseModel editNew(@RequestBody NewModel newModel){
        try {
            newRepository.save(newModel);
            return new ResponseModel(
                    "1",
                    "",
                    "edit new success"
            );
        }catch (Exception ex){
            return new ResponseModel(
                    "0",
                    "",
                    "edit new fail"
            );
        }
    }

    @RequestMapping(value = "/get-news",method = RequestMethod.GET)
    public List<NewModel> getNews(){
        return newRepository.findAll();
    }

    @RequestMapping(value = "/delete-new/{id}",method = RequestMethod.DELETE)
    public ResponseModel deleteNew(@PathVariable String id){
        NewModel newModel = newRepository.findOne(id);
        if(newModel != null){
            newRepository.delete(id);
            return new ResponseModel(
                    "1",
                    "",
                    "delete new success"
            );
        }else {
            return new ResponseModel(
                    "0",
                    "",
                    "id user invalid"
            );
        }
    }

}
