// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package _untouchable_.data;


import java.io.Serializable;
import java.util.Objects;

import _untouchable_.supportGeneral.Version;


/**
 * Die Klasse Data dient als Platzhalter-Klasse.
 * Diese Klasse steht "dort", wo typischer Weise ein Generic stehen wuerde.
 * "Hier" in der Rechnerpruefung, soll die Klasse von moeglichen Problemen mit Generics entlasten.
 * Die Klasse selbst ist nicht weiter interessant - sie dient nur als Platzhalter-Klasse.
 *<br />
 *<br />
 *
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          P1@Hamburg-UAS.eu 
 * @version {@value #encodedVersion} 
 */
public class Data implements Comparable<Data> {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00000_001___2022_07_04__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    
    
    
    
    
    private int data;
    
    
    
    
    
    public Data( final int data ){
        this.data = data;
    }//constructor()
    //
    public Data (){
        this(0);
    }//constructor()
    
    
    
    
    
    @Override
    public boolean equals( final Object otherObject ){
        if( this == otherObject )  return true;
        if( null == otherObject )  return false;
        if( getClass()!=otherObject.getClass() )  return false;
        
        final Data other = (Data)( otherObject );
        if( data != other.data  )  return false;
        return true;
    }//method()
    
    @Override
    public int hashCode(){ return Objects.hash(data); }
    
    @Override
    public int compareTo( final Data other ){ return Integer.compare( data, other.data ); }
    
    @Override
    public String toString(){
        return String.format(
            "[<%s>: data=%d]",
            Data.class.getSimpleName(),
            this.data
        );
    }//method()
    
}//class
