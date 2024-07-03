package _untouchable_.thingy.internalThingySupport;


import _untouchable_.thingy.Color;
import _untouchable_.thingy.Item;
import _untouchable_.thingy.Size;
import _untouchable_.thingy.Weight;
import _untouchable_.supportGeneral.Version;
import _untouchable_.supportGeneral.random.RandomGenerator;
import _untouchable_.supportGeneral.random.RandomGeneratorImplementationWrapper;
import java.io.Serializable;


/**
 * Die Klasse ItemFabric erlaubt es mittels der create()-Methoden Items zu generieren.
 * Die ItemFabric ist zugeschnitten auf Prüfungen.
 * 
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          P1@Hamburg-UAS.eu 
 * @version {@value #encodedVersion} 
 */
public class ItemFabric implements Serializable {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00002_004___2023_06_12__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung
    
    private static final long serialVersionUID = version.getEncodedVersion();
    
    
    
    
    
    static final private int numberOfColors = Color.values().length;
    static final private int numberOfSizes = Size.values().length;
    static final private int numberOfWeights = Weight.values().length;
    static final private int defaultAmountOfDifferentValues = 1_000_003;        // "int" as tribute to "Random"-class
    
    static final private long defaultEnsureLongFactor = 1L;                     // "nicer"
  //static final private long defaultEnsureLongFactor = 1_000_000_007L;         // prime that ensures longs
    
    static final private RandomGenerator randomGenerator = new RandomGeneratorImplementationWrapper();
    
    
    /**
     * Die (statische) Methode createRandomBasedItem() generiert ein zufälliges Item.
     * 
     * @return zufällig generiertes Item
     */
    static public Item createRandomBasedItem(){
        final Color randomGeneratedColor   = Color.values()[  randomGenerator.nextInt( numberOfColors ) ];                  // any color
        final Size randomGeneratedSize     = Size.values()[   randomGenerator.nextInt( numberOfSizes ) ];                   // any size
        final Weight randomGeneratedWeight = Weight.values()[ randomGenerator.nextInt( numberOfWeights ) ];                 // any weight
        final Long ramdonGeneratedValue    = (long)(          randomGenerator.nextInt( defaultAmountOfDifferentValues ));   // any value
        //
        final Item randomGeneratedItem = new Item( randomGeneratedColor, randomGeneratedSize, randomGeneratedWeight, ramdonGeneratedValue );
        return randomGeneratedItem;
    }//method()
    
    
    
    // configure characteristic of item attribute value
    final private long ensureLongFactor;
    final private int amountOfDifferentValues;
    final private int modulus;
    
    
    
    
    
    public ItemFabric( final long ensureLongFactor,  final int amountOfDifferentValues,  final int modulus ){
        assert amountOfDifferentValues < modulus : "Uuupps : Dubious configuration";
        //
        this.ensureLongFactor = ensureLongFactor;
        this.amountOfDifferentValues = amountOfDifferentValues;
        this.modulus = modulus;
    }//constructor()
    //
    public ItemFabric( final int amountOfDifferentValues ){
        this( defaultEnsureLongFactor, amountOfDifferentValues, Integer.MAX_VALUE );
    }//constructor()
    //
    public ItemFabric(){
        this( defaultAmountOfDifferentValues );
    }//constructor()
    
    
    
    
    
    /**
     * Die Methode createDeterministicItem() generiert ein Item basierend auf dem creationValue.
     * 
     * @param creationValue ist der "magische" Wert für die Objekterzeugung
     * @return generiertes Item
     */
    public Item createDeterministicItem( final int creationValue ){
        assert 0 <= creationValue : "INTERNAL ERROR negative values are NOT expected";
        
        final int adaptedCreationValue = creationValue % modulus;
        final Item generatedItem = new Item(
            Color.values()[  adaptedCreationValue % numberOfColors ],
            Size.values()[   adaptedCreationValue % numberOfSizes ],
            Weight.values()[ adaptedCreationValue % numberOfWeights ],
            ( adaptedCreationValue % amountOfDifferentValues ) * ensureLongFactor 
        );
        return generatedItem;
    }//method()
    
}//class
