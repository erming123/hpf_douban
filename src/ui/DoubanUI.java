package ui;
/*
 * ��ѯ�Ի���
 * 
 */


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.json.JSONException;

import vo.DoubanVo;

import doubanAPI.DoubanAPI;

public class DoubanUI extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JComboBox jComboBox=null;
	JTextField jTextField=null;
	JButton jButton=null;
	public DoubanUI() {
		setLocation(300, 150);
		setSize(1000, 500);
		setVisible(true);
		setTitle("�����鼮��ѯϵͳ");
		init();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void init() {
		// TODO Auto-generated method stub
		setLayout(new FlowLayout());
		jComboBox=new JComboBox();
		jComboBox.addItem("�����");
		jComboBox.addItem("ISBN");
		jTextField=new JTextField(20);
		jButton=new JButton("����");
		add(jComboBox);
		add(jTextField);
		add(jButton);
		
		jButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String url;
		String result;
		if(jComboBox.getSelectedIndex()==0){
				int book_id;
				try {
					book_id=Integer.parseInt(jTextField.getText());
					url="https://api.douban.com/v2/book/"+book_id;
					DoubanAPI analysisData=new DoubanAPI();
					result=analysisData.getApiData(url);
					DoubanVo book=analysisData.GetBookInfo(result);
					JOptionPane.showMessageDialog(this, "������"+book.getTitle()+"\n"+
														"���ߣ�"+book.getAuthor()+"\n"+
														"�۸�"+book.getPrice()+"\n"+
														"ҳ����"+book.getPages()+"\n"+
														"�����磺"+book.getPublisher()+"\n"+
														"ISBN��"+book.getIsbn10()+"\n"+
														"ͼ����ܣ�"+book.getSummary()+"\n"+
														"ͼƬ��ַ:"+book.getImage(), "��ѯ���", JOptionPane.DEFAULT_OPTION);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, "û�ҵ�������������", "����", JOptionPane.WARNING_MESSAGE);
				} 
		}
		else{
			JOptionPane.showMessageDialog(this, "û�ҵ�������������", "����", JOptionPane.WARNING_MESSAGE);
		}
	}


}
