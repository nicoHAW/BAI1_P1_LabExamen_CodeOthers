package _untouchable_.supportC1x01;


import _untouchable_.supportGeneral.Version;


/**
 * LabExam13xx_4B-XI1-P1    (PTP/Eclipse)<br />
 * ...
 * 
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          P1@Hamburg-UAS.eu
 * @version {@value #encodedVersion}
 */
public class AlternativeWpiFormula {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00002_002___2023_06_05__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    
    
    
    
    
    final private double coeffLevel1;
    final private double coeffLevel2;
    final private double coeffLevel3;
    final private double expo;
    final private double scale;
    
    
    
    /**
     * ...
     * 
     * @param coeffLevel1  ...
     * @param coeffLevel2  ...
     * @param coeffLevel3  ...
     * @param expo  ...
     */
    public AlternativeWpiFormula(
        final double coeffLevel1,
        final double coeffLevel2,
        final double coeffLevel3,
        final double expo
    ){
        assert coeffLevel1 > coeffLevel2 : String.format( "SETUP-ERROR: cl1:%6.2f ~ cl2:%6.2f",  coeffLevel1, coeffLevel2 );
        assert coeffLevel2 > coeffLevel3 : String.format( "SETUP-ERROR: cl2:%6.2f ~ cl3:%6.2f",  coeffLevel2, coeffLevel3 );
        
        this.coeffLevel1 = coeffLevel1;
        this.coeffLevel2 = coeffLevel2;
        this.coeffLevel3 = coeffLevel3;
        this.expo = expo;
        this.scale = Math.pow( ( coeffLevel3 + coeffLevel2 + coeffLevel1 )*100, expo );
    }//constructor()
    
    
    
    /**
     * ...
     * 
     * @param scaledValueLevelI  ...
     * @param scaledValueLevelA  ...
     * @param scaledValueLevelB  ...
     * @param scaledValueLevelC  ...
     * @param scaledValueLevelD  ...
     * @return  ...
     */
    public double compute(
        final double scaledValueLevelI,
        final double scaledValueLevelA,
        final double scaledValueLevelB,
        final double scaledValueLevelC,
        final double scaledValueLevelD
    ){
        final double epsilon = 0.001;                                           // Toleranz für Rundungsfehler
        if( ( 100.0-epsilon ) > scaledValueLevelI ){
            return 0.00;
        }else{
            return compute( (scaledValueLevelA+scaledValueLevelB)/2.0, scaledValueLevelC, scaledValueLevelD );
        }//if
    }//method()
    //
    /**
     * ...
     * 
     * @param scaledValueLevel1  ...
     * @param scaledValueLevel2  ...
     * @param scaledValueLevel3  ...
     * @return  ...
     */
    public double compute(
        final double scaledValueLevel1,
        final double scaledValueLevel2,
        final double scaledValueLevel3
    ){
        return
            Math.pow( 
                (   scaledValueLevel3 * coeffLevel3
                  + scaledValueLevel2 * coeffLevel2
                  + scaledValueLevel1 * coeffLevel1
                ),
                expo
            ) * 100/scale;
    }//method()
    
}//class
//
//  Koeffizient:
//  Level1  Level2  Level3 Exponent         Version
//    8,80    4,20    1,00     2,40         2021/07/29 v3.00  -->>  [#6-before-1]
//   17,60    4,20    1,00     2,40         2021/07/29
//   15,70   13,00    2,00     2,40         2021/07/31
//   29,20   24,40    3,50     2,40         2021/08/01 v3.01
//   20,20   16,90    2,40     2,40         2021/08/01
//   33,70   28,20    4,00     2,40         2021/08/01
//   59,20   29,60    3,80     3,60         2021/08/01 v3.02