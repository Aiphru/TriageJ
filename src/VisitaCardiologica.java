import java.time.LocalDate;

public class VisitaCardiologica extends Visita {
    private int frequenzaCardiaca;
    private int pressioneSist;
    private int pressioneDiast;

    //constructor
    public VisitaCardiologica(String priorita, String diagnosi, String medico,int frequenzaCardiaca , int pressioneSist, int pressioneDiast){
        super(LocalDate.now(), priorita, diagnosi, medico);        // Data impostata automaticamente alla creazione
        this.frequenzaCardiaca = frequenzaCardiaca;
        this.pressioneSist = pressioneSist;
        this.pressioneDiast = pressioneDiast;
    }

    // Usato per ricostruire la visita da file mantenendo la data original
    public VisitaCardiologica(LocalDate data, String priorita, String diagnosi, String medico, int frequenzaCardiaca, int pressioneSist, int pressioneDiast){
        super(data, priorita, diagnosi, medico);
        this.frequenzaCardiaca = frequenzaCardiaca;
        this.pressioneSist = pressioneSist;
        this.pressioneDiast = pressioneDiast;
    }

    //getters
    public int getFrequenzaCardiaca() {
        return frequenzaCardiaca;
    }
    public int getPressioneDiast() {
        return pressioneDiast;
    }

    public int getPressioneSist() {
        return pressioneSist;
    }

    @Override 
    public String descriviVisita(){
        return "Visita Cardiologica - Date:" + getData() +
            ", Dottore:" + getMedico() +
            ", Diagnosi: " + getDiagnosi() +
            ", Priorità: " + getPriorita() +
            ", Battito Cardiaco: " + frequenzaCardiaca +
            ", Pressione sist: " + pressioneSist +
            ", Pressione dist: " + pressioneDiast;
    }

}
