package travel.management;

import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import travel.management.entity.*;
/**
 *
 * @author sriramvalluri
 */
public class ViewTravelPackages extends JFrame{
    String username;
    ArrayList<TravelPackage> travel_packages;
    
    ViewTravelPackages(String username){
        this.username = username;
        travel_packages = new ArrayList();
        
        setBounds(500,80,600,700);
        
        DBConnection conn = new DBConnection();
        try{
            ResultSet results;
            results = conn.stmt.executeQuery("SELECT tp.package_name\n" +
                    "FROM travel_package tp\n" +
                    "WHERE tp.username = '" + username + "'");
            
            while(results.next()){
                String package_name = results.getString("package_name");
                travel_packages.add(new TravelPackage(package_name));
            }
            results.close();
            
            for(TravelPackage travel_packet : travel_packages){
                results = conn.stmt.executeQuery("SELECT d.destination_name\n" +
                    "FROM travel_package tp\n" +
                    "JOIN destination d\n" +
                    "	ON tp.username = d.username \n" +
                    "	AND tp.package_name = d.package_name\n" +
                    "WHERE tp.username = '" + username+ "' AND tp.package_name = '" + travel_packet.getTravelPackageName() +"'");
                while(results.next()){
                    String destination_name = results.getString("destination_name");
                    travel_packet.addDestination(destination_name);
                }
            }
            results.close();
            for(TravelPackage travel_packet : travel_packages){
                for(Destination destination : travel_packet.getDestinationList()){
                    String destination_name = destination.getDestinationName();
                    results = conn.stmt.executeQuery("SELECT * FROM activity WHERE destination_name = '" + destination_name +"'");
                    while(results.next()){
                        String activity_name = results.getString("activity_name");
                        String activity_desc = results.getString("activity_desc");
                        int cost = results.getInt("cost");
                        int capacity = results.getInt("capacity");
                        travel_packet.addActivity(new Activity(activity_name, activity_desc, cost, capacity, destination_name));
                    }
                }
            }
            results.close();
                    
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        JTabbedPane tabbedpane = new JTabbedPane();
        for(int i = 0 ; i < travel_packages.size(); i++){
            
            TravelPackage tp = travel_packages.get(i);
            tabbedpane.addTab(tp.getTravelPackageName(), null, createPackagePanel(tp));
        }
        add(tabbedpane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public JPanel createPackagePanel(TravelPackage packageDetails){
        JPanel package_info_panel = new JPanel(new BorderLayout());
        package_info_panel.setBounds(0,80, 400, 900);
        
        
        
        DefaultListModel<String> list = new DefaultListModel<>();
        for(Destination dest : packageDetails.getDestinationList()){
            
            for(String val : dest.getStringList()){
                list.addElement(val);
            }
            JList<String> jList = new JList<>(list);
            JScrollPane jsp = new JScrollPane(jList);
            package_info_panel.add(jsp);
        }
        
        return package_info_panel;
    }
}
