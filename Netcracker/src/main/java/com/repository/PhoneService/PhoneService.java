package com.repository.PhoneService;

import com.entities.Phones;

import java.util.List;

/**
 * Created by Admin on 03.12.2018.
 */
public interface PhoneService {
    Phones addPhone(Phones phone);

    void deletePhoneFromPhone(Long phoneId);

    List<Phones> findAllPhones();

    Phones findById(long id);

    boolean isPhoneExist(Phones phone);

    Phones findByName(String modelName);

    void savePhone(Phones phone);

    void updatePhone(Phones currentPhone);

    void deletePhoneById(long id);

    void deleteAllPhones();
}
