import java.time.LocalDate;

public class VisitaCardiologica extends Visita {
    private int frequenzaCardiaca;
    private int pressioneSist;
    private int pressioneDiast;

    //constructor
    public VisitaCardiologica(String priorita, String diagnosi, String medico,int frequenzaCardiaca , int pressioneSist, int pressioneDiast){
        super(LocalDate.now(), priorita, diagnosi, medico);        
        this.frequenzaCardiaca = frequenzaCardiaca;
        this.pressioneSist = pressioneSist;
        this.pressioneDiast = pressioneDiast;
    }

    //for loading visits from file with original saved date
    public VisitaCardiologica(LocalDate date, String priority, String diagnosis, String doctor, int frequenzaCardiaca, int pressioneSist, int pressioneDiast){
        super(date, priority, diagnosis, doctor);
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
