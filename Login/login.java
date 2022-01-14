package Login;
import java.awt.*;
import javax.swing.*;
/**
 * login
 */
public class login {

    public static void main(String[] args) {
       loginbody frame = new loginbody();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10,10,370,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setcolor
        frame.getContentPane().setBackground(Color.CYAN);
    }
}