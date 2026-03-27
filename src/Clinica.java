import java.util.HashMap;

public class Clinica {
    private static HashMap<String, Paziente> archivio = new HashMap<>();

    public static void registerPatient(String cf, Paziente paziente) {
        archivio.put(cf, paziente);
    }
    
    public static Paziente cercaPaziente(String cf) throws PazienteNonTrovatoException { 
        Paziente p = archivio.get(cf);
        if (p != null) return p;
        throw new PazienteNonTrovatoException("Il paziente non esiste");
    }

    public static HashMap<String, Paziente> getArchivio() {
        return archivio;
    }

    public static void setArchivio(HashMap<String, Paziente> nuovoArchivio) {
        archivio = nuovoArchivio;
    }

    public static void elencaPazientiPerColore(String colore) {
        boolean trovato = false;

        for (Paziente p : archivio.values()) {
            if (p.getTriageColor().equalsIgnoreCase(colore)) {
                System.out.println("--------------------------------------------");
                System.out.println("Name   : " + p.getName() + " " + p.getSurname());
                System.out.println("CF     : " + p.getId());
                System.out.println("Colore  : " + p.getTriageColor());
                trovato = true;
            }
        }

        if (!trovato) {
            System.out.println("[ERROR] Nessun paziente con questo colore: " + colore);
        }
    }
    
    public static void stampaStatistiche() {
        int totalPatients = archivio.size();
        int totalVisits = 0;
        int rosso = 0;
        int arancione = 0;
        int azzurro = 0;
        int verde = 0;
        int bianco = 0;
        for (Paziente p : archivio.values()) {
            totalVisits += p.getVisite().size();
            String colore = p.getTriageColor();
            if (colore.equalsIgnoreCase("Rosso")) {
                rosso++;
            } 
            else if (colore.equalsIgnoreCase("Arancione")) {
                arancione++;
            } 
            else if (colore.equalsIgnoreCase("Azzurro")) {
                azzurro++;
            } 
            else if (colore.equalsIgnoreCase("Verde")) {
                verde++;
            } 
            else if (colore.equalsIgnoreCase("Bianco")) {
                bianco++;
            }
        }
        double averageVisits = 0;
        if (totalPatients > 0) {
            averageVisits = (double) totalVisits / totalPatients;
        }
        System.out.println("============================================");
        System.out.println("                STATISTICS                  ");
        System.out.println("============================================");
        System.out.println("Pazienti totali          : " + totalPatients);
        System.out.println("Visite totali            : " + totalVisits);
        System.out.println("Visite on avg per paz.   : " + Math.round(averageVisits));
        System.out.println("--------------------------------------------");
        System.out.println("Rosso                    : " + rosso);
        System.out.println("Arancione                : " + arancione);
        System.out.println("Azzurro                  : " + azzurro);
        System.out.println("Verde                    : " + verde);
        System.out.println("Bianco                   : " + bianco);
        System.out.println("============================================");
    }
}