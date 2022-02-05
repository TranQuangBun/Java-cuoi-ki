import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

class search_ts extends JFrame {

    JFrame f = new JFrame("TÌM KIẾM THÍ SINH");

    JLabel idstudent = new JLabel("Mã học sinh");
    JLabel name = new JLabel("Họ tên");
    JLabel gender = new JLabel("Giới tính");
    JLabel birthday = new JLabel("Ngày sinh");
    JLabel toan = new JLabel("Điểm toán");
    JLabel van = new JLabel("Điểm văn");
    JLabel anh = new JLabel("Điểm anh");

    JTextField idstudenttf = new JTextField(10);
    JTextField nametf = new JTextField(10);
    JTextField gendertf = new JTextField(10);
    JTextField birthdaytf = new JTextField(10);
    JTextField toantf = new JTextField(10);
    JTextField vantf = new JTextField(10);
    JTextField anhtf = new JTextField(10);
    JTable tableSearch;
    JButton searchButton = new JButton("TÌM KIẾM");
    JButton returnButton = new JButton("QUAY LẠI");

    PreparedStatement ps;
    Connection conn;
    ResultSet rs;

    private void TaoFrame(){
        f.setSize(1200,500);
        f.add(TaoPanel());
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private JPanel TaoPanel() {
        JPanel panel = new JPanel(new BorderLayout(7,7));
        panel.add(input(), BorderLayout.NORTH);
        panel.add(tbdate(), BorderLayout.CENTER);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.add(table(), BorderLayout.CENTER);
                panel.revalidate();
                reset();
            }
        });
        panel.add(button(), BorderLayout.SOUTH);
        panel.setBackground(Color.pink);
        return panel;
    }
    private JPanel input(){
        JPanel panel = new JPanel(new GridLayout(2,7,5 ,5));
        panel.add(idstudent);
        panel.add(name);
        panel.add(gender);
        panel.add(birthday);
        panel.add(toan);
        panel.add(van);
        panel.add(anh);
        panel.setBackground(Color.pink);

        idstudent.setFont(new Font("Calibri", Font.BOLD, 15));
        name.setFont(new Font("Calibri", Font.BOLD, 15));
        gender.setFont(new Font("Calibri", Font.BOLD, 15));
        birthday.setFont(new Font("Calibri", Font.BOLD, 15));
        toan.setFont(new Font("Calibri", Font.BOLD, 15));
        van.setFont(new Font("Calibri", Font.BOLD, 15));
        anh.setFont(new Font("Calibri", Font.BOLD, 15));

        panel.add(idstudenttf);
        panel.add(nametf);
        panel.add(gendertf);
        panel.add(birthdaytf);
        panel.add(toantf);
        panel.add(vantf);
        panel.add(anhtf);

        return panel;
    }
    private JPanel button() {
        JPanel panel = new JPanel(new GridLayout(1,1));
        panel.add(searchButton);
        searchButton.setFont(new Font("Calibri", Font.BOLD, 15));
        searchButton.setBackground(Color.BLUE);
        searchButton.setForeground(Color.WHITE);
        //return button
        panel.add(returnButton);
        returnButton.setFont(new Font("Calibri", Font.BOLD, 15));
        returnButton.setBackground(Color.BLUE);
        returnButton.setForeground(Color.WHITE);
        return  panel;
    }
    private JPanel table() {
        JPanel panel = new JPanel(new GridLayout(1,1));
        BangTimkiem();
        JScrollPane sp = new JScrollPane(tableSearch);
        panel.add(sp);
        return  panel;
    }
    private JPanel tbdate(){
        JPanel panel = new JPanel(new GridLayout(1,1));
        BangDL();
        JScrollPane sp = new JScrollPane(tableSearch);
        panel.add(sp);
        return  panel;
    }
    public void BangDL() {
        ConnectDB connectdb = new ConnectDB();
        String[] column = {"mahocsinh","hoten", "gioitinh", "ngaysinh", "toan", "van", "anh"};
        DefaultTableModel tm1 = new DefaultTableModel(column, 0);

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = connectdb.connect_SQL();
            // tạo đối tượng thực thi câu lệnh SELECT
            st = conn.createStatement();
            // Thực thi
            rs = st.executeQuery("SELECT * FROM thisinh");
            Vector vtor = null;
            tm1.setRowCount(0);
            if (rs.isBeforeFirst() ==  false){
                JOptionPane.showMessageDialog(this, "Không có thí sinh đó");
                return;}
            // Trong khi chưa hết dữ liệu
            while (rs.next()){
                vtor = new Vector();
                vtor.add(rs.getString("mahocsinh"));
                vtor.add(rs.getString("hoten"));
                vtor.add(rs.getString("gioitinh"));
                vtor.add(rs.getString("ngaysinh"));
                vtor.add(rs.getString("toan"));
                vtor.add(rs.getString("van"));
                vtor.add(rs.getString("anh"));
                // thêm dòng vào table model
                tm1.addRow(vtor);
            }
            tableSearch = new JTable();
            tableSearch.setModel(tm1); // thêm dữ liệu vào table
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void BangTimkiem()  {
        ConnectDB connectdb = new ConnectDB();
        String[] column = {"mahocsinh","hoten", "gioitinh", "ngaysinh", "toan", "van", "anh"};
        DefaultTableModel tm1 = new DefaultTableModel(column, 0);

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = connectdb.connect_SQL();

            st = conn.createStatement();
            rs = st.executeQuery(sql_statement(idstudenttf.getText(),nametf.getText(),gendertf.getText(),birthdaytf.getText(), toantf.getText(), vantf.getText(), anhtf.getText()));
            Vector vtor = null;
            tm1.setRowCount(0);
            if (rs.isBeforeFirst() ==  false){
                JOptionPane.showMessageDialog(this, "Không có thí sinh đó");
                return;
            }

            while (rs.next()){
                vtor = new Vector();
                vtor.add(rs.getString("mahocsinh"));
                vtor.add(rs.getString("hoten"));
                vtor.add(rs.getString("gioitinh"));
                vtor.add(rs.getString("ngaysinh"));
                vtor.add(rs.getString("toan"));
                vtor.add(rs.getString("van"));
                vtor.add(rs.getString("anh"));
                tm1.addRow(vtor);
            }
            tableSearch = new JTable();
            tableSearch.setModel(tm1);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void reset() {
        idstudenttf.setText("");
        nametf.setText("");
        gendertf.setText("");
        birthdaytf.setText("");
        toantf.setText("");
        vantf.setText("");
        anhtf.setText("");
    }
    public String sql_statement(String mahocsinh, String hoten, String gioitinh, String ngaysinh, String toan, String van, String anh ) {
        // câu lệnh xem dữ liệu
        String sql = "SELECT * FROM thisinh WHERE ";

        if (mahocsinh.length()>0){
            sql += (String)("mahocsinh like '%"+mahocsinh+"%'");
        }
        if (hoten.length()>0){
            if (mahocsinh.length()>0) sql += " AND";
            sql += (String)(" hoten like '%"+hoten+"%'");
        }
        if (gioitinh.length()>0){
            if (mahocsinh.length()>0 || hoten.length()>0) sql += " AND";
            sql += (String)(" Gioitinh like '%"+gioitinh+"%'");
        }
        if (ngaysinh.length()>0){
            if (mahocsinh.length()>0 || hoten.length()>0 || gioitinh.length()>0) sql += " AND";
            sql += (String)(" ngaysinh like '%"+ngaysinh+"%'");
        }
        if (toan.length()>0){
            if (mahocsinh.length()>0 || hoten.length()>0 || gioitinh.length()>0 || ngaysinh.length()>0 ) sql += " AND";
            sql += (String)(" toan like '%"+toan+"%'");
        }
        if (van.length()>0){
            if (mahocsinh.length()>0 || hoten.length()>0 || gioitinh.length()>0 || ngaysinh.length()>0 || toan.length()>0) sql += " AND";
            sql += (String)(" van like '%"+van+"%'");
        }
        if (anh.length()>0){
            if (mahocsinh.length()>0 || hoten.length()>0 || gioitinh.length()>0 || ngaysinh.length()>0 || toan.length()>0 || van.length()>0) sql += " AND";
            sql += (String)(" anh like '%"+anh+"%'");
        }
        return sql;
    }
    public search_ts(){
        TaoFrame();
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new main_gd();
                f.setVisible(false);
            }
        });
    }
    public static void main(String[] args) {
        new search_ts();
    }
}