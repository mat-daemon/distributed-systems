package org.example;

public class PersonLinks {
    private Link self;
    private Link listAll;
    private Link add;
    private Link update;
    private Link delete;
    private Link size;

    public PersonLinks() {
    }

    public PersonLinks(Link self, Link listAll, Link add, Link update, Link delete, Link size) {
        this.self = self;
        this.listAll = listAll;
        this.add = add;
        this.update = update;
        this.delete = delete;
        this.size = size;
    }

    public Link getSelf() {
        return self;
    }

    public void setSelf(Link self) {
        this.self = self;
    }

    public Link getListAll() {
        return listAll;
    }

    public void setListAll(Link listAll) {
        this.listAll = listAll;
    }

    public Link getAdd() {
        return add;
    }

    public void setAdd(Link add) {
        this.add = add;
    }

    public Link getUpdate() {
        return update;
    }

    public void setUpdate(Link update) {
        this.update = update;
    }

    public Link getDelete() {
        return delete;
    }

    public void setDelete(Link delete) {
        this.delete = delete;
    }

    public Link getSize() {
        return size;
    }

    public void setSize(Link size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PersonLinks{" +
                "self=" + self +
                ", listAll=" + listAll +
                ", add=" + add +
                ", update=" + update +
                ", delete=" + delete +
                ", size=" + size +
                '}';
    }
}

