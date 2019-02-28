package com.repository.PhoneService;


import com.entities.Phones;
import com.entities.Pictures;
import com.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Admin on 02.12.2018.
 */
@Service
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneRepository phoneRep;

    public Pictures getPicture(Phones phone){
        return phone.getPictures().get(0);
    }
    //добавление телефона
    @Override
    public Phones addPhone(Phones phone) {
        Phones savedPhone = phoneRep.saveAndFlush(phone);
        return savedPhone;
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
