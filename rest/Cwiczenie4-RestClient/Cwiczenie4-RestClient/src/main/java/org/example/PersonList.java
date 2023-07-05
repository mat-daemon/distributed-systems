package org.example;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonList {

    @JsonProperty("_embedded")
    private Embedded embedded;

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Embedded {
        @JsonProperty("personList")
        private List<PersonReceive> personList;

        public Embedded(List<PersonReceive> personList) {
            this.personList = personList;
        }

        public Embedded() {
        }

        public List<PersonReceive> getPersonList() {
            return personList;
        }

        public void setPersonList(List<PersonReceive> personList) {
            this.personList = personList;
        }

        @Override
        public String toString() {
            return " " + personList;
        }
    }

    @Override
    public String toString() {
        return "PersonList{" +
                "personList=" + embedded +
                '}';
    }
}