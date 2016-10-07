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
	
     private int gameWidth;//��Ϸ���ڵĿ��
     private int gameHeight;
     private volatile State currentState;//��¼��ǰ��Ϸ����
     private Image game;
     private Thread gameThread;
     private volatile boolean running;
     private Image gameImage;
     private InputHandler inputHandler;
     private volatile boolean isPause = false;
     
     //����һ���Զ���Ĺ�����,������������
     public Game(int gameWidth, int gameHeight){
    	 
    	this.gameWidth = gameWidth;//Ϊ�����ʵ��������ʼ����ֵ
    	this.gameHeight = gameHeight;
    	
    	//��������JPanel��4��������ͨ���̳ж�����ʹ�ã�
    	
        /*void javax.swing.JComponent.setPreferredSize(Dimension preferredSize)˵����
         * ��JPanel�̳���JComponent�ķ���������Ϊ������óߴ��С��
         * ��������Dimension���͵ģ�������API�ĵ��в�ѯDimension�Ĺ������÷����Ӷ���������
         */
    	setPreferredSize(new Dimension(gameWidth, gameHeight));
    	//���ñ�����ɫ
    	setBackground(Color.BLACK);
    	/*
    	 * ��ôΪһ������Ϥ�������������?
    	 * ���Ȳ�ѯAPI�ĵ�����֪public void setFocusable(boolean focusable)�����Ĳ������£�
    	 * Parameters:focusable - indicates whether this Component is focusable
    	 * ����ָʾ�Ƿ�focusable�������
    	 * ��д��setFocusable�������ʱ������ʾ��ݼ� Alt+/,Ȼ��ѡ�����跽�����س�����
    	 * �ͻ����һ��ѡ������Ľ��棬�Ӷ������ҵ�true����isFocusable()
    	 * �ɰٶȲ�ѯfocusable���۽������÷�������API�ĵ���ٶȵĽ��ʹ�á�
    	 */
    	setFocusable(true);//���þ۽�
    	requestFocus(); //����۽�
    	/*
    	 * �������������ζ�ż����¼��Ͱ�ť�¼��ɹ�Game����ʹ�ã�
    	 * ����ʼ�����û����루�Լ����¼�������¼�����ʽ��
    	 */
    	
     }
     
     
     public void setCurrentState(State newState)
 	 {
 		System.gc();//����δʹ�õĶ���
 		//��̬���κ�State������඼������Ϊ�����Ĳ���
 		newState.init();//������Դ��������ʾ��Դ
 		currentState = newState;//�ѵ�ǰ��������ΪcurrentState�������������������ͼ�����
 		//com.jamescho.framework.util.InputHandler.setCurrentState(State currentState)
 		//�ѵ�ǰ�Ľ���״̬��������MenuState�����ݸ�IntputHandler�࣬
 		//�Ӷ�ʹIntputHandler���е�currentState.onLick(e);���Եõ����£���ϵ��MenuState����
 		inputHandler.setCurrentState(currentState);
 	 }
     
     //��Game����ɹ���ӵ�JFrame��ʱ�����Զ����ô˷���������������������
     //�����Overrideָ������ķ���������д���෽��
     @Override
     public void addNotify(){
    	 //ʹ�����������ʾ��ͨ�����ӵ�������Ļ��Դ
    	super.addNotify();
    	/*
    	 * Ϊʲô������䲻�ܷ���setCurrentState(new LoadState());�����أ�������������������
    	 * ��ΪҪ��ͨ��initInput��������InputHandler����
    	 * setCurrentState()�����е�inputHandler.setCurrentState(currentState);����������
    	 * ���������е������ʱ����������InputHandler��Ķ�����Ӽ�������Panel
    	 */
    	initInput();
    	//����LoadState�е�init()����������Դ������LoadState״̬���渳��currentState
    	setCurrentState(new LoadState());
    	initGame();//�Զ��巽�������Ϸ�߳�
    	
     }
     
     /* �����Ϸ�̣߳�
      * д��gameThread = new Thread(this,"Game Thread");ʱ
      * ����ᱨ����Ϊ����thisָ����Game����Thread���췽��Ҫ
      * ����Thread(Runnable target,String name),��Runnable��һ���ӿڣ�
      * ���Կ���ʹGame��ʵ��Runable�ӿڣ���ʵ��run()����
      */
     private void initGame(){
    	 running = true;
    	 gameThread = new Thread(this,"Game Thread");
    	 gameThread.start();
     }

     //ʵ����Ϸѭ�������º���Ⱦ
     /*
      * ����ֻҪ��currentState������updata()�������ɡ�
      * ��Ⱦ�Ļ������裺
      * 1.׼��һ�������Ŀյ�ͼ��
      * 2.��currentState�����е���Ϸ������Ⱦ�����ƣ������ͼ��
      * 3.����ɺ������ͼ����Ƶ���Ļ��JPanel��
      * 
      * ֡ÿ��Ͷ�ʱ����:
      * ˼����Ϸ�ķ�ʽ������+��Ⱦ = ��Ϸѭ����һ�ε��� = һ֡
      * FPS��֡ÿ�룩�ȼ���ÿ������Ϸѭ���ĵ������ظ���������FPSԽ�ߣ���Ϸ���̾�Խ����
      * ��ÿ�ε����ж������º���Ⱦ��Ϸһ�Σ�ˢ����Ļһ��
      * ���ǵĿ��Ŀ��Ϊ60FPS����Ӧ���ܹ�������Ϸ������
      * ÿ�����60�Σ���ÿ�ε�����ԼҪ0.017�루17���룩��
      * ���裬�����������Ϲ����Ĵ������Ϸ�����º���Ⱦ������2~3��������ɡ�
      * �������Ǵ�����ÿһ�θ��º���Ⱦ��Ҫ����Ϸѭ��˯��14���룬���Ӷ���CPU��ʱ��ȥִ����������
      * ����ǰ���2~3���룬ÿ�ε�������Ҫ17���롣
      */
	@Override
	public void run() {
		
		while(running)//��Ϸѭ��
		{
			//��isPauseΪ��ʱ����ʱ�ͽ�����һ������ѭ����ע������и��ֺţ�
			while(isPause);
			//��LoadStateת����MenuState����״̬
			currentState.update();
			prepareGameImage();
			//�����������������ʾ����Ļ�ϵ���ʲôͼ��
			//��Ҫ��ʾ��ͼ����ӵ�������ͼ����
			currentState.render(gameImage.getGraphics());
			repaint();//���»��ƣ�ˢ����Ļ
			try{
				Thread.sleep(14);//˯��14����
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
		System.exit(0);//��running���falseʱ��ִ�д�����˳���Ϸѭ��
	}
	public void isPause(){
	 	isPause = true;
		System.out.println("�ٺ�");
	}
	public void isResume(){
		isPause = false;
		System.out.println("����");
	}
	
	private void prepareGameImage()
	{
		if(gameImage == null)//�ж��Ƿ�Ϊ��
		{
			//����һ�������Ŀյ�ͼ��
			//Image java.awt.Component.createImage(int width, int height)
            //����һ��������ͼ��,ΪImage���͵�ֵ������˫���弼����
			//����ֱ�Ӱ�ͼ�������JPanel�������Ļ���ϣ�������׼��һ�������Ŀյ�ͼ�񣬰�ͼ�������ȥ��
			//�����ÿ��һ֡�Ľ���ɺ�ĳ������Ƶ���Ļ�ϡ�
			gameImage = createImage(gameWidth, gameHeight );
		}
		//Graphics java.awt.Image.getGraphics()
        //��ȡgameImage�����Graphics���ʣ����ڻ���ͼ��
		Graphics g = gameImage.getGraphics();
		//��ÿһ֡�У�����һ��������ͼ����ȴ�С�ľ��Σ��������ǰһ֡���Ѿ����Ƶ���Ļ������ͼ��
		g.clearRect(0, 0, gameWidth, gameHeight);
	}
	//��Ҫ�˳���Ϸʱ���ô˷�����
	public void exit(){
		running = false;
	}
	
	//��ͼ����Ƶ���Ļ
	protected void paintComponent(Graphics g){
		//������Game.paintComponent()����ʱ�������JComponent.paintComponent()����Ҳ�ᱻ����
		super.paintComponent(g);
		if(gameImage == null)
		{
			return;//���Ϊ�գ������÷���
		}
		g.drawImage(gameImage, 0, 0, null);
	}
	//��Ӽ�����
	private void initInput(){
		inputHandler = new InputHandler();
		//ͨ��addKeyListener�����Ѽ�����InputHandler��ӵ�JPanel�ϣ�ע����JPanel��Ҳ����Game��
		addKeyListener(inputHandler);
		addMouseListener(inputHandler);
	}
}
