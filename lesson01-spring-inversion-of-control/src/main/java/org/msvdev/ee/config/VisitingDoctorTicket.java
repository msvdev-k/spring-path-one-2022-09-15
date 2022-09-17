package org.msvdev.ee.config;

/**
 * Талон на посещение врача
 */
public class VisitingDoctorTicket {

    private String doctor;
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
