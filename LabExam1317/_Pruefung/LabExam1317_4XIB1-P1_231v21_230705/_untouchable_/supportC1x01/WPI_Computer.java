package _untouchable_.supportC1x01;


import _untouchable_.supportGeneral.Herald;
import _untouchable_.supportGeneral.Version;

import static _untouchable_.supportC1x01.Configuration.dbgConfigurationVector;

import java.util.Map;


// NOTE: Outsourced from TestResultAnalyzer::printPerformance()
/**
 * LabExam13xx_4B-XI1-P1    (PTP/Eclipse)<br />
 * <br />
 * This code belongs to the LabExam test support routine collection as part of some LabExam<br />
 * <br />
 * WPI Computer, actually still the "2017/02/03 v2.91"version.
 * Was &quot;designed&quot; the way most code (for university) is &quot;designed&quot;
 * - as low level background task, that actually was NOT designed in any way - just evolved.
 * It's ugly, dirty and anti-code, but working.
 * <br />
 * <br />
 * VCS: see ReadMe.txt
 *
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          P1@Hamburg-UAS.eu
 * @version {@value #encodedVersion}
 */
public class WPI_Computer {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00002_092___2023_06_05__01L; // still "algorithm version" :  2017/02/03 v2.91  -->>  [#5]";
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    
    
    
    
    
    //##########################################################################
    //###
    //###   WPI computation
    //###
    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    //
    // Ueberlegungen auf denen die Bewertung basiert:
    // ==============================================
    //
    // Es ist nur schwer vorstellbar, dass in einer Aufgabe "4s"-Punkte erzielt werden ohne die zugehoerigen "1e"-&"2b"-Punkte (Ohne "3n"-Punkte bedingt moeglich).
    // Es ist nur schwer vorstellbar, dass in einer Aufgabe "3n"-Punkte erzielt werden ohne die zugehoerigen "1e"-&"2b"-Punkte.
    // Kompensation erfolgt (also) nur fuer fehlende Punkte in anderen Aufgabenteilen.
    //
    // Wer 3 von 4 Aufgaben 100% richtig hat, aber die 4.Aufgabe gar nicht, der sollte bestanden haben.
    // "Durchfallszenarien" solten also nicht mehr WPI erzielen.
    // Nur mit ueberall "1e"-&"2b"-Punkte darf man NICHT bestehen.
    //
    // Licht im Dunkel des Namens-Wirrwarrs (bzgl. der Level):
    //  Level I = Level 0i          Idee: Information bzw. Student kann sich fehlerfrei identifizieren
    //  Level A = Level 1e          Idee: Existenz bzw. die Dinge existieren.
    //  Level B = Level 2b          Idee: Basic bzw. Anstarten (der Methoden) ohne Absturz moeglich
    //  Level C = Level 3n          Idee: Normal bzw. "Laeuft" mit einfachen bis leicht komplizierten Eingaben (die zulaessig sind) 
    //  Level D = Level 4s          Idee: Speziell bzw. extrem komplizierte oder unzulaessige Eingaben 
    //  --------------------------------
    //  Level 1 = Level A + Level B
    //  Level 2 = Level C
    //  Level 3 = Level D
    //  Bemerkung: Ohne 100% in Level I / 0i geht eh ueberhaupt nichts ;-)
    //
    //
    //
    // Die "Konfigurationsstellen" sind markiert mit:
    //  "<<-- HERE: configuration of WPI computation"
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    // HISTORY:
    // ========
    //
    // since:   170203 | 170127 160710 160709 160707
    // ------
    // fL1/fA ---+o.35 |   o.25   o.35   o.4    o.4    ( 160707-160710 was fA )
    // fL1/fB --/      |   o.1o   ----   ----   ----
    // fL2/fC     o.5o |   o.5o   o.5o   o.45   o.45   ( 160707-160710 was fB )
    // fL3/fD     o.15 |   o.15   o.15   o.15   o.15   ( 160707-160710 was fC )
    // ------
    // cL1xL3     2.34     2.oo   2.oo  10.oo   ?.??   ( 160707-160710 was fABXD, fAC )
    // cL1xL2     2.oo    10.oo  10.oo  25.oo   ?.??   ( 160707-160710 was fABXC  fAB )
    //
    // there is NO compensation fBC ,  since fC is smaller than fB anyway
    //
    // this method is related to the very actual lab exam and has to be adapted for each lab exam
    // NO MORE static since state is accessed for debugging purposes
    static double computeWeakPerformanceIndex( final Map<TL,Fraction> testResultMap ){ // package scope on purpose
        if( null == testResultMap )  throw new IllegalArgumentException( "null is NOT supported as argument" );
        //
        final boolean dbgLocalCompensationInformationOutputEnable = ( 0 != ( dbgConfigurationVector & 0x0040_0000 ));
        
        
        final double fL1 = 0.35;                                                // <<-- HERE: configuration of WPI computation
        final double fL2 = 0.5;                                                 // <<-- HERE: configuration of WPI computation
        final double fL3 = 0.15;                                                // <<-- HERE: configuration of WPI computation
        if( ! aboutEqual( 1.0, fL1+fL2+fL3 )){
            Herald.proclaimError( "[[[[>>>> ATTENTION : INTERNAL setup ERROR <<<<]]]]" );
        }//if
        //
        final double cL1xL2 = 2.0;                                              // <<-- HERE: configuration of WPI computation - factor for compensation of missing A points with B points
        //\=> "penalty per point":       2.0 * (fL2/fL1)  ~> 2,857
        final double cL1xL3 = fL1/fL3;                  //~> 2,334              // <<-- HERE: configuration of WPI computation - factor for compensation of missing A points with C points
        //\=> "penalty per point": (fL1/fL3) * (fL3/fL1)  ~> 1,00
        // there is NO compensation fBC ,  since fC is smaller than fB anyway
        
        final double pI = testResultMap.get( TL.I ).toPercentagePoint();
        final double pA = testResultMap.get( TL.A ).toPercentagePoint();
        final double pB = testResultMap.get( TL.B ).toPercentagePoint();
        final double pC = testResultMap.get( TL.C ).toPercentagePoint();
        final double pD = testResultMap.get( TL.D ).toPercentagePoint();
        //
        double level0 = pI;
        double level1 = (pA+pB) - 100.0;
        double level2 = pC;
        double level3 = pD;
        
        double wpi;
        double delta;
        //
        if( 99.999999 >= level0 ){
            //\=> examinee info is NOT ok, hence unsuccessfull
            wpi = 0.0;
            
            
        }else{
            //\=> at least examinee info is ok
            if( 99.999999 >= level1 ){
                //\=> NOT all indispensable elementary/basic exercises were solved
                //
                // take level3 points first (it is assumed that only neglectable level3 points are gained without related level2 points
                delta = cL1xL3 * ( 100.0 - level1 );
                if( level3 >= delta ){
                    //\=> enough D/level3 to compensate missing A+B/level1
                    level3 -= delta;
                    level1 = 100.0;
                }else{
                    //\=> NOT enough C/level2 to compensate missing A+B/level1
                    level1 += level3 / cL1xL3;
                    level3 = 0.0;
                    
                    delta = cL1xL2 * ( 100.0 - level1 );
                    if( level2 >= delta ){
                        //\=> enough C/level2 to compensate missing A+B/level1
                        level2 -= delta;
                        level1 = 100.0;
                    }else{
                        //\=> NOT enough C/level2 to compensate missing A+B/level1
                        level1 += level2 / cL1xL2;
                        level2 = 0.0;
                    }//if
                }//if
            }//if
            if( dbgLocalCompensationInformationOutputEnable ){
                if( 99.999999 >= level1 ){
                    Herald.proclaimError( "NOT ENOUGH   level-1 / A+B / \"1e\"+\"2b\"   points  !!!" );
                }//if
            }//if
            wpi  =  level1 * fL1  +  level2 * fL2  +  level3 * fL3;
        }//if
        if( dbgLocalCompensationInformationOutputEnable ){
            System.out.printf( "\n" );
            System.out.printf(
                "wpi=%6.2f    <-    pI=%6.2f  pA=%6.2f  pB=%6.2f  pC=%6.2f  pD=%6.2f    l1=%6.2f  l2=%6.2f  l3=%6.2f\n",
                wpi,
                pI, pA, pB, pC, pD,
                level1, level2, level3
            );
            System.out.printf( "\n" );
        }//if
        if( 2.0 > wpi ){
            //\=> scale calues below 2 into range [0,..,2]
            if( 0.0 > wpi ){
                //\=> [-35,..,0] -> [0,..,+1]
                wpi = 1.0 + (wpi / 35.0);
            }else{
                //\=> [0,..,2] -> [1,..,2]
                wpi = 1.0 + (wpi / 2.0);
            }//if
        }//if
        return wpi;
    }//method()
    //
    private static boolean aboutEqual( final double value1,  final double value2 ){
        final double epsilon = 0.001;
        
        return (( value1 < value2+epsilon ) && ( value1 > value2-epsilon ));
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
