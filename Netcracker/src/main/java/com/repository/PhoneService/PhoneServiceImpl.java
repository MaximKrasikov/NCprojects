package com.repository.PhoneService;


import com.entities.Phones;
import com.entities.Pictures;
import com.repository.ModelRepository;
import com.repository.PhoneRepository;
import com.restentities.PhoneForRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created by Admin on 02.12.2018.
 */
@Service
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneRepository phoneRep;
    @Autowired
    private ModelRepository modelRepository;

    static final String URL_PHONE_POST = "http://localhost:5030";//Cracker
    static final String URL_PHONE_DELETE = "http://localhost:5030/deletephone";//Cracker
    static final String URL_PHONE_UPDATE = "http://localhost:5030/modifyphone";//Cracker
    static final String  URL_PHONE_PREFIX = "http://localhost:5030/phone";//Cracker
/*==================================REST================================================*/

    @Override
    public void createPhone(Phones phones) {
        RestTemplate restTemplate = new RestTemplate();
        PhoneForRest phoneForRest= new PhoneForRest(phones);
        HttpEntity<PhoneForRest> requestBody = new HttpEntity<>(phoneForRest);
        Set<String> urlSet = new HashSet<String>();
        urlSet.add(URL_PHONE_POST);
        for (String URL_PHONE : urlSet) {
            try {
                PhoneForRest e = restTemplate.postForObject(URL_PHONE, requestBody, PhoneForRest.class);
            } catch (Exception e) {
                System.out.println("I am falling!");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
    @Override
    public void deletePhone(Long phoneId) {
        RestTemplate restTemplate = new RestTemplate();
        Phones phone= phoneRep.findPhoneById(phoneId);
        PhoneForRest phoneForRest= new PhoneForRest(phone);
        HttpEntity<PhoneForRest> requestBody = new HttpEntity<>(phoneForRest);
        Set<String> urlSet = new HashSet<String>();
        urlSet.add(URL_PHONE_DELETE);
        for (String URL_PHONE : urlSet) {
            try {
               PhoneForRest e= restTemplate.postForObject(URL_PHONE, requestBody, PhoneForRest.class);
            } catch (Exception e) {
                System.out.println("I am falling!");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updatePhone(Phones phone) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        RestTemplate restTemplate = new RestTemplate();
        PhoneForRest phoneForRest = new PhoneForRest(phone);
        HttpEntity<PhoneForRest> requestBody = new HttpEntity<>(phoneForRest, headers);
        Set<String> urlSet = new HashSet<String>();
        urlSet.add(URL_PHONE_UPDATE);
        for (String URL_PHONE : urlSet) {
            try {
                restTemplate.put(URL_PHONE, requestBody, PhoneForRest.class);
            } catch (Exception e) {
                System.out.println("I am falling!");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /*=========================================REST===============================================*/
    @Override
    public Phones addPhone(Phones phone) {
        return  phoneRep.saveAndFlush(phone);
    }

    public Pictures getPicture(Phones phone){
        return phone.getPictures().get(0);
    }

    @Override
    public void deletePhoneFromPhone(Long phoneId) {
        Phones phone= phoneRep.findPhoneById(phoneId);
        phoneRep.delete(phone);
    }

    @Override
    public List<Phones> findAllPhones() {
        return phoneRep.findAll();
    }

    @Override
    public Phones findById(long id) {
        for (Phones phone : phoneRep.findAll()) {
            if (phone.getPhone_id() == id) {
                return phone;
            }
        }
        return null;
    }

    @Override
    public boolean isPhoneExist(Phones phone) {
        return findByName(phone.getModelName()) != null;
    }
    @Override
    public Phones findByName(String modelName) {
        for (Phones phone : phoneRep.findAll()) {
            if (phone.getModelName().equalsIgnoreCase(modelName)) {
                return phone;
            }
        }
        return null;
    }

    @Override
    public void savePhone(Phones phone) {
        phoneRep.save(phone);
    }
/*
    @Override
    public void updatePhone(Phones currentPhone) {
        int index = phoneRep.findAll().indexOf(currentPhone);
        phoneRep.findAll().set(index, currentPhone);
    }
*/
    @Override
    public void deletePhoneById(long id) {
        for (Iterator<Phones> iterator = phoneRep.findAll().iterator(); iterator.hasNext(); ) {
            Phones phone = iterator.next();
            if (phone.getPhone_id() == id) {
                iterator.remove();
            }
        }
    }

    @Override
    public void deleteAllPhones() {
        phoneRep.findAll().clear();
    }

    public void deletePhone(Phones phone) {
        phoneRep.delete(phone);
    }
    //сохранение телефона
    public void save(Phones phone) {
        phoneRep.save(phone);
    }
}
