package org.example.com;

import com.google.gson.Gson;

import java.time.ZonedDateTime;

public class PersonData {

    private String Name;
    private String Time;
    private int Nr;

    public PersonData() {
    }

    public PersonData(String name, String time, int nr) {
        Name = name;
        Time = time;
        Nr = nr;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getNr() {
        return Nr;
    }

    public void setNr(int nr) {
        Nr = nr;
    }

    public String serializeToJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static PersonData deserializeFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, PersonData.class);
    }


    @Override
    public String toString() {
        return "PersonData{" +
                "Name='" + Name + '\'' +
                ", Nr=" + Nr +
                ", Time=" + Time +
                '}';
    }
}
