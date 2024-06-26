package com.samy.fraud;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudController {
    private final FraudCheckService fraudCheckService;

//    public FraudController(FraudCheckService fraudCheckService) { // we can delete this because we have Lombok, we can add @AllArgsConstructor
//        this.fraudCheckService = fraudCheckService;
//    }

    @GetMapping(path= "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerID){
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerID);
        log.info("fraud check request for customer {}", customerID);
        return new FraudCheckResponse(isFraudulentCustomer);

    }
}
