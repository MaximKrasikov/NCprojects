package com.repository.PhoneService;

import com.entities.Phones;

/**
 * Created by Admin on 03.12.2018.
 */
public interface PhoneService {
    Phones addPhone(Phones phone);

    void deletePhoneFromPhone(Long phoneId);

}
