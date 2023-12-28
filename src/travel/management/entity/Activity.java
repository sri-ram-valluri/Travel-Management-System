package travel.management.entity;

/**
 *
 * @author sriramvalluri
 */
public class Activity {
    String activityName;
    String activityDescription;
    int cost;
    int capacity;
    String destinationName;

    public Activity(String activityName, String activityDescription, int cost, int capacity, String destinationName) {
        this.activityName = activityName;
        this.activityDescription = activityDescription;
        this.cost = cost;
        this.capacity = capacity;
        this.destinationName = destinationName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    @Override
    public String toString() {
        return "Activity Name : " + this.activityName + 
                "\n Activity Description : " + this.activityDescription + 
                "\n Capacity : " + this.capacity + "\n Cost : " + this.cost + "\n";
    }
    
    
    
}
