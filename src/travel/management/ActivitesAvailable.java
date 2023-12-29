package travel.management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author sriramvalluri
 */
public class ActivitesAvailable extends JFrame implements ActionListener{
    
    String username;
    DefaultTableModel tableModel;
    Choice travel_package_choice;
    
    ActivitesAvailable(String username) {
        super("ACTIVITIES AVAILABLE");
        
        this.username = username;
        
        setBounds(420, 220, 1100, 600);
        setLayout(null);
        
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
        JLabel activites_avail_label = new JLabel("ACTIVITIES AVAILABLE");
        activites_avail_label.setFont(new Font("Yu Mincho", Font.BOLD, 30));
        activites_avail_label.setBounds(270, 11, 600, 53);
        contentPane.add(activites_avail_label);
        
        JLabel select_pkg_lbl = new JLabel("Select Package :");
        select_pkg_lbl.setFont(new Font("Yu Mincho", Font.PLAIN, 15));
        select_pkg_lbl.setBounds(35, 110, 150, 30);
        contentPane.add(select_pkg_lbl);
        
        travel_package_choice = new Choice();
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
        
        JButton activites_avail_btn = new JButton("Check available activities");
        activites_avail_btn.setBounds(500, 110, 200, 30);
        activites_avail_btn.addActionListener(this);
        contentPane.add(activites_avail_btn);
        
        tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        table.setRowHeight(30);
        
        tableModel.addColumn("Activity Name");
        tableModel.addColumn("Destination Name");
        tableModel.addColumn("Spaces Available");
        
        for(int i = 0 ; i < 2; i++){
            table.getColumnModel().getColumn(i).setPreferredWidth(30);
        }
        
        JScrollPane table_container = new JScrollPane(table);
        table_container.setBounds(60, 200, 700, 200);
        contentPane.add(table_container);
           
    }
    
    public void actionPerformed(ActionEvent ae){
        tableModel.setRowCount(0);
        try{
            DBConnection conn = new DBConnection();
            String sql = "SELECT d.destination_name, ac.activity_name, ac.capacity "
                    + "FROM travel_package tp "
                    + "JOIN destination d "
                    + "ON tp.username = d.username "
                    + "AND tp.package_name = d.package_name "
                    + "JOIN activity ac "
                    + "ON ac.destination_name = d.destination_name "
                    + "WHERE tp.username = '" + username + "' AND tp.package_name = '" + travel_package_choice.getSelectedItem() + "'";

            ResultSet results = conn.stmt.executeQuery(sql);
            ArrayList<ArrayList<String>> data = new ArrayList();
            
            while(results.next()){
                ArrayList<String> row = new ArrayList();
                row.add(results.getString("activity_name"));
                row.add(results.getString("destination_name"));
                row.add(Integer.toString(results.getInt("capacity")));
                data.add(row);
            }
            results.close();
            
            for(ArrayList<String> row : data){
                sql = "SELECT count(*) as enrolled FROM passenger_activity "
                    + "WHERE username = '" + username
                    + "' AND activity_name = '" + row.get(0)
                    + "' AND destination_name = '" +row.get(1) + "'";
                results = conn.stmt.executeQuery(sql);
                if(results.next()){
                   int enrolled = results.getInt("enrolled");
                    int capacity = Integer.parseInt(row.get(2));
                    if(capacity > enrolled){
                        ArrayList<String> available_row = new ArrayList();
                        available_row.add(row.get(0));
                        available_row.add(row.get(1));
                        available_row.add(Integer.toString(capacity - enrolled));
                        tableModel.addRow(available_row.toArray(new Object[available_row.size()]));
                    }
                }
                results.close();
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
