import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.time.LocalDate;

public class GestoreArchivio {
    //Append true,I valori duplicati non vanno a popolare la hashmap.
    public void saveArchive(HashMap<String, Paziente> archive){
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pazienti.txt", true)))) {  
            for (Paziente p : archive.values()){
                pw.println("### PAZIENTE");
                pw.println("CF:" + p.getId());
                pw.println("NOME:" + p.getName());
                pw.println("COGNOME:" + p.getSurname());
                pw.println("COLORE:" + p.getTriageColor());
                pw.println("DATA_REG:" + p.getRegistrationDate());
                for (Visita v : p.getVisite()) {
                    pw.println("--- VISITA");
                    pw.println("TIPO:" + v.getClass().getSimpleName());
                    pw.println("DATA:" + v.getData());
                    pw.println("PRIORITY:" + p.calculatePriority(p.getId()));
                    pw.println("DIAGNOSIS:" + v.getDiagnosi());
                    pw.println("DOCTOR:" + v.getMedico());
                    if (v instanceof VisitaGenerica) {
                        VisitaGenerica vg = (VisitaGenerica) v;
                        pw.println("REPARTO:" + vg.getReparto());
                    } else if (v instanceof VisitaOrtopedica) {
                        VisitaOrtopedica vo = (VisitaOrtopedica) v;
                        pw.println("PARTE_CORPO:" + vo.getParteCorpo());
                    } else if (v instanceof VisitaCardiologica) {
                        VisitaCardiologica vc = (VisitaCardiologica) v;
                        pw.println("FREQUENZA:" + vc.getFrequenzaCardiaca());
                        pw.println("PRESSIONE_SIST:" + vc.getPressioneSist());
                        pw.println("PRESSIONE_DIAST:" + vc.getPressioneDiast());
                    }
                    pw.println("--- FINE_VISITA");
                }
                pw.println("### FINE_PAZIENTE");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Carica archivio da pazienti.txt e lo ritorna come hashmap gestita dalla classe clinica
    public HashMap<String, Paziente> loadArchive() {
        HashMap<String, Paziente> archive = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("pazienti.txt"))) {
            String line;
            Paziente currentPatient = null;
            while ((line = br.readLine()) != null) {
                //
                if (line.equals("### PAZIENTE")) {
                    String cf = br.readLine().substring(3);
                    String nome = br.readLine().substring(5);
                    String cognome = br.readLine().substring(8);
                    String colore = br.readLine().substring(7);
                    String dataReg = br.readLine().substring(9);
                    currentPatient = new Paziente(
                        cf, 
                        nome, 
                        cognome, 
                        colore, 
                        LocalDate.parse(dataReg)
                    );
                    archive.put(cf, currentPatient);
                }
                else if (line.equals("--- VISITA")) {
                    String tipo = br.readLine().substring(5); 
                    String data = br.readLine().substring(5);
                    String priority = br.readLine().substring(9);
                    String diagnosis = br.readLine().substring(10);
                    String doctor = br.readLine().substring(7);
                    Visita visita = null;
                    if (tipo.equals("VisitaGenerica")) {
                        String reparto = br.readLine().substring(8);
                        visita = new VisitaGenerica(
                            LocalDate.parse(data),
                            priority,
                            diagnosis,
                            doctor,
                            reparto
                        );
                    } else if (tipo.equals("VisitaOrtopedica")) {
                        String parteCorpo = br.readLine().substring(12);
                        visita = new VisitaOrtopedica(
                            LocalDate.parse(data),
                            priority,
                            diagnosis,
                            doctor,
                            parteCorpo
                        );
                    } else if (tipo.equals("VisitaCardiologica")) {
                        int frequenza = Integer.parseInt(br.readLine().substring(10));
                        int pressioneSist = Integer.parseInt(br.readLine().substring(15));
                        int pressioneDiast = Integer.parseInt(br.readLine().substring(16));
                        visita = new VisitaCardiologica(
                            LocalDate.parse(data),
                            priority,
                            diagnosis,
                            doctor,
                            frequenza,
                            pressioneSist,
                            pressioneDiast
                        );
                    }
                    br.readLine();
                    if (currentPatient !=null) {
                        currentPatient.addVisit(visita);
                    }
                }
            } 
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CodiceFiscaleNonValidoException e) {
            System.out.println(e.getMessage());
        }
        return archive;
    }

}
