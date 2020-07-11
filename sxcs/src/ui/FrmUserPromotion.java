package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Promotion;
import starter.SXCSUtil;
import util.BaseException;

public class FrmUserPromotion extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	   private Object tblPromotionTitle[]=Promotion.tblPromotionTitle;
		private Object tblPromotionData[][];
		DefaultTableModel tabPromotionModel=new DefaultTableModel();
		private JTable dataTablePromotion=new JTable(tabPromotionModel);
	
	List<Promotion> allPromotion=null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmUserPromotion frame = new FrmUserPromotion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
private void reloadPromotionTable(){
		
		try {
			allPromotion=SXCSUtil.commodityManager.loadPromotion();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblPromotionData =  new Object[allPromotion.size()][Promotion.tblPromotionTitle.length];
		for(int i=0;i<allPromotion.size();i++){
			for(int j=0;j<Promotion.tblPromotionTitle.length;j++)
				tblPromotionData[i][j]=allPromotion.get(i).getCell(j);
		}
		tabPromotionModel.setDataVector(tblPromotionData,tblPromotionTitle);
		this.dataTablePromotion.validate();
		this.dataTablePromotion.repaint();
	}
	
	/**
	 * Create the frame.
	 */
	public FrmUserPromotion() {

		setFont(new Font("Dialog", Font.PLAIN, 30));
		setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setTitle("限时促销列表");
			
			 
			FrmUserPromotion.this.reloadPromotionTable();
		    this.getContentPane().add(new JScrollPane(this.dataTablePromotion), BorderLayout.CENTER);

	}

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}

}
