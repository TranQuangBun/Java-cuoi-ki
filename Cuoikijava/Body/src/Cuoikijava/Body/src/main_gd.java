import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class main_gd extends JFrame{

    JFrame frame = new JFrame("Quản Lý Thí Sinh Thi THPT - QUẢN LÝ");
    JLabel idstudent = new JLabel("MÃ HỌC SINH");
    JLabel name = new JLabel("HỌ TÊN");
    JLabel genderlb = new JLabel("GIỚI TÍNH");
    JLabel birthday = new JLabel("NGÀY SINH");
    JLabel diemmontoan = new JLabel("ĐIỂM TOÁN");
    JLabel diemmonvan = new JLabel("ĐIỂM VĂN");
    JLabel diemmonanh = new JLabel("ĐIỂM ANH");

    //bảng
    JTable tbhouse = new JTable();
    JScrollPane sp = new JScrollPane(tbhouse);
    //db
    PreparedStatement pst;
    Connection conn = null;
    ResultSet rs;
    DefaultTableModel tm;

    //textfield

    JTextField idstudenttf = new JTextField(10);
    JTextField nameTextField = new JTextField(100);
    String[] gender = {"", "Nam", "Nữ"};
    JComboBox cboGender = new JComboBox(gender);
    JTextField birthdaytf = new JTextField(30);
    JTextField toantf = new JTextField(10);
    JTextField vantf = new JTextField(10);
    JTextField anhtf = new JTextField(10);

    //button

    JButton themButton = new JButton("THÊM THÔNG TIN");
    JButton refreshButton = new JButton("LÀM MỚI");
    JButton suaButton = new JButton("SỬA THÔNG TIN");
    JButton xoaButton = new JButton("XÓA THÔNG TIN");
    JButton findButton = new JButton("TÌM KIẾM THÔNG TIN THÍ SINH");
    JButton qltruongButton = new JButton("QUẢN LÝ TRƯỜNG HỌC");
     public main_gd(){
//panel 1
         JPanel panel = new JPanel();
         panel.add(idstudent);
         panel.add(idstudenttf);
         panel.add(name);
         panel.add(nameTextField);

         panel.add(genderlb);
         panel.add(cboGender);

         panel.add(birthday);
         panel.add(birthdaytf);
         panel.add(diemmontoan);
         panel.add(toantf);
         panel.add(diemmonvan);
         panel.add(vantf);
         panel.add(diemmonanh);
         panel.add(anhtf);
         panel.add(themButton);
         panel.add(xoaButton);
         panel.add(suaButton);
         panel.add(refreshButton);
         panel.add(findButton);
         panel.add(qltruongButton);

         panel.setBackground(Color.pink);
         panel.setLayout(new GridLayout (10,2,1,1));
         Border bd = BorderFactory.createLineBorder(Color.pink);
//head title
         TitledBorder tbdpnall= BorderFactory.createTitledBorder(bd, "QUẢN LÝ THÍ SINH THI VÀO LỚP 10 ");
         tbdpnall.setTitleFont(new Font("Calibri", Font.BOLD, 15));
         tbdpnall.setTitleColor(Color.black);
         panel.setBorder(tbdpnall);

//panel 2
         JPanel pn = new JPanel();
         pn.add(sp);
         pn.setBackground(Color.pink);
         Border bdtb = BorderFactory.createLineBorder(Color.pink);
         TitledBorder tbtb= BorderFactory.createTitledBorder(bdtb, "THÔNG TIN THÍ SINH");
         tbtb.setTitleFont(new Font("Calibri", Font.BOLD, 15));
         tbtb.setTitleColor(Color.black);
         pn.setBorder(tbtb);
         Display();

//set color
         idstudent.setForeground(Color.black);
         name.setForeground(Color.black);
         birthday.setForeground(Color.black);
         genderlb.setForeground(Color.black);
         diemmontoan.setForeground(Color.black);
         diemmonanh.setForeground(Color.black);
         diemmonvan.setForeground(Color.black);

         suaButton.setBackground(Color.BLUE);
         suaButton.setForeground(Color.WHITE);
         refreshButton.setBackground(Color.BLUE);
         refreshButton.setForeground(Color.white);


//Action

         themButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 ConnectDB condb = new ConnectDB();
                 String ngaysinh = birthdaytf.getText();
                 Date date = Date.valueOf(ngaysinh);
                 String query = "INSERT INTO thisinh(mahocsinh,hoten,gioitinh,ngaysinh,toan,van,anh) VALUES (?,?,?,?,?,?,?)";
                 conn = condb.connect_SQL();
                 try{
                     pst = conn.prepareStatement(query);
                     pst.setString(1,idstudenttf.getText());
                     pst.setString(2,nameTextField.getText());
                     String s ;
                     s = cboGender.getSelectedItem().toString();
                     pst.setString(3,s);
                     pst.setString(4,String.valueOf(date));
                     pst.setString(5,toantf.getText());
                     pst.setString(6,anhtf.getText());
                     pst.setString(7,vantf.getText());
                     pst.executeUpdate();
                     JOptionPane.showMessageDialog(null, "Thêm thành công");
                 }
                 catch(Exception e1){
                     e1.printStackTrace();
                     JOptionPane.showMessageDialog(null," Thêm thất bại, vui lòng thử lại");
                 }
             }
         });
         qltruongButton.addActionListener(new ActionListener() {
             @Override
              public void actionPerformed(ActionEvent e) {
                 if (e.getSource() == qltruongButton){
                     new truonghoc();
                 }
            }
         });
         suaButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
             int ret = JOptionPane.showConfirmDialog(suaButton, "Bạn có muốn sửa không ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if(ret != JOptionPane.YES_OPTION){
                    return;
                }
                 ConnectDB condb = new ConnectDB();
                 String ngaysinh = birthdaytf.getText();
                 Date date = Date.valueOf(ngaysinh);
                 //  Date date = Date.valueOf(ngaysinh);
                 String query = "UPDATE thisinh SET hoten=?,gioitinh =?, ngaysinh =? , toan =?, van =?, anh =? WHERE mahocsinh = ?";
                 conn = condb.connect_SQL();
                 try{ //:D
                     pst = conn.prepareStatement(query);
                     pst.setString(1,nameTextField.getText());
                     String s ;
                     s = cboGender.getSelectedItem().toString();
                     pst.setString(2,s);
                     pst.setString(3,ngaysinh);
                     pst.setString(4,toantf.getText());
                     pst.setString(5,vantf.getText());
                     pst.setString(6,anhtf.getText());
                     pst.setString(7,idstudenttf.getText());
                     pst.executeUpdate();
                     JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                 }
                 catch(Exception e1){
                     e1.printStackTrace();
                     JOptionPane.showMessageDialog(null," Cập nhật thất bại, vui lòng thử lại");
                 }
             }

         });
         xoaButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 int ret = JOptionPane.showConfirmDialog(xoaButton, "Bạn có muốn xóa không ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                 if (ret != JOptionPane.YES_OPTION) {
                     return;
                 }
              ConnectDB condb = new ConnectDB();
              conn = condb.connect_SQL();
                 String query = "DELETE FROM thisinh WHERE mahocsinh = ?";
                 conn = condb.connect_SQL();
                 try{
                     pst = conn.prepareStatement(query);
                     pst.setString(1,idstudenttf.getText());
                     pst.executeUpdate();
                     JOptionPane.showMessageDialog(null, "Xóa thành công");
                 }
                 catch(Exception e1){
                     e1.printStackTrace();
                     JOptionPane.showMessageDialog(null," Xóa thất bại, vui lòng thử lại");
                 }
             }
         });
         refreshButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 ConnectDB cn = new ConnectDB();
                 rs = cn.Danhsach("SELECT*FROM thisinh");
                 String[] column = {"mahocsinh","hoten", "gioitinh", "ngaysinh", "toan", "van", "anh"};
                 String gender = cboGender.getSelectedItem().toString();
                 DefaultTableModel tm = new DefaultTableModel(column, 0);
                 try {
                     while (rs.next()) {
                         Object data[] = {rs.getString("mahocsinh"), rs.getString("hoten"), rs.getString("gioitinh"), rs.getString("ngaysinh"), rs.getString("toan"), rs.getString("van"), rs.getString("anh")};
                         tm.addRow(data);
                     }
                     tbhouse.setModel(tm);
                 } catch (SQLException e1) {
                     e1.printStackTrace();
                 }
             }
         });
         findButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 new search_ts();
             }
         });
//frame
         frame.setVisible(true);
         frame.pack();
         frame.setSize(1200, 500);
         frame.setLocationRelativeTo(null);
         frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         Container cont = frame.getContentPane();
         cont.add(panel, BorderLayout.CENTER);
         cont.add(pn, BorderLayout.EAST);
         setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Acer\\Downloads\\227-2271076_student-management-system-symbol-hd-png-download.png"));

     }
    public void Display(){

        ConnectDB cn = new ConnectDB();
        rs = cn.Danhsach("SELECT*FROM thisinh");
        String[] column = {"mahocsinh","hoten", "gioitinh", "ngaysinh", "toan", "van", "anh"};
        String gender = cboGender.getSelectedItem().toString();
        DefaultTableModel tm = new DefaultTableModel(column, 0);
        try {
            while (rs.next()) {
                Object data[] = {rs.getString("mahocsinh"), rs.getString("hoten"), rs.getString("gioitinh"), rs.getString("ngaysinh"), rs.getString("toan"), rs.getString("van"), rs.getString("anh")};
                tm.addRow(data);
            }
            tbhouse.setModel(tm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String [] agrs){

         new main_gd();
    }
}
