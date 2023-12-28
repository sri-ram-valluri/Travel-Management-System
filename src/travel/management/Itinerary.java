package travel.management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;
import travel.management.entity.TravelPackage;

/**
 *
 * @author sriramvalluri
 */
public class Itinerary extends JFrame{
    String username;
    TravelPackage travel_package;
    ArrayList<ArrayList<String>> table_data;
    String[] column_names = {"Destination Name ","Activity name","Activity Description","Capacity","Cost"};
    int[] column_width = {50, 30, 70, 5, 5};
    final int COLUMN_SIZE = 5;
    JTable table;
    Object[][] table_data_object;
    
    Itinerary(String username){
        
        super("ITINERARY OF TRAVEL PACKAGE");
        
        this.username = username;
        
        setBounds(420, 220, 1100, 600);
        setLayout(null);
        
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel itinerary_label = new JLabel("ITINERARY OF TRAVEL PACKAGE");
        itinerary_label.setFont(new Font("Yu Mincho", Font.BOLD, 30));
        itinerary_label.setBounds(270, 11, 600, 53);
        contentPane.add(itinerary_label);
        
        JLabel select_pkg_lbl = new JLabel("Select Package :");
        select_pkg_lbl.setFont(new Font("Yu Mincho", Font.PLAIN, 15));
        select_pkg_lbl.setBounds(35, 110, 150, 30);
        contentPane.add(select_pkg_lbl);
        
        Choice travel_package_choice = new Choice();
        travel_package_choice.setBounds(185, 110, 300, 30);
        
        DBConnection conn = new DBConnection();
        try {

            ResultSet results = conn.stmt.executeQuery("SELECT tp.package_name\n"
                    + "FROM travel_package tp\n"
                    + "WHERE tp.username = '" + username + "'");

            while (results.next()) {
                String package_name = results.getString("package_name");
                travel_package_choice.add(package_name);
            }
            
            results.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        contentPane.add(travel_package_choice);
        
        DefaultTableModel tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setRowHeight(30);
        for(int i = 0 ; i < COLUMN_SIZE; i++){
            tableModel.addColumn(column_names[i]);
        }
        for(int i = 0 ; i < COLUMN_SIZE; i++){
            table.getColumnModel().getColumn(i).setPreferredWidth(column_width[i]);
        }
        
        
        JScrollPane table_container = new JScrollPane(table);
        table_container.setBounds(50, 150, 700, 200);
        contentPane.add(table_container);
        
        
        JButton check_itinerary_btn = new JButton("Check itinerary");
        check_itinerary_btn.setBounds(500, 110, 150, 30);
        
        check_itinerary_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String package_name = travel_package_choice.getSelectedItem();
                try{
                    tableModel.setRowCount(0);
                    ResultSet results = conn.stmt.executeQuery("SELECT d.destination_name, ac.activity_name, ac.activity_desc, ac.capacity, ac.cost "
                            + "FROM travel_package tp "
                            + "JOIN destination d "
                            + "ON tp.username = d.username "
                            + "AND tp.package_name = d.package_name "
                            + "JOIN activity ac "
                            + "ON ac.destination_name = d.destination_name "
                            + "WHERE tp.username = '" + username 
                            + "'AND tp.package_name = '" + package_name + "'");
                    while(results.next()){
                         ArrayList<String> row = new ArrayList();
                         row.add(results.getString("destination_name"));
                         row.add(results.getString("activity_name"));
                         row.add(results.getString("activity_desc"));
                         row.add(Integer.toString(results.getInt("capacity")));
                         row.add(Integer.toString(results.getInt("cost")));
                         tableModel.addRow(row.toArray(new Object[row.size()]));
                    }
                    results.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
                
            }
        });
        
        
        contentPane.add(check_itinerary_btn);
        
        
    }
    
    public static void main(String[] args){
        new Itinerary("agency1").setVisible(true);
    }
}
