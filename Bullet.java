import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;



public class Bullet extends GameObject{

	private Handler handler;
	private SoundLoader sound;
	protected int xxx, yyy, zzz;
	protected Random rand = new Random();
	protected GameObject temp;

	public Bullet(int x, int y, int width, int height, ObjectId id, Handler handler){
		super(x, y, width, height, id);
		this.handler = handler;

		soundPath = "./Music-SoundEffects/blaster.wav";
		playSound(soundPath, 5.0f);
		setVelY(10);

		xxx = 255;
		yyy = 1;
		zzz = 1;
	}

	public void tick(LinkedList<GameObject> object){
		
		xxx--;

		y-=getVelY();
		
		if (xxx <= 150){
			xxx = 255;
		}
		collision();
		remove();
	}

	public void collision(){

		for(int i = 0; i < handler.objectList.size(); i++){
			GameObject tempObject = handler.objectList.get(i);

			if(tempObject.getId() == ObjectId.Test){
				if(tempObject.recBounds().intersects(recBounds()) || tempObject.getBounds().intersects(getX(), getY(), getWidth(), getHeight())){
					soundPath = "./Music-SoundEffects/bang.wav";

					playSound(soundPath, -8.0f);

					handler.remove(tempObject);
					handler.remove(this);

					addPoints();
					
				}
			}
		}
	}
	private void addPoints(){

		for(int i = 0; i < handler.objectList.size(); i++){
			GameObject tempObject = handler.objectList.get(i);

			if(tempObject.getId() == ObjectId.Test){
				getPlayerPosition().setPoints(getPlayerPosition().getPoints() + tempObject.getPoints());
				System.out.println(tempObject.getPoints() + " " + tempObject.getId());
				break;
			}
		}
	}

	private GameObject getPlayerPosition(){

		for(int i = 0; i < handler.objectList.size(); i++){
			GameObject temp = handler.objectList.get(i);

			if(temp.getId() == ObjectId.Player);
			return temp;
		}
		return temp;
	}

	private void remove(){


		if(getY() < getPlayerPosition().getY() - 700){
			handler.remove(this);
			clip.stop();
		}
	}

	public void playSound(){


	}

	public void render(Graphics g){

		Graphics2D g2d = (Graphics2D)g;

		g.setColor(new Color(xxx, yyy, zzz));
		g.fillRect(x, y, width, height);

		g.setColor(Color.BLUE);
		g2d.draw(recBounds());


		
	}

	public Ellipse2D getBounds(){
		return (new Ellipse2D.Double(getX(), getY(), getWidth(), getHeight()));
	}

	public Rectangle recBounds(){
		return (new Rectangle(getX(), getY(), getWidth(), getHeight()));
	}
}