// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package template_raw.a5;


import static _untouchable_.supportC1x01.Configuration.dbgConfigurationVector;
import static _untouchable_.supportC1x01.PointDefinition.countsOnePoint;
import static _untouchable_.supportC1x01.PointDefinition.countsTwoPoints;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import _untouchable_.data.Data;
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
import _untouchable_.supportGeneral.random.RandomBasedOnPRBS;
import _untouchable_.supportGeneral.random.RandomGenerator;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Pattern;
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
    final static private TE exercise = TE.A5;                                   // <<<<===  _HERE_
    final static private String packagePath = getPackagePath();
    final static private String rootPackageName = getRootPackageName();
    
    final static private boolean enableAutomaticEvaluation  =  ( 0 > dbgConfigurationVector );
    
    
    
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
    // No constructor was requested for Node<T>.
    // Hence, no Node-object can be generated.
    
    /** Ausgabe auf Bildschirm zur visuellen Kontrolle */
    @Test
    public void tol_1e_printSupportForManualReview_CC(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        final boolean dbgLocalOutputEnable = ( 0 != ( dbgConfigurationVector & 0x0200 ));
        if( dbgLocalOutputEnable ){
            System.out.printf( "\n\n" );
            System.out.printf( "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv\n" );
            System.out.printf( "%s():\n",  testName );
            System.out.printf( "\n\n" );    
            
            try{
                TS.printDetailedInfoAboutClass( requestedRefTypeWithPath );
                System.out.printf( "\n" );
                //
                @SuppressWarnings("unchecked")
                final Stack_I<Long> stack = (Stack_I<Long>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                //\=> Stack parameter was created
                TS.printDetailedInfoAboutObject( stack, "Stack" );
                //
                if( TS.isActualMethod( stack.getClass(), "toString", String.class, null )){
                    System.out.printf( "~.toString(): \"%s\"     again ;-)\n",  stack.toString() );
                }else{
                    System.out.printf( "NO! toString() implemented by class \"%s\" itself\n",  stack.getClass().getSimpleName() );
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
    
    /** Testing: "Stack" */
    @Test
    public void tol_1e_classExistence_Stack(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
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
    
    /** Testing: Eigenschaften des Referenz-Typs Stack incl. Constructor */
    @Test
    public void tol_1e_properties_Stack(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
            assertTrue( TS.isClass(             classUnderTest ),               String.format( "requested class %s missing", requestedRefTypeName ));
            assertTrue( TS.isClassPublic(       classUnderTest ),               "false class access modifier" );
            assertTrue( TS.isImplementing(      classUnderTest,   "Stack_I" ),  "requested supertype missing" );
            assertTrue( TS.isConstructor(       classUnderTest ),               "requested constructor missing" );
            assertTrue( TS.isConstructorPublic( classUnderTest ),               "false constructor access modifier" );
        }catch( final ClassNotFoundException ex ){
            fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: Eigenschaften "Stack" - Access Modifier Methoden */
    @Test
    public void tol_1e_propertiesMethods_Stack(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
            assertTrue( TS.isProcedure(       classUnderTest, "push",     new Class<?>[]{ Object.class } ),                  "requested method missing" );
            assertTrue( TS.isFunction(        classUnderTest, "pop",                                        Object.class ),  "requested method missing" );
            assertTrue( TS.isFunction(        classUnderTest, "top",                                        Object.class ),  "requested method missing");
            assertTrue( TS.isFunction(        classUnderTest, "isEmpty",                                    boolean.class ), "requested method missing" );
            assertTrue( TS.isProcedure(       classUnderTest, "clear" ),                                                     "requested method missing" );
            
            assertTrue( TS.isProcedurePublic( classUnderTest, "push",     new Class<?>[]{ Object.class } ),                  "requested method missing" );
            assertTrue( TS.isFunctionPublic(  classUnderTest, "pop",                                        Object.class ),  "requested method missing" );
            assertTrue( TS.isFunctionPublic(  classUnderTest, "top",                                        Object.class ),  "requested method missing");
            assertTrue( TS.isFunctionPublic(  classUnderTest, "isEmpty",                                    boolean.class ), "requested method missing" );
            assertTrue( TS.isProcedurePublic( classUnderTest, "clear" ),                                                     "requested method missing" );
           
        }catch( final ClassNotFoundException ex ){
            fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: Eigenschaften "Stack" - Access Modifier Variablen */
    @Test
    public void tol_1e_propertiesFields_Stack(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
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
    
    /* Testing: Eigenschaften "Stack" - Schreibweise Methoden */
    @Test
    public void tol_1e_notationMethods_Stack(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
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
    
    /** Testing: Eigenschaften "Stack" - Schreibweise Variablen */
    @Test
    public void tol_1e_notationFields_Stack(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
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
    
    /** Testing: Kann überhaupt ein Objekt erzeugt werden? - "Stack erzeugen" (With Parameter) */
    @Test
    public void tol_1e_objectCreation_Stack(){                        // WP ::= With Parameter
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final Stack_I<?> stack = (Stack_I<?>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            // NO crash yet => success ;-)
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.CREATION ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    /** Testing: (NUR-)Methoden-Aufruf "push()" */
    @Test
    public void tol_1e_behavior_push(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            stack.push( 42 );
            // NO crash yet => success ;-)
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: (NUR-)Methoden-Aufruf "pop()  ( & push() )" */
    @Test
    public void tol_1e_behavior_pop(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            stack.push( 42 );
            final Integer anyValue = stack.pop();
            assertNotNull( anyValue );
            // NO crash yet => success ;-)
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: (NUR-)Methoden-Aufruf "top()  ( & push() )" */
    @Test
    public void tol_1e_behavior_top(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            stack.push( 42 );
            final Integer anyValue = stack.top();
            assertNotNull( anyValue );
            // NO crash yet => success ;-)
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: (NUR-)Methoden-Aufruf "isEmpty()" */
    @Test
    public void tol_1e_behavior_isEmpty(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            final Boolean anyValue = stack.isEmpty();
            assertNotNull( anyValue );
            // NO crash yet => success ;-)
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: (NUR-)Methoden-Aufruf "clear()" */
    @Test
    public void tol_1e_behavior_clear(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            //\=> Stack was created
            assertNotNull( stack );
            stack.clear();
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
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            //
            stack.push( 42 );
            //
            Integer anyIntValue = stack.top();
            assertNotNull( anyIntValue );
            //
            anyIntValue = stack.pop();
            assertNotNull( anyIntValue );
            //
            Boolean anyBoolValue = stack.isEmpty();
            assertNotNull( anyBoolValue );
            //
            stack.clear();
            //
            stack.push( 13 );
            anyIntValue = stack.pop();
            assertNotNull( anyIntValue );
            // NO crash yet => success ;-)               //...optimizations
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: "push()" & "top()" */
    @Test
    public void tol_2b_behavior_top_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            final int expectedValue = 42;
            stack.push( expectedValue );
            final int computedValue = stack.top();
            assertEquals( expectedValue, computedValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: "push()" & "pop()" */
    @Test
    public void tol_2b_behavior_pop_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            final int expectedValue = 42;
            stack.push( expectedValue );
            final int computedValue = stack.pop();
            assertEquals( expectedValue, computedValue );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: "isEmpty()" */
    @Test
    public void tol_2b_behavior_isEmpty_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            assertTrue( stack.isEmpty() );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: "isEmpty() ( &push() & pop() )" */
    @Test
    public void tol_2b_behavior_isEmpty_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            assertTrue( stack.isEmpty() );
            //
            stack.push( 42 );
            assertFalse( stack.isEmpty() );
            //
            stack.pop();
            assertTrue( stack.isEmpty() );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: "clear() & isEmpty()" */
    @Test
    public void tol_2b_behavior_clear_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            //
            assertTrue( stack.isEmpty() );
            //
            stack.push( 42 );
            assertFalse( stack.isEmpty() );
            //
            stack.clear();
            assertTrue( stack.isEmpty() );
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
    
    
    /** Testing: "push()" & "top()" */
    @Test
    public void tol_3n_behavior_pop_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            checkForForbiddenTypes( testName, requestedRefTypeName, ManualExceptionInvestigationRelatedAddOn.NONE );
            
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            final int theValue = 13;
            for( int i=0; i<theValue; i++ ){
                stack.push( i );
            }//for
            for( int i=theValue; --i>=0; ){
                final int computedValue = stack.pop();
                assertEquals( i, computedValue );
            }//for
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Testing: "push()" & "top()" */
    @Test
    public void tol_3n_behavior_top_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            checkForForbiddenTypes( testName, requestedRefTypeName, ManualExceptionInvestigationRelatedAddOn.NONE );
            
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            final int theValue = 13;
            for( int i=0; i<theValue; i++ ){
                stack.push( i );
            }//for
            for( int i=theValue; --i>=0; ){
                final int computedValue = stack.top();
                stack.pop();
                assertEquals( i, computedValue );
            }//for
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Testing: "push()" & "top()" & "pop()" */
    @Test
    public void tol_3n_behavior_combined_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            checkForForbiddenTypes( testName, requestedRefTypeName, ManualExceptionInvestigationRelatedAddOn.NONE );
            
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            final int theValue = 13;
            //
            for( int i=0; i<theValue; i++ ){
                stack.push( i );
            }//for
            for( int i=theValue; --i>=0; ){
                assertEquals( i, stack.top() );
                assertEquals( i, stack.pop() );
            }//for
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Testing: "push()" & "top()" & "pop()" */
    @Test
    public void tol_3n_behavior_combined_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            checkForForbiddenTypes( testName, requestedRefTypeName, ManualExceptionInvestigationRelatedAddOn.NONE );
            
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            final int theValue = 13;
            //
            for( int i=0; i<theValue; i++ ){
                stack.push( i );
            }//for
            for( int i=theValue; --i>=0; ){
                assertEquals( i, stack.top() );
                assertEquals( i, stack.pop() );
            }//for
            assertTrue( stack.isEmpty() );
            
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Testing: "push()" & "top()" & "pop()" & "clear()" */
    @Test
    public void tol_3n_behavior_combined_no3(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            checkForForbiddenTypes( testName, requestedRefTypeName, ManualExceptionInvestigationRelatedAddOn.NONE );
            
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            final int stopValue = 13;
            //
            for( int i=0; i<stopValue; i++ ){
                stack.push( i );
                assertFalse( stack.isEmpty() );
            }//for
            for( int i=stopValue; --i>=4; ){
                assertEquals( i, stack.top() );
                assertEquals( i, stack.pop() );
                assertFalse( stack.isEmpty() );
            }//for
            stack.clear();
            assertTrue( stack.isEmpty() );
            //
            for( int i=0; i<stopValue; i++ ){
                stack.push( i );
                assertFalse( stack.isEmpty() );
            }//for
            for( int i=stopValue; --i>=0; ){
                assertEquals( i, stack.top() );
                assertEquals( i, stack.pop() );
            }//for
            assertTrue( stack.isEmpty() );
            //
            for( int i=0; i<stopValue; i++ ){
                stack.push( i );
                assertFalse( stack.isEmpty() );
            }//for
            stack.clear();
            assertTrue( stack.isEmpty() );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Stress-Test: Ping-Pong #1 */
    @Test
    public void tol_3n_behavior_pingPong_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            checkForForbiddenTypes( testName, requestedRefTypeName, ManualExceptionInvestigationRelatedAddOn.NONE );
            
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack1 = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack1 );
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack2 = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack2 );
            //\=> Stacks with were created
            
            final int numberOfSequentialTestData = 7;
            final int numberOfCoreTestLoopRuns = 3;
            for( int value=numberOfSequentialTestData; --value>=0; ){
                stack1.push( value );
            }//for
            
            for( int stillToDo=numberOfCoreTestLoopRuns; --stillToDo>=0; ){
                while( ! stack1.isEmpty() ){
                    stack2.push( stack1.pop() );
                }//while
                while( ! stack2.isEmpty() ){
                    stack1.push( stack2.pop() );
                }//while
            }//for
            
            for( int expectedValue=0; expectedValue<numberOfSequentialTestData; expectedValue++ ){
                final Integer computedValue = stack1.pop();
                assertEquals( expectedValue, computedValue );
            }//for
            assertTrue( stack1.isEmpty() );
            
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Stress-Test: Ping-Pong #2 */
    @Test
    public void tol_3n_behavior_pingPong_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            checkForForbiddenTypes( testName, requestedRefTypeName, ManualExceptionInvestigationRelatedAddOn.NONE );
            
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack1 = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack1 );
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack2 = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack2 );
            //\=> Stacks with were created
            
            final int numberOfSequentialTestData = 23;
            final int numberOfCoreTestLoopRuns = 7;
            for( int value=numberOfSequentialTestData; --value>=0; ){
                stack1.push( value );
            }//for
            
            for( int stillToDo=numberOfCoreTestLoopRuns; --stillToDo>=0; ){
                while( ! stack1.isEmpty() ){
                    stack2.push( stack1.top() );
                    stack2.push( stack1.pop() );
                }//while
                while( ! stack2.isEmpty() ){
                    final Integer currentData = stack2.pop();
                    assertEquals( currentData, stack2.pop() );
                    stack1.push( currentData );
                }//while
            }//for
            
            for( int value=0; value<numberOfSequentialTestData; value++ ){
                final Integer currentData = stack1.pop();
                assertEquals( currentData, value );
            }//for
            assertTrue( stack1.isEmpty() );
            
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
    
    /** Check for TODOs inside Code marking code NOT ready. */
    @Test
    public void tol_4s_containsSuspiciousTodoMarks(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String requestedRefTypeNames[] = { "Stack" };                     // Node hat keinen eingeforderten parameterlosen Konstruktor - sei es drum ;-)
        for( final String requestedRefTypeName : requestedRefTypeNames ){
            final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
            
            doGeneralGuardTest( requestedRefTypeName, requestedRefTypeWithPath );
            
            // start of actual test
            final RefTypeInfoContainer refTypeInfoContainer = generateRefTypeInfoContainer( requestedRefTypeName );
            if( TS.containsSuspiciousToDoMarks( testName, refTypeInfoContainer, ManualTodoInvestigationRelatedAddOn.NONE ) ){
                fail( "Source code contains TODO marks indicating code is NOT ready" );
            }//if
        }//for
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    /** Stress-Test: "parameter is null" */
    @Test
    public void tol_4s_behavior_parameter_null_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            doGeneralGuardTest( testName, requestedRefTypeWithPath );
            checkForForbiddenTypes( testName, requestedRefTypeName, ManualExceptionInvestigationRelatedAddOn.NONE );
            
            
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            boolean expectedExceptionOccured = false;
            try{
                stack.push( null );
                fail( "illegal argument - null accepted" );
            }catch( final AssertionError | IllegalArgumentException ex ){
                expectedExceptionOccured = true;
            }//try
            assertTrue( expectedExceptionOccured );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Stress-Test: underflow after "pop()" - 1x"push()", 2x"pop()" */
    @Test
    public void tol_4s_behavior_underflow_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            doGeneralGuardTest( testName, requestedRefTypeWithPath );
            checkForForbiddenTypes( testName, requestedRefTypeName, ManualExceptionInvestigationRelatedAddOn.NONE );
            
            
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            //
            stack.push( 13 );
            int computedResult = stack.pop();
            assertEquals( 13, computedResult );
            boolean expectedExceptionOccured = false;
            try{
                computedResult = stack.pop();
                fail( "undetected underflow" );
            }catch( final AssertionError | EmptyStackException | IllegalStateException ex ){
                expectedExceptionOccured = true;
            }//try
            assertTrue( expectedExceptionOccured );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Stress-Test: underflow after "top()" - 1x"push()", 1x"pop()", !x"top()" */
    @Test
    public void tol_4s_behavior_underflow_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            doGeneralGuardTest( testName, requestedRefTypeWithPath );
            checkForForbiddenTypes( testName, requestedRefTypeName, ManualExceptionInvestigationRelatedAddOn.NONE );
            
            
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            //
            stack.push( 13 );
            int computedResult = stack.pop();
            assertEquals( 13, computedResult );
            boolean expectedExceptionOccured = false;
            try{
                computedResult = stack.top();
                assertEquals( 13, computedResult );
                fail( "undetected underflow" );
            }catch( final AssertionError | EmptyStackException | IllegalStateException ex ){
                expectedExceptionOccured = true;
            }//try
            assertTrue( expectedExceptionOccured );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Stress-Test: underflow after "pop()" - 13x"push()", 1x"clear()", 1x"pop()" */
    @Test
    public void tol_4s_behavior_underflow_no3(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            doGeneralGuardTest( testName, requestedRefTypeWithPath );
            checkForForbiddenTypes( testName, requestedRefTypeName, ManualExceptionInvestigationRelatedAddOn.NONE );
            
            
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            //
            for( int stillToDo=13; --stillToDo>=0; )  stack.push( stillToDo );
            stack.clear();
            boolean expectedExceptionOccured = false;
            try{
                final Integer anyValue  = stack.pop();
                fail( "undetected underflow" );
                TS.bo( anyValue );
            }catch( final AssertionError | EmptyStackException | IllegalStateException ex ){
                expectedExceptionOccured = true;
            }//try
            assertTrue( expectedExceptionOccured );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Stress-Test: underflow after "top()" - 13x"push()", 1x"clear()", 1x"top()" */
    @Test
    public void tol_4s_behavior_underflow_no4(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            doGeneralGuardTest( testName, requestedRefTypeWithPath );
            checkForForbiddenTypes( testName, requestedRefTypeName, ManualExceptionInvestigationRelatedAddOn.NONE );
            
            
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            //
            final RandomGenerator randomeGenerator = new RandomBasedOnPRBS( 0xFEBCDA09_87654321L );
            for( int stillToDo=13; --stillToDo>=0; )  stack.push( randomeGenerator.nextInt( 97 ));
            stack.clear();
            boolean expectedExceptionOccured = false;
            try{
                final Integer anyValue = stack.top();
                fail( "undetected underflow" );
                TS.bo( anyValue );
            }catch( final AssertionError | EmptyStackException | IllegalStateException ex ){
                expectedExceptionOccured = true;
            }//try
            assertTrue( expectedExceptionOccured );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Stress-Test: underflow after "pop()" - 1x"pop()" */
    @Test
    public void tol_4s_behavior_underflow_no5(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            doGeneralGuardTest( testName, requestedRefTypeWithPath );
            checkForForbiddenTypes( testName, requestedRefTypeName, ManualExceptionInvestigationRelatedAddOn.NONE );
            
            
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            //
            boolean expectedExceptionOccured = false;
            try{
                final Integer anyValue = stack.pop();
                fail( "undetected underflow" );
                TS.bo( anyValue );
            }catch( final AssertionError | EmptyStackException | IllegalStateException ex ){
                expectedExceptionOccured = true;
            }//try
            assertTrue( expectedExceptionOccured );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Stress-Test: underflow after "top()" - 1x"top()" */
    @Test
    public void tol_4s_behavior_underflow_no6(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            doGeneralGuardTest( testName, requestedRefTypeWithPath );
            checkForForbiddenTypes( testName, requestedRefTypeName, ManualExceptionInvestigationRelatedAddOn.NONE );
            
            
            @SuppressWarnings("unchecked")
            final Stack_I<Integer> stack = (Stack_I<Integer>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            //
            boolean expectedExceptionOccured = false;
            try{
                final Integer anyValue = stack.top();
                fail( "undetected underflow" );
                TS.bo( anyValue );
            }catch( final AssertionError | EmptyStackException | IllegalStateException ex ){
                expectedExceptionOccured = true;
            }//try
            assertTrue( expectedExceptionOccured );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    
    /** Kombinierter Extreme-Stress-Test "Stack" */
    @Test
    public void tol_4s_behavior_stressStack_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String requestedRefTypeName = "Stack";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            doGeneralGuardTest( testName, requestedRefTypeWithPath );
            checkForForbiddenTypes( testName, requestedRefTypeName, ManualExceptionInvestigationRelatedAddOn.NONE );
            
            
            @SuppressWarnings("unchecked")
            final Stack_I<Data> stack = (Stack_I<Data>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( stack );
            //\=> Stack was created
            
            final int stayOnTheSafeSideFactor = 3;      // Once should be enough, but stay on the safe side ;-)
            boolean expectedExceptionOccured = false;

            // keep things easy at beginning    ( similar testBehavior_Stack() )
            for( int testRun=0; testRun<19_000; testRun+=1_000 ){
                //
                assertTrue( stack.isEmpty() );
                for( int i=0+testRun; i<42+testRun; i++ ){
                    final Data expectedValueCpy = new Data( i );
                    stack.push( expectedValueCpy );
                    assertFalse( stack.isEmpty() );
                    Data computedValue = null;
                    for( int stillToDo=stayOnTheSafeSideFactor; --stillToDo>=0; ){
                        computedValue = stack.top();
                        assertEquals( expectedValueCpy, computedValue );
                        assertFalse( stack.isEmpty() );
                    }//for
                }//for
                
                for( int i=42+testRun; --i>=0+testRun; ){
                    assertFalse( stack.isEmpty() );
                    final Data expectedValueCpy = new Data( i );
                    Data computedValue = null;
                    for( int stillToDo=stayOnTheSafeSideFactor; --stillToDo>=0; ){
                        computedValue = stack.top();
                        assertEquals( expectedValueCpy, computedValue );
                        assertFalse( stack.isEmpty() );
                    }//for
                    final Data computedValuePop = stack.pop();
                    assertSame( computedValue, computedValuePop );
                    assertEquals( expectedValueCpy, computedValuePop );
                }//for
                assertTrue( stack.isEmpty() );
                //
            }//for
            
            // now stress it
            for( int testRun=0; testRun<17_000_000; testRun+=1_000_000 ){
                //
                assertTrue( stack.isEmpty() );
                for( int i=0+testRun; i<42+testRun; i++ ){
                    final Data someValue = new Data( i );
                    stack.push( someValue );
                    assertFalse( stack.isEmpty() );
                }//for
                
                for( int i=42+testRun; --i>=0+testRun; ){
                    assertFalse( stack.isEmpty() );
                    final Data expectedValueCpy = new Data( i );
                    final Data computedValue = stack.pop();
                    assertEquals( expectedValueCpy, computedValue );
                }//for
                assertTrue( stack.isEmpty() );
                
                try{
                    // causing underflow(s) by top()
                    for( int stillToDo=stayOnTheSafeSideFactor; --stillToDo>=0; ){
                        expectedExceptionOccured = false;
                        try{
                            final Data anyValue = stack.top();
                            fail( "undetected underflow" );
                            TS.bo( anyValue );
                        }catch( final AssertionError | EmptyStackException | IllegalStateException ex ){
                            expectedExceptionOccured = true;
                        }catch( final NullPointerException ex ){
                            fail( ex.getMessage() );
                        }catch( final Exception ex ){
                            TS.printInformationAboutUnexpectedException( testName, ex, ManualExceptionInvestigationRelatedAddOn.NONE );
                        }//try
                        assertTrue( expectedExceptionOccured );
                    }//for
                    //
                    // causing underflow(s) by pop()
                    for( int stillToDo=stayOnTheSafeSideFactor; --stillToDo>=0; ){
                        expectedExceptionOccured = false;
                        try{
                            final Data anyValue = stack.pop();
                            fail( "undetected underflow" );
                            TS.bo( anyValue );
                        }catch( final AssertionError | EmptyStackException | IllegalStateException ex ){
                            expectedExceptionOccured = true;
                        }catch( final NullPointerException ex ){
                            fail( ex.getMessage() );
                        }catch( final Exception ex ){
                            TS.printInformationAboutUnexpectedException( testName, ex, ManualExceptionInvestigationRelatedAddOn.NONE );
                        }//try
                        assertTrue( expectedExceptionOccured );
                    }//for
                }catch( final Exception ex ){
                    TS.printInformationAboutUnexpectedException( testName, ex, ManualExceptionInvestigationRelatedAddOn.NONE );
                }//try
                //
                assertTrue( stack.isEmpty() );
                
                try{
                    for( int stillToDo=stayOnTheSafeSideFactor; --stillToDo>=0; ){
                        // fill stack & 1* clear() before underflow
                        //
                        // fill stack
                        for( int i=0; i<7; i++ ){
                            final Data someValue = new Data( i );
                            stack.push( someValue );
                            assertFalse( stack.isEmpty() );
                        }//for
                        //
                        // clear
                        stack.clear();
                        //
                        // 1st: causing underflow(s) by pop()
                        assertTrue( stack.isEmpty() );
                        expectedExceptionOccured = false;
                        try{
                            final Data anyValue = stack.pop();
                            fail( "undetected underflow" );
                            TS.bo( anyValue );
                        }catch( final AssertionError | EmptyStackException | IllegalStateException ex ){
                            expectedExceptionOccured = true;
                        }catch( final Exception ex ){
                            TS.printInformationAboutUnexpectedException( testName, ex, ManualExceptionInvestigationRelatedAddOn.NONE );
                        }//try
                        assertTrue( expectedExceptionOccured );
                        //
                        // 2nd: causing underflow(s) by top()
                        expectedExceptionOccured = false;
                        try{
                            final Data anyValue = stack.top();
                            fail( "undetected underflow" );
                            TS.bo( anyValue );
                        }catch( final AssertionError | EmptyStackException | IllegalStateException ex ){
                            expectedExceptionOccured = true;
                        }catch( final Exception ex ){
                            TS.printInformationAboutUnexpectedException( testName, ex, ManualExceptionInvestigationRelatedAddOn.NONE );
                        }//try
                        assertTrue( expectedExceptionOccured );
                        //
                        assertTrue( stack.isEmpty() );
                        
                        
                        // fill stack & 1* clear() before several underflows
                        for( int i=0; i<5; i++ ){
                            final Data someData = new Data( i );
                            stack.push( someData );
                            assertFalse( stack.isEmpty() );
                        }//for
                        stack.clear();
                        //
                        // 1st: causing underflow(s) by top()
                        for( int subStillToDo=stayOnTheSafeSideFactor; --subStillToDo>=0; ){
                            expectedExceptionOccured = false;
                            try{
                                final Data anyValue = stack.top();
                                fail( "undetected underflow" );
                                TS.bo( anyValue );
                            }catch( final AssertionError | EmptyStackException | IllegalStateException ex ){
                                expectedExceptionOccured = true;
                            }//try
                            assertTrue( expectedExceptionOccured );
                        }//for
                        //
                        // 2nd: causing underflow(s) by pop()
                        for( int subStillToDo=stayOnTheSafeSideFactor; --subStillToDo>=0; ){
                            expectedExceptionOccured = false;
                            try{
                                final Data anyValue = stack.pop();
                                fail( "undetected underflow" );
                                TS.bo( anyValue );
                            }catch( final AssertionError | EmptyStackException | IllegalStateException ex ){
                                expectedExceptionOccured = true;
                            }//try
                            assertTrue( expectedExceptionOccured );
                        }//for
                        //
                        assertTrue( stack.isEmpty() );
                        
                        
                        // n * clear() before several unflow
                        //
                        // n * clear()
                        for( int subStillToDo=stayOnTheSafeSideFactor; --subStillToDo>=0; ){
                            stack.clear();
                        }//for
                        //
                        // causing underflow(s) by pop()
                        for( int subStillToDo=stayOnTheSafeSideFactor; --subStillToDo>=0; ){
                            expectedExceptionOccured = false;
                            try{
                                final Data anyValue = stack.pop();
                                fail( "undetected underflow" );
                                TS.bo( anyValue );
                            }catch( final AssertionError | EmptyStackException | IllegalStateException ex ){
                                expectedExceptionOccured = true;
                            }//try
                            assertTrue( expectedExceptionOccured );
                        }//for
                        //
                        // causing underflow(s) by top()
                        for( int subStillToDo=stayOnTheSafeSideFactor; --subStillToDo>=0; ){
                            expectedExceptionOccured = false;
                            try{
                                final Data anyValue = stack.top();
                                fail( "undetected underflow" );
                                TS.bo( anyValue );
                            }catch( final AssertionError | EmptyStackException | IllegalStateException ex ){
                                expectedExceptionOccured = true;
                            }//try
                            assertTrue( expectedExceptionOccured );
                        }//for
                        //
                        assertTrue( stack.isEmpty() );
                    }//for
                }catch( final NullPointerException ex ){
                    fail( ex.getMessage() );
                }catch( final Exception ex ){
                    TS.printInformationAboutUnexpectedException( testName, ex, ManualExceptionInvestigationRelatedAddOn.NONE );
                }//try
                assertTrue( stack.isEmpty() );
            }//for
            
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsTwoPoints ));
        }//if
    }//method()
    
    
    
    
    
    
    
    
    
    
    private void doGeneralGuardTest (
        final String testName,
        final String requestedRefTypeWithPath
    ){
        // test MULTIPLE objects
        try{
            final int testElemCnt = 7;
            final int ObjectCnt = 3;
          //final Stack_I<Character>[] stack = (Stack_I<Character>[])( new Stack[ObjectCnt] );
            final List<Stack_I<Character>> stackList = new ArrayList<Stack_I<Character>>();
            for( int objCnt=0; objCnt<ObjectCnt; objCnt++ ){
              //stack[objCnt] = (Stack_I<Character>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
              //assertNotNull( stack[objCnt] );
                @SuppressWarnings("unchecked")
                final Stack_I<Character> currentStack = (Stack_I<Character>)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                assertNotNull( currentStack,  "[Guard test failed]" );
                stackList.add( currentStack );
            }//for
            //\=> Stacks were created
            Character elem = null;
            
            // isEmpty
            for( int chkObjId=0; chkObjId<ObjectCnt; chkObjId++ ){
                final Stack_I<Character> sut =  stackList.get( chkObjId );
                assertTrue( sut.isEmpty(),  "[Guard test failed]" );
            }//for
            
            //------------------------------------------------------------------
            
            // push & isEmpty
            for( int wrkObjId=0; wrkObjId<ObjectCnt; wrkObjId++ ){
                final Stack_I<Character> wsut =  stackList.get( wrkObjId );
                for( int elemId=0; elemId<testElemCnt; elemId++ ){
                    elem = (char)( 'a' + elemId );
                    wsut.push( elem );
                }//for
                for( int chkObjId=0; chkObjId<ObjectCnt; chkObjId++ ){
                    final Stack_I<Character> csut = stackList.get( chkObjId );
                    if( wrkObjId >= chkObjId ){
                        assertFalse( csut.isEmpty(),  "[Guard test failed]" );
                        assertEquals( elem, csut.top(),  "[Guard test failed]" );
                    }else { //\=>wrkObjId < chkObjId
                        assertTrue( csut.isEmpty(),  "[Guard test failed]" );
                    }//if
                }//for
            }//for
            
            // pop & top & isEmpty
            for( int wrkObjId=0; wrkObjId<ObjectCnt; wrkObjId++ ){
                final Stack_I<Character> wsut = stackList.get( wrkObjId );
                for( int elemId=testElemCnt; --elemId>=0; ){
                    elem = (char)( 'a' + elemId );
                    assertEquals( elem, wsut.top(),  "[Guard test failed]" );
                    assertEquals( elem, wsut.pop(),  "[Guard test failed]" );
                }//for
                for( int chkObjId=0; chkObjId<ObjectCnt; chkObjId++ ){
                    final Stack_I<Character> csut =  stackList.get( chkObjId );
                    if( wrkObjId >= chkObjId ){
                        assertTrue( csut.isEmpty(),  "[Guard test failed]" );
                    }else { //\=>wrkObjId < chkObjId
                        assertFalse( csut.isEmpty(),  "[Guard test failed]" );
                        elem = (char)( 'a' + testElemCnt -1 );
                        assertEquals( elem, csut.top(),  "[Guard test failed]" );
                    }//if
                }//for
            }//for
            
            //------------------------------------------------------------------
            
            // push & isEmpty (once again)
            for( int wrkObjId=0; wrkObjId<ObjectCnt; wrkObjId++ ){
                final Stack_I<Character> wsut = stackList.get( wrkObjId );
                for( int elemId=0; elemId<testElemCnt; elemId++ ){
                    elem = (char)( 'a' + elemId );
                    wsut.push( elem );
                    assertEquals( elem, wsut.top(),  "[Guard test failed]" );
                    assertFalse( wsut.isEmpty(),  "[Guard test failed]" );
                }//for
                for( int chkObjId=0; chkObjId<ObjectCnt; chkObjId++ ){
                    final Stack_I<Character> csut = stackList.get( chkObjId );
                    if( wrkObjId >= chkObjId ){
                        assertFalse( csut.isEmpty(),  "[Guard test failed]" );
                        assertEquals( elem, csut.top(),  "[Guard test failed]" );
                    }else{  //\=>wrkObjId < chkObjId
                        assertTrue( csut.isEmpty(),  "[Guard test failed]" );
                    }//if
                }//for
            }//for
            
            // clear & top & isEmpty
            for( int wrkObjId=0; wrkObjId<ObjectCnt; wrkObjId++ ){
                final Stack_I<Character> wsut = stackList.get( wrkObjId );
                for( int elemId=testElemCnt; --elemId>=0; ){
                    wsut.clear();
                    assertTrue( wsut.isEmpty(),  "[Guard test failed]" );
                }//for
                for( int chkObjId=0; chkObjId<ObjectCnt; chkObjId++ ){
                    final Stack_I<Character> csut = stackList.get( chkObjId );
                    if( wrkObjId >= chkObjId ){
                        assertTrue( csut.isEmpty(),  "[Guard test failed]" );
                    }else { //\=>wrkObjId < chkObjId
                        assertFalse( csut.isEmpty(),  "[Guard test failed]" );
                        elem = (char)( 'a' + testElemCnt -1 );
                        assertEquals( elem, csut.top(),  "[Guard test failed]" );
                    }//if
                }//for
            }//for
            
            //------------------------------------------------------------------
            
            // push & isEmpty (once again)
            for( int wrkObjId=0; wrkObjId<ObjectCnt; wrkObjId++ ){
                final Stack_I<Character> wsut =  stackList.get( wrkObjId );
                for( int elemId=0; elemId<testElemCnt; elemId++ ){
                    elem = (char)( 'a' + elemId );
                    wsut.push( elem );
                }//for
                for( int chkObjId=0; chkObjId<ObjectCnt; chkObjId++ ){
                    final Stack_I<Character> csut = stackList.get( chkObjId );
                    if( wrkObjId >= chkObjId ){
                        assertFalse( csut.isEmpty(),  "[Guard test failed]" );
                        assertEquals( elem, csut.top(),  "[Guard test failed]" );
                    }else { //\=>wrkObjId < chkObjId
                        assertTrue( csut.isEmpty(),  "[Guard test failed]" );
                    }//if
                }//for
            }//for
            
            // pop & top & isEmpty (once again)
            for( int wrkObjId=0; wrkObjId<ObjectCnt; wrkObjId++ ){
                final Stack_I<Character> wsut = stackList.get( wrkObjId );
                for( int elemId=testElemCnt; --elemId>=0; ){
                    elem = (char)( 'a' + elemId );
                    assertEquals( elem, wsut.top(),  "[Guard test failed]" );
                    assertEquals( elem, wsut.pop(),  "[Guard test failed]" );
                }//for
                for( int chkObjId=0; chkObjId<ObjectCnt; chkObjId++ ){
                    final Stack_I<Character> csut = stackList.get( chkObjId );
                    if( wrkObjId >= chkObjId ){
                        assertTrue( csut.isEmpty(),  "[Guard test failed]" );
                    }else { //\=>wrkObjId < chkObjId
                        assertFalse( csut.isEmpty(),  "[Guard test failed]" );
                        elem = (char)( 'a' + testElemCnt -1 );
                        assertEquals( elem, csut.top(),  "[Guard test failed]" );
                    }//if
                }//for
            }//for
            
        }catch( final Exception ex ){
            fail( String.format( "[Guard test failed] -->> %s <<--",  ex.getMessage() ));
        }//try
    }//method()
    
    //##########################################################################
    //###
    //###   internal / local test support
    //###
    
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
    
    private void checkForForbiddenTypes(
        final String testName,
        final String requestedRefTypeName,
        final ManualExceptionInvestigationRelatedAddOn manualInvestigationRelatedAddOn
    ){
        final RefTypeInfoContainer refTypeInfoContainer = generateRefTypeInfoContainer( requestedRefTypeName );
        try {
            //
            //
            // check object state
            //
            if( TS.deepContainsForbiddenType( refTypeInfoContainer, new Class<?>[]{ Node[].class,  Map.class, List.class, Queue.class } )){
                switch( manualInvestigationRelatedAddOn ){
                    case NONE, PROVED_UNACCEPTABLE :
                        Herald.proclaimError( String.format(
                            "XXX currently classified as an error\n"
                          + "XXX\n"
                        ));
                        fail( String.format( "%s GUARD TEST : FORBIDDEN TYPE in object state detected",  testName ));
                        break;
                    case PROVED_ACCEPTABLE :
                        break;
                    default :
                        assert false : "Uuuupppss : INTERNAL programming error";
                        break;
                }//switch
            }//if
            //
            //
            // check source code
            //
            final Pattern[] unwantedPattern = { 
                Pattern.compile( "^(.*\\*/)?(.*;)?\\s*List\\s*.*$" ),                              //  ... List ...
                Pattern.compile( "^(.*\\*/)?(.*;)?\\s*Queue\\s*.*$" ),                             //  ... Queue ...
                Pattern.compile( "^(.*\\*/)?(.*;)?\\s*Map\\s*.*$" ),                               //  ... Map ...
                //
                Pattern.compile( "^(.*\\*/)?(.*;)?\\s*ArrayList\\s*.*$" ),                         //  ... ArrayList ...
                Pattern.compile( "^(.*\\*/)?(.*;)?\\s*LinkedList\\s*.*$" ),                        //  ... LinkedList ...
                Pattern.compile( "^(.*\\*/)?(.*;)?\\s*Stack\\s*.*$" ),                             //  ... Stack ...
                Pattern.compile( "^(.*\\*/)?(.*;)?\\s*Deque\\s*.*$" ),                             //  ... Deque ...
                Pattern.compile( "^(.*\\*/)?(.*;)?\\s*HashMap\\s*.*$" ),                           //  ... HashMap ...
                Pattern.compile( "^(.*\\*/)?(.*;)?\\s*TreeMap\\s*.*$" )                            //  ... TreeMap ...
            };
            if( TS.containsSuspiciousType( testName, refTypeInfoContainer, unwantedPattern )){
                switch( manualInvestigationRelatedAddOn ){
                    case NONE :
                        Herald.proclaimError( String.format(
                            "XXX currently classified as an error\n"
                          + "XXX\n"
                        ));
                        fail( String.format( "%s GUARD TEST : FORBIDDEN TYPE detected - manual checks have to be done",  testName ));
                        break;
                    case PROVED_ACCEPTABLE :
                        break;
                    case PROVED_UNACCEPTABLE :
                        Herald.proclaimError( String.format(
                            "XXX currently classified as an error\n"
                          + "XXX\n"
                        ));
                        fail( String.format( "%s GUARD TEST : FORBIDDEN TYPE detected !!!",  testName ));
                        break;
                    default :
                        assert false : "Uuuupppss : INTERNAL programming error";
                        break;
                }//switch
            }//if
            //
            // Still usefull ????
          //if( ! TS.doManualAcceptanceCheck() )  fail( String.format( "%s GUARD TEST : FORBIDDEN TYPE in object state detected by manual inspection",  testName ));
        }catch( final Throwable ex ){
            fail( String.format("%s",  ex.getMessage() ));
        }//try
    }
    
}//class
