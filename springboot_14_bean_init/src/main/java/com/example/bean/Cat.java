package com.example.bean;

import org.springframework.stereotype.Service;

@Service("Tom")
public class Cat {
    public Cat(){}

    int age;

    public Cat(int age){
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat [age=" + age + "]";
    }

    
}
