package com.jamescho.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.jamescho.game.main.GameMain;

//�����״̬Ҳ��������������˼
//����һ�������״̬�ࣨ���ࣩ��Ϊ������״̬���棨���ࣩ�ṩһ��ͨ�õĽṹ
public abstract class State {
	
	 //����ת��һ���µĽ���ʱ����ʼ����Ҫʹ�õ���Ϸ����
     public abstract void init();
     //��Ϸѭ������ÿһ֡�ϵ��õ�ǰ״̬��update���������ڸ�����Ϸ����
     public abstract void update();
     //��Ϸѭ������ÿһ֡�ϵ��õ�ǰ״̬��render���������ڰ�ͼ����Ⱦ�����ƣ�����Ļ
     public abstract void render(Graphics g);
     //������¼�
     public abstract void onClick(MouseEvent e);
     //���¼����¼�
     public abstract void onKeyPress(KeyEvent e);
     //�ͷż����¼�
     public abstract void onKeyRelease(KeyEvent e);
     
     //�Զ���һ��״̬ת���ķ���
     public void setCurrentState(State newState){
    	 //��JFrame�������е�JPanel�������setCurrentState()����
    	 //�������һ��State���ӽ�����Ϊ�����������Դ��ݵ�������
    	 //֮���Խ����ת��������ΪsetCurrentState()��������Game���е��Ǹ�����
    	 //
    	 GameMain.sGame.setCurrentState(newState);
     }
}
