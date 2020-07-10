package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.CommodityPurchase;
import model.Promotion;
import starter.SXCSUtil;
import util.BaseException;

public class FrmPurchaseList extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPurchaseList frame = new FrmPurchaseList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private Object tblPurchaseTitle[]=CommodityPurchase.tblPurchaseTitle;
	private Object tblPurchaseData[][];
	DefaultTableModel tabPurchaseModel=new DefaultTableModel();
	private JTable dataTablePurchase=new JTable(tabPurchaseModel);
	
	List<CommodityPurchase> allPurchase=null;
	
   private void reloadPurchaseTable(){//这是测试数据，需要用实际数替换
		
		try {
			allPurchase=SXCSUtil.commodityManager.loadPurchase();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblPurchaseData =  new Object[allPurchase.size()][CommodityPurchase.tblPurchaseTitle.length];
		for(int i=0;i<allPurchase.size();i++){
			for(int j=0;j<CommodityPurchase.tblPurchaseTitle.length;j++)
				tblPurchaseData[i][j]=allPurchase.get(i).getCell(j);
		}
		tabPurchaseModel.setDataVector(tblPurchaseData,tblPurchaseTitle);
		this.dataTablePurchase.validate();
		this.dataTablePurchase.repaint();
	}
	
	
	public FrmPurchaseList() {
		setFont(new Font("Dialog", Font.PLAIN, 30));
		setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setTitle("商品采购表");
			 this.getContentPane().add(new JScrollPane(this.dataTablePurchase), BorderLayout.CENTER);
			FrmPurchaseList.this.reloadPurchaseTable();
	}

}
