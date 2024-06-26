package com.samirllcc.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate; // inject the restTemplate here that we created in CustomerConfig
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email (request.email())
                .build();

        //1-check if email valid
        //2- check if email not taken
        //3- store customer in db
        customerRepository.saveAndFlush(customer);
        //4- check if fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD:8081/api/v1/fraud-check/{customerId}", // FRAUD was localhost but we are going to let eureka handles that part we only need to care about the second part of the url
                FraudCheckResponse.class,
                customer.getId()
        );

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster customer");
        }
        //5- send notification
      // 3-
    }
}
