package org.msvdev.ee.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Медицинская карта пациента
 */
@Component("patientMedicalCard")
public class MedicalCard {

    @Value("Тимофей")
    private String name;

    @Value("05.06.1995")
    private String birthday;


    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }
}
