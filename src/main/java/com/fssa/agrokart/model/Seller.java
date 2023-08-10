package com.fssa.agrokart.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A class which holds the structure of the seller details
 * @author HemanathMuralikrishnan
 */

public class Seller {
    private int id;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private String emailId;
    private String mobileNumber;
    private Address address;
    private String businessName;
    private String passWord;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String accountStatus;
    private String accountType;
}
