import java.time.LocalDate;

public class VisitaOrtopedica extends Visita{
    private String parteCorpo; 

    //constructor
    public VisitaOrtopedica(String priorita, String diagnosi, String medico, String parteCorpo){
        super(LocalDate.now(), priorita, diagnosi, medico); // Data impostata automaticamente alla creazione
        this.parteCorpo = parteCorpo;
    }

    // Usato per ricostruire la visita da file mantenendo la data original
    public VisitaOrtopedica(LocalDate data, String priorita, String diagnosi, String medico, String parteCorpo) {
        super(data, priorita, diagnosi, medico);
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
