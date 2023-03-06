package com.company.bcpayments.service;

import com.company.bcpayments.repository.EthereumManagement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@Primary
@Slf4j
public class AdminService {
    private final EthereumManagement bck;

    public AdminService(EthereumManagement bck) {
        this.bck = bck;
    }

    public String name(){
        String name;

        try{
            name = bck.getName();

            return name;

        } catch (Exception e){

            return "Error getting the token name";

        }
    }

    public String totalTokens(){


        //Comprueba los permisos


        String total;

        try{
            //Recibo y el resultado y lo envuelvo en la respuesta
            total = bck.getTotalTokens();

            return total;

        } catch (NullPointerException e){

            return "Error getting the total tokens";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public String sendTokens(){
        //TODO
        return "123";
    }


}
