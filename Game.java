import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Font;


public class Game extends Canvas implements Runnable{
	
	//Game Frame
	private static Frame frame;


	//Game starting variable
	protected final static String _TITLE = "Space Blasters";
	protected final static int _WIDTH = 1000;
	protected final static int _HEIGHT = 800;
	private boolean isRunning = false;
	private Thread thread;

	//Image Loader
	private static ImageLoader imageLoader;
	private Background background;
	private BufferedImage backgroundImage;

	//Handler for GameObjects
	private Handler handler;
	public Random rand = new Random();
	private HealthUp hpUp;
	protected static HighScores hs;
	private HighScoreMenu hsm;



	//Camera for player
	private Camera cam;

	//Player
	private Player player;

	//KeyBoard class
	private static KeyInput keyInput;

	//Mouse class
	private MouseInput mouseInput;

	//Plays Background Music
	private SoundLoader soundLoader;

	//Starting Screen
	protected boolean pressedStart;
	protected boolean pressedScores;
	private StartMenu menu;
	

	public Game(){


	}

	private void init(){

		//Initiate Needed classes
		handler = new Handler();
		imageLoader = new ImageLoader();
		background = new Background();
		backgroundImage = background.getBackground();
		soundLoader = new SoundLoader();
		
		soundLoader.loadSound("./Music-SoundEffects/Pio.wav");
		soundLoader.playBackGroundMusic();
		hs = new HighScores(handler);

		keyInput = new KeyInput(handler);
		addKeyListener(keyInput);

		mouseInput = new MouseInput(handler);
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
		
		//Add Player
		cam = new Camera(0, 0);

		
		//Add Start Menu Buttons
		menu = new StartMenu(mouseInput, frame, this);
		hsm = new HighScoreMenu(mouseInput, frame, handler, this);
		handler.highscores.add("CAMMSN  899");


	}

	public void loadMenu(){

	}


	public synchronized void start(){

		if(isRunning)
			return;

		isRunning = true;

		thread = new Thread(this);
		thread.run();

	}

	public void run(){

		init();
		this.requestFocus();

		//Game Loop makes sure everything isn't running to fast or slow
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double nanoSec = 1000000000/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;


		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime)/ nanoSec;
			lastTime = now;

			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}

			render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " +frames+ " TICKS: " + updates);
				frames = 0;
				updates = 0;

			}
		}		
	}

	private void render(){

		//Shows all pictures buttons and icons
		BufferStrategy bs = this.getBufferStrategy();

		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		Graphics2D g2d = (Graphics2D)g;

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, _WIDTH, _HEIGHT);

		if(pressedStart == true && pressedScores != true){
		//////////////////////////////
			
			g.translate(cam.getX(), cam.getY());

			for(int xx = 0; xx < backgroundImage.getWidth()*200; xx+=backgroundImage.getWidth()){
				for(int yy = 0; yy < backgroundImage.getHeight()*200;yy+=backgroundImage.getHeight()){
					g.drawImage(backgroundImage, xx, yy, this);
				}
			}
			handler.render(g);

			g.translate(-cam.getX(), -cam.getY());
		/////////////////////////////

		}else if(pressedScores != true && pressedStart != true){
			//Start Menu
			menu.render(g);
			
			
		}else if(pressedScores == true && pressedStart != true){
			hsm.render(g);
		}

		g.dispose();
		bs.show();
	}

	private void tick(){

		//Updates all positions and timing

		//Check if start has been pressed
		if(pressedStart == true){
			if(player == null){

				player = new Player(4500, 9500, 20, 40, ObjectId.Player, handler);
				handler.add(player);

				hpUp = new HealthUp(player.getX() + 40, player.getY() - 70, 25, 25, ObjectId.PowerUps, handler);
				handler.add(hpUp);


			}

			//Ticks all existing game Objects
			for(int i = 0; i < 200; i++){
				int spawn = rand.nextInt(20000);
				int spawn1 = rand.nextInt(20000);
					
					if(spawn > player.getX() + 1000 && spawn < player.getX() + 1500 && spawn1 > player.getY() + 1000 && spawn1 < player.getY()+1500){
						handler.add(new Test(rand.nextInt(spawn), rand.nextInt(spawn1), 25, 25, ObjectId.Test, handler));
						System.out.println("x: " + spawn + " y: " + spawn1);
						break;
					}
			}	
			handler.tick();


			//Checks if player has died
			if(player.isDead == true){
				player = null;
				handler.objectList.clear();
				pressedStart = false;

			}

			//Ticks Camera on Player
			for(int i = 0; i <handler.objectList.size(); i++){
				if(handler.objectList.get(i).getId() == ObjectId.Player){
					cam.tick(handler.objectList.get(i));
				}
			}
		}
	}

	//loads images
	public static ImageLoader getImageLoader(){
		return imageLoader;
	}

	public static KeyInput getKeyInput(){
		return keyInput;
	}

	public static HighScores getHighScores(){
		return hs;
	}


	public static void main(String args[]){
		frame = new Frame(_WIDTH, _HEIGHT, _TITLE, new Game());
	}
}