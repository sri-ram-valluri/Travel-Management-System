package travel.management;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author sriramvalluri
 */
public class Dashboard extends JFrame implements ActionListener{
    String username, agency_name;
    JButton my_details_btn, travel_packages_btn, destinations_btn, itinerary_btn, passenger_list_btn, passenger_details_btn, available_activites_btn, about_btn ;
    
    Dashboard(String username, String agency_name){
        super("NYMBLE TRAVEL MANAGEMENT SYSTEM");
        this.username = username;
        this.agency_name = agency_name;
        setForeground(Color.CYAN);
        
        
        setBounds(0,0,1600,900);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        JPanel top_panel = new JPanel();        
        top_panel.setLayout(null);
        top_panel.setBackground(Color.CYAN);
        top_panel.setBounds(0,0, 1600, 80);
        add(top_panel);
        
        JLabel heading = new JLabel("Dashboard");
        heading.setBounds(20, 40, 1600, 25);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        top_panel.add(heading);
        
        JPanel buttons_panel = new JPanel();
        buttons_panel.setLayout(null);
        buttons_panel.setBounds(0,80, 400, 900);
        buttons_panel.setBackground(Color.BLUE);
        add(buttons_panel);
        
        JPanel text_panel = new JPanel();
        text_panel.setLayout(null);
        text_panel.setBounds(400,80, 1200, 900);
        text_panel.setBackground(Color.ORANGE);
        add(text_panel);
        
        my_details_btn = setCustomButton("My Details", 0, 0, 400, 100, buttons_panel);
        travel_packages_btn = setCustomButton("Travel Packages", 0, 100, 400, 100, buttons_panel);
        destinations_btn = setCustomButton("Destinations", 0, 200, 400, 100, buttons_panel);
        itinerary_btn = setCustomButton("Itinerary", 0, 300, 400, 100, buttons_panel);
        passenger_list_btn = setCustomButton("Passenger List", 0, 400, 400, 100, buttons_panel);
        passenger_details_btn = setCustomButton("Passenger Details", 0, 500, 400, 100, buttons_panel);
        available_activites_btn = setCustomButton("Available Activities", 0, 600, 400, 100, buttons_panel);
        about_btn = setCustomButton("About", 0, 700, 400, 100, buttons_panel);
        
        JLabel text_panel_heading = new JLabel("TRAVEL MANAGEMENT SYSTEM - " + agency_name);
        text_panel_heading.setBounds(250, 20, 1200, 40);
        text_panel_heading.setFont(new Font("Raleway", Font.BOLD, 25));
        text_panel.add(text_panel_heading);
        
        setVisible(true);
    }
    
    public JButton setCustomButton(String btn_name, int x, int y, int width, int height, JPanel panel){
        JButton btn = new JButton(btn_name);
        btn.setBounds(x, y, width, height);
        btn.setBackground(Color.BLUE);
        btn.setFont(new Font("Tahoma", Font.BOLD, 20));
        btn.addActionListener(this);
        panel.add(btn);
        return btn;
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == my_details_btn){
            try{
                new MyDetails(username).setVisible(true);
            }catch (Exception e){
                e.printStackTrace();
            }
        } 
        if(ae.getSource() == travel_packages_btn){
            try{
                new ViewTravelPackages(username).setVisible(true);    
		} catch (Exception e2) {
                    e2.printStackTrace();
		}
        }
        if(ae.getSource() == destinations_btn){
        }
        if(ae.getSource() == itinerary_btn){
            try{
               new Itinerary(username).setVisible(true);    
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if(ae.getSource() == passenger_list_btn){
            try{
               new PassengerList(username).setVisible(true);    
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if(ae.getSource() == passenger_details_btn){
            try{
               new PassengerDetails(username).setVisible(true);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if(ae.getSource() == available_activites_btn){
            try{
               new ActivitesAvailable(username).setVisible(true);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if(ae.getSource() == about_btn){
        }        
    }        
}
