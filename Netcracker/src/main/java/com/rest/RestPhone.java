package com.rest;

import com.entities.Phones;
import com.repository.PhoneService.PhoneService;
import com.repository.PictureService.PictureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Admin on 28.02.2019.
 */
@RestController
public class RestPhone {
    @Autowired
    PhoneService phoneService;

    @Autowired
    PictureServiceImpl pictureService;

    //-------------------Retrieve All Phones--------------------------------------------------------

    //картинки гонять по rest не будем
    @RequestMapping(value = "/phones/", method = RequestMethod.GET)
    public ResponseEntity<List<Phones>> listAllPhones(){
        List<Phones> phones = phoneService.findAllPhones();
        /*for (Phones phone : phones) {
            pictureService.searchForPicturesList(pictureService.findAllPictures(), phone);
        }
        */
        if (phones.isEmpty()){
            return new ResponseEntity<List<Phones>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Phones>>(phones, HttpStatus.OK);
    }
    //-------------------Retrieve Single Phone--------------------------------------------------------

    @RequestMapping(value = "/phone/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Phones> getPhone(@PathVariable("id") long id){
        System.out.println("Fetching phone with id :" + id);
        /*List<Phones> phones = phoneService.findAllPhones();
        for(Phones phone: phones) {
            pictureService.searchForPicturesList(pictureService.findAllPictures(), phone);
        }
        */
        Phones phone= phoneService.findById(id);
        //Phones phone = phones.get(id);
        if (phone == null){
            System.out.println("Phone with id :" + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Phones>(phone, HttpStatus.OK);
    }

    //-------------------Create a Phone--------------------------------------------------------
    @RequestMapping(value = "/phone/createphone/", method = RequestMethod.POST)
    public ResponseEntity<Void> createPhone(@RequestBody Phones phone, UriComponentsBuilder ucBuilder){
        System.out.println("Creating phone " + phone.getModelName());

        if (phoneService.isPhoneExist(phone)){
            System.out.println("Phone with name " + phone.getModelName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        phoneService.savePhone(phone);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/phone/{id}").buildAndExpand(phone.getPhone_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    //------------------- Update a Phone --------------------------------------------------------

    @RequestMapping(value = "/phone/updatephone/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Phones> updatePhone(@PathVariable("id") long id, @RequestBody Phones phone){
        System.out.println("Updating phone " + id);
        Phones currentPhone = phoneService.findById(id);
        //pictureService.searchForPicturesList(pictureService.findAllPictures(), phone);

        if (currentPhone == null){
            System.out.println("Phone with id " + id + " not found");
            return new ResponseEntity<Phones>(HttpStatus.NOT_FOUND);
        }
        //Model_Char model, Double price, String color,String comment,List<Pictures> pictures
        currentPhone.setModel(phone.getModel());
        currentPhone.setPictures(phone.getPictures());
        currentPhone.setColor(phone.getColor());
        currentPhone.setComment(phone.getComment());
        currentPhone.setCreation_date(phone.getCreation_date());
        currentPhone.setPrice(phone.getPrice());
        currentPhone.setUsers(phone.getUsers());

        phoneService.updatePhone(currentPhone);
        return new ResponseEntity<Phones>(currentPhone, HttpStatus.OK);
    }
    //------------------- Delete a Phone --------------------------------------------------------

    @RequestMapping(value = "/phone/deletephone/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Phones> deletePhone(@PathVariable("id") long id){
        System.out.println("Fetching & deleting phone with id " + id);

        Phones phone = phoneService.findById(id);

        if (phone == null){
            System.out.println("Unable to delete. Phone with id " + id + " not found");
            return new ResponseEntity<Phones>(HttpStatus.NOT_FOUND);
        }
        phoneService.deletePhoneById(id);
        return new ResponseEntity<Phones>(phone, HttpStatus.NO_CONTENT);
    }

    //------------------- Delete All Phones --------------------------------------------------------

    @RequestMapping(value = "/phone/deleteall/", method = RequestMethod.DELETE)
    public ResponseEntity<Phones> deleteAllPhones(){
        System.out.println("Deleting all phones");
        phoneService.deleteAllPhones();
        return new ResponseEntity<Phones>(HttpStatus.NO_CONTENT);
    }

}
