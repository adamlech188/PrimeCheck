public class PrimeChecker { 

 public static boolean isPrime(Integer number) { 
 
 
 //if(!(number instanceof Integer)) { 
//	System.out.println("This is not integer.Only integers can be primes!"); 
//  return false;  } 
 if(number == 0 || number == 1){ 
	return false; 
}
if (number == 2 || number ==3) {  

	return true; 
}
if (number%2==0 || number%3==0) {

	return false; 
}
else { 
   return true; 

} 



}   }

