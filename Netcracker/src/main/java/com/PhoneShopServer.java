package com; /**
 * Created by Admin on 28.11.2018.
 */

import com.configs.MVCConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MVCConfig.class)
public class PhoneShopServer  {
       public static void main(String [] args){
           SpringApplication.run(PhoneShopServer.class, args);
    }
}