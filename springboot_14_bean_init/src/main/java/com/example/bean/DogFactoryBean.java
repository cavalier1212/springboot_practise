package com.example.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

public class DogFactoryBean implements FactoryBean<Dog> {

    @Override
    @Nullable
    public Dog getObject() throws Exception {
       Dog dog = new Dog();
       // 此處可加強
       return dog;
    }

    @Override
    @Nullable
    public Class<?> getObjectType() {
        return Dog.class;
    }

}
