/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vandana
 */
public class hw3 extends javax.swing.JFrame {
public static Connection conn = null;
Statement stmtreview = null;
public JDialog dialog;
    /**
     * Creates new form hw3
     */
    public hw3() {
        initComponents();

        
        String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
        String DB_URL = "jdbc:mysql://localhost/";

        String user = "DBHW";
        String password = "25@Scooty";
        String port = "1521";
        String DBname = "SYSTEM";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Connection failed!" + e);
            return;
        }
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", user, password);
            System.out.println("Creating database...");
            System.out.println("Database created successfully...");

        } catch (SQLException se) {
            System.out.println("Connection failed!" + se);
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();

        }

        System.out.println("business button is clicked");

        StringBuffer businessbuffer = new StringBuffer();
        businessbuffer.append("select DISTINCT(category) from subcategories");
        String getCatQuery = "select DISTINCT(category) from subcategories";

        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", user, password);
            System.out.println("Database created successfully...");

            PreparedStatement catstmt = conn.prepareStatement(getCatQuery);
            ResultSet businessresult = catstmt.executeQuery();
            ArrayList<String> catlist = new ArrayList<>();
            //Cat List Model
            DefaultListModel cat_model = new DefaultListModel();
            int count = 0;
            while (businessresult.next()) {
                String cat = businessresult.getString(1);
                cat_model.add(count, cat);
                count++;
            }
            CatUIList.setModel(cat_model);

            businessresult.close();
            catstmt.close();
            
        } catch (SQLException se) {
            System.out.println("Connection failed!" + se);
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();

        }
        
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        
        int row = jTable1.rowAtPoint(evt.getPoint());
        int col = jTable1.columnAtPoint(evt.getPoint());
        System.out.println(".mouseClicked()");
         JTable dialogtable = new JTable();
             DefaultTableModel dt = new DefaultTableModel();
             dialogtable.setModel(dt);
             dialog = new JDialog();
             dialog.setSize(1000, 1000);
             dialog.add(dialogtable);
             dialog.setVisible(true);
        if(userbutton.isSelected()){
                    if (row >= 0 && col == 3) {
             String userid= (String) jTable1.getValueAt(row, col);
            StringBuffer sf = new StringBuffer();
            sf.append("select Text from reviews where USER_ID='");
            sf.append(userid);
            sf.append("'");
            
            String q = sf.toString();
            System.out.println(q);
            
             try {
        stmtreview = conn.createStatement();
        ResultSet reviewslt = stmtreview.executeQuery(q);
        ResultSetMetaData reviewresultSetMetaData = reviewslt.getMetaData();
                int columnCount = reviewresultSetMetaData.getColumnCount();
                for (int i = 0; i < columnCount; ++i) {
                    String name = reviewresultSetMetaData.getColumnName(i + 1);
                    dt.addColumn(name);
                }
                while (reviewslt.next()) {
                    Object[] rowData = new Object[columnCount];
                    for (int i = 0; i < rowData.length; i++) {
                        rowData[i] = reviewslt.getObject(i + 1);
                    }
                    dt.addRow(rowData);
                }    } catch (SQLException e ) {
                 System.out.println("onclick failed");
    }
                    }
        }
        else {
            if (row >= 0 && col == 0) {
             String business_id= (String) jTable1.getValueAt(row, col);
            
            StringBuffer businessbuffer = new StringBuffer();
            businessbuffer.append("select DISTINCT(Text) from reviews r, business b where r.BUSINESS_ID='");
            businessbuffer.append(business_id);
            businessbuffer.append("'");
            
            String q = businessbuffer.toString();
            System.out.println(q);
            
             try {
        stmtreview = conn.createStatement();
        ResultSet reviewslt = stmtreview.executeQuery(q);
        ResultSetMetaData reviewresultSetMetaData = reviewslt.getMetaData();
                int columnCount = reviewresultSetMetaData.getColumnCount();
                for (int i = 0; i < columnCount; ++i) {
                    String name = reviewresultSetMetaData.getColumnName(i + 1);
                    dt.addColumn(name);
                }
                while (reviewslt.next()) {
                    Object[] rowData = new Object[columnCount];
                    for (int i = 0; i < rowData.length; i++) {
                        rowData[i] = reviewslt.getObject(i + 1);
                    }
                    dt.addRow(rowData);
                }
            } catch (SQLException e ) {
                 System.out.println("onclick failed");
    } 
        }
        }

    }
}); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ReviewCountdropdown = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        Yelpingsince = new org.jdesktop.swingx.JXDatePicker();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        AvgStarsdropdown = new javax.swing.JComboBox<>();
        NumoffriendsDropdown = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        Reviewcountvalue = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Avgstarsvalue = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Numoffriendsvalue = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Querydisplay = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        CatUIList = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        SubcatUIList = new javax.swing.JList<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        checkinfrom = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        checkinto = new javax.swing.JComboBox<>();
        userbutton = new javax.swing.JRadioButton();
        BusinessRadioButton = new javax.swing.JRadioButton();
        Executequery = new javax.swing.JButton();
        ANDORBOX = new javax.swing.JComboBox<>();
        numofcheckinoperator = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        numodcheckin = new javax.swing.JTextField();
        hoursfrom = new javax.swing.JTextField();
        hoursto = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        reviewfromdate = new org.jdesktop.swingx.JXDatePicker();
        reviewtodate = new org.jdesktop.swingx.JXDatePicker();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        starsoperator = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        reviewstarvalue = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        votesoperator = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        votesvalue = new javax.swing.JTextField();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Users");

        jLabel2.setText("Review Count ");

        ReviewCountdropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<", ">", "=" }));

        jLabel3.setText("Yelping Since");

        Yelpingsince.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YelpingsinceActionPerformed(evt);
            }
        });

        jLabel4.setText("Num of Friends");

        jLabel5.setText("Avg Stars");

        AvgStarsdropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<", ">", "=" }));
        AvgStarsdropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AvgStarsdropdownActionPerformed(evt);
            }
        });

        NumoffriendsDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<", ">", "=" }));

        jLabel6.setText("value");

        Reviewcountvalue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReviewcountvalueActionPerformed(evt);
            }
        });

        jLabel7.setText("value");

        Avgstarsvalue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AvgstarsvalueActionPerformed(evt);
            }
        });

        jLabel8.setText("value");

        Numoffriendsvalue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumoffriendsvalueActionPerformed(evt);
            }
        });

        Querydisplay.setEditable(false);
        Querydisplay.setColumns(20);
        Querydisplay.setRows(5);
        jScrollPane1.setViewportView(Querydisplay);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        DefaultListModel<String> model1 = new DefaultListModel<>();
        CatUIList.setModel(model1);
        CatUIList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                CatUIListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(CatUIList);

        jScrollPane4.setViewportView(SubcatUIList);

        jLabel11.setText("Category");

        jLabel12.setText("Subcategory");

        jLabel13.setText("Checkin");

        jLabel14.setText("from");

        checkinfrom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monday", "Tuesday", "Wednesday", "Thrusday", "Friday", "Saturday", "Sunday" }));

        jLabel15.setText("to");

        checkinto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monday", "Tuesday", "Wednesday", "Thrusday", "Friday", "Saturday", "Sunday" }));

        userbutton.setText("User");
        userbutton.setToolTipText("");
        userbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userbuttonActionPerformed(evt);
            }
        });

        BusinessRadioButton.setText("Business");
        BusinessRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BusinessRadioButtonMouseClicked(evt);
            }
        });
        BusinessRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BusinessRadioButtonActionPerformed(evt);
            }
        });

        Executequery.setText("Execute Query");
        Executequery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExecutequeryActionPerformed(evt);
            }
        });

        ANDORBOX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AND", "OR" }));
        ANDORBOX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ANDORBOXActionPerformed(evt);
            }
        });

        numofcheckinoperator.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<", ">", "=" }));

        jLabel9.setText("Number of Checkins");

        jLabel10.setText("value");

        numodcheckin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numodcheckinActionPerformed(evt);
            }
        });

        hoursfrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hoursfromActionPerformed(evt);
            }
        });

        jLabel16.setText("Review");

        jLabel17.setText("To");

        jLabel18.setText("From");

        jLabel19.setText("Star");

        starsoperator.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<", ">", "=" }));

        jLabel20.setText("value");

        jLabel21.setText("votes");

        votesoperator.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<", ">", "=" }));

        jLabel22.setText("value");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(184, 184, 184)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(78, 78, 78)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(numodcheckin, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(numofcheckinoperator, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(checkinfrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(checkinto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(hoursfrom, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                            .addComponent(hoursto)))))
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(60, 60, 60)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(NumoffriendsDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(AvgStarsdropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3))
                                        .addGap(65, 65, 65)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Yelpingsince, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ReviewCountdropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Reviewcountvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Avgstarsvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Numoffriendsvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addGap(158, 158, 158)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel19))
                                        .addGap(11, 11, 11))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel22)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel21)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel18)
                                                    .addComponent(jLabel17))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(starsoperator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(reviewfromdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(reviewtodate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(reviewstarvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(votesoperator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(votesvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(ANDORBOX, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(273, 273, 273)
                                .addComponent(Executequery))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addComponent(userbutton))
                                .addGap(16, 16, 16)
                                .addComponent(BusinessRadioButton)))
                        .addGap(1063, 1063, 1063)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(211, 211, 211))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userbutton)
                            .addComponent(BusinessRadioButton))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(checkinfrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(hoursfrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(checkinto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(hoursto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9)
                                        .addGap(16, 16, 16)
                                        .addComponent(numofcheckinoperator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(numodcheckin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(reviewfromdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel17))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(reviewtodate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel18))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel19)
                                            .addComponent(starsoperator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel20)
                                            .addComponent(reviewstarvalue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel21)
                                            .addComponent(votesoperator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel22)
                                            .addComponent(votesvalue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 36, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(Yelpingsince, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ReviewCountdropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(Reviewcountvalue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(AvgStarsdropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(Avgstarsvalue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(NumoffriendsDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Numoffriendsvalue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)))
                .addGap(113, 113, 113)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ANDORBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Executequery))
                .addGap(67, 67, 67))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AvgStarsdropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AvgStarsdropdownActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AvgStarsdropdownActionPerformed

    private void ReviewcountvalueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReviewcountvalueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ReviewcountvalueActionPerformed

    private void AvgstarsvalueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AvgstarsvalueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AvgstarsvalueActionPerformed

    private void NumoffriendsvalueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumoffriendsvalueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NumoffriendsvalueActionPerformed

    private void userbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userbuttonActionPerformed
        // TODO add your handling code here:
        if (userbutton.isSelected()) {
            jLabel1.setEnabled(rootPaneCheckingEnabled);
            checkinfrom.setEnabled(false);
            checkinto.setEnabled(false);
            CatUIList.setEnabled(false);
            SubcatUIList.setEnabled(false);
            Yelpingsince.setEnabled(true);
            Reviewcountvalue.setEnabled(true);
            Avgstarsvalue.setEnabled(true);
            Numoffriendsvalue.setEnabled(true);
            numodcheckin.setEnabled(false);
            reviewstarvalue.setEnabled(false);
            votesvalue.setEnabled(false);
            reviewfromdate.setEnabled(false);
            reviewtodate.setEnabled(false);
            BusinessRadioButton.setEnabled(false);
        }
        else{
            BusinessRadioButton.setEnabled(true);
        }
    }//GEN-LAST:event_userbuttonActionPerformed

    private void BusinessRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BusinessRadioButtonActionPerformed
        if (BusinessRadioButton.isSelected()) {
            Yelpingsince.setEnabled(false);
            Reviewcountvalue.setEnabled(false);
            Avgstarsvalue.setEnabled(false);
            Numoffriendsvalue.setEnabled(false);

            checkinfrom.setEnabled(true);
            checkinto.setEnabled(true);
            CatUIList.setEnabled(true);
            SubcatUIList.setEnabled(true);
            numodcheckin.setEnabled(true);
            reviewstarvalue.setEnabled(true);
            votesvalue.setEnabled(true);
            reviewfromdate.setEnabled(true);
            reviewtodate.setEnabled(true);
        }
    }//GEN-LAST:event_BusinessRadioButtonActionPerformed

    private void BusinessRadioButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BusinessRadioButtonMouseClicked


    }//GEN-LAST:event_BusinessRadioButtonMouseClicked

    private void ExecutequeryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExecutequeryActionPerformed

        Connection conn = null;
        String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
        String DB_URL = "jdbc:mysql://localhost/";

        String user = "DBHW";
        String password = "25@Scooty";
        String port = "1521";
        String DBname = "SYSTEM";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Connection failed!" + e);
            return;
        }
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", user, password);
            System.out.println("Creating database...");
            System.out.println("Database created successfully...");

        } catch (SQLException se) {
            System.out.println("Connection failed!" + se);
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();

        }

        if (userbutton.isSelected()) {
            StringBuffer buffer = new StringBuffer();
            Boolean andflag = false;
            buffer = buffer.append("select * from yelp_users ");
            if (Yelpingsince.getDate() != null) {
                java.sql.Date yelpuserdate = null;
                try {
                    yelpuserdate = new java.sql.Date(Yelpingsince.getDate().getTime());
                } catch (Exception e) {
                    System.out.println("Exception in date format");
                }
                andflag = true;
                buffer.append("where");
                buffer.append(" YELPING_SINCE > to_date('" + yelpuserdate + "','YYYY-MM-DD')");
            }
            if (!Reviewcountvalue.getText().isEmpty()) {
                if (andflag == true) {
                    if (ANDORBOX.getSelectedItem().toString() == "OR") {
                        buffer.append(" or");
                    } else {
                        buffer.append(" and");
                    }

                } else {
                    buffer.append("where");
                }
                andflag = true;
                buffer.append(" REVIEW_COUNT");
                buffer.append(ReviewCountdropdown.getSelectedItem());
                buffer.append(Reviewcountvalue.getText());
            }
            if (!Avgstarsvalue.getText().isEmpty()) {
                if (andflag == true) {
                    if (ANDORBOX.getSelectedItem().toString() == "OR") {
                        buffer.append(" or");
                    } else {
                        buffer.append(" and");
                    }

                } else {
                    buffer.append("where");
                }
                andflag = true;
                buffer.append(" AVERAGE_STARS");
                buffer.append(AvgStarsdropdown.getSelectedItem());
                double newavg = Double.parseDouble(Avgstarsvalue.getText());
                buffer.append(newavg);
            }
            if (!Numoffriendsvalue.getText().isEmpty()) {
                if (andflag == true) {
                    if (ANDORBOX.getSelectedItem().toString() == "OR") {
                        buffer.append(" or");
                    } else {
                        buffer.append(" and");
                    }
                } else {
                    buffer.append("where");
                }
                andflag = true;
                buffer.append(" COUNT_FRIENDS");
                buffer.append(NumoffriendsDropdown.getSelectedItem());
                buffer.append(Numoffriendsvalue.getText());

            }

            System.out.println(buffer);
            Querydisplay.setText(buffer.toString());
                    
                    //buffer.toString());

            try {
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setColumnCount(0);
                model.setRowCount(0);

                String sql = buffer.toString();
                //	String sql ="select * from yelp_users";
                System.out.println("m here");
                System.out.println(sql);

                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();

                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();
                for (int i = 0; i < columnCount; ++i) {
                    String name = resultSetMetaData.getColumnName(i + 1);
                    model.addColumn(name);
                }
                while (rs.next()) {
                    Object[] rowData = new Object[columnCount];
                    for (int i = 0; i < rowData.length; i++) {
                        rowData[i] = rs.getObject(i + 1);
                    }
                    model.addRow(rowData);
                }
                rs.close();
                pstmt.close();

            } catch (SQLException se) {
                System.out.println("Connection failed!" + se);
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }
        } else {

            String finalquery = "";
            StringBuffer finalquerybuffer = new StringBuffer();
            System.out.println("business button is clicked");
            //if only cat is selected
            Object[] l = CatUIList.getSelectedValues();
            if (l.length > 0) {
                int count = 0;
                String temp_subcat = "";
                for (int i = 0; i < l.length; i++) {
                    temp_subcat = temp_subcat + "'" + l[i].toString() + "' ,";
                }
                temp_subcat = temp_subcat.substring(0, temp_subcat.length() - 1);
                StringBuffer catquerybuffer = new StringBuffer();
                DefaultListModel Subcat_model = new DefaultListModel();
                catquerybuffer.append("Select * from Business where BUSINESS_ID IN(\n"
                        + "Select BUSINESS_ID from SUBCATEGORIES where category IN(");
                catquerybuffer.append(temp_subcat);
                catquerybuffer.append("))");
                finalquerybuffer = catquerybuffer;
                Object[] k = SubcatUIList.getSelectedValues();
                if (k.length > 0) {
                    String catfinalquery = catquerybuffer.toString();
                    catfinalquery = catfinalquery.substring(0, catfinalquery.length() - 1);
                    StringBuffer subcatBuffer = new StringBuffer(catfinalquery);
                    subcatBuffer.append("and SUBCATEGORY IN(");
                    int count1 = 0;
                    String temp_subcatq = "";
                    for (int i = 0; i < k.length; i++) {
                        temp_subcatq = temp_subcatq + "'" + k[i].toString() + "' ,";
                    }
                    temp_subcatq = temp_subcatq.substring(0, temp_subcatq.length() - 1);
                    subcatBuffer.append(temp_subcatq);
                    subcatBuffer.append("))");
                    finalquerybuffer = subcatBuffer;
                }

                if (!hoursfrom.getText().equals("") && !hoursto.getText().equals("") && !numodcheckin.getText().equals("")) {
                    StringBuffer checkinbufferwithcat = new StringBuffer();
                    checkinbufferwithcat = finalquerybuffer;
                    checkinbufferwithcat.append("and BUSINESS_ID IN(select BUSINESS_ID from checkin where (day='");
                    HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
                    hashMap.put(1, "Monday");
                    hashMap.put(2, "Tuesday");
                    hashMap.put(3, "Wednesday");
                    hashMap.put(4, "Thrusday");
                    hashMap.put(5, "Friday");
                    hashMap.put(6, "Saturday");
                    hashMap.put(0, "Sunday");

                    String daysfromvalue = checkinfrom.getSelectedItem().toString();

                    int daysintfromvalue = 0;
                    for (Entry<Integer, String> entry : hashMap.entrySet()) {
                        if (entry.getValue().equals(daysfromvalue)) {
                            daysintfromvalue = entry.getKey();
                        }
                    }
                    String daystovalue = checkinto.getSelectedItem().toString();
                    int daysinttovalue = 0;
                    for (Entry<Integer, String> entry : hashMap.entrySet()) {
                        if (entry.getValue().equals(daystovalue)) {
                            daysinttovalue = entry.getKey();
                        }
                    }
                    checkinbufferwithcat.append(daysintfromvalue + "' and hours >= '");
                    checkinbufferwithcat.append(hoursfrom.getText());
                    checkinbufferwithcat.append("') or (day='");
                    checkinbufferwithcat.append(daysinttovalue + "' and hours <='");
                    checkinbufferwithcat.append(hoursto.getText() + "')");
                    System.out.println(checkinbufferwithcat);

                    int difference = 0;
                    int newdays;
                    if (daysinttovalue > daysintfromvalue) {
                        difference = daysinttovalue - daysintfromvalue;

                        for (int i = daysintfromvalue + 1; i < daysinttovalue; i++) {
                            daysintfromvalue++;
                            checkinbufferwithcat.append("or (day='" + daysintfromvalue + "' and hours >='0')");
                        }
                    } else {

                        for (int i = daysintfromvalue; i < 10; i++) {
                            daysintfromvalue++;
                            if (daysintfromvalue == 7) {
                                daysintfromvalue = 0;
                            }
                            checkinbufferwithcat.append("or (day='" + daysintfromvalue + "' and hours >='0')");
                            if (daysintfromvalue == 0) {
                                break;
                            }
                        }
                        int counttovalue = 0;
                        counttovalue = daysinttovalue;
                        for (int i = 0; i < counttovalue; i++) {
                            i++;
                            checkinbufferwithcat.append("or (day='" + i + "' and hours >='0')");
                        }

                    }
                    checkinbufferwithcat.append("group by BUSINESS_ID having sum(NUMCHECKIN)");
                    checkinbufferwithcat.append(numofcheckinoperator.getSelectedItem());
                    checkinbufferwithcat.append(numodcheckin.getText() + ")");
                    finalquerybuffer = checkinbufferwithcat;
                    System.out.println(checkinbufferwithcat);
                    if (!reviewstarvalue.getText().equals("") && !votesvalue.getText().equals("") && !reviewfromdate.getDate().equals("") && !reviewtodate.getDate().equals("")) {
                    java.sql.Date reviewDatefrom = null;
                    java.sql.Date reviewDateto = null;

                    StringBuffer reviewbufferwithcat = new StringBuffer();
                    reviewbufferwithcat = finalquerybuffer;
                    reviewbufferwithcat.append("and BUSINESS_ID IN(select BUSINESS_ID from reviews where REVIEWDATE> to_date('");
                    reviewDatefrom = new java.sql.Date(reviewfromdate.getDate().getTime());
                    reviewDateto = new java.sql.Date(reviewtodate.getDate().getTime());
                    reviewbufferwithcat.append(reviewDatefrom);
                    reviewbufferwithcat.append("','YYYY-MM-DD') and REVIEWDATE< to_date('");
                    reviewbufferwithcat.append(reviewDateto);
                    reviewbufferwithcat.append("','YYYY-MM-DD') group by BUSINESS_ID having AVG(STARS) ");
                    reviewbufferwithcat.append(starsoperator.getSelectedItem());
                    reviewbufferwithcat.append(reviewstarvalue.getText());
                    reviewbufferwithcat.append("and sum(useful + COOL+ FUNNY)");
                    reviewbufferwithcat.append(votesoperator.getSelectedItem());
                    reviewbufferwithcat.append(votesvalue.getText());
                    reviewbufferwithcat.append(")");
                    finalquerybuffer = reviewbufferwithcat;
                }
                }

                if (!reviewstarvalue.getText().equals("") && !votesvalue.getText().equals("") && !reviewfromdate.getDate().equals("") && !reviewtodate.getDate().equals("")) {
                    java.sql.Date reviewDatefrom = null;
                    java.sql.Date reviewDateto = null;

                    StringBuffer reviewbufferwithcat = new StringBuffer();
                    reviewbufferwithcat = finalquerybuffer;
                    reviewbufferwithcat.append("and BUSINESS_ID IN(select BUSINESS_ID from reviews where REVIEWDATE> to_date('");
                    reviewDatefrom = new java.sql.Date(reviewfromdate.getDate().getTime());
                    reviewDateto = new java.sql.Date(reviewtodate.getDate().getTime());
                    reviewbufferwithcat.append(reviewDatefrom);
                    reviewbufferwithcat.append("','YYYY-MM-DD') and REVIEWDATE< to_date('");
                    reviewbufferwithcat.append(reviewDateto);
                    reviewbufferwithcat.append("','YYYY-MM-DD') group by BUSINESS_ID having AVG(STARS) ");
                    reviewbufferwithcat.append(starsoperator.getSelectedItem());
                    reviewbufferwithcat.append(reviewstarvalue.getText());
                    reviewbufferwithcat.append("and sum(useful + COOL+ FUNNY)");
                    reviewbufferwithcat.append(votesoperator.getSelectedItem());
                    reviewbufferwithcat.append(votesvalue.getText());
                    reviewbufferwithcat.append(")");
                    finalquerybuffer = reviewbufferwithcat;
                }
            } else if ((!reviewstarvalue.getText().equals("") && !votesvalue.getText().equals("") && !reviewfromdate.getDate().equals("") && !reviewtodate.getDate().equals(""))
                    && (!hoursfrom.getText().equals("") && !hoursto.getText().equals("") && !numodcheckin.getText().equals(""))) {
                StringBuffer checkinbufferwithcat = new StringBuffer();
                checkinbufferwithcat = finalquerybuffer;
                checkinbufferwithcat.append("Select * from Business where BUSINESS_ID IN(select BUSINESS_ID from checkin where (day='");
                HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
                hashMap.put(1, "Monday");
                hashMap.put(2, "Tuesday");
                hashMap.put(3, "Wednesday");
                hashMap.put(4, "Thrusday");
                hashMap.put(5, "Friday");
                hashMap.put(6, "Saturday");
                hashMap.put(0, "Sunday");

                String daysfromvalue = checkinfrom.getSelectedItem().toString();

                int daysintfromvalue = 0;
                for (Entry<Integer, String> entry : hashMap.entrySet()) {
                    if (entry.getValue().equals(daysfromvalue)) {
                        daysintfromvalue = entry.getKey();
                    }
                }
                String daystovalue = checkinto.getSelectedItem().toString();
                int daysinttovalue = 0;
                for (Entry<Integer, String> entry : hashMap.entrySet()) {
                    if (entry.getValue().equals(daystovalue)) {
                        daysinttovalue = entry.getKey();
                    }
                }
                checkinbufferwithcat.append(daysintfromvalue + "' and hours >= '");
                checkinbufferwithcat.append(hoursfrom.getText());
                checkinbufferwithcat.append("') or (day='");
                checkinbufferwithcat.append(daysinttovalue + "' and hours <='");
                checkinbufferwithcat.append(hoursto.getText() + "')");
                System.out.println(checkinbufferwithcat);

                int difference = 0;
                int newdays;
                if (daysinttovalue > daysintfromvalue) {
                    difference = daysinttovalue - daysintfromvalue;

                    for (int i = daysintfromvalue + 1; i < daysinttovalue; i++) {
                        daysintfromvalue++;
                        checkinbufferwithcat.append("or (day='" + daysintfromvalue + "' and hours >='0')");
                    }
                } else {

                    for (int i = daysintfromvalue; i < 10; i++) {
                        daysintfromvalue++;
                        if (daysintfromvalue == 7) {
                            daysintfromvalue = 0;
                        }
                        checkinbufferwithcat.append("or (day='" + daysintfromvalue + "' and hours >='0')");
                        if (daysintfromvalue == 0) {
                            break;
                        }
                    }
                    int counttovalue = 0;
                    counttovalue = daysinttovalue;
                    for (int i = 0; i < counttovalue; i++) {
                        i++;
                        checkinbufferwithcat.append("or (day='" + i + "' and hours >='0')");
                    }

                }
                checkinbufferwithcat.append("group by BUSINESS_ID having sum(NUMCHECKIN)");
                checkinbufferwithcat.append(numofcheckinoperator.getSelectedItem());
                checkinbufferwithcat.append(numodcheckin.getText() + ")");

                java.sql.Date reviewDatefrom = null;
                java.sql.Date reviewDateto = null;

                StringBuffer reviewbuffer = new StringBuffer();
                reviewbuffer = checkinbufferwithcat;
                reviewbuffer.append("and BUSINESS_ID IN(select BUSINESS_ID from reviews where REVIEWDATE> to_date('");
                reviewDatefrom = new java.sql.Date(reviewfromdate.getDate().getTime());
                reviewDateto = new java.sql.Date(reviewtodate.getDate().getTime());
                reviewbuffer.append(reviewDatefrom);
                reviewbuffer.append("','YYYY-MM-DD') and REVIEWDATE< to_date('");
                reviewbuffer.append(reviewDateto);
                reviewbuffer.append("','YYYY-MM-DD') group by BUSINESS_ID having AVG(STARS)");
                reviewbuffer.append(starsoperator.getSelectedItem());
                reviewbuffer.append(reviewstarvalue.getText() + ")");
                finalquerybuffer = reviewbuffer;

                System.out.println(finalquerybuffer);
            } else if (!reviewstarvalue.getText().equals("") && !votesvalue.getText().equals("") && !reviewfromdate.getDate().equals("") && !reviewtodate.getDate().equals("")) {
                java.sql.Date reviewDatefrom = null;
                java.sql.Date reviewDateto = null;

                StringBuffer reviewbuffer = new StringBuffer();
                reviewbuffer = finalquerybuffer;
                reviewbuffer.append("Select * from Business where BUSINESS_ID IN(select BUSINESS_ID from reviews where REVIEWDATE> to_date('");
                reviewDatefrom = new java.sql.Date(reviewfromdate.getDate().getTime());
                reviewDateto = new java.sql.Date(reviewtodate.getDate().getTime());
                reviewbuffer.append(reviewDatefrom);
                reviewbuffer.append("','YYYY-MM-DD') and REVIEWDATE< to_date('");
                reviewbuffer.append(reviewDateto);
                reviewbuffer.append("','YYYY-MM-DD') group by BUSINESS_ID having AVG(STARS)");
                reviewbuffer.append(starsoperator.getSelectedItem());
                reviewbuffer.append(reviewstarvalue.getText() + ")");
                finalquerybuffer = reviewbuffer;
            } else if (!hoursfrom.getText().equals("") && !hoursto.getText().equals("") && !numodcheckin.getText().equals("")) {
                StringBuffer checkinbufferwithcat = new StringBuffer();
                checkinbufferwithcat = finalquerybuffer;
                checkinbufferwithcat.append("Select * from Business where BUSINESS_ID IN(select BUSINESS_ID from checkin where (day='");
                HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
                hashMap.put(1, "Monday");
                hashMap.put(2, "Tuesday");
                hashMap.put(3, "Wednesday");
                hashMap.put(4, "Thrusday");
                hashMap.put(5, "Friday");
                hashMap.put(6, "Saturday");
                hashMap.put(0, "Sunday");

                String daysfromvalue = checkinfrom.getSelectedItem().toString();

                int daysintfromvalue = 0;
                for (Entry<Integer, String> entry : hashMap.entrySet()) {
                    if (entry.getValue().equals(daysfromvalue)) {
                        daysintfromvalue = entry.getKey();
                    }
                }
                String daystovalue = checkinto.getSelectedItem().toString();
                int daysinttovalue = 0;
                for (Entry<Integer, String> entry : hashMap.entrySet()) {
                    if (entry.getValue().equals(daystovalue)) {
                        daysinttovalue = entry.getKey();
                    }
                }
                checkinbufferwithcat.append(daysintfromvalue + "' and hours >= '");
                checkinbufferwithcat.append(hoursfrom.getText());
                checkinbufferwithcat.append("') or (day='");
                checkinbufferwithcat.append(daysinttovalue + "' and hours <='");
                checkinbufferwithcat.append(hoursto.getText() + "')");
                System.out.println(checkinbufferwithcat);

                int difference = 0;
                int newdays;
                if (daysinttovalue > daysintfromvalue) {
                    difference = daysinttovalue - daysintfromvalue;

                    for (int i = daysintfromvalue + 1; i < daysinttovalue; i++) {
                        daysintfromvalue++;
                        checkinbufferwithcat.append("or (day='" + daysintfromvalue + "' and hours >='0')");
                    }
                } else {

                    for (int i = daysintfromvalue; i < 10; i++) {
                        daysintfromvalue++;
                        if (daysintfromvalue == 7) {
                            daysintfromvalue = 0;
                        }
                        checkinbufferwithcat.append("or (day='" + daysintfromvalue + "' and hours >='0')");
                        if (daysintfromvalue == 0) {
                            break;
                        }
                    }
                    int counttovalue = 0;
                    counttovalue = daysinttovalue;
                    for (int i = 0; i < counttovalue; i++) {
                        i++;
                        checkinbufferwithcat.append("or (day='" + i + "' and hours >='0')");
                    }

                }
                checkinbufferwithcat.append("group by BUSINESS_ID having sum(NUMCHECKIN)");
                checkinbufferwithcat.append(numofcheckinoperator.getSelectedItem());
                checkinbufferwithcat.append(numodcheckin.getText() + ")");
                finalquerybuffer = checkinbufferwithcat;
                System.out.println(checkinbufferwithcat);
            }

            try {
                Querydisplay.setText(finalquerybuffer.toString());
                System.out.println(finalquerybuffer);
                String finalq = "";
                finalq = finalquerybuffer.toString();
                DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
                model1.setColumnCount(0);
                model1.setRowCount(0);
                PreparedStatement subcatstmt1 = conn.prepareStatement(finalq);
                ResultSet Subcatresult1 = subcatstmt1.executeQuery();
                ResultSetMetaData resultSetMetaData = Subcatresult1.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();
                for (int i = 0; i < columnCount; ++i) {
                    String name = resultSetMetaData.getColumnName(i + 1);
                    model1.addColumn(name);
                }
                while (Subcatresult1.next()) {
                    Object[] rowData = new Object[columnCount];
                    for (int i = 0; i < rowData.length; i++) {
                        rowData[i] = Subcatresult1.getObject(i + 1);
                    }
                    model1.addRow(rowData);
                }

                Subcatresult1.close();
                subcatstmt1.close();

            } catch (SQLException se) {
                System.out.println("Connection failed!" + se);
                se.printStackTrace();
            }

        }


    }//GEN-LAST:event_ExecutequeryActionPerformed

    private void YelpingsinceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YelpingsinceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_YelpingsinceActionPerformed

    private void ANDORBOXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ANDORBOXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ANDORBOXActionPerformed

    private void CatUIListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_CatUIListValueChanged
        // TODO add your handling code here:
        Connection conn2 = null;
        String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
        String DB_URL = "jdbc:mysql://localhost/";
        String user = "DBHW";
        String password = "25@Scooty";
        String port = "1521";
        String DBname = "SYSTEM";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Connection failed!" + e);
            return;
        }
        try {
            System.out.println("business block");
            System.out.println("Connecting to database...");
            conn2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", user, password);
            System.out.println("Database created successfully...");
        } catch (SQLException se) {
            System.out.println("Connection failed!" + se);
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();

        }

        Object[] l = CatUIList.getSelectedValues();
        int count = 0;
        DefaultListModel Subcat_model = new DefaultListModel();
        for (int i = 0; i < l.length; i++) {
            //Query
            StringBuffer Subcatquerybuffer = new StringBuffer();
            Subcatquerybuffer.append("select DISTINCT(SUBCATEGORY) from subcategories where SUBCATEGORY IS NOT NULL and CATEGORY IN ('");
            Subcatquerybuffer.append(l[i].toString());
            Subcatquerybuffer.append("')");
            String getsubcatQuery = Subcatquerybuffer.toString();

            try {

                PreparedStatement subcatstmt = conn2.prepareStatement(getsubcatQuery);
                ResultSet Subcatresult = subcatstmt.executeQuery();
                ArrayList<String> Subcatlist = new ArrayList<>();
                //Cat List Model
                while (Subcatresult.next()) {
                    String Subcat = Subcatresult.getString(1);
                    Subcat_model.add(count, Subcat);
                    count++;
                }
                SubcatUIList.setModel(Subcat_model);
                Subcatresult.close();
                subcatstmt.close();

            } catch (SQLException se) {
                System.out.println("Connection failed!" + se);
                se.printStackTrace();
            }

        }

    }//GEN-LAST:event_CatUIListValueChanged

    private void numodcheckinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numodcheckinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numodcheckinActionPerformed

    private void hoursfromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoursfromActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hoursfromActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(hw3.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(hw3.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(hw3.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(hw3.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new hw3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ANDORBOX;
    private javax.swing.JComboBox<String> AvgStarsdropdown;
    private javax.swing.JTextField Avgstarsvalue;
    private javax.swing.JRadioButton BusinessRadioButton;
    private javax.swing.JList<String> CatUIList;
    private javax.swing.JButton Executequery;
    private javax.swing.JComboBox<String> NumoffriendsDropdown;
    private javax.swing.JTextField Numoffriendsvalue;
    private javax.swing.JTextArea Querydisplay;
    private javax.swing.JComboBox<String> ReviewCountdropdown;
    private javax.swing.JTextField Reviewcountvalue;
    private javax.swing.JList<String> SubcatUIList;
    private org.jdesktop.swingx.JXDatePicker Yelpingsince;
    private javax.swing.JComboBox<String> checkinfrom;
    private javax.swing.JComboBox<String> checkinto;
    private javax.swing.JTextField hoursfrom;
    private javax.swing.JTextField hoursto;
    private javax.swing.JFrame jFrame1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField numodcheckin;
    private javax.swing.JComboBox<String> numofcheckinoperator;
    private org.jdesktop.swingx.JXDatePicker reviewfromdate;
    private javax.swing.JTextField reviewstarvalue;
    private org.jdesktop.swingx.JXDatePicker reviewtodate;
    private javax.swing.JComboBox<String> starsoperator;
    private javax.swing.JRadioButton userbutton;
    private javax.swing.JComboBox<String> votesoperator;
    private javax.swing.JTextField votesvalue;
    // End of variables declaration//GEN-END:variables
}
