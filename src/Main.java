
public class Main {
 public static void main(String[] args) {
  BigInt b=new BigInt("999999999"),c=new BigInt("1");
  System.out.println(b.add(c));
  PascalTri a=new PascalTri(100);
 
  
  for(int i = 0; i<a.getMaxN() ; i++) {
	  for(int j = 0; j<=i; j++) {
		  System.out.print(a.getConb(i, j) + " ");
	  }
	  System.out.println();
  }
 }
}