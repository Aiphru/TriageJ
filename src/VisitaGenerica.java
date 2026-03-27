import java.time.LocalDate;

public class VisitaGenerica extends Visita {
    private String reparto; 

    //constructor
    public VisitaGenerica(String priority, String diagnosis, String doctor, String reparto) {
        super(LocalDate.now(), priority, diagnosis, doctor);
        this.reparto = reparto;
    }

    //for loading visits from file with original saved date
    public VisitaGenerica(LocalDate date, String priority, String diagnosis, String doctor, String reparto) {
        super(date, priority, diagnosis, doctor);
        this.reparto = reparto;
    }

    //getters
    public String getReparto() {
        return reparto;
    }

    @Override
    public String descriviVisita() {
        return "Visita Generica - Date:" + getData() +
                ", Doctor:" + getMedico() +
                ", Diagnosis: " + getDiagnosi() +
                ", Priority: " + getPriorita() +
                ", Department: " + reparto;
    }

}
