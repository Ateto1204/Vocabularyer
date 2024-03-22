package myproject;

/**
 *
 * @author Ateto
 *
 * Description: Home class
 */

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Home implements ActionListener{
    
    public JFrame frame = new JFrame("學測 7000 單");
    public int page = 0;
    public JButton CButton;
    public JButton EButton;
    public JButton SButton;
    
    
    public Home(){
        
        CButton = setButton(0);
        CButton.setText("中翻英");
        
        EButton = setButton(1);
        EButton.setText("英翻中");
        
        SButton = setButton(2);
        SButton.setText("拼字");
        
        frame.add(CButton);
        frame.add(EButton);
        frame.add(SButton);
        frame.setBounds(450, 150, 420, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    
    }
    
    private JButton setButton(int idx){
        JButton button = new JButton();
        button.setBounds(130, 100+idx*60, 150, 50);
        button.addActionListener(this);
        button.setFocusable(false);
        button.setBackground(Color.WHITE);
        
        return button;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
    }
    
    public void removeAllButton(){
        CButton.setVisible(false);
        EButton.setVisible(false);
        SButton.setVisible(false);
    }
    
    public void returnHome(){
        CButton.setVisible(true);
        EButton.setVisible(true);
        SButton.setVisible(true);
    }
    
}
