package org.msvdev.ee.config;

/**
 * Пациент
 */
public class Patient {

    private MedicalCard medicalCard;
    private VisitingDoctorTicket visitingDoctorTicket;

    public MedicalCard getMedicalCard() {
        return medicalCard;
    }

    public void go() {
        System.out.println(medicalCard.getName() + " отправился на приём в кабинет №" + visitingDoctorTicket.getCabinet());
    }

    public void setMedicalCard(MedicalCard medicalCard) {
        System.out.println(medicalCard.getName() + " получил медицинскую карту");
        this.medicalCard = medicalCard;
    }

    public VisitingDoctorTicket getVisitingDoctorTicket() {
        return visitingDoctorTicket;
    }

    public void setVisitingDoctorTicket(VisitingDoctorTicket visitingDoctorTicket) {
        System.out.println(medicalCard.getName() + " получил талон к " + visitingDoctorTicket.getDoctor());
        this.visitingDoctorTicket = visitingDoctorTicket;
    }
}
