import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;



public class UserMenu extends JFrame {

    public static void user_menu(String UID) {

        JFrame f = new JFrame("User Menu");
        //System.out.println("User Menu JFrame created.");

        JButton view_but = new JButton("View Books");
        view_but.setBounds(20,20,120,25);
        view_but.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e){

            JFrame f = new JFrame("Books Available");
            Connection connection = Connect.connect();
            String sql="select * from BOOKS";

            try {

                Statement stmt = connection.createStatement();

                stmt.executeUpdate("USE LIBRARY_DB");
                ResultSet rs = stmt.executeQuery(sql);
                
                DefaultTableModel tableModel = new DefaultTableModel();
                JTable table = new JTable(tableModel);

                //get meta data and column count
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                //add the columns
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
            

                JScrollPane scrollPane = new JScrollPane(table);
                //add everything

                f.add(scrollPane); //add scroll bar
                f.setSize(800, 400); //set dimensions of view books frame
                f.setVisible(true);
                f.setLocationRelativeTo(null);

            } catch (Exception ex) {
                ex.printStackTrace();

            }
            }
        });

        JButton my_books = new JButton("My Books");
        my_books.setBounds(150,20,120,25);
        my_books.addActionListener(new ActionListener () {

            public void actionPerformed(ActionEvent e) {

                JFrame f = new JFrame("Books Available");
                int UID_int = Integer.parseInt(UID);
                Connection connection = Connect.connect();
                
                String sql="select distinct issued.*,books.bname,books.genre,books.price from issued,books " + "where ((issued.uid=" + UID_int + ") and (books.bid in (select bid from issued where issued.uid="+UID_int+"))) group by iid";
            
                try {
                    
                Statement stmt = connection.createStatement();
                //use the database
                stmt.executeUpdate("USE library_db");

                ResultSet rs = stmt.executeQuery(sql);

                DefaultTableModel tableModel = new DefaultTableModel();
                JTable table = new JTable(tableModel);

                //get meta data and column count
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                //add the columns
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
                
                JScrollPane scrollPane = new JScrollPane(table);
                //add everything
                
                
                f.add(scrollPane); //add scroll bar
                f.setSize(800, 400); //set dimensions of view books frame
                f.setVisible(true);
                f.setLocationRelativeTo(null);

                } catch (Exception exc) {
                    exc.printStackTrace();
                }



            }


        });

        
        f.add(my_books);
        f.add(view_but); // add view books
        f.setSize(300,100);//400 width and 500 height  
        f.setLayout(null);//using no layout managers  
        f.setVisible(true);
        f.setLocationRelativeTo(null);


    }
}
