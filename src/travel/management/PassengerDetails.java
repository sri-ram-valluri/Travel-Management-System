package travel.management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author sriramvalluri
 */
public class PassengerDetails extends JFrame implements ActionListener{
    String username;
    JTextField passenger_name_tf;
    JLabel passenger_number_value_lbl, balance_value_lbl;
    DefaultTableModel tableModel;

    PassengerDetails(String username){
        
        super("PASSENGER DETAILS");
        
        this.username = username;
        
        setBounds(420, 220, 800, 600);
        setLayout(null);
        
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel passenger_details_lbl = new JLabel("PASSENGER DETAILS");
        passenger_details_lbl.setFont(new Font("Yu Mincho", Font.BOLD, 30));
        passenger_details_lbl.setBounds(160, 11, 600, 53);
        contentPane.add(passenger_details_lbl);
        
        JLabel passenger_name_lbl = new JLabel("Name : ");
        passenger_name_lbl.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        passenger_name_lbl.setBounds(60, 80, 100, 25);
        contentPane.add(passenger_name_lbl);
        
        passenger_name_tf = new JTextField();
        passenger_name_tf.setBounds(160, 80, 300,25);
        passenger_name_tf.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(passenger_name_tf);
        
        JButton get_details_btn = new JButton("Get details");
        get_details_btn.setBounds(500, 80, 150, 30);
        get_details_btn.addActionListener(this);
        contentPane.add(get_details_btn);
        
        JLabel passenger_number_lbl = new JLabel("Number : ");
        passenger_number_lbl.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        passenger_number_lbl.setBounds(60, 110, 100, 25);
        contentPane.add(passenger_number_lbl);
        
        passenger_number_value_lbl = new JLabel();
        passenger_number_value_lbl.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        passenger_number_value_lbl.setBounds(160, 110, 100, 25);
        contentPane.add(passenger_number_value_lbl);
        
        JLabel balance_lbl = new JLabel("Balance : ");
        balance_lbl.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        balance_lbl.setBounds(60, 140, 100, 25);
        contentPane.add(balance_lbl);
        
        balance_value_lbl = new JLabel();
        balance_value_lbl.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        balance_value_lbl.setBounds(160, 140, 100, 25);
        contentPane.add(balance_value_lbl);
        
        JLabel activities_lbl = new JLabel("Activities Enrolled");
        activities_lbl.setBounds(60, 170, 200, 25);
        contentPane.add(activities_lbl);
        
        tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        table.setRowHeight(30);
        
        tableModel.addColumn("Activity Name");
        tableModel.addColumn("Destination Name");
        tableModel.addColumn("Price paid");
        
        for(int i = 0 ; i < 2; i++){
            table.getColumnModel().getColumn(i).setPreferredWidth(30);
        }
        
        JScrollPane table_container = new JScrollPane(table);
        table_container.setBounds(60, 200, 700, 200);
        contentPane.add(table_container);
        
        
    }
    public void actionPerformed(ActionEvent ae){
        try{
            tableModel.setRowCount(0);
            passenger_number_value_lbl.setText("");
            balance_value_lbl.setText("");
            String passenger_name = passenger_name_tf.getText();
            DBConnection conn = new DBConnection();
            
            String sql = "SELECT pa.passenger_number, pa.activity_name, pa.destination_name, p.category, p.balance, a.cost "
                    + "FROM passenger_activity pa "
                    + "JOIN passenger p "
                    + "ON p.passenger_name = pa.passenger_name "
                    + "JOIN activity a 	"
                    + "ON a.activity_name = pa.activity_name     "
                    + "AND a.destination_name = a.destination_name "
                    + "WHERE pa.username = '" + username + "' AND pa.passenger_name = '" + passenger_name + "'";
            
            ResultSet results = conn.stmt.executeQuery(sql);
            
            if (results.isBeforeFirst()) {
                while(results.next()){

                    int passenger_number = results.getInt("passenger_number");
                    int balance = results.getInt("balance");
                    passenger_number_value_lbl.setText(Integer.toString(passenger_number));
                    balance_value_lbl.setText(Integer.toString(balance));
                    ArrayList<String> row = new ArrayList();
                    row.add(results.getString("activity_name"));
                    row.add(results.getString("destination_name"));
                    int category = results.getInt("category");
                    int cost = results.getInt("cost");
                    if(category == 0){ // standard
                        row.add(Integer.toString(cost));
                    }else if(category == 1){ //gold
                        row.add(Double.toString(0.9 * cost));
                    }else { //premium
                        row.add("0");
                    }

                    tableModel.addRow(row.toArray(new Object[row.size()]));
                }    
                    
            } else {
                JOptionPane.showMessageDialog(null, "Passenger Name " + passenger_name + " is invalid.");
            }
            
            results.close();   
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
