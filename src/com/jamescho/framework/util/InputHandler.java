package com.jamescho.framework.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.jamescho.game.state.State;

/*
 * InputHandler��һ����������
 * ��ʵ����KeyListener��MouseListener�ӿ�
 */
public class InputHandler implements KeyListener, MouseListener{
	
	private State newCurrentState;//����һ���µ�State���͵�ʵ������
	//��������õĲ�����State����,��ζ�����һ������״̬���ݽ�������ʹnewCurrentState�õ����¡�
	public void setCurrentState(State currentState)
	{
		this.newCurrentState = currentState;
		System.out.println("����InputHandler���ڵĽ���״̬������" + newCurrentState);
	}
    
	/*
	 * ��������ʱ�������˷���
	 * mouseClicked(MouseEvent e)�����������¼������������еĲ���MouseEvent������¼��������е�����
	 * ���¡��ɿ����ƶ����ȶ���ʱ������������¼����Ӷ������������¼�����������ִ�з������ڵ���䡣
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		newCurrentState.onClick(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		newCurrentState.onKeyPress(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		newCurrentState.onKeyRelease(e);
	}

}
