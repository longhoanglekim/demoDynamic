package com.example.tranings.hlklong.jdbcbasics.models;

public class Account {
    private int accno;
    private String firstname;
    private String lastname;
    private int bal;

    public Account(int accno, String firstname, String lastname, int bal) {
        this.accno = accno;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bal = bal;
    }

    public int getAccno() {
        return accno;
    }

    public void setAccno(int accno) {
        this.accno = accno;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getBal() {
        return bal;
    }

    public void setBal(int bal) {
        this.bal = bal;
    }
}
