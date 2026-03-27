import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static void println(Object msg) {
        System.out.println(msg);
    }

    static void print(Object msg) {
        System.out.print(msg);
    }

    static void printLine() {
        System.out.println("============================================");
    }

    static void printSection(String title) {
        printLine();
        System.out.println(title);
        printLine();
    }

    static void printSuccess(String message) {
        System.out.println("[OK] " + message);
    }

    static void printError(String message) {
        System.out.println("[ERROR] " + message);
    }

    static void registraPaziente(Scanner sc) throws CodiceFiscaleNonValidoException,PazienteGiaEsistenteException {
        printSection("REGISTRA NUOVO PAZIENTE");

        String cf = "";
        boolean validCF = false;

        do {
            println("CF: ");
            cf = sc.nextLine();
            try {
                Paziente.validateCodiceFiscale(cf);
                Clinica.verificaEsistenzaCF(cf);
                validCF = true;
            } catch (CodiceFiscaleNonValidoException e) {
                printError("Codice fiscale non valido.");
            } catch (PazienteGiaEsistenteException e){
                printError("Paziente già esistente.");
                println("Vuoi riprovare (Y/N)?");
                String choice = sc.nextLine();
                if (choice.toLowerCase().equals("n")){
                    return;
                }

            }
        } while (!validCF);

        println("Nome: ");
        String nome = sc.nextLine();

        println("Cognome: ");
        String cognome = sc.nextLine();

        String colore = "";
        int scelta;

        do {
            Menu.stampaMenuTriage();
            scelta = sc.nextInt();

            switch (scelta) {
                case 1 -> colore = "Rosso";
                case 2 -> colore = "Arancione";
                case 3 -> colore = "Azzurro";
                case 4 -> colore = "Verde";
                case 5 -> colore = "Bianco";
            }
        } while (scelta < 1 || scelta > 5);

        Clinica.registerPatient(cf, new Paziente(cf, nome, cognome, colore));
        printSuccess("Paziente registrato correttamente.");
        printLine();
    }

    static void cercaPazientePerCF(Scanner sc) {
        printSection("CERCA PAZIENTE PER CF");

        print("Inserisci CF del paziente: ");
        String cfDaCercare = sc.nextLine();

        try {
            Paziente p = Clinica.cercaPaziente(cfDaCercare);
            printSuccess("Paziente trovato.");
            System.out.println("Name: " + p.getName() + " " + p.getSurname());
            System.out.println("Triage: " + p.getTriageColor());
            printLine();
        } catch (PazienteNonTrovatoException e) {
            printError(e.getMessage());
        }
    }

    static void aggiungiVisitaGenerica(Scanner sc, Paziente p) {
        print("\nMedico: ");
        String medico = sc.nextLine();

        print("\nPriorita: ");
        String priorita = sc.nextLine();

        print("\nDiagnosi: ");
        String diagnosi = sc.nextLine();

        print("\nReparto: ");
        String reparto = sc.nextLine();

        VisitaGenerica visita = new VisitaGenerica(priorita, diagnosi, medico, reparto);
        p.addVisit(visita);

        printSuccess("Visita generica aggiunta correttamente.");
    }

    static void aggiungiVistaOrtopedica(Scanner sc, Paziente p) {
        print("\nPriorita: ");
        String priorita = sc.nextLine();

        print("\nDiagnosi: ");
        String diagnosi = sc.nextLine();

        print("\nMedico: ");
        String medico = sc.nextLine();

        print("\nParte del corpo: ");
        String parteCorpo = sc.nextLine();

        VisitaOrtopedica visita = new VisitaOrtopedica(priorita, diagnosi, medico, parteCorpo);
        p.addVisit(visita);

        printSuccess("Visita ortopedica aggiunta correttamente.");
    }

    static void aggiungiVisitaCardiologica(Scanner sc, Paziente p) {
        print("\nPriorita: ");
        String priorita = sc.nextLine();

        print("\nDiagnosi: ");
        String diagnosi = sc.nextLine();

        print("\nMedico: ");
        String medico = sc.nextLine();

        print("\nFrequenza cardiaca: ");
        int frequenzaCardiaca = sc.nextInt();
        sc.nextLine();

        print("\nPressione sistolica: ");
        int pressioneSist = sc.nextInt();
        sc.nextLine();

        print("\nPressione diastolica: ");
        int pressioneDiast = sc.nextInt();
        sc.nextLine();

        VisitaCardiologica visita = new VisitaCardiologica(
            priorita,
            diagnosi,
            medico,
            frequenzaCardiaca,
            pressioneSist,
            pressioneDiast
        );

        p.addVisit(visita);
        printSuccess("Visita cardiologica aggiunta correttamente.");
    }

    static void aggiungiVisita(Scanner sc) {
        printSection("AGGIUNGI VISITA");

        try {
            print("Inserisci CF del paziente: ");
            String cfDaCercare = sc.nextLine();

            Paziente p = Clinica.cercaPaziente(cfDaCercare);

            int scelta;
            do {
                Menu.stampaMenuVisita();
                scelta = sc.nextInt();
                sc.nextLine();

                switch (scelta) {
                    case 1 -> aggiungiVisitaGenerica(sc, p);
                    case 2 -> aggiungiVistaOrtopedica(sc, p);
                    case 3 -> aggiungiVisitaCardiologica(sc, p);
                }
            } while (scelta < 1 || scelta > 3);

            printLine();

        } catch (PazienteNonTrovatoException e) {
            printError(e.getMessage());
        }
    }

    static void stampaSchedaCompleta(Scanner sc) {
        printSection("STAMPA SCHEDA COMPLETA");

        try {
            print("Inserisci CF del paziente: ");
            String cf = sc.nextLine();

            Paziente p = Clinica.cercaPaziente(cf);
            p.printCard();

        } catch (PazienteNonTrovatoException e) {
            printError(e.getMessage());
        }
    }

    static void elencaPazientiPerColore(Scanner sc) {
        printSection("ELENCA PAZIENTI PER COLORE");

        int scelta;
        String colore = "";

        Menu.stampaMenuTriage();
        do {
            scelta = sc.nextInt();
            sc.nextLine();

            switch (scelta) {
                case 1 -> colore = "Rosso";
                case 2 -> colore = "Arancione";
                case 3 -> colore = "Azzurro";
                case 4 -> colore = "Verde";
                case 5 -> colore = "Bianco";
            }
        } while (scelta < 1 || scelta > 5);

        Clinica.elencaPazientiPerColore(colore);
        printLine();
    }

    static void stampaStatistiche(Scanner sc) {
        printSection("STATISTICHE GENERALI");
        Clinica.stampaStatistiche();
    }

    static void salvaArchivio(Scanner sc) {
        printSection("SALVATAGGIO ARCHIVIO");

        GestoreArchivio gestore = new GestoreArchivio();
        HashMap<String, Paziente> archive = Clinica.getArchivio();
        gestore.saveArchive(archive);

        printSuccess("Archivio salvato con successo.");
        printLine();
    }

    static void caricaArchivio(Scanner sc) {
        printSection("CARICAMENTO ARCHIVIO");

        GestoreArchivio gestore = new GestoreArchivio();
        HashMap<String, Paziente> archive = gestore.loadArchive();
        Clinica.setArchivio(archive);

        printSuccess("Archivio caricato correttamente.");
        printLine();
    }

    static boolean running = true;
    static byte choice = 0;
    static boolean isFirstStart = true;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        while (running) {
            if (isFirstStart){
                caricaArchivio(sc); //Carichiamo l'archivio all'avvio per impedire di inserire pazienti già esistenti.
            }
            isFirstStart = false;
            Menu.stampaMenu();
            choice = sc.nextByte();
            sc.nextLine();
            switch (choice) {
                case 1 -> registraPaziente(sc);
                case 2 -> cercaPazientePerCF(sc);
                case 3 -> aggiungiVisita(sc);
                case 4 -> stampaSchedaCompleta(sc);
                case 5 -> elencaPazientiPerColore(sc);
                case 6 -> stampaStatistiche(sc);
                case 7 -> salvaArchivio(sc);
                case 8 -> caricaArchivio(sc);
                case 9 -> {
                    GestoreArchivio gestore = new GestoreArchivio();
                    gestore.saveArchive(Clinica.getArchivio());
                    printSuccess("Archivio salvato.");
                    running = false;
                }
                default -> printError("Scelta non valida.");
            }

        }
        sc.close();
    }
}