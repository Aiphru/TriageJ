import java.time.LocalDate;

public abstract class Visita {
    private LocalDate Data;
    private String medico;
    private String diagnosi;
    private String priorita;

    //constructor 
    public Visita(LocalDate Data, String priorita, String diagnosi, String medico) {
        this.Data = Data;
        this.priorita = priorita;
        this.diagnosi = diagnosi;
        this.medico = medico;
    }

    //getters
    public LocalDate getData() {
        return Data;
    }

    public String getMedico() {
        return medico;
    }

    public String getDiagnosi() {
        return diagnosi;
    }

    public String getPriorita() {
        return priorita;
    }
    
    public abstract String descriviVisita();

}
