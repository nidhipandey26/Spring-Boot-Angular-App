package com.java.assessment.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Supervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "FirstName cannot be  null")
    @Column(unique = true)
    @Pattern(regexp = "[a-zA-Z]")
    private String firstName;
    @NotNull
    @Pattern(message = "Email Invalid",regexp = "[a-zA-Z]")
    private String lastName;
    @Email(regexp = "[a-zA-Z][a-zA-Z0-9-.]*@[a-zA-Z0-\n" + "9]+([.][a-zA-Z]+)\n" + "+\"")
    @NotNull
    private String email;
    @NotNull
    @Pattern(regexp = "(0?[6-9][0-9]{9})")
    private String phoneNo;
    @NotNull
    private String supervisor;

    private String jurisdiction;

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }
    public String getJurisdiction() {
        return jurisdiction;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Supervisor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", supervisor='" + supervisor + '\'' +
                ", jurisdiction='" + jurisdiction + '\'' +
                '}';
    }


}
