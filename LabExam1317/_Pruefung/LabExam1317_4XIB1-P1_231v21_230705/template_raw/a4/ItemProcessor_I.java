// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package template_raw.a4;


import _untouchable_.thingy.Item;


/**
 * Das Interface ItemProcessor_I
 * <ul>
 *     <li>beschreibt einen ItemProcessor, der Items verarbeitet
 *         und
 *     </li>
 *     <li>definiert die Funktionalität möglicher Implementierungen und
 *         fordert die entsprechende(n) Methode(n) ein.
 *     </li>
 * </ul>
 * Die von Ihnen zu implementierende Klasse ItemProcessor muss
 * <ul>
 *     <li>einen öffentlichen parameterlosen Konstruktor aufweisen, der der folgenden Signatur genügt:<br />
 *         <code>ItemProcessor()</code>
 *     </li>
 * </ul>
 * 
 * Aufgabe des ItemProcessors ist es Items zunächst zu sammeln und immer sobald
 * drei Items gleichen Gewichts vorliegen, diese abzuliefern.
 * 
 * Eine genaue Auflistung der Anforderungen an die zu implementierende Klasse
 * findet sich auf dem Aufgabenzettel.<br />
 *<br />
 *<br />
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  LabExam1317_4XIB1-P1
 */
public interface ItemProcessor_I {
    
    /*
     * requested constructor()
     * =======================
    ItemProcessor();
    */
    
    
    
    
    
    /**
     * Die Operation process() verarbeitet ein Item.<br />
     * Der von Ihnen zu implementierende ItemProcessor reagiert auf ein jeweils "erscheinendes"
     * Item (bzw. konkret auf das als Parameter übergebene Item ).
     * Immer wenn ein Item mit process() an dem ItemProcessor übergeben wird,
     * wird dieses Item (zunächst) in den internen Bestand übernommen.
     * Sobald drei Items gleichen Gewichts im internen Bestand vorliegen,
     * werden diese als Ergebnis von process() abgeliefert
     * und aus dem internen Bestand entfernt.<br />
     * Der als Parameter übergebene Wert darf nicht <code>null</code> sein.
     * <br />
     * Bemerkungen:
     * <ul><li>Der ItemProcessor reagiert unmittelbar sofort.
     *         D.h., das als Parameter übergebene Item ist bei einer möglichen Zusammenstellung
     *         eines Triples zu berücksichtigen.
     *     </li>
     *     <li>Es wird zugesichert, dass solange sich ein und dasselbe Item (korrekter Weise)
     *         noch im Bestand befindet, dieses Item nicht noch einmal dem ItemProcessor übergeben wird.
     *         Ein bereits aus dem internen Bestand entferntes Item kann dem ItemProcessor
     *         wieder mit process() übergeben werden.
     *         (Maßstab für diese Zusicherung ist das Verhalten bei korrekter Funktion.)
     *     </li>
     * </ul>
     *
     * @param item  bestimmt das (neue;) Item, das zu verarbeiten ist.
     * 
     * @return  ein Triple (gebildet aus drei Items gleichen Werts) - sofern dieses zusammengestellt werden kann
     *          oder sonst <code>null</code>.
     */
    public Triple_I process( Item item );
    
    
    /**
     * Die Operation itemsProcessed() liefert die Anzahl der Items, die vom ItemProcessor
     * verarbeitet wurden ( <i>bzw. die Anzahl Aufrufe von process() mit gültigen Parameter</i> )
     * seit dem letzten Aufruf von reset()
     * oder sofern kein reset() aufgerufen wurde, seit dem Programmstart.
     *
     * @return  Die Anzahl der vom ItemProcessor verarbeiteten Items.
     */
    public int itemsProcessed();
    
    
    /**
     * Die Operation triplesFound() liefert die Anzahl der bisher gefundenen Triple
     * (gebildet aus 3 Items gleichen Gewichts) seit dem letzten Aufruf von reset()
     * oder sofern kein reset() aufgerufen wurde, seit dem Programmstart.
     *
     * @return  Die Anzahl der bisher gefundenen Triple (gebildet aus 3 Items gleichen Gewichts)
     */
    public int triplesFound();
    
    
    /**
     * Die Methode reset() löscht den internen Bestand und setzt alle eingeforderten Zähler auf den Startwert 0.<br />
     * ( <i>reset() macht halt einen reset. Der Name sollte selbsterklärend sein.</i> )
     */
    public void reset();
    
}//interface
