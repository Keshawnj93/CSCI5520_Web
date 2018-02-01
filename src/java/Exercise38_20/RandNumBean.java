//RandNumBean.java (Used to generate random number for Exercise38_20.jsp

package Exercise38_20;

public class RandNumBean {
    private int number;
    private int guess;
    
    public RandNumBean(){
        setNumber((int)((Math.random() * 1000) + 1));
    }
    
    public int getNumber(){
        return number;
    }
    
    public int getGuess(){
        return guess;
    }
    
    public void setNumber(int n){
        number = n;
    }
    
    public void setGuess(int n){
        guess = n;
    }
    
    public void generateRand(){
       setNumber((int)((Math.random() * 1000) + 1));
    }
    
    public String check(){
        if (guess < number)
            return "Too Low. Try Again.";
        else if (guess > number)
            return "Too High. Try Again.";
        else if (guess == number){
            generateRand();
            return "Correct! A new number has been generated.";
        }
        return "ERR";
    }
}
