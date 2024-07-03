package _untouchable_.supportC1x01;


import _untouchable_.supportGeneral.Version;
import java.io.Serializable;


/**
 * LabExam13xx_4B-XI1-P1    (PTP/Eclipse)<br />
 * <br />
 * This code belongs to the LabExam test support routine collection as part of some LabExam<br />
 * <br />
 * The class {@link TestTopic} ...
 * <br />
 * <br />
 * VCS: see ReadMe.txt
 *
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          P1@Hamburg-UAS.eu
 * @version {@value #encodedVersion}
 */
public record TestTopic (
    TL  level,                                                                  // test level
    TE  exercise,                                                               // exercise - e.g. A1, A2, A3, A4, A5
    TC  category                                                                // test category
) implements Comparable<TestTopic>, Serializable {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00002_004___2023_06_05__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    
    // Note: TestResultTable is THE VERY class that is serialized resp. causally TestResultTable must be Serializable
    // Note: TestResultTable -> TestTopic
    // Note: records...
    
    
    
    
    
    @Override
    public int compareTo( final TestTopic other ){
        int tmp;
        tmp = level.compareTo( other.level );
        if( 0 != tmp )  return tmp;
        tmp = exercise.compareTo( other.exercise );
        if( 0 != tmp )  return tmp;
        tmp = category.compareTo( other.category );
        return tmp;
    }//method()
    
    
    /**
     * Generates String containing level, exercise and category.
     */
    public String toSpecialString(){
        return String.format(
            "[ %s %s %s ]",
            level,
            exercise,
            category
        );
    }//method()
    
}//record
