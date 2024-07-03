// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package kamischke_laurin.a2;


/**
 * Das Interface ArrayProcessor_I
 * <ul><li>beschreibt einen ArrayProcessor, der "2-dimensionale" Arrays long[][] verarbeitet und</li>
 *     <li>definiert die Funktionalität möglicher Implementierungen und
 *         fordert die entsprechenden Methoden ein.
 *     </li>
 * </ul>
 * Die von Ihnen zu implementierende Klasse ArrayProcessor muss
 * <ul><li>einen öffentlichen Konstruktor aufweisen, der der folgenden Signatur genügt:<br />
 *         <code>ArrayProcessor( long[][] )</code><br />
 *         <br />
 *         Der Konstruktor darf keine Arrays akzeptieren, die
 *         <ul><li>null sind oder</li>
 *             <li>null enthalten oder</li>
 *             <li>leer sind oder</li>
 *             <li>leere Arrays enthalten.</li>
 *         </ul> 
 *     </li>
 * </ul>
 *
 * Eine genaue Auflistung der Anforderungen an die zu implementierende Klasse
 * findet sich auf dem Aufgabenzettel.<br />
 *<br />
 *<br />
 * 
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  LabExam1317_4XIB1-P1
 */
public interface ArrayProcessor_I {
    
    /*
     * requested constructor()
     * =======================
    ArrayProcessor( long[][] sourceArray );
    */
    
    
    
    
    
    // Beispiele für expand1stDimBySum
    // --------------=================
    // In den nachfolgenden Beispielen läuft
    // die 1.Dimension entlang der Y-Achse und
    // die 2.Dimension entlang der X-Achse
    //
    // OriginalArray     wird zu        Ergebnis
    //
    //  o                                o
    //  |                                |
    //  v                                v
    // +-+  +-+-+-+                     +-+  +-+-+-+
    // +o-->|1|1|1|                     +o-->|1|1|1|
    // +-+  +-+-+-+                     +-+  +-+-+-+
    // +o-->|1|2|1|         =>          +o-->|1|2|1|
    // +-+  +-+-+-+                     +-+  +-+-+-+
    // +o-->|1|1|3|                     +o-->|1|1|3|
    // +-+  +-+-+-+                     +-+  +-+-+-+
    //                                  +o-->|3|4|5|
    //                                  +-+  +-+-+-+
    //
    //################################################
    //
    // Achtung ab Level D bzw. tol_4s müssen auch nicht-Matrizen unterstützt werden.
    // Beispiel:
    //
    // OriginalArray     wird zu        Ergebnis
    //
    //  o                                o
    //  |                                |
    //  v                                v
    // +-+  +-+-+                       +-+  +-+-+
    // +o-->|1|1|                       +o-->|1|1|
    // +-+  +-+-+-+-+                   +-+  +-+-+-+-+
    // +o-->|1|2|1|1|       =>          +o-->|1|2|1|1|
    // +-+  +-+-+-+-+                   +-+  +-+-+-+-+
    // +o-->|1|1|3|                     +o-->|1|1|3|
    // +-+  +-+-+-+                     +-+  +-+-+-+-+
    //                                  +o-->|3|4|4|1|
    //                                  +-+  +-+-+-+-+
    //
    /**
     * expand1stDimBySum erweitert das Array long[][] in der 1.Dimension
     * um die Summe der jeweiligen (Grund-)Werte mit gleicher Position in der 2.Dimension.
     */
    void expand1stDimBySum();
    
    
    
    // Beispiele für expand2ndDimBySum
    // --------------=================
    // In den nachfolgenden Beispielen läuft
    // die 1.Dimension entlang der Y-Achse und
    // die 2.Dimension entlang der X-Achse
    //
    // OriginalArray     wird zu        Ergebnis
    //
    //  o                                o
    //  |                                |
    //  v                                v
    // +-+  +-+-+-+                     +-+  +-+-+-+-+
    // +o-->|1|1|1|                     +o-->|1|1|1|3|
    // +-+  +-+-+-+                     +-+  +-+-+-+-+
    // +o-->|1|2|1|         =>          +o-->|1|2|1|4|
    // +-+  +-+-+-+                     +-+  +-+-+-+-+
    // +o-->|1|1|3|                     +o-->|1|1|3|5|
    // +-+  +-+-+-+                     +-+  +-+-+-+-+
    //
    //################################################
    //
    // Achtung ab Level D bzw. tol_4s müssen auch nicht-Matrizen unterstützt werden.
    // Beispiel:
    //
    // OriginalArray     wird zu        Ergebnis
    //
    //  o                                o
    //  |                                |
    //  v                                v
    // +-+  +-+-+                       +-+  +-+-+-+
    // +o-->|1|1|                       +o-->|1|1|2|
    // +-+  +-+-+-+-+                   +-+  +-+-+-+-+-+
    // +o-->|1|2|1|1|       =>          +o-->|1|2|1|1|4|
    // +-+  +-+-+-+-+                   +-+  +-+-+-+-+-+
    // +o-->|1|1|3|                     +o-->|1|1|3|5|
    // +-+  +-+-+-+                     +-+  +-+-+-+-+
    //
    /**
     * expand2ndDimBySum erweitert das Array long[][] in der 2.Dimension
     * um die Summe der jeweiligen (Grund-)Werte mit gleicher Position in der 1.Dimension.
     */
    void expand2ndDimBySum();
    
    
    
    /**
     * getCurrentArray liefert das Array in seinem aktuellen Zustand.
     * 
     * @return  das Array in seinem aktuellen Zustand.
     */
    long[][] getCurrentArray();
    
    
    
    /**
     * getOriginalArray) liefert das ursprünglich als Parameter an den ItemProcessor
     * übergebene Array in seinem Original-Zustand.
     * 
     * @return  das ursprünglich als Parameter an den ItemProcessor übergebene Array
     *          in seinem Original-Zustand.
     */
    long[][] getOriginalArray();
    
}//interface
