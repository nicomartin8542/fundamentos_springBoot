package com.fundamentosplatzi.springboot.fundamentos.bean;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);

    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency(int num) {

        try {
            LOGGER.info("Hemos ingresado al metodo printwithDependency");
            int numero = num ;
            LOGGER.debug("El numero enviado como parametro es: " +numero );
            System.out.println(myOperation.suma(numero));
            System.out.println("Hola desde mi depenencia inyectada");

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
