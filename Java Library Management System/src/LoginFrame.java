import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;



public class LoginFrame extends JFrame{

    
    public LoginFrame() {
        JLabel l1,l2;  
        l1=new JLabel("Username");  //Create label Username
        l1.setBounds(30,15, 100,30); //x axis, y axis, width, height 
         
        l2=new JLabel("Password");  //Create label Password
        l2.setBounds(30,50, 100,30);    

        JTextField l1Field = new JTextField(10);
        l1Field.setBounds(110, 15, 200, 30);
        JPasswordField l2Field = new JPasswordField(10);
        l2Field.setBounds(110, 50, 200, 30);



        JButton login_but = new JButton("Login");//creating instance of JButton for Login Button
        login_but.setBounds(130,90,80,25);
        login_but.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String username = l1Field.getText();
                String password = new String(l2Field.getPassword());
                // we want to check if it empty
                //then check if its in database
                if(username == "") {
                    JOptionPane.showMessageDialog(null, "Username is Empty.");
                } else if (password == "") {
                    JOptionPane.showMessageDialog(null, "Password is Empty");
                } else {
                    Connection connection = Connect.connect();
                    try {
                        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        stmt.executeUpdate("USE LIBRARY_DB");
                        String st = ("SELECT * FROM USERS WHERE USERNAME='"+username+"' AND PASSWORD='"+password+"'");
                        ResultSet rs = stmt.executeQuery(st);
                        
                        if (rs.next() == false) {
                            System.out.println("No User.");
                            JOptionPane.showMessageDialog(null,"Wrong Username/Password.");
                            
                        } else {
                            dispose();
                            rs.beforeFirst();


                            while(rs.next()) {
                                String role = rs.getString("role");
                                String UID = rs.getString("uid");


                                
                                if (role.equals("admin")) {
                                    //admin_menu();
                                    System.out.println("Logging in as admin.");
                                    UserMenu.user_menu(UID);
                                } else {
                                    //user_menu(UID);
                                    UserMenu.user_menu(UID);
                                }

                            }

                        }



                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
                
            }

        }

        );

    
        add(l2Field);
        add(login_but);
        add(l1Field);
        add(l1);
        add(l2);

        setTitle("Login");     
        setSize(400,180);//400 width and 500 height  
        setLayout(null);//using no layout managers  
        setVisible(true);//making the frame visible 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

}
    
