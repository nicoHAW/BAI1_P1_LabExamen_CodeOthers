package _untouchable_.supportC1x01;


import _untouchable_.supportGeneral.Herald;
import _untouchable_.supportGeneral.Version;

import static _untouchable_.supportC1x01.Configuration.dbgConfigurationVector;

import java.util.HashMap;
import java.util.Map;


/**
 * LabExam13xx_4B-XI1-P1    (PTP/Eclipse)<br />
 * <br />
 * This code belongs to the LabExam test support routine collection as part of some LabExam<br />
 * <br />
 * Test Result Analyzer ...
 * <br />
 * <br />
 * VCS: see ReadMe.txt
 *
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          P1@Hamburg-UAS.eu
 * @version {@value #encodedVersion}
 */
public class TestResultAnalyzer {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00002_003___2023_06_05__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    
    
    
    
    
    final private boolean dbgLocalCompensationInformationOutputEnable = ( 0 != ( dbgConfigurationVector & 0x0040_0000 ));
    final private boolean dbgShowAlternativeWpiUnderDevelopmentEnable = ( 0 != ( dbgConfigurationVector & 0x0080_0000 ));
    
    final private TestResultTable theTable;                                     // package scope on purpose
    
    
    
    
    
    /**
     * Constructs a test result table.
     *
     * @param testResultTable   the test result table that is analyzed
     */
    TestResultAnalyzer( final TestResultTable testResultTable ){                // package scope on purpose
        theTable = testResultTable;
    }//constructor()
    
    
    
    
    
    /**
     * Print detailed performance on screen (as performance is resulting out of test result table)
     */
    public void printPerformanceDetailed(){
        //
        // for each test level: e.g. I, A, B, C, D
        for( final TL level : TL.values() ){
            int sumExpectedPointsPerLevel = 0;
            int sumActualPointsPerLevel = 0;
            //
            // for each exercises: e.g. a1, a2, a3, a4, a5
            for( final TE exercise : TE.values() ){
                int points = theTable.getPoints( exercise, level );
                sumActualPointsPerLevel += points;
                
                // do some checks & printing
                final Integer hundredPercent = Configuration.valuationTable.get( new ValuationUnit( level, exercise ));
                if( null!=hundredPercent ){
                    sumExpectedPointsPerLevel += hundredPercent;
                    if( points > hundredPercent ){
                        throw new IllegalStateException( String.format(
                            "INTERNAL ERROR - it was NOT expected to end up \"HERE\" - call schaefers  ->  %s: %d > %d",
                            level,
                            points,
                            hundredPercent
                        ));
                    }//if
                    if( 0 < hundredPercent ){
                        System.out.printf(
                            "%s %s  -> %3d /%3d  ->  %6.2f%%\n",
                            exercise,
                            level,
                            points,
                            hundredPercent,
                            100.0 * points / hundredPercent
                        );
                    }//if
                }//if
            }//for
            System.out.printf(                                                  //  changed @18/01/15 to improve readability
                "====>>>> %3d /%3d\n",                                          //  "====>>>> %3d /%3d  => (%6.2f)\n",
                sumActualPointsPerLevel,                                        //
                sumExpectedPointsPerLevel                                       //
                //                                                              //  100.0 * sumActualPointsPerLevel / sumExpectedPointsPerLevel
            );
        }//for
    }//method()
    
    /**
     * Print performance on screen (as performance is resulting out of test result table)
     */
    public void printPerformance(){
        final Map<TL,Fraction> testResultMap = new HashMap<>();                 // required by computeWeakPerformanceIndex()
        final StringBuffer sb = new StringBuffer( "[" );
        //
        // for each test level: e.g. I, A, B, C, D
        for( final TL level : TL.values() ){
            sb.append( " " );
            sb.append( level.toString() );
            final Fraction achievement = theTable.getPortion( level );
            sb.append( String.format( ":%.2f ",  achievement.toPercentagePoint() ));
            testResultMap.put( level, achievement );
        }//for
        final double wpi = WPI_Computer.computeWeakPerformanceIndex( testResultMap );
        //
        //
        //__SCHMUDDEL___vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv        __???__<220606> remove this SCHMUDDEL code ???
        if( dbgLocalCompensationInformationOutputEnable ){
            for( final TE exercise : TE.values() ){
                if( TE.A1 != exercise ){
                    //\=> a2,a3,a4,a5
                    final int[] points = new int[ TL.values().length ];
                    for( final TL level : TL.values() ){
                        //\=> I/0i,A/1e,B/2b,C/3n,D/4s
                        //
                        final int levelIndex = level.ordinal();
                        points[levelIndex] = theTable.getPoints( exercise, level );
                    }//for
                    
                    //------------------------------->   X/0i A/1e B/2b C/3n D/4s       // first entry is never used
                    final double percentageOnXABCD[] = { 0.0, 0.0, 0.0, 0.0, 0.0 };     // it's just for convenience ;-)
                    for( int i=1; i<=4; i++ ){
                        final Integer pointsForHundredPercent = Configuration.valuationTable.get( new ValuationUnit( TL.values()[i], exercise ));
                        if( null!=pointsForHundredPercent ){
                            percentageOnXABCD[i] = 1.0*points[i]/pointsForHundredPercent;
                        }//if
                    }//for
                    
                    final double lpol1 = percentageOnXABCD[1] + percentageOnXABCD[2];   // Local Percantage On Level 1 - better name required
                    final double lpol2 = percentageOnXABCD[3];                          // Local Percantage On Level 2 - better name required
                    final double lpol3 = percentageOnXABCD[4];                          // Local Percantage On Level 3 - better name required
                    //
                    if( lpol1 < lpol2 ){
                        Herald.proclaimError( String.format(
                            "@%s:  level1=%6.2f < %6.2f=level2",
                            exercise, lpol1, lpol2
                        ));
                    }//if
                    if( lpol1 < lpol3 ){
                        Herald.proclaimError( String.format(
                            "@%s:  level1=%6.2f < %6.2f=level3",
                            exercise, lpol1, lpol3
                        ));
                    }//if
                    if( lpol2 < lpol3 ){
                        Herald.proclaimError( String.format(
                            "@%s:  level2=%6.2f < %6.2f=level3",
                            exercise, lpol2, lpol3
                        ));
                    }//if
                }//if
            }//for
        }//if        
        //--SCHMUDDEL---^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        
        
        final String wpiAsString = String.format( "%.2f",  wpi );
        if(       6 <= wpiAsString.length() ){                                  //  100.00
            sb.append( String.format( "] =>  >> WPI: %s <<",    wpiAsString ));
        }else if( 5 <= wpiAsString.length() ){                                  //  oXX.XX
            sb.append( String.format( "] =>  >> WPI: _%s <<",   wpiAsString ));
        }else{                                                                  //  ooX.XX
            sb.append( String.format( "] =>  >> WPI: __%s <<",  wpiAsString ));
        }//if
        //
        if( dbgShowAlternativeWpiUnderDevelopmentEnable ){
            sb.append( "\n" );
            sb.append( "TRIAL-WPI(s) =>  " );
            final double[] alternativeWpiResult = computeAlternativeWPI( testResultMap );
            for ( final double awpi : alternativeWpiResult ){
                sb.append( String.format( "   %6.2f",  awpi ));
            }//for
        }//if
        System.out.printf( "%s\n",  sb.toString() );
        System.out.flush();
    }//method()
    
    
    
    private double[] computeAlternativeWPI( final Map<TL,Fraction> testResultMap ){
        if( null == testResultMap )  throw new IllegalArgumentException( "null is NOT supported as argument" );
        //
        final double pI = testResultMap.get( TL.I ).toPercentagePoint();
        final double pA = testResultMap.get( TL.A ).toPercentagePoint();
        final double pB = testResultMap.get( TL.B ).toPercentagePoint();
        final double pC = testResultMap.get( TL.C ).toPercentagePoint();
        final double pD = testResultMap.get( TL.D ).toPercentagePoint();
        /*
        final double level0 = pI;
        final double level1 = (pA+pB);
        final double level2 = pC;
        final double level3 = pD;
        */
        
        final AlternativeWpiFormula[] awpif = {
            //                         __cA_  __cB_  __cC_   expo
            new AlternativeWpiFormula(  8.80,  4.20,  1.00,  2.40 ),            // 210729 v3.00
            new AlternativeWpiFormula( 29.20, 24.40,  3.50,  2.40 ),            // 210731 v3.01
            new AlternativeWpiFormula( 11.20,  5.60,  1.00,  2.60 ),            // 210802 v3.03
            new AlternativeWpiFormula( 59.20, 29.60,  3.80,  3.60 )             // 210801 v3.02
        };
        final double[] wpi = new double[awpif.length];
        
        for( int i=0; i<awpif.length; i++ ){
            wpi[i] = awpif[i].compute( pI,  pA, pB,  pC,  pD );
        }//for
        
        return wpi;
    }//method()
    
}//class
//
//
//
//
//
// Namen für die Level in den verschiedenen Varianten ihres Auftretens
// ===================================================================
//
// Level_____________________vv  (method prefix)
// Level____    internal_    ID
//
// Level0/L0    I/pI/TL.I    0i  (tol_0i_*)    Information/Identification (about/of examinee) - precondition for any other test
//
// Level1/L1    A/pA/TL.A    1e  (tol_1e_*)    Elementary
// Level1/L1    B/pB/TL.B    2b  (tol_2b_*)    Basic
//
// Level2/L2    C/pC/TL.C    3n  (tol_3n_*)    Normal
//
// Level3/L3    D/pD/TL.D    4s  (tol_4s_*)    Sophisticated
//
