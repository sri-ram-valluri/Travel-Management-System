package travel.management;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
/**
 *
 * @author sriramvalluri
 */
public class AgencyLogin extends JFrame implements ActionListener{
    
    final int FRAME_WIDTH = 1200;
    final int FRAME_HEIGHT = 400;
    
    JTextField username_tf, password_tf;
    JButton login_btn;
     
    AgencyLogin(){
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setLocation(350, 200);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        JPanel info_panel = new JPanel();
        info_panel.setBackground(Color.GREEN);
        info_panel.setLayout(null);
        info_panel.setBounds(0,0, 600,400);
        add(info_panel);
        
        JLabel heading_label = new JLabel("NYMBLE TRAVEL MANAGEMENT SYSTEM");
        heading_label.setBounds(20, 100, 600, 100);
        heading_label.setFont(new Font("Raleway", Font.BOLD, 25));
        info_panel.add(heading_label);
        
        JPanel  details_panel = new JPanel();
        details_panel.setBounds(600,0, 600, 400);
        details_panel.setLayout(null);
        add(details_panel);
        
        JLabel username_label = new JLabel("Username");
        username_label.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        username_label.setBounds(60, 60, 100, 25);
        details_panel.add(username_label);
        
        username_tf = new JTextField();
        username_tf.setBounds(60, 90, 300,25);
        username_tf.setBorder(BorderFactory.createEmptyBorder());
        details_panel.add(username_tf);
        
        
        JLabel password_label = new JLabel("Password");
        password_label.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        password_label.setBounds(60, 120, 100, 25);
        details_panel.add(password_label);
        
        password_tf = new JTextField();
        password_tf.setBounds(60, 150, 300,25);
        password_tf.setBorder(BorderFactory.createEmptyBorder());
        details_panel.add(password_tf);
        
        login_btn = new JButton("Login");
        login_btn.setBounds(160, 180, 100, 30);
        login_btn.addActionListener(this);
        details_panel.add(login_btn);
                
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == login_btn){
            String username = username_tf.getText();
            String password = password_tf.getText();
            Boolean status = false;
            try{
                DBConnection conn = new DBConnection();
                String sql = "SELECT * FROM agencies WHERE username=? and password=?";
                PreparedStatement statement = conn.conn.prepareStatement(sql);
                
                statement.setString(1, username);
                statement.setString(2, password);
                
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    this.setVisible(false);
                    new Loading(username,rs.getString("name")).setVisible(true);
                    
                } else
                    JOptionPane.showMessageDialog(null, "Invalid Login or Password!");
                       
		} catch (Exception e2) {
                    e2.printStackTrace();
		}
        }
    }
    
    public static void main(String[] args){
        new AgencyLogin();
    }
}
