package _untouchable_.supportC1x01;


import _untouchable_.supportGeneral.GivenCodeVersion;
import _untouchable_.supportGeneral.Version;


// __???__<220601>: TODO Macht diese Klasse noch Sinn?
/**
 * LabExam13xx_4B-XI1-P1    (PTP/Eclipse)<br />
 * <br />
 * Central "container" for version IDs.
 * Here, the different version IDs are "centralized".
 * <br />
 * <br />
 * VCS: see ReadMe.txt
 *
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          P1@Hamburg-UAS.eu
 * @version {@value #encodedVersion}
 */
public class CentralVersionData {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00002_004___2023_06_28__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    
    
    
    
    
    // __???__<220601>: TODO Macht diese Klasse noch Sinn?
    //====================================================
    
    
    // lab exam version ID
    public final static String centralLabExamVersionID = "P1 LabExam1317, (231v01), 2023/07/05 [v1.01]";       //  ### <<<<====
    
    
    // test support routine collection (the "engine" ;-) version ID
    public final static String centralTestSupportVersionID = GivenCodeVersion.getDecodedVersion();
    
    
    // version ID of WPI computer
    public final static String centralWPIComputerVersionID = WPI_Computer.getDecodedVersion();
    
    
    // version ID of configuration class/structure/possibilities
    public final static String centralTestConfigurationVersionID = Configuration.configurationVersionID;
    
    
    // test cycle
    public final static String testCycle = "C1x01";
    
    
    
    // "serialVersionUID": test result data base (format for serialization) version ID   - generally the "Demo and Reference lab exam" shall contain the latest version
    public final static long centralTestResultDataBaseRelatedSerialVersionUID = encodedVersion;
    
}//class
