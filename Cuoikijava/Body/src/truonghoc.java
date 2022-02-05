import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class truonghoc extends JFrame{
    PreparedStatement pst;
    Connection conn = null;
    ResultSet rs;
    DefaultTableModel tm;
JFrame frame = new JFrame("Quản Lý Thí Sinh Thi THPT - QUẢN LÝ TRƯỜNG HỌC");
JLabel idst= new JLabel("MÃ HỌC SINH");
JLabel idschool = new JLabel("MÃ TRƯỜNG");
JLabel nameschool = new JLabel("TÊN TRƯỜNG");

JTable tb = new JTable();
JScrollPane sp = new JScrollPane(tb);
JTextField idsttf = new JTextField(10);
JTextField idschooltf = new JTextField(10);
String[] namesc = {"","THPT Dao Duy Tu","THPT Vo Nguyen Giap","THPT Phan Dinh Phung"};
JComboBox cbonameschool = new JComboBox(namesc);

    JButton themButton = new JButton("THÊM THÔNG TIN");
    JButton suaButton = new JButton("SỬA THÔNG TIN");
    JButton xoaButton = new JButton("XÓA THÔNG TIN");
    JButton refreshButton = new JButton("LÀM MỚI");
    JButton returnButton = new JButton("QUAY LẠI");
    public truonghoc() {
        JPanel panel = new JPanel();
        panel.add(idst);
        panel.add(idsttf);
        panel.add(idschool);
        panel.add(idschooltf);
        panel.add(nameschool);
        panel.add(cbonameschool);
idst.setFont(new Font("Calibri", Font.BOLD, 15));
idschool.setFont(new Font("Calibri", Font.BOLD, 15));
nameschool.setFont(new Font("Calibri", Font.BOLD, 15));
idst.setForeground(Color.black);
idschool.setForeground(Color.black);
nameschool.setForeground(Color.black);
        panel.add(themButton);
        themButton.setFont(new Font("Calibri", Font.BOLD, 17));
        panel.add(suaButton);
        suaButton.setFont(new Font("Calibri", Font.BOLD, 17));
        panel.add(xoaButton);
        xoaButton.setFont(new Font("Calibri", Font.BOLD, 17));
        panel.add(refreshButton);
        refreshButton.setFont(new Font("Calibri", Font.BOLD, 17));
//1
        panel.setBackground(Color.pink);
        panel.setLayout(new GridLayout(5, 2,20 ,10));
        Border bd = BorderFactory.createLineBorder(Color.pink);

        TitledBorder title = BorderFactory.createTitledBorder(bd, "QUẢN LÝ THÍ TRƯỜNG ");
        title.setTitleFont(new Font("Calibri", Font.BOLD, 15));
        title.setTitleColor(Color.black);
        panel.setBorder(title);
// 2
        JPanel pn = new JPanel();
        pn.add(sp);
        pn.setBackground(Color.pink);
        Border bdtb = BorderFactory.createLineBorder(Color.pink);
        TitledBorder tbtb= BorderFactory.createTitledBorder(bdtb, "THÔNG TIN CỦA THÍ SINH VÀ TRƯỜNG");
        tbtb.setTitleFont(new Font("Calibri", Font.BOLD, 15));
        tbtb.setTitleColor(Color.black);
        pn.setBorder(tbtb);
        Display();
// 3
         JPanel pn2 = new JPanel();
         pn.add(returnButton);

//frame
        frame.setVisible(true);
        frame.pack();
        frame.setSize(1200, 490);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container container = frame.getContentPane();
        container.add(panel, BorderLayout.CENTER);
        container.add(pn, BorderLayout.EAST);

//add Action
        themButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectDB condb = new ConnectDB();
                String query = "INSERT INTO truonghoc (mahocsinh, matruong, tentruong) VALUES (?,?,?)";
                conn = condb.connect_SQL();
                try{
                    pst = conn.prepareStatement(query);
                    pst.setString(1,idsttf.getText());
                    pst.setString(2,idschooltf.getText());
                    String s ;
                    s = cbonameschool.getSelectedItem().toString();
                    pst.setString(3,s);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                }
                catch(Exception e1){
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null," Thêm thất bại, vui lòng thử lại");
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
                //  Date date = Date.valueOf(ngaysinh);
                String query = "UPDATE truonghoc SET matruong = ? , tentruong = ? WHERE mahocsinh = ?";
                conn = condb.connect_SQL();
                try{ //:D
                    pst = conn.prepareStatement(query);

                    pst.setString(1,idschooltf.getText());
                    String s ;
                    s = cbonameschool.getSelectedItem().toString();
                    pst.setString(2,s);
                    pst.setString(3,idsttf.getText());

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
                String query = "DELETE FROM truonghoc WHERE mahocsinh = ?";
                conn = condb.connect_SQL();
                try{
                    pst = conn.prepareStatement(query);
                    pst.setString(1,idsttf.getText());
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
                rs = cn.Danhsach("SELECT*FROM truonghoc");
                String[] column = {"mahocsinh","matruong", "tentruong"};
                String name = cbonameschool.getSelectedItem().toString();
                DefaultTableModel tm = new DefaultTableModel(column, 0);
                try {
                    while (rs.next()) {
                        Object data[] = {rs.getString("mahocsinh"), rs.getString("matruong"), rs.getString("tentruong")};
                        tm.addRow(data);
                    }
                    tb.setModel(tm);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new main_gd();
            }
        });

    }

        public void Display(){

            ConnectDB cn = new ConnectDB();
            rs = cn.Danhsach("SELECT*FROM truonghoc");
            String[] column = {"mahocsinh","matruong", "tentruong"};
            String name = cbonameschool.getSelectedItem().toString();
            DefaultTableModel tm = new DefaultTableModel(column, 0);
            try {
                while (rs.next()) {
                    Object data[] = {rs.getString("mahocsinh"), rs.getString("matruong"), rs.getString("tentruong")};
                    tm.addRow(data);
                }
                tb.setModel(tm);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

    }

    public static void main(String[] args) {
        new truonghoc();
    }
}
