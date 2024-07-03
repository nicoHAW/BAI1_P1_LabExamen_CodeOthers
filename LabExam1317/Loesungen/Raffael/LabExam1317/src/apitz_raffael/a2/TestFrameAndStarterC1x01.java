// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package apitz_raffael.a2;


import static _untouchable_.supportC1x01.Configuration.dbgConfigurationVector;
import static _untouchable_.supportC1x01.PointDefinition.countsOnePoint;
import static _untouchable_.supportC1x01.PointDefinition.countsTwoPoints;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
    final static private TE exercise = TE.A2;                                   // <<<<===  _HERE_
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
    
    /** Ausgabe auf Bildschirm zur visuellen Kontrolle */
    @Test
    public void tol_1e_printSupportForManualReview_CC(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        final boolean dbgLocalOutputEnable = ( 0 != ( dbgConfigurationVector & 0x0200 ));
        if( dbgLocalOutputEnable ){
            System.out.printf( "\n\n" );
            System.out.printf( "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv\n" );
            System.out.printf( "%s():\n",  testName );
            System.out.printf( "\n\n" );    
            
            final long[][] someTestConstructorParameter = { {1,2}, {3,4} };  // anything ;-)
            try{
                TS.printDetailedInfoAboutClass( requestedRefTypeWithPath );
                System.out.printf( "\n" );
                //
                final ArrayProcessor_I arrayProcessor = (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ someTestConstructorParameter }
                ));
                //\=> ArrayProcessor with some parameter was created
                TS.printDetailedInfoAboutObject( arrayProcessor, "ArrayProcessor" );
                //
                if( TS.isActualMethod( arrayProcessor.getClass(), "toString", String.class, null )){
                    System.out.printf( "~.toString(): \"%s\"     again ;-)\n",  arrayProcessor.toString() );
                }else{
                    System.out.printf( "NO! toString() implemented by class \"%s\" itself\n",  arrayProcessor.getClass().getSimpleName() );
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
    
    /** Testing: "ArrayProcessor" */
    @Test
    public void tol_1e_classExistence_ArrayProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
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
    
    /** Testing: Eigenschaften des Referenz-Typs ArrayProcessor incl. Constructor */
    @Test
    public void tol_1e_properties_ArrayProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
            assertTrue( TS.isClass(             classUnderTest ),                                       String.format( "requested class %s missing", requestedRefTypeName ));
            assertTrue( TS.isClassPublic(       classUnderTest ),                                       "false class access modifier" );
            assertTrue( TS.isImplementing(      classUnderTest,   "ArrayProcessor_I" ),                 "requested supertype missing" );
            assertTrue( TS.isConstructor(       classUnderTest,   new Class<?>[]{ long[][].class } ),   "requested constructor missing" );
            assertTrue( TS.isConstructorPublic( classUnderTest,   new Class<?>[]{ long[][].class } ),   "false constructor access modifier" );
        }catch( final ClassNotFoundException ex ){
            fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: Eigenschaften "ArrayProcessor" - Access Modifier Methoden */
    @Test
    public void tol_1e_propertiesMethods_ArrayProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
            assertTrue( TS.isFunction(        classUnderTest, "getOriginalArray",    long[][].class ), "requested method missing" );
            assertTrue( TS.isFunction(        classUnderTest, "getCurrentArray",     long[][].class ), "requested method missing" );
            assertTrue( TS.isProcedure(       classUnderTest, "expand1stDimBySum" ),                   "requested method missing" );
            assertTrue( TS.isProcedure(       classUnderTest, "expand2ndDimBySum" ),                   "requested method missing" );
            assertTrue( TS.isFunctionPublic(  classUnderTest, "getOriginalArray",    long[][].class ), "false method access modifier" );
            assertTrue( TS.isFunctionPublic(  classUnderTest, "getCurrentArray",     long[][].class ), "false method access modifier" );
            assertTrue( TS.isProcedurePublic( classUnderTest, "expand1stDimBySum" ),                   "false method access modifier" );
            assertTrue( TS.isProcedurePublic( classUnderTest, "expand2ndDimBySum" ),                   "false method access modifier" );
        }catch( final ClassNotFoundException ex ){
            fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: Eigenschaften "ArrayProcessor" - Access Modifier Variablen */
    @Test
    public void tol_1e_propertiesFields_ArrayProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
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
    
    /* Testing: Eigenschaften "ArrayProcessor" - Schreibweise Methoden */
    @Test
    public void tol_1e_notationMethods_ArrayProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
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
    
    /** Testing: Eigenschaften "ArrayProcessor" - Schreibweise Variablen */
    @Test
    public void tol_1e_notationFields_ArrayProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
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
    
    /** Testing: Kann überhaupt ein Objekt erzeugt werden? - "ArrayProcessor erzeugen" (With Parameter) */
    @Test
    public void tol_1e_objectCreationWP_ArrayProcessor(){                        // WP ::= With Parameter
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // prepare test parameter
        final long[][] testParameter = { {1,2}, {3,4} };
        //
        // actual test
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ testParameter }
                ));
            assertNotNull( arrayProcessor );
            //\=> ArrayProcessor with some parameter was created
            // NO crash yet => success ;-)
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.CREATION ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    /** Testing: (NUR-)Methoden-Aufruf "getOriginalArray()" */
    @Test
    public void tol_1e_behavior_getOriginalArray(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {1,2}, {3,4} } }
                ));
            assertNotNull( arrayProcessor );
            //\=> ArrayProcessor with some parameter was created
            final long[][] anyValue = arrayProcessor.getOriginalArray();
            // NO crash yet => success ;-)
            TS.bo( anyValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: (NUR-)Methoden-Aufruf "getCurrentArray()" */
    @Test
    public void tol_1e_behavior_getCurrentArray(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {1,2}, {3,4} } }
                ));
            assertNotNull( arrayProcessor );
            //\=> ArrayProcessor with some parameter was created
            final long[][] anyValue = arrayProcessor.getOriginalArray();
            // NO crash yet => success ;-)
            TS.bo( anyValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: (NUR-)Methoden-Aufruf "expand1stDimBySum()" */
    @Test
    public void tol_1e_behavior_expand1stDimBySum(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {1,2}, {3,4} } }
                ));
            //\=> ArrayProcessor with some parameter was created
            arrayProcessor.expand1stDimBySum();
            // NO crash yet => success ;-)
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: (NUR-)Methoden-Aufruf "expand2ndDimBySum()" */
    @Test
    public void tol_1e_behavior_expand2ndDimBySum(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {1,2}, {3,4} } }
                ));
            //\=> ArrayProcessor with some parameter was created
            arrayProcessor.expand2ndDimBySum();
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
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {1,2}, {3,4} } }
                ));
            //\=> ArrayProcessor with some parameter was created
            long[][] anyValue;
            anyValue = arrayProcessor.getCurrentArray();
            anyValue = arrayProcessor.getOriginalArray();
            arrayProcessor.expand1stDimBySum();
            anyValue = arrayProcessor.getCurrentArray();
            anyValue = arrayProcessor.getOriginalArray();
            arrayProcessor.expand2ndDimBySum();
            anyValue = arrayProcessor.getCurrentArray();
            anyValue = arrayProcessor.getOriginalArray();
            arrayProcessor.expand1stDimBySum();
            anyValue = arrayProcessor.getOriginalArray();
            arrayProcessor.expand2ndDimBySum();
            anyValue = arrayProcessor.getOriginalArray();
            arrayProcessor.expand1stDimBySum();
            arrayProcessor.expand2ndDimBySum();
            anyValue = arrayProcessor.getCurrentArray();
            assertNotNull( anyValue );
            // NO crash yet => success ;-)
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Testing: getOriginalArray() (& combined) */
    @Test
    public void tol_2b_behavior_getOriginalArray_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {1,2}, {3,4} } }
                ));
            //\=> ArrayProcessor with some parameter was created
            long[][] anyValue;
            //
            anyValue = arrayProcessor.getOriginalArray();
            assertNotNull( anyValue );
            //
            arrayProcessor.expand1stDimBySum();
            //
            anyValue = arrayProcessor.getOriginalArray();
            assertNotNull( anyValue );
            //
            arrayProcessor.expand2ndDimBySum();
            //
            anyValue = arrayProcessor.getOriginalArray();
            assertNotNull( anyValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsTwoPoints ));
        }//if
    }//method()
    
    
    /** Testing: getCurrentArray() (& combined) */
    @Test
    public void tol_2b_behavior_getCurrentArray_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {1,2}, {3,4} } }
                ));
            //\=> ArrayProcessor with some parameter was created
            long[][] anyValue;
            //
            anyValue = arrayProcessor.getCurrentArray();
            assertNotNull( anyValue );
            //
            arrayProcessor.expand1stDimBySum();
            //
            anyValue = arrayProcessor.getCurrentArray();
            assertNotNull( anyValue );
            //
            arrayProcessor.expand2ndDimBySum();
            //
            anyValue = arrayProcessor.getCurrentArray();
            assertNotNull( anyValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsTwoPoints ));
        }//if
    }//method()
    
    
    /** Testing: expand1stDimBySum() (& getOriginalArray()) */
    @Test
    public void tol_2b_behavior_expand1stDimBySum_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {1,2}, {3,4} } }
                ));
            //\=> ArrayProcessor with some parameter was created
            long[][] anyValue;
            //
            for( int i=2; --i>=0; ){
                arrayProcessor.expand1stDimBySum();
            }//for
            //\=>NO crash yet
            //
            anyValue = arrayProcessor.getOriginalArray();
            assertNotNull( anyValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsTwoPoints ));
        }//if
    }//method()
    
    
    /** Testing: expand1stDimBySum() (& getCurrentArray()) */
    @Test
    public void tol_2b_behavior_expand1stDimBySum_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {1,2}, {3,4} } }
                ));
            //\=> ArrayProcessor with some parameter was created
            long[][] anyValue;
            //
            for( int i=2; --i>=0; ){
                arrayProcessor.expand1stDimBySum();
            }//for
            //\=>NO crash yet
            //
            anyValue = arrayProcessor.getCurrentArray();
            assertNotNull( anyValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsTwoPoints ));
        }//if
    }//method()
    
    
    /** Testing: expand2ndimBySum() (& getOriginalArray()) */
    @Test
    public void tol_2b_behavior_expand2ndDimBySum_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {1,2}, {3,4} } }
                ));
            //\=> ArrayProcessor with some parameter was created
            long[][] anyValue;
            //
            for( int i=2; --i>=0; ){
                arrayProcessor.expand2ndDimBySum();
            }//for
            //\=>NO crash yet
            //
            anyValue = arrayProcessor.getOriginalArray();
            assertNotNull( anyValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsTwoPoints ));
        }//if
    }//method()
    
    
    /** Testing: expand2ndimBySum() (& getCurrentArray()) */
    @Test
    public void tol_2b_behavior_expand2ndDimBySum_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {1,2}, {3,4} } }
                ));
            //\=> ArrayProcessor with some parameter was created
            long[][] anyValue;
            //
            for( int i=2; --i>=0; ){
                arrayProcessor.expand2ndDimBySum();
            }//for
            //\=>NO crash yet
            //
            anyValue = arrayProcessor.getCurrentArray();
            assertNotNull( anyValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsTwoPoints ));
        }//if
    }//method()
    
    
    
    
    
    
    
    
    
    
    //##########################################################################
    //###
    //###   C / "3n"
    //###
    
    /** Testing: getOriginalArray() */
    @Test
    public void tol_3n_behavior_getOriginalArray_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {1,2}, {3,4} } }
                ));
            //\=> ArrayProcessor with some parameter was created
            arrayProcessor.expand1stDimBySum();
            final long[][] computedValue = arrayProcessor.getOriginalArray();
            final long[][] expectedValue = { {1,2}, {3,4} };
            assertArrayEquals( expectedValue, computedValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Testing: getOriginalArray() */
    @Test
    public void tol_3n_behavior_getOriginalArray_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {1,2}, {3,4} } }
                ));
            //\=> ArrayProcessor with some parameter was created
            arrayProcessor.expand2ndDimBySum();
            final long[][] computedValue = arrayProcessor.getOriginalArray();
            final long[][] expectedValue = { {1,2}, {3,4} };
            assertArrayEquals( expectedValue, computedValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    /** Funktions-Test: getOriginalArray() & getCurrentArray() */
    @Test
    public void tol_3n_behavior_getter_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {42} } }
                ));
            //\=> ArrayProcessor with some parameter was created
            final long[][] expectedValue = { {42} };
            long[][] computedValue = null;
            computedValue = arrayProcessor.getCurrentArray();
            assertArrayEquals( expectedValue, computedValue );
            computedValue = arrayProcessor.getOriginalArray();
            assertArrayEquals( expectedValue, computedValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Funktions-Test: Getter */
    @Test
    public void tol_3n_behavior_getOriginalArray_no3(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {1,2}, {3,4} } }
                ));
            //\=> ArrayProcessor with some parameter was created
            final long[][] expectedValue = { {1,2}, {3,4} };
            long[][] computedValue = null;
            computedValue = arrayProcessor.getOriginalArray();
            assertArrayEquals( expectedValue, computedValue );
            for( int i=7; --i>=0;){
                arrayProcessor.expand1stDimBySum();
                computedValue = arrayProcessor.getOriginalArray();
                assertArrayEquals( expectedValue, computedValue );
                arrayProcessor.expand2ndDimBySum();
                computedValue = arrayProcessor.getOriginalArray();
                assertArrayEquals( expectedValue, computedValue );
            }//for
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    /** Testing: expand1stDimBySum()) & getCurrentArray() */
    @Test
    public void tol_3n_behavior_expand1stDimBySum_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {1,2}, {3,4} } }
                ));
            //\=> ArrayProcessor with some parameter was created
            arrayProcessor.expand1stDimBySum();
            final long[][] computedValue = arrayProcessor.getCurrentArray();
            final long[][] expectedValue = { {1,2}, {3,4}, {4,6} };
            assertArrayEquals( expectedValue, computedValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Testing: expand1stDimBySum() & getCurrentArray() */
    @Test
    public void tol_3n_behavior_expand1stDimBySum_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {2,3}, {5,7} } }
                ));
            //\=> ArrayProcessor with some parameter was created
            long[][] computedValue;
            long[][] expectedValue;
            //
            arrayProcessor.expand1stDimBySum();
            computedValue = arrayProcessor.getCurrentArray();
            expectedValue = new long[][]{ {2,3}, {5,7}, {7,10} };
            assertArrayEquals( expectedValue, computedValue );
            //
            arrayProcessor.expand1stDimBySum();
            computedValue = arrayProcessor.getCurrentArray();
            expectedValue = new long[][]{ {2,3}, {5,7}, {7,10}, {14,20} };
            assertArrayEquals( expectedValue, computedValue );
            //
            arrayProcessor.expand1stDimBySum();
            computedValue = arrayProcessor.getCurrentArray();
            expectedValue = new long[][]{ {2,3}, {5,7}, {7,10}, {14,20}, {28,40} };
            assertArrayEquals( expectedValue, computedValue );
            //
            arrayProcessor.expand1stDimBySum();
            computedValue = arrayProcessor.getCurrentArray();
            expectedValue = new long[][]{ {2,3}, {5,7}, {7,10}, {14,20}, {28,40}, {56,80} };
            assertArrayEquals( expectedValue, computedValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsTwoPoints ));
        }//if
    }//method()
    
    
    
    
    /** Testing: expand2ndDimBySum() & getCurrentArray() */
    @Test
    public void tol_3n_behavior_expand2ndDimBySum_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {1,2}, {3,4} } }
                ));
            //\=> ArrayProcessor with some parameter was created
            arrayProcessor.expand2ndDimBySum();
            final long[][] computedValue = arrayProcessor.getCurrentArray();
            final long[][] expectedValue = { {1,2,3}, {3,4,7} };
            assertArrayEquals( expectedValue, computedValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Testing: expand2ndDimBySum() & getCurrentArray() */
    @Test
    public void tol_3n_behavior_expand2ndDimBySum_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {2,3}, {5,7} } }
                ));
            //\=> ArrayProcessor with some parameter was created
            long[][] computedValue;
            long[][] expectedValue;
            //
            arrayProcessor.expand2ndDimBySum();
            computedValue = arrayProcessor.getCurrentArray();
            expectedValue = new long[][]{ {2,3,5}, {5,7,12} };
            assertArrayEquals( expectedValue, computedValue );
            //
            arrayProcessor.expand2ndDimBySum();
            computedValue = arrayProcessor.getCurrentArray();
            expectedValue = new long[][]{ {2,3,5,10}, {5,7,12,24} };
            assertArrayEquals( expectedValue, computedValue );
            //
            arrayProcessor.expand2ndDimBySum();
            computedValue = arrayProcessor.getCurrentArray();
            expectedValue = new long[][]{ {2,3,5,10,20}, {5,7,12,24,48} };
            assertArrayEquals( expectedValue, computedValue );
            //
            arrayProcessor.expand2ndDimBySum();
            computedValue = arrayProcessor.getCurrentArray();
            expectedValue = new long[][]{ {2,3,5,10,20,40}, {5,7,12,24,48,96} };
            assertArrayEquals( expectedValue, computedValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsTwoPoints ));
        }//if
    }//method()
    
    
    
    /** Testing: combined */
    @Test
    public void tol_3n_behavior_combined_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {7,2}, {5,3} } }
                ));
            //\=> ArrayProcessor with some parameter was created
            final long[][] originalArray = { {7,2}, {5,3} };
            long[][] expectedValue;
            long[][] computedValue;
            //
            arrayProcessor.expand2ndDimBySum();
            computedValue = arrayProcessor.getCurrentArray();
            expectedValue = new long[][]{ {7,2,9}, {5,3,8} };
            assertArrayEquals( expectedValue, computedValue );
            computedValue = arrayProcessor.getOriginalArray();
            assertArrayEquals( originalArray, computedValue );
            //
            arrayProcessor.expand1stDimBySum();
            computedValue = arrayProcessor.getCurrentArray();
            expectedValue = new long[][]{ {7,2,9}, {5,3,8}, {12,5,17} };
            assertArrayEquals( expectedValue, computedValue );
            computedValue = arrayProcessor.getOriginalArray();
            assertArrayEquals( originalArray, computedValue );
            //
            arrayProcessor.expand2ndDimBySum();
            computedValue = arrayProcessor.getCurrentArray();
            expectedValue = new long[][]{ {7,2,9,18}, {5,3,8,16}, {12,5,17,34} };
            assertArrayEquals( expectedValue, computedValue );
            computedValue = arrayProcessor.getOriginalArray();
            assertArrayEquals( originalArray, computedValue );
            //
            arrayProcessor.expand1stDimBySum();
            computedValue = arrayProcessor.getCurrentArray();
            expectedValue = new long[][]{ {7,2,9,18}, {5,3,8,16}, {12,5,17,34}, {24,10,34,68} };
            assertArrayEquals( expectedValue, computedValue );
            computedValue = arrayProcessor.getOriginalArray();
            assertArrayEquals( originalArray, computedValue );
            //
            arrayProcessor.expand2ndDimBySum();
            computedValue = arrayProcessor.getCurrentArray();
            expectedValue = new long[][]{ {7,2,9,18,36}, {5,3,8,16,32}, {12,5,17,34,68}, {24,10,34,68,136} };
            assertArrayEquals( expectedValue, computedValue );
            computedValue = arrayProcessor.getOriginalArray();
            assertArrayEquals( originalArray, computedValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsTwoPoints ));
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
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        final RefTypeInfoContainer refTypeInfoContainer = generateRefTypeInfoContainer( requestedRefTypeName );
        
        // guard test
        doGeneralGuardTest( testName, requestedRefTypeWithPath );
        
        // start of actual test
        if( TS.containsSuspiciousToDoMarks( testName, refTypeInfoContainer, ManualTodoInvestigationRelatedAddOn.NONE ) ){
            fail( "Source code contains TODO marks indicating code is NOT ready" );
        }//if
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Stress-Test: parameter is null */
    @Test
    public void tol_4s_behavior_parameter_null_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // guard test
        doGeneralGuardTest( testName, requestedRefTypeWithPath );
        
        // start of actual test
        boolean expectedExceptionOccured = false;
        try{
            final long[][] testParameter = null;
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ testParameter }
                ));
            assertNotNull( arrayProcessor );
            //\=> ArrayProcessor with some parameter was created
        }catch( final IllegalArgumentException | AssertionError ex ){           /* ??? HERE */
            expectedExceptionOccured = true;
            if( dbgOutputEnable ){
                Herald.proclaimError( String.format( "??? %s -> AE/IAE\n", testName ));
                Herald.proclaimError( String.format( "%s\n",   ex.getMessage() ));
            }//if
        }catch( final TestSupportException ex ){
            TS.examineInternallyRaisedTestSupportExceptionForIllegalArgumentCauseAndReact( testName, ex, ManualExceptionInvestigationRelatedAddOn.NONE );
            expectedExceptionOccured = true;
            //
            if( dbgOutputEnable ){
                Herald.proclaimError( String.format( "%s -> TSE\n", testName ));
                Herald.proclaimError( String.format( "\\=> %s ->msg: %s\n",  ex.getClass().getSimpleName(), ex.getMessage() ));
            }//if
        }//try                    
        assertTrue( expectedExceptionOccured,  "undetected illegal argument -> null accepted" );
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Stress-Test: parameter is null */
    @Test
    public void tol_4s_behavior_parameter_null_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // guard test
        doGeneralGuardTest( testName, requestedRefTypeWithPath );
        
        // start of actual test
        boolean expectedExceptionOccured = false;
        try{
            for( int indx1D=0; indx1D<3; indx1D++ ){
                final long[][] testParameter = { {1,2,3}, {4,5,6}, {7,8,9} };
                testParameter[indx1D] = null;
                final ArrayProcessor_I arrayProcessor =
                    (ArrayProcessor_I)( TS.generateRequestedObject(
                        requestedRefTypeWithPath,
                        new Class<?>[]{ long[][].class },
                        new Object[]{ testParameter }
                    ));
                assertNotNull( arrayProcessor );
                //\=> ArrayProcessor with some parameter was created
            }//for
        }catch( final IllegalArgumentException | AssertionError ex ){
            expectedExceptionOccured = true;
            //
            //------------------------------------------------------------------
            if( dbgOutputEnable ){
                Herald.proclaimError( String.format( "??? %s -> AE/IAE\n", testName ));
                Herald.proclaimError( String.format( "%s\n",   ex.getMessage() ));
            }//if
            //------------------------------------------------------------------
        }catch( final TestSupportException ex ){
            TS.examineInternallyRaisedTestSupportExceptionForIllegalArgumentCauseAndReact( testName, ex, ManualExceptionInvestigationRelatedAddOn.NONE );
            expectedExceptionOccured = true;
            //
            //------------------------------------------------------------------
            if( dbgOutputEnable ){
                Herald.proclaimError( String.format( "%s -> TSE\n", testName ));
                Herald.proclaimError( String.format( "\\=> %s ->msg: %s\n",  ex.getClass().getSimpleName(), ex.getMessage() ));
            }//if
            //------------------------------------------------------------------
        }catch( final Throwable ex ){
            fail( String.format("%s ->  %s",  ex.getClass().getSimpleName(), ex.getMessage() ));
        }//try
        assertTrue( expectedExceptionOccured,  "undetected illegal argument -> null accepted" );
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Stress-Test: parameter is empty */
    @Test
    public void tol_4s_behavior_parameter_empty_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // guard test
        doGeneralGuardTest( testName, requestedRefTypeWithPath );
        
        // start of actual test
        boolean expectedExceptionOccured = false;
        try{
            final long[][] testParameter = { {} };
            final ArrayProcessor_I arrayProcessor =
                (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ testParameter }
                ));
            assertNotNull( arrayProcessor );
            //\=> ArrayProcessor with some parameter was created
        }catch( final IllegalArgumentException | AssertionError ex ){           /* ??? HERE */
            expectedExceptionOccured = true;
            if( dbgOutputEnable ){
                Herald.proclaimError( String.format( "??? %s -> AE/IAE\n", testName ));
                Herald.proclaimError( String.format( "%s\n",   ex.getMessage() ));
            }//if
        }catch( final TestSupportException ex ){
            TS.examineInternallyRaisedTestSupportExceptionForIllegalArgumentCauseAndReact( testName, ex, ManualExceptionInvestigationRelatedAddOn.NONE );
            expectedExceptionOccured = true;
            //
            if( dbgOutputEnable ){
                Herald.proclaimError( String.format( "%s -> TSE\n", testName ));
                Herald.proclaimError( String.format( "\\=> %s ->msg: %s\n",  ex.getClass().getSimpleName(), ex.getMessage() ));
            }//if
        }//try                    
        assertTrue( expectedExceptionOccured,  "undetected illegal argument -> null accepted" );
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Stress-Test: parameter is empty */
    @Test
    public void tol_4s_behavior_parameter_empty_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // guard test
        doGeneralGuardTest( testName, requestedRefTypeWithPath );
        
        // start of actual test
        boolean expectedExceptionOccured = false;
        try{
            for( int indx1D=0; indx1D<3; indx1D++ ){
                final long[][] testParameter = { {1,2,3}, {4,5,6}, {7,8,9} };
                testParameter[indx1D] = new long[]{};
                final ArrayProcessor_I arrayProcessor =
                    (ArrayProcessor_I)( TS.generateRequestedObject(
                        requestedRefTypeWithPath,
                        new Class<?>[]{ long[][].class },
                        new Object[]{ testParameter }
                    ));
                assertNotNull( arrayProcessor );
                //\=> ArrayProcessor with some parameter was created
            }//for
        }catch( final IllegalArgumentException | AssertionError ex ){
            expectedExceptionOccured = true;
            if( dbgOutputEnable ){
                Herald.proclaimError( String.format( "??? %s -> AE/IAE\n", testName ));
                Herald.proclaimError( String.format( "%s\n",   ex.getMessage() ));
            }//if
        }catch( final TestSupportException ex ){
            TS.examineInternallyRaisedTestSupportExceptionForIllegalArgumentCauseAndReact( testName, ex, ManualExceptionInvestigationRelatedAddOn.NONE );
            expectedExceptionOccured = true;
            //
            if( dbgOutputEnable ){
                Herald.proclaimError( String.format( "%s -> TSE\n", testName ));
                Herald.proclaimError( String.format( "\\=> %s ->msg: %s\n",  ex.getClass().getSimpleName(), ex.getMessage() ));
            }//if
        }catch( final Throwable ex ){
            fail( String.format("%s ->  %s",  ex.getClass().getSimpleName(), ex.getMessage() ));
        }//try
        assertTrue( expectedExceptionOccured,  "undetected illegal argument -> null accepted" );
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Funktions-Test: normal test with zero */
    @Test
    public void tol_4s_behavior_normalTestWithZero_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // guard test
        doGeneralGuardTest( testName, requestedRefTypeWithPath );
        
        // start of actual test
        try{
            ArrayProcessor_I arrayProcessor;
            long[][] expectedValue;
            long[][] computedValue;
            
            //------------------------------------------------------------------
            // { { 0 } }
            // expand1stDimBySum()
            // expand2ndDimBySum()
            arrayProcessor = (ArrayProcessor_I)( TS.generateRequestedObject(
                requestedRefTypeWithPath,
                new Class<?>[]{ long[][].class },
                new Object[]{ new long[][]{ { 0 } } }
            ));
            //\=> ArrayProcessor with some parameter was created
            arrayProcessor.expand1stDimBySum();
            expectedValue = new long[][]{ {0}, {0} };
            computedValue = arrayProcessor.getCurrentArray();
            assertArrayEquals( expectedValue, computedValue );
            //
            arrayProcessor.expand2ndDimBySum();
            expectedValue = new long[][]{ {0,0}, {0,0} };
            computedValue = arrayProcessor.getCurrentArray();
            assertArrayEquals( expectedValue, computedValue );
            
            //------------------------------------------------------------------
            // { { 0 } }
            // expand2ndDimBySum()
            // expand1stDimBySum()
            arrayProcessor = (ArrayProcessor_I)( TS.generateRequestedObject(
                requestedRefTypeWithPath,
                new Class<?>[]{ long[][].class },
                new Object[]{ new long[][]{ { 0 } } }
            ));
            //\=> ArrayProcessor with some parameter was created
            arrayProcessor.expand2ndDimBySum();
            expectedValue = new long[][]{ {0, 0} };
            computedValue = arrayProcessor.getCurrentArray();
            assertArrayEquals( expectedValue, computedValue );
            //
            arrayProcessor.expand1stDimBySum();
            expectedValue = new long[][]{ {0,0}, {0,0} };
            computedValue = arrayProcessor.getCurrentArray();
            assertArrayEquals( expectedValue, computedValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Funktions-Test: normal test */
    @Test
    public void tol_4s_behavior_normalTest_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // guard test
        doGeneralGuardTest( testName, requestedRefTypeWithPath );
        
        // start of actual test
        try{
            ArrayProcessor_I arrayProcessor;
            long[][] expectedValue;
            long[][] computedValue;
            
            //------------------------------------------------------------------
            // { {1,2,3}, {3,4}, {5}, {9,6,3} }
            // expand1stDimBySum()
            // expand2ndDimBySum()
            arrayProcessor = (ArrayProcessor_I)( TS.generateRequestedObject(
                requestedRefTypeWithPath,
                new Class<?>[]{ long[][].class },
                new Object[]{ new long[][]{ {1,2,3}, {3,4}, {5} } }
            ));
            //\=> ArrayProcessor with some parameter was created
            arrayProcessor.expand1stDimBySum();
            expectedValue = new long[][]{ {1,2,3}, {3,4}, {5}, {9,6,3} };
            computedValue = arrayProcessor.getCurrentArray();
            assertArrayEquals( expectedValue, computedValue );
            //
            arrayProcessor.expand2ndDimBySum();
            expectedValue = new long[][]{ {1,2,3,6}, {3,4,7}, {5,5}, {9,6,3,18} };
            computedValue = arrayProcessor.getCurrentArray();
            assertArrayEquals( expectedValue, computedValue );
            
            //------------------------------------------------------------------
            // { {1,2,3}, {3,4}, {5}, {9,6,3} }
            // expand2ndDimBySum()
            // expand1stDimBySum()
            arrayProcessor = (ArrayProcessor_I)( TS.generateRequestedObject(
                requestedRefTypeWithPath,
                new Class<?>[]{ long[][].class },
                new Object[]{ new long[][]{ {1,2,3}, {3,4}, {5} } }
            ));
            //\=> ArrayProcessor with some parameter was created
            arrayProcessor.expand2ndDimBySum();
            expectedValue = new long[][]{ {1,2,3,6}, {3,4,7}, {5,5} };
            computedValue = arrayProcessor.getCurrentArray();
            assertArrayEquals( expectedValue, computedValue );
            //
            arrayProcessor.expand1stDimBySum();
            expectedValue = new long[][]{ {1,2,3,6}, {3,4,7}, {5,5}, {9,11,10,6} };
            computedValue = arrayProcessor.getCurrentArray();
            assertArrayEquals( expectedValue, computedValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Funktions-Test: normal test */
    @Test
    public void tol_4s_behavior_normalTest_no3(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ArrayProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // guard test
        doGeneralGuardTest( testName, requestedRefTypeWithPath );
        
        // start of actual test
        try{
            ArrayProcessor_I arrayProcessor;
            long[][] expectedValue;
            long[][] computedValue;
            
            //------------------------------------------------------------------
            // { {2,3}, {5,7,11}, {13}, {17,19,23,29}, {31} } }
            // expand1stDimBySum()
            // expand2ndDimBySum()
            // expand1stDimBySum()
            arrayProcessor = (ArrayProcessor_I)( TS.generateRequestedObject(
                requestedRefTypeWithPath,
                new Class<?>[]{ long[][].class },
                new Object[]{ new long[][]{ {2,3}, {5,7,11}, {13}, {17,19,23,29}, {31} } }
            ));
            //\=> ArrayProcessor with some parameter was created
            //
            arrayProcessor.expand1stDimBySum();
            expectedValue = new long[][]{ {2,3}, {5,7,11}, {13}, {17,19,23,29}, {31}, {68,29,34,29} };
            computedValue = arrayProcessor.getCurrentArray();
            assertArrayEquals( expectedValue, computedValue );
            //
            arrayProcessor.expand2ndDimBySum();
            expectedValue = new long[][]{ {2,3,5}, {5,7,11,23}, {13,13}, {17,19,23,29,88}, {31,31}, {68,29,34,29,160} };
            computedValue = arrayProcessor.getCurrentArray();
            assertArrayEquals( expectedValue, computedValue );
            //
            arrayProcessor.expand1stDimBySum();
            expectedValue = new long[][]{ {2,3,5}, {5,7,11,23}, {13,13}, {17,19,23,29,88}, {31,31}, {68,29,34,29,160}, {136,102,73,81,248} };
            computedValue = arrayProcessor.getCurrentArray();
            assertArrayEquals( expectedValue, computedValue );
            
            
            //------------------------------------------------------------------
            // { {2,3}, {5,7,11}, {13}, {17,19,23,29}, {31} } }
            // expand2ndDimBySum()
            // expand1stDimBySum()
            // expand2ndDimBySum()
            arrayProcessor = (ArrayProcessor_I)( TS.generateRequestedObject(
                requestedRefTypeWithPath,
                new Class<?>[]{ long[][].class },
                new Object[]{ new long[][]{ {2,3}, {5,7,11}, {13}, {17,19,23,29}, {31} } }
            ));
            //\=> ArrayProcessor with some parameter was created
            //
            arrayProcessor.expand2ndDimBySum();
            expectedValue = new long[][]{ {2,3,5}, {5,7,11,23}, {13,13}, {17,19,23,29,88}, {31,31} };
            computedValue = arrayProcessor.getCurrentArray();
            assertArrayEquals( expectedValue, computedValue );
            //
            arrayProcessor.expand1stDimBySum();
            expectedValue = new long[][]{ {2,3,5}, {5,7,11,23}, {13,13}, {17,19,23,29,88}, {31,31}, {68,73,39,52,88} };
            computedValue = arrayProcessor.getCurrentArray();
            assertArrayEquals( expectedValue, computedValue );
            //
            arrayProcessor.expand2ndDimBySum();
            expectedValue = new long[][]{ {2,3,5,10}, {5,7,11,23,46}, {13,13,26}, {17,19,23,29,88,176}, {31,31,62}, {68,73,39,52,88,320} };
            computedValue = arrayProcessor.getCurrentArray();
            assertArrayEquals( expectedValue, computedValue );
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
            //
            /*scope*/{
                ArrayProcessor_I arrayProcessor = null;
                long[][] computedValue = null;
                long[][] expectedValue = null;
                
                arrayProcessor = (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {3,5}, {7,11} } }
                ));
                //\=> ArrayProcessor with some parameter was created
                
                arrayProcessor.expand1stDimBySum();
                computedValue = arrayProcessor.getCurrentArray();
                expectedValue = new long[][]{ {3,5}, {7,11}, {10,16} };
                assertArrayEquals(
                    expectedValue,
                    computedValue,
                    String.format(
                        "%s GUARD TEST expand1stDimBySum -> wrong result:  expected:%s  !=  computed:%s",
                        testName,
                        expectedValue,
                        computedValue
                    )
                );
                
                arrayProcessor = (ArrayProcessor_I)( TS.generateRequestedObject(
                    requestedRefTypeWithPath,
                    new Class<?>[]{ long[][].class },
                    new Object[]{ new long[][]{ {3,5}, {7,11} } }
                ));
                //\=> ArrayProcessor with some parameter was created
                
                arrayProcessor.expand2ndDimBySum();
                computedValue = arrayProcessor.getCurrentArray();
                expectedValue = new long[][]{ {3,5,8}, {7,11,18} };
                assertArrayEquals(
                    expectedValue,
                    computedValue,
                    String.format(
                        "%s GUARD TEST expand2ndDimBySum -> wrong result:  expected:%s  !=  computed:%s",
                        testName,
                        expectedValue,
                        computedValue
                    )
                );
            }//scope
            
            
            // test uses MULTIPLE OBJECTs of ItemProcessor on purpose
            /*scope*/{
                // setup test parameter etc.
                final int objectCnt = 3;
                
                final long[][][] testParameter = {
                    { { 1, 2 },
                      { 3, 5 }
                    },
                    //---------
                    { { 2, 3 },
                      { 5, 7 }
                    },
                    //---------
                    { { 3, 5 },
                      { 7,11 }
                    }
                };
                assert objectCnt==testParameter.length :  "Uuuupps : internal configuration error";
                //
                final long[][][] expectedResult = {
                    { { 1, 2 },
                      { 3, 5 },
                      { 4, 7 }
                    },
                    //---------
                    { { 2, 3, 5 },
                      { 5, 7,12 }
                    },
                    //---------
                    { { 3, 5 },
                      { 7,11 },
                      {10,16 }
                    }
                };                
                assert objectCnt==expectedResult.length :  "Uuuupps : internal configuration error";
                
                
                // generate objects / create ArrayProcessors
                final ArrayProcessor_I[] arrayProcessor = new ArrayProcessor_I[objectCnt];
                for( int objId=0; objId<objectCnt;objId++ ){
                    arrayProcessor[objId] = (ArrayProcessor_I)( TS.generateRequestedObject(
                        requestedRefTypeWithPath,
                        new Class<?>[]{ long[][].class },
                        new Object[]{ testParameter[objId] }
                    ));
                }//for
                //\=> objects created
                
                // compute
                for( int objId=0; objId<objectCnt;objId++ ){
                    if( 0b0 == ( objId & 0b1 )){
                        arrayProcessor[objId].expand1stDimBySum();
                    }else{
                        arrayProcessor[objId].expand2ndDimBySum();
                    }//if
                }//for
                
                // check results
                for( int objId=0; objId<objectCnt;objId++ ){
                    assertArrayEquals( expectedResult[objId], arrayProcessor[objId].getCurrentArray(),  "[Guard test failed]" );
                    assertArrayEquals( testParameter[objId],  arrayProcessor[objId].getOriginalArray(), "[Guard test failed]" );
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
