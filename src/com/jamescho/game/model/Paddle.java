package com.jamescho.game.model;

import java.awt.Rectangle;

import com.jamescho.game.main.GameMain;

public class Paddle {
	//���嵲������꣬�Լ�����ߣ�
	//velY����Ϊ���ʣ���ʾ������ÿһ֡���ƶ����������������û�����ƵĻ��᲻ͣ���˶�����������
    private int x, y, width, height, velY;
    //����һ���߽���Σ������жϵ����������ײ
    private Rectangle rect;
    private final static int MOVE_SPEED_Y =12;
    
    //���幹����
    public Paddle(int x, int y, int width, int height)
    {
    	this.x = x;
    	this.y = y;
    	this.width = width;
    	this.height = height;
    	rect = new Rectangle(x, y, width, height);
    	velY = 0;
    }
    
    //�ƶ�����
    public void update(){
    	//��ÿһ֡�н�velY��y���һ�Σ���Ч�����ǽ������ƶ���һ��y����ֵΪ��y+velY����λ��
    	y += velY;
    	
    	if(y < 0)   //���Ƶ��岻���ƶ����������
    	{
    		y = 0;
    	}
    	else if (y + height > GameMain.GAME_HEIGHT)
    	{
    		y = GameMain.GAME_HEIGHT - height;
    	}
    	updateRect();//���߽�����ƶ�����ͬ��λ��
    }
    //ͬ�����¾��α߽�
    private void updateRect(){
    	rect.setBounds(x, y, width, height);
    }
    public void accelUp(){
    	//������ƶ��ٶ���ÿ֡-5
    	//velY = -5;
    	velY = -MOVE_SPEED_Y;//�ó�������������
    }
    public void accelDown(){
    	//velY = 5;
    	velY = MOVE_SPEED_Y;
    }
    public void stop(){
    	velY = 0;
    }
    //ʵ����������private���Σ�����getter���������������ȡPaddle�����ʵ������
    public int getX(){
    	return x;
    }
    public int getY(){
    	return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
    	return height;
    }
    public Rectangle getRect(){
    	return rect;
    }
}
