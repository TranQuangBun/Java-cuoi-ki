package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class loginbody extends JFrame implements ActionListener {
    
    Container container=getContentPane();
    JLabel DN = new JLabel("DANG NHAP");
    JLabel user=new JLabel("USERNAME");
    JLabel password=new JLabel("PASSWORD");
    JTextField usertf =new JTextField();
    JPasswordField passwordf =new JPasswordField();
    JButton loginButton=new JButton("LOGIN");
    JButton resetButton=new JButton("RESET");
    JCheckBox showPassword=new JCheckBox("Show Password");
 
    loginbody()
    {
        setLayoutManager();
        setLocationAndSize();
        addComponents();
        addAction();
     
    }
    //setLayout
   public void setLayoutManager()
   {
       container.setLayout(null);
   }
   //setLocation and size
   public void setLocationAndSize()
   {
       //x,y,w,h
       DN.setBounds(150,100,150,30);
       user.setBounds(50,150,100,30);
       password.setBounds(50,220,100,30);
       usertf.setBounds(150,150,150,30);
       passwordf.setBounds(150,220,150,30);
       showPassword.setBounds(150,250,150,30);
       loginButton.setBounds(50,300,100,30);
       resetButton.setBounds(200,300,100,30);
   }
 
 
         //add component to loginbody
   public void addComponents()
   {
       container.add(DN);
       container.add(user);
       container.add(password);
       container.add(usertf );
       container.add(passwordf);
       container.add(showPassword);
       container.add(loginButton);
       container.add(resetButton);
   }
 //add action
 public void addAction(){
     loginButton.addActionListener(this);
     resetButton.addActionListener(this);
     showPassword.addActionListener(this);
 }
    @Override
    public void actionPerformed(ActionEvent e) {
 if (e.getSource() == loginButton) {
     String usertext;
     String passwordtext;
     usertext = usertf.getText();
     passwordtext = passwordf.getText();
     if (usertext.equalsIgnoreCase("tranquang") && passwordtext.equalsIgnoreCase("123456")) {
         JOptionPane.showMessageDialog(this, "Dang nhap thanh cong");
     }else
     {
         JOptionPane.showMessageDialog(this, "Dang nhap that bai");
     }
 }
 if (e.getSource() == resetButton) {
     usertf.setText("");
     passwordf.setText("");
 }
 if (e.getSource() == showPassword) {
     if (showPassword.isSelected() ) {
         passwordf.setEchoChar((char)0);
     }else{
passwordf.setEchoChar('*');
     }
 }
    }
}