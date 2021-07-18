package com.jiajun.sqlite_example;

public class CustomerModel {
    private int id;
    private String name;
    private int age;
    private boolean isPremium;

    public CustomerModel(int id, String name, int age, boolean isPremium) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isPremium = isPremium;
    }

    @Override
    public String toString() {
        return "Customer Info: " + "\n" +
                "id: " + id + "\n" +
                "Name: " + name + "\n" +
                "age: " + age + "\n" +
                "is Premium: " + isPremium;
    }

    public CustomerModel() {
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

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }
}
