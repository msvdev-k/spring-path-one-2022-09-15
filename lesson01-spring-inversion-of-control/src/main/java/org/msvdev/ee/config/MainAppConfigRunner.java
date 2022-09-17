package org.msvdev.ee.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainAppConfigRunner {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Patient patient = context.getBean("patient", Patient.class);
        patient.go();
    }

}
