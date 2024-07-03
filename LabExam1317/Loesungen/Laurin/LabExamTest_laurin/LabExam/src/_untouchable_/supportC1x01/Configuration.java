package _untouchable_.supportC1x01;


import _untouchable_.supportGeneral.Version;
import java.util.HashMap;
import java.util.Map;


/**
 * LabExam13xx_4B-XI1-P1    (PTP/Eclipse)<br />
 * <br />
 * This code belongs to the LabExam test support routine collection as part of some LabExam<br />
 * <br />
 * Configuration
 * <br />
 * <br />
 *
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          Px@Hamburg-UAS.eu
 * @version {@value #encodedVersion}
 */
public class Configuration {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00002_003___2023_06_07__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    
     
    
    
    
    /**
     * The debug-output enable vector controls the debug messages printed on screen
     */
    //                                                 vvvv_vvvv__vvvv_vvvv____vvvv_vvvv__vvvv_vvvv
    final static public int dbgConfigurationVector = 0b1000_0000__0010_0000____0000_0000__0000_0011;
    //                                                 +..._....__---._..--____...._..-?__...._.-?+
    //                                                 ^          ^^^    ^^    ^      ^^        ^^^
    //                                                 |          |||    ||    |      ||        |||
    //                       enable automatic test   --/          |||    ||    |      ||        |||
    //                                                            |||    ||    |      ||        |||
    //      show alternative WPI under development   -------------/||    ||    |      ||        |||
    //        wpi related compensation information   --------------/|    ||    |      ||        |||
    //   additional information on $variable names   ---------------/    ||    |      ||        |||
    //                                                                   ||    |      ||        |||
    //                         OBSOLETE/DEPRECATED   --------------------/|    |      ||        |||     <-  was support for BlueJ JUnit annotation bug(fix)
    //                         OBSOLETE/DEPRECATED   ---------------------/    |      ||        |||     <-  was             BlueJ JUnit annotation bug fix
    //                                                                         |      ||        |||
    // (temporary) debug output (for test testing)   --------------------------/      ||        |||
    //                                                                                ||        |||
    //        manual review of basic general stuff(*)---------------------------------/|        |||
    //            printExamineeInfoForManualReview(*)----------------------------------/        |||
    //                                                                                          |||
    //                       detailed result table   -------------------------------------------/||
    //                                result table   --------------------------------------------/|
    //                                         WPI   ---------------------------------------------/
    //
    //
    // 20/06/29: LabExam is Eclipse based - it is very unlikely, that BlueJ is used again (to execute this kind of lab exam)
    // OBSOLETE/DEPRECATED(*) ATTENTION: As result of BlueJ-BUG, this influences the workaround: "numberOfTests" (in TestFrame)
    
    
    
    /**
     * Path to data base respectively name of directory containing test results without database file
     */
    final static String mainPartOfTestResultDataBasePath = "TestResultDatabase" + CentralVersionData.testCycle; // package scope on purpose
    
    /**
     * (Base) name without extension
     * E.g. there will be ".data" and ".guard"
     */
    final static String mainPartOfTestResultDataBaseFileName = "testResultDB";                                  // package scope on purpose
    
    
    /**
     * Version ID of configuration this very Configuration-class with its internal structure respectively possibilities
     */
    final static String configurationVersionID = getDecodedVersion();

    
    
    
    
    
    //--------------------------------------------------------------------------
    //
    // INTERNAL
    //
    
    /**
     * Table respectively map containing max. point that can be achieved.
     */
    final static Map<ValuationUnit,Integer> valuationTable = new HashMap<ValuationUnit,Integer>(){
        {   //---~~~~~~~~~~~~~~~~~~~VVVVVVVVVVV~~~~VVV~~~
            put( new ValuationUnit( TL.I, TE.A1 ),  10 );
            //------------------------------------------
            put( new ValuationUnit( TL.A, TE.A2 ),  12 );
            put( new ValuationUnit( TL.A, TE.A3 ),  10 );
            put( new ValuationUnit( TL.A, TE.A4 ),  12 );
            put( new ValuationUnit( TL.A, TE.A5 ),  13 );
            //------------------------------------------
            put( new ValuationUnit( TL.B, TE.A2 ),  13 );
            put( new ValuationUnit( TL.B, TE.A3 ),   6 );
            put( new ValuationUnit( TL.B, TE.A4 ),   6 );
            put( new ValuationUnit( TL.B, TE.A5 ),   6 );
            //------------------------------------------
            put( new ValuationUnit( TL.C, TE.A2 ),  12 );
            put( new ValuationUnit( TL.C, TE.A3 ),   8 );
            put( new ValuationUnit( TL.C, TE.A4 ),   9 );
            put( new ValuationUnit( TL.C, TE.A5 ),   7 );
            //------------------------------------------
            put( new ValuationUnit( TL.D, TE.A2 ),   8 );
            put( new ValuationUnit( TL.D, TE.A3 ),   6 );
            put( new ValuationUnit( TL.D, TE.A4 ),   5 );
            put( new ValuationUnit( TL.D, TE.A5 ),  10 );
        }//constructor()
        //
        final static private long serialVersionUID = Configuration.encodedVersion;
    };//class
    //
    /**
     * Number of levels per exercise (Attention there are different definitions of level - still cleanup required ;-)
     */
    //                                                                             ___1___  ___4____________  ___4____________  ___4____________  ___4____________
    final static int[] ratioVector = { 1, 4, 4, 4, 4 };                         // TL.I:A1, TL.A:A2,A3,A4,A5, TL.B:A2,A3,A4,A5, TL.C:A2,A3,A4,A5, TL.D:A2,A3,A4,A5  used in TestResultTable.printPerformance()
    
}//class
