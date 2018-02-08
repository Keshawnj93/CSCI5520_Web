/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercise39_01;

import java.math.BigInteger;
import java.text.NumberFormat;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;


/**
 *
 * @author Keshawn
 */
@Named(value = "factorialBean")
@ManagedBean
@RequestScoped
public class FactorialBean {
    private int number;
    private int factorial;
    
    /**
     * Creates a new instance of FactorialBean
     */
    public FactorialBean() {
        number = 0;
    }
        
    //Get Number
    public int getNumber(){
        return number;
    }
    
    //Set Number
    public void setNumber(int newValue){
        this.number = newValue;
    }
    
    //Get the factorial of number
    public String getFactorial(){
         factorial = 1;
         String ret = "<table style style=\"border: 1px solid black; margin-left: auto; margin-right: auto\">"
                 + "<tr><th style=\"border: 1px solid black\">Number</th><th style=\"border: 1px solid black\">Factorial</th></tr>"
                 + "<tr><td style=\"border: 1px solid black\">0</td><td style=\"border: 1px solid black\">1</td></tr>";
        
        for (int i = 1; i <= number; i++){
            factorial *= i;
            ret = ret + "<tr><td style=\"border: 1px solid black\">" + i + "</td><td style=\"border: 1px solid black\">" + factorial + "</td></tr>";
        }
        
        ret = ret + "</table>";
        return ret;
    }
    
    public void setFactorial(){
        
    }
    
    
    public static String format(BigInteger number){
        NumberFormat format = NumberFormat.getNumberInstance();
        return format.format(number);
    }
    
}