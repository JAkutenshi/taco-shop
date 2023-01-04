package com.github.jakutenshi.tacoshop;

import lombok.Data;

@Data
public class Order {
    //ToDo: Split on Name | Address | CreditCardInfo
    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
}
