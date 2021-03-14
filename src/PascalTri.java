import java.util.ArrayList;
public class PascalTri {
private ArrayList <BigInt> comb = new ArrayList <BigInt>();
private int  maxN;
public PascalTri( int maxn) {
	maxN = maxn;
	maxn = (maxn * (maxn + 1)) >>> 1;
	comb = new ArrayList<BigInt>(maxn);
	
	for (int i = 0; i < maxn; i++)
		comb.add(null); 
	
	for( int i = 0 ; i<maxN; i++) {
		for( int j = 0 ; j <= i ; j++) {
			if( j==0 ) {
				BigInt a = new BigInt("1");
				comb.set(getIndex(i,j), a );
			}
			else if( i==j ) {
				BigInt a = new BigInt("1");
				comb.set(getIndex(i,j), a );
			}
			else {
				comb.set(getIndex(i,j), (getConb(i-1,j-1).add(getConb(i-1,j))) );
			}
			
		}
		
	}
}
public ArrayList<BigInt> getArr() {
	return comb;
}
public int getMaxN() {
	return maxN;
}

public BigInt getConb(int y ,int x) throws IllegalArgumentException{ 
	if( x > y)
		throw new IllegalArgumentException();
	
	int n = getIndex(y, x);
	return comb.get(n);
}


private int getIndex(int y, int x) throws IndexOutOfBoundsException {

	if (x >= maxN || y >= maxN)
		throw new IndexOutOfBoundsException();

	return ((y * (y + 1)) >>> 1) + x; 
}






}
