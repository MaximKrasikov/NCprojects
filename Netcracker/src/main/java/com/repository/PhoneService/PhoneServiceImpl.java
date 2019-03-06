package com.repository.PhoneService;


import com.entities.Phones;
import com.entities.Pictures;
import com.repository.PhoneRepository;
import com.repository.PhoneRepositoryForRest;
import com.restentities.PhoneForRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 02.12.2018.
 */
@Service
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneRepository phoneRep;
    @Autowired
    private PhoneRepositoryForRest phoneRepositoryForRest;

    static final String URL_PHONE_POST = "http://localhost:5030";//Cracker

    @Override
    public void createPhone(Phones phones) {
        RestTemplate restTemplate = new RestTemplate();
        PhoneForRest phoneForRest= new PhoneForRest(phones);
        HttpEntity<PhoneForRest> requestBody = new HttpEntity<>(phoneForRest);
        Set<String> urlSet = new HashSet<String>();
        urlSet.add(URL_PHONE_POST);
        for (String URL_PHONE : urlSet) {
            try {
                PhoneForRest e = restTemplate.postForObject(URL_PHONE_POST, requestBody, PhoneForRest.class);
                if (e != null)  {
                    phoneRepositoryForRest.save(e);
                }
            } catch (Exception e) {
                System.out.println("I am falling!");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
//запрос на удаление
    @Override
    public void deletePhone(long phoneId) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:5030/phones/{phoneId}";
        Object[] uriPhoneValues = new Object[] {String.valueOf(phoneId)};
        restTemplate.delete(resourceUrl,uriPhoneValues);
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
