package _untouchable_.supportC1x01;


import static org.junit.jupiter.api.Assertions.fail;

import _untouchable_.supportGeneral.Version;


/**
 * LabExam13xx_4B-XI1-P1    (PTP/Eclipse)<br />
 * <br />
 * This code belongs to the LabExam test support routine collection as part of some LabExam<br />
 * <br />
 * JUW ::= JUnit Wrapper
 * ...
 * <br />
 * <br />
 * VCS: see ReadMe.txt
 * HomeVCS ...
 *
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          P1@Hamburg-UAS.eu
 * @version {@value #encodedVersion}
 */

class JUW {                                                                     // package scope on purpose
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00001_002___2023_06_05__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    
    
    
    
    
    public static void jufail( String message ){
        fail( message );
    }//method()
    
}//class
