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

//游戏过程界面
//注意:每个新界面都要记得扩展State
public class PlayState extends State{

	private Paddle paddleLeft, paddleRight;//定义两个挡板
	private static final int PADDLE_WIDTH = 15;
	private static final int PADDLE_HEIGHT = 100;
	private int playerScore = 0;//用来存放分数的变量
	private int playerScore2 = 0;
	private Font scoreFont;
	private Ball ball;
	private static final int BALL_DIAMETER = 30;
	private static final int PROPS_DIAMETER = 60;//道具直径
	private Props props, props2, props3;
	
	//初始化新的Paddle变量
	public void init() {
		//此时游戏的的分辨率（屏幕图片）是800*450，挡板居中显示
		//还可以使用GameMain的GAME_WIDTH和GAME_HEIGHT常量得出x、y的坐标，
		//从而降低对屏幕分辨率的依赖性
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

	//当PlayState界面显示在屏幕时，游戏循环每帧都会调用update方法,在下面的render方法之前调用！！！
	public void update() {   
		//通过委托来更新挡板
		// 调用Paddle对象的update方法进行更新，才能把移动后的图像显示出来！
		paddleLeft.update();
		paddleRight.update();
		ball.update();
		/*
		 * 通过intersects方法判断两个矩形边界是否重合,即判断碰撞
		 * 
		 */
		if(ball.getRect().intersects(paddleLeft.getRect())) //处理碰撞
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
			
			//如果球在左边出界，则左边玩家分数-1
			if(ball.getX() < GameMain.GAME_WIDTH / 2)
			{
				playerScore -=1;
			}
			else//如果球在右边出界，则右边玩家分数-1
			{
				playerScore2 -=1;
			}
			ball.reset();	
		}
		//当碰到道具气球时，启动改变方向功能
		else if(ball.getRect().intersects(props.getRect()))
		{
			ball.changeDirection();
		}
		//当碰到道具气球时，启动加速功能
		else if(ball.getRect().intersects(props2.getRect()))
		{
			ball.increaseSpeed();
		}
		//当碰到道具气球时，启动加速功能
		else if(ball.getRect().intersects(props3.getRect()))
		{
			ball.increaseSpeed();
		}
	}
	
	//当PlayState界面显示在屏幕时，游戏循环每帧都会调用这方法！！！！！！！
	//现在是绘制图像到离屏的图像上，后绘制的图像会覆盖先绘制的图像。
	public void render(Graphics g) {
		// ---------------------Draw Background---------------------------------
		//此时这个背景每帧都会调用，为了减小CPU负担，可否先绘制好背景图片放到Resources类
		//然后再通过PlayState的init（）方法加载一次就好。
		g.setColor(Resources.darkBlue);
		g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
		g.setColor(Resources.darkRed);//绘制红色背景覆盖右边半张桌面
		g.fillRect(GameMain.GAME_WIDTH / 2, 0, GameMain.GAME_WIDTH / 2, GameMain.GAME_HEIGHT);
		// ---------------------Draw Separator Line------------------------------
		//因为line图像的宽为4px,所以下面坐标需要减去2，使line图像位于中间
		//g.drawImage(Resources.line, (GameMain.GAME_WIDTH / 2)-2, 0, null);
		// ---------------------Draw Paddles-------------------------------------
		g.setColor(Color.GREEN);
		//使用getY()方法得到y的值来绘图的原因：当移动挡板时，y的值会改变，从而传递到这里，及时绘制更新
		g.fillRect(paddleLeft.getX(), paddleLeft.getY(), PADDLE_WIDTH, paddleLeft.getHeight());
		g.fillRect(paddleRight.getX(), paddleRight.getY(), paddleRight.getWidth(), paddleRight.getHeight());
		// ---------------------Draw Ball-------------------------------------
		g.setColor(Color.WHITE);
		g.fillOval(ball.getX(), ball.getY(), ball.getWidty(), ball.getHeight());
		// ---------------------Draw Score-------------------------------------
        g.setFont(scoreFont);
        //因为该方法要求第一个参数得是String类型，所以有两种方法解决：
        //1.String.valueOf(playerScore)  2."" + playerScore,这种会导致内存泄露！！！
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
		// 这一步很关键，即松开按键时，会使速率为0.即停止运动。
		//否则按下按键后挡板就停不下来了
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN||
				e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_X)
		{
			paddleLeft.stop();
			paddleRight.stop();
		}
	}
    
	
}
