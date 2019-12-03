package LOGIN;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;


import java.awt.*;

public class NewAccount extends JFrame{
	private JButton Save;
	private JTextField NIDInput, NPASSWORDInput, NameInput, SchoolInput, MajorInput, NumInput, PhoneInput, MailInput;
	public NewAccount() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ButtonListener2 listener2=new ButtonListener2();//�̺�Ʈ ����
		
		
		setTitle("Creat Account");
		setSize(450,580);
		
		
		JPanel p=new JPanel();
		p.setLayout(null);
		p.setBackground(new Color(205,247,189));
		
		JLabel Title=new JLabel("ȸ������");
		Title.setBounds(200,50,150,20);
		Title.setFont(new Font("Monospaced",Font.BOLD, 20));//ȸ������ �� ����
		p.add(Title);
		
		JLabel NID=new JLabel("���̵�");
		NID.setBounds(60,100,50,25);
		p.add(NID);//ID�� ����
		
		NIDInput=new JTextField(20);
		NIDInput.setBounds(150,100,200,25);
		NIDInput.addActionListener(listener2);
		p.add(NIDInput);//ID�Է� �ʵ� ���� �� �̺�Ʈ ó��
		
		JLabel NPASSWORD=new JLabel("�н�����");
		NPASSWORD.setBounds(60,140,50,25);
		p.add(NPASSWORD);//�н����� �� ����
		
		NPASSWORDInput=new JTextField(20);
		NPASSWORDInput.setBounds(150,140,200,25);
		NPASSWORDInput.addActionListener(listener2);
		p.add(NPASSWORDInput);//�н����� �Է� �ʵ� ���� �� �̺�Ʈ ó��
		
		
		JLabel Name=new JLabel("�̸�");
		Name.setBounds(60,180,100,25);
		p.add(Name);//�̸� �� ����
		
		NameInput=new JTextField(20);
		NameInput.setBounds(150,180,200,25);
		p.add(NameInput);
		NameInput.addActionListener(listener2);//�̸� �Է� �ʵ� ���� �� �̺�Ʈ ó��
		
		JLabel School=new JLabel("�б�");
		School.setBounds(60,220,100,25);
		p.add(School);//�б� �� ����
		
		SchoolInput=new JTextField(20);
		SchoolInput.setBounds(150,220,200,25);
		p.add(SchoolInput);
		SchoolInput.addActionListener(listener2);//�б� �Է� �ʵ� ���� �� �̺�Ʈ ó��

		JLabel Major=new JLabel("����");
		Major.setBounds(60,260,100,25);
		p.add(Major);//���� �� ����
		
		MajorInput=new JTextField(20);
		MajorInput.setBounds(150,260,200,25);
		p.add(MajorInput);
		MajorInput.addActionListener(listener2);//���� �Է� �ʵ� ���� �� �̺�Ʈ ó��
		
		JLabel Num=new JLabel("�й�");
		Num.setBounds(60,300,100,25);
		p.add(Num);//�й� �� ����
		
		NumInput=new JTextField(20);
		NumInput.setBounds(150,300,200,25);
		p.add(NumInput);
		NumInput.addActionListener(listener2);//�й� �Է� �ʵ� ���� �� �̺�Ʈ ó��
		
		JLabel Phone=new JLabel("��ȭ��ȣ");
		Phone.setBounds(60,340,100,25);
		p.add(Phone);//��ȭ��ȣ �� ����
		
		PhoneInput=new JTextField(20);
		PhoneInput.setBounds(150,340,200,25);
		p.add(PhoneInput);
		PhoneInput.addActionListener(listener2);//��ȭ��ȣ �Է� �ʵ� ���� �� �̺�Ʈ ó��
		
		JLabel Mail=new JLabel("�̸���");
		Mail.setBounds(60,380,100,25);
		p.add(Mail);//�̸��� �� ����
		
		MailInput=new JTextField(20);
		MailInput.setBounds(150,380,200,25);
		p.add(MailInput);
		MailInput.addActionListener(listener2);	//�̸��� �Է� �ʵ� ���� �� �̺�Ʈ ó��
		
		Save=new JButton("����");
		Save.setBounds(180,450,90,40);
		Save.setBackground(Color.WHITE);
		p.add(Save);
		Save.addActionListener(listener2);//���� ��ư ���� �� �̺�Ʈ ó��
		
		
		
		add(p);
		
		setVisible(true);
	}
	
	private class ButtonListener2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==Save) {
				try {
					int count=0;
					String s;
					String[] array;	
					String NID=NIDInput.getText();
					BufferedReader User=new BufferedReader(new FileReader("C:\\Users\\Grace\\eclipse-workspace\\Diary\\ȸ�����.txt"));//��� ���� �ʿ�
					while((s=User.readLine())!=null) {
						array=s.split("/");
						if(NID.equals(array[0])) {
							JOptionPane.showMessageDialog(null, "�ߺ��� ���̵��Դϴ�.");//���̵� �ߺ� ���� Ȯ��
							count++;
							break;
						}
					}
					if(count==0) {
					BufferedWriter NUser=new BufferedWriter(new FileWriter("C:\\Users\\Grace\\eclipse-workspace\\Diary\\ȸ�����.txt",true));//��� ���� �ʿ�
					NUser.write(NIDInput.getText()+"/");
					NUser.write(NPASSWORDInput.getText()+"/");
					NUser.write(NameInput.getText()+"/");
					NUser.write(SchoolInput.getText()+"/");
					NUser.write(MajorInput.getText()+"/");
					NUser.write(NumInput.getText()+"/");
					NUser.write(PhoneInput.getText()+"/");
					NUser.write(MailInput.getText()+"\r\n");//�Է��� ���� 'ȸ�����.txt'�� ����
					User.close();
					NUser.close();
					JOptionPane.showMessageDialog(null, "ȸ�������� �����մϴ�.");
					dispose();}
					
					
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "ȸ�����Կ� �����Ͽ����ϴ�.");
				}
			}
		}
		
	}


}
