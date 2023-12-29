package travel.management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author sriramvalluri
 */
public class MyDetails extends JFrame{
    String username;
    JButton back_btn;
    final int LABEL_WIDTH = 150;
    final int LABEL_HEIGHT = 40;
    final int LABEL_X1 = 200;
    final int LABEL_X2 = 350;
    
    MyDetails(String username){
        this.username = username;
        String name = "Unable to fetch";
        String password = "Unable to fetch";
        
        
        setBounds(500, 220, 600, 500);
        setLayout(null);
        
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        createAndAddLabels(contentPane, "MY  DETAILS", new Font("Yu Mincho", Font.PLAIN, 20), 250, 11, 350, 53);
        
        createAndAddLabels(contentPane, "Username : ",LABEL_X1, 100, LABEL_WIDTH, LABEL_HEIGHT);
        createAndAddLabels(contentPane, "Name : ",LABEL_X1, 140, LABEL_WIDTH, LABEL_HEIGHT);
        createAndAddLabels(contentPane, "Password : ",LABEL_X1, 180, LABEL_WIDTH, LABEL_HEIGHT);
        
        
        DBConnection conn = new DBConnection();
        try{
            
            ResultSet results = conn.stmt.executeQuery("SELECT * FROM agencies WHERE username = '" + username + "'");
            
            while(results.next()){
                name = results.getString("name");
                password = results.getString("password");
            }
            
            results.close();   
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        createAndAddLabels(contentPane, username,LABEL_X2, 100, LABEL_WIDTH, LABEL_HEIGHT);
        createAndAddLabels(contentPane, name,LABEL_X2, 140, LABEL_WIDTH, LABEL_HEIGHT);
        createAndAddLabels(contentPane, password,LABEL_X2, 180, LABEL_WIDTH, LABEL_HEIGHT);
        
        back_btn = new JButton("Back");
        back_btn.addActionListener((ActionEvent e) -> {
            setVisible(false);
        }); 
        
        back_btn.setBounds(250, 350, 120, 30);
        contentPane.add(back_btn);
        
        getContentPane().setBackground(Color.WHITE);
        
    }
    
    public void createAndAddLabels(JPanel panel, String Label_name, Font font,int x, int y, int width, int height){
        JLabel lbl = new JLabel(Label_name);
        lbl.setBounds(x, y, width, height);
        lbl.setFont(font);
        panel.add(lbl);
    }
    public void createAndAddLabels(JPanel panel, String Label_name,int x, int y, int width, int height){
        JLabel lbl = new JLabel(Label_name);
        lbl.setBounds(x, y, width, height);
        panel.add(lbl);
    }
    
}
