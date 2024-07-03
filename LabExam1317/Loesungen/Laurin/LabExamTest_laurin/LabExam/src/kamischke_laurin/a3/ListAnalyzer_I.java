// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package kamischke_laurin.a3;


import java.util.List;


/**
 * Das Interface ListAnalyzer_I
 * <ul>
 *     <li>beschreibt einen generischen ListAnalyzer, der im wesentlichen eine Liste
 *         bezüglich der Anzahl ihrer Elemente analysiert und</li>
 *     <li>definiert die Funktionalität möglicher Implementierungen und
 *         fordert die entsprechenden Methoden ein.
 *     </li>
 * </ul>
 * Die zu analysierende Liste wird als Parameter an den Konstruktor übergeben und
 * kann Mehrfach-Einträge enthalten.<br />
 * Die von Ihnen zu implementierende Klasse Stack muss:
 * <ul>
 *     <li>einen öffentlichen Konstruktor aufweisen, der der folgenden Signatur genügt:<br />
 *         <code>ListAnalyzer( List<T> )</code>
 *     <li>die jeweilige Anzahl des Vorkommens der (Listen-)Elemente in der gegebenen Liste bestimmen und</li>
 *     <li>die Anzahl unterschiedlicher Elemente bestimmen sowie</li>
 *     <li>eine Menge, die alle unterschiedliche Elements enthält,bestimmen.</li>
 * </ul>
 * Diese Informationen/"Ergebnisse" sollen in Form eines FrequencyReporter-Objekts,
 * (das dem Interface {@link FrequencyReporter_I} genügt) zur Verfügung gestellt werden.<br />
 *<br />
 *<br />
 *
 * @param <T>  (Daten-)Typ der (Listen-)Elemente
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  LabExam1317_4XIB1-P1
 */
public interface ListAnalyzer_I<T> {
    
    /*
     * requested constructor()
     * =======================
    ListAnalyzer( List<T> list );
    */
    
    
    
    /**
     * Die Methode bestimmt für eine (als Konstruktor-Parameter) gegebene Liste:<br />
     * &bull; für die jeweiligen (Listen-)Elemente die Anzahl des Vorkommenens in der Liste<br />
     * &bull; die Anzahl unterschiedlicher (Listen-)Elemente<br />
     * &bull; eine Menge aller unterschiedlichen (Listen-)Elemente<br />
     * Diese Informationen/"Ergebnisse" sollen in Form eines FrequencyReporter-Objekts,
     * (das dem Interface {@link FrequencyReporter_I} genügt,) abgeliefert werden.
     * 
     * @return  ein FrequencyReporter-Objekt in dem die eingeforderten Informationen/"Ergebnisse" zusammengefasst sind.
     */
    FrequencyReporter_I<T> computeFrequencyReporter();
    
    /**
     * Diese Methode liefert die Original-Liste, die als Konstruktor-Parameter übergeben wurde.
     * 
     * @return  Original-Liste
     */
    List<T> getList();
    
}//interface
