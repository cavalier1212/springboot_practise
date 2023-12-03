package com.example.bean;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector{

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        boolean flag = importingClassMetadata.hasAnnotation("org.springframework.context.annotation.Import");
        if(flag){
            return new String[]{"com.example.bean.Dog"};
        }
        return new String[]{"com.example.bean.Cat"};
    }
}
