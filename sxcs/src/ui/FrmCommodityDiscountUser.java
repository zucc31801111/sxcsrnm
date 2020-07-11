package ui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.CommodityInformation;
import model.DiscountCommodity;
import model.DiscountInformation;
import model.FreshCategory;
import starter.SXCSUtil;
import util.BaseException;

public class FrmCommodityDiscountUser extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_Manager=new JMenu("个人信息管理");


	private Object tblDiscountInformationTitle[]=DiscountInformation.tblDiscountInformationTitle;
	private Object tblDiscountInformationData[][];
	DefaultTableModel tabDiscountInformationModel=new DefaultTableModel();
	private JTable dataTableDiscountInformation=new JTable(tabDiscountInformationModel);
	
	
	private Object tblDiscountCommodityTitle[]=DiscountCommodity.tblDiscountCommodityTitle;
	private Object tblDiscountCommodityData[][];
	DefaultTableModel tabDiscountCommodityModel=new DefaultTableModel();
	private JTable dataTableDiscountCommodity=new JTable(tabDiscountCommodityModel);
	
	private DiscountInformation curDiscountInformation=null;
	List<DiscountCommodity> allDiscountCommodity=null;
	List<DiscountInformation> allDiscountInformation=null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCommodityDiscountUser frame = new FrmCommodityDiscountUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void reloadDiscountInformationTable(){
		try {
			allDiscountInformation=SXCSUtil.commodityManager.loadDiscountInformation();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblDiscountInformationData =  new Object[allDiscountInformation.size()][DiscountInformation.tblDiscountInformationTitle.length];
		for(int i=0;i<allDiscountInformation.size();i++){
			for(int j=0;j<DiscountInformation.tblDiscountInformationTitle.length;j++)
				tblDiscountInformationData[i][j]=allDiscountInformation.get(i).getCell(j);
		}
		tabDiscountInformationModel.setDataVector(tblDiscountInformationData,tblDiscountInformationTitle);
		this.dataTableDiscountInformation.validate();
		this.dataTableDiscountInformation.repaint();
	}
	private void reloadCommodityInformationTabel(int planIdx){
		if(planIdx<0) return;
		curDiscountInformation=allDiscountInformation.get(planIdx);
		try {
			allDiscountCommodity=SXCSUtil.commodityManager.loadDiscountCommodity(curDiscountInformation);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblDiscountCommodityData =new Object[allDiscountCommodity.size()][DiscountCommodity.tblDiscountCommodityTitle.length];
		for(int i=0;i<allDiscountCommodity.size();i++){
			for(int j=0;j<DiscountCommodity.tblDiscountCommodityTitle.length;j++)
				tblDiscountCommodityData[i][j]=allDiscountCommodity.get(i).getCell(j);
		}
		
		tabDiscountCommodityModel.setDataVector(tblDiscountCommodityData,tblDiscountCommodityTitle);
		this.dataTableDiscountCommodity.validate();
		this.dataTableDiscountCommodity.repaint();
	}
	/**
	 * Create the frame.
	 */
	public FrmCommodityDiscountUser() {
		setFont(new Font("Dialog", Font.PLAIN, 30));
		setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setTitle("满折系统");
			
			 this.getContentPane().add(new JScrollPane(this.dataTableDiscountInformation), BorderLayout.CENTER);
			    this.dataTableDiscountInformation.addMouseListener(new MouseAdapter (){

			    	
			    	
					@Override
					public void mouseClicked(MouseEvent e) {
						int i=FrmCommodityDiscountUser.this.dataTableDiscountInformation.getSelectedRow();
						if(i<0) {
							return;
						}
						FrmCommodityDiscountUser.this.reloadCommodityInformationTabel(i);
					}
			    	
			    });
			    this.getContentPane().add(new JScrollPane(this.dataTableDiscountCommodity), BorderLayout.EAST);
			    
			    this.reloadDiscountInformationTable();	
			
			    this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		
	}

}
