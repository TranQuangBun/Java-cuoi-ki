import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class login extends JFrame implements ActionListener {
    Container container=getContentPane();

    JLabel ten=new JLabel("TÊN ĐĂNG NHẬP");
    JLabel password=new JLabel("MẬT KHẨU");
    JTextField tentf =new JTextField();
    JPasswordField passwordf =new JPasswordField();
    JButton loginButton=new JButton("LOGIN");
    JButton resetButton=new JButton("RESET");

    login()
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
        ten.setBounds(40,100,120,30);
        password.setBounds(40,160,120,30);
        tentf.setBounds(40,130,150,30);
        passwordf.setBounds(40,190,150,30);
        loginButton.setBounds(40,300,100,30);
        resetButton.setBounds(170,300,100,30);
    }


    //add component to login
    public void addComponents()
    {
        container.add(ten);
        container.add(password);
        container.add(tentf );
        container.add(passwordf);
        container.add(loginButton);
        container.add(resetButton);
    }
    //add action
    public void addAction(){
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String tentext;
            String passwordtext;
            tentext = tentf.getText();
            passwordtext = passwordf.getText();
            if (tentext.equalsIgnoreCase("xmai") && passwordtext.equalsIgnoreCase("xmai123")) {
                new main();
            }else
            {
                JOptionPane.showMessageDialog(this, "Đăng nhập thất bại");
            }
        }
        if (e.getSource() == resetButton) {
            tentf.setText("");
            passwordf.setText("");
        }
    }

    public static void main(String[] args) {
        login frame = new login();
        frame.setTitle("Login");
        frame.setVisible(true);
        frame.setBounds(10,10,300,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
