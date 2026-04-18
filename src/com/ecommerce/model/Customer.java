package com.ecommerce.model;

import com.ecommerce.enums.Gender;
import com.ecommerce.enums.Membership;
import com.ecommerce.enums.Status;

import java.util.Objects;

public class Customer {
    private int id;
    private String name;
    private String email;
    private int age;
    private Gender gender;
    private Status status;
    private Membership membershipType;
    private Address resdentialAddress;
    private Address shippingAddress;

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public Customer setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Customer setAge(int age) {
        this.age = age;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public Customer setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Customer setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Membership getMembershipType() {
        return membershipType;
    }

    public Customer setMembershipType(Membership membershipType) {
        this.membershipType = membershipType;
        return this;
    }

    public Address getResdentialAddress() {
        return resdentialAddress;
    }

    public Customer setResdentialAddress(Address resdentialAddress) {
        this.resdentialAddress = resdentialAddress;
        return this;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public Customer setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && age == customer.age && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && gender == customer.gender && status == customer.status && membershipType == customer.membershipType && Objects.equals(resdentialAddress, customer.resdentialAddress) && Objects.equals(shippingAddress, customer.shippingAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age, gender, status, membershipType, resdentialAddress, shippingAddress);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", status=" + status +
                ", membershipType=" + membershipType +
                ", resdentialAddress=" + resdentialAddress +
                ", shippingAddress=" + shippingAddress +
                '}';
    }
}
