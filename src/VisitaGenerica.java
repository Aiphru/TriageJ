import java.time.LocalDate;

public class VisitaGenerica extends Visita {
    private String reparto; 

    //constructor
    public VisitaGenerica(String priorita, String diagnosi, String medico, String reparto) {
        super(LocalDate.now(), priorita, diagnosi, medico); // Data impostata automaticamente alla creazione
        this.reparto = reparto;
    }

    // Usato per ricostruire la visita da file mantenendo la data originale
    public VisitaGenerica(LocalDate data, String priorita, String diagnosi, String medico, String reparto) {
        super(data, priorita, diagnosi, medico);
        this.reparto = reparto;
    }

    //getters
    public String getReparto() {
        return reparto;
    }

    @Override
    public String descriviVisita() {
        return "Visita Generica - Data:" + getData() +
                ", Medico:" + getMedico() +
                ", Diagnosi: " + getDiagnosi() +
                ", Priorità: " + getPriorita() +
                ", Reparto: " + reparto;
    }

}
