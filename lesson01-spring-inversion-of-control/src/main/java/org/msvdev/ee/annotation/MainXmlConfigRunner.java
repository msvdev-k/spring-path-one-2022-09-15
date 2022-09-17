package org.msvdev.ee.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MainXmlConfigRunner {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config-annotation.xml");

        Patient patient = context.getBean("patient", Patient.class);
        patient.go();
    }

}
