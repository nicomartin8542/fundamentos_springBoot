package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyBeanWithPropertyImplement implements MyBeanWithProperty{

    private String name;
    private String apellido;

    public MyBeanWithPropertyImplement(String name, String apellido) {
        this.name = name;
        this.apellido = apellido;
    }

    @Override
    public String function() {
        return name + "-" + apellido;
    }
}
