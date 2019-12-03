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
		ButtonListener3 listener2=new ButtonListener3();//버튼 이벤트 생성
		
		JPanel J=new JPanel();
		J.setBackground(new Color(200,191,231));
		J.setLayout(null);//패널 생성
		
		JLabel Title=new JLabel("MY DIARY");
		Title.setBounds(100,50,300,200);
		Title.setFont(new Font("DialogInput",Font.BOLD|Font.ITALIC, 50));
		Title.setForeground(Color.WHITE);//제목 생성
		J.add(Title);
	
		JLabel ID= new JLabel("I    D");
		ID.setBounds(100,285,120,50);
		ID.setFont(new Font("DialogInput",Font.PLAIN|Font.BOLD, 15));
		J.add(ID);//ID 라벨 생성
		
		IDInput= new JTextField();
		IDInput.setBounds(180,300,100,20);
		J.add(IDInput);//Iㅇ입력 필드 생성
		IDInput.addActionListener(listener);//ID입력 이벤트 처리
		
		JLabel PASSWORD=new JLabel("PASSWORD");
		PASSWORD.setBounds(100,340,100,20);
		PASSWORD.setFont(new Font("DialogInput",Font.PLAIN|Font.BOLD, 15));
		J.add(PASSWORD);//Password 라벨 생성
		
		PASSWORDInput=new JPasswordField();
		PASSWORDInput.setBounds(180,340,100,20);
		J.add(PASSWORDInput);//Password field 생성
		PASSWORDInput.addActionListener(listener);//password 입력 이벤트 처리
		
			
		
		login=new JButton("LOGIN");
		login.setBounds(300,300,80,60);
		login.setBackground(Color.white); //Login 버튼 생성
		login.setFont(new Font("DialogInput",Font.PLAIN|Font.BOLD, 15));
		J.add(login);
		login.addActionListener(listener);//로그인버튼 이벤트 처리
		
		account=new JButton("CREAT ACCOUNT");
		account.setBounds(160,400,150,30);
		account.setBackground(Color.white);
		account.setFont(new Font("DialogInput",Font.PLAIN|Font.BOLD, 15));//회원가입 버튼 생성
		J.add(account);
		account.addActionListener(listener2);//회원가입 이벤트 처리
		
		
		
		JLabel image=new JLabel();
		ImageIcon icon=new ImageIcon("다이어리.png");
		image.setIcon(icon);
		J.add(image);//다이어리 이미지 추가
		image.setBounds(75,90,100,100);
		
		add(J);//판넬 추가
		
		setVisible(true);
	}
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==login||e.getSource()==PASSWORDInput) {//로그인 버튼을 누르거나 password input에 엔터키를 누르면
				try {
					int count=0;
					String s;
					String[] array;
					String I=IDInput.getText();
					String P=PASSWORDInput.getText();
					
					BufferedReader User=new BufferedReader(new FileReader("C:\\Users\\Grace\\eclipse-workspace\\Diary\\회원명단.txt"));
					while((s=User.readLine())!=null) {
						array=s.split("/");//회원명단이 아이디/비번/기타정보로 이루어져 있어 아이디 비번을 각각 array[0], array[1]에 입력
						if(I.equals(array[0])&&P.equals(array[1])) {//아이디 비번 일치 여부
							JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
							count++;//아이디가 일치 되었는지
							Board B=new Board();//메인 창 생성
							dispose();
							
						}
					}
					if(count!=1)JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다.");//일치하는 아이디 없을 경우 로그인 실패
					User.close();

				
				}catch(IOException e1) {
					e1.printStackTrace();}
			
			}
		}
	}
	private class ButtonListener3 implements ActionListener{//회원가입 이벤트처리
		@Override
		public void actionPerformed(ActionEvent e) {
			NewAccount N=new NewAccount();//회원가입창 띄움
		}
	}
	public static void main(String[] args) {
		Diary D=new Diary();//객체 생성
		
	}
}