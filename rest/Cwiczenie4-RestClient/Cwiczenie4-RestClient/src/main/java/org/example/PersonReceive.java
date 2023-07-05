package org.example;

import java.util.Map;

public class PersonReceive {
    private int id;
    private String name;
    private int age;
    private String email;
    private PersonLinks  _links;

    public PersonReceive() {
    }

    public PersonReceive(int id, String name, int age, String email, PersonLinks links) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this._links = links;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonLinks get_links() {
        return _links;
    }

    public void set_links(PersonLinks _links) {
        this._links = _links;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", links=" + _links +
                '}';
    }
}