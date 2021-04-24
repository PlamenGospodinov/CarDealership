import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MainPanel extends JFrame implements ChangeListener{
	Connection conn=null;
	static int id=-1;
	static int row;
	static int selectedTab = 0;
	static int currentTab = 0;
	static String selected;
	PreparedStatement state = null;
	JTable brandTable = new JTable();
	JScrollPane scroller = new JScrollPane(brandTable);
	JTable carTable = new JTable();
	JScrollPane scrollerCar = new JScrollPane(carTable);
	JTabbedPane tab = new JTabbedPane();
	JPanel brandConfig = new JPanel();
	JPanel carConfig = new JPanel();
	JPanel salePanel = new JPanel();
	JPanel searchPanel = new JPanel();
	
	//panels for brandConfig Tab
	//upPanel for TextFields
	JPanel upPanel = new JPanel();
	//midPanel for the Buttons
	JPanel midPanel = new JPanel();
	//search panel
	JPanel searchPanelBrand = new JPanel();
	//downPanel for the results
	JPanel downPanel = new JPanel();
	JPanel blankPanel = new JPanel();
	//----------------------------
	
	//labels for brandConfig Tab
	JLabel carBrand = new JLabel("Въведи марка на автомобила: ");
	JLabel countryOfOrigin = new JLabel("Държава на производство: ");
	JLabel searchLabelBrand = new JLabel("Търсене по държава: ");
	
	//TextFields for brandConfig Tab
	JTextField carBrandTF = new JTextField();
	JTextField countryOfOriginTF = new JTextField();
	JTextField searchBrandTF = new JTextField();
	
	//buttons for brandConfig Tab
	JButton addBtn = new JButton("Добави");
	JButton deleteBtn = new JButton("Изтрий");
	JButton editBtn = new JButton("Промени");
	JButton searchBrandBtn = new JButton("Търси");
	
	//-----------------------------
	
	//panels for carConfig Tab
	//upPanel for TextFields
	JPanel upPanelCar = new JPanel();
	//midPanel for the Buttons
	JPanel midPanelCar = new JPanel();
	//downPanel for the results
	JPanel downPanelCar = new JPanel();
	
	//labels for carConfig
	JLabel brandCar = new JLabel("Избери марка автомобил: ");
	JLabel modelCar = new JLabel("Въведи модел на автомобил: ");
	JLabel yearCar = new JLabel("Въведи година на производство: ");
	JLabel priceCar = new JLabel("Въведи цена на автомобила: ");
	JLabel commentCar = new JLabel("Коментар: ");
	JLabel searchCar = new JLabel("Търси по цена: ");
	//TextFields and ComboBox of carConfig
	//Don't forget to link brands to comboBox later !!!!
	
	
	
	static ArrayList<String> brandList = DBBrandHelper.getBrandData(); 
	static String[] array = brandList.toArray(new String[brandList.size()]);
	//static JComboBox brandCombo = new JComboBox(array);
	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(array);
    JComboBox<String> brandCombo = new JComboBox<>(model);
    
	JTextField modelCarTF = new JTextField();
	JTextField yearCarTF = new JTextField();
	JTextField priceCarTF = new JTextField();
	JTextField commentTF = new JTextField();
	JTextField searchCarTF = new JTextField();
	
	//Buttons for carConfig
	JButton addBtnCar = new JButton("Добави");
	JButton deleteBtnCar = new JButton("Изтрий");
	JButton editBtnCar = new JButton("Промени");
	JButton searchCarBtn = new JButton("Търси");
	
	
	//method which checks which tab is opened
	public void stateChanged(ChangeEvent e) {
        JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
        selectedTab = tabbedPane.getSelectedIndex();
        currentTab = selectedTab;
    }
	
	
	public MainPanel() {
		
		this.setSize(800,800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//this.setLayout(new GridLayout(3,1));
		
		//brandConfig Code
		//-------------------------------------------------
		//configuration of upPanel in brandConfig
		brandConfig.setLayout(new GridLayout(4,1));
		upPanel.setLayout(new GridLayout(2,2));
		upPanel.add(carBrand);
		upPanel.add(carBrandTF);
		upPanel.add(countryOfOrigin);
		upPanel.add(countryOfOriginTF);
		brandConfig.add(upPanel);
		
		//Configuration of midPanel in brandConfig
		midPanel.setLayout(new GridLayout(3,1));
		midPanel.add(addBtn);
		midPanel.add(deleteBtn);
		midPanel.add(editBtn);
		brandConfig.add(midPanel);
		
		//buttons action listeners
		addBtn.addActionListener(new AddAction());
		deleteBtn.addActionListener(new DeleteAction());
		editBtn.addActionListener(new EditAction());
		//setting button colors
		addBtn.setBackground(Color.green);
		deleteBtn.setBackground(new Color(255,138,138));
		editBtn.setBackground(new Color(204,204,255));
		
		
		
		//Adding tabs to the mainPanel
		tab.add(brandConfig,"Конфигурация на марки");
		tab.add(carConfig,"Конфигурация на автомобили");
		tab.add(salePanel,"Продажби");
		tab.add(searchPanel,"Търсене по критерии");
		this.add(tab);
		
		//setting fontSize to bigger one
		carBrand.setFont(carBrand.getFont().deriveFont(15.0f));
		countryOfOrigin.setFont(countryOfOrigin.getFont().deriveFont(15.0f));
		
		
		//downPanel of brandConfig
		downPanel.add(scroller);
		brandConfig.add(downPanel);
		
		scroller.setPreferredSize(new Dimension(760,170));
		brandTable.setModel(DBBrandHelper.getAllData());
		brandTable.addMouseListener(new TableListener());
		//-------------------------------------------------
		
		//adding search textBox and label to searchPanelBrand
		searchPanelBrand.setLayout(new GridLayout(1,3));
		searchPanelBrand.add(searchLabelBrand);
		searchPanelBrand.add(searchBrandTF);
		searchPanelBrand.add(searchBrandBtn);
		brandConfig.add(searchPanelBrand);
		
		
		
		carConfig.setLayout(new GridLayout(3,1));
		//setting upPanel in carConfig
		upPanelCar.setLayout(new GridLayout(5,2));
		upPanelCar.add(brandCar);
		upPanelCar.add(brandCombo);
		upPanelCar.add(modelCar);
		upPanelCar.add(modelCarTF);
		upPanelCar.add(yearCar);
		upPanelCar.add(yearCarTF);
		upPanelCar.add(priceCar);
		upPanelCar.add(priceCarTF);
		upPanelCar.add(commentCar);
		upPanelCar.add(commentTF);
		carConfig.add(upPanelCar);
		//------------------------
		//midPanelCar.setLayout(new GridLayout(1,3));
		//setting some fonts and colors
		brandCar.setFont(brandCar.getFont().deriveFont(20.0f));
		modelCar.setFont(modelCar.getFont().deriveFont(20.0f));
		yearCar.setFont(yearCar.getFont().deriveFont(20.0f));
		priceCar.setFont(priceCar.getFont().deriveFont(20.0f));
		commentCar.setFont(commentCar.getFont().deriveFont(20.0f));
		addBtnCar.setFont(addBtnCar.getFont().deriveFont(25.0f));
		editBtnCar.setFont(editBtnCar.getFont().deriveFont(25.0f));
		deleteBtnCar.setFont(deleteBtnCar.getFont().deriveFont(25.0f));
		addBtnCar.setBackground(Color.green);
		deleteBtnCar.setBackground(new Color(255,138,138));
		editBtnCar.setBackground(new Color(204,204,255));
		midPanelCar.setLayout(new GridLayout(2,6));
		midPanelCar.add(addBtnCar);
		midPanelCar.add(editBtnCar);
		midPanelCar.add(deleteBtnCar);
		midPanelCar.add(searchCar);
		midPanelCar.add(searchCarTF);
		midPanelCar.add(searchCarBtn);
		carConfig.add(midPanelCar);
		downPanelCar.add(scrollerCar);
		carConfig.add(downPanelCar);
		//setting table of cars
		scrollerCar.setPreferredSize(new Dimension(760,230));
		searchCar.setFont(searchCar.getFont().deriveFont(20.0f));
		searchCarBtn.setFont(searchCarBtn.getFont().deriveFont(20.0f));
		carTable.setModel(DBCarHelper.getAllData());
		carTable.addMouseListener(new TableListener());
		
		//adding actionListeners to second tab buttons
		addBtnCar.addActionListener(new AddAction());
		
		tab.addChangeListener(this);
		this.setVisible(true);
	}
	
	
	//clears text fields in first form
	public void clearFirstForm() {
		carBrandTF.setText("");
		countryOfOriginTF.setText("");
		
	}
	
	//clears text fields in the second form
	public void clearSecondForm() {
		
		modelCarTF.setText("");
		yearCarTF.setText("");
		priceCarTF.setText("");
		commentTF.setText("");
	}
	
	//class with addBtn Action
	class AddAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(currentTab == 0) {
				conn = DBBrandHelper.getConnection();
				String sql = "insert into BRANDS values(null,?,?)";
				try {
					state = conn.prepareStatement(sql);
					state.setString(1, carBrandTF.getText());
					state.setString(2, countryOfOriginTF.getText());
					
					state.execute();
					brandTable.setModel(DBBrandHelper.getAllData());
					
					//getting all the brands again
					array = brandList.toArray(new String[brandList.size()]);
					model.addElement(carBrandTF.getText());
				    brandCombo.setSelectedItem(carBrandTF.getText());
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						//brandCombo = new JComboBox();
						
						state.close();
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				clearFirstForm();
			}
			else if(currentTab == 1) {
				conn = DBCarHelper.getConnection();
				String sql = "insert into CARS values(null,?,?,?,?,?)";
				try {
					state = conn.prepareStatement(sql);
					state.setString(1, brandCombo.getSelectedItem().toString());
					state.setString(2, modelCarTF.getText());
					state.setInt(3, Integer.parseInt(yearCarTF.getText()));
					state.setFloat(4, Float.parseFloat(priceCarTF.getText()));
					state.setString(5, commentTF.getText());
					
					state.execute();
					carTable.setModel(DBCarHelper.getAllData());
					/*brandTable.setModel(DBBrandHelper.getAllData());
					
					//getting all the brands again
					array = brandList.toArray(new String[brandList.size()]);
					model.addElement(carBrandTF.getText());
				    brandCombo.setSelectedItem(carBrandTF.getText());*/
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						//brandCombo = new JComboBox();
						
						state.close();
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				clearSecondForm();
			}
		}
		
	}
	
	
	//class with deleteBtn Action
	class DeleteAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			conn = DBBrandHelper.getConnection();
			String sql = "DELETE FROM BRANDS WHERE ID=?";
			try {
				
				state = conn.prepareStatement(sql);
				state.setInt(1, id);
				state.execute();
				
				
				brandTable.setModel(DBBrandHelper.getAllData());
				array = brandList.toArray(new String[brandList.size()]);
				model.removeElement(selected);
				
				id = -1;
			    
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	//class with editBtn Action
	class EditAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			conn = DBBrandHelper.getConnection();
			String sql = "UPDATE BRANDS SET BRAND = \'" + carBrandTF.getText() + "\', COUNTRY = \'"  + countryOfOriginTF.getText() + "\' WHERE ID=?;";
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, id);
				state.execute();
				id = -1;
				brandTable.setModel(DBBrandHelper.getAllData());
				array = brandList.toArray(new String[brandList.size()]);
				//int index = brandList.indexOf(selected);
				model.removeElement(selected);
				model.addElement(carBrandTF.getText());
			    brandCombo.setSelectedItem(carBrandTF.getText());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	class TableListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(currentTab == 0) {
				row = brandTable.getSelectedRow();
				selected = (String) brandTable.getValueAt(row, 1);
				id = Integer.parseInt(brandTable.getValueAt(row, 0).toString());
			}
			else if(currentTab == 1) {
				//continue from here
				row = carTable.getSelectedRow();
				selected = (String)carTable.getValueAt(row, 1);
			}
			
			
			
			if(e.getClickCount() == 2) {
			    if(currentTab == 0) {
			    	carBrandTF.setText(brandTable.getValueAt(row, 1).toString());
					countryOfOriginTF.setText(brandTable.getValueAt(row, 2).toString());
			    }
			    
			    
			}
			
		}

		//e.getClickCount()-for edit
		//if getClickCount() == 2 - edit
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
