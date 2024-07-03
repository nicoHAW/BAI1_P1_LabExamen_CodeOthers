// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package apitz_raffael.a3;


import static _untouchable_.supportC1x01.Configuration.dbgConfigurationVector;
import static _untouchable_.supportC1x01.PointDefinition.countsOnePoint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import _untouchable_.supportC1x01.ManualExceptionInvestigationRelatedAddOn;
import _untouchable_.supportC1x01.ManualTodoInvestigationRelatedAddOn;
import _untouchable_.supportC1x01.RefTypeInfoContainer;
import _untouchable_.supportC1x01.TC;
import _untouchable_.supportC1x01.TE;
import _untouchable_.supportC1x01.TL;
import _untouchable_.supportC1x01.TS;
import _untouchable_.supportC1x01.TestResult;
import _untouchable_.supportC1x01.TestResultDataBaseManager;
import _untouchable_.supportC1x01.TestSupportException;
import _untouchable_.supportC1x01.TestTopic;
import _untouchable_.supportGeneral.Herald;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


/**
 * LabExam1317_4XIB1-P1         (PTP-Eclipse-JUnit5)<br />
 * <br />
 * Diese Sammlung von Tests soll nur die Sicherheit vermitteln, dass Sie die Aufgabe richtig verstanden haben.
 * Dass von den Tests dieser Testsammlung keine Fehler gefunden wurden, kann nicht als Beweis dienen, dass Ihr Code fehlerfrei ist.
 * Es liegt in Ihrer Verantwortung sicherzustellen, dass Sie fehlerfreien Code geschrieben haben.
 * Bei der Bewertung werden u.U. andere - konkret : modifizierte und härtere Tests - verwendet.
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  LabExam1317_4XIB1-P1_231_v01
 */
@TestMethodOrder( MethodOrderer.MethodName.class )
public class TestFrameAndStarterC1x01 {
    
    // constant(s)
    
    // exercise related
    final static private TE exercise = TE.A3;                                   // <<<<===  _HERE_
    final static private String packagePath = getPackagePath();
    final static private String rootPackageName = getRootPackageName();
    
    final static private boolean enableAutomaticEvaluation  =  ( 0 > dbgConfigurationVector );
    
    final static private boolean dbgOutputEnable = false;
    
    
    
    // variable(s) - since the methods are static, the following variables have to be static
    
    // TestFrame "state"
    static private TestResultDataBaseManager  dbManager;
    
    
    
    //--------------------------------------------------------------------------
    //
    // support methods for setup computation of final static stuff
    //
    
    private static String getPackagePath(){
        return new Object(){}.getClass().getPackage().getName();
    }//method()
    
    private static String getRootPackageName(){
        final String packagePath = getPackagePath();
        final String[] packagePathPart = packagePath.split( "\\." );
        if( packagePathPart.length != 2 ){
            Herald.proclaimError( String.format(
                "Uuupps : This should NOT have happened\n"
              + "    Path: \"%s\" is NOT matching expectations\n"
              + "\n"
              + "This might be an CRITICAL ERROR resulting in unpredictable behavior\n",
                packagePath
            ));
        }//if
        if( packagePathPart.length >= 2 ){
            return packagePathPart[0];
        }else{
            return packagePath;
        }//if
    }//method
    
    
    private static String getPathOfBinFolderViaTF(){                            // TF ::= TestFrame-class
        return TestFrameAndStarterC1x01.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }//method()
    
    private static String getPathOfBinFolderViaTS(){                            // TS ::= TestSupport- resp. TS-class
        return TS.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }//method()
    
    
    
    
    
    
    
    
    
    
    /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * @BeforeAll
     * ============
     */
    @BeforeAll
    public static void runSetupBeforeAnyUnitTestStarts(){
        guaranteeExerciseConsistency( exercise.toString().toLowerCase(), packagePath );
        if( enableAutomaticEvaluation ){
            dbManager = new TestResultDataBaseManager( exercise, rootPackageName );
            TS.runTestSetupBeforeAnyUnitTestStarts( dbManager, exercise, rootPackageName );
        }//if
    }//method()
    //
    private static void guaranteeExerciseConsistency(
        final String  stringAsResultOfEnum,
        final String  stringAsResultOfPath
    ){
        // Macht das wirklich Sinn? - Koennte hier wirklich jemals was anbrennen ???
        // 1.) Sind Class-Dateien fuer Examinee-Code und _untouchable_ im gleichen bin-Folder?
        final String pathOfBinFolderViaTF = getPathOfBinFolderViaTF();
        final String pathOfBinFolderViaTS = getPathOfBinFolderViaTS();
        if( ! pathOfBinFolderViaTF.equals( pathOfBinFolderViaTS )){
            Herald.proclaimError( String.format(
                "Uuupps : This should NOT have happened\n"
              + "    This might indicate coming problems\n"
              + "    Have you already done \"%s\" in a proper way?\n"
              + "\n"
              + "CRITICAL ERROR :  (TS) %s <-> %s (TF)\n",
                exercise,
                pathOfBinFolderViaTS,
                pathOfBinFolderViaTF
            ));
        }//if
        //\=> _untouchable_ und Examinee-Code liegen im gleichen bin-Folder
        
        // 2.) Entspricht Folder mit Examinee-Source-Code den Erwartungen?
        if( ! stringAsResultOfPath.matches( "^[a-z]+" + "_" + "[a-z]+" + "\\." + stringAsResultOfEnum + "$" )) {
            Herald.proclaimError( String.format(
                "Uuupps : This should NOT have happened\n"
              + "    Path: \"%s\" is NOT matching expectations\n"
              + "    Have you done \"%s\" in a proper way?\n"
              + "\n"
              + "CRITICAL ERROR :  %s <-> %s\n",
                stringAsResultOfPath,
                exercise,
                stringAsResultOfEnum,
                stringAsResultOfPath
            ));
            throw new IllegalStateException( String.format(
                "Uuupps : this should NOT have happened - path to your solution is NOT matching expectations => CRITICAL ERROR :  %s : ???-->> %s <<--???",
                stringAsResultOfEnum,
                stringAsResultOfPath
            ));
        }//if
        //\=> Folder mit Examinee-Source-Code hat Aufbau: xxx_xxx.a1
    }//method()
    
    
    
    /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * @AfterAll
     * ===========
     */
    @AfterAll
    public static void runTearDownAfterAllUnitTestsHaveFinished(){
        if( enableAutomaticEvaluation ){
            TS.runTestTearDownAfterAllUnitTestsHaveFinished( dbManager, rootPackageName );          // <<<<==== the actual job is done here
            dbManager.reset();                                                                      //
        }//if
    }//method()
    
    
    
    
    
    
    
    
    
    
    //##########################################################################
    //##########################################################################
    //##########################################################################
    //###
    //###
    //###   START OF THE VERY TESTS
    //###   =======================
    //###
    
    
    
    
    
    
    
    
    
    
    //##########################################################################
    //###
    //###   A / "1e"
    //###
    
    // Note:
    // =====
    // No constructor was requested for FrequencyReporter.
    // Hence, no FrequencyReporter-object can be generated.
    
    /** Ausgabe auf Bildschirm zur visuellen Kontrolle */
    @Test
    public void tol_1e_printSupportForManualReview_CC(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        final boolean dbgLocalOutputEnable = ( 0 != ( dbgConfigurationVector & 0x0200 ));
        if( dbgLocalOutputEnable ){
            System.out.printf( "\n\n" );
            System.out.printf( "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv\n" );
            System.out.printf( "%s():\n",  testName );
            System.out.printf( "\n\n" );    
            
            // prepare test parameter
            final String someText = "abcde";                                                        // just...
            final List<Character> characterListAsTesztParameter = new ArrayList<Character>();       //...anything
            for( final char c : someText.toCharArray() )  characterListAsTesztParameter.add( c );   //...;-)
            //
            // actual test
            try{
                TS.printDetailedInfoAboutClass( requestedRefTypeWithPath );
                System.out.printf( "\n" );
                //
                @SuppressWarnings("unchecked")
                final ListAnalyzer_I<Character> listAnalyzer =
                    (ListAnalyzer_I<Character>)( TS.generateRequestedObject(
                            requestedRefTypeWithPath,
                        new Class<?>[]{ List.class },
                        new Object[]{ characterListAsTesztParameter }
                ));
                //\=> ListAnalyzer with some parameter was created
                TS.printDetailedInfoAboutObject( listAnalyzer, "ListAnalyzer" );
                //
                if( TS.isActualMethod( listAnalyzer.getClass(), "toString", String.class, null )){
                    System.out.printf( "~.toString(): \"%s\"     again ;-)\n",  listAnalyzer.toString() );
                }else{
                    System.out.printf( "NO! toString() implemented by class \"%s\" itself\n",  listAnalyzer.getClass().getSimpleName() );
                }//if
                
                System.out.printf( "\n\n" );
                System.out.printf( "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n" );
                System.out.printf( "\n\n" );
            }catch( final TestSupportException ex ){
                ex.printStackTrace();
                fail( ex.getMessage() );
            }finally{
                System.out.flush();
            }//try
        }//if
        
        // at least the unit test was NOT destroyed by student ;-)
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.DBGPRINT ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    /** Testing: "ListAnalyzer" */
    @Test
    public void tol_1e_classExistence_ListAnalyzer(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
            assertNotNull( classUnderTest );
            // NO crash yet => success ;-)
        }catch( final ClassNotFoundException ex ){
            fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.EXISTENCE ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    /** Testing: Eigenschaften des Referenz-Typs ListAnalyzer incl. Constructor */
    @Test
    public void tol_1e_properties_ListAnalyzer(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
            assertTrue( TS.isClass(             classUnderTest ),                                   String.format( "requested class %s missing", requestedRefTypeName ));
            assertTrue( TS.isClassPublic(       classUnderTest ),                                   "false class access modifier" );
            assertTrue( TS.isImplementing(      classUnderTest,   "ListAnalyzer_I" ),               "requested supertype missing" );
            assertTrue( TS.isConstructor(       classUnderTest,   new Class<?>[]{ List.class } ),   "requested constructor missing" );
            assertTrue( TS.isConstructorPublic( classUnderTest,   new Class<?>[]{ List.class } ),   "false constructor access modifier");
        }catch( final ClassNotFoundException ex ){
            fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: Eigenschaften "ListAnalyzer" - Access Modifier Methoden */
    @Test
    public void tol_1e_propertiesMethods_ListAnalyzer(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
            assertTrue( TS.isFunction(        classUnderTest, "computeFrequencyReporter", FrequencyReporter_I.class ), "requested method missing" );
            assertTrue( TS.isFunction(        classUnderTest, "getList",                  List.class ),                "requested method missing" );
            assertTrue( TS.isFunctionPublic(  classUnderTest, "computeFrequencyReporter", FrequencyReporter_I.class ), "false method access modifier" );
            assertTrue( TS.isFunctionPublic(  classUnderTest, "getList",                  List.class ),                "false method access modifier" );
        }catch( final ClassNotFoundException ex ){
            fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: Eigenschaften "ListAnalyzer" - Access Modifier Variablen */
    @Test
    public void tol_1e_propertiesFields_ListAnalyzer(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
            assertTrue( TS.allVariableFieldAccessModifiersPrivate( classUnderTest ), "false field access modifier" );
        }catch( final ClassNotFoundException ex ){
            fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /* Testing: Eigenschaften "ListAnalyzer" - Schreibweise Methoden */
    @Test
    public void tol_1e_notationMethods_ListAnalyzer(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
            final String faultExample = TS.hasInvalidMethodNotation( classUnderTest );
            if( null != faultExample ){
                fail( String.format( "method name: \"%s\" does NOT follow the VERY JAVA LAW !",  faultExample ));
            }//if
        }catch( final ClassNotFoundException ex ){
            fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: Eigenschaften "ListAnalyzer" - Schreibweise Variablen */
    @Test
    public void tol_1e_notationFields_ListAnalyzer(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
            final String faultExample = TS.hasInvalidFieldNotation( classUnderTest );
            if( null != faultExample ){
                fail( String.format( "field name: \"%s\" does NOT follow the VERY JAVA LAW !",  faultExample ));
            }//if
        }catch( final ClassNotFoundException ex ){
            fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    /** Testing: Kann überhaupt ein Objekt erzeugt werden? - "ListAnalyzer erzeugen" (With Parameter) */
    @Test
    public void tol_1e_objectCreationWP_ListAnalyzer(){                        // WP ::= With Parameter
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // prepare test parameter
        final String someText = "abcde";
        final List<Character> characterListAsTestParameter = new ArrayList<Character>();
        for( final char c : someText.toCharArray() )  characterListAsTestParameter.add( c );
        //
        // actual test
        try{
            @SuppressWarnings("unchecked")
            final ListAnalyzer_I<Character> listAnalyzer =
                (ListAnalyzer_I<Character>)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ List.class },
                    new Object[]{ characterListAsTestParameter }
                ));
            assertNotNull( listAnalyzer );
            //\=> ListAnalyzer with some parameter was created
            // NO crash yet => success ;-)
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.CREATION ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    /** Testing: (NUR-)Methoden-Aufruf "computeFrequencyReporter()" */
    @Test
    public void tol_1e_behavior_computeFrequencyReporter(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // prepare test parameter
        final String text = "abcde";
        final List<Character> characterList = new ArrayList<Character>();
        for( final char c : text.toCharArray() )  characterList.add( c );
        
        // actual test
        try{
            @SuppressWarnings("unchecked")
            final ListAnalyzer_I<Character> listAnalyzer =
                (ListAnalyzer_I<Character>)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ List.class },
                    new Object[]{ characterList }
                ));
            assertNotNull( listAnalyzer );
            //\=> ListAnalyzer with some parameter was created
            final FrequencyReporter_I<Character> anyValue = listAnalyzer.computeFrequencyReporter();
            TS.bo( anyValue );
            // NO crash yet => success ;-)
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: (NUR-)Methoden-Aufruf "getList()" */
    @Test
    public void tol_1e_behavior_getCurrentArray(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // prepare test parameter
        final String text = "abcde";
        final List<Character> characterList = new ArrayList<Character>();
        for( final char c : text.toCharArray() )  characterList.add( c );
        
        // actual test
        try{
            @SuppressWarnings("unchecked")
            final ListAnalyzer_I<Character> listAnalyzer =
                (ListAnalyzer_I<Character>)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ List.class },
                    new Object[]{ characterList }
                ));
            //\=> ListAnalyzer with some parameter was created
            final List<Character> anyValue = listAnalyzer.getList();
            assertNotNull( anyValue );
            // NO crash yet => success ;-)
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    
    
    
    
    
    
    
    //##########################################################################
    //###
    //###   B / "2b"
    //###
    
    /** Testing: (NUR-)Sequenz-Methoden-Aufruf: "alle ;-)" */
    @Test
    public void tol_2b_behavior_sequencePureMethodCall_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // prepare test parameter
        final String text = "abc";
        final List<Character> characterList = new ArrayList<Character>();
        for( final char c : text.toCharArray() )  characterList.add( c );
        
        // actual test
        try{
            @SuppressWarnings("unchecked")
            final ListAnalyzer_I<Character> listAnalyzer =
                (ListAnalyzer_I<Character>)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ List.class },
                    new Object[]{ characterList }
                ));
            //\=> ListAnalyzer with some parameter was created
            //
            List<Character> anyListValue = listAnalyzer.getList();
            int optimizationBlocker = (null==anyListValue) ? 0 : anyListValue.size();
            //
            final FrequencyReporter_I<Character> anyFrequencyReporter = listAnalyzer.computeFrequencyReporter();
            int anyIntValue = anyFrequencyReporter.getFrequency( 'b' );
            optimizationBlocker ^= anyIntValue;
            //
            anyIntValue = anyFrequencyReporter.getAmountOfUniqueItems();
            optimizationBlocker ^= anyIntValue;
            //
            final Set<Character> anySetValue = anyFrequencyReporter.knownElements();
            optimizationBlocker *= (null==anySetValue) ? -1 : +1;
            //
            anyListValue = listAnalyzer.getList();
            optimizationBlocker *= (null==anyListValue) ? -1 : +1;
            // NO crash yet => success ;-)
            //
            final int otherValue = optimizationBlocker ^ 0xFEDCBA98;                        // blocking...
            assert otherValue != optimizationBlocker : "error when blocking optimizations"; //...optimizations
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: getList()" */
    @Test
    public void tol_2b_behavior_getList_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // prepare test parameter
        final String text = "abc";
        final List<Character> characterList = new ArrayList<Character>();
        for( final char c : text.toCharArray() )  characterList.add( c );
        
        // actual test
        try{
            @SuppressWarnings("unchecked")
            final ListAnalyzer_I<Character> listAnalyzer =
                (ListAnalyzer_I<Character>)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ List.class },
                    new Object[]{ characterList }
                ));
            //\=> ListAnalyzer with some parameter was created
            //
            final List<Character> computedResult = listAnalyzer.getList();
            assertTrue( characterList.equals( computedResult ));
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: computeFrequencyReporter()" */
    @Test
    public void tol_2b_behavior_computeFrequencyReporter_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // prepare test parameter
        final String text = "abc";
        final List<Character> characterList = new ArrayList<Character>();
        for( final char c : text.toCharArray() )  characterList.add( c );
        
        // actual test
        try{
            @SuppressWarnings("unchecked")
            final ListAnalyzer_I<Character> listAnalyzer =
                (ListAnalyzer_I<Character>)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ List.class },
                    new Object[]{ characterList }
                ));
            assertNotNull( listAnalyzer );
            //\=> ListAnalyzer with some parameter was created
            //
            final FrequencyReporter_I<Character> anyFrequencyReporter = listAnalyzer.computeFrequencyReporter();
            TS.bo( anyFrequencyReporter );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: computeFrequencyReporter() & getfrequency()" */
    @Test
    public void tol_2b_behavior_getfrequency_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // prepare test parameter
        final String text = "abc";
        final List<Character> characterList = new ArrayList<Character>();
        for( final char c : text.toCharArray() )  characterList.add( c );
        
        // actual test
        try{
            @SuppressWarnings("unchecked")
            final ListAnalyzer_I<Character> listAnalyzer =
                (ListAnalyzer_I<Character>)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ List.class },
                    new Object[]{ characterList }
                ));
            //\=> ListAnalyzer with some parameter was created
            //
            final FrequencyReporter_I<Character> frequencyReporter = listAnalyzer.computeFrequencyReporter();
            assertNotNull( frequencyReporter );
            //
            assertEquals( 1, frequencyReporter.getFrequency( 'a' ));
            assertEquals( 1, frequencyReporter.getFrequency( 'b' ));
            assertEquals( 1, frequencyReporter.getFrequency( 'c' ));
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: computeFrequencyReporter() & getAmountOfUniqueItems()" */
    @Test
    public void tol_2b_behavior_getAmountOfUniqueItems_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // prepare test parameter
        final String text = "abc";
        final List<Character> characterList = new ArrayList<Character>();
        for( final char c : text.toCharArray() )  characterList.add( c );
        
        // actual test
        try{
            @SuppressWarnings("unchecked")
            final ListAnalyzer_I<Character> listAnalyzer =
                (ListAnalyzer_I<Character>)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ List.class },
                    new Object[]{ characterList }
                ));
            //\=> ListAnalyzer with some parameter was created
            //
            final FrequencyReporter_I<Character> frequencyReporter = listAnalyzer.computeFrequencyReporter();
            assertNotNull( frequencyReporter );
            //
            assertEquals( 3, frequencyReporter.getAmountOfUniqueItems());
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: computeFrequencyReporter() & knownElements())" */
    @Test
    public void tol_2b_behavior_knownElements_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // prepare test parameter
        final String text = "abc";
        final List<Character> characterList = new ArrayList<Character>();
        for( final char c : text.toCharArray() )  characterList.add( c );
        
        // actual test
        try{
            @SuppressWarnings("unchecked")
            final ListAnalyzer_I<Character> listAnalyzer =
                (ListAnalyzer_I<Character>)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ List.class },
                    new Object[]{ characterList }
                ));
            //\=> ListAnalyzer with some parameter was created
            //
            final FrequencyReporter_I<Character> frequencyReporter = listAnalyzer.computeFrequencyReporter();
            assertNotNull( frequencyReporter );
            //
            final Set<Character> expectedResult = new HashSet<Character>();
            for( final Character elem : new Character[]{ 'a', 'b', 'c' } )  expectedResult.add( elem );
            final Set<Character> computedResult = frequencyReporter.knownElements();
            assertEquals( expectedResult, computedResult );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    
    
    
    
    
    
    
    //##########################################################################
    //###
    //###   C / "3n"
    //###
    
    /** Testing: computeFrequencyReporter -> getfrequency" */
    @Test
    public void tol_3n_behavior_getfrequency_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // prepare test parameter
        final String text = "abbcccddddeeeee";
        final List<Character> characterList = new ArrayList<Character>();
        for( final char c : text.toCharArray() )  characterList.add( c );
        
        // actual test
        try{
            @SuppressWarnings("unchecked")
            final ListAnalyzer_I<Character> listAnalyzer =
                (ListAnalyzer_I<Character>)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ List.class },
                    new Object[]{ characterList }
                ));
            //\=> ListAnalyzer with some parameter was created
            //
            final FrequencyReporter_I<Character> computedFrequencyReporter = listAnalyzer.computeFrequencyReporter();
            for( int i=0; i<5; i++ ){
                final char listElem = (char)( 'a' + i );
                final int computedValue = computedFrequencyReporter.getFrequency( listElem );
                assertEquals( i+1, computedValue );
            }//for
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: computeFrequencyReporter -> getfrequency" */
    @Test
    public void tol_3n_behavior_getfrequency_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        for( final String text : new String[]{
            "qsrqrssqoprrssp",
            "rsqqqoppsrsrsrs",
            "sssssrrrrqqqppo",
            "oppqqqrrrrsssss"
        }){
            // prepare test parameter
            final List<Character> characterList = new ArrayList<Character>();
            for( final char c : text.toCharArray() )  characterList.add( c );
            
            // actual test
            try{
                @SuppressWarnings("unchecked")
                final ListAnalyzer_I<Character> listAnalyzer =
                    (ListAnalyzer_I<Character>)( TS.generateRequestedObject(
                        requestedRefTypeWithPath,
                        new Class<?>[]{ List.class },
                        new Object[]{ characterList }
                    ));
                //\=> ListAnalyzer with some parameter was created
                //
                final FrequencyReporter_I<Character> computedFrequencyReporter = listAnalyzer.computeFrequencyReporter();
                for( int i=1; i<=5; i++ ){
                    final char listElem = (char)( 'n' + i );
                    final int computedValue = computedFrequencyReporter.getFrequency( listElem );
                    assertEquals( i, computedValue );
                }//for
            }catch( final TestSupportException ex ){
                fail( ex.getMessage() );
            }//try
        }//for
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Testing: computeFrequencyReporter -> getAmountOfUniqueItems" */
    @Test
    public void tol_3n_behavior_getAmountOfUniqueItems_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // prepare test parameter
        final String text = "abbcccddddeeeee";
        final List<Character> characterList = new ArrayList<Character>();
        for( final char c : text.toCharArray() )  characterList.add( c );
        
        // actual test
        try{
            @SuppressWarnings("unchecked")
            final ListAnalyzer_I<Character> listAnalyzer =
                (ListAnalyzer_I<Character>)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ List.class },
                    new Object[]{ characterList }
                ));
            //\=> ListAnalyzer with some parameter was created
            //
            final FrequencyReporter_I<Character> computedFrequencyReporter = listAnalyzer.computeFrequencyReporter();
            assertNotNull( computedFrequencyReporter );
            final int computedValue = computedFrequencyReporter.getAmountOfUniqueItems();
            assertEquals( 5, computedValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: computeFrequencyReporter -> getAmountOfUniqueItems" */
    @Test
    public void tol_3n_behavior_getAmountOfUniqueItems_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        for( final String text : new String[]{
            "abcde",
            "qsrqrssqoprrssp",
            "rsqqqoppsrsrsrs",
            "sssssrrrrqqqppo",
            "oppqqqrrrrsssss",
            "xxoxxxfxxxxxxxxkkkkkmk",
            "xyzxyzzzzzzzzqxpxxxxxxxxxqpxqpxxxxxyxz"
        }){
            // prepare test parameter
            final List<Character> characterList = new ArrayList<Character>();
            for( final char c : text.toCharArray() )  characterList.add( c );
            
            // actual test
            try{
                @SuppressWarnings("unchecked")
                final ListAnalyzer_I<Character> listAnalyzer =
                    (ListAnalyzer_I<Character>)( TS.generateRequestedObject(
                        requestedRefTypeWithPath,
                        new Class<?>[]{ List.class },
                        new Object[]{ characterList }
                    ));
                //\=> ListAnalyzer with some parameter was created
                //
                final FrequencyReporter_I<Character> computedFrequencyReporter = listAnalyzer.computeFrequencyReporter();
                assertNotNull( computedFrequencyReporter );
                final int computedValue = computedFrequencyReporter.getAmountOfUniqueItems();
                assertEquals( 5, computedValue );
            }catch( final TestSupportException ex ){
                fail( ex.getMessage() );
            }//try
        }//for
            
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Testing: computeFrequencyReporter -> knownElements" */
    @Test
    public void tol_3n_behavior_knownElements_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // prepare test parameter
        final String text = "qp";
        final List<Character> characterList = new ArrayList<Character>();
        for( final char c : text.toCharArray() )  characterList.add( c );
        
        // actual test
        try{
            @SuppressWarnings("unchecked")
            final ListAnalyzer_I<Character> listAnalyzer =
                (ListAnalyzer_I<Character>)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ List.class },
                    new Object[]{ characterList }
                ));
            //\=> ListAnalyzer with some parameter was created
            //
            final FrequencyReporter_I<Character> computedFrequencyReporter = listAnalyzer.computeFrequencyReporter();
            assertNotNull( computedFrequencyReporter );
            final Set<Character> computedSet = computedFrequencyReporter.knownElements();
            assertNotNull( computedSet );
            //
            final String knownElements = "pq";
            final Set<Character> expectedSet = new HashSet<Character>();
            for( final char c : knownElements.toCharArray() )  expectedSet.add( c );
            //
            assertEquals( expectedSet, computedSet );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: computeFrequencyReporter -> knownElements" */
    @Test
    public void tol_3n_behavior_knownElements_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // prepare test parameter
        final String text = "abbcccddddeeeee";
        final List<Character> characterList = new ArrayList<Character>();
        for( final char c : text.toCharArray() )  characterList.add( c );
        
        // actual test
        try{
            @SuppressWarnings("unchecked")
            final ListAnalyzer_I<Character> listAnalyzer =
                (ListAnalyzer_I<Character>)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ List.class },
                    new Object[]{ characterList }
                ));
            //\=> ListAnalyzer with some parameter was created
            //
            final FrequencyReporter_I<Character> computedFrequencyReporter = listAnalyzer.computeFrequencyReporter();
            assertNotNull( computedFrequencyReporter );
            final Set<Character> computedSet = computedFrequencyReporter.knownElements();
            assertNotNull( computedSet );
            //
            final String knownElements = "abcde";
            final Set<Character> expectedSet = new HashSet<Character>();
            for( final char c : knownElements.toCharArray() )  expectedSet.add( c );
            //
            assertEquals( expectedSet, computedSet );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Testing: getList" */
    @Test
    public void tol_3n_behavior_getList_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // prepare test parameter
        final String text = "qp";
        final List<Character> expectedList = new ArrayList<Character>();
        for( final char c : text.toCharArray() )  expectedList.add( c );
        
        // actual test
        try{
            @SuppressWarnings("unchecked")
            final ListAnalyzer_I<Character> listAnalyzer =
                (ListAnalyzer_I<Character>)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ List.class },
                    new Object[]{ expectedList }
                ));
            //\=> ListAnalyzer with some parameter was created
            //
            List<Character> computedList = listAnalyzer.getList();
            assertEquals( expectedList, computedList );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: getList (&combined)" */
    @Test
    public void tol_3n_behavior_getList_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // prepare test parameter
        final String text = "xyzxyzzzzzzzzqxpxxxxxxxxxqpxqpxxxxxyxz";
        final List<Character> expectedList = new ArrayList<Character>();
        for( final char c : text.toCharArray() )  expectedList.add( c );
        
        // actual test
        try{
            @SuppressWarnings("unchecked")
            final ListAnalyzer_I<Character> listAnalyzer =
                (ListAnalyzer_I<Character>)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ List.class },
                    new Object[]{ expectedList }
                ));
            //\=> ListAnalyzer with some parameter was created
            //
            List<Character> computedList = listAnalyzer.getList();
            assertEquals( expectedList, computedList );
            //
            //
            final FrequencyReporter_I<Character> someFrequencyReporter = listAnalyzer.computeFrequencyReporter();
            assertNotNull( someFrequencyReporter );
            //
            int cnt=0;                                                          // variable cnt, since test shall give no/minimum solution hint
            final Set<Character> someSet = someFrequencyReporter.knownElements();
            for( final Character someKey : someSet ){
                final int someValue = someFrequencyReporter.getFrequency(someKey);
                assertTrue( 0 < someValue );
                cnt++;
            }//for
            //
            final int someValue = someFrequencyReporter.getAmountOfUniqueItems();
            assertEquals( cnt, someValue,  "value inconsistency" );
            //
            //
            computedList = listAnalyzer.getList();
            assertEquals( expectedList, computedList );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    
    
    
    
    
    
    //##########################################################################
    //###
    //###   D / "4s"
    //###
    
    /** Check for TODOs inside Examinee-Code marking code NOT ready */
    @Test
    public void tol_4s_containsSuspiciousTodoMarks(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        for( final String requestedRefTypeName : new String[]{ "ListAnalyzer", "FrequencyReporter" } ){
            final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
            final RefTypeInfoContainer refTypeInfoContainer = generateRefTypeInfoContainer( requestedRefTypeName );
            
            if( requestedRefTypeName.equals( "ListAnalyzer" )){
                // guard test
                doGeneralGuardTest( testName, requestedRefTypeWithPath );
            }//if
            
            // start of actual test
            if( TS.containsSuspiciousToDoMarks( testName, refTypeInfoContainer, ManualTodoInvestigationRelatedAddOn.NONE ) ){
                fail( "Source code contains TODO marks indicating code is NOT ready" );
            }//if
        }//for
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** special case: null */
    @Test
    public void tol_4s_behavior_parameter_null_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // guard test
        doGeneralGuardTest( testName, requestedRefTypeWithPath );
        
        // prepare test parameter
        final List<Byte> testParameter = null;
        
        // actual test
        boolean expectedExceptionOccured = false;
        try{
            @SuppressWarnings("unchecked")
            final ListAnalyzer_I<Byte> listAnalyzer =
                (ListAnalyzer_I<Byte>)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ List.class },
                    new Object[]{ testParameter }
                ));
            //\=> ListAnalyzer with some parameter was created
            fail( "undetected illegal argument -> null accepted" );
            assertNotNull( listAnalyzer,  "false anyway - optimization blocker" );
            //
        }catch( final IllegalArgumentException | AssertionError ex ){           // for further investigation - NOT expected
            expectedExceptionOccured = true;
            if( dbgOutputEnable ){
                Herald.proclaimError( "??? tol_4s_behavior_parameter_null_no1  AE/IAE\n" );
                Herald.proclaimError( String.format( "%s\n",   ex.getMessage() ));
            }//if
        }catch( final TestSupportException ex ){                                // for further investigation - Expected
            TS.examineInternallyRaisedTestSupportExceptionForIllegalArgumentCauseAndReact( testName, ex, ManualExceptionInvestigationRelatedAddOn.NONE );   /* ??? HERE */
            expectedExceptionOccured = true;
            if( dbgOutputEnable ){
                Herald.proclaimError( "??? tol_4s_behavior_parameter_null_no1  TSE\n" );
                Herald.proclaimError( String.format( "%s\n",   ex.getMessage() ));
            }//if
        }catch( final Throwable ex ){
            fail( String.format("%s ->  %s",  ex.getClass().getSimpleName(), ex.getMessage() ));
        }//try
        assertTrue( expectedExceptionOccured,  "undetected illegal argument -> null accepted" );
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** special case: null */
    @Test
    public void tol_4s_behavior_parameter_null_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // guard test
        doGeneralGuardTest( testName, requestedRefTypeWithPath );
        
        // test
        for( final Integer[] ia : new Integer[][]{
            {1,2,null},
            {1,null,3},
            {null,2,3},
            {1,null,null},
            {null,2,null},
            {null,null,3},
            {null,null,null}
        }){
            // prepare test parameter
            final List<Integer> testParameter = new ArrayList<Integer>();
            for( final Integer i : ia )  testParameter.add( i );
            
            // actual test
            boolean expectedExceptionOccured = false;
            try{
                @SuppressWarnings("unchecked")
                final ListAnalyzer_I<Byte> listAnalyzer =
                    (ListAnalyzer_I<Byte>)( TS.generateRequestedObject(
                        requestedRefTypeWithPath,
                        new Class<?>[]{ List.class },
                        new Object[]{ testParameter }
                    ));
                //\=> ListAnalyzer with some parameter was created
                fail( "undetected illegal argument -> null accepted" );
                assertNotNull( listAnalyzer,  "false anyway - optimization blocker" );
                //
            }catch( final TestSupportException ex ){                            // for further investigation - Expected
                TS.examineInternallyRaisedTestSupportExceptionForIllegalArgumentCauseAndReact( testName, ex, ManualExceptionInvestigationRelatedAddOn.NONE );   // ??? HERE
                expectedExceptionOccured = true;
            }catch( final Throwable ex ){
                fail( String.format("%s ->  %s",  ex.getClass().getSimpleName(), ex.getMessage() ));
            }//try
            assertTrue( expectedExceptionOccured,  "undetected illegal argument -> null accepted" );
        }//for
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** special case: empty list */
    @Test
    public void tol_4s_behavior_emptyInputList_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // guard test
        doGeneralGuardTest( testName, requestedRefTypeWithPath );
        
        // prepare test parameter
        final List<Byte> testParameter = new ArrayList<Byte>();
        
        // actual test
        try{
            @SuppressWarnings("unchecked")
            final ListAnalyzer_I<Byte> listAnalyzer =
                (ListAnalyzer_I<Byte>)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ List.class },
                    new Object[]{ testParameter }
                ));
            //\=> ListAnalyzer with some parameter was created
            //
            List<Byte> computedList = listAnalyzer.getList();
            assertEquals( testParameter, computedList );
            //
            //
            final FrequencyReporter_I<Byte> someFrequencyReporter = listAnalyzer.computeFrequencyReporter();
            assertNotNull( someFrequencyReporter );
            //
            int cnt=0;                                                          // variable cnt, since test shall give no/minimum solution hint
            final Set<Byte> someSet = someFrequencyReporter.knownElements();
            for( final Byte someKey : someSet ){
                final int someValue = someFrequencyReporter.getFrequency( someKey );
                assertEquals( 0, someValue );
                cnt++;
            }//for
            assertEquals( 0, cnt );
            //
            for( short sKey=Byte.MIN_VALUE; sKey<=Byte.MAX_VALUE; sKey++ ){
                assertEquals( 0, someFrequencyReporter.getFrequency( (byte)( sKey )));
            }//for
            //
            final int someValue = someFrequencyReporter.getAmountOfUniqueItems();
            assertEquals( cnt, someValue,  "value inconsistency" );
            //
            
            //
            computedList = listAnalyzer.getList();
            assertEquals( testParameter, computedList );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** special case: 42 */
    @Test
    public void tol_4s_behavior_42_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // guard test
        doGeneralGuardTest( testName, requestedRefTypeWithPath );
        
        // prepare test parameter
        final List<Long> testParameter = new ArrayList<Long>();
        testParameter.add( 42L );
        
        // actual test
        try{
            @SuppressWarnings("unchecked")
            final ListAnalyzer_I<Long> listAnalyzer =
                (ListAnalyzer_I<Long>)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ List.class },
                    new Object[]{ testParameter }
                ));
            //\=> ListAnalyzer with some parameter was created
            //
            List<Long> computedList = listAnalyzer.getList();
            assertEquals( testParameter, computedList );
            //
            //
            final FrequencyReporter_I<Long> someFrequencyReporter = listAnalyzer.computeFrequencyReporter();
            assertNotNull( someFrequencyReporter );
            //
            int cnt=0;                                                          // variable cnt, since test shall give no/minimum solution hint
            final Set<Long> someSet = someFrequencyReporter.knownElements();
            for( final Long someKey : someSet ){
                final int someValue = someFrequencyReporter.getFrequency( someKey );
                assertEquals( 1, someValue );
                assertEquals( 42, someKey );
                cnt++;
            }//for
            assertEquals( 1, cnt );
            //
            final int someValue = someFrequencyReporter.getAmountOfUniqueItems();
            assertEquals( cnt, someValue,  "value inconsistency" );
            //
            computedList = listAnalyzer.getList();
            assertEquals( testParameter, computedList );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** multiple result reporter */
    @Test
    public void tol_4s_behavior_multipleResultReporter_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ListAnalyzer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // guard test
        doGeneralGuardTest( testName, requestedRefTypeWithPath );
        
        // prepare test parameter
        final int cnt = 9; 
        final List<Byte> testParameter = new ArrayList<Byte>();
        for( byte b=1; b<=cnt; b++ ){
            for( int i=0; i<b; i++ ){
                testParameter.add( b );
            }//for
        }//for
        
        // actual test
        try{
            @SuppressWarnings("unchecked")
            final ListAnalyzer_I<Byte> listAnalyzer =
                (ListAnalyzer_I<Byte>)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ List.class },
                    new Object[]{ testParameter }
                ));
            //\=> ListAnalyzer with some parameter was created
            //
            List<Byte> computedList = listAnalyzer.getList();
            assertEquals( testParameter, computedList );
            //
            //
            // generate and check first frequencyReporter
            final List<FrequencyReporter_I<Byte>> frequencyReporter = new ArrayList<FrequencyReporter_I<Byte>>();
            final FrequencyReporter_I<Byte> theFrequencyReporter = listAnalyzer.computeFrequencyReporter();
            assertNotNull( theFrequencyReporter );
            frequencyReporter.add( theFrequencyReporter );
            //
            int someValue = theFrequencyReporter.getAmountOfUniqueItems();
            assertEquals( cnt, someValue,  "value inconsistency" );
            //
            Set<Byte> someSet = theFrequencyReporter.knownElements();
            for( final Byte someKey : someSet ){
                someValue = theFrequencyReporter.getFrequency( someKey );
                final int amount = someKey;                                     // consequence of testparamter  generation
                assertEquals( amount, someValue );
            }//for
            //
            //
            // generate and check further frequencyReporter
            for( int i=1; i<10; i++ ){
                final FrequencyReporter_I<Byte> someFrequencyReporter = listAnalyzer.computeFrequencyReporter();
                assertNotNull( someFrequencyReporter );
                frequencyReporter.add( someFrequencyReporter );
            }//for
            for( int stillToDo=7; --stillToDo>=0; ){
                for( int i=1; i<10; i++ ){
                    someValue = frequencyReporter.get(i).getAmountOfUniqueItems();
                    assertEquals( cnt, someValue,  "value inconsistency" );
                    assertEquals( cnt, theFrequencyReporter.getAmountOfUniqueItems(),  "value inconsistency" );
                    //
                    someSet = frequencyReporter.get(i).knownElements();
                    for( final Byte someKey : someSet ){
                        someValue = frequencyReporter.get(i).getFrequency( someKey );
                        final int amount = someKey;                                 // consequence of testparamter  generation
                        assertEquals( amount, someValue );
                        assertEquals( amount, theFrequencyReporter.getFrequency( someKey ) );
                    }//for
                    assertEquals( cnt, someSet.size(),  "set is NOT of expected size" );
                }//for
            }//for
            //
            computedList = listAnalyzer.getList();
            assertEquals( testParameter, computedList );
            //
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    
    
    
    
    
    
    
    //##########################################################################
    //###
    //###   internal / local test support
    //###
    
    private void doGeneralGuardTest (
        final String testName,
        final String requestedRefTypeWithPath
    ){
        try{
            // test uses MULTIPLE OBJECTs of ItemProcessor on purpose
            /*scope*/{
                final int objectCnt = 3;
                
                // prepare test parameter
                final String text[] = new String[objectCnt];
                @SuppressWarnings("unchecked")
                final List<Character>[] originalList = (List<Character>[])( new List[objectCnt]);
                for( int objId=0; objId<objectCnt; objId++ ){
                    text[objId] = "qx" + (char)('a' + objId) + "xp";
                    originalList[objId] = new ArrayList<Character>();
                    for( final char c : text[objId].toCharArray() )  originalList[objId].add( c );
                }//for
                
                // create ListAnalyzer and FrequencyCounter
                final List<ListAnalyzer_I<Character>> objList = new ArrayList<ListAnalyzer_I<Character>>();
                final List<FrequencyReporter_I<Character>> freqRepList = new ArrayList<FrequencyReporter_I<Character>>();
                for( int objId=0; objId<objectCnt; objId++ ){
                    @SuppressWarnings("unchecked")
                    final ListAnalyzer_I<Character> listAnalyzer =
                        (ListAnalyzer_I<Character>)( TS.generateRequestedObject(
                            requestedRefTypeWithPath,
                            new Class<?>[]{ List.class },
                            new Object[]{ originalList[objId] }
                        ));
                    objList.add( listAnalyzer );
                    freqRepList.add( listAnalyzer.computeFrequencyReporter() );
                }//for
                //\=> objects created
                
                // check ListAnalyzer
                for( int objId=0; objId<objectCnt; objId++ ){
                    final ListAnalyzer_I<Character> currentListAnalyzer = objList.get( objId );
                    assertEquals( originalList[objId], currentListAnalyzer.getList() );
                }//for
                
                // check FrequencyCounter
                for( int objId=0; objId<objectCnt; objId++ ){
                    final ListAnalyzer_I<Character> currentListAnalyzer = objList.get( objId );
                    final FrequencyReporter_I<Character> currentFreqRep = currentListAnalyzer.computeFrequencyReporter();
                    //
                    assertEquals( 4, currentFreqRep.getAmountOfUniqueItems() );
                    //
                    assertEquals( 1, currentFreqRep.getFrequency( 'p' ));
                    assertEquals( 1, currentFreqRep.getFrequency( 'q' ));
                    assertEquals( 2, currentFreqRep.getFrequency( 'x' ));
                    assertEquals( 1, currentFreqRep.getFrequency( (char)('a' + objId) ));
                    //
                    final String knownElements = "qx" + (char)('a' + objId) + "xp";
                    final Set<Character> expectedSet = new HashSet<Character>();
                    for( final char c : knownElements.toCharArray() )  expectedSet.add( c );
                    final Set<Character> computedSet = currentFreqRep.knownElements();
                    assertEquals( expectedSet, computedSet );
                }//for
            }//scope
        }catch( final Exception ex ){
            fail( String.format( "[Guard test failed] -->> %s <<--",  ex.getMessage() ));
        }//try
    }//method()
    
    
    
    private RefTypeInfoContainer generateRefTypeInfoContainer(
        final String requestedRefTypeName
    ){
        // just for safety's sake
        final String pathToBinFolderV1TF = getPathOfBinFolderViaTF();
        final String pathToBinFolderV2TS = getPathOfBinFolderViaTS();
        assert pathToBinFolderV1TF.equals(pathToBinFolderV2TS) : String.format( // already done in guaranteeExerciseConsistency(), but for safety's sake
            "Uuuupppss: generateRefTypeInfoContainer() -->>  TF:%s differs from TS:$s",
            pathToBinFolderV1TF,
            pathToBinFolderV2TS
        );
        
        RefTypeInfoContainer refTypeInfoContainer = null;
        try {
            refTypeInfoContainer = new RefTypeInfoContainer(
                requestedRefTypeName,
                packagePath,
                pathToBinFolderV1TF
            );
        }catch( final ClassNotFoundException ex ){
            fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));    // student has NOT implemented proper class file yet
        }//try
        
        return refTypeInfoContainer;
    }//method()
    
}//class
