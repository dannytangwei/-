package com.jamescho.game.model;

import java.awt.Rectangle;

import com.jamescho.framework.util.RandomNumberGenerator;
import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;

/*
 * �����
 * 1.Ball�еı���
 *    ����ʹ�С��
 *    �߽���Σ�
 *    ���ʣ�
 *    
 *  2.Ball��ķ���  
 *  
 *  
 */
public class Ball {
     
	private static final int VEL_X = 10;
	private int x, y, width, height,velX,velY;
	private Rectangle rect;
	
	public Ball(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		velX = VEL_X;
		velY = RandomNumberGenerator.getRandIntBetween(-4, 5);
		rect = new Rectangle(x, y, width, height);
	}
	public void update()
	{
		x += velX;
		y += velY;
		correctYCollisions();
		updateRect();
	}
	//�ж��Ƿ��������±߽磨�����������ж���ײ��
	private void correctYCollisions()//��Ϊ����Ҫ����������ã�����ѡ��private����
	{
		if(y < 0)
		{   //������ֻ��ͣ��һ֡��ʱ����ΪС����ÿ֡���ڲ�ͣ���˶�
			y = 0;
		}
		else if(y + height > GameMain.GAME_HEIGHT)
		{
			y = GameMain.GAME_HEIGHT-height;
		}
		else
		{
			return;//���û���������±߽磬������÷���
		}
		velY = -velY;//��ص�
		Resources.bounce.play();//����Resources�е�������Դ��������ײ������
	}
	//ͬ�����¾��α߽�
	private void updateRect()
	{
		rect.setBounds(x, y, width, height);
	}
	
	public void onCollideWith(Paddle p){
		//��������ײʱ�����ж����������ıߵ�����
		if(x < GameMain.GAME_WIDTH / 2)
		{   //�������������ģ������λ�øպ�λ������֮ǰ����������ԭ����Ϊ��
			//������ͣ��������֮����ײ��
			x = p.getX() + p.getWidth();
		}
		else
		{
			x = p.getX() - width;
		}
		velX = -velX;
		velY = RandomNumberGenerator.getRandIntBetween(-2, 3);
	}
	public boolean isDead()
	{
		return (x < 0 || x + width > GameMain.GAME_WIDTH);
	}
	public void reset()//���������»���С��
	{
		x = GameMain.GAME_WIDTH/2-60/2;
		y = 280;
		if(RandomNumberGenerator.getRandInt(2) ==1)
		{
			velX = VEL_X;
		}
		else{
			velX = -VEL_X;
		}
		
		velY = RandomNumberGenerator.getRandIntBetween(-4, 5);
	}
	public void increaseSpeed()//���ٷ���
	{
		velX += velX*0.111112 ;
		//velY = RandomNumberGenerator.getRandIntBetween(1, 2);
	}
	public void changeDirection()
	{
		velY = RandomNumberGenerator.getRandIntBetween(-10, 10);
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getWidty()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	public Rectangle getRect()
	{
		return rect;
	}
	
}
