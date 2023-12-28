package travel.management.entity;

import java.util.*;
/**
 *
 * @author sriramvalluri
 */

enum Type {
   STANDARD, GOLD, PREMIUM;
}

public class Passenger {
    String passengerName;
    int passengerNumber;
    Type type;
    int balance;
    ArrayList<Activity> activityList;

    public Passenger(String passengerName, int passengerNumber, Type type, int balance) {
        this.passengerName = passengerName;
        this.passengerNumber = passengerNumber;
        this.type = type;
        this.balance = balance;
        this.activityList = new ArrayList<>();
    }

    public String getPassengerName() {
        return passengerName;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    public void addActivity(Activity activity){
        this.activityList.add(activity);
    }
}


