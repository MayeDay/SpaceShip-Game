import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.Random;
import javax.sound.sampled.Clip;


public class Player extends GameObject{

	private Handler handler;
	private Random rand = new Random();

	protected int health = 300;
	protected int xxx, yyy, zzz;
	protected int points = 0;
	protected boolean isDead = false;
	protected int loop = 0;


	public Player(int x, int y, int width, int height, ObjectId id, Handler handler){
		super(x, y, width, height, id);
		this.handler = handler;

		//flying sound
		soundPath = "./Music-SoundEffects/flying.wav";
		playSound(soundPath, 5.0f);
		clip.loop(Clip.LOOP_CONTINUOUSLY);

		//collision sound
		soundEffect = "./Music-SoundEffects/shoot.wav";


		xxx = 1;
		yyy = 255;
		zzz = 255;
	}

	public void tick(LinkedList<GameObject> object){
		
		xxx--;
		yyy--;

		x+= getVelX();
		y+= getVelY();
		this.points = getPoints();

		if (xxx <= 150){
			xxx = 255;
		}
		if(yyy <= 150){
			yyy = 255;
		}
		//Move To Game Class
		for(int i = 0; i < 200; i++){
			int spawn = rand.nextInt(20000);
			int spawn1 = rand.nextInt(20000);
			if(spawn > getX() + 1000 && spawn < getX() + 1500 && spawn1 > getY() + 1000 && spawn1 < getY()+1500){
				handler.add(new Test(rand.nextInt(spawn), rand.nextInt(spawn1), 25, 25, ObjectId.Test, handler));
				System.out.println("x: " + spawn + " y: " + spawn1);
				break;
			}
		}

		collision();
		deathCheck();
	}

	public void collision(){

		for(int i = 0; i < handler.objectList.size(); i++){
			GameObject tempObject = handler.objectList.get(i);
			if(tempObject.getId() == ObjectId.Test){
				if(recBounds().intersects(tempObject.recBounds()) || recBounds().intersects(tempObject.getX(), tempObject.getY(), tempObject.getWidth(), tempObject.getHeight())){
					handler.remove(tempObject);
					playSound(soundEffect, 6.0f);
					health -=50;

				}
			}
		}
	}

	private void deathCheck(){
		if(health <= 0){
		}
	}

	public void shoot(){

		handler.add(new Bullet(getX() + 7, getY() - 20, 4, 10,ObjectId.Bullet, handler));
	}

	public void render(Graphics g){

		Graphics2D g2d = (Graphics2D)g;

		if(health > 0){
			//Main Body
			g.setColor(new Color(xxx, yyy, zzz));
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			g.fillOval(getX()-2, getY() - 10, getWidth() + 4, getHeight());

			//Thrusters
			//g.setColor(new Color(xxx, yyy, zzz));
			//g.fillRect(getX()+25, getY() +20, getWidth()-10, getHeight()-10);
			//g.fillRect(getX()-20, getY() +20, getWidth()-10, getHeight()-10);

			g.setColor(Color.BLUE);
			g.fillOval(getX()+19, getY() +15, getWidth()-5, getHeight()-10);
			g.fillOval(getX()-14, getY() +15, getWidth()-5, getHeight()-10);
			//Panel
			g.setColor(Color.BLACK);
			g.fillOval(getX() + 5, getY() +3, getWidth()-10, getHeight()-25);

			//HitBox
			//g.setColor(Color.RED);
			//g2d.draw(recBounds());

			//HUD
			g.setColor(Color.RED);
			g.fillRect(getX() -480, getY() - 360, 300, 30);
			g.setColor(Color.GREEN);
			g.fillRect(getX() -480, getY() - 360, health, 30);

			g.setFont(new Font("Arial", Font.ITALIC, 20));
			g.drawString("Health ", getX() -480, getY() - 370);

			g.setFont(new Font("Arial", Font.ITALIC, 30));
			g.drawString("Points: "+ points, getX() + 280, getY() - 340);
		}else{
			if(loop != 1800){
				g.setColor(Color.RED);
				g.setFont(new Font("Arial", Font.ITALIC, 20));
				g.drawString("YOU HAVE DIED ", getX() - 25, getY());
				loop++;

			}else if(loop == 1800){
				isDead = true;

			}

		}
	}

	public Ellipse2D getBounds(){
		return (new Ellipse2D.Double(getX(), getY(), getWidth(), getHeight()));
	}

	public Rectangle recBounds(){
		return (new Rectangle(getX() - 15, getY(), getWidth() + 30, getHeight()+ 15));
	}

}
	