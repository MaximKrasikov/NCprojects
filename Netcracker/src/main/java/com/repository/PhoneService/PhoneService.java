package com.repository.PhoneService;

import com.entities.Model_Char;
import com.entities.Phones;

import java.util.List;

/**
 * Created by Admin on 03.12.2018.
 */
public interface PhoneService {
    Phones addPhone(Phones phone);

    void deletePhoneFromModel(Model_Char model_char);

    List<Phones> findAllPhones();

     void deletePhone(Phones phone);

     Phones findPhoneById(Long phone);

     void deletePhoneFromPhone(Long phoneId);

}
