package suhael.service.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {

    private String firstName  = "suhael";
    private String lastName  = "suhael";

    public Person(){}

    public Person(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
