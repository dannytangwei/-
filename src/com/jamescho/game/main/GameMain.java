package com.jamescho.game.main;


/*
 * 基本思路：
 * 1.创建JFrame窗口
 * 2.并把JPanel容器添加到窗口
 * 3.给项目添加图像文件，把图片资源添加到resources包中
 * 4.在com.jamescho.game.main包中创建一个Resources类，从resources包中加载
 *   图片，并且将它们存储为可供游戏中其它类来访问的的公有变量。
 * 5.定义状态，即主界面、游戏界面、设置界面之间的跳转，创建状态抽象类State
 * 6.在com.jamescho.game.state创建一个游戏加载界面LoadState
 * 7.通过在Game类中，添加方法，从而启动加载资源
 * 8.使加载资源后显示出来图像，即从LoadState跳转到另一状态（界面）。
 * 9.为了使每一个状态都能转变为另一个状态类，可以在State超类中，添加跳转方法。
 * 10.由于游戏需要同时执行各种任务，所以需要添加多线程
 * 11.在Game类中添加多线程，执行游戏循环
 * 12.处理玩家输入，创建InputHandler监听器并添加至JPanel上
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.BorderLayout;

import javax.swing.JButton;

public class GameMain {
	//定义一个标题常量
    private static final String GAME_TITLE = "LoneBall";
	public static final int GAME_WIDTH = 1350;
	public static final int GAME_HEIGHT = 700;
	public static Game sGame; //这里使用public修饰符，方便别的包里的类调用
	private static JButton pause = new JButton("暂停游戏");
	private static JButton resume = new JButton("继续游戏");
	//Timer timer = new Timer();
	public static void main(String[] args)
  	{   //先写new JFrame();部分，然后按下Ctrl+2 停顿一下再按 L，即可自动生成左边部分。
		JFrame frame = new JFrame(GAME_TITLE);//创建窗口框架，并设置标题
		//关闭窗口时
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗口不可改变尺寸大小，即不能手动修改窗口尺寸
		frame.setResizable(false);
		//设置为可见
		frame.setVisible(true);
		
		/*
		 * 把JPanel容器添加到窗口：
		 * 先创建Game对象，注意：它有两个参数,
		 * 1.可先在Game构造器中输入GAME_WIDTH，然后根据eclipse的提示创建常量！
		 * 2.同样是先写右边部分，然后按快捷键生成左边部分
		 * 3.单击sGame,按下Ctrl+1，选中Convert local variable to field,设置为成员变量
		 */
		sGame = new Game(GAME_WIDTH, GAME_HEIGHT);
		frame.add(sGame);
		//Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
		//使这个窗口大小适合子组件的首选大小和布局。(这个大小由子组件中的setPreferredSize方法设置)
		frame.pack();
		frame.setIconImage(Resources.iconimage);//设置游戏图标
		JPanel jPanel = new JPanel();
		/*jPanel.add(pause);
		jPanel.add(resume);
		frame.add(jPanel,BorderLayout.SOUTH);
		pause.addActionListener(e ->
		{
			sGame.isPause();  //暂停
		});
		resume.addActionListener(e ->
		{
			sGame.isResume();
		});*/
	}
	
}
