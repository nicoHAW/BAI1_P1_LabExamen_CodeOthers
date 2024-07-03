// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package template_raw.a4;


import _untouchable_.thingy.*;


/**
 * Das Interface Triple_I
 * <ul><li>beschreibt ein Triple bzw. eine Zusammenstellung von 3 Items gleichen Gewichts
 *         und
 *     </li>
 *     <li>definiert die Funktionalität möglicher Implementierungen und
 *         fordert die entsprechende(n) Methode(n) ein.
 *     </li>
 * </ul>
 * In einem Triple sind exakt 3 Items gleichen Gewichts zusammengestellt.
 * Beginnend (an der Position 0) mit dem ältesten bzw. dem zuerst aufgetretenen Item
 * hin zum jügsten bzw. zuletzt aufgetretenen Item (an der Position 2).
 * 
 * Eine genaue Auflistung der Anforderungen an die zu implementierende Klasse
 * findet sich auf dem Aufgabenzettel.<br />
 *<br />
 *<br />
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  LabExam1317_4XIB1-P1
 */
public interface Triple_I {
    
    /**
     * Die Operation get() liefert ein Item im Triple, wobei der Zugriff entsprechend
     * des jeweiligen Auftrittsalters (z.B. in einem ItemProcessorer) erfolgt.
     * Ein Triple besteht auf 3 Items gleichen Gewichts.
     * Mit Hilfe von getItem() kann auf diese Items zugegriffen werden.
     * getItem(0) liefert das älteste Item
     * und getItem(2) das letzte bzw. jüngste Item.
     * 
     * @param appearenceRelatedIndex  die Position im Triple (bezogen auf das Auftritts-Alter).
     *                                Es sind nur Werte von 0 bis 2 zulässig.
     * @return  das Item an der spezifizierten Position.
     */
    Item getItem( int appearenceRelatedIndex );
    
}//interface
