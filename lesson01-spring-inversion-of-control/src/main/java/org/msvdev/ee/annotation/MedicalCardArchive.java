package org.msvdev.ee.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Архив медицинских карт
 */
@Component("medicalCardArchive")
public class MedicalCardArchive {

    private MedicalCard patientCard;


    @Autowired
    public void setPatientCard(MedicalCard patientCard) {
        this.patientCard = patientCard;
        System.out.println("Карта пациента " + patientCard.getName() + " занесена в архив");
    }


    public MedicalCard getPatientCard() {
        return patientCard;
    }
}
