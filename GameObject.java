import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.LinkedList;
import java.io.File;
import sun.audio.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.FloatControl;



public abstract class GameObject implements States{

	//Object position
	protected int x;
	protected int y;

	//Object speed
	protected int velX;
	protected int velY;

	//Object size
	protected int width;
	protected int height;

	//Object image
	protected BufferedImage image;

	//Object Id
	protected ObjectId id;

	//Player Firing
	protected boolean isShooting = false;
	protected boolean isDead = false;

	//Object Direction
	protected boolean isRight;
	protected boolean isDown;

	//Object Sound Effects
	protected String soundPath;
	protected String soundEffect;
	protected AudioInputStream stream;
	protected Clip clip;
	protected FloatControl soundVolume;

	//Player exp enemy Points
	protected int points;



	public GameObject(int x, int y, int width, int height, ObjectId id){
		this.x = x;
		this.y = y;
		this.id = id;
		this.width = width;
		this.height = height;

	}

	
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Ellipse2D getBounds();
	public abstract Rectangle recBounds();

	public void shoot(){

	}

	public void isIdle(boolean isIdle){

	}

	private void gravity(){


	}

	public void playSound(String path, float volume){

		try{

			stream = AudioSystem.getAudioInputStream(new File(path));

			clip = AudioSystem.getClip();

			clip.open(stream);
			soundVolume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			soundVolume.setValue(volume);
			clip.start();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void setVolume(float volume){


	}

	//Setters and Getters for position
	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

	//Setters and Getters for speed;
	public int getVelX(){
		return this.velX;
	}

	public int getVelY(){
		return this.velY;
	}

	public void setVelX(int velX){
		this.velX = velX;
	}

	public void setVelY(int velY){
		this.velY = velY;
	}

	//Setters and getters for size
	public int getWidth(){
		return this.width;
	}

	public int getHeight(){
		return this.height;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public void setHeight(int height){
		this.height = height;
	}

	//Setter and Getter for image
	public BufferedImage getImage(){
		return this.image;
	}

	public void setImage(BufferedImage image){
		this.image = image;
	}

	//Getters for ObjectId
	public ObjectId getId(){
		return this.id;
	}

	//Setters and Getters for point and exp allocation
	public int getPoints(){
		return this.points;
	}

	public void setPoints(int points){
		this.points = points;
	}





}