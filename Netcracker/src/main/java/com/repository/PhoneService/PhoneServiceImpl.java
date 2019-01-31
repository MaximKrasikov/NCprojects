package com.repository.PhoneService;


import com.entities.Model_Char;
import com.entities.Phones;
import com.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Admin on 02.12.2018.
 */
@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneRepository phoneRep;

    public PhoneServiceImpl(PhoneRepository phoneRep) {
        this.phoneRep = phoneRep;
    }

    @Override
    public Phones addPhone(Phones phone) {
        Phones savedPhone = phoneRep.saveAndFlush(phone);
        return savedPhone;
    }

    @Override
    public void deletePhoneFromModel(Model_Char model_char) {
        List<Phones> listOfPhones= phoneRep.findListByModel(model_char);
        for(Phones phone:listOfPhones){
            phoneRep.delete(phone);
        }
    }
    @Override
    public void deletePhoneFromPhone(Long phoneId) {
        List<Phones> listOfPhones= phoneRep.findListByPhone(phoneId);
        for(Phones phone:listOfPhones){
            phoneRep.delete(phone);
        }
    }

    @Override
    public void deletePhone(Phones phone) {
       phoneRep.delete(phone);
    }

    @Override
    public List<Phones> findAllPhones() {
        List<Phones> res = phoneRep.findAll();
        System.out.println(res);
        return res;
    }

    public void save(Phones phone) {
        phoneRep.save(phone);
    }


    @Override
    public Phones findPhoneById(Long phone) {
        return phoneRep.findPhoneById(phone);
    }

    public Phones findPhoneByModel(Model_Char model){
        return phoneRep.findPhoneByModel(model);
    }
}
