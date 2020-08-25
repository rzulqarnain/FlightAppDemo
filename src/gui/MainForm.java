package gui;

//import common.FlightAppConstants;
import db.AirportAccessor;
import db.AircraftAccessor;
import db.PassengerAccessor;
import db.ConnectionManager;
import db.FlightAccessor;
import entity.Airport;
import entity.Aircraft;
import entity.Flight;
import entity.Passenger;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import utils.DepartureTimeComparator;
import utils.DepartureTimeComparator;
import utils.FlightAppConstants;




public class MainForm extends javax.swing.JFrame {

    DefaultListModel<Airport> airportsListModel;
    DefaultListModel<Aircraft> aircraftsListModel;
    DefaultListModel<Passenger> passengersListModel;
    DefaultListModel<Flight> outboundFlightsListModel;
    DefaultListModel<Flight> inboundFlightsListModel;

    boolean returnFlight;
    Flight outboundFlight = null, inboundFlight = null;
    
    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();

        if (!ConnectionManager.testConnection()) {
            JOptionPane.showMessageDialog(null, "Could not connect to database. Make sure the database server is running and accessing the correct folder.", "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        // Initialise the airport maintenance GUI list.
        airportsListModel = new DefaultListModel();
        lstAirports.setModel(airportsListModel);
        
        aircraftsListModel = new DefaultListModel();
        OutputAircraft.setModel(aircraftsListModel);
        
        passengersListModel = new DefaultListModel();
        lstpassengers.setModel(passengersListModel);
        
        
        // Set initial state
        setAirportScreenState(FlightAppConstants.AIRPORTS_NOT_LOADED);
        // Load the airports into the combo boxes.
        List<Airport> airports = AirportAccessor.findAllAirports();
        if (airports.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Could not connect to database. Make sure the database server is running and try again.", "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        cmbOrigin.removeAllItems();
        cmbDestination.removeAllItems();
        for (Airport a : airports) {
            cmbOrigin.addItem(a);
            cmbDestination.addItem(a);
        }
        cmbOrigin.setSelectedItem(new Airport("DOH", "", "", "")); // select Doha

        // Set the default selection for round-trip/one-way
        radRoundTrip.setSelected(true);
        radOneWay.setSelected(false);
        grpReturnOrOneway.add(radRoundTrip);
        grpReturnOrOneway.add(radOneWay);

        // Set the default number of adults
        spnAdults.getModel().setValue(1);

        // Initialise the outbound and inbound GUI list elements.
        outboundFlightsListModel = new DefaultListModel();
        lstOutboundFlights.setModel(outboundFlightsListModel);
        inboundFlightsListModel = new DefaultListModel();
        lstInboundFlights.setModel(inboundFlightsListModel);

        // Disable Proceed button
        btnProceed.setEnabled(false);

        tablePassengerInfo.setRowHeight(24);

        // Ensure we start on the search tab
      //  mainTabbedPane.setSelectedIndex(0);

    }

    private void setAirportScreenState(int state) {
        if (state == FlightAppConstants.AIRPORTS_NOT_LOADED) {
            btnLoadAirports.setEnabled(true);
            btnDeleteAirport.setEnabled(false);
            btnEditAirport.setEnabled(false);
            btnAddNewAirport.setEnabled(false);
            btnUpdateAirport.setEnabled(false);
            txtAirportCode.setEnabled(false);
            txtAirportCity.setEnabled(false);
            txtAirportCountry.setEnabled(false);
            txtAirportTimeZone.setEnabled(false);
        } else if (state == FlightAppConstants.AIRPORTS_LOADED) {
            btnLoadAirports.setEnabled(true);
            btnDeleteAirport.setEnabled(false);
            btnEditAirport.setEnabled(false);
            btnAddNewAirport.setEnabled(true);
            btnUpdateAirport.setEnabled(true);
            txtAirportCode.setEnabled(true);
            txtAirportCity.setEnabled(true);
            txtAirportCountry.setEnabled(true);
            txtAirportTimeZone.setEnabled(true);
        } else if (state == FlightAppConstants.AIRPORT_SELECTED) {
            btnLoadAirports.setEnabled(true);
            btnDeleteAirport.setEnabled(true);
            btnEditAirport.setEnabled(true);
            btnAddNewAirport.setEnabled(true);
            btnUpdateAirport.setEnabled(true);
            txtAirportCode.setEnabled(true);
            txtAirportCity.setEnabled(true);
            txtAirportCountry.setEnabled(true);
            txtAirportTimeZone.setEnabled(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpReturnOrOneway = new javax.swing.ButtonGroup();
        mainTabbedPane = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        LoadAircraft = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        btnDeleteAircraft = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        txtAircraftId = new javax.swing.JTextField();
        txtModel = new javax.swing.JTextField();
        txtecoSeat = new javax.swing.JTextField();
        busSeat = new javax.swing.JTextField();
        FirstSeat = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        OutputAircraft = new javax.swing.JList();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnLoadAirports = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstAirports = new javax.swing.JList();
        btnDeleteAirport = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtAirportCode = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtAirportCity = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtAirportCountry = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtAirportTimeZone = new javax.swing.JTextField();
        btnAddNewAirport = new javax.swing.JButton();
        btnUpdateAirport = new javax.swing.JButton();
        btnEditAirport = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstpassengers = new javax.swing.JList();
        LoadPassenger = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtFamilyName = new javax.swing.JTextField();
        txtGivenName = new javax.swing.JTextField();
        Country = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        PNumber = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        radRoundTrip = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel36 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstInboundFlights = new javax.swing.JList();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lstOutboundFlights = new javax.swing.JList();
        lblInboundFlights = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        txtDepartureDate = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtReturnDate = new javax.swing.JTextField();
        cmbClass = new javax.swing.JComboBox();
        spnAdults = new javax.swing.JSpinner();
        spninfant = new javax.swing.JSpinner();
        spnChildren = new javax.swing.JSpinner();
        btnProceed = new javax.swing.JButton();
        radOneWay = new javax.swing.JRadioButton();
        cmbOrigin = new javax.swing.JComboBox();
        cmbDestination = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        title = new javax.swing.JComboBox();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jComboBox19 = new javax.swing.JComboBox();
        jComboBox20 = new javax.swing.JComboBox();
        jComboBox21 = new javax.swing.JComboBox();
        jLabel44 = new javax.swing.JLabel();
        jRadioButton11 = new javax.swing.JRadioButton();
        jLabel45 = new javax.swing.JLabel();
        jRadioButton12 = new javax.swing.JRadioButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jComboBox22 = new javax.swing.JComboBox();
        jComboBox23 = new javax.swing.JComboBox();
        jComboBox24 = new javax.swing.JComboBox();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablePassengerInfo = new javax.swing.JTable();
        lblSelectedFlights = new javax.swing.JLabel();
        btnConfirm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CP1953 Project Database Demo");

        LoadAircraft.setText("Load All Aircrafts");
        LoadAircraft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadAircraftActionPerformed(evt);
            }
        });

        jButton11.setText("Add New Aircraft");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Update Aircraft");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        btnDeleteAircraft.setText("Remove");
        btnDeleteAircraft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAircraftActionPerformed(evt);
            }
        });

        jLabel50.setText("AirCraft ID:");

        jLabel51.setText("Model:");

        jLabel52.setText("Economy Seats:");

        jLabel53.setText("Business Seats:");

        jLabel54.setText("First Seats:");

        txtecoSeat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtecoSeatActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(OutputAircraft);

        jButton6.setText("Edit Aircraft");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(LoadAircraft, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDeleteAircraft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jButton11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 358, Short.MAX_VALUE)
                                .addComponent(jButton12))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel52)
                                    .addComponent(jLabel51)
                                    .addComponent(jLabel53)
                                    .addComponent(jLabel54)
                                    .addComponent(jLabel50))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAircraftId)
                                    .addComponent(FirstSeat)
                                    .addComponent(busSeat)
                                    .addComponent(txtModel)
                                    .addComponent(txtecoSeat))))
                        .addGap(37, 37, 37))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(LoadAircraft)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton11)
                            .addComponent(jButton12))
                        .addGap(26, 26, 26)
                        .addComponent(btnDeleteAircraft)
                        .addGap(56, 56, 56)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50)
                            .addComponent(txtAircraftId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(txtecoSeat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel53)
                            .addComponent(busSeat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel54)
                            .addComponent(FirstSeat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(114, 114, 114)
                        .addComponent(jButton6)))
                .addContainerGap(397, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Aircraft", jPanel8);

        btnLoadAirports.setText("Load Airports");
        btnLoadAirports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadAirportsActionPerformed(evt);
            }
        });

        lstAirports.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstAirportsValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(lstAirports);

        btnDeleteAirport.setText("Delete Airport");
        btnDeleteAirport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAirportActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Add/Update"));

        jLabel6.setText("Code:");

        jLabel9.setText("City:");

        jLabel10.setText("Country:");

        jLabel11.setText("Time Zone:");

        btnAddNewAirport.setText("Add New Airport");
        btnAddNewAirport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewAirportActionPerformed(evt);
            }
        });

        btnUpdateAirport.setText("Update Airport");
        btnUpdateAirport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateAirportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAirportTimeZone, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAirportCity, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                    .addComponent(txtAirportCountry)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtAirportCode, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(btnAddNewAirport)
                            .addGap(18, 18, 18)
                            .addComponent(btnUpdateAirport))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtAirportCity, txtAirportCode, txtAirportCountry, txtAirportTimeZone});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtAirportCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtAirportCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAirportCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtAirportTimeZone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddNewAirport)
                    .addComponent(btnUpdateAirport))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnEditAirport.setText("<html>Edit Airport &rarr;</html>");
        btnEditAirport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditAirportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLoadAirports)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDeleteAirport)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnEditAirport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnDeleteAirport, btnEditAirport});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLoadAirports)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(btnEditAirport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteAirport)
                .addContainerGap(605, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Airports", jPanel4);

        jScrollPane2.setViewportView(lstpassengers);

        LoadPassenger.setText("Load All Passenger");
        LoadPassenger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadPassengerActionPerformed(evt);
            }
        });

        jButton3.setText("Delet Passenger");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Add new Passenger");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update Passenger");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jLabel1.setText("Add/Update:");

        jLabel3.setText("Family Name:");

        jLabel4.setText("Given Name:");

        jLabel7.setText("Country:");

        btnEdit.setText("Edit Passenger");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jLabel8.setText("Pasport Number");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LoadPassenger)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(82, 82, 82)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(btnEdit)
                            .addGap(13, 13, 13)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel7)
                                .addComponent(jLabel4)
                                .addComponent(jLabel8))
                            .addGap(26, 26, 26)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtGivenName, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Country, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFamilyName, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(PNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(211, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoadPassenger)
                    .addComponent(jButton4)
                    .addComponent(btnUpdate))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtFamilyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtGivenName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(Country, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEdit)))
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addGap(27, 27, 27)
                .addComponent(jButton3)
                .addContainerGap(411, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Passenger_Admin", jPanel2);

        jPanel7.setBackground(java.awt.SystemColor.window);

        radRoundTrip.setText("Round Trip");

        jLabel2.setText("From:");

        jLabel5.setText("To:");

        jLabel24.setText("Return");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Guest Travel Class:");

        jLabel32.setText("Guests:");

        jLabel33.setText("Adults (12+)");

        jLabel34.setText("Children (2-11)");

        jLabel35.setText("Infants (0-2)");

        jLabel36.setText("Promotion Code:");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Matching Flights"));

        lstInboundFlights.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lstInboundFlights.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstInboundFlights.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstInboundFlightsValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(lstInboundFlights);

        jLabel12.setText("Outbound Flights");

        lstOutboundFlights.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lstOutboundFlights.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstOutboundFlights.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstOutboundFlightsValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(lstOutboundFlights);

        lblInboundFlights.setText("Inbound Flights");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(lblInboundFlights))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblInboundFlights)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSearch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtDepartureDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDepartureDate.setText("2/1/2016");

        jLabel13.setText("Departure:");

        txtReturnDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtReturnDate.setText("5/1/2016");

        cmbClass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbClass.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Economy", "Business", "First" }));

        spnAdults.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        spnAdults.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        spninfant.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        spninfant.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        spnChildren.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        spnChildren.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        btnProceed.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btnProceed.setText("Proceed to Booking");
        btnProceed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProceedActionPerformed(evt);
            }
        });

        radOneWay.setText("One Way");
        radOneWay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radOneWayActionPerformed(evt);
            }
        });

        cmbOrigin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbOrigin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "??? (??????????, ????????????????????????)" }));
        cmbOrigin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbOriginActionPerformed(evt);
            }
        });

        cmbDestination.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbDestination.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "??? (??????????, ????????????????????????)" }));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(254, Short.MAX_VALUE)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnProceed, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(cmbOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDepartureDate, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator5)
                            .addComponent(jSeparator6)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(radRoundTrip)
                                .addGap(49, 49, 49)
                                .addComponent(radOneWay))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addGap(77, 77, 77)
                                .addComponent(jLabel33)
                                .addGap(8, 8, 8)
                                .addComponent(spnAdults, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spnChildren, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel35)
                                .addGap(18, 18, 18)
                                .addComponent(spninfant, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addGap(18, 18, 18)
                                .addComponent(cmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radRoundTrip)
                    .addComponent(radOneWay))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(cmbOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(txtReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txtDepartureDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(cmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35)
                    .addComponent(spnAdults, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spninfant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnChildren, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProceed))
                .addGap(18, 18, 18)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Book", jPanel7);

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(102, 0, 0));
        jLabel37.setText("Enter your Passenger Details");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setText("Adult");

        jLabel39.setText("Title");

        jLabel40.setText("First Name*");

        jLabel41.setText("Last Name*");

        title.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mr.", "Mrs.", "Ms.", "Miss", "HE", "HH", "HRH" }));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(102, 0, 0));
        jLabel42.setText("Secure Fligth Information");

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel43.setText("Date of Birth:");

        jComboBox19.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Day" }));

        jComboBox20.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Month" }));

        jComboBox21.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Year" }));

        jLabel44.setText("Gender:");

        jRadioButton11.setText("Male");

        jRadioButton12.setText("Female");

        jCheckBox1.setText("I have the redress number");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(102, 0, 0));
        jLabel46.setText("Contact Information");

        jLabel47.setText("Phone");

        jLabel48.setText("Country Code");

        jLabel49.setText("Number");

        jComboBox22.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mobile" }));

        jComboBox23.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Business" }));

        jComboBox24.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Residence" }));

        tablePassengerInfo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tablePassengerInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(tablePassengerInfo);

        lblSelectedFlights.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblSelectedFlights.setText("Flight Info goes here");
        lblSelectedFlights.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnConfirm.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnConfirm.setText("Confirm");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator8)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addGap(60, 60, 60)
                                .addComponent(jRadioButton11))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jCheckBox1)
                                    .addComponent(jLabel43))
                                .addGap(43, 43, 43)
                                .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jComboBox21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButton12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel45))))
                    .addComponent(lblSelectedFlights, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1023, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConfirm))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel39)
                                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(56, 56, 56)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel40)
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(33, 33, 33)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel41)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBox24, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox22, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox23, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(64, 64, 64)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(81, 81, 81)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTextField13)
                                                .addComponent(jTextField12)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel48))
                                            .addGap(81, 81, 81)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel49)
                                                    .addGap(0, 0, Short.MAX_VALUE))
                                                .addComponent(jTextField14))))
                                    .addGap(21, 21, 21))))
                        .addGap(0, 496, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSeparator9)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jRadioButton11)
                    .addComponent(jLabel45)
                    .addComponent(jRadioButton12))
                .addGap(31, 31, 31)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel46)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(jLabel49)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblSelectedFlights, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(btnConfirm)
                .addContainerGap())
        );

        mainTabbedPane.addTab("Passenger", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainTabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainTabbedPane)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoadAirportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadAirportsActionPerformed

        List<Airport> airports = AirportAccessor.findAllAirports();
        airportsListModel.clear();
        for (Airport a : airports) {
            airportsListModel.addElement(a);
        }
        setAirportScreenState(FlightAppConstants.AIRPORTS_LOADED);

    }//GEN-LAST:event_btnLoadAirportsActionPerformed

    private void btnAddNewAirportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewAirportActionPerformed

        String code = txtAirportCode.getText();
        if (code.length() != 3) {
            JOptionPane.showMessageDialog(this, "Airport Code must be three letters", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String city = txtAirportCity.getText();
        String country = txtAirportCountry.getText();
        String timeZone = txtAirportTimeZone.getText();
        Airport a = new Airport(code, city, country, timeZone);
        boolean success = AirportAccessor.addAirport(a);
        if (success) {
            JOptionPane.showMessageDialog(this, "Airport '" + code + "' added.", "Successful Insert", JOptionPane.INFORMATION_MESSAGE);
            clearAirportTextFields();
            btnLoadAirports.doClick();
        } else {
            JOptionPane.showMessageDialog(this, "Airport '" + code + "' already exists ... not added!", "Duplicate Airport", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnAddNewAirportActionPerformed

    private void lstAirportsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstAirportsValueChanged

        setAirportScreenState(FlightAppConstants.AIRPORT_SELECTED);

    }//GEN-LAST:event_lstAirportsValueChanged

    private void btnDeleteAirportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAirportActionPerformed

        int pos = lstAirports.getSelectedIndex();
        Airport a = airportsListModel.get(pos);
        boolean success = AirportAccessor.deleteAirport(a);
        if (success) {
            JOptionPane.showMessageDialog(this, "Airport '" + a.getCode() + "' deleted.", "Successful Delete", JOptionPane.INFORMATION_MESSAGE);
            clearAirportTextFields();
            btnLoadAirports.doClick();
        } else {
            JOptionPane.showMessageDialog(this, "Airport '" + a.getCode() + "' not deleted!", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnDeleteAirportActionPerformed

    private void btnEditAirportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditAirportActionPerformed

        int pos = lstAirports.getSelectedIndex();
        Airport a = airportsListModel.get(pos);
        txtAirportCode.setText(a.getCode());
        txtAirportCity.setText(a.getCity());
        txtAirportCountry.setText(a.getCountry());
        txtAirportTimeZone.setText(a.getTimeZone());

    }//GEN-LAST:event_btnEditAirportActionPerformed

    private void btnUpdateAirportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateAirportActionPerformed

        String code = txtAirportCode.getText();
        if (code.length() != 3) {
            JOptionPane.showMessageDialog(this, "Airport Code must be three letters", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String city = txtAirportCity.getText();
        String country = txtAirportCountry.getText();
        String timeZone = txtAirportTimeZone.getText();
        Airport a = new Airport(code, city, country, timeZone);
        boolean success = AirportAccessor.updateAirport(a);
        if (success) {
            JOptionPane.showMessageDialog(this, "Airport '" + code + "' updated.", "Successful Update", JOptionPane.INFORMATION_MESSAGE);
            clearAirportTextFields();
            btnLoadAirports.doClick();
        } else {
            JOptionPane.showMessageDialog(this, "Airport '" + code + "' does not exist ... no change!", "Invalid Airport", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnUpdateAirportActionPerformed

    private void LoadAircraftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadAircraftActionPerformed
         List<Aircraft> aircrafts = AircraftAccessor.findAllAircraft();
        aircraftsListModel.clear();
        for (Aircraft e : aircrafts) {
            aircraftsListModel.addElement(e);
        }
        //setAirportScreenState(FlightAppConstants.AIRPORTS_LOADED);
        
        
        
        
        
        
//        String[] list1 = FileUtils.readIntoArray("aircraft.cvs");
//        aircrafts = new Aircraft[list1.length];
        //for (int i = 0; i < aircrafts.length; i++) {
            //String[] data = list1[i].split(",");
            //aircrafts[i] = new Aircraft(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]),Integer.parseInt(data[4]));
           // OutputAircraft.append("\n" + aircrafts[i].toString()+"/n");

        
    }//GEN-LAST:event_LoadAircraftActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        String code = txtAircraftId.getText();
        if (code.length() != 4) {
            JOptionPane.showMessageDialog(this, "Aircraft Code must be four letters", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String model = txtModel.getText();
        int EcoSeat = Integer.parseInt(txtecoSeat.getText());
        int BusSeat = Integer.parseInt(busSeat.getText());
        int firstSeat = Integer.parseInt(FirstSeat.getText());
        
        Aircraft a = new Aircraft(code, model, EcoSeat, BusSeat,firstSeat);
        boolean success = AircraftAccessor.updateAircraft(a);
        if (success) {
            JOptionPane.showMessageDialog(this, "Aircraft '" + code + "' updated.", "Successful Update", JOptionPane.INFORMATION_MESSAGE);
            clearAircraftTextFields();
            LoadAircraft.doClick();
        } else {
            JOptionPane.showMessageDialog(this, "Aircraft '" + code + "' does not exist ... no change!", "Invalid Aircraft", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void btnDeleteAircraftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAircraftActionPerformed
        int pos = OutputAircraft.getSelectedIndex();
        Aircraft b = aircraftsListModel.get(pos);
        boolean success = AircraftAccessor.deleteAircraft(b);
        if (success) {
            JOptionPane.showMessageDialog(this, "Aircraft '" + b.getAircraftID() + "' deleted.", "Successful Delete", JOptionPane.INFORMATION_MESSAGE);
            clearAircraftTextFields();
            LoadAircraft.doClick();
        } else {
            JOptionPane.showMessageDialog(this, "Airport '" + b.getAircraftID() + "' not deleted!", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteAircraftActionPerformed

    private void txtecoSeatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtecoSeatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtecoSeatActionPerformed

    private void LoadPassengerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadPassengerActionPerformed

        List<Passenger> passengers = PassengerAccessor.findAllPassengers();
        passengersListModel.clear();
        for (Passenger a : passengers) {
            passengersListModel.addElement(a);
        }
        //setAirportScreenState(FlightAppConstants.AIRPORTS_LOADED);

    }//GEN-LAST:event_LoadPassengerActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        String code = txtAircraftId.getText();
        if (code.length() != 4) {
            JOptionPane.showMessageDialog(this, "Aircraft Code must be four letters", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String model = txtModel.getText();
        int EcoSeat = Integer.parseInt(txtecoSeat.getText());
        int BusSeat = Integer.parseInt(busSeat.getText());
        int firstSeat = Integer.parseInt(FirstSeat.getText());
        
        Aircraft a = new Aircraft(code, model, EcoSeat, BusSeat,firstSeat);
        boolean success = AircraftAccessor.addAircraft(a);
        if (success) {
            JOptionPane.showMessageDialog(this, "Aircraft Id '" + code + "' added.", "Successful Insert", JOptionPane.INFORMATION_MESSAGE);
            clearAircraftTextFields();
            LoadAircraft.doClick();
        } else {
            JOptionPane.showMessageDialog(this, "Aircraft Id '" + code + "' already exists ... not added!", "Duplicate Aircraft", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int pos = OutputAircraft.getSelectedIndex();
        Aircraft a = aircraftsListModel.get(pos);
        txtAircraftId.setText(a.getAircraftID());
        txtModel.setText(a.getModel());
        txtecoSeat.setText("" + a.getEconomySeats());
        busSeat.setText("" +a.getBusinessSeats());
        FirstSeat.setText("" + a.getFirstSeats());
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int pos = lstpassengers.getSelectedIndex();
        Passenger a = passengersListModel.get(pos);
        boolean success = PassengerAccessor.deletePassenger(a);
        if (success) {
            JOptionPane.showMessageDialog(this, "Passenger '" + a.getGivenName() + "' deleted.", "Successful Delete", JOptionPane.INFORMATION_MESSAGE);
            clearPassengerTextFields();
            LoadPassenger.doClick();
        } else {
            JOptionPane.showMessageDialog(this, "Passenger '" + a.getGivenName() + "' not deleted!", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int pos = lstpassengers.getSelectedIndex();
        Passenger a = passengersListModel.get(pos);
        txtFamilyName.setText(a.getFamilyName());
        txtGivenName.setText(a.getGivenName());
        Country.setText(a.getPassengerCountry());
        PNumber.setText(a.getPasportNo());
    }//GEN-LAST:event_btnEditActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String pnumber = PNumber.getText();
        if (pnumber.length() != 9) {
            JOptionPane.showMessageDialog(this, "Passport Number be NINE letters", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String familyname = txtFamilyName.getText();
        String givenname = txtGivenName.getText();
        String country = Country.getText();
        
        Passenger a = new Passenger(pnumber, familyname, givenname, country);
        boolean success = PassengerAccessor.addPassenger(a);
        if (success) {
            JOptionPane.showMessageDialog(this, "Passport Number '" + pnumber + "' added.", "Successful Insert", JOptionPane.INFORMATION_MESSAGE);
            clearPassengerTextFields();
            LoadPassenger.doClick();
        } else {
            JOptionPane.showMessageDialog(this, "Passport Number '" + pnumber + "' already exists ... not added!", "Duplicate Passenger", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String PnUMBER = PNumber.getText();
        if (PnUMBER.length() != 9) {
            JOptionPane.showMessageDialog(this, "Passport Number must be nine letters", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String familyname = txtFamilyName.getText();
        String givenname = txtGivenName.getText();
        String country = Country.getText();
        
        Passenger a = new Passenger(PnUMBER, familyname, givenname, country);
        boolean success = PassengerAccessor.updatePassenger(a);
        if (success) {
            JOptionPane.showMessageDialog(this, "Passenger '" + familyname + "' updated.", "Successful Update", JOptionPane.INFORMATION_MESSAGE);
            clearPassengerTextFields();
            LoadPassenger.doClick();
        } else {
            JOptionPane.showMessageDialog(this, "Passenger '" + familyname + "' does not exist ... no change!", "Invalid Passenger", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void lstInboundFlightsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstInboundFlightsValueChanged

        btnProceed.setEnabled(true);
    }//GEN-LAST:event_lstInboundFlightsValueChanged

    private void lstOutboundFlightsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstOutboundFlightsValueChanged

        if (!returnFlight) {
            btnProceed.setEnabled(true);
        }
    }//GEN-LAST:event_lstOutboundFlightsValueChanged

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        returnFlight = radRoundTrip.isSelected();
        String origin = ((Airport) cmbOrigin.getSelectedItem()).getCode();
        String destination = ((Airport) cmbDestination.getSelectedItem()).getCode();
        if (!isValidDate(txtDepartureDate.getText())) {
            JOptionPane.showMessageDialog(this, "Invalid Departure Date!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String[] datePieces = txtDepartureDate.getText().split("/");
        String depDate = datePieces[2] + "-" + datePieces[1] + "-" + datePieces[0];
        int adults = (int) spnAdults.getValue();
        int children = (int) spninfant.getValue();
        String seatClass = cmbClass.getSelectedItem().toString();

        List<Flight> outboundFlights = FlightAccessor.search(origin, destination, depDate);
        Collections.sort(outboundFlights, new DepartureTimeComparator());

        outboundFlightsListModel.removeAllElements();
        inboundFlightsListModel.removeAllElements();

        List<Flight> inboundFlights = new ArrayList();
        if (returnFlight) {
            if (!isValidDate(txtReturnDate.getText())) {
                JOptionPane.showMessageDialog(this, "Invalid Departure Date!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            datePieces = txtReturnDate.getText().split("/");
            depDate = datePieces[2] + "-" + datePieces[1] + "-" + datePieces[0];
            inboundFlights = FlightAccessor.search(destination, origin, depDate);
            Collections.sort(inboundFlights, new DepartureTimeComparator());
        }

        if (outboundFlights.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No flights found for departure date.", "No flights", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (inboundFlights.isEmpty() && returnFlight) {
            JOptionPane.showMessageDialog(this, "No flights found for return date.", "No flights", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            outboundFlights = checkSeats(outboundFlights, adults, children, seatClass);
            inboundFlights = checkSeats(inboundFlights, adults, children, seatClass);
            if (outboundFlights.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No outbound flights with enough seats.", "No flights", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (inboundFlights.isEmpty() && returnFlight) {
                JOptionPane.showMessageDialog(this, "No inbound flights with enough seats.", "No flights", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                for (Flight f : outboundFlights) {
                    outboundFlightsListModel.addElement(f);
                }
                if (returnFlight) {
                    for (Flight f : inboundFlights) {
                        inboundFlightsListModel.addElement(f);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnProceedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProceedActionPerformed

        outboundFlight = (Flight) lstOutboundFlights.getSelectedValue();
        if (returnFlight) {
            inboundFlight = (Flight) lstInboundFlights.getSelectedValue();
        }
        int adults = (int) spnAdults.getValue();
        int children = (int) spnChildren.getValue();
        Object[][] data = new Object[adults + children][4];
        for (int i = 0; i < adults + children; i++) {
            String[] vals = {"", "", "", ""};
            data[i] = vals;
        }
        String[] columnNames = {"Given Names", "Family Name", "Passport Number", "Passport Country"};
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        tablePassengerInfo.setModel(tableModel);

        String text = "<html>" + outboundFlight.toString();
        if (returnFlight) {
            text += "<br>" + inboundFlight.toString();
        }
        text += "</html>";
        lblSelectedFlights.setText(text);
        tablePassengerInfo.getParent().setPreferredSize(new Dimension(1000, 600));
        tablePassengerInfo.getColumnModel().getColumn(0).setPreferredWidth(400);
        tablePassengerInfo.getColumnModel().getColumn(1).setPreferredWidth(200);
        tablePassengerInfo.getColumnModel().getColumn(2).setPreferredWidth(200);
        tablePassengerInfo.getColumnModel().getColumn(3).setPreferredWidth(200);

        mainTabbedPane.setSelectedIndex(4);
    }//GEN-LAST:event_btnProceedActionPerformed

    private void radOneWayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radOneWayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radOneWayActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed

        int rows = tablePassengerInfo.getRowCount();
        List<Passenger> passengers = new ArrayList();
        for (int i = 0; i < rows; i++) {
            String givenNames = tablePassengerInfo.getValueAt(i, 0).toString();
            String familyName = tablePassengerInfo.getValueAt(i, 1).toString();
            String passportNumber = tablePassengerInfo.getValueAt(i, 2).toString();
            String passportCountry = tablePassengerInfo.getValueAt(i, 3).toString();
            if (givenNames.length() > 0 && familyName.length() > 0 && passportNumber.length() > 0 && passportCountry.length() > 0) {
                passengers.add(new Passenger(passportNumber, passportCountry, familyName, givenNames));
            }
            else {
                JOptionPane.showMessageDialog(this, "You must enter all data for all passengers.", "Incomplete Data", JOptionPane.ERROR_MESSAGE);
                passengers.clear();
                break;
            }
        }

        // for now, just display the passengers and confirm in a popup
        // later we'll add the passengers to a booking
        if (!passengers.isEmpty()) {
            for (Passenger p : passengers) {
                System.out.println(p);
            }
            JOptionPane.showMessageDialog(this, "You entered " + passengers.size() + " passengers.", "Booking Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void cmbOriginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbOriginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbOriginActionPerformed
    private List<Flight> checkSeats(List<Flight> items, int adults, int children, String seatClass) {
        List<Flight> res = new ArrayList();

        for (Flight f : items) {
            int seats = 0;
            if (seatClass.equals(FlightAppConstants.ECONOMY)) {
                seats = f.getEconomySeatsAvailable();
            }
            else if (seatClass.equals(FlightAppConstants.BUSINESS)) {
                seats = f.getBusinessSeatsAvailable();
            }
            else if (seatClass.equals(FlightAppConstants.FIRST)) {
                seats = f.getFirstSeatsAvailable();
            }
            if ((adults + children) <= seats) {
                res.add(f);
            }
        }

        return res;
    }

    private boolean isValidDate(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() < 8) {
            return false;
        }
        String[] tokens = s.split("/");
        if (tokens.length != 3) {
            return false;
        }
        try {
            int day = Integer.parseInt(tokens[0]);
            int month = Integer.parseInt(tokens[1]);
            int year = Integer.parseInt(tokens[2]);
            if (day < 1 || day > 31 || month < 1 || month > 12 || year < 2015) {
                return false;
            }
            if (month == 2 || month == 4 || month == 6 || month == 9 || month == 11) {
                if (day == 31) {
                    return false;
                }
            }
            if (month == 2) {
                if (isLeapYear(year)) {
                    if (day > 29) {
                        return false;
                    }
                }
                else {
                    if (day > 28) {
                        return false;
                    }
                }
            }
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
    
    
    private boolean isLeapYear(int year) {
        boolean res;
        if (year % 400 == 0) {
            res = true;
        }
        else if (year % 100 == 0) {
            res = false;
        }
        else if (year % 4 == 0) {
            res = true;
        }
        else {
            res = false;
        }
        return res;
    }
    
    
    
    private void clearAirportTextFields() {
        txtAirportCode.setText("");
        txtAirportCity.setText("");
        txtAirportCountry.setText("");
        txtAirportTimeZone.setText("");
        txtAirportCode.grabFocus();
    }
    private void clearAircraftTextFields() {
        txtAircraftId.setText("");
        txtModel.setText("");
        txtecoSeat.setText("");
        busSeat.setText("");
        FirstSeat.setText("");
    }
    private void clearPassengerTextFields() {
        txtFamilyName.setText("");
        txtGivenName.setText("");
        Country.setText("");
        PNumber.setText("");
    }
    

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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Country;
    private javax.swing.JTextField FirstSeat;
    private javax.swing.JButton LoadAircraft;
    private javax.swing.JButton LoadPassenger;
    private javax.swing.JList OutputAircraft;
    private javax.swing.JTextField PNumber;
    private javax.swing.JButton btnAddNewAirport;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnDeleteAircraft;
    private javax.swing.JButton btnDeleteAirport;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEditAirport;
    private javax.swing.JButton btnLoadAirports;
    private javax.swing.JButton btnProceed;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateAirport;
    private javax.swing.JTextField busSeat;
    private javax.swing.JComboBox cmbClass;
    private javax.swing.JComboBox cmbDestination;
    private javax.swing.JComboBox cmbOrigin;
    private javax.swing.ButtonGroup grpReturnOrOneway;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox19;
    private javax.swing.JComboBox jComboBox20;
    private javax.swing.JComboBox jComboBox21;
    private javax.swing.JComboBox jComboBox22;
    private javax.swing.JComboBox jComboBox23;
    private javax.swing.JComboBox jComboBox24;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblInboundFlights;
    private javax.swing.JLabel lblSelectedFlights;
    private javax.swing.JList lstAirports;
    private javax.swing.JList lstInboundFlights;
    private javax.swing.JList lstOutboundFlights;
    private javax.swing.JList lstpassengers;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JRadioButton radOneWay;
    private javax.swing.JRadioButton radRoundTrip;
    private javax.swing.JSpinner spnAdults;
    private javax.swing.JSpinner spnChildren;
    private javax.swing.JSpinner spninfant;
    private javax.swing.JTable tablePassengerInfo;
    private javax.swing.JComboBox title;
    private javax.swing.JTextField txtAircraftId;
    private javax.swing.JTextField txtAirportCity;
    private javax.swing.JTextField txtAirportCode;
    private javax.swing.JTextField txtAirportCountry;
    private javax.swing.JTextField txtAirportTimeZone;
    private javax.swing.JTextField txtDepartureDate;
    private javax.swing.JTextField txtFamilyName;
    private javax.swing.JTextField txtGivenName;
    private javax.swing.JTextField txtModel;
    private javax.swing.JTextField txtReturnDate;
    private javax.swing.JTextField txtecoSeat;
    // End of variables declaration//GEN-END:variables
}
