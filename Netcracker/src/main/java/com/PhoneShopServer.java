package com; /**
 * Created by Admin on 28.11.2018.
 */

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor

public class PhoneShopServer {
       public static void main(String [] args){
           SpringApplication.run(PhoneShopServer.class, args);
    }
}