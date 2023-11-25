package com.example.newMock.Controller;

import com.example.newMock.Model.RequestDTO;
import com.example.newMock.Model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_UP;


@RestController
public class MainController {
    private Logger log = LoggerFactory.getLogger(MainController.class);
    ObjectMapper mapper = new ObjectMapper();

    @PostMapping(
            value = "/info/postBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )

    public Object postBalances(@RequestBody RequestDTO requestDTO){

        /*String response2 = "{\n" +
                "\t\"rqUID\": \"1337tugrique\",\n" +
                "\t\"clientId\": \"133713371337\",\n" +
                "\t\"account\": \"133713371337\",\n" +
                "\t\"openDate\": \"2033-01-01\",\n" +
                "\t\"closeDate\": \"2077-01-01\"\n" +
                "\t}\n" +
                "\t";*/

        try{
            String clientId = requestDTO.getClientId();
            char firstDigit = clientId.charAt(0);
            BigDecimal maxLimit, balance;
            String currency;

            if (firstDigit == '8'){
                maxLimit = new BigDecimal(2000);
                currency = "US";
                balance = BigDecimal.valueOf(Math.random()* maxLimit.doubleValue());

            } else if (firstDigit == '9') {
                maxLimit = new BigDecimal(1000);
                currency = "EU";
                balance = BigDecimal.valueOf(Math.random()* maxLimit.doubleValue());


            }
            else {
                maxLimit = new BigDecimal(50000);
                currency = "RU";
                balance = BigDecimal.valueOf(Math.random()* maxLimit.doubleValue());

            }

            ResponseDTO responseDTO = new ResponseDTO();

            //String rqUID = requestDTO.getRqUID();
            responseDTO.setRqUID(requestDTO.getRqUID());
            responseDTO.setClientId(clientId);
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setCurrency(currency);
            responseDTO.setBalance(balance.setScale(2, ROUND_UP));
            responseDTO.setMaxLimit(maxLimit);
            log.error("*********** RequestDTO ***********" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.error("*********** ResponseDTO ***********" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));

            //return response2;
            return responseDTO;
        } catch (Exception e){return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());}
    }
}
