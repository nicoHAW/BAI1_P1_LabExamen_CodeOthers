package _untouchable_.supportC1x01;


import _untouchable_.supportGeneral.Version;
import java.io.File;


/**
 * LabExam13xx_4B-XI1-P1    (PTP/Eclipse)<br />
 * <br />
 * This code belongs to the LabExam test support routine collection as part of some LabExam<br />
 * <br />
 * ...
 * 
 * VCS: see ReadMe.txt
 *
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          P1@Hamburg-UAS.eu
 * @version {@value #encodedVersion}
 */
public class RefTypeInfoContainer {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00002_007___2023_06_05__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung

    
    
    
    
    
    final Class<?> refTypeAsClass;
    final String   referenceTypeNameAsString;
    final String   localPackagePathInJavaStyleAsString;
    final String   pathToJavaRootBinHomeInUnixStyleAsString;
    final String   pathToJavaRootSrcHomeInUnixStyleAsString;
    final String   pathToReferenceTypeBinFileInUnixStyleAsString;
    final String   pathToReferenceTypeSrcFileInUnixStyleAsString;
    final String   pathToReferenceTypeSrcFileInDosStyleAsString;
    final File     referenceTypeClassAsFile;
  //TODO requestedRefTypeWithPath ???
    
    
    
    
    /**
     * RefTypeInfoContainer
     * 
     * @param refTypeName               name of reference type
     * @param exercisePackagePath       package path of exercise
     * @param pathToJavaRootBinHome     ...
     */
    public RefTypeInfoContainer (
        final String refTypeName,
        final String exercisePackagePath,
        final String pathToJavaRootBinHome
    ) throws ClassNotFoundException {
        final String refTypeWithPath =  exercisePackagePath + "." + refTypeName;
        //
        refTypeAsClass = Class.forName( refTypeWithPath );  //<<<<================ might cause ClassNotFoundException()
        if( null == refTypeAsClass )  throw new ClassNotFoundException( String.format( "can NOT find/generate \"%s\"",  refTypeName ));
        //
        referenceTypeNameAsString = refTypeName;
        //
        localPackagePathInJavaStyleAsString = exercisePackagePath;
        //
        pathToJavaRootBinHomeInUnixStyleAsString = pathToJavaRootBinHome;
        pathToJavaRootSrcHomeInUnixStyleAsString = pathToJavaRootBinHome.replaceFirst( "/bin/$", "/src/" );
        //
        final String adaptedPathToRefTypeInUnixStyle = refTypeAsClass.getCanonicalName().replaceAll( "\\.", "/" );
        pathToReferenceTypeBinFileInUnixStyleAsString = pathToJavaRootBinHomeInUnixStyleAsString + adaptedPathToRefTypeInUnixStyle + ".class";
        final String protoInUnixStyle = pathToJavaRootSrcHomeInUnixStyleAsString + adaptedPathToRefTypeInUnixStyle;
        final String protoInDosStyle = protoInUnixStyle.replaceFirst( "/C:/", "C:/" ).replaceAll( "/", "\\\\" );
        pathToReferenceTypeSrcFileInUnixStyleAsString = protoInUnixStyle + ".java";
        pathToReferenceTypeSrcFileInDosStyleAsString = protoInDosStyle + ".java";
        //
        referenceTypeClassAsFile = new File( pathToReferenceTypeSrcFileInDosStyleAsString );
    }//constructor()
    
    
    
    @Override
    public String toString(){
        return String.format(
            "[<%s>: %s]",
            RefTypeInfoContainer.class.getSimpleName(),
            refTypeAsClass.getSimpleName()
        );
    }//method()
    
}//class
