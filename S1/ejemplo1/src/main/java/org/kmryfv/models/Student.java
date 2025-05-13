package org.kmryfv.models;

public class Student {
    private String cif;
    private String name;
    private String surname;
    private String email;
    private String major;

    public Student(){

    }

    public Student(String cif, String name, String surname, String email, String major){
        this.cif = cif;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.major = major;
    }

    public void setCif(String cif){
        this.cif = cif;
    }

    public String getCif(){
        return cif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student{" +
                "cif='" + cif + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
