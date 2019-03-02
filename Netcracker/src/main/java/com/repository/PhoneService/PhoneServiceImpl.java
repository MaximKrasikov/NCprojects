package com.repository.PhoneService;


import com.entities.Phones;
import com.entities.Pictures;
import com.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Admin on 02.12.2018.
 */
@Service
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneRepository phoneRep;

    static final String URL_CREATE_PHONE = "http://localhost:8080/phone/";
    static final String URL_UPDATE_PHONE = "http://localhost:8080/phone/";
    static final String URL_PHONE_PREFIX = "http://localhost:8080/phone/";

    /*
    Сторонний магазин будет посылать запросы на главный магазин с просьбой на создание/изменение/удаление телефона
    String modelName,long priceMin,long priceMax
    */
    @Override
    public Phones createPhone(Phones phone) {
        //Model_Char model, long price, String color
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_XML_VALUE);
        headers.setContentType(MediaType.APPLICATION_XML);

        RestTemplate restTemplate = new RestTemplate();
        // Data attached to the request.
        HttpEntity<Phones> requestBody = new HttpEntity<>(phone, headers);
        // Send request with POST method.
        ResponseEntity<Phones> result = restTemplate.postForEntity(URL_CREATE_PHONE, requestBody, Phones.class);
        if (result.getStatusCode() == HttpStatus.CREATED) {
            Phones e = result.getBody();
            System.out.println("(Client Side) Phone Created: "+ e.getPhone_id()+" Name: "+e.getModelName());
            return e;
        }
        return  null;
    }
    @Override
    public Phones updatePhoneFromRest(Phones updateInfo) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        RestTemplate restTemplate = new RestTemplate();
        // Data attached to the request.
        HttpEntity<Phones> requestBody = new HttpEntity<>(updateInfo, headers);
        // Send request with PUT method.
        restTemplate.exchange(URL_UPDATE_PHONE, HttpMethod.PUT,  requestBody, Void.class);
        String resourceUrl = URL_PHONE_PREFIX + "/" + updateInfo.getPhone_id().toString();

        Phones e = restTemplate.getForObject(resourceUrl, Phones.class);

        if (e != null) {
            System.out.println("(Client side) Phone after update: ");
            System.out.println(e.toString());
            return  e;
        }
        return null;
    }
    @Override
    public void deletePhoneFromRest(Phones phone) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:8080/phone/{id}";
        Object[] uriValues = new Object[] { phone.getPhone_id().toString() };
        restTemplate.delete(resourceUrl, uriValues);
        Phones e = restTemplate.getForObject(resourceUrl, Phones.class);
        if (e != null) {
            System.out.println("(Client side) Phone after delete: ");
            System.out.println("Phone: " + e.getPhone_id() + " - " + e.getModelName());
        } else {
            System.out.println("Phone not found!");
        }
    }

    //добавление телефона
    @Override
    public Phones addPhone(Phones phone) {
        Phones savedPhone = phoneRep.saveAndFlush(phone);
        return savedPhone;
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

    @Override
    public void updatePhone(Phones currentPhone) {
        int index = phoneRep.findAll().indexOf(currentPhone);
        phoneRep.findAll().set(index, currentPhone);
    }

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
