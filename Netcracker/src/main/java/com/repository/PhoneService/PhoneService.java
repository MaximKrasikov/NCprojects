package com.repository.PhoneService;

import com.entities.Phones;

import java.util.List;

/**
 * Created by Admin on 03.12.2018.
 */
public interface PhoneService {
    public void createPhone(Phones phones);
    void updatePhone(Phones currentPhone);
    void deletePhone( long phoneId);


    Phones addPhone(Phones phone);

    List<Phones> findAllPhones();
    Phones findById(long id);
    boolean isPhoneExist(Phones phone);
    Phones findByName(String modelName);
    void savePhone(Phones phone);

    void deletePhoneById(long id);
    void deleteAllPhones();
    void deletePhone(Phones phone);
    void deletePhoneFromPhone(Long phoneId);

}
