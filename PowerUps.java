import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;

public class PowerUps extends GameObject{
	
	private Handler handler;
	private GameObject tempObject;

	//Power up attributes
	private int size;
	private int xxx = 1, yyy = 255, zzz = 255;
	private Random rand = new Random();

	//Loop
	private int loop = 0;


	public PowerUps(int x, int y, int width, int height, ObjectId id, Handler handler){
		super(x, y, width, height, id);

		this.handler = handler;
		size = rand.nextInt(2);
	}

	public void tick(LinkedList<GameObject> object){
		//Color changer
		xxx++;

		setWidth(getWidth() + size);
		setHeight(getHeight() + size);

			if(getWidth() >= 60 && getHeight() >= 60){
				size = -size;

			}else if(getWidth() <= 10 && getHeight() <= 10){
				size = -size;
			}

			if(xxx >= 160) xxx = 1;
			if(yyy <= 1) xxx = 255;
			if(zzz <= 1) xxx = 255;

		}

	public void collision(){

		for(int i = 0; i < handler.objectList.size(); i++){
			tempObject = handler.objectList.get(i);

			if(tempObject.getId() == ObjectId.Player){

				if(recBounds().intersects(tempObject.recBounds())){

				}
			}
		}

	}

	public void render(Graphics g){

		Graphics2D g2d = (Graphics2D)g;

		g.setColor(new Color(xxx, yyy, zzz));
		g.fillOval(x, y, width, height);

		g.setColor(new Color(xxx + 80, yyy- 80, zzz- 80));
		g.fillOval(x + 6, y + 6, width/2, height/2);

		g.setColor(new Color(xxx, yyy, zzz));
		g2d.draw(getBounds());

	}

	public Rectangle recBounds(){
		return(new Rectangle(x, y, width, height));
	}

	public Ellipse2D getBounds(){
		return(new Ellipse2D.Double(x - 12, y - 11 , width*2, height*2));
	}



}