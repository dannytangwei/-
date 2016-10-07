package com.jamescho.game.main;


/*
 * ����˼·��
 * 1.����JFrame����
 * 2.����JPanel������ӵ�����
 * 3.����Ŀ���ͼ���ļ�����ͼƬ��Դ��ӵ�resources����
 * 4.��com.jamescho.game.main���д���һ��Resources�࣬��resources���м���
 *   ͼƬ�����ҽ����Ǵ洢Ϊ�ɹ���Ϸ�������������ʵĵĹ��б�����
 * 5.����״̬���������桢��Ϸ���桢���ý���֮�����ת������״̬������State
 * 6.��com.jamescho.game.state����һ����Ϸ���ؽ���LoadState
 * 7.ͨ����Game���У���ӷ������Ӷ�����������Դ
 * 8.ʹ������Դ����ʾ����ͼ�񣬼���LoadState��ת����һ״̬�����棩��
 * 9.Ϊ��ʹÿһ��״̬����ת��Ϊ��һ��״̬�࣬������State�����У������ת������
 * 10.������Ϸ��Ҫͬʱִ�и�������������Ҫ��Ӷ��߳�
 * 11.��Game������Ӷ��̣߳�ִ����Ϸѭ��
 * 12.����������룬����InputHandler�������������JPanel��
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.BorderLayout;

import javax.swing.JButton;

public class GameMain {
	//����һ�����ⳣ��
    private static final String GAME_TITLE = "LoneBall";
	public static final int GAME_WIDTH = 1350;
	public static final int GAME_HEIGHT = 700;
	public static Game sGame; //����ʹ��public���η��������İ���������
	private static JButton pause = new JButton("��ͣ��Ϸ");
	private static JButton resume = new JButton("������Ϸ");
	//Timer timer = new Timer();
	public static void main(String[] args)
  	{   //��дnew JFrame();���֣�Ȼ����Ctrl+2 ͣ��һ���ٰ� L�������Զ�������߲��֡�
		JFrame frame = new JFrame(GAME_TITLE);//�������ڿ�ܣ������ñ���
		//�رմ���ʱ
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ô��ڲ��ɸı�ߴ��С���������ֶ��޸Ĵ��ڳߴ�
		frame.setResizable(false);
		//����Ϊ�ɼ�
		frame.setVisible(true);
		
		/*
		 * ��JPanel������ӵ����ڣ�
		 * �ȴ���Game����ע�⣺������������,
		 * 1.������Game������������GAME_WIDTH��Ȼ�����eclipse����ʾ����������
		 * 2.ͬ������д�ұ߲��֣�Ȼ�󰴿�ݼ�������߲���
		 * 3.����sGame,����Ctrl+1��ѡ��Convert local variable to field,����Ϊ��Ա����
		 */
		sGame = new Game(GAME_WIDTH, GAME_HEIGHT);
		frame.add(sGame);
		//Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
		//ʹ������ڴ�С�ʺ����������ѡ��С�Ͳ��֡�(�����С��������е�setPreferredSize��������)
		frame.pack();
		frame.setIconImage(Resources.iconimage);//������Ϸͼ��
		JPanel jPanel = new JPanel();
		/*jPanel.add(pause);
		jPanel.add(resume);
		frame.add(jPanel,BorderLayout.SOUTH);
		pause.addActionListener(e ->
		{
			sGame.isPause();  //��ͣ
		});
		resume.addActionListener(e ->
		{
			sGame.isResume();
		});*/
	}
	
}
