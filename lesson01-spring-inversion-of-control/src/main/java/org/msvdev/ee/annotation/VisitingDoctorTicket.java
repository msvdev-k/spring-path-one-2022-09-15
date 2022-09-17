package org.msvdev.ee.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Талон на посещение врача
 */
@Component("visitingDoctorTicket")
public class VisitingDoctorTicket {

    @Value("окулист")
    private String doctor;

    @Value("12")
    private String cabinet;

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }
}
