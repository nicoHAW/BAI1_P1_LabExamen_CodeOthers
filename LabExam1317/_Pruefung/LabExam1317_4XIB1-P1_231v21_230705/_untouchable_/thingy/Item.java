// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
// "HOME-VCS": git@git.HAW-Hamburg.de:SHF/Px/LabExercise/CXZ_Thingy[.git]
package _untouchable_.thingy;


import java.io.Serializable;
import java.util.Objects;
import _untouchable_.supportGeneral.Version;


/*
 * Bemerkung:
 * Diese Klasse "schreit" eigentlich danach als record implementiert zu werden.
 * Etwa:
 *     public record Item( Color color, Size size, Weight weight, Long value ){}
 * 
 * Bzgl. Serializable wären gerinfügige Ergänzungen nötig, aber das ist erst Thema im 2.Semester
 * bzw. ist Serializable überhaupt gewollt/nötig? Im 1.Semester ist das noch unbekannt.
 * 
 * Beim ersten Kontakt mit dem thingy-Package sind records meist noch noch unbekannt
 * oder zu neu (in der Veranstaltung).
 * Ferner könnte die andere Getter-Schreibweise bei records noch verwirrend sein.
 * Daher wurde an der "alten" Implemntierung als Klasse fest gehalten.
 * Es wäre aber eine (Studenten-)Übung hier auf record umzustellen und in der
 * CardDemo entsprechend nachzuziehen, sobald (Java-)record bekannt ist.
 */
/**
 * Die Klasse Item beschreibt ein Item.
 * Ein Item hat die folgenden Eigenschaften:
 * <ul>
 *     <li>Farbe (color) - hierauf kann mit getColor() zugegriffen werden.</li>
 *     <li>Gewicht (weight) - hierauf kann mit getWeight() zugegriffen werden.</li>
 *     <li>Größe (size) - hierauf kann mit getSize() zugegriffen werden.</li>
 *     <li>Wert (value) gemessen in (Euro-)Cent - hierauf kann mit getValue() zugegriffen werden.</li>
 * </ul>
 * Die Eigenschaften eines (bereits erzeugten) Items können sich nicht mehr verändern.
 * D.h. die Werte eines Items sind (nach der Erzeugung) unveränderlich!
 *<br />
 * 
 *<br />
 * 
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          P1@Hamburg-UAS.eu 
 * @version {@value #encodedVersion} 
 */
final public class Item implements Serializable {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00001_005___2023_06_05__01L; // for LEs23s
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    
    private static final long serialVersionUID = version.getEncodedVersion();
    
    
    
    
    
    // Die Farbe eines Items
    final private Color color;
    
    // Die Größe eines Items
    final private Size size;
    
    // Der Wert eines Items. Dieser kann auch negativ sein!
    // Dies gilt z.B. für "Sondermüll", der teuer entsorgt werden muss.
    // Der komplette Wertebereich eines Long ist grundsätzlich als Wert möglich!
    final private Long value;
    
    // Das Gewicht eines Items
    final private Weight weight;
    
    
    
    
    
    /**
     * Der Konstruktor erzeugt ein Item.
     * Ein Item hat die folgenden Eigenschaften:
     * <ul>
     *     <li>Farbe (color)</li>
     *     <li>Gewicht (weight)</li>
     *     <li>Größe (size)</li>
     *     <li>Wert (value) gemessen in (Euro-)Cent</li>
     * </ul>
     *
     * @param color  bestimmt die Farbe des zu erzeugenden Items.
     * @param size   bestimmt die Größe des zu erzeugenden Items.
     * @param weight bestimmt das Gewicht des zu erzeugenden Items.
     * @param value  bestimmt den Wert (gemessen in (Euro-)Cent) des zu erzeugenden Items.
     */
    public Item(
        final Color  color,
        final Size   size,
        final Weight weight,
        final Long   value
    ){
        this.color  = color;
        this.size   = size;
        this.weight = weight;
        this.value  = value;
    }//constructor()
    
    
    
    
    
    /**
     * getColor() liefert die Farbe des jeweiligen Items.
     * 
     * @return  Farbe des Items
     */
    public Color getColor(){ return color; }
    
    /**
     * getSize() liefert die Größe des jeweiligen Items.
     * 
     * @return Größe des Items
     */
    public Size getSize(){ return size; }
    
    /**
     * getWeight() liefert die Gewicht des jeweiligen Items.
     * 
     * @return  Gewicht des Items
     */
    public Weight getWeight(){ return weight; }
    
    /**
     * getValue() liefert den Wert gemessen in (Euro-)Cent des jeweiligen Items.
     * <strong>Achtung!</strong> Der Wert kann grundsätzlich auch negativ sein.
     * Dies gilt z.B. für "Sondermüll", der teuer entsorgt werden muss.
     * Der komplette Wertebereich eines Long ist grundsätzlich als Wert möglich!
     * 
     * @return Wert des Items
     */
    public Long getValue(){ return value; }
    
    
    
    @Override
    public boolean equals( final Object otherObject ){
        if( this == otherObject )  return true;
        if( null == otherObject )  return false;
        if( getClass() != otherObject.getClass() )  return false;
        
        final Item other = ((Item)( otherObject ));
        if( color != other.color )  return false;
        if( size != other.size )  return false;
        if( weight != other.weight )  return false;
        if( ! Objects.equals( value, other.value  ))  return false;             // NOTE: constants only exist once -> hence "!=" is ok too ;-)
        return true;
    }//method()
    
    @Override
    public int hashCode() {
        return Objects.hash( color, size, value, weight );
    }//method()
    
    @Override
    public String toString(){
        return String.format(
            "[<%s>: %s %s %s %s]",
            Item.class.getSimpleName(),
            color,
            size,
            weight,
            value
        );
    }//method()
    
}//class
//Note: Objects of this class are immutable
