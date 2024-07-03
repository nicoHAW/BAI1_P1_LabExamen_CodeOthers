// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package kamischke_laurin.a1;


/**
 * Über das Interface ExamineeInfo_I wird der Zugriff auf Information(en) über den Prüfling sicher gestellt.<br />
 * <br />
 * Das Interface ExamineeInfo_I definiert die Funktionalität möglicher Implementierungen und fordert die entsprechenden Methoden ein.
 * Objekte, die diesem Interface genügen werden verwendet um Sie zu identifizieren.<br />
 * <br />
 * Die von Ihnen zu implementierenden Klasse ExamineeInfo muss
 * <ul>
 *     <li>einen öffentlichen Kontruktor aufweisen, der der folgenden Signatur genügt:<br />
 *         <code>ExamineeInfo()</code>
 *     </li>
 * </ul>
 * Eine genaue Auflistung der Anforderungen an die zu implementierende Klasse findet sich auf dem Aufgabenzettel,
 * sowie weitere (Teil-)Aufgaben, die gelöst werden müssen.
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  LabExam1317_4XIB1-P1_231_v01
 */
public interface ExamineeInfo_I {
    
    /**
     * getExamineeSurName() liefert den Nachnamen des Prüflings in Kleinbuchstaben ohne mögliche Bindestriche oder Leerzeichen.
     * (Mit Kleinbuchstaben sind die 26 Kleinbuchstaben a bis z des (modernen) lateinischen Alphabets gemeint.)
     * Sollten Sie mehr als einen Nachnamen haben, ist derjenige zu verwenden mit dem Sie auch bei der HAW (zuerst) geführt werden.
     * Im Zweifelsfall schauen Sie einfach auf Ihrem Studentenausweis nach, der vor Ihnen auf den Tisch liegt.
     * Ferner müssen mögliche Namenszusätze (wie z.B. von, van, ten, oder Mc) hinten angestellt werden.<br />
     * Beispielsweise würde "von Croÿ-Gräßler" zu "croygraesslervon".<br />
     * 
     * @return Nachname des Prüflings in Kleinbuchstaben.
     */
    public String getExamineeSurName();
    
    /**
     * getExamineeFirstName() liefert den Vornamen des Prüflings in Kleinbuchstaben ohne mögliche Bindestriche oder Leerzeichen.
     * (Mit Kleinbuchstaben sind die 26 Kleinbuchstaben a bis z des (modernen) lateinischen Alphabets gemeint.)
     * Sollten Sie mehr als einen Vornamen haben, ist derjenige zu verwenden mit dem Sie auch bei der HAW (zuerst) geführt werden.
     * Im Zweifelsfall schauen Sie einfach auf Ihrem Studentenausweis nach, der vor Ihnen auf den Tisch liegt.<br />
     * Beispielsweise würde "André-Ëvïno" zu "andreevino".<br />
     * 
     * @return Vorname des Prüflings in Kleinbuchstaben.
     */
    public String getExamineeFirstName();

}//interface
