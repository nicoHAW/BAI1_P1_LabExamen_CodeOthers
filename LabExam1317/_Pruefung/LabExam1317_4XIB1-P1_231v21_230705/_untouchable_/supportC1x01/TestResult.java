package _untouchable_.supportC1x01;


import _untouchable_.supportGeneral.Version;
import java.io.Serializable;


/**
 * LabExam13xx_4B-XI1-P1    (PTP/Eclipse)<br />
 * <br />
 * This code belongs to the LabExam test support routine collection as part of some LabExam<br />
 * <br />
 * TestResult ...
 * <br />
 * <br />
 * VCS: see ReadMe.txt
 *
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          P1@Hamburg-UAS.eu
 * @version {@value #encodedVersion}
 */
public record TestResult (
    String  testMethodName,                                                     //
    int     weight                                                              // weight for this very test (default is one)
) implements Comparable<TestResult>, Serializable {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00002_003___2023_06_03__02L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    
    // Note: TestResultTable is THE VERY class that is serialized resp. causally TestResultTable must be Serializable
    // Note: TestResultTable -> Testresult
    final static private long  serialVersionUID = version.getEncodedVersion();
    
    
    
    
    
    @Override
    public int compareTo( final TestResult other ){
        int tmp;
        tmp = testMethodName.compareTo( other.testMethodName );
        if( 0 != tmp )  return tmp;
        tmp = Integer.compare( weight, other.weight );
        return tmp;
    }//method()
    
}//class
