package com.repository.PhoneService;


import com.entities.Phones;
import com.entities.Pictures;
import com.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Phones phone= phoneRep.findListByPhone(phoneId);
        phoneRep.delete(phone);

    }
    public void deletePhone(Phones phone) {
        phoneRep.delete(phone);
    }
    //сохранение телефона
    public void save(Phones phone) {
        phoneRep.save(phone);
    }
}
