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
    
    //Costruttore
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

    public static boolean isValidCodiceFiscale(String id) throws CodiceFiscaleNonValidoException {
        if (id == null ) {
            throw new CodiceFiscaleNonValidoException("Codice fiscale non valido.");
        }  //Controlla se il formato del codice Fiscale è valido
        if (!Pattern.matches("^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$", id.toUpperCase())){
            throw new CodiceFiscaleNonValidoException(("Formato codice fiscale non valido"));
        } //Controlla se l'ultima l'ultima lettera del codice fiscale è valida.
        char expectedLastLetter = calculateLastLetterCF(id);
        char actualLastLetter = id.charAt(id.length()-1);

        if (actualLastLetter == expectedLastLetter) return true;
        throw new CodiceFiscaleNonValidoException("CF: Carettere di controllo finale non valido");
    }
    /*
        calculateLastLetterCF calcola il carattere di controllo del codice Fiscale.
        1. Divide i primi 15 caratteri e calcola il loro valore utilizzando una tabella predefinita.
        2. Il valore complessivo viene salvato all'interno di sum
        3. Calcola il modulo % 26 .  
        Aggiunge il resto ad 'A' che occupa il posto 65 nella tabella Ascii, quindi con un resto di 3,
        Avremo il valore 68 che dopo essere castato a char sarà, per esempio, D.
     */
    public static char calculateLastLetterCF(String cf){
        cf = cf.toUpperCase();
        int sum = 0;
        for (int i=0;i<cf.length()-1;i++){ //Il calcolo viene fatto sulle prime 15 lettere.
            if (isOdd(i+1)){ //Il calcolo pari-dispari inizia da 1 non 0
                sum = sum + getOddValue(cf.charAt(i));
            } else {
                sum = sum + getEvenValue(cf.charAt(i));
            }
        }
        int resto = sum % 26;
        return (char) ('A' + resto);
    }

    public static boolean isOdd(int n){
        if (n%2==0) return false;
        return true;
    }

    public static int getOddValue(char letter){
        return switch (letter){
            case '0', 'A' -> 1;
            case '1', 'B' -> 0;
            case '2', 'C' -> 5;
            case '3', 'D' -> 7;
            case '4', 'E' -> 9;
            case '5', 'F' -> 13;
            case '6', 'G' -> 15;
            case '7', 'H' -> 17;
            case '8', 'I' -> 19;
            case '9', 'J' -> 21;
            case 'K' -> 2;
            case 'L' -> 4;
            case 'M' -> 18;
            case 'N' -> 20;
            case 'O' -> 11;
            case 'P' -> 3;
            case 'Q' -> 6;
            case 'R' -> 8;
            case 'S' -> 12;
            case 'T' -> 14;
            case 'U' -> 16;
            case 'V' -> 10;
            case 'W' -> 22;
            case 'X' -> 25;
            case 'Y' -> 24;
            case 'Z' -> 23;
            default -> 0;
        };
    }

    public static int getEvenValue(char letter){
          return switch (letter){
            case '0', 'A' -> 0;
            case '1', 'B' -> 1;
            case '2', 'C' -> 2;
            case '3', 'D' -> 3;
            case '4', 'E' -> 4;
            case '5', 'F' -> 5;
            case '6', 'G' -> 6;
            case '7', 'H' -> 7;
            case '8', 'I' -> 8;
            case '9', 'J' -> 9;
            case 'K' -> 10;
            case 'L' -> 11;
            case 'M' -> 12;
            case 'N' -> 13;
            case 'O' -> 14;
            case 'P' -> 15;
            case 'Q' -> 16;
            case 'R' -> 17;
            case 'S' -> 18;
            case 'T' -> 19;
            case 'U' -> 20;
            case 'V' -> 21;
            case 'W' -> 22;
            case 'X' -> 23;
            case 'Y' -> 24;
            case 'Z' -> 25;
            default -> 0;
        };
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
        System.out.println("              RECORD PAZIENTE               ");
        System.out.println("============================================");
        System.out.println("Nome            : " + name + " " + surname);
        System.out.println("Codice Fiscale  : " + id);
        System.out.println("Colore Triage   : " + coloreTriage);
        System.out.println("Registrato      : " + registrationDate);
        System.out.println("--------------------------------------------");
        System.out.println("VISITE:");

        if (visits.isEmpty()) {
            System.out.println("No visits recorded.");
        } else {
            for (Visita v : visits) {
                System.out.println("- " + v.descriviVisita());
            }
        }

        System.out.println("--------------------------------------------");
        System.out.println("Priorità        : " + calculatePriority());
        System.out.println("============================================");
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


}