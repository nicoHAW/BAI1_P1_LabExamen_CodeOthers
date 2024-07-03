// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package template_raw.a3;


import java.util.Set;


/**
 * Das Interface FrequencyReporter_I
 * <ul>
 *     <li>beschreibt einen generichen FrequencyReporter und</li>
 *     <li>definiert die Funktionalität möglicher Implementierungen und
 *         fordert die entsprechenden Methoden ein.
 *     </li>
 * </ul>
 * Ein FrequencyReporter wird genutzt um die "Ergebnisse der Berechnungen" eines 
 * {@link ListAnalyzer_I} in einem Objekt abliefern zu können.
 * Diese Ergebnisse beziehen sich auf die Elemente der vom {@link ListAnalyzer_I}
 * analysierten Liste.
 * Der FrequencyReporter hält die folgenden Informationen/"Ergebnisse" bereit:<br />
 * &bull; für die einzelnen Elemente, die Anzahl ihres jeweiligen Vorkommens<br />
 * &bull; eine Menge der unterschiedlichen Elemente<br />
 * &bull; die Anzahl unterschiedlicher Elemente<br />
 * <br />
 * 
 * @param <T>  (Daten-)Typ der Elemente
 */
public interface FrequencyReporter_I<T> {
    
    /**
     * Liefert das Berechnungsergebnis:
     * Die Anzahl des Vorkommens des jeweiligen Elements.
     *  
     * @param element  das jeweilige Element für das die Anzahl seines Auftretens
     *                 angefragt wird
     * @return  die Anzahl des Auftretend des als Parameter übergebenen Elements
     */
    int getFrequency( final T element );
    
    
    /**
     * Liefert das Berechnungsergebnis:
     * Die Menge von unterschiedlichen Elementen.
     * 
     * @return  Menge unterschiedlicher Elemente.
     */
    Set<T> knownElements();
    
    
    /**
     * Liefert das Berechnungsergebnis:
     * Die Anzahl unterschiedlicher Elemente.
     *  
     * @return  Anzahl unterschiedlicher Elemente.
     */
    int getAmountOfUniqueItems();
    
}//interface
