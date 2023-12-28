package travel.management.entity;

import java.util.*;

/**
 *
 * @author sriramvalluri
 */
public class TravelPackage {
    String travelPackageName;
    ArrayList<Destination> destinationList;

    public TravelPackage(String travelPackageName) {
        this.travelPackageName = travelPackageName;
        this.destinationList = new ArrayList();
    }

    public String getTravelPackageName() {
        return travelPackageName;
    }

    public ArrayList<Destination> getDestinationList() {
        return destinationList;
    }
    
    public Destination getDestination(Destination destination){
        for(Destination d: this.destinationList){
            if(destination.equals(d)){
                return d;
            }
        }
        return null;
    }
    
    
    public void addDestination(Destination destination){
        this.destinationList.add(destination);
    }
    
    public void addDestination(String destinationName){
        Destination destination = new Destination(travelPackageName, destinationName);
        this.destinationList.add(destination);
    }
    
    public void addActivity(Activity activity){
        for(int i = 0 ; i < destinationList.size(); i++){
            if(destinationList.get(i).getDestinationName().equals(activity.getDestinationName())){
                destinationList.get(i).addActivity(activity);
            }
        }
    }
    
//    public boolean isDestinationAvailable(Destination destination){
//        for(Destination d : this.getDestinationList()){
//            if(destination.getDestinationName().equals(d.getDestinationName())){
//                return true;
//            }
//        }
//        return false;
//    }
    
    
    
//    public ArrayList<TravelPackage> addTravelPackage(TravelPackage tp){
//        ArrayList<TravelPackage> new_package = new ArrayList<>();
//        if(this.travelPackageName.equals(tp.getTravelPackageName())){
//            for(Destination destination : tp.getDestinationList()){
//                Destination temp = getDestination(destination);
//                if(temp == null){
//                    this.destinationList.add(destination);
//                }else{
//                    
//                }
//            }
//            
//        }else{
//            new_package.add(this);
//            new_package.add(tp);
//        }
//        return new_package;
//    }
    
}
