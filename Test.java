import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;

public class Test extends GameObject {

	private Handler handler;
	private Random rand = new Random();
	private GameObject tempObject;
	private int xxx, yyy, zzz;

	private int size;

	private int removal = 0;
	private int set = 1;
	private int loop = 0;

	public Test(int x, int y, int width, int height, ObjectId id, Handler handler){
		super(x, y, width, height, id);

		this.handler = handler;
		points = 10;

		setVelY(8);
		setVelX(8);

		size = rand.nextInt(2);



	}

	public void tick(LinkedList<GameObject> object){
		
		tempObject = getPlayerPosition();

		if(loop == 3){
			loop = 0;	
			y-=getVelY();
			x-=getVelX();
		
		}else{
				loop++;
			}

			xxx = rand.nextInt(255);
			yyy = rand.nextInt(255);
			zzz = rand.nextInt(255);
		
			setWidth(getWidth() + size);
			setHeight(getHeight() + size);

			//Resizing Shape	
			if(getWidth() >= 30 && getHeight() >= 30){
				size = -size;

			}else if(getWidth() <= 10 && getHeight() <= 10){
				size = -size;
			}else{
				size++;
			}


			//Moving Shape
			if(getX() > tempObject.getX()){
				setVelX(8);
				set = -set;

			}else if(getX() < tempObject.getX()){
				setVelX(-8);
				set = -set;
			}

			if(getY() > tempObject.getY()){
				setVelY(8);
				set = -set;

			}else if(getY() < tempObject.getY()){
				setVelY(-8);
				set = -set;
			}
			
			collision();
			remove();
	}

	public void collision(){


		for(int i = 0; i < handler.objectList.size(); i++){
			tempObject = handler.objectList.get(i);
			if(tempObject != this){
				if(getBounds().intersects(tempObject.getX(), tempObject.getY(), tempObject.getWidth(), tempObject.getHeight()) || recBounds().intersects(tempObject.recBounds())){
					//setVelX(-getVelX());
					//setVelY(-getVelY());
					set = -set;
				}
			}
		}
	}

	private void remove(){
		for(int i = 0; i < handler.objectList.size(); i++){
			tempObject = handler.objectList.get(i);

			if(tempObject.getId() == ObjectId.Player){
				if(x > tempObject.getX() +2000 || x < tempObject.getX() -2000 || y > tempObject.getY() + 2000 || y < tempObject.getY() - 2000){
					handler.remove(this);
					System.out.println("Removed x:" + x +" y:" +y);
				}
			}
		}
	}

	private GameObject getPlayerPosition(){

		for(int i = 0; i < handler.objectList.size(); i++){
			tempObject = handler.objectList.get(i);

			if(tempObject.getId() == ObjectId.Player){
				return tempObject;
			}
		}
		return tempObject;
	}

	public void render(Graphics g){

		Graphics2D g2d = (Graphics2D)g;

		if(set == 1){

			g.setColor(Color.WHITE);
			g2d.draw(getBounds());

			g.setColor(new Color(xxx,yyy,zzz));
			g.fillOval(x, y, width, height);
			points = 10;
		}else if(set == -1){

			g.setColor(Color.WHITE);
			g2d.draw(recBounds());

			g.setColor(new Color(xxx,yyy,zzz));
			g.fillRect(x, y, width, height);
			points = 20;
		}	
		

	}
	public Ellipse2D getBounds(){
		return (new Ellipse2D.Double(getX() -5, getY() - 5, getWidth() + 10, getHeight() + 10));
	}

	public Rectangle recBounds(){
		return (new Rectangle(getX() -5, getY() - 5, getWidth() + 10, getHeight() + 10));
	}

}