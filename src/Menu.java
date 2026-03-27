public class Menu {
    private static final String MENU = """
            GESTIONE ARCHIVIO PAZIENTI - (ANDREA-NABIL)
            ============================================
            [1] Registra nuovo paziente
            [2] Cerca paziente per CF
            [3] Aggiungi visita a paziente
            [4] Stampa scheda completa
            [5] Elenca pazienti per codice colore
            [6] Statistiche generali
            [7] Salva archivio su file
            [8] Carica archivio da file
            [9] Esci
            ============================================
            Scelta: """;

    private static final String TRIAGE_MENU = """
            CODICI TRIAGE
            ============================================
            [1] \u001B[31mRosso\u001B[0m      - [Pericolo di vita immediato]
            [2] \u001B[33mArancione\u001B[0m  - [Patologie gravi a rischio evolutivo]
            [3] \u001B[36mAzzurro\u001B[0m    - [Condizioni stabili]
            [4] \u001B[32mVerde\u001B[0m      - [Patologie di modesta entità]
            [5] \u001B[37mBianco\u001B[0m     - [Problemi non urgenti]
            ============================================
            Scelta: """;

    private static final String VISIT_MENU = """
            SELEZIONA VISITA
            ============================================
            [1] Visita Generica
            [2] Visita Ortopedica
            [3] Visita Cardiologica
            ============================================
            Scelta: """;
            
    public static void stampaMenu() {System.out.print(MENU);}
    public static void stampaMenuTriage(){System.out.print(TRIAGE_MENU);}
    public static void stampaMenuVisita(){ System.out.print(VISIT_MENU);}
}