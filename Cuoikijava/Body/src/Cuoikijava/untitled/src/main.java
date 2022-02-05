import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

class main extends JFrame {
    JFrame f = new JFrame("Menu");
    JLabel LbHoten = new JLabel("Họ tên");
    JTextField tfHoten = new JTextField(50);
    JLabel lbcmnd = new JLabel("CMND");
    JTextField tfcmnd = new JTextField(20);
    JLabel lbsdt = new JLabel("SDT");
    JTextField tfsdt = new JTextField(10);
    JLabel lbmaphong = new JLabel("Ma Phòng");
    JTextField tfmaphong = new JTextField(10);
    JLabel lbloaiphong = new JLabel("Loại Phòng");
    JTextField tfloaiphong = new JTextField(10);
    JLabel lbgiatien = new JLabel("Giá Tiền");
    JTextField tfgiatien= new JTextField(10);
    JLabel lbtrangthai= new JLabel("Trạng thái");
    JTextField tftrangthai = new JTextField(10);
    JLabel lbngaydat = new JLabel("Ngay Dat");
    JTextField tfngaydat = new JTextField(20);
    JLabel lbngaytra = new JLabel("Ngay Tra");
    JTextField tfngaytra = new JTextField(20);
    
    JButton themButton = new JButton("Thêm");
    JButton btnDoanvien = new JButton("Sửa");
    JButton btnDoanphi = new JButton("xóa");
    JButton lammoiButton = new JButton("RESET");

    JTable tbSearch;

    PreparedStatement ps;
    Connection conn;
    ResultSet rs;

    private void TaoFrame(){
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocation(500,230);
        f.setSize(1000,450);
        f.add(TaoPanel());
        f.setVisible(true);
    }
    private JPanel TaoPanel() {
        JPanel panel = new JPanel(new BorderLayout(11,11));
        panel.add(input(), BorderLayout.NORTH);
        panel.add(tableData(), BorderLayout.CENTER);
        themButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.add(table(), BorderLayout.CENTER);
                panel.revalidate();
                reset();
            }
        });
        panel.add(button(), BorderLayout.SOUTH);
        return panel;
    }
    private JPanel input(){
        JPanel panel = new JPanel(new GridLayout(2,9,2,2));
        panel.add(LbHoten);
        LbHoten.setFont(new java.awt.Font("Segoe UI", 1, 12));
        panel.add(lbcmnd);
        lbcmnd.setFont(new java.awt.Font("Segoe UI", 1, 12));
        panel.add(lbsdt);
        lbsdt.setFont(new java.awt.Font("Segoe UI", 1, 12));
        panel.add(lbmaphong);
        lbmaphong.setFont(new java.awt.Font("Segoe UI", 1, 12));
        panel.add(lbloaiphong);
        lbloaiphong.setFont(new java.awt.Font("Segoe UI", 1, 12));
        panel.add(lbgiatien);
        lbgiatien.setFont(new java.awt.Font("Segoe UI", 1, 12));
        panel.add(lbtrangthai);
        lbtrangthai.setFont(new java.awt.Font("Segoe UI", 1, 12));
        panel.add(themButton);
        themButton.setFont(new java.awt.Font("Segoe UI", 1, 12));
        themButton.setBackground(Color.BLUE);
        themButton.setForeground(Color.WHITE);

        panel.add(tfHoten);
        panel.add(tfcmnd);
        panel.add(tfsdt);
        panel.add(tfmaphong);
        panel.add(tfloaiphong);
        panel.add(tfgiatien);
        panel.add(tftrangthai);
        panel.add(tfngaydat);
        panel.add(tfngaytra);
        return panel;
    }
    private JPanel button() {
        JPanel panel = new JPanel(new GridLayout(1,3,2,2));

        panel.add(btnDoanvien);
        btnDoanvien.setFont(new java.awt.Font("Segoe UI", 1, 12));
        panel.add(btnDoanphi);
        btnDoanphi.setFont(new java.awt.Font("Segoe UI", 1, 12));
        panel.add(lammoiButton);
        lammoiButton.setFont(new java.awt.Font("Segoe UI", 1, 12));

        return  panel;
    }
    private JPanel table() {
        JPanel panel = new JPanel(new GridLayout(1,1,5,5));
        BangTimkiem();
        JScrollPane sp = new JScrollPane(tbSearch);
        panel.add(sp);
        return  panel;
    }
    private JPanel tableData(){
        JPanel panel = new JPanel(new GridLayout(1,1,5,5));
        BangDL();
        JScrollPane sp = new JScrollPane(tbSearch);
        panel.add(sp);
        return  panel;
    }
    public void BangDL() {
        ConnectDB_QLDV con_DV = new ConnectDB_QLDV();
        String col[] = {"Mã ĐV", "Mã SV", "Họ tên","Ngày sinh", "Giới tính","Địa chỉ", "Tên chi đoàn", "Ngành học", "Chức vụ"};
        DefaultTableModel tm1 = new DefaultTableModel(col, 0);

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = con_DV.connect_SQL();
            // tạo đối tượng thực thi câu lệnh SELECT
            st = conn.createStatement();
            // Thực thi
            rs = st.executeQuery("SELECT * FROM DOANVIEN");
            Vector data = null;
            tm1.setRowCount(0);
            // Nếu đoàn viên khong tồn tại
            if (rs.isBeforeFirst() ==  false){
                JOptionPane.showMessageDialog(this, "Đoàn viên không tồn tại");
                return;
            }
            // Trong khi chưa hết dữ liệu
            while (rs.next()){
                data = new Vector();
                data.add(rs.getString("MaDV"));
                data.add(rs.getString("MaSV"));
                data.add(rs.getString("Hoten"));
                data.add(rs.getString("Ngaysinh"));
                data.add(rs.getString("Gioitinh"));
                data.add(rs.getString("Diachi"));
                data.add(rs.getString("Tenchidoan"));
                data.add(rs.getString("Nganhhoc"));
                data.add(rs.getString("Chucvu"));
                // thêm dòng vào table model
                tm1.addRow(data);
            }
            tbSearch = new JTable();
            tbSearch.setModel(tm1); // thêm dữ liệu vào table
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void BangTimkiem()  {
        ConnectDB_QLDV con_DV = new ConnectDB_QLDV();
        String col[] = {"Mã ĐV", "Mã SV", "Họ tên","Ngày sinh", "Giới tính","Địa chỉ", "Tên chi đoàn", "Ngành học", "Chức vụ"};
        DefaultTableModel tm1 = new DefaultTableModel(col, 0);

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = con_DV.connect_SQL();
            // tạo đối tượng thực thi câu lệnh SELECT
            st = conn.createStatement();
            // Thực thi
            rs = st.executeQuery(sql_statement(tfHoten.getText(),tfcmnd.getText(),tfsdt.getText(),tfmaphong.getText(), tfloaiphong.getText(), tfgiatien.getText(), tftrangthai.getText()));
            Vector data = null;
            tm1.setRowCount(0);
            // Nếu đoàn viên khong tồn tại
            if (rs.isBeforeFirst() ==  false){
                JOptionPane.showMessageDialog(this, "Đoàn viên không tồn tại");
                return;
            }
            // Trong khi chưa hết dữ liệu
            while (rs.next()){
                data = new Vector();
                data.add(rs.getString("MaDV"));
                data.add(rs.getString("MaSV"));
                data.add(rs.getString("Hoten"));
                data.add(rs.getString("Ngaysinh"));
                data.add(rs.getString("Gioitinh"));
                data.add(rs.getString("Diachi"));
                data.add(rs.getString("Tenchidoan"));
                data.add(rs.getString("Nganhhoc"));
                data.add(rs.getString("Chucvu"));
                // thêm dòng vào table model
                tm1.addRow(data);
            }
            tbSearch = new JTable();
            tbSearch.setModel(tm1); // thêm dữ liệu vào table
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void reset() {
        tfHoten.setText("");
        tfcmnd.setText("");
        tfsdt.setText("");
        tfmaphong.setText("");
        tfloaiphong.setText("");
        tfgiatien.setText("");
        tftrangthai.setText("");
    }

    public main(){
        TaoFrame();

        lammoiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
    }
    public static void main(String[] args) {
        new main();
    }
}