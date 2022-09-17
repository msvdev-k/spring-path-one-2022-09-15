package org.msvdev.ee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    @Bean(name = "visitingDoctorTicket")
    public VisitingDoctorTicket visitingDoctorTicket() {
        VisitingDoctorTicket visitingDoctorTicket = new VisitingDoctorTicket();
        visitingDoctorTicket.setDoctor("стоматолог");
        visitingDoctorTicket.setCabinet("123б");
        return visitingDoctorTicket;
    }


    @Bean(name = "medicalCard")
    public MedicalCard medicalCard() {
        MedicalCard medicalCard = new MedicalCard();
        medicalCard.setName("Степан");
        medicalCard.setBirthday("25.03.2002");
        return medicalCard;
    }


    @Bean(name = "medicalCardArchive")
    public MedicalCardArchive medicalCardArchive(MedicalCard medicalCard) {
        MedicalCardArchive medicalCardArchive = new MedicalCardArchive();
        medicalCardArchive.setPatientCard(medicalCard);
        return medicalCardArchive;
    }


    @Bean(name = "patient")
    public Patient patient(MedicalCard medicalCard, VisitingDoctorTicket visitingDoctorTicket) {
        Patient patient = new Patient();
        patient.setMedicalCard(medicalCard);
        patient.setVisitingDoctorTicket(visitingDoctorTicket);
        return patient;
    }

}
