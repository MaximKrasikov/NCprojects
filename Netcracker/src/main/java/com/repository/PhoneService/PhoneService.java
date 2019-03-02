package com.repository.PhoneService;

import com.entities.Phones;

import java.util.List;

/**
 * Created by Admin on 03.12.2018.
 */
public interface PhoneService {
    //Сторонний магазин будет посылать запросы на главный магазин с просьбой на создание/изменение/удаление телефона
    //String modelName,long priceMin,long priceMax

    /*Rest
    Сторонний магазин будет посылать запросы на главный магазин с просьбой на создание/изменение/удаление телефона
    String modelName,long priceMin,long priceMax
    */
    Phones createPhone(Phones phone);
    Phones updatePhoneFromRest(Phones updateInfo);
    void deletePhoneFromRest(Phones phone);

    void updatePhone(Phones currentPhone);
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
