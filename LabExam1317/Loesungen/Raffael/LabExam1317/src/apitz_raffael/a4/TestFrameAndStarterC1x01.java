// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package apitz_raffael.a4;


import static _untouchable_.supportC1x01.Configuration.dbgConfigurationVector;
import static _untouchable_.supportC1x01.PointDefinition.countsOnePoint;
import static _untouchable_.supportC1x01.PointDefinition.countsThreePoints;
import static _untouchable_.supportC1x01.PointDefinition.countsTwoPoints;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
import _untouchable_.thingy.Color;
import _untouchable_.thingy.Item;
import _untouchable_.thingy.Size;
import _untouchable_.thingy.Weight;
import _untouchable_.thingy.internalThingySupport.ItemFabric;
import java.util.ArrayList;
import java.util.List;
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
    final static private TE exercise = TE.A4;                                   // <<<<===  _HERE_
    final static private String packagePath = getPackagePath();
    final static private String rootPackageName = getRootPackageName();
    
    final static private boolean enableAutomaticEvaluation  =  ( 0 > dbgConfigurationVector );
    
    
    
    // variable(s) - since the methods are static, the following variables have to be static
    
    // TestFrame "state"
    static private TestResultDataBaseManager  dbManager;
    
    // optimization blocker support - have to be mutable even so they never change to block optimization
    static boolean fTrue = true;                                                // package scope on purpose
    static boolean tTrue = true;                                                // package scope on purpose
    
    
    
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
    // No constructor was requested for Triple.
    // Hence, no Triple-object can be generated.
    
    /** Ausgabe auf Bildschirm zur visuellen Kontrolle */
    @Test
    public void tol_1e_printSupportForManualReview_CC(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        final boolean dbgLocalOutputEnable = ( 0 != ( dbgConfigurationVector & 0x0200 ));
        if( dbgLocalOutputEnable ){
            System.out.printf( "\n\n" );
            System.out.printf( "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv\n" );
            System.out.printf( "%s():\n",  testName );
            System.out.printf( "\n\n" );    
            
            // prepare test parameter
            final String someText = "abcde";                                        // just...
            final List<Character> characterListAsTestParameter = new ArrayList<Character>();   //...anything
            for( final char c : someText.toCharArray() )  characterListAsTestParameter.add( c );   //...;-)
            //
            // actual test
            try{
                TS.printDetailedInfoAboutClass( requestedRefTypeWithPath );
                System.out.printf( "\n" );
                //
                final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                assertNotNull( itemProcessor );
                //\=> ItemProcessor was created
                TS.printDetailedInfoAboutObject( itemProcessor, "ItemProcessor" );
                //
                if( TS.isActualMethod( itemProcessor.getClass(), "toString", String.class, null )){
                    System.out.printf( "~.toString(): \"%s\"     again ;-)\n",  itemProcessor.toString() );
                }else{
                    System.out.printf( "NO! toString() implemented by class \"%s\" itself\n",  itemProcessor.getClass().getSimpleName() );
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
    
    /** Testing: "ItemProcessor" */
    @Test
    public void tol_1e_classExistence_ItemProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
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
    
    /** Testing: Eigenschaften des Referenz-Typs ItemProcessor incl. Constructor */
    @Test
    public void tol_1e_properties_ItemProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
            assertTrue( TS.isClass(             classUnderTest ),                       String.format( "requested class %s missing", requestedRefTypeName ));
            assertTrue( TS.isClassPublic(       classUnderTest ),                       "false class access modifier" );
            assertTrue( TS.isImplementing(      classUnderTest,   "ItemProcessor_I" ),  "requested supertype missing" );
            assertTrue( TS.isConstructor(       classUnderTest ),                       "requested constructor missing" );
            assertTrue( TS.isConstructorPublic( classUnderTest ),                       "false constructor access modifier" );
        }catch( final ClassNotFoundException ex ){
            fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: Eigenschaften "ItemProcessor" - Access Modifier Methoden */
    @Test
    public void tol_1e_propertiesMethods_ItemProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
            assertTrue( TS.isFunction(        classUnderTest, "process",        new Class<?>[]{ Item.class }, Triple_I.class ), "requested method missing" );
            assertTrue( TS.isFunction(        classUnderTest, "itemsProcessed",                               int.class ),      "requested method missing");
            assertTrue( TS.isFunction(        classUnderTest, "triplesFound",                                 int.class ),      "requested method missing" );
            assertTrue( TS.isProcedure(       classUnderTest, "reset" ),                                                        "requested method missing" );
            assertTrue( TS.isFunctionPublic(  classUnderTest, "process",        new Class<?>[]{ Item.class }, Triple_I.class ), "false method access modifier" );
            assertTrue( TS.isFunctionPublic(  classUnderTest, "itemsProcessed",                               int.class ),      "false method access modifier" );
            assertTrue( TS.isFunctionPublic(  classUnderTest, "triplesFound",                                 int.class ),      "false method access modifier" );
            assertTrue( TS.isProcedurePublic( classUnderTest, "reset" ),                                                        "false method access modifier" );
            
        }catch( final ClassNotFoundException ex ){
            fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: Eigenschaften "ItemProcessor" - Access Modifier Variablen */
    @Test
    public void tol_1e_propertiesFields_ItemProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
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
    
    /* Testing: Eigenschaften "ItemProcessor" - Schreibweise Methoden */
    @Test
    public void tol_1e_notationMethods_ItemProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
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
    
    /** Testing: Eigenschaften "ItemProcessor" - Schreibweise Variablen */
    @Test
    public void tol_1e_notationFields_ItemProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
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
    
    /** Testing: Kann überhaupt ein Objekt erzeugt werden? - "ItemProcessor erzeugen" (With Parameter) */
    @Test
    public void tol_1e_objectCreationWP_ItemProcessor(){                        // WP ::= With Parameter
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            // NO crash yet => success ;-)
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.CREATION ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    /** Testing: (NUR-)Methoden-Aufruf "process()" */
    @Test
    public void tol_1e_behavior_process(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // prepare test parameter
        final Item testParameter = new Item( Color.CYAN, Size.MEDIUM, Weight.MEDIUM, 42L );
        
        // actual test
        try{
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            final Triple_I anyTriple = itemProcessor.process( testParameter );
            assertTrue( null==anyTriple ? tTrue : fTrue );
            // NO crash yet => success ;-)
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: (NUR-)Methoden-Aufruf "itemsProcessed()" */
    @Test
    public void tol_1e_behavior_getCurrentArray(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            final Integer anyValue = itemProcessor.itemsProcessed();
            assertNotNull( anyValue );
            // NO crash yet => success ;-)
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
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            final Integer anyValue = itemProcessor.triplesFound();
            assertNotNull( anyValue );
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
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            itemProcessor.reset();
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
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            //
            Triple_I anyTriple = itemProcessor.process( new Item( Color.values()[0], Size.values()[0], Weight.values()[0], 0L ) );
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            Integer anyIntValue;
            //
            anyIntValue = itemProcessor.itemsProcessed();
            assertNotNull( anyIntValue );
            //
            anyIntValue = itemProcessor.triplesFound();
            assertNotNull( anyIntValue );
            //
            itemProcessor.reset();
            //
            anyIntValue = itemProcessor.itemsProcessed();
            assertNotNull( anyIntValue );
            //
            anyIntValue = itemProcessor.triplesFound();
            assertNotNull( anyIntValue );
            //
            anyTriple = itemProcessor.process( new Item( Color.values()[1], Size.values()[1], Weight.values()[1], 1L ) );
            assertTrue( null==anyTriple ? tTrue : fTrue );
            //
            anyIntValue = itemProcessor.itemsProcessed();
            assertNotNull(anyIntValue );
            //
            anyIntValue = itemProcessor.triplesFound();
            assertNotNull( anyIntValue );
            // NO crash yet => success ;-)
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Testing: itemsProcessed() */
    @Test
    public void tol_2b_behavior_itemsProcessed_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            final Item testParameter = new Item( Color.values()[2], Size.values()[1], Weight.values()[0], 42L );
            assertEquals( 0, itemProcessor.itemsProcessed() );
            itemProcessor.process( testParameter );
            assertEquals( 1, itemProcessor.itemsProcessed() );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Testing: process() */
    @Test
    public void tol_2b_behavior_process_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            // test with 2 items of equal weight
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            int itemCreationId;
            for( itemCreationId=0; itemCreationId<2; itemCreationId++ ){
                final Item currentItem = new Item(
                    Color.values()[ itemCreationId % Color.values().length ],
                    Size.values()[  itemCreationId % Size.values().length ],
                    Weight.HEAVY,
                    (long)( itemCreationId )
                );
                assertNull( itemProcessor.process( currentItem ));
            }//for
            //
            // and now the 3rd item of equal weight
            final Item currentItem = new Item(
                Color.values()[ itemCreationId % Color.values().length ],
                Size.values()[  itemCreationId % Size.values().length ],
                Weight.HEAVY,
                (long)( itemCreationId )
            );
            final Triple_I computedResult = itemProcessor.process( currentItem );
            assertNotNull( computedResult );
            //\=> since NOT null, it's a Triple_I
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Testing: process() */
    @Test
    public void tol_2b_behavior_triplesFound_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            // test with 2 items of equal weight
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            int itemCreationId;
            for( itemCreationId=0; itemCreationId<2; itemCreationId++ ){
                final Item currentItem = new Item(
                    Color.values()[ itemCreationId % Color.values().length ],
                    Size.values()[  itemCreationId % Size.values().length ],
                    Weight.HEAVY,
                    (long)( itemCreationId )
                );
                itemProcessor.process( currentItem );
                assertEquals( 0, itemProcessor.triplesFound());
            }//for
            //
            // and now the 3rd item of equal weight
            final Item currentItem = new Item(
                Color.values()[ itemCreationId % Color.values().length ],
                Size.values()[  itemCreationId % Size.values().length ],
                Weight.HEAVY,
                (long)( itemCreationId )
            );
            itemProcessor.process( currentItem );
            assertEquals( 1, itemProcessor.triplesFound());
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Testing: reset() */
    @Test
    public void tol_2b_behavior_reset_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            // test with 2 items of equal weight
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            int itemCreationId;
            for( itemCreationId=0; itemCreationId<2; itemCreationId++ ){
                final Item currentItem = new Item(
                    Color.values()[ itemCreationId % Color.values().length ],
                    Size.values()[  itemCreationId % Size.values().length ],
                    Weight.HEAVY,
                    (long)( itemCreationId )
                );
                final Triple_I computedResult = itemProcessor.process( currentItem );
                assertNull( computedResult, String.format(
                    "null was expected ;  last incoming item was %s ;  unexpectly reported triple is %s",
                    currentItem,
                    convertTripleToString( computedResult )
                ));
                assertEquals( 0, itemProcessor.triplesFound() );
                assertNotEquals( 0, itemProcessor.itemsProcessed() );
            }//for
            //
            // reset before 3rd item with equal weight
            itemProcessor.reset();
            //
            assertEquals( 0, itemProcessor.itemsProcessed() );
            assertEquals( 0, itemProcessor.triplesFound() );
            //
            // test with 3rd item of equal weight (reset was done before)
            final Item currentItem = new Item(
                Color.values()[ itemCreationId % Color.values().length ],
                Size.values()[  itemCreationId % Size.values().length ],
                Weight.HEAVY,
                (long)( itemCreationId )
            );
            final Triple_I computedResult = itemProcessor.process( currentItem );
            assertNull( computedResult, String.format(
                "null was expected ;  last incoming item was %s ;  unexpectly reported triple is %s",
                currentItem,
                convertTripleToString( computedResult )
            ));
            assertEquals( 0, itemProcessor.triplesFound() );
            assertNotEquals( 0, itemProcessor.itemsProcessed() );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: reset() */
    @Test
    public void tol_2b_behavior_reset_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        // reset after each processed item there will NEVER be three equal item weights and hence NOT any triplet
        try{
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            Triple_I computedResult;
            for( int itemCreationId=0; itemCreationId<17; itemCreationId++ ){
                final Item currentItem = new Item(
                    Color.values()[ itemCreationId % Color.values().length ],
                    Size.values()[  itemCreationId % Size.values().length ],
                    Weight.LIGHT,
                    (long)( itemCreationId )
                );
                computedResult = itemProcessor.process( currentItem );
                assertNull( computedResult, String.format(
                    "null was expected ;  last incoming item was %s ;  unexpectly reported triple is %s",
                    currentItem,
                    convertTripleToString( computedResult )
                ));
                assertEquals( 0, itemProcessor.triplesFound() );
                assertNotEquals( 0, itemProcessor.itemsProcessed() );
                itemProcessor.reset();
                assertEquals( 0, itemProcessor.triplesFound() );
                assertEquals( 0, itemProcessor.itemsProcessed() );
            }//for
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
    
    /** Funktions-Test: process() */
    @Test
    public void tol_3n_behavior_process_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            final Item[] expectedResult = new Item[3];
            int itemCreationId;
            //
            // test with 2 items of equal weight
            for( itemCreationId=0; itemCreationId<2; itemCreationId++ ){
                final Item currentItem = new Item(
                    Color.values()[ itemCreationId % Color.values().length ],
                    Size.values()[  itemCreationId % Size.values().length ],
                    Weight.MEDIUM,
                    (long)( itemCreationId )
                );
                assertNull( itemProcessor.process( currentItem ));
                expectedResult[itemCreationId] = currentItem;
            }//for
            //
            // and now the 3rd item of equal weight
            final Item currentItem = new Item(
                Color.values()[ itemCreationId % Color.values().length ],
                Size.values()[  itemCreationId % Size.values().length ],
                Weight.MEDIUM,
                (long)( itemCreationId )
            );
            final Triple_I computedResult = itemProcessor.process( currentItem );
            assertNotNull( computedResult );
            expectedResult[itemCreationId] = currentItem;
            //
            // was correct triple computed ?
            final Item reported1stItem = computedResult.getItem(0);
            final Item reported2ndItem = computedResult.getItem(1);
            final Item reported3rdItem = computedResult.getItem(2);
            assertArrayEquals(
                expectedResult,
                new Item[]{ reported1stItem, reported2ndItem, reported3rdItem },
                "triple is NOT as expected"
            );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Funktions-Test: process() */
    @Test
    public void tol_3n_behavior_process_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final int numberOfWeights = Weight.values().length;
            final int numberOfIterations = 2 * numberOfWeights;
            final ItemFabric itemFabric = new ItemFabric();
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            int creationId;
            //
            for( creationId=0; creationId<numberOfIterations; creationId++ ){
                final Item currentItem = itemFabric.createDeterministicItem( creationId );
                final Triple_I computedResult = itemProcessor.process( currentItem );
                assertNull( computedResult, String.format(
                    "null was expected ;  last incoming item was %s ;  unexpectly reported triple is %s",
                    currentItem,
                    convertTripleToString( computedResult )
                ));
            }//for
            //
            final Item currentItem = itemFabric.createDeterministicItem( creationId );
            final Triple_I computedResult = itemProcessor.process( currentItem );
            assertNotNull( computedResult, String.format(
                "Triple was expected  but null was computed ;  last incoming item was %s",
                currentItem
            ));
            //
            // was correct triple computed ?
            final Item reported1stItem = computedResult.getItem(0);
            final Item reported2ndItem = computedResult.getItem(1);
            final Item reported3rdItem = computedResult.getItem(2);
            final Item expected1stItem = itemFabric.createDeterministicItem( 0*numberOfWeights );
            final Item expected2ndItem = itemFabric.createDeterministicItem( 1*numberOfWeights );
            final Item expected3rdItem = itemFabric.createDeterministicItem( 2*numberOfWeights );
            assertArrayEquals(
                new Item[]{ expected1stItem, expected2ndItem, expected3rdItem },
                new Item[]{ reported1stItem, reported2ndItem, reported3rdItem },
                "Triple is NOT as expected"
            );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Funktions-Test: process() */
    @Test
    public void tol_3n_behavior_process_no3(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final int numberOfWeights = Weight.values().length;
            final int numberOfIterations = 2 * numberOfWeights;
            final ItemFabric itemFabric = new ItemFabric();
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            int creationId;
            //
            for( creationId=0; creationId<numberOfIterations; creationId++ ){
                final Item currentItem = itemFabric.createDeterministicItem( creationId );
                final Triple_I computedResult = itemProcessor.process( currentItem );
                assertNull( computedResult, String.format(
                    "null was expected ;  last incoming item was %s ;  unexpectly reported triple is %s",
                    currentItem,
                    convertTripleToString( computedResult )
                ));
            }//for
            //
            for( int offSet=numberOfWeights; --offSet>=0; ){
                creationId = numberOfIterations+offSet;
                final Item currentItem = itemFabric.createDeterministicItem( creationId );
                final Triple_I computedResult = itemProcessor.process( currentItem );
                assertNotNull( computedResult, String.format(
                    "Triple was expected  but null was computed ;  last incoming item was %s",
                    currentItem
                ));
                //
                // was correct triple computed ?
                final Item reported1stItem = computedResult.getItem(0);
                final Item reported2ndItem = computedResult.getItem(1);
                final Item reported3rdItem = computedResult.getItem(2);
                final Item expected1stItem = itemFabric.createDeterministicItem( creationId - 2*numberOfWeights );
                final Item expected2ndItem = itemFabric.createDeterministicItem( creationId - 1*numberOfWeights );
                final Item expected3rdItem = itemFabric.createDeterministicItem( creationId - 0*numberOfWeights );
                assertArrayEquals(
                    new Item[]{ expected1stItem, expected2ndItem, expected3rdItem },
                    new Item[]{ reported1stItem, reported2ndItem, reported3rdItem },
                    "Triple is NOT as expected"
                );
            }//for
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Funktions-Test: triplesFound() */
    @Test
    public void tol_3n_behavior_triplesFound_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            // test with 2 items of equal weight
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            int itemCreationId;
            for( itemCreationId=0; itemCreationId<2; itemCreationId++ ){
                final Item currentItem = new Item(
                    Color.values()[ itemCreationId % Color.values().length ],
                    Size.values()[  itemCreationId % Size.values().length ],
                    Weight.MEDIUM,
                    (long)( itemCreationId )
                );
                final Triple_I anyComputedTriple = itemProcessor.process( currentItem );
                assertEquals( 0, itemProcessor.triplesFound() );
                TS.bo( anyComputedTriple );
            }//for
            //
            // and now the 3rd item of equal weight
            final Item currentItem = new Item(
                Color.values()[ itemCreationId % Color.values().length ],
                Size.values()[  itemCreationId % Size.values().length ],
                Weight.MEDIUM,
                (long)( itemCreationId )
            );
            final Triple_I anyComputedTriple = itemProcessor.process( currentItem );
            assertEquals( 1, itemProcessor.triplesFound() );
            TS.bo( anyComputedTriple );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Funktions-Test: triplesFound() */
    @Test
    public void tol_3n_behavior_triplesFound_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final int numberOfWeights = Weight.values().length;
            final int numberOfIterations = 2 * numberOfWeights;
            final ItemFabric itemFabric = new ItemFabric();
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            int creationId;
            //
            for( creationId=0; creationId<numberOfIterations; creationId++ ){
                final Item currentItem = itemFabric.createDeterministicItem( creationId );
                final Triple_I anyComputedTriple = itemProcessor.process( currentItem );
                assertEquals( 0, itemProcessor.triplesFound() );
                TS.bo( anyComputedTriple );
            }//for
            //
            final Item currentItem = itemFabric.createDeterministicItem( creationId );
            final Triple_I anyComputedTriple = itemProcessor.process( currentItem );
            assertEquals( 1, itemProcessor.triplesFound() );
            TS.bo( anyComputedTriple );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Funktions-Test: triplesFound() */
    @Test
    public void tol_3n_behavior_triplesFound_no3(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final int numberOfWeights = Weight.values().length;
            final int numberOfIterations = 2 * numberOfWeights;
            final ItemFabric itemFabric = new ItemFabric();
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            int creationId;
            //
            for( creationId=0; creationId<numberOfIterations; creationId++ ){
                final Item currentItem = itemFabric.createDeterministicItem( creationId );
                final Triple_I anyComputedTriple = itemProcessor.process( currentItem );
                assertEquals( 0, itemProcessor.triplesFound() );
                TS.bo( anyComputedTriple );
            }//for
            //
            for( int expectedResult=1, offSet=numberOfWeights;  --offSet>=0;  expectedResult++ ){
                creationId = numberOfIterations+offSet;
                final Item currentItem = itemFabric.createDeterministicItem( creationId );
                final Triple_I anyComputedTriple = itemProcessor.process( currentItem );
                assertEquals( expectedResult, itemProcessor.triplesFound() );
                TS.bo( anyComputedTriple );
            }//for
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Funktions-Test: itemsProcessed() */
    @Test
    public void tol_3n_behavior_itemsProcessed_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final int justAFactor = 2;
            final int numberOfValues = 7;
            final ItemFabric itemFabric = new ItemFabric( numberOfValues );
            final int numberOfIterations = justAFactor
                                         * Color.values().length
                                         * Size.values().length
                                         * numberOfValues
                                         * Weight.values().length;
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            int creationId;
            for( creationId=0; creationId<numberOfIterations; creationId++ ){
                assertEquals( creationId, itemProcessor.itemsProcessed() );
                final Item currentItem = itemFabric.createDeterministicItem( creationId );
                final Triple_I anyComputedTriple = itemProcessor.process( currentItem );
                TS.bo( anyComputedTriple );
            }//for
            assertEquals( creationId, itemProcessor.itemsProcessed() );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    
    
    /** Funktions-Test: combined */
    @Test
    public void tol_3n_behavior_process_itemSequence_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        try{
            final int justAFactor = 7;
            final int numberOfColors = Color.values().length;
            final int numberOfSizes = Size.values().length;
            final int numberOfValues = 13;
            final int numberOfWeights = Weight.values().length;
            final ItemFabric itemFabric = new ItemFabric( numberOfValues );
            final int numberOfIterations = justAFactor
                                         * numberOfColors
                                         * numberOfSizes
                                         * numberOfValues
                                         * numberOfWeights;
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            int expectedTripleCnt = 0;
            int creationId;
            //
            for( creationId=0; creationId<numberOfIterations; creationId++ ){
                assertEquals( creationId, itemProcessor.itemsProcessed() );
                final Item currentItem = itemFabric.createDeterministicItem( creationId );
                final Triple_I computedResult = itemProcessor.process( currentItem );
                if( isTripleExpected( creationId, numberOfColors, numberOfSizes, numberOfValues, numberOfWeights )){
                    
                    expectedTripleCnt++;
                    final int computedTripleCnt = itemProcessor.triplesFound();
                    assertEquals( expectedTripleCnt, computedTripleCnt );
                    
                    assertNotNull( computedResult, String.format(
                        "triple was expected  but null was computed ;  last incoming item was %s",
                        currentItem
                    ));
                    assertTrue( computedResult instanceof Triple_I, "TYPE-CONFLICT-ERROR" );
                    //
                    // was correct triple computed ?
                    final Item reported1stItem = computedResult.getItem(0);
                    final Item reported2ndItem = computedResult.getItem(1);
                    final Item reported3rdItem = computedResult.getItem(2);
                    final Item expected1stItem = itemFabric.createDeterministicItem( creationId - 2*numberOfWeights );
                    final Item expected2ndItem = itemFabric.createDeterministicItem( creationId - 1*numberOfWeights );
                    final Item expected3rdItem = itemFabric.createDeterministicItem( creationId - 0*numberOfWeights );
                    assertArrayEquals(
                        new Item[]{ expected1stItem, expected2ndItem, expected3rdItem },
                        new Item[]{ reported1stItem, reported2ndItem, reported3rdItem },
                        "triple is NOT as expected"
                    );
                    
                }else{
                    
                    final int computedTripleCnt = itemProcessor.triplesFound();
                    assertEquals( expectedTripleCnt, computedTripleCnt );
                    
                    assertNull( computedResult, String.format(
                        "null was expected ;  last incoming item was %s ;  unexpectly reported triple is %s",
                        currentItem,
                        convertTripleToString( computedResult )
                    ));
                    
                }//if
            }//for
            assertEquals( creationId, itemProcessor.itemsProcessed() );
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
    
    /** Check for TODOs inside Code marking code NOT ready. */
    @Test
    public void tol_4s_containsSuspiciousTodoMarks(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        for( final String requestedRefTypeName : new String[]{ "ItemProcessor", "Triple" } ){;
            final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
            
            if( requestedRefTypeName.equals( "ItemProcessor" )){
                // guard test
                doGeneralGuardTest( requestedRefTypeName, requestedRefTypeWithPath, turnOnAdditionalGuardTests );
            }//if
            
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
    
    
    
    /** Stress-Test: parameter is null" */
    @Test
    public void tol_4s_behavior_parameter_null_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        doGeneralGuardTest( requestedRefTypeName, requestedRefTypeWithPath, turnOffAdditionalGuardTests );
        //
        // start of actual test
        try{
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            boolean expectedExceptionOccured = false;
            try{
                final Triple_I anyComputedTriple = itemProcessor.process( null );
                assertTrue( null==anyComputedTriple ? tTrue : fTrue );
            }catch( final Throwable ex ){
                TS.examineExceptionForIllegalArgumentCauseAndReact( testName, ex );
                expectedExceptionOccured = true;
            }//try
            //\=> either AssertionError|IllegalArgumentException or NO exception at all
            assertTrue( expectedExceptionOccured,  "undetected illegal argument -> null accepted" );
        }catch( final TestSupportException ex ){
            fail( String.format("%s",  ex.getMessage() ));
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    /** Funktions-Test: combined focus on reset() ;  dieser Test könnte länger dauern (abhängig vom Rechner)*/
    @Test
    public void tol_4s_behavior_combinedIncludingReset_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        
        doGeneralGuardTest( requestedRefTypeName, requestedRefTypeWithPath, turnOnAdditionalGuardTests );
        //
        // start of actual test
        try{
            // Somesome setup
            final int numberOfElementsPerTriple = 3;
            //
            // Thingy/Item setup
            final int numberOfColors = Color.values().length;
            final int numberOfSizes = Size.values().length;
            final int numberOfValues = 11;
            final int numberOfWeights = Weight.values().length;
            final ItemFabric itemFabric = new ItemFabric( numberOfValues );
            //
            // Test setup
            final int numberOfRuns = 2;
            final int numberOfItemCreationsPerRun = numberOfColors
                                                  * numberOfSizes
                                                  * numberOfValues
                                                  * numberOfWeights
                                                  * 2;  // <- repetition factor
            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            assertNotNull( itemProcessor );
            //\=> ItemProcessor was created
            
            int expectedItemCnt = 0;
            for( int runId=0; runId<numberOfRuns; runId++ ){
                assertEquals( expectedItemCnt, itemProcessor.itemsProcessed() );
                //
                itemProcessor.reset();  // <<<<=================================
                expectedItemCnt = 0;
                int creationId = 0;
                while( creationId < numberOfItemCreationsPerRun ){
                    assertEquals( expectedItemCnt, itemProcessor.itemsProcessed() );
                    //
                    final Item currentItem = itemFabric.createDeterministicItem( creationId );
                    final Triple_I computedTriple = itemProcessor.process( currentItem );
                    assertNull( computedTriple, String.format(
                        "null was expected ;  last incoming item was %s ;  unexpectly reported triple is %s",
                        currentItem,
                        convertTripleToString( computedTriple )
                    ));
                    expectedItemCnt++;  // <<<<=================================
                    creationId++;       // <<<<=================================
                    //
                    final int computedTripleCnt = itemProcessor.triplesFound();
                    assertEquals( 0, computedTripleCnt );
                    assertEquals( expectedItemCnt, itemProcessor.itemsProcessed() );
                    //
                    if( expectedItemCnt == numberOfWeights * (numberOfElementsPerTriple-1) ){
                        itemProcessor.reset();  // <<<<=========================
                        expectedItemCnt = 0;
                    }//if
                }//while
            }//for
            assertEquals( expectedItemCnt, itemProcessor.itemsProcessed() );
            //
            itemProcessor.reset();      // <<<<=================================
            
            
            final int numberOfItemCreations = numberOfColors
                                            * numberOfSizes
                                            * numberOfValues
                                            * numberOfWeights
                                            * 7;    // <- repetition factor
            int expectedTripleCnt = 0;
            int creationId;
            for( creationId=0; creationId<numberOfItemCreations; creationId++ ){
                assertEquals( creationId, itemProcessor.itemsProcessed() );
                final Item currentItem = itemFabric.createDeterministicItem( creationId );
                final Triple_I computedTriple = itemProcessor.process( currentItem );
                if( isTripleExpected( creationId, numberOfColors, numberOfSizes, numberOfValues, numberOfWeights )){
                    //\=> triple expected
                    assertNotNull( computedTriple, String.format(
                        "triple was expected  but null was computed ;  last incoming item was %s",
                        currentItem
                    ));
                    //
                    expectedTripleCnt++;
                    final int computedTripleCnt = itemProcessor.triplesFound();
                    assertEquals( expectedTripleCnt, computedTripleCnt );
                    //
                    // was correct triple computed ?
                    final Item reported1stItem = computedTriple.getItem(0);
                    final Item reported2ndItem = computedTriple.getItem(1);
                    final Item reported3rdItem = computedTriple.getItem(2);
                    final Item expected1stItem = itemFabric.createDeterministicItem( creationId - 2*numberOfWeights );
                    final Item expected2ndItem = itemFabric.createDeterministicItem( creationId - 1*numberOfWeights );
                    final Item expected3rdItem = itemFabric.createDeterministicItem( creationId - 0*numberOfWeights );
                    assertArrayEquals(
                        new Item[]{ expected1stItem, expected2ndItem, expected3rdItem },
                        new Item[]{ reported1stItem, reported2ndItem, reported3rdItem },
                        "triple is NOT as expected"
                    );
                    
                }else{
                    //\=> triple NOT expected
                    assertNull( computedTriple, String.format(
                        "null was expected ;  last incoming item was %s ;  unexpectly reported triple is %s",
                        currentItem,
                        convertTripleToString( computedTriple )
                    ));
                    //
                    final int computedTripleCnt = itemProcessor.triplesFound();
                    assertEquals( expectedTripleCnt, computedTripleCnt );
                }//if
            }//for
            assertEquals( creationId, itemProcessor.itemsProcessed() );
        }catch( final TestSupportException ex ){
            fail( String.format("%s",  ex.getMessage() ));
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsThreePoints ));
        }//if
    }//method()
    
    
    
    
    
    
    
    
    
    
    //##########################################################################
    //###
    //###   internal / local test support
    //###
    
    private static String convertTripleToString( final Triple_I triple ){                                       // <<<<==== adapt here
        return convertSomesomeToString( triple, 3 );                                                            // <<<<==== adapt here
    }//method()
    //
    private static String convertSomesomeToString( final Triple_I somesome,  final int numberOfElements ){      // <<<<==== adapt here
        assert null==somesome || isSomesomeSetupOk(somesome.getClass().getSimpleName(),numberOfElements) : iGenerateMessage(somesome.getClass().getSimpleName());
        
        // action
        final StringBuffer sb = new StringBuffer();
        if( null==somesome ){
            sb.append( "null" );
        }else{
            try {
                final int maxIndexValue = numberOfElements -1;
                sb.append( "[ " );
                for( int i=0; i<numberOfElements; i++ ){
                    final Item currentItem = somesome.getItem(i);
                    if( null==currentItem ){
                        sb.append( "null" );
                    }else{
                        sb.append( currentItem.toString());
                    }//if
                    if( maxIndexValue > i ){
                        sb.append( " , " );
                    }//if
                }//for
                sb.append( " ]" );
            }catch( final Exception ex ){
                // es kann eigentlich nur getItem() das auslösende Problem sein
                sb.append( ex.getMessage() );
            }//try
        }//if
        return sb.toString();
    }//method()
    //
    private static boolean isSomesomeSetupOk( final String somesomeClassName,  final int numberOfElements ){
        switch( somesomeClassName ){
            case "Threesome", "Triple" :
                return 3 == numberOfElements;
            case "Foursome", "Quartet" :
                return 4 == numberOfElements;
            case "Fivesome", "Quintet" :
                return 5 == numberOfElements;
            case "Sixsome", "Sextet" :
                return 6 == numberOfElements;
            case "Sevensome", "Septet" :
                return 7 == numberOfElements;
            case "Eightsome", "Octet" :
                return 8 == numberOfElements;
            default:
                // uncheckable
                return true;
        }//switch
        
    }
    //
    private static String iGenerateMessage( final String somesomeClassName ){
        return String.format(
            "Uuupps : Internal self check detects  either configuration error  or false class naming by student -> %s",
            somesomeClassName
        );
    }//method()
    
    
    
    
    
    private boolean isTripleExpected (
        final int creationId,
        final int numberOfColors,
        final int numberOfSizes,
        final int numberOfValues,
        final int numberOfWeights
    ){
        final int numberOfElementsInTriple = 3;
        return (( creationId / numberOfWeights ) % numberOfElementsInTriple  ==  ( numberOfElementsInTriple -1 ));
        //
        // Note
        // ====
        //              creationId
        // -----------------------------------   %   "wieviele brauche ich?"  <->  "nur das letzte fehlt noch"
        // "wieviele unterschiedliche gibt es?"
        //
    }//method()
    
    
    
    
    
    final static private boolean turnOnAdditionalGuardTests = true;
    final static private boolean turnOffAdditionalGuardTests = false;
    //
    private void doGeneralGuardTest (
        final String testName,
        final String requestedRefTypeWithPath,
        final boolean enableAdditionalGuardTests
    ){
        // test uses MULTIPLE OBJECTs of ItemProcessor on purpose
        try{
            final int numberOfWeights = Weight.values().length;
            final int numberOfElementsInTriple = 3;
            final int numberOfItemCreations = numberOfWeights * (numberOfElementsInTriple -1);
            //
            final int numberOfItemProcessorObjects = 7;
            final ItemFabric itemFabric = new ItemFabric();
            final ItemProcessor_I itemProcessor[] = new ItemProcessor_I[numberOfItemProcessorObjects];
            //
            // 1st(!) create ItemProcessor objects
            for( int itemProcessorId=0; itemProcessorId<numberOfItemProcessorObjects; itemProcessorId++ ){
                itemProcessor[itemProcessorId] = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                assertNotNull( itemProcessor[itemProcessorId] );
                //\=> ItemProcessor was created
            }//for
            //
            // 2nd(!) use ItemProcessor objects (after creation of all ItemProcessor objects is done)
            //
            for( int itemProcessorId=0; itemProcessorId<numberOfItemProcessorObjects; itemProcessorId++ ){
                for( int itemCreationId=0; itemCreationId<numberOfItemCreations; itemCreationId++ ){
                    final Item guardSomeParameter = itemFabric.createDeterministicItem( itemCreationId );
                    final Triple_I guardComputedResult = itemProcessor[itemProcessorId].process( guardSomeParameter );
                    //
                    assertNull( guardComputedResult, String.format(
                        "[Guard test failed] -->> null was expected ;  last incoming item was %s ;  unexpectly reported triple is %s",
                        guardSomeParameter,
                        convertTripleToString( guardComputedResult )
                    ));
                    if( enableAdditionalGuardTests ){
                        assertEquals( itemCreationId+1, itemProcessor[itemProcessorId].itemsProcessed(),  "[Guard test failed]" );
                        assertEquals( 0, itemProcessor[itemProcessorId].triplesFound(),  "[Guard test failed]" );
                    }//if
                }//for
            }//for
            //
            for( int itemProcessorId=0; itemProcessorId<numberOfItemProcessorObjects; itemProcessorId++ ){
                final Item guardCurrentItem = itemFabric.createDeterministicItem( Weight.HEAVY.ordinal() + (numberOfElementsInTriple-1)*numberOfWeights );
                final Triple_I guardComputedResult = itemProcessor[itemProcessorId].process( guardCurrentItem );
                //
                assertNotNull( guardComputedResult, String.format(
                    "triple was expected  but null was computed ;  last incoming item was %s",
                    guardCurrentItem
                ));
                //
                // was correct triple computed ?
                final Item reported1stItem = guardComputedResult.getItem(0);
                final Item reported2ndItem = guardComputedResult.getItem(1);
                final Item reported3rdItem = guardComputedResult.getItem(2);
                final Item expected1stItem = itemFabric.createDeterministicItem( Weight.HEAVY.ordinal() + 0*numberOfWeights );
                final Item expected2ndItem = itemFabric.createDeterministicItem( Weight.HEAVY.ordinal() + 1*numberOfWeights );
                final Item expected3rdItem = itemFabric.createDeterministicItem( Weight.HEAVY.ordinal() + 2*numberOfWeights );
                assertArrayEquals(
                    new Item[]{ expected1stItem, expected2ndItem, expected3rdItem },
                    new Item[]{ reported1stItem, reported2ndItem, reported3rdItem },
                    "triple is NOT as expected"
                );
                //
                if( enableAdditionalGuardTests ){
                    assertEquals( numberOfItemCreations+1, itemProcessor[itemProcessorId].itemsProcessed(),  "[Guard test failed]" );
                    assertEquals( 1, itemProcessor[itemProcessorId].triplesFound(),  "[Guard test failed]" );
                }//if
            }//for
        }catch( final Exception ex ){
            fail( String.format( "[Guard test failed] -->> %s <<--",  ex.getMessage() ));
        }//try
    }//method()
    
    
    
    
    
    private RefTypeInfoContainer generateRefTypeInfoContainer(
        final String requestedRefTypeName
    ){
        
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
