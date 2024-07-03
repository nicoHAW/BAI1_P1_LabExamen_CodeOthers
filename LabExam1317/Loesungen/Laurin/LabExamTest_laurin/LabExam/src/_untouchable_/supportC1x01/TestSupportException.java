package _untouchable_.supportC1x01;


import _untouchable_.supportGeneral.Version;


/**
 * LabExam13xx_4B-XI1-P1    (PTP/Eclipse)<br />
 * <br />
 * This code belongs to the LabExam test support routine collection as part of some LabExam<br />
 * <br />
 * Test Support Exception
 * ...
 * <br />
 * <br />
 * VCS: see ReadMe.txt
 *
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          P1@Hamburg-UAS.eu
 * @version {@value #encodedVersion}
 */
public class TestSupportException extends Exception {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00002_002___2023_06_05__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    
    final static private long  serialVersionUID = version.getEncodedVersion();
    
    
    
    
    
    final private Throwable causingThrowable;
    
    
    
    
    
    /**
     * ...
     *
     * @param message                   ...
     * @param causingThrowable          ...
     */
    TestSupportException( final String message,  final Throwable causingThrowable ){     // package scope on purpose
        super( message );
        this.causingThrowable = causingThrowable;
    }//constructor()
    //
    /**
     * ...
     *
     * @param message    ...
     */
    TestSupportException( final String message ){                               // package scope on purpose
        this( message, null );
    }//constructor()
    //
    /**
     * ...
     */
    TestSupportException(){                                                     // package scope on purpose
        this( "test support function broken" );
    }//constructor()
    
    
    
    
    
    @Override
    public Throwable getCause(){
        return causingThrowable;
    }//method()
    
    // see definition of Throwable.getCause() : Returns ... or null if the cause is nonexistent or unknown... 
    public boolean causingThrowableExists(){
        return null == causingThrowable;
    }//method()
    
}//class
