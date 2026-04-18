package com.ecommerce.model;

import java.util.Objects;

public class Address {
    private String houseNo;
    private String street;
    private String area;
    private String city;
    private int pincode;

    public Address() {
    }

    public String getHouseNo() {
        return houseNo;
    }

    public Address setHouseNo(String houseNo) {
        this.houseNo = houseNo;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Address setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getArea() {
        return area;
    }

    public Address setArea(String area) {
        this.area = area;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public int getPincode() {
        return pincode;
    }

    public Address setPincode(int pincode) {
        this.pincode = pincode;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return pincode == address.pincode && Objects.equals(houseNo, address.houseNo) && Objects.equals(street, address.street) && Objects.equals(area, address.area) && Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseNo, street, area, city, pincode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "houseNo='" + houseNo + '\'' +
                ", street='" + street + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", pincode=" + pincode +
                '}';
    }
}
