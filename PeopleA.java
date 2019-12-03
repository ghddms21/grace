package LOGIN;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.*;


public class PeopleA extends JFrame{
	private JTextField NameInput, AgeInput, NumInput, AdressInput, MailInput;
	private JButton Save;
	public PeopleA() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ButtonListener listener=new ButtonListener();//�̺�Ʈ ����
		
		
		setTitle("�ּҷ� �߰�");
		setSize(450,450);

		
		
		JPanel p=new JPanel();
		p.setLayout(null);
		p.setBackground(new Color(205,247,189));
		
		JLabel Title=new JLabel("�ּҷ� �߰�");
		Title.setBounds(170,50,150,20);
		Title.setFont(new Font("Monospaced",Font.BOLD, 20));//�ּҷ� �߰� �� ����
		p.add(Title);
		
		JLabel Name=new JLabel("�̸�");
		Name.setBounds(50,100,50,25);
		p.add(Name);//�̸� �� ����
		
		NameInput=new JTextField(20);
		NameInput.setBounds(150,100,200,25);
		NameInput.addActionListener(listener);
		p.add(NameInput);//�̸� �Է� �ʵ� ���� �� �̺�Ʈ ó��
		
		JLabel Age=new JLabel("����");
		Age.setBounds(50,140,50,25);
		p.add(Age);//���� �� ����
		
		AgeInput=new JTextField(20);
		AgeInput.setBounds(150,140,200,25);
		AgeInput.addActionListener(listener);
		p.add(AgeInput);//���� �Է� �ʵ� ���� �� �̺�Ʈ ó��
		
		
		JLabel Num=new JLabel("��ȭ��ȣ(-����)");
		Num.setBounds(50,180,100,25);
		p.add(Num);//��ȭ��ȣ �� ����
		
		NumInput=new JTextField(20);
		NumInput.setBounds(150,180,200,25);
		p.add(NumInput);
		NumInput.addActionListener(listener);//��ȭ��ȣ �Է� �ʵ� ���� �� �̺�Ʈ ó��
		
		JLabel Adress=new JLabel("�ּ�");
		Adress.setBounds(50,220,100,25);
		p.add(Adress);//�ּ� �� ����
		
		AdressInput=new JTextField(20);
		AdressInput.setBounds(150,220,200,25);
		p.add(AdressInput);
		AdressInput.addActionListener(listener);//�ּ� �Է� �ʵ� ���� �� �̺�Ʈ ó��

		JLabel Mail =new JLabel("����");
		Mail.setBounds(50,260,100,25);
		p.add(Mail);//���� �� ����
		
		MailInput=new JTextField(20);
		MailInput.setBounds(150,260,200,25);
		p.add(MailInput);
		MailInput.addActionListener(listener);//���� �Է� �ʵ� ���� �� �̺�Ʈ ó��
		
			
		
		Save=new JButton("����");
		Save.setBounds(180,300,90,40);
		p.add(Save);
		Save.addActionListener(listener);
		Save.setBackground(Color.WHITE);//�����ư ���� �� �̺�Ʈ ó��
		
		
		
		add(p);
		
		setVisible(true);
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==Save) {
				try {
					String s;
					String[] array;
					BufferedReader User=new BufferedReader(new FileReader("C:\\Users\\Grace\\eclipse-workspace\\Diary\\�ּҷ�.txt"));
					
					BufferedWriter NUser=new BufferedWriter(new FileWriter("C:\\Users\\Grace\\eclipse-workspace\\Diary\\�ּҷ�.txt",true));//���� ���Ͽ� �̾�����
					NUser.write(NameInput.getText()+"/");
					NUser.write(AgeInput.getText()+"/");
					NUser.write(NumInput.getText()+"/");
					NUser.write(AdressInput.getText()+"/");
					NUser.write(MailInput.getText()+"\r\n");
					NUser.write("\r\n");
					User.close();
					NUser.close();
					JOptionPane.showMessageDialog(null, "�ּҷϿ� �߰��Ǿ����ϴ�.");
					dispose();
					
					
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "�ּҷ� ��Ͽ� �����Ͽ����ϴ�.");
				}
			}
		}
		
	}
}

