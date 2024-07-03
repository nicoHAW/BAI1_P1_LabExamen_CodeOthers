package _untouchable_.supportC1x01;


import _untouchable_.supportGeneral.Version;
import java.util.Objects;


/**
 * LabExam13xx_4B-XI1-P1    (PTP/Eclipse)<br />
 * <br />
 * This code belongs to the LabExam test support routine collection as part of some LabExam<br />
 * <br />
 * Valuation Unit
 * <br />
 * <br />
 * VCS: see ReadMe.txt
 *
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          P1@Hamburg-UAS.eu
 * @version {@value #encodedVersion}
 */
public class ValuationUnit {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00002_004___2023_06_05__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    
    
    
    
    
    /**
     * ...
     */
    final TL level;                                                             // test level
    
    /**
     * ...
     */
    final TE exercise;                                                          // test exercise - e.g. A1
    
    
    
    
    
    /**
     * ...
     *
     * @param level  ...
     * @param exercise  ...
     */
    ValuationUnit( final TL level, final TE exercise ){
        if( null == level  ||  null == exercise )  throw new IllegalArgumentException( "null is NOT supported as argument" );
        //
        this.level = level;
        this.exercise = exercise;
    }//constructor()
    
    
    
    
    
    @Override
    public int hashCode() {
        return Objects.hash( exercise, level );
    }//method()
    
    @Override
    public boolean equals( final Object otherObject ){
        if( this == otherObject )  return true;
        if( null == otherObject )  return false;
        if( getClass()!=otherObject.getClass() )  return false;
        final ValuationUnit other = (ValuationUnit)( otherObject );
        if( ! Objects.equals( level, other.level ))  return false;
        if( ! Objects.equals( exercise, other.exercise ))  return false;
        return true;        
    }//method()
    
    
    @Override
    public String toString(){
        return String.format(
            "[%s>: %s %s]",
            ValuationUnit.class.getSimpleName(),
            level,
            exercise
        );
    }//method()
    
}//class
