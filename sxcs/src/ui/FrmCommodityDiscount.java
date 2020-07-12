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
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.DiscountCommodity;
import model.DiscountInformation;
import model.FreshCategory;
import starter.SXCSUtil;
import util.BaseException;

public class FrmCommodityDiscount extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_Manager=new JMenu("满折管理");
    private JMenuItem  menuItem_Flash=new JMenuItem("刷新");
    private JMenuItem  menuItem_DiscountInformationAdd =new JMenuItem("增加满折");
    private JMenuItem  menuItem_DiscountInformationDel =new JMenuItem("删除满折");
    private JMenuItem  menuItem_DiscountCommodityAdd =new JMenuItem("增加满折商品");
    private JMenuItem  menuItem_DiscountCommodityDel =new JMenuItem("删除满折商品");
    
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
					FrmCommodityDiscount frame = new FrmCommodityDiscount();
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
	private void reloadloadDiscountCommodityTabel(int planIdx){
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
	public FrmCommodityDiscount() {
		setFont(new Font("Dialog", Font.PLAIN, 30));
		setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setTitle("满折系统");
			
			menu_Manager.setFont(new Font("新宋体", Font.PLAIN, 15));
			menuItem_Flash.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Manager.add(menuItem_Flash);
		    menuItem_Flash.addActionListener(this);		    
		    menuItem_DiscountInformationAdd.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Manager.add(menuItem_DiscountInformationAdd);
		    menuItem_DiscountInformationAdd.addActionListener(this);		    
		    menuItem_DiscountInformationDel.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Manager.add(menuItem_DiscountInformationDel);
		    menuItem_DiscountInformationDel.addActionListener(this);		    
		    menuItem_DiscountCommodityAdd.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Manager.add(menuItem_DiscountCommodityAdd);
		    menuItem_DiscountCommodityAdd.addActionListener(this);	    
		    menuItem_DiscountCommodityDel.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Manager.add(menuItem_DiscountCommodityDel);
		    menuItem_DiscountCommodityDel.addActionListener(this);
		    menubar.add(menu_Manager);
		    this.setJMenuBar(menubar);
		 
			
			 this.getContentPane().add(new JScrollPane(this.dataTableDiscountInformation), BorderLayout.CENTER);
			    this.dataTableDiscountInformation.addMouseListener(new MouseAdapter (){	
			    	
					@Override
					public void mouseClicked(MouseEvent e) {
						int i=FrmCommodityDiscount.this.dataTableDiscountInformation.getSelectedRow();
						if(i<0) {
							return;
						}
						FrmCommodityDiscount.this.reloadloadDiscountCommodityTabel(i);
					}
			    	
			    });
			    this.getContentPane().add(new JScrollPane(this.dataTableDiscountCommodity), BorderLayout.EAST);
			    
			    this.reloadDiscountInformationTable();	
			
			    this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.menuItem_Flash){
			this.reloadloadDiscountCommodityTabel(0);
			this.reloadDiscountInformationTable();	
		}
		else if(e.getSource()==this.menuItem_DiscountInformationAdd){
			FrmCommodityDiscountAdd dlg=new FrmCommodityDiscountAdd();
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_DiscountCommodityAdd){
			if(this.curDiscountInformation==null) {
				JOptionPane.showMessageDialog(null, "请选择满折编号", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmDiscountcommodityAdd dlg=new FrmDiscountcommodityAdd();
			dlg.discountInformation=curDiscountInformation;
			dlg.setVisible(true);
		}
		
		else if(e.getSource()==this.menuItem_DiscountInformationDel) {
			if(this.curDiscountInformation==null) {
				JOptionPane.showMessageDialog(null, "请选择满折", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				SXCSUtil.commodityManager.deleteDiscountInformation(this.curDiscountInformation);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
    else if(e.getSource()==this.menuItem_DiscountCommodityDel) {
    	int i=FrmCommodityDiscount.this.dataTableDiscountCommodity.getSelectedRow();
		if(i<0) {
			JOptionPane.showMessageDialog(null, "请选择满折商品", "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			SXCSUtil.commodityManager.deleteDiscountCommodity(this.allDiscountCommodity.get(i));
		} catch (BaseException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}	
    	
    	
		}
			
		
	}

}
