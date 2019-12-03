package LOGIN;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;


import LOGIN.NewAccount;
import LOGIN.Board;

public class Diary extends JFrame{
	private JButton login, account;
	private JTextField IDInput;
	private JPasswordField PASSWORDInput;
	public Diary() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("login window");
		setSize(450,600);
		ButtonListener listener=new ButtonListener();
		ButtonListener3 listener2=new ButtonListener3();//��ư �̺�Ʈ ����
		
		JPanel J=new JPanel();
		J.setBackground(new Color(200,191,231));
		J.setLayout(null);//�г� ����
		
		JLabel Title=new JLabel("MY DIARY");
		Title.setBounds(100,50,300,200);
		Title.setFont(new Font("DialogInput",Font.BOLD|Font.ITALIC, 50));
		Title.setForeground(Color.WHITE);//���� ����
		J.add(Title);
	
		JLabel ID= new JLabel("I    D");
		ID.setBounds(100,285,120,50);
		ID.setFont(new Font("DialogInput",Font.PLAIN|Font.BOLD, 15));
		J.add(ID);//ID �� ����
		
		IDInput= new JTextField();
		IDInput.setBounds(180,300,100,20);
		J.add(IDInput);//I���Է� �ʵ� ����
		IDInput.addActionListener(listener);//ID�Է� �̺�Ʈ ó��
		
		JLabel PASSWORD=new JLabel("PASSWORD");
		PASSWORD.setBounds(100,340,100,20);
		PASSWORD.setFont(new Font("DialogInput",Font.PLAIN|Font.BOLD, 15));
		J.add(PASSWORD);//Password �� ����
		
		PASSWORDInput=new JPasswordField();
		PASSWORDInput.setBounds(180,340,100,20);
		J.add(PASSWORDInput);//Password field ����
		PASSWORDInput.addActionListener(listener);//password �Է� �̺�Ʈ ó��
		
			
		
		login=new JButton("LOGIN");
		login.setBounds(300,300,80,60);
		login.setBackground(Color.white); //Login ��ư ����
		login.setFont(new Font("DialogInput",Font.PLAIN|Font.BOLD, 15));
		J.add(login);
		login.addActionListener(listener);//�α��ι�ư �̺�Ʈ ó��
		
		account=new JButton("CREAT ACCOUNT");
		account.setBounds(160,400,150,30);
		account.setBackground(Color.white);
		account.setFont(new Font("DialogInput",Font.PLAIN|Font.BOLD, 15));//ȸ������ ��ư ����
		J.add(account);
		account.addActionListener(listener2);//ȸ������ �̺�Ʈ ó��
		
		
		
		JLabel image=new JLabel();
		ImageIcon icon=new ImageIcon("���̾.png");
		image.setIcon(icon);
		J.add(image);//���̾ �̹��� �߰�
		image.setBounds(75,90,100,100);
		
		add(J);//�ǳ� �߰�
		
		setVisible(true);
	}
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==login||e.getSource()==PASSWORDInput) {//�α��� ��ư�� �����ų� password input�� ����Ű�� ������
				try {
					int count=0;
					String s;
					String[] array;
					String I=IDInput.getText();
					String P=PASSWORDInput.getText();
					
					BufferedReader User=new BufferedReader(new FileReader("C:\\Users\\Grace\\eclipse-workspace\\Diary\\ȸ�����.txt"));
					while((s=User.readLine())!=null) {
						array=s.split("/");//ȸ������� ���̵�/���/��Ÿ������ �̷���� �־� ���̵� ����� ���� array[0], array[1]�� �Է�
						if(I.equals(array[0])&&P.equals(array[1])) {//���̵� ��� ��ġ ����
							JOptionPane.showMessageDialog(null, "�α��� �Ǿ����ϴ�.");
							count++;//���̵� ��ġ �Ǿ�����
							Board B=new Board();//���� â ����
							dispose();
							
						}
					}
					if(count!=1)JOptionPane.showMessageDialog(null, "�α��ο� �����Ͽ����ϴ�.");//��ġ�ϴ� ���̵� ���� ��� �α��� ����
					User.close();

				
				}catch(IOException e1) {
					e1.printStackTrace();}
			
			}
		}
	}
	private class ButtonListener3 implements ActionListener{//ȸ������ �̺�Ʈó��
		@Override
		public void actionPerformed(ActionEvent e) {
			NewAccount N=new NewAccount();//ȸ������â ���
		}
	}
	public static void main(String[] args) {
		Diary D=new Diary();//��ü ����
		
	}
}