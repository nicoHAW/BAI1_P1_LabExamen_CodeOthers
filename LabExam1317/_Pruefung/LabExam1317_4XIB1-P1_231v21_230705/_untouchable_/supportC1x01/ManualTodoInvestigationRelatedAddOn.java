package _untouchable_.supportC1x01;


import _untouchable_.supportGeneral.Version;


/**
 * LabExam13xx_4B-XI1-P1    (PTP/Eclipse)<br />
 * <br />
 * ...
 * <br />
 * <br />
 * VCS: see ReadMe.txt
 *
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          P1@Hamburg-UAS.eu
 * @version {@value #encodedVersion}
 */
public enum ManualTodoInvestigationRelatedAddOn {
    
    NONE,                                                                       // normal setting during lab exam
    PROVED_ACCEPTABLE,
    PROVED_UNACCEPTABLE;
    
    
    
    
    
    // Das Folgende dient nur der Versionierung:
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00002_002___2023_06_05__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    //
    // Note: Enums are automatically serializable and shall NOT contain serialVersionUID
    // =====
    // enum are inherently serializable since Class java.lang.Enum extends Object implements Serializable.
    // Futher declared serialVersionUID fields are ignored anyway.
    // See  http://docs.oracle.com/javase/8/docs/api/java/lang/Enum.html
    //      http://docs.oracle.com/javase/8/docs/api/serialized-form.html#java.lang.Enum
    //      http://docs.oracle.com/javase/8/docs/platform/serialization/spec/serialTOC.html ->  1.12 Serialization of Enum Constants
    //      http://docs.oracle.com/javase/8/docs/platform/serialization/spec/serial-arch.html#a6469
    // Feel free to check with serialver ;-)
    
}//enum
