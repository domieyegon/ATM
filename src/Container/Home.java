/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Container;


import dbconnector.JavaConnect;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.swing.JRDesignViewer;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author DOMINIC YEGON
 */
public class Home extends javax.swing.JFrame {

 public JTextField txthide=new JTextField();
   Connection conn=null;
   PreparedStatement pst=null;
   ResultSet rs=null;
    public Home() {
        super("Home"); 
        initComponents();
        conn=JavaConnect.ConnectDB();
        FormLoadEvs();  
        ClockPane();
    }
    
    
    //What to do on formload
    private void FormLoadEvs(){
          //lblpicmsg.setVisible(false);
          txttest.setVisible(false);
          profilepanel.setVisible(true);
          depositpanel.setVisible(false);
          transferpanel.setVisible(false);
          checkbalancepanel.setVisible(false);
          transactionpanel.setVisible(false);
          widthrawpanel.setVisible(false);
          txttest2.setVisible(false);
          txtdepam.setVisible(false);
          txtwitam.setVisible(false);
          txtalltotal.setVisible(false);
          txttest3.setVisible(false);
          txttam.setVisible(false);
          txtram.setVisible(false);
    }

    //link to the login form
    public void inputth(){
        txtacc.setText(txthide.getText());
         MyProfile();
         user.setText("Welcome "+txtname.getText());
         amountinacc();
    }
    
    private void Balance(){
        lblbalance.setText(txtalltotal.getText()+".00");
    }
    
    //setting date and time 
    private void ClockPane(){
        Timer timer=new Timer(500, (ActionEvent e) -> {
            tickTock();
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
    }
    private void tickTock(){
         clock.setText(DateFormat.getDateTimeInstance().format(new Date()));
    }
   
    
    private void amountinacc(){
        try {
            String sql="select sum(amount), sum(withdraw), sum(transfer) from deposit where accountno='"+txtacc.getText()+"' group by accountno";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                 String add1=rs.getString("sum(amount)");
                txtdepam.setText(add1);
                String add2=rs.getString("sum(withdraw)");
                txtwitam.setText(add2);
               String add4=rs.getString("sum(transfer)");
                txttam.setText(add4);
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }MoneyRecieved();
        Calculation();
        Balance();
    }
    private void MoneyRecieved(){
     try {
         String sql="select sum(transfer) from deposit where recipientaccno='"+txtacc.getText()+"' group by recipientaccno";
         pst=conn.prepareStatement(sql);
         rs=pst.executeQuery();
         if(rs.next()){
             String add3=rs.getString("sum(transfer)");
             txtram.setText(add3);
     }
     } catch (SQLException e) {
         System.out.println(e);
     }
    }
    private void Calculation(){     
       
        
        int firstnum= Integer.parseInt("0"+txtdepam.getText());
        int secondnum=Integer.parseInt("0"+txtwitam.getText());
        int thirdnum= Integer.parseInt("0"+txtram.getText());
        int forthnum=Integer.parseInt("0"+txttam.getText());
       int result=(firstnum+thirdnum)-(secondnum+forthnum);
        String Total= String.valueOf(result);
            txtalltotal.setText(Total);
            txtincount.setText(txtalltotal.getText());
            txttotal.setText(txtalltotal.getText());
            txtwinaccount.setText(txtalltotal.getText());
            txtwbalance.setText(txtalltotal.getText());
            txttinaccount.setText(txtalltotal.getText());
            txttbalance.setText(txtalltotal.getText());
    }
    
    private void MyProfile(){
        String sql="select * from account Where accno='"+txtacc.getText()+"'";
        try {
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1=rs.getString("name");
                txtname.setText(add1);
                String add2=rs.getString("nationalid");
                txtid.setText(add2);
                String add3=rs.getString("gender");
                txtgender.setText(add3);
                String add4=rs.getString("mobile");
                txtmobile.setText(add4);
                
                 byte[] imagedata=rs.getBytes("img");
                 ImageIcon image=new ImageIcon(new ImageIcon(imagedata).getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(), lblimg.getWidth()));
                 lblimg.setIcon(image);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        clock = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        profilepanel = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtacc = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtgender = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtmobile = new javax.swing.JTextField();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        lblimg = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        user = new javax.swing.JLabel();
        depositpanel = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        txtdeposit = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtincount = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        txttest = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        transferpanel = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        txtracc = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txttbalance = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txttinaccount = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtrname = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txttamount = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        txttest3 = new javax.swing.JTextField();
        txttam = new javax.swing.JTextField();
        txtram = new javax.swing.JTextField();
        checkbalancepanel = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        lblbalance = new javax.swing.JLabel();
        widthrawpanel = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtwbalance = new javax.swing.JTextField();
        txtwinaccount = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        txtwamount = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtalltotal = new javax.swing.JTextField();
        txtdepam = new javax.swing.JTextField();
        txtwitam = new javax.swing.JTextField();
        txttest2 = new javax.swing.JTextField();
        transactionpanel = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 255, 0));
        jLabel1.setText("MADOW ATM");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 0));
        jLabel2.setText("Easy and safe way for keeping and accessing your money");

        clock.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(382, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clock, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(clock, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel3MouseExited(evt);
            }
        });
        jPanel3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel3KeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("MyProfile ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap())
        );

        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel4MouseExited(evt);
            }
        });
        jPanel4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel4KeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Deposit ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap())
        );

        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel5MouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Widthdraw ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap())
        );

        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel6MouseExited(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Transfer ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap())
        );

        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel7MouseExited(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Check Balance ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap())
        );

        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel8MouseExited(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("View Transactions");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        profilepanel.setBackground(new java.awt.Color(255, 255, 255));
        profilepanel.setMaximumSize(new java.awt.Dimension(688, 354));
        profilepanel.setMinimumSize(new java.awt.Dimension(688, 354));
        profilepanel.setPreferredSize(new java.awt.Dimension(688, 354));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("National ID :");

        txtacc.setEditable(false);
        txtacc.setBackground(new java.awt.Color(255, 255, 255));
        txtacc.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Name :");

        txtname.setEditable(false);
        txtname.setBackground(new java.awt.Color(255, 255, 255));
        txtname.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Account NO :");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Gender :");

        txtid.setEditable(false);
        txtid.setBackground(new java.awt.Color(255, 255, 255));
        txtid.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtgender.setEditable(false);
        txtgender.setBackground(new java.awt.Color(255, 255, 255));
        txtgender.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Phone NO :");

        txtmobile.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtmobile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmobileKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtgender, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtacc, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtmobile, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtacc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtgender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtmobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblimg.setForeground(new java.awt.Color(255, 255, 255));

        jDesktopPane1.setLayer(lblimg, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblimg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblimg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setText("Choose Your Profile Picture");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton2.setText("Update Profile");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        user.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout profilepanelLayout = new javax.swing.GroupLayout(profilepanel);
        profilepanel.setLayout(profilepanelLayout);
        profilepanelLayout.setHorizontalGroup(
            profilepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilepanelLayout.createSequentialGroup()
                .addGroup(profilepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(profilepanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(profilepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(profilepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDesktopPane1)))
                    .addGroup(profilepanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(user)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        profilepanelLayout.setVerticalGroup(
            profilepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(profilepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDesktopPane1))
                .addGap(18, 18, 18)
                .addGroup(profilepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(52, 52, 52))
        );

        depositpanel.setBackground(new java.awt.Color(255, 255, 255));
        depositpanel.setMaximumSize(new java.awt.Dimension(688, 354));
        depositpanel.setMinimumSize(new java.awt.Dimension(688, 354));
        depositpanel.setPreferredSize(new java.awt.Dimension(688, 354));

        txtdeposit.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtdeposit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdepositKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdepositKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdepositKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("Amount in Account :");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setText("Amount to Deposit :");

        txtincount.setEditable(false);
        txtincount.setBackground(new java.awt.Color(255, 255, 255));
        txtincount.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setText("Total Amount :");

        txttotal.setEditable(false);
        txttotal.setBackground(new java.awt.Color(255, 255, 255));
        txttotal.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setText("Deposit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtdeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtincount, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(txttest, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdeposit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtincount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(txttest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel17.setText("Deposit Money");

        javax.swing.GroupLayout depositpanelLayout = new javax.swing.GroupLayout(depositpanel);
        depositpanel.setLayout(depositpanelLayout);
        depositpanelLayout.setHorizontalGroup(
            depositpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(depositpanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(depositpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(188, Short.MAX_VALUE))
        );
        depositpanelLayout.setVerticalGroup(
            depositpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(depositpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        transferpanel.setBackground(new java.awt.Color(255, 255, 255));
        transferpanel.setMaximumSize(new java.awt.Dimension(688, 354));
        transferpanel.setMinimumSize(new java.awt.Dimension(688, 354));
        transferpanel.setPreferredSize(new java.awt.Dimension(688, 354));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel22.setText("Transfer Money");

        txtracc.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtracc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtraccFocusLost(evt);
            }
        });
        txtracc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtraccKeyReleased(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel23.setText("Reciever's name :");

        txttbalance.setEditable(false);
        txttbalance.setBackground(new java.awt.Color(255, 255, 255));
        txttbalance.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txttbalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttbalanceActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel24.setText("Amount to Transfer :");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel27.setText("Balance :");

        txttinaccount.setEditable(false);
        txttinaccount.setBackground(new java.awt.Color(255, 255, 255));
        txttinaccount.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel25.setText("Reciever's Acc No :");

        txtrname.setEditable(false);
        txtrname.setBackground(new java.awt.Color(255, 255, 255));
        txtrname.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel26.setText("Amount in Acc :");

        txttamount.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txttamount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttamountKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttamountKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttamountKeyTyped(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton5.setText("Transfer");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttamount, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttbalance, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttinaccount, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtrname, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtracc, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txttamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtracc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtrname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txttinaccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txttbalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout transferpanelLayout = new javax.swing.GroupLayout(transferpanel);
        transferpanel.setLayout(transferpanelLayout);
        transferpanelLayout.setHorizontalGroup(
            transferpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transferpanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(transferpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transferpanelLayout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addGroup(transferpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txttest3)
                            .addComponent(txttam)
                            .addComponent(txtram, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)))
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        transferpanelLayout.setVerticalGroup(
            transferpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transferpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(transferpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transferpanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(transferpanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(txttest3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(txttam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txtram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        checkbalancepanel.setBackground(new java.awt.Color(255, 255, 255));
        checkbalancepanel.setToolTipText("");
        checkbalancepanel.setMaximumSize(new java.awt.Dimension(688, 354));
        checkbalancepanel.setMinimumSize(new java.awt.Dimension(688, 354));
        checkbalancepanel.setPreferredSize(new java.awt.Dimension(688, 354));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel28.setText("You have a balance of ksh ");

        lblbalance.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N

        javax.swing.GroupLayout checkbalancepanelLayout = new javax.swing.GroupLayout(checkbalancepanel);
        checkbalancepanel.setLayout(checkbalancepanelLayout);
        checkbalancepanelLayout.setHorizontalGroup(
            checkbalancepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkbalancepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblbalance, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                .addContainerGap())
        );
        checkbalancepanelLayout.setVerticalGroup(
            checkbalancepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkbalancepanelLayout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addGroup(checkbalancepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblbalance, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addContainerGap(181, Short.MAX_VALUE))
        );

        widthrawpanel.setBackground(new java.awt.Color(255, 255, 255));
        widthrawpanel.setMaximumSize(new java.awt.Dimension(688, 354));
        widthrawpanel.setMinimumSize(new java.awt.Dimension(688, 354));
        widthrawpanel.setPreferredSize(new java.awt.Dimension(688, 354));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel20.setText("Balance :");

        txtwbalance.setEditable(false);
        txtwbalance.setBackground(new java.awt.Color(255, 255, 255));
        txtwbalance.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txtwinaccount.setEditable(false);
        txtwinaccount.setBackground(new java.awt.Color(255, 255, 255));
        txtwinaccount.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton4.setText("Withdraw");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtwamount.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtwamount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtwamountKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtwamountKeyTyped(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel18.setText("Amount in Account :");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setText("Amount to Withdraw :");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtwinaccount, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtwbalance, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtwamount, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtwamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtwinaccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtwbalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap())
        );

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel21.setText("Withdraw Money");

        javax.swing.GroupLayout widthrawpanelLayout = new javax.swing.GroupLayout(widthrawpanel);
        widthrawpanel.setLayout(widthrawpanelLayout);
        widthrawpanelLayout.setHorizontalGroup(
            widthrawpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(widthrawpanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(widthrawpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(widthrawpanelLayout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(widthrawpanelLayout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(txttest2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, widthrawpanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtdepam, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(txtwitam, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(txtalltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(254, 254, 254))
        );
        widthrawpanelLayout.setVerticalGroup(
            widthrawpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(widthrawpanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(widthrawpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(widthrawpanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(widthrawpanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(txttest2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(widthrawpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtalltotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtwitam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdepam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        transactionpanel.setBackground(new java.awt.Color(255, 255, 255));
        transactionpanel.setMaximumSize(new java.awt.Dimension(688, 354));
        transactionpanel.setMinimumSize(new java.awt.Dimension(688, 354));
        transactionpanel.setPreferredSize(new java.awt.Dimension(688, 354));

        javax.swing.GroupLayout transactionpanelLayout = new javax.swing.GroupLayout(transactionpanel);
        transactionpanel.setLayout(transactionpanelLayout);
        transactionpanelLayout.setHorizontalGroup(
            transactionpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 708, Short.MAX_VALUE)
        );
        transactionpanelLayout.setVerticalGroup(
            transactionpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
        );

        jLayeredPane1.setLayer(profilepanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(depositpanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(transferpanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(checkbalancepanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(widthrawpanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(transactionpanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(profilepanel, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                .addGap(0, 0, 0))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(depositpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(transferpanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(checkbalancepanel, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(widthrawpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(transactionpanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(profilepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(depositpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addComponent(transferpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addComponent(checkbalancepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 17, Short.MAX_VALUE)))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addComponent(widthrawpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 17, Short.MAX_VALUE)))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addComponent(transactionpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 12, Short.MAX_VALUE)))
        );

        jLabel29.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel29.setText("Developer : Dominic Yegon");

        jLabel30.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel30.setText("@Copyright 2018");

        jLabel31.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel31.setText("domieyegon70@gmail.com");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("jMenu3");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLayeredPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(234, 234, 234)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
     jPanel3.setBackground(Color.gray);
    }//GEN-LAST:event_jPanel3MouseEntered

    private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
      jPanel4.setBackground(Color.gray);
    }//GEN-LAST:event_jPanel4MouseEntered

    private void jPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseEntered
        jPanel5.setBackground(Color.gray);
    }//GEN-LAST:event_jPanel5MouseEntered

    private void jPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseEntered
        jPanel6.setBackground(Color.gray);
    }//GEN-LAST:event_jPanel6MouseEntered

    private void jPanel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseEntered
       jPanel7.setBackground(Color.gray);
    }//GEN-LAST:event_jPanel7MouseEntered

    private void jPanel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseExited
        jPanel3.setBackground(Color.lightGray);
    }//GEN-LAST:event_jPanel3MouseExited

    private void jPanel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseExited
       jPanel4.setBackground(Color.lightGray);
    }//GEN-LAST:event_jPanel4MouseExited

    private void jPanel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseExited
        jPanel5.setBackground(Color.lightGray);
    }//GEN-LAST:event_jPanel5MouseExited

    private void jPanel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseExited
        jPanel6.setBackground(Color.lightGray);
    }//GEN-LAST:event_jPanel6MouseExited

    private void jPanel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseExited
        jPanel7.setBackground(Color.lightGray);
    }//GEN-LAST:event_jPanel7MouseExited

    private void jPanel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseEntered
       jPanel8.setBackground(Color.gray);
    }//GEN-LAST:event_jPanel8MouseEntered

    private void jPanel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseExited
        jPanel8.setBackground(Color.lightGray);
    }//GEN-LAST:event_jPanel8MouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       // lblimg.setIcon(null);
        
        JFileChooser chooser=new JFileChooser();
          chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
          chooser.setDialogTitle("Select your Image");
          chooser.setFileFilter(new FileNameExtensionFilter("png|jpg|bmp","png","jpg","bmp"));
          if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
        
        File f=chooser.getSelectedFile();
        filename=f.getAbsolutePath();
     

        ImageIcon image=new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance( lblimg.getWidth(),  lblimg.getHeight(),  lblimg.getWidth()));
        lblimg.setIcon(image);
    }
          
          //converting image to bytes
          try{
            File img=new File(filename);
           fis=new FileInputStream(img);
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            byte[] buf=new byte[1024];
            for(int readnum; (readnum=fis.read(buf)) !=-1;){
                bos.write(buf,0,readnum);
            }
           personimage=bos.toByteArray();
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null, e);
        }  
         
          try {
                  String value2= txtid.getText();
                  String sql="update account set img=? where nationalid='"+value2+"'";
                  pst=conn.prepareStatement(sql);
                  pst.setBytes(1, personimage);
                  pst.executeUpdate();
            } catch (SQLException ex) {
            try {
                JOptionPane.showMessageDialog(null, "This Picture is too large Choose Another!","Error", JOptionPane.ERROR_MESSAGE);
                if(rs.next()){
                String sql="select * from account Where accno='"+txtacc.getText()+"'";
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                while(rs.next()){
                    byte[] imagedata=rs.getBytes("img");
                    ImageIcon image=new ImageIcon(new ImageIcon(imagedata).getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(), lblimg.getWidth()));
                    lblimg.setIcon(image);
                } 
                }else{
                    lblimg.setIcon(null);
                }
            } catch (SQLException ex1) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex1);
            }
            }    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

       
            String value1= txtmobile.getText();
            String value2= txtid.getText();
            
            if(txtmobile.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please enter your Phone Number to complete you profile", "Error", JOptionPane.ERROR_MESSAGE);
            }else if(txtmobile.getText().length()>10){
                JOptionPane.showMessageDialog(null, "This is not a correct Phone Number Length!","Error",JOptionPane.ERROR_MESSAGE);
                txtmobile.setText("");
            }else{
            try {
              
            String sql="update account set mobile='"+value1+"' Where nationalid='"+value2+"'";
            pst=conn.prepareStatement(sql);
           pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated");
            } catch (SQLException ex) {
                System.out.println(ex);
            }  
            }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPanel3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel3KeyPressed
      
    }//GEN-LAST:event_jPanel3KeyPressed

    private void jPanel4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel4KeyPressed
       
    }//GEN-LAST:event_jPanel4KeyPressed

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
       profilepanel.setVisible(false);
       depositpanel.setVisible(true);
       transferpanel.setVisible(false);
       checkbalancepanel.setVisible(false);
       widthrawpanel.setVisible(false);
       transactionpanel.setVisible(false);
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        profilepanel.setVisible(true);
       depositpanel.setVisible(false);
       transferpanel.setVisible(false);
       checkbalancepanel.setVisible(false);
       widthrawpanel.setVisible(false);
       transactionpanel.setVisible(false);
    }//GEN-LAST:event_jPanel3MouseClicked

    private void txtdepositKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdepositKeyTyped
      char c=evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();  
      }
    }//GEN-LAST:event_txtdepositKeyTyped

    private void txtdepositKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdepositKeyPressed
        
    }//GEN-LAST:event_txtdepositKeyPressed

    private void txtdepositKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdepositKeyReleased
        
        txttest.setText("0"+txtdeposit.getText());  
        int firstnum= Integer.parseInt(txttest.getText());
        int secondnum=Integer.parseInt(txtincount.getText());
       int result=firstnum+secondnum;
        String Total= String.valueOf(result);
            txttotal.setText(Total); 
            if(Integer.parseInt(txttest.getText())>1000000){
            JOptionPane.showMessageDialog(null, "You Can not Deposit more than 1,000,000!");
            txtdeposit.setText(""); 
            txttotal.setText(txtincount.getText());
        }
    }//GEN-LAST:event_txtdepositKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       if(txtdeposit.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Fill in the Amount to Deposit","Error",JOptionPane.ERROR_MESSAGE);
       }else{
           try {
               String sql="insert into deposit(amount,total,accountno,savedate)values(?,?,?,?)";
               pst=conn.prepareStatement(sql);
               pst.setString(1, txtdeposit.getText());
               pst.setString(2, txttotal.getText());
               pst.setString(3, txtacc.getText());
               pst.setString(4, clock.getText());
                int result=JOptionPane.showConfirmDialog(null, "Deposit Ksh "+txtdeposit.getText()+"?","Take Action", JOptionPane.YES_NO_OPTION);
                if(result==JOptionPane.YES_OPTION){
                    pst.execute();
                }else{
                    txtdeposit.setText("");
                }
           } catch (SQLException | HeadlessException e) {
               System.out.println(e);
           }amountinacc();
           txtdeposit.setText("");
       }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
         profilepanel.setVisible(false);
        depositpanel.setVisible(false);
        transferpanel.setVisible(true);
        checkbalancepanel.setVisible(false);
          widthrawpanel.setVisible(false);
          transactionpanel.setVisible(false);
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
         profilepanel.setVisible(false);
         depositpanel.setVisible(false);
         transferpanel.setVisible(false);
         widthrawpanel.setVisible(true);
         transactionpanel.setVisible(false);
         checkbalancepanel.setVisible(false);
         
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
         profilepanel.setVisible(false);
         depositpanel.setVisible(false);
         transferpanel.setVisible(false);
         checkbalancepanel.setVisible(true);
         widthrawpanel.setVisible(false);
         transactionpanel.setVisible(false);
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
         profilepanel.setVisible(false);
         depositpanel.setVisible(false);
         transferpanel.setVisible(false);
         checkbalancepanel.setVisible(false);
         widthrawpanel.setVisible(false);
         transactionpanel.setVisible(true);
       
         
         //to display Transactions
          JasperReportBuilder report = DynamicReports. report () ; // a new report
       
        report. columns (
        Columns . column( "Date" , "savedate" , DataTypes . stringType ( ) ) ,
        Columns . column( "Deposited" , "amount" , DataTypes . stringType ( ) ) ,
        Columns . column( "Withdrawn" , "withdraw" , DataTypes . stringType ( ) ),
        Columns . column( "Transfered" , "transfer" , DataTypes . stringType ( ) ) ,
        Columns . column( "Recipient" , "recipientname" , DataTypes . stringType ( ) ) ,
        Columns . column( "Balance" , "total" , DataTypes . stringType ( ) )
        )//.columnFooter(components)
        . title ( // title of the report
                Components. text( "NAME :          "+txtname.getText() ),
                Components. text( "ACCOUNT NUMBER :"+txtacc.getText() ),
                Components. text( "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------" )
        . setHorizontalAlignment ( HorizontalAlignment. LEFT),
          Components. text( "My Transaction History" )
        . setHorizontalAlignment ( HorizontalAlignment. CENTER) )
          .title(Components.text("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------"))
        . pageFooter ( Components. pageXofY ( ) ) // show page number on the page footer
        . setDataSource ( "SELECT savedate, amount,withdraw,transfer,recipientname,total,total FROM deposit WHERE accountno='"+txtacc.getText()+"' ORDER BY id  DESC" ,
        conn )
         ;
        try {
            
        // show the report
        //report.setPageFormat(901, 531, PageOrientation.PORTRAIT);
        
        report . show() ;
        
        // export the report to a pdf file
       // report . toPdf ( new FileOutputStream ( "C:/Users/DOMINIC YEGON/Documents/report.pdf" ) ) ;
        } catch ( DRException e ) {
        System.out.println(e);
        }
         
    }//GEN-LAST:event_jPanel8MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        
        try {
            String sql="insert into deposit(withdraw,total,accountno,savedate)values(?,?,?,?)";
               pst=conn.prepareStatement(sql);
               pst.setString(1, txtwamount.getText());
               pst.setString(2, txtwbalance.getText());
               pst.setString(3, txtacc.getText());
               pst.setString(4, clock.getText());
               int dialog= JOptionPane.showConfirmDialog(null, "Withdraw Ksh "+txtwamount.getText()+"?","Take Action", JOptionPane.YES_NO_OPTION);
               if(dialog==JOptionPane.YES_OPTION){
               pst.execute();
               }else{
                  txtwamount.setText("");
               }
        } catch (SQLException | HeadlessException e) {
            System.out.println(e);
        }amountinacc();
        txtwamount.setText("");
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtwamountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtwamountKeyReleased
               txttest2.setText("0"+txtwamount.getText());
        
         if(Integer.parseInt(txttest2.getText())>Integer.parseInt(txtwinaccount.getText())){
                JOptionPane.showMessageDialog(null, "This Amount is more than what you have in account!","Error",JOptionPane.ERROR_MESSAGE);
                txtwamount.setText("");
                 txttest2.setText("0"+txtwamount.getText());
            }
        
        
        int firstnum= Integer.parseInt(txttest2.getText());
        int secondnum=Integer.parseInt(txtwinaccount.getText());
       int result=secondnum-firstnum;
        String Total= String.valueOf(result);
            txtwbalance.setText(Total); 
              
    }//GEN-LAST:event_txtwamountKeyReleased

    private void txtraccFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtraccFocusLost
        try {
            Statement stmt;
            stmt= conn.createStatement();
            String sql="select * from account where accno='"+txtracc.getText()+"'";
            rs=stmt.executeQuery(sql);  
     
      if(rs.next()){   
        String recipientname=rs.getString("name");
        txtrname.setText(recipientname);  
      }else{
         JOptionPane.showMessageDialog( null, "The Account Number does not exists","Error", JOptionPane.ERROR_MESSAGE);
          txtracc.setText("");
          txtrname.setText("");
        txtracc.setText("");
      }    
        } catch (SQLException | HeadlessException e) {
            System.err.println(e);
        }
    }//GEN-LAST:event_txtraccFocusLost

    private void txttbalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttbalanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttbalanceActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            Statement stmt;
            stmt= conn.createStatement();
            String sql1="select * from account where accno='"+txtracc.getText()+"'";
            rs=stmt.executeQuery(sql1);
            
            if(txttamount.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please Insert the Amount to Transfer","Error",JOptionPane.ERROR_MESSAGE);
            }else if(txtracc.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please Insert the recipient's Account Number","Error",JOptionPane.ERROR_MESSAGE);
            }else if(!rs.next()){
                txttamount.setText("");
          }else{ 
            
           String sql="insert into deposit(transfer,recipientaccno,recipientname,total,accountno,savedate)values(?,?,?,?,?,?)";
            pst=conn.prepareStatement(sql);
            pst.setString(1, txttamount.getText());
            pst.setString(2, txtracc.getText());
            pst.setString(3, txtrname.getText());
            pst.setString(4, txttbalance.getText());
            pst.setString(5, txtacc.getText());
            pst.setString(6, clock.getText());
            int dialogResult=JOptionPane.showConfirmDialog(null, "Transfer "+txttamount.getText()+" To "+txtrname.getText()+ " Account Number "+txtracc.getText()+"?","Take Action", JOptionPane.YES_NO_OPTION);
            if(dialogResult==JOptionPane.YES_OPTION){
                 pst.execute();
            }else{
                txttamount.setText("");
            } 
            }
            } catch (SQLException | HeadlessException e) {
            System.err.println(e);
        }amountinacc();
         txttamount.setText("");
         txtracc.setText("");
         txtrname.setText("");      
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtraccKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtraccKeyReleased

        if(txtracc.getText().equals(txtacc.getText())){
            JOptionPane.showMessageDialog(null, "You Can Not Transfer Money From One Account to the same Account!","Warning",JOptionPane.WARNING_MESSAGE);
            txtracc.setText("");
            txtrname.setText("");
        }
         
    }//GEN-LAST:event_txtraccKeyReleased

    private void txttamountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttamountKeyReleased
        
        txttest3.setText("0"+txttamount.getText());
        
         if(Integer.parseInt(txttest3.getText())>Integer.parseInt(txtwinaccount.getText())){
                JOptionPane.showMessageDialog(null, "This Amount is more than what you have in account!","Error",JOptionPane.ERROR_MESSAGE);
                txttamount.setText("");
                 txttest3.setText("0"+txttamount.getText());
            }
        
        
        int firstnum= Integer.parseInt(txttest3.getText());
        int secondnum=Integer.parseInt(txttinaccount.getText());
       int result=secondnum-firstnum;
        String Total= String.valueOf(result);
            txttbalance.setText(Total); 
        
    }//GEN-LAST:event_txttamountKeyReleased

    private void txttamountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttamountKeyPressed
      
    }//GEN-LAST:event_txttamountKeyPressed

    private void txttamountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttamountKeyTyped
       char c=evt.getKeyChar();
       if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
       }
    }//GEN-LAST:event_txttamountKeyTyped

    private void txtwamountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtwamountKeyTyped
       char c=evt.getKeyChar();
       if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
       }
    }//GEN-LAST:event_txtwamountKeyTyped

    private void txtmobileKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmobileKeyTyped
        char c=evt.getKeyChar();
       if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
       }
    }//GEN-LAST:event_txtmobileKeyTyped

   
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel checkbalancepanel;
    private javax.swing.JLabel clock;
    private javax.swing.JPanel depositpanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblbalance;
    private javax.swing.JLabel lblimg;
    private javax.swing.JPanel profilepanel;
    private javax.swing.JPanel transactionpanel;
    private javax.swing.JPanel transferpanel;
    private javax.swing.JTextField txtacc;
    private javax.swing.JTextField txtalltotal;
    private javax.swing.JTextField txtdepam;
    private javax.swing.JTextField txtdeposit;
    private javax.swing.JTextField txtgender;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtincount;
    private javax.swing.JTextField txtmobile;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtracc;
    private javax.swing.JTextField txtram;
    private javax.swing.JTextField txtrname;
    private javax.swing.JTextField txttam;
    private javax.swing.JTextField txttamount;
    private javax.swing.JTextField txttbalance;
    private javax.swing.JTextField txttest;
    private javax.swing.JTextField txttest2;
    private javax.swing.JTextField txttest3;
    private javax.swing.JTextField txttinaccount;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txtwamount;
    private javax.swing.JTextField txtwbalance;
    private javax.swing.JTextField txtwinaccount;
    private javax.swing.JTextField txtwitam;
    private javax.swing.JLabel user;
    private javax.swing.JPanel widthrawpanel;
    // End of variables declaration//GEN-END:variables

    String filename=null;
    byte[] personimage=null;
    FileInputStream fis;
}
