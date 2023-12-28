package travel.management.entity;

import java.util.*;

/**
 *
 * @author sriramvalluri
 */
public class Destination {
    String packageName;
    String destinationName;
    ArrayList<Activity> activityList;

    public Destination(String packageName, String destinationName) {
        this.packageName = packageName;
        this.destinationName = destinationName;
        activityList = new ArrayList();
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }
    
    public void addActivity(Activity activity){
//        if(this.destinationName.equals(activity.getDestinationName())){
//            this.activityList.add(activity);
//        }
        this.activityList.add(activity);
    }
    
    public boolean updateDestination(Destination destination){
        if(this.destinationName.equals(destination.getDestinationName())){
            for(Activity activity : destination.getActivityList()){
                addActivity(activity);
            }
            return true;
        }
        return false;
    }

    public ArrayList<Activity> getActivityList() {
        return activityList;
    }
    
    @Override
    public String toString(){
        return "Destination name : " + this.destinationName +
                "\n" + this.activityList.toString();
    }
    
    
    public boolean equals(Destination destination){
        return destination.getDestinationName().equals(this.destinationName);
    }
    
    public ArrayList<String> getStringList(){
        ArrayList<String> stringList = new ArrayList();
        stringList.add("Destination name : " + this.destinationName);
        for(Activity ac: this.activityList){
            stringList.add(ac.toString());
        }
        return stringList;
    }
    

}
