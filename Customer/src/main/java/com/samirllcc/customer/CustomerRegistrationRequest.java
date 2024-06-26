package com.samirllcc.customer;

public record CustomerRegistrationRequest   // we are using a record instead of a class because we get toString, immutability, equals etc for free
    (String firstName, String lastName, String email){

}
