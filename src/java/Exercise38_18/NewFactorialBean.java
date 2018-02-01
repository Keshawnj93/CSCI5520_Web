//NewFactorialBean.java (Edited to use BigInteger)

package Exercise38_18;

import java.math.BigInteger;
import java.text.NumberFormat;


public class NewFactorialBean {
    private int number;
    
    
    //Get Number
    public int getNumber(){
        return number;
    }
    
    //Set Number
    public void setNumber(int newValue){
        this.number = newValue;
    }
    
    //Get the factorial of number
    public BigInteger getFactorial(){
        BigInteger factorial = BigInteger.valueOf(1);
        
        for (int i = 1; i <= number; i++){
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }
    
    public static String format(BigInteger number){
        NumberFormat format = NumberFormat.getNumberInstance();
        return format.format(number);
    }
}
