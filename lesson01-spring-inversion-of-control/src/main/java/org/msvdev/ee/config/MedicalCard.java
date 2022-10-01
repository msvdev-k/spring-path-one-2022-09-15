package org.msvdev.ee.config;

/**
 * Медицинская карта пациента
 */
public class MedicalCard {

    private String name;
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
