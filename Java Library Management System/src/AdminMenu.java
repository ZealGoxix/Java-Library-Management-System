import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminMenu extends JFrame {

    public static void adminMenu() {

        JFrame f = new JFrame("Admin Menu");


        JButton view_but = new JButton("View Books");
        view_but.setBounds(20,20,120,25);
        view_but.addActionListener(new ActionListener() {


        public void actionPerformed(ActionEvent e) {
                
            String sql = "SELECT * FROM BOOKS";

            Connection connection = Connect.connect();

            try {
                Statement stmt = connection.createStatement();

                stmt.executeUpdate("USE LIBRARY_DB");
    
                ResultSet rs = stmt.executeQuery(sql);
    
                DefaultTableModel tableModel = new DefaultTableModel();
                JTable table = new JTable(tableModel);



                //get meta data and column count
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                //add column
                for(int i = 1; i < columnCount;i++) {
                    tableModel.addColumn(metaData.getColumnName(i));
                }

                
                while (rs.next()) {
                    Object[] rowData = new Object[columnCount];
                    for(int i = 0; i < columnCount; i++) {
                        rowData[i] = rs.getObject(i + 1); 
                    }
                    tableModel.addRow(rowData);
                }

                JScrollPane pane = new JScrollPane(table);

                f.add(pane); //add scroll bar
                f.setSize(800, 400); //set dimensions of view books frame
                f.setVisible(true);
                f.setLocationRelativeTo(null);

            } catch (SQLException a) {
                a.printStackTrace();

            }

            }
        });

        f.add(view_but); 
        f.setSize(300,100);
        f.setLayout(null); 
        f.setVisible(true);
        f.setLocationRelativeTo(null);

    }
}
