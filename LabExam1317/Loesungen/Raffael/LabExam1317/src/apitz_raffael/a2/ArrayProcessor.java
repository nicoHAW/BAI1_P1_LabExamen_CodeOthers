package apitz_raffael.a2;

public class ArrayProcessor implements ArrayProcessor_I {

    private long[][] originalTwoDimArray;
    private long[][] currentTwoDimArray;
    
    public ArrayProcessor( long[][] twoDimArray ) {
        assertIfTwoDimArrayIsInvalid(twoDimArray);
        originalTwoDimArray = twoDimArray.clone();
        currentTwoDimArray = twoDimArray.clone();
    }
    
    private void assertIfTwoDimArrayIsInvalid(long[][] array) {
        assert array != null : "array can't be null";
        assert array.length != 0 : "array can't be empty";
        for (int y = 0; y < array.length; y++) {
            assert array[y] != null : "array content can't be null";
            assert array[y].length != 0 : "array content can't be empty";
        }
    }
    
    private int getLongestXAxis(long[][] twoDimArray) {
        int maxX = 1; //because we dont allow empty arrays
        for (int y = 0; y < currentTwoDimArray.length; y++) {
            for (int x = 0; x < currentTwoDimArray[y].length; x++) {
                if (x + 1 > maxX) {
                    maxX = x;
                }
            }
        }
        return maxX;
    }
    
    @Override
    public void expand1stDimBySum() {
        final int longestXAxis = getLongestXAxis(currentTwoDimArray);
        long[] newYArray = new long[longestXAxis];
        for (int y = 0; y < currentTwoDimArray.length; y++) {
            for (int x = 0; x < currentTwoDimArray[y].length; x++) {
                long currentLong = currentTwoDimArray[y][x];
                newYArray[x] += currentLong;
            }
        }
        
        long[][] withExtraRow = new long[currentTwoDimArray.length + 1][];
        System.arraycopy(currentTwoDimArray, 0, withExtraRow, 0, currentTwoDimArray.length);
        withExtraRow[currentTwoDimArray.length] = newYArray;
    }

    @Override
    public void expand2ndDimBySum() {
        for (int y = 0; y < currentTwoDimArray.length; y++) {
            long currentSum = 0l;
            for (int x = 0; x < currentTwoDimArray[y].length; x++) {
                long currentLong = currentTwoDimArray[y][x];
                currentSum += currentLong;
            }
            
            long[] withExtraEle = new long[currentTwoDimArray[y].length + 1];
            System.arraycopy(currentTwoDimArray[y], 0, withExtraEle, 0, currentTwoDimArray[y].length);
            withExtraEle[currentTwoDimArray[y].length] = currentSum;
            currentTwoDimArray[y] = withExtraEle;
        }
    }

    @Override
    public long[][] getCurrentArray() {
        return currentTwoDimArray;
    }

    @Override
    public long[][] getOriginalArray() {
        return originalTwoDimArray;
    }

}
