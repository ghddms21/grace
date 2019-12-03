package LOGIN;
import java.awt.*;
import javax.swing.*;
import java.io.*;


import java.util.Calendar;
import java.util.Date;
import java.awt.event.*;

import LOGIN.PeopleA;



public class Board extends JFrame implements ActionListener {
	

	JLabel label_in, label_out, in_m, out_m, d_label ; //����� �� ����
	JTextField field_in, in_memo, field_out, out_memo, total; // ����� �ؽ�Ʈ�ʵ� ����
	JButton insert, insert2, view_in, view_out ; //����� ��ư ����
	JFrame J1; //������(����ο� �̿�)
	JTextArea t; //�޸�â ���
	
	private JButton PeopleAdd; //�ּҷ� �߰� ��ư
	private JTextArea People; //�ּҷ� ��� ��ư
	
	JButton[]lbl=new JButton[32];
	JPanel J=new JPanel();
	JPanel topPane = new JPanel();
	JButton prevBtn = new JButton("��");
	JButton nextBtn = new JButton("��");
	JLabel yearLbl = new JLabel("��");
	JLabel monthLbl = new JLabel("��");
	JComboBox<Integer> yearCombo = new JComboBox<Integer>();
	DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();
	JComboBox<Integer> monthCombo = new JComboBox<Integer>();
	DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();

	JPanel centerPane = new JPanel(new BorderLayout());//���Ϳ��� NORTH SOUTH EAST WEST CENTER ��ġ������ ����
	JPanel titlePane = new JPanel(new GridLayout(1, 7));//7������ ������ ��ġ���� �ۿ�
	String titleStr[] = {"��", "��", "ȭ", "��", "��", "��", "��"};
	JPanel datePane = new JPanel(new GridLayout(0, 7)); //1���� ��� 7���� ���� ������ ��ġ
	
	
	Calendar now;
	int year, month, date;


	public Board() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);	//�ڿ� ���� �� ����
		now = Calendar.getInstance();	//���� ��¥
		year = now.get(Calendar.YEAR);
		month = now.get(Calendar.MONTH)+1;
		date = now.get(Calendar.DATE);
		topPane.add(prevBtn);

		for(int i=year-100; i<=year+50; i++){ //����⵵���� 100���� 50���� �⵵���� yearModel�� �Ҵ�
			yearModel.addElement(i);
		}
		yearCombo.setModel(yearModel); //�޺��� yearModel ����
		yearCombo.setSelectedItem(year);	//���� �⵵ ����
		topPane.add(yearCombo); //��ܿ� �޺� ����
		topPane.add(yearLbl);//��ܿ� "��" ����

		for(int i=1; i<=12; i++){ //1��~12�� �޸𵨿� ����
			monthModel.addElement(i);
		}
		monthCombo.setModel(monthModel); // �޸��� month �޺��� �Ҵ�
		monthCombo.setSelectedItem(month);	//���� �� ����
		topPane.add(monthCombo); //��ܿ� �޺� ����
		topPane.add(monthLbl); //��ܿ� "��" ����
		topPane.add(nextBtn); //��ܿ� �������� �ѱ�� ��ư ����
		topPane.setBackground(new Color(100, 200, 200)); //��ܿ� ���� ����
		topPane.setBounds(20,20,350,40);
		J.add(topPane); //���������� ��ܹ� ����


//���� �κ�
		titlePane.setBackground(Color.white);
		for(int i=0; i<titleStr.length; i++){ //��ȭ��������� 7���� �迭
			JLabel lbl = new JLabel(titleStr[i], JLabel.CENTER); // ��ȭ��������� ���� ����
			if(i == 0){
				lbl.setForeground(Color.red);
			}else if(i == 6){
				lbl.setForeground(Color.blue);
			}
			titlePane.add(lbl); // ������ �迭 ���ߴ� �ٿ� ����
		}
		centerPane.add(titlePane, "North");//��¥ ���
		dayPrint(year, month);
		centerPane.add(datePane, "Center");//date�г��� �����гο� �߰��Ѵ�
		centerPane.setBounds(20,60,350,260);
		centerPane.setBackground(Color.white);
		J.add(centerPane);//�����г��� �����ӿ� �߰��Ѵ�
		
		datePane.setBackground(Color.WHITE);
	
		//����
		setSize(700,730);//���� ũ�� ����
		J.setLayout(null);
		J.setBackground(new Color(200,191,231));
		JButton back=new JButton("���� ����");
		back.setBackground(Color.orange);
		back.setBounds(575,625,100,30);
		J.add(back);
		back.addActionListener(e->{
			int R=(int)(Math.random()*256);
			int G=(int)(Math.random()*256);
			int B=(int)(Math.random()*256);
			Color color=new Color(R,G,B);
			J.setBackground(color);//���� �������� ����
		
		});
	
		
   	 	t = new JTextArea(); //�޸� ���� JTextArea ����
   	 	JScrollPane s2=new JScrollPane(t);//��ũ������ ����
		s2.setBounds(375,40,300,280);
   	 	J.add(s2);
   	 
 
   	 	
   	 	d_label=new JLabel("��");
   	 	d_label.setBounds(400,18,50,20);
   	 	d_label.setFont(new Font("SanSerif",Font.BOLD,15));
   	 	J.add(d_label);
		
		prevBtn.addActionListener(this);// �̺�Ʈ ����
		nextBtn.addActionListener(this);// �̺�Ʈ ����
		yearCombo.addActionListener(this);// �̺�Ʈ ����
		monthCombo.addActionListener(this);// �̺�Ʈ ����
		
   	 	
		//�ּҷ� ����
		PeopleAdd=new JButton("�ּҷ� �߰�");
		PeopleAdd.setBounds(200,610,150,30);
		PeopleAdd.setFont(new Font("DialogInput",Font.BOLD,16));
		PeopleAdd.setBackground(Color.white);
		J.add(PeopleAdd);
		PeopleAdd.addActionListener(e->{
			PeopleA A=new PeopleA();
		});
		
		People=new JTextArea();
		People.setEditable(false);
		People.setFont(new Font("DialogInput",Font.PLAIN,12));
		JScrollPane s=new JScrollPane(People);
		s.setBounds(20,330,330,270);
		
		try {
			BufferedReader in=new BufferedReader(new FileReader("�ּҷ�.txt"));
			String line;
			People.append("\n");
			while((line=in.readLine())!=null) {
				People.append("  "+line+" \r\n");
				
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		JButton refresh=new JButton("���ΰ�ħ");//�ּҷ� �߰��� �� �� ���ΰ�ħ�� ����� �Է��� ������ ����
		refresh.setBounds(20,610,100,30);
		refresh.setBackground(Color.WHITE);
		J.add(refresh);
		refresh.addActionListener(e->{
			try {
				BufferedReader in=new BufferedReader(new FileReader("�ּҷ�.txt"));
				String line;
				People.setText(null);
				People.append("\n");
				while((line=in.readLine())!=null) {
					People.append("  "+line+" \r\n");//JTextArea People�� ���ο� ���� �ٽ� ��
				}
			}catch(IOException e1) {
				e1.printStackTrace();
			}	
		});
		People.setCaretPosition(MAXIMIZED_HORIZ);//��ũ�� ��ġ �� ����
		J.add(s);
		//�ּҷ� ��
		

		
		
		//����� ����
	    ButtonListener3 listener3 = new ButtonListener3(); // ��ư ��ü ����
	    ButtonListener4 listener4 = new ButtonListener4();
	    
		in_m=new JLabel("�뵵"); //���� �뵵 �� ����
		J.add(in_m);
		in_m.setBounds(400,430,100,20);
		   
		out_m=new JLabel("�뵵"); //���� �뵵 �� ����
		J.add(out_m);
		out_m.setBounds(400,490,100,20);	    
		   
		in_memo=new JTextField(15); //���� �뵵 �Է� �ʵ� ����
		J.add(in_memo);
	    in_memo.setBounds(450,430,100,20);	       
		   
		out_memo=new JTextField(15); //���� �뵵 �Է� �ʵ� ����
		J.add(out_memo);
	    out_memo.setBounds(450,490,100,20);		    
		    
		label_in = new JLabel("����"); //���� �� ����  
	    J.add(label_in);
		label_in.setBounds(400,460,100,20);
		
	    label_out = new JLabel("����"); //���� �� ����
	    J.add(label_out);
		label_out.setBounds(400,520,100,20);
	   
	    field_in = new JTextField(10); //���� �Է� �ʵ� ����
	    J.add(field_in);
	    field_in.setBounds(450,460,100,20);	    
	    
	    field_out = new JTextField(10); //���� �Է� �ʵ� ����
	    J.add(field_out);
	    field_out.setBounds(450,520,100,20);	    
	    
	    insert = new JButton("���� �Է�"); //���� �Է� ��ư ����
	    J.add(insert);
	    insert.setBounds(560,440,100,30);
	    insert.setBackground(Color.WHITE); 
	   
	    insert2 =new JButton("���� �Է�"); //���� �Է� ��ư ����  
	    J.add(insert2);
	    insert2.setBounds(560,500,100,30);
	    insert2.setBackground(Color.WHITE);
	    
	    view_in=new JButton("���� ����"); //���� ���� ��ư ����
	    J.add(view_in);
	    view_in.setBounds(410,570,100,30);
	    view_in.setBackground(Color.WHITE);	    
	    
	    view_out=new JButton("���� ����"); //���� ���� ��ư ����
	    J.add(view_out);
	    view_out.setBounds(520,570,100,30);
	    view_out.setBackground(Color.WHITE);	       
	   
	    JLabel Money=new JLabel("<�����>");//����� �� ����
	    Money.setBounds(460,330,150,50);
	    Money.setFont(new Font("Monospaced",Font.BOLD, 25));
	    J.add(Money);
	    
	    JLabel Total=new JLabel("���� �ܰ�");//���� �ܰ� �� ���
	    Total.setBounds(420,390,100,20);
	    total=new JTextField(10);
	    J.add(Total);
	    J.add(total);
	    total.setBackground(Color.WHITE);
	    total.setBounds(500,390,100,20);
	    total.setEditable(false);
	    try{int sum=0;
		String [] array=null;
		String s1;
		BufferedReader User=new BufferedReader(new FileReader("�����ܰ�.txt"));
		while((s1=User.readLine())!=null) {
			array=s1.split("/");
		}
		sum=Integer.parseInt(array[0]);
		total.setText(""+sum);
	    }catch(Exception ex){
	    	ex.printStackTrace();
		}
	    
	    total.addActionListener(listener3);
	    out_memo.addActionListener(listener3);
	    in_memo.addActionListener(listener3);
	    insert.addActionListener(listener3);
	    insert2.addActionListener(listener3);
	    field_in.addActionListener(listener3);
	    field_out.addActionListener(listener3); 
	    view_in.addActionListener(listener4);
	    view_out.addActionListener(listener4);//����� ���� ��ư �� �ʵ� �̺�Ʈ ó��
	   //����� ��
	    
	    
	    JButton Logout=new JButton("�α׾ƿ�");
	    Logout.setBounds(470,625,100,30);
	    Logout.setBackground(Color.orange);
	    J.add(Logout);//�α׾ƿ� ��ư ����
	    Logout.addActionListener(e->{
	    	Diary D1=new Diary();
	    	dispose();
	    });
	    JButton Finish=new JButton("����");
	    Finish.setBounds(395,625,70,30);
	    Finish.setBackground(Color.orange);
	    J.add(Finish);//���� ��ư ����
	    Finish.addActionListener(e->{
	    	dispose();
	    });
	    add(J);		
		setVisible(true);

	}
	

//�̺�Ʈ ó�� �޼ҵ�!!!
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj instanceof JButton){
			JButton eventBtn = (JButton)obj;
			int yy = (Integer)yearCombo.getSelectedItem();
			int mm = (Integer)monthCombo.getSelectedItem();
			if(eventBtn.equals(prevBtn)){	//����
				if(mm==1){
					yy--; mm=12;
				}else{
					mm--;
				}				//prev��ư�� �����ÿ� 1������ 12���� �̵�
			}else if(eventBtn.equals(nextBtn)){	//������
				if(mm==12){
					yy++; mm=1;
				}else{
					mm++;
				}
			}// next��ư ������ 12���� 1���� �̵�

			yearCombo.setSelectedItem(yy);
			monthCombo.setSelectedItem(mm);
		}else if(obj instanceof JComboBox){	//�޺��ڽ� �̺�Ʈ �߻���
			createDayStart();
		}
	}

	public void createDayStart(){
		datePane.setVisible(false);	//�г� ����� �׷��� �Ŵ޸��� ���ڰ� �ٲ�
		datePane.removeAll();	//��¥ ����� �� �����
		dayPrint((Integer)yearCombo.getSelectedItem(), (Integer)monthCombo.getSelectedItem());
		datePane.setVisible(true);	//�г� �����	
		

	}	

	public void dayPrint(int y, int m){
		ButtonListener listener=new ButtonListener();
		Calendar cal = Calendar.getInstance();
		cal.set(y, m-1, 1);	//����� ù���� ��ü �����.
		int week = cal.get(Calendar.DAY_OF_WEEK);	//1�Ͽ� ���� ����	�Ͽ��� : 0
		int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);	//�� ���� ������ ��
		for(int i=1; i<week; i++){	//��¥ ��� �������� ���� ���
			datePane.add(new JLabel(" "));
		}
		;
		
		for(int i=1; i<=lastDate; i++){
			lbl[i] = new JButton(String.valueOf(i)); //��¥ ������ �� �ִ� ��ư ����
			lbl[i].setBackground(new Color(250,250,100));
			lbl[i].addActionListener(listener);
			if(y==now.get(Calendar.YEAR)&&m==now.get(Calendar.MONTH)+1&&i==now.get(Calendar.DATE)) {//���� ��¥ ��ư ���� �ٸ��� ��
				lbl[i].setForeground(Color.WHITE);
				lbl[i].setBackground(Color.ORANGE);
				}
			cal.set(y, m-1, i);
			int outWeek = cal.get(Calendar.DAY_OF_WEEK);
			if(outWeek==1){
				lbl[i].setForeground(Color.red); //�Ͽ����� �� ���ڻ� ����			
			}else if(outWeek==7){
				lbl[i].setForeground(Color.BLUE); //������� �� ���ڻ� ����
			}
			datePane.add(lbl[i]);
			}
	}	
	private class ButtonListener implements ActionListener{//�޸��� �̺�Ʈ ó��
		 public void actionPerformed(ActionEvent e) {
			 for(int i=0;i<32;i++) {
		           
		         if(e.getSource()==lbl[i]) {
		            int j = i;
		            t.setText("");
		            t.setFont(new Font("Serif",Font.PLAIN,14));
		        	 d_label.setText(i+"��");
		        	 JMenuBar menuBar=new JMenuBar();
		        	 JMenuItem save=new JMenuItem("�޸� ����");
		        	 JMenuItem load=new JMenuItem("�޸� �ҷ�����");
		        	 save.setBackground(Color.WHITE);
		        	 menuBar.add(save);
		        	 menuBar.add(load);
		        	 setJMenuBar(menuBar);
		        	 try {
		        	 String filename="C:\\Users\\Grace\\eclipse-workspace\\Diary\\DateMemo\\"+j+"date.txt"; //��μ��� �ʿ�
	                	FileReader r=new FileReader(filename);
	                	if(r.read()!=0) {
	                		JOptionPane.showMessageDialog(null, "����� �޸� �ֽ��ϴ�. Ȯ���� �ּ���.");
	                	}r.close();
		        	 }catch(Exception E) {JOptionPane.showMessageDialog(null, "����� �޸� �����ϴ�.");}//Ŭ���� ��¥ j�� ���� �޸� ���� ���翩�� Ȯ��,â ����
		         save.addActionListener(new ActionListener() {
		             public void actionPerformed(ActionEvent e) {
		                 try {
		                	String filename="C:\\Users\\Grace\\eclipse-workspace\\Diary\\DateMemo\\"+j+"date.txt"; //Ŭ���� ��¥ j�� �޸� ������ ������ �ּ�, �̸��� ��¥�� ����
		                	FileWriter w = new FileWriter(filename);
		                    String s =" "+ t.getText();
		                    s = s.replace("\n","\r\n");
		                    w.write(s); //�޸� ������ �Է¹ް� ������. �ε��� ���� ���� " "�� �����ϰ� ��. 
		                    w.close();
		                    JOptionPane.showMessageDialog(null, "����Ǿ����ϴ�.");
		                    } catch (Exception ex1) {
		                    	JOptionPane.showMessageDialog(null, "���忡 �����Ͽ����ϴ�.");
		                 }  
		            }    
		     }); 
		        load.addActionListener(new ActionListener() {
		             public void actionPerformed(ActionEvent e) {
		             }            
		         }); 
		         load.addActionListener(new ActionListener() {
		             
		             public void actionPerformed(ActionEvent e) {
		                             
		                String filename2="C:\\Users\\Grace\\eclipse-workspace\\Diary\\DateMemo\\"+j+"date.txt"; //Ŭ���� ��¥ j�� �޸𳻿��� �ҷ��� �ּ�  
		                //�׽�Ʈ�� ���� ��� ���� �ʿ�
		                  try {
		                       FileReader r = new FileReader( filename2 ); 
		                       if(r.read()==0) {  
		                          return ;//���� ������ �������� ���� ��� return
		                       }
		                       int k; 
		                       String s ="";
		                       for( ;  ; ) {
		                           k = r.read();// �޸� ������ ���� ���� k�� �������
		                           if( k == -1) break;
		                           s += (char)k; 
		                           t.setText(s); //k���� -1�� �ƴ϶�� ����Ÿ������ ��ȯ�� s�� �־� �� ���� �̸� �����ش�.
		                       }           
		                       r.close();
		                       
		                    } catch (Exception e2) {
		                    	JOptionPane.showMessageDialog(null, "���ο� �޸� �����ϼ���.");
		                    } 
		             	}            
		         	}); 
		         }
			 	}
			 }
		 }
	
	 
	   private class ButtonListener3 implements ActionListener{ // ��ư �̺�Ʈ ����
	       
	       public void actionPerformed(ActionEvent e) {
	          if(e.getSource()==insert) {
					try {
						int sum=0;
						String in_get = field_in.getText();
				        int s_add = Integer.parseInt(in_get);
						String s;
						String [] array=null;
						BufferedReader User=new BufferedReader(new FileReader("C:\\Users\\Grace\\eclipse-workspace\\Diary\\�����ܰ�.txt"));//���� �ܾ��� ������ ����ϱ� ���� txt���Ͽ� ���� ����
						while((s=User.readLine())!=null) {
							array=s.split("/");
						}
						sum=Integer.parseInt(array[0]);
						sum+=s_add;
						total.setText(""+sum);//���� �ܰ� ���
								
						BufferedWriter NUser=new BufferedWriter(new FileWriter("C:\\Users\\Grace\\eclipse-workspace\\Diary\\�����ܰ�.txt",false));//���� ��� �� �ܾ� �ٽ� �ۼ�
						NUser.write(sum+"/");
						in_memo.requestFocus();
						User.close();
						NUser.close();
						
						String s2;
						String[] array2;
						BufferedWriter NUser2=new BufferedWriter(new FileWriter("C:\\Users\\\\Grace\\eclipse-workspace\\Diary\\���Գ���.txt",true));//���Գ��� ����
						NUser2.write(in_memo.getText()+"/");
						NUser2.write(field_in.getText()+"\r\n");
						NUser2.close();
					 
					
					}
	          
					catch(Exception ex){
						ex.printStackTrace();
					}
				}
	          if(e.getSource()==insert2) {
					try {
						int sum=0;
						String out_get = field_out.getText();
				        int s_minus = Integer.parseInt(out_get);
						String s;
						String [] array=null;
						BufferedReader User=new BufferedReader(new FileReader("C:\\Users\\Grace\\eclipse-workspace\\Diary\\�����ܰ�.txt"));//���� �ܾ��� ������ ����ϱ� ���� txt���Ͽ� ���� ����
						while((s=User.readLine())!=null) {
							array=s.split("/");
						}
						sum=Integer.parseInt(array[0]);
						sum-=s_minus;
						total.setText(""+sum);//���� �ܰ� ���
								
						BufferedWriter NUser=new BufferedWriter(new FileWriter("C:\\Users\\Grace\\eclipse-workspace\\Diary\\�����ܰ�.txt",false));//���� ��� �� �ܾ� �ٽ� �ۼ�
						NUser.write(sum+"/");
						out_memo.requestFocus();
						User.close();
						NUser.close();
						
						String s2;
						String[] array2;
						BufferedWriter NUser2=new BufferedWriter(new FileWriter("C:\\Users\\Grace\\eclipse-workspace\\Diary\\���⳻��.txt",true));//���⳻�� ����
						NUser2.write(out_memo.getText()+"/");
						NUser2.write(field_out.getText()+"\r\n");
						NUser2.close();
						
					 }
	          
					catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
	       }
	   private class ButtonListener4 implements ActionListener{//ȸ������ �̺�Ʈó��
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==view_in) {//���Գ��� ��ư�� ������ �� ���Գ��� ������ �ҷ���
					try {
						J1=new JFrame();
						J1.setBounds(500,500,300,300);
						JTextArea view=new JTextArea();
						view.setEditable(false);
						view.setFont(new Font("DialogInput",Font.PLAIN,15));
						JScrollPane s=new JScrollPane(view, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);//��ũ�� ����
						s.setSize(300,300);
						J1.add(s);
						BufferedReader in=new BufferedReader(new FileReader("C:\\Users\\Grace\\eclipse-workspace\\Diary\\���Գ���.txt"));
						String line;
						while((line=in.readLine())!=null) {
							view.append("  "+line+" \r\n");
						}
						in.close();
						J1.setVisible(true);
					}catch(IOException e1) {
						e1.printStackTrace();}
				}
				else if(e.getSource()==view_out) {//���⳻�� ��ư�� ������ �� ���Գ��� ������ �ҷ���
					try{
						J1=new JFrame();
						J1.setBounds(500,500,300,300);
						JTextArea view=new JTextArea();
						view.setEditable(false);
						view.setFont(new Font("DialogInput",Font.PLAIN,15));
						JScrollPane s=new JScrollPane(view, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);//��ũ�� ����
						s.setSize(300,300);
						J1.add(s);
						
						BufferedReader in=new BufferedReader(new FileReader("C:\\Users\\Grace\\eclipse-workspace\\Diary\\���⳻��.txt"));
						String line;
						while((line=in.readLine())!=null) {
							view.append("  "+line+" \r\n");
						}
						J1.setVisible(true);
						in.close();
					}catch(IOException e1) {
						e1.printStackTrace();
					}
				
			}	
		}
  }
	
	   
}
