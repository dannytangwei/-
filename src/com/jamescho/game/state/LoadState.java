package com.jamescho.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.jamescho.game.main.Resources;

//LoadState���ʾ��Ϸ�ļ��ؽ���
//��com.jamescho.game.state���д���LoadState�࣬������չState��
public class LoadState extends State{

	//ע�⣬��д��public class LoadState extends State��һ�����ʱ��
    //����к�ɫ�»��ߵ�LoadState,�Զ����ɳ��󷽷���
	@Override
	public void init() {
		//����Resources���е���Դ
		Resources.load();//��Ϊload()��static���εľ�̬���������Կ�����Resources��ֱ�ӵ���
		System.out.println("���سɹ�");
	}

	@Override
	public void update() {
		// �̳и����״̬ת�䷽������LoadStateת����MenuState
		//������Game���е�setCurrentState()������ͬʱҲ������Game�е�currentState��ֵ
		setCurrentState(new MenuState());
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
     
	
	
}
