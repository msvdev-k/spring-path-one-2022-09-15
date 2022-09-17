package org.msvdev.ee.config;

/**
 * Архив медицинских карт
 */
public class MedicalCardArchive {

    private MedicalCard patientCard;


    public void setPatientCard(MedicalCard patientCard) {
        this.patientCard = patientCard;
        System.out.println("Карта пациента " + patientCard.getName() + " занесена в архив");
    }


    public MedicalCard getPatientCard() {
        return patientCard;
    }
}
