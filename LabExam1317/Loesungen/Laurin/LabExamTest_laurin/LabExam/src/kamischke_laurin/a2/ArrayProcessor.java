package kamischke_laurin.a2;

public class ArrayProcessor implements ArrayProcessor_I {
	
	private long[][] currentArray;
	private long[][] originalArray;

	public ArrayProcessor(final long[][] givenArray) {
		assert (givenArray != null);
		
		currentArray = givenArray;
		this.originalArray = givenArray;
	}
	
	@Override
	public void expand1stDimBySum() {
		long[] result = new long[currentArray[0].length];
		
		for (int i = 0; i < currentArray.length; i++) {
			for (int j = 0; j < currentArray[i].length; j++) {
				result[j] += currentArray[i][j];
			}
		}
		long[][] temp = new long[currentArray.length+1][currentArray[0].length];
		for (int i = 0; i < currentArray.length; i++) {
			for (int j = 0; j < currentArray[i].length; j++) {
				temp[i][j] = currentArray[i][j];
			}
		}
		for (int i = 0; i < result.length; i++) {
			temp[currentArray.length][i] = result[i];
		}
		currentArray = temp;
	}

	@Override
	public void expand2ndDimBySum() {
		
		long[] result = new long[currentArray.length];
		
		for (int i = 0; i < currentArray.length; i++) {
			for (int j = 0; j < currentArray[i].length; j++) {
				result[i] += currentArray[i][j];
			}
		}
		long[][] temp = new long[currentArray.length][currentArray[0].length+1];
		for (int i = 0; i < currentArray.length; i++) {
			for (int j = 0; j < currentArray[i].length; j++) {
				temp[i][j] = currentArray[i][j];
			}
		}
		for (int i = 0; i < result.length; i++) {
			temp[i][currentArray[0].length] = result[i];
		}
		currentArray = temp;
	}

	@Override
	public long[][] getCurrentArray() {
		return currentArray;
	}

	@Override
	public long[][] getOriginalArray() {
		return originalArray;
	}

}
