package travel.management;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author sriramvalluri
 */
public class Loading extends JFrame implements Runnable{
    
    String username, name;
    JProgressBar bar;
    Thread thread;
    
    public Loading(String username, String name){
        this.username = username;
        this.name = name;
        
        thread = new Thread(this);
        
        setBounds(500, 200, 650,400 );
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading_label = new JLabel("NYMBLE TRAVEL MANAGEMENT SYSTEM");
        heading_label.setBounds(50, 20, 600, 40);
        heading_label.setForeground(Color.BLUE);
        heading_label.setFont(new Font("Raleway", Font.BOLD, 25));
        add(heading_label);
        
        JLabel wait_label = new JLabel("Loading, Please wait " + name);
        wait_label.setBounds(150, 65, 600, 40);
        wait_label.setForeground(Color.RED);
        wait_label.setFont(new Font("Raleway", Font.ITALIC, 20));
        add(wait_label);
        
        bar = new JProgressBar();
        bar.setBounds(180, 100, 300, 40);
        add(bar);
        
        thread.start();
        setVisible(true);
        
    }
    
    public void run(){
        try{
            for(int i = 1 ; i <= 101; i++){
                int max = bar.getMaximum();
                int value = bar.getValue();
                
                if(value < max){
                    bar.setValue(bar.getValue() + 1);
                } else{
                    
                    setVisible(false);
                    new Dashboard(username, name).setVisible(true);
                }
                Thread.sleep(20);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
