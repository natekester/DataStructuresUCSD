package week2;

public abstract class CreateArray {
	
	public void createArray() {
	
	}

	static long[] createIntArr(long size) {
		long k=0;
		long[] array = new long[(int) (size+1)];
		for(long i =size; i>=0;i--)
		{
			
			
			array[(int) k] = i;
			k++;
		}
		
		return array;
	}
}
