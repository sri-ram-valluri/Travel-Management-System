package travel.management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
/**
 *
 * @author sriramvalluri
 */
public class PassengerList extends JFrame implements ActionListener{
    String username;
    JLabel passenger_cap_value_lbl,passenger_enrolled_value_lbl;
    DefaultTableModel tableModel;
    Choice travel_package_choice;
    
    PassengerList(String username){
        
        super("PASSENGER LIST");
        
        this.username = username;
        
        setBounds(420, 220, 1100, 600);
        setLayout(null);
        
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel passenger_list_label = new JLabel("PASSENGER LIST");
        passenger_list_label.setFont(new Font("Yu Mincho", Font.BOLD, 30));
        passenger_list_label.setBounds(270, 11, 600, 53);
        contentPane.add(passenger_list_label);
        
        JLabel select_package_lbl = createAndAddLabels(contentPane, "Select Package :", 35, 110, 150, 30);
        
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
        
        tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        table.setRowHeight(30);
        
        tableModel.addColumn("Passenger Name");
        tableModel.addColumn("Passenger Number");
        tableModel.addColumn("Activity Enrolled");
        
        for(int i = 0 ; i < 2; i++){
            table.getColumnModel().getColumn(i).setPreferredWidth(30);
        }
        
        
        JScrollPane table_container = new JScrollPane(table);
        table_container.setBounds(50, 250, 700, 200);
        contentPane.add(table_container);
        
        JButton check_passengers_btn = new JButton("Print Passengers");
        check_passengers_btn.setBounds(500, 110, 150, 30);
        check_passengers_btn.addActionListener(this);

        
        contentPane.add(check_passengers_btn);
        
        JLabel passenger_cap_lbl = createAndAddLabels(contentPane, "Passenger Capacity :", 35, 150, 250, 30); 
        passenger_cap_value_lbl = createAndAddLabels(contentPane, "0", 285, 150, 150, 30);
        
        JLabel passenger_enrolled_lbl = createAndAddLabels(contentPane, "No. of passengers enrolled :", 35, 180, 250, 30); 
        passenger_enrolled_value_lbl = createAndAddLabels(contentPane, "0", 285, 180, 150, 30);
       
    }
    
    public JLabel createAndAddLabels(JPanel panel, String Label_name,int x, int y, int width, int height){
        JLabel lbl = new JLabel(Label_name);
        lbl.setBounds(x, y, width, height);
        panel.add(lbl);
        return lbl;
    }
    
    public void actionPerformed(ActionEvent ae){
        
        try
        {
            DBConnection conn = new DBConnection();
            String sql = "SELECT d.destination_name, ac.activity_name, ac.capacity "
                    + "FROM travel_package tp "
                    + "JOIN destination d "
                    + "ON tp.username = d.username "
                    + "AND tp.package_name = d.package_name "
                    + "JOIN activity ac "
                    + "ON ac.destination_name = d.destination_name "
                    + "WHERE tp.username = '" + username + "' AND tp.package_name = '" + travel_package_choice.getSelectedItem() + "'";

            ResultSet rs = conn.stmt.executeQuery(sql);
            int passenger_capacity = 0;
            while(rs.next()) {
                passenger_capacity += rs.getInt("capacity");
            }
            passenger_cap_value_lbl.setText(Integer.toString(passenger_capacity));
            
            tableModel.setRowCount(0);
            
            sql = "SELECT pa.passenger_name, pa.passenger_number, pa.activity_name "
                    + "FROM travel_package tp "
                    + "JOIN destination d "
                    + "ON tp.username = d.username "
                    + "AND tp.package_name = d.package_name "
                    + "JOIN passenger_activity pa "
                    + "ON tp.username = pa.username "
                    + "AND d.destination_name = pa.destination_name "
                    +"WHERE tp.username = '" + username + "' AND tp.package_name = '" + travel_package_choice.getSelectedItem() + "'";
            
            rs = conn.stmt.executeQuery(sql);
            int passenger_enrolled = 0;
            while(rs.next()){
                passenger_enrolled++;
                ArrayList<String> row = new ArrayList();
                row.add(rs.getString("passenger_name"));
                row.add(Integer.toString(rs.getInt("passenger_number")));
                row.add(rs.getString("activity_name"));
                tableModel.addRow(row.toArray(new Object[row.size()]));
            }
            passenger_enrolled_value_lbl.setText(Integer.toString(passenger_enrolled));
            rs.close();
            
        } catch (Exception e2) {
                e2.printStackTrace();
        }
    }
    
}
