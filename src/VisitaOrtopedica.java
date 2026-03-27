import java.time.LocalDate;

public class VisitaOrtopedica extends Visita{
    private String parteCorpo; 

    //constructor
    public VisitaOrtopedica(String priority, String diagnosis, String doctor, String parteCorpo){
        super(LocalDate.now(), priority, diagnosis, doctor);
        this.parteCorpo = parteCorpo;
    }

    //for loading visits from file with original saved date
    public VisitaOrtopedica(LocalDate date, String priority, String diagnosis, String doctor, String parteCorpo) {
        super(date, priority, diagnosis, doctor);
        this.parteCorpo = parteCorpo;
    }

    //getters
    public String getParteCorpo() {
        return parteCorpo;
    }

    @Override
    public String descriviVisita(){
        return "Visita Ortopedica - Date: " + getData() +
            ", Doctor: " + getMedico() +
            ", Diagnosis: " + getDiagnosi() +
            ", Priority: " + getPriorita() +
            ", Body Part: " + parteCorpo;
    }
}
