import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Paziente {
    private String id;
    private String name;
    private String surname;
    private String coloreTriage;
    private ArrayList<Visita> visits;
    private LocalDate registrationDate;
    
    //constructor
    public Paziente(String id, String name, String surname, String coloreTriage) throws CodiceFiscaleNonValidoException {
        if (!isValidCodiceFiscale(id)) {
            throw new CodiceFiscaleNonValidoException("Codice Fiscale non valido: " + id);
        }
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.coloreTriage = coloreTriage;
        this.registrationDate = LocalDate.now();
        this.visits = new ArrayList<>();
    }

    public Paziente(String id, String name, String surname, String coloreTriage, LocalDate registrationDate) throws CodiceFiscaleNonValidoException {
        if (!isValidCodiceFiscale(id)) {
            throw new CodiceFiscaleNonValidoException("Codice Fiscale non valido: " + id);
        }
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.coloreTriage = coloreTriage;
        this.registrationDate = registrationDate;
        this.visits = new ArrayList<>();
    }

    public static boolean isValidCodiceFiscale(String id) {
        if (id == null) return false;
        return Pattern.matches("^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$", id.toUpperCase());
    }

    public static void validateCodiceFiscale(String id) throws CodiceFiscaleNonValidoException {
        if (!isValidCodiceFiscale(id)) {
            throw new CodiceFiscaleNonValidoException("Codice Fiscale non valido: " + id);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws CodiceFiscaleNonValidoException {
        if (!isValidCodiceFiscale(id)) {
            throw new CodiceFiscaleNonValidoException("Codice Fiscale not valid: " + id);
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTriageColor() {
        return coloreTriage;
    }

    public void setColoreTriage(String coloreTriage) {
        this.coloreTriage = coloreTriage;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
    
    public ArrayList<Visita> getVisite() {
        return visits;
    }

    public void addVisit(Visita visita) {
        visits.add(visita);
    }

    public String calculatePriority() {
        if (visits.isEmpty()) {
            return "NOT VISITED";
        }

        Visita lastVisit = visits.get(visits.size() - 1);
        String lastPriority = lastVisit.getPriorita();
        String lastDiagnosis = lastVisit.getDiagnosi();

        if (lastPriority.equalsIgnoreCase("Rosso") && lastDiagnosis.equalsIgnoreCase("Cardiology")) {
            return "CODE 1 - EMERGENCY";
        } else if (lastPriority.equalsIgnoreCase("Rosso")) {
            return "CODE 2 - URGENT";
        } else if (lastPriority.equalsIgnoreCase("Azzurro")) {
            return "CODE 3 - PRIORITY";
        } else if (lastPriority.equalsIgnoreCase("Verde") || lastPriority.equalsIgnoreCase("Bianco")) {
            return "CODE 4 - STANDARD";
        } else {
            return "UNKNOWN PRIORITY";
        }
    }

    public void printCard() {
        System.out.println("============================================");
        System.out.println("               PATIENT RECORD               ");
        System.out.println("============================================");
        System.out.println("Name            : " + name + " " + surname);
        System.out.println("Codice Fiscale  : " + id);
        System.out.println("Triage Color    : " + coloreTriage);
        System.out.println("Registered      : " + registrationDate);
        System.out.println("--------------------------------------------");
        System.out.println("VISITS:");

        if (visits.isEmpty()) {
            System.out.println("No visits recorded.");
        } else {
            for (Visita v : visits) {
                System.out.println("- " + v.descriviVisita());
            }
        }

        System.out.println("--------------------------------------------");
        System.out.println("Calculated Priority: " + calculatePriority());
        System.out.println("============================================");
    }
}