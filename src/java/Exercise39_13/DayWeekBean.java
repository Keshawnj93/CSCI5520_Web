//DayWeekBean.java

package Exercise39_13;

import java.time.LocalDate;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Keshawn
 */
@Named(value = "dayWeekBean")
@ManagedBean
@RequestScoped
public class DayWeekBean {
    
    private int day, month, year;
    private String time, dayOfWeek;
    
    public DayWeekBean() {
    
    }
    
    public void setDay(int d) {
        day = d;
    }
    
    public int getDay() {
        return day;
    }

    public void setMonth(int m) {
        month = m;
    }
    
    public int getMonth() {
        return month;
    }

    public void setYear(int y) {
        year = y;
    }

    public int getYear() {
        return year;
    }

    public void setTime(String s) {
        time = s;
    }

    public String getTime() {
        return time;
    }

    public void setDayOfWeek(String s) {
        dayOfWeek = s;
    }

    public String getDayOfWeek() {

        return dayOfWeek;

    }

    public void calculate() {
        LocalDate currentTime = LocalDate.now();
        LocalDate date = LocalDate.of(year, month, day);
        if (month == 1 || month == 2) {
            month += 12;
            year--;
        }
        
        int k, c, d, m, dow;
        // Compute the answer
        k = year % 100; // The year of the century
        c = (int) (year / 100.0); // the century
        d = day;
        m = month;
        dow = (d + (int) ((13 * (m + 1)) / 5.0) + k + (int) (k / 4.0)
                + (int) (c / 4.0) + (5 * c)) % 7;

        //Display the name of the day of the week
        switch (dow){
            case 0: dayOfWeek = "Saturday"; break;
            case 1: dayOfWeek = "Sunday"; break;
            case 2: dayOfWeek = "Monday"; break;
            case 3: dayOfWeek = "Tuesday"; break;
            case 4: dayOfWeek = "Wednesday"; break;
            case 5: dayOfWeek = "Thursday"; break;
            case 6: dayOfWeek = "Friday"; break;
            default: dayOfWeek = "Error";
        }
        
        if (month == 13 || month == 14) {
            month -= 12;
            year++;
        }

        if (currentTime.compareTo(date) > 0) {
            time = "Past";
            
        } else if (currentTime.compareTo(date) < 0) {
            time = "Future";
            
        } else if (currentTime.compareTo(date) == 0) {
            time = "Present";
            
        } else {
            time = "Error";
        }
    }
}
