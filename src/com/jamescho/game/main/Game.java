package com.jamescho.game.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.jamescho.framework.util.InputHandler;
import com.jamescho.game.state.LoadState;
import com.jamescho.game.state.State;

public class Game extends JPanel implements Runnable {
	
     private int gameWidth;//游戏窗口的宽度
     private int gameHeight;
     private volatile State currentState;//记录当前游戏界面
     private Image game;
     private Thread gameThread;
     private volatile boolean running;
     private Image gameImage;
     private InputHandler inputHandler;
     private volatile boolean isPause = false;
     
     //创建一个自定义的构造器,它有两个参数
     public Game(int gameWidth, int gameHeight){
    	 
    	this.gameWidth = gameWidth;//为上面的实例变量初始化赋值
    	this.gameHeight = gameHeight;
    	
    	//调用属于JPanel的4个方法（通过继承而可以使用）
    	
        /*void javax.swing.JComponent.setPreferredSize(Dimension preferredSize)说明这
         * 是JPanel继承自JComponent的方法，用来为组件设置尺寸大小；
         * 其叁数是Dimension类型的，继续在API文档中查询Dimension的构造器用法，从而创建参数
         */
    	setPreferredSize(new Dimension(gameWidth, gameHeight));
    	//设置背景颜色
    	setBackground(Color.BLACK);
    	/*
    	 * 怎么为一个不熟悉的类输入参数呢?
    	 * 首先查询API文档，得知public void setFocusable(boolean focusable)方法的参数如下：
    	 * Parameters:focusable - indicates whether this Component is focusable
    	 * 即，指示是否focusable该组件；
    	 * 在写完setFocusable这个类名时按下提示快捷键 Alt+/,然后选中所需方法按回车键，
    	 * 就会出现一个选择参数的界面，从而可以找到true或者isFocusable()
    	 * 可百度查询focusable（聚焦）的用法，做的API文档与百度的结合使用。
    	 */
    	setFocusable(true);//设置聚焦
    	requestFocus(); //请求聚焦
    	/*
    	 * 上面两条语句意味着键盘事件和按钮事件可供Game对象使用，
    	 * 即开始接受用户输入（以键盘事件和鼠标事件的形式）
    	 */
    	
     }
     
     
     public void setCurrentState(State newState)
 	 {
 		System.gc();//清理未使用的对象
 		//多态，任何State类的子类都可以作为方法的参数
 		newState.init();//加载资源而不是显示资源
 		currentState = newState;//把当前界面设置为currentState变量，这个变量决定了图像绘制
 		//com.jamescho.framework.util.InputHandler.setCurrentState(State currentState)
 		//把当前的界面状态（现在是MenuState）传递给IntputHandler类，
 		//从而使IntputHandler类中的currentState.onLick(e);可以得到更新，联系上MenuState界面
 		inputHandler.setCurrentState(currentState);
 	 }
     
     //当Game对象成功添加到JFrame中时，会自动调用此方法！！！！！！！！！
     //下面的Override指定下面的方法必须重写父类方法
     @Override
     public void addNotify(){
    	 //使这个容器可显示的通过连接到本机屏幕资源
    	super.addNotify();
    	/*
    	 * 为什么这条语句不能放在setCurrentState(new LoadState());后面呢？？？？？？？？？？
    	 * 因为要先通过initInput方法创建InputHandler对象，
    	 * setCurrentState()方法中的inputHandler.setCurrentState(currentState);语句才能运行
    	 * 当程序运行到此语句时，调用生成InputHandler类的对象，添加监听器到Panel
    	 */
    	initInput();
    	//调用LoadState中的init()方法加载资源，并把LoadState状态界面赋给currentState
    	setCurrentState(new LoadState());
    	initGame();//自定义方法添加游戏线程
    	
     }
     
     /* 添加游戏线程：
      * 写完gameThread = new Thread(this,"Game Thread");时
      * 程序会报错，因为这里this指的是Game，而Thread构造方法要
      * 的是Thread(Runnable target,String name),因Runnable是一个接口，
      * 所以可以使Game类实现Runable接口，并实现run()方法
      */
     private void initGame(){
    	 running = true;
    	 gameThread = new Thread(this,"Game Thread");
    	 gameThread.start();
     }

     //实现游戏循环：更新和渲染
     /*
      * 更新只要让currentState调用其updata()方法即可。
      * 渲染的基本步骤：
      * 1.准备一个离屏的空的图像
      * 2.将currentState中所有的游戏对象渲染（绘制）到这个图像
      * 3.将完成后的离屏图像绘制到屏幕（JPanel）
      * 
      * 帧每秒和定时机制:
      * 思考游戏的方式：更新+渲染 = 游戏循环的一次迭代 = 一帧
      * FPS（帧每秒）等价于每秒钟游戏循环的迭代（重复）次数。FPS越高，游戏过程就越流畅
      * 在每次迭代中都将更新和渲染游戏一次，刷新屏幕一次
      * 我们的框架目标为60FPS。这应该能够保持游戏流畅。
      * 每秒迭代60次，即每次迭代大约要0.017秒（17毫秒），
      * 假设，对于这个框架上构建的大多数游戏，更新和渲染都将在2~3毫秒内完成。
      * 现在我们打算在每一次更新和渲染后，要求游戏循环睡眠14毫秒，（从而让CPU有时间去执行其它任务）
      * 加上前面的2~3毫秒，每次迭代共需要17毫秒。
      */
	@Override
	public void run() {
		
		while(running)//游戏循环
		{
			//当isPause为真时，这时就进入了一个无限循环。注意最后有个分号！
			while(isPause);
			//从LoadState转换到MenuState界面状态
			currentState.update();
			prepareGameImage();
			//这条语句决定了最后显示在屏幕上的是什么图像
			//把要显示的图像添加到离屏的图像上
			currentState.render(gameImage.getGraphics());
			repaint();//重新绘制，刷新屏幕
			try{
				Thread.sleep(14);//睡眠14毫秒
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			/*if(isPause)
			{
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
		}
		System.exit(0);//当running变成false时，执行此语句退出游戏循环
	}
	public void isPause(){
	 	isPause = true;
		System.out.println("嘿嘿");
	}
	public void isResume(){
		isPause = false;
		System.out.println("哈哈");
	}
	
	private void prepareGameImage()
	{
		if(gameImage == null)//判断是否为空
		{
			//创建一个离屏的空的图像
			//Image java.awt.Component.createImage(int width, int height)
            //创建一个离屏的图像,为Image类型的值，用于双缓冲技术：
			//不是直接把图像绘制在JPanel组件（屏幕）上，而是先准备一个离屏的空的图像，把图像绘制上去，
			//最后再每次一帧的将完成后的场景绘制到屏幕上。
			gameImage = createImage(gameWidth, gameHeight );
		}
		//Graphics java.awt.Image.getGraphics()
        //获取gameImage对象的Graphics画笔，由于绘制图像
		Graphics g = gameImage.getGraphics();
		//在每一帧中，绘制一个与离屏图像相等大小的矩形，来清除在前一帧中已经绘制到屏幕的所有图像。
		g.clearRect(0, 0, gameWidth, gameHeight);
	}
	//需要退出游戏时调用此方法。
	public void exit(){
		running = false;
	}
	
	//将图像绘制到屏幕
	protected void paintComponent(Graphics g){
		//当调用Game.paintComponent()方法时，下面的JComponent.paintComponent()方法也会被调用
		super.paintComponent(g);
		if(gameImage == null)
		{
			return;//如果为空，结束该方法
		}
		g.drawImage(gameImage, 0, 0, null);
	}
	//添加监听器
	private void initInput(){
		inputHandler = new InputHandler();
		//通过addKeyListener方法把监听器InputHandler添加到JPanel上！注意是JPanel，也就是Game上
		addKeyListener(inputHandler);
		addMouseListener(inputHandler);
	}
}
