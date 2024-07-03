// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package template_raw.a5;

 
/**
 * Das Interface Stack_I
 * <ul><li>beschreibt einen generischen Stack, der Datensätze entsprechend des generischen Typs aufnehmen kann und</li>
 *     <li>definiert die Funktionalität möglicher Implementierungen und fordert die entsprechenden Methoden ein.</li>
 * </ul>
 * Ein Stack bzw. die von Ihnen zu implementierende Klasse Stack muss:
 * <ul><li>sich wie ein Stack verhalten. Dies war Thema der Programmierenvorlesung.<br />
 *         Stichwort: LIFO.
 *     </li>
 *     <li>einen öffentlichen parameterlosen Konstruktor aufweisen, der der folgenden Signatur genügt:<br />
 *         <code>Stack()</code>
 *     </li>
 *     <li>eine selbstgeschriebene dynamische Datenstruktur sein.
 *         Sie dürfen für die Implementierung von Stack insbesondere <b><u>NICHT</u></b>
 *         auf bereits existierende Datenstrukturen wie z.B. Collection, Map, Array usw.
 *         zurückgreifen.<br />
 *         Die Implementierung muss in Form einer verketteten Liste erfolgen.
 *         Sie werden dafür auf Knoten zurückgreifen müssen. Die zugehörige Klasse muss <b><code>Node</code></b> heißen.
 *         Ein Klassen-Skelett Node ist vorgegeben (damit der TestFrame frei von Syntax-Fehlern ist).
 *         Es ist Ihre Aufgabe dieses Code-Skelett sinnvoll zu füllen!
 *      </li>
 * </ul>
 * Bemerkung:<br />
 * Noch einmal: Für diese Aufgabe dürfen Sie <b><u>NICHT</u></b> auf bereits existierende
 * Datenstrukturen wie z.B. Collection, Map, Array usw. zurückgreifen.
 * Sie müssen den generischen Stack mit <strong>eigenen Mitteln</strong> selbst implementieren
 * und zwar als <strong>dynamische Datenstruktur</strong> bzw. konkret als verkettete Liste.<br />
 * <br />
 * Eine genaue Auflistung der Anforderungen an die zu implementierende Klasse findet sich auf dem Aufgabenzettel.<br />
 *<br />
 *<br />
 * 
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  LabExam1317_4XIB1-P1
 */
interface Stack_I<T> {
    
    /*
     * requested constructor()
     * =======================
    Stack();
    */
    
    
    
    
    
    /**
     * Diese Methode legt einen Datensatz vom generischen Typ T auf dem Stack ab.
     * Falls der übergebene Parameter keine gültige Referenz enthält, ist geeignet zu reagieren.
     * Diese Methode beachtet die LIFO-Ordnung.
     * <br />
     * 
     * @param data  bestimmt den Datensatz, der auf dem Stack abgelegt werden soll.
     */
    void push( final T data );
    
    
    /**
     * Diese Methode liefert (von den im Stack enthaltenen Datensätzen) den zuletzt
     * auf dem Stack abgelegten Datensatz (vom generischen Typ T) und
     * entfernt ihn vom Stack.
     * Falls der Stack im Vorfeld leer ist, ist geeignet zu reagieren.<br />
     * Diese Methode beachtet die LIFO-Ordnung.
     * <br />
     * 
     * @return  Der zuletzt auf dem Stack abgelegten Datensatz.
     */
    T pop();
    
    
    /**
     * Diese Methode liefert (von den im Stack enthaltenen Datensätzen) den zuletzt
     * auf dem Stack abgelegten Datensatz (vom generischen Typ T).
     * Im Gegensatz zu pop() entfernt diese Methode NICHT den Datensatz.
     * Der Datensatz verbleibt im Stack.
     * Falls der Stack im Vorfeld leer ist, ist geeignet zu reagieren.
     * Diese Methode beachtet die LIFO-Ordnung.
     * <br />
     * 
     * @return  Der zuletzt auf dem Stack abgelegten Datensatz.
     */
    T top();
    
    /**
     * Diese Methode leert den Stack.
     */
    void clear();
    
    /**
     * Diese Methode prüft, ob der Stack leer ist.
     * <br />
     * 
     * @return  <code>true</code> falls der Stack leer ist und
     *          sonst <code>false</code>.
     */
    boolean isEmpty();
    
}//interface
