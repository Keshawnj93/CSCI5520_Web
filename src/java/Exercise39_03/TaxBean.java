//TaxBean.java

package Exercise39_03;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Keshawn
 */
@Named(value = "taxBean")
@ManagedBean
@RequestScoped
public class TaxBean {
    private int income;
    private String status;
    private double tax;
    
    public TaxBean() {
        income = 0;
        tax = 0;
        status = "";
    }
    
    public int getIncome(){
        return income;
    }
    
    public void setIncome(int n){
        income = n;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String s){
        status = s;
    }
    
    public String getTax(){
        tax = 0;
        int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, filing = 0;
        
        String ret = "<p>Taxable Income: " + income + "</p>";
        
        if (status.equals("Single")){
            a = 9325;
            b = 37950;
            c = 91900;
            d = 191650;
            e = 416700;
            f = 418400;
            filing = 0;
        }
        
        else if (status.equals("Married (Filing Jointly)")){
            a = 18650;
            b = 75900;
            c = 153100;
            d = 233350;
            e = 416700;
            f = 470700;
            filing = 1;
        }
        
        else if (status.equals("Married (Filing Seperately)")){
            a = 9325;
            b = 37950;
            c = 76550;
            d = 116675;
            e = 208350;
            f = 235350;
            filing = 2;
        }
        
        else if (status.equals("Head of Household")){
            a = 13350;
            b = 50800;
            c = 131200;
            d = 212500;
            e = 416700;
            f = 444550;
            filing = 3;
        }
        
        ret = ret + "<p>Filing Status: " + filing + "</p>";
        
        if (income <= a){
            tax = income * 0.1;
        }
        else if (income <= b){
            tax = (a * 0.1) + ((income - a) * 0.15);
        }
        else if (income <= c){
            tax = (a * 0.1) + ((b - a) * 0.15) + ((income - b) * 0.25);
        }
        else if (income <= d){
            tax = (a * 0.1) + ((b - a) * 0.15) + ((c - b) * 0.25) + ((income - c) * 0.28);
        }
        else if (income <= e){
            tax = (a * 0.1) + ((b - a) * 0.15) + ((c - b) * 0.25) + ((d - c) * 0.28)
                    + ((income - d) * 0.33);
        }
        else if (income <= f){
            tax = (a * 0.1) + ((b - a) * 0.15) + ((c - b) * 0.25) + ((d - c) * 0.28)
                    + ((e - d) * 0.33) + ((income - e) * 0.35);
        }
        else if (income > f){
            tax = (a * 0.1) + ((b - a) * 0.15) + ((c - b) * 0.25) + ((d - c) * 0.28)
                    + ((e - d) * 0.33) + ((f - e) * 0.35) + ((income - f) * 0.35);
        }
        
        ret = ret + "<p>Tax: " + tax + "</p>";
        
        return ret;
    }
    
    public void setTax(){
        
    }
    
}
