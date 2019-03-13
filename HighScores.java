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

	private int xxx, yyy, zzz;


	public PowerUps(int x, int y, int width, int height, ObjectId id, Handler handler){
		super(x, y, width, height, id);

		this.handler = handler;
	}

	public void tick(LinkedList<GameObject> object){

		if(xxx > 110) xxx = 1;
		if(yyy > 225) yyy = 1;
		if(zzz > 210) zzz = 1;

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



	}

	public Rectangle recBounds(){
		return(new Rectangle(x, y, width, height));
	}

	public void Ellipse2D(){
		return(new Ellipse2D.Double(x, y , width, height));
	}



}