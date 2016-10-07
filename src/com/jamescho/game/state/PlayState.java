package com.jamescho.game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;
import com.jamescho.game.model.Ball;
import com.jamescho.game.model.Paddle;
import com.jamescho.game.model.Props;

//��Ϸ���̽���
//ע��:ÿ���½��涼Ҫ�ǵ���չState
public class PlayState extends State{

	private Paddle paddleLeft, paddleRight;//������������
	private static final int PADDLE_WIDTH = 15;
	private static final int PADDLE_HEIGHT = 100;
	private int playerScore = 0;//������ŷ����ı���
	private int playerScore2 = 0;
	private Font scoreFont;
	private Ball ball;
	private static final int BALL_DIAMETER = 30;
	private static final int PROPS_DIAMETER = 60;//����ֱ��
	private Props props, props2, props3;
	
	//��ʼ���µ�Paddle����
	public void init() {
		//��ʱ��Ϸ�ĵķֱ��ʣ���ĻͼƬ����800*450�����������ʾ
		//������ʹ��GameMain��GAME_WIDTH��GAME_HEIGHT�����ó�x��y�����꣬
		//�Ӷ����Ͷ���Ļ�ֱ��ʵ�������
	    paddleLeft = new Paddle(0, (GameMain.GAME_HEIGHT / 2) -(PADDLE_HEIGHT/2),
	    		PADDLE_WIDTH, PADDLE_HEIGHT);
	    paddleRight = new Paddle(GameMain.GAME_WIDTH-PADDLE_WIDTH, 
	    		(GameMain.GAME_HEIGHT / 2) -(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT);
	    scoreFont = new Font("SansSerif", Font.BOLD, 60);
	    ball = new Ball(GameMain.GAME_WIDTH/2-PROPS_DIAMETER/2, 280, BALL_DIAMETER,
	    		        BALL_DIAMETER);
	    props = new Props(GameMain.GAME_WIDTH/2-PROPS_DIAMETER/2,GameMain.GAME_HEIGHT / 2,
	    		          PROPS_DIAMETER,PROPS_DIAMETER);
	    props2 = new Props(GameMain.GAME_WIDTH/2-PROPS_DIAMETER/2,
	    		GameMain.GAME_HEIGHT/3 - PROPS_DIAMETER,PROPS_DIAMETER,PROPS_DIAMETER);
		          
	    props3 = new Props(GameMain.GAME_WIDTH/2-PROPS_DIAMETER/2,
	    	(GameMain.GAME_HEIGHT / 3)*2+PROPS_DIAMETER, PROPS_DIAMETER,PROPS_DIAMETER);
	}

	//��PlayState������ʾ����Ļʱ����Ϸѭ��ÿ֡�������update����,�������render����֮ǰ���ã�����
	public void update() {   
		//ͨ��ί�������µ���
		// ����Paddle�����update�������и��£����ܰ��ƶ����ͼ����ʾ������
		paddleLeft.update();
		paddleRight.update();
		ball.update();
		/*
		 * ͨ��intersects�����ж��������α߽��Ƿ��غ�,���ж���ײ
		 * 
		 */
		if(ball.getRect().intersects(paddleLeft.getRect())) //������ײ
		{
			playerScore++;
			
			ball.onCollideWith(paddleLeft);
			Resources.hit.play();
		}
		else if(ball.getRect().intersects(paddleRight.getRect()))
		{
			playerScore2++;
			ball.onCollideWith(paddleRight);
			Resources.hit.play();
		}
		else if(ball.isDead())
		{
			
			//���������߳��磬�������ҷ���-1
			if(ball.getX() < GameMain.GAME_WIDTH / 2)
			{
				playerScore -=1;
			}
			else//��������ұ߳��磬���ұ���ҷ���-1
			{
				playerScore2 -=1;
			}
			ball.reset();	
		}
		//��������������ʱ�������ı䷽����
		else if(ball.getRect().intersects(props.getRect()))
		{
			ball.changeDirection();
		}
		//��������������ʱ���������ٹ���
		else if(ball.getRect().intersects(props2.getRect()))
		{
			ball.increaseSpeed();
		}
		//��������������ʱ���������ٹ���
		else if(ball.getRect().intersects(props3.getRect()))
		{
			ball.increaseSpeed();
		}
	}
	
	//��PlayState������ʾ����Ļʱ����Ϸѭ��ÿ֡��������ⷽ����������������
	//�����ǻ���ͼ��������ͼ���ϣ�����Ƶ�ͼ��Ḳ���Ȼ��Ƶ�ͼ��
	public void render(Graphics g) {
		// ---------------------Draw Background---------------------------------
		//��ʱ�������ÿ֡������ã�Ϊ�˼�СCPU�������ɷ��Ȼ��ƺñ���ͼƬ�ŵ�Resources��
		//Ȼ����ͨ��PlayState��init������������һ�ξͺá�
		g.setColor(Resources.darkBlue);
		g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
		g.setColor(Resources.darkRed);//���ƺ�ɫ���������ұ߰�������
		g.fillRect(GameMain.GAME_WIDTH / 2, 0, GameMain.GAME_WIDTH / 2, GameMain.GAME_HEIGHT);
		// ---------------------Draw Separator Line------------------------------
		//��Ϊlineͼ��Ŀ�Ϊ4px,��������������Ҫ��ȥ2��ʹlineͼ��λ���м�
		//g.drawImage(Resources.line, (GameMain.GAME_WIDTH / 2)-2, 0, null);
		// ---------------------Draw Paddles-------------------------------------
		g.setColor(Color.GREEN);
		//ʹ��getY()�����õ�y��ֵ����ͼ��ԭ�򣺵��ƶ�����ʱ��y��ֵ��ı䣬�Ӷ����ݵ������ʱ���Ƹ���
		g.fillRect(paddleLeft.getX(), paddleLeft.getY(), PADDLE_WIDTH, paddleLeft.getHeight());
		g.fillRect(paddleRight.getX(), paddleRight.getY(), paddleRight.getWidth(), paddleRight.getHeight());
		// ---------------------Draw Ball-------------------------------------
		g.setColor(Color.WHITE);
		g.fillOval(ball.getX(), ball.getY(), ball.getWidty(), ball.getHeight());
		// ---------------------Draw Score-------------------------------------
        g.setFont(scoreFont);
        //��Ϊ�÷���Ҫ���һ����������String���ͣ����������ַ��������
        //1.String.valueOf(playerScore)  2."" + playerScore,���ֻᵼ���ڴ�й¶������
        g.drawString(String.valueOf(playerScore), 20, 60);
        g.drawString(String.valueOf(playerScore2), GameMain.GAME_WIDTH-80, 60);
        // ---------------------Draw Props-------------------------------------
        g.setColor(Color.red);
		g.drawOval(GameMain.GAME_WIDTH/2-PROPS_DIAMETER/2,GameMain.GAME_HEIGHT / 2,
		          PROPS_DIAMETER,PROPS_DIAMETER);
		g.drawOval(GameMain.GAME_WIDTH/2-PROPS_DIAMETER/2,
	    		GameMain.GAME_HEIGHT/3 - PROPS_DIAMETER,PROPS_DIAMETER,PROPS_DIAMETER);
		g.drawOval(GameMain.GAME_WIDTH/2-PROPS_DIAMETER/2,
		    	(GameMain.GAME_HEIGHT / 3)*2+PROPS_DIAMETER, PROPS_DIAMETER,PROPS_DIAMETER);
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			//paddleLeft.accelUp();
			paddleRight.accelUp();
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			//paddleLeft.accelDown();
			paddleRight.accelDown();
		}
		else if(e.getKeyCode() == KeyEvent.VK_W)
		{
			paddleLeft.accelUp();
		}
		else if(e.getKeyCode() == KeyEvent.VK_X)
		{
			paddleLeft.accelDown();
		}
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		// ��һ���ܹؼ������ɿ�����ʱ����ʹ����Ϊ0.��ֹͣ�˶���
		//�����°����󵲰��ͣ��������
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN||
				e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_X)
		{
			paddleLeft.stop();
			paddleRight.stop();
		}
	}
    
	
}
