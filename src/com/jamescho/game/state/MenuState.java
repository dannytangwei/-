package com.jamescho.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.jamescho.game.main.Resources;

//�˵�����
public class MenuState extends State{

	@Override
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("����˵�����");
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Resources.welcome, 0, 0, null);
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("����������");
		//void com.jamescho.game.state.State.setCurrentState(State newState)
        //��ʱ��ʾ����Ļ����MenuState���棬��������������¼�����
		//�����Ļ�����˷�������ת����Ϸ���̽���
		setCurrentState(new PlayState());
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("�������˼���");
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("���ɿ��˼���");
	}

}
