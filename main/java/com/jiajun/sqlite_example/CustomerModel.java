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
        if (id == -1){
            return "Customer Info: " + "\n" +
                    "Name: " + name + "\n" +
                    "age: " + age + "\n" +
                    "is Premium: " + isPremium;
        }else{
            return "Customer Info: " + "\n" +
                "id: " + id + "\n" +
                "Name: " + name + "\n" +
                "age: " + age + "\n" +
                "is Premium: " + isPremium;
        }
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isPremium() {
        return isPremium;
    }

}
