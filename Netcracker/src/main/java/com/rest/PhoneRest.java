package com.rest;

import com.repository.PhoneRepository;
import com.repository.PhoneService.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Admin on 06.03.2019.
 */
@RestController
public class PhoneRest {
    @Autowired
    PhoneService phoneService;
    @Autowired
    PhoneRepository phoneRepository;

    /*
    //главный магазин запрашивает телефон
    @RequestMapping(value = "/phones", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.ALL_VALUE )
    public ResponseEntity<List<PhoneForRest>> getPhone(){
        Iterable<Phones> allPhones = phoneRepository.findAll();
        List<PhoneForRest> phonesForRest = new ArrayList<>();
        for (Phones e:allPhones) {
            PhoneForRest eForRest = new PhoneForRest(e);
            phonesForRest.add(eForRest);
        }
        return new ResponseEntity<List<PhoneForRest>>(phonesForRest, HttpStatus.OK);
    }
*/
}
