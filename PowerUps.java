import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.Random;

public class PowerUps extends GameObject{
	
	protected Handler handler;
	protected GameObject tempObject;
	protected SoundLoader sound;	

	//Power up attributes
	protected int size;
	protected String powerName;
	protected int color = 1;

	//Color Green
	protected int xxx, yyy, zzz;
	protected Random rand = new Random();


	//Loop
	private int loop = 0;


	public PowerUps(int x, int y, int width, int height, ObjectId id, Handler handler){
		super(x, y, width, height, id);

		this.handler = handler;
		powerUpColor(0, 255, 50);

		size = 1;
		soundPath = "./Music-SoundEffects/powerUP.wav";

	}

	public void tick(LinkedList<GameObject> object){
		//Color changer
		xxx+=color;
		zzz+=color;

		setWidth(getWidth() + size);
		setHeight(getHeight() + size);

		if(loop == 15){
			loop = 0;
			if(getWidth() >= 26 && getHeight() >= 26){
				size = -size;

			}else if(getWidth() <= 10 && getHeight() <= 10){
				size = -size;
			}else{
				size++;
			}
		}else{
			loop++;
		}


			if(xxx >= 170){
				color= -color;
			
			}else if(xxx<=0){
				color = -color;
			}

			if(zzz >= 185){

				color = -color;
			}else if(zzz <= 0){
				color= - color;
			}
			collision();

		}

	public void collision(){

		for(int i = 0; i < handler.objectList.size(); i++){
			tempObject = handler.objectList.get(i);

			if(tempObject.getId() == ObjectId.Player){

				if(recBounds().intersects(tempObject.recBounds()) || getBounds().intersects(tempObject.getX(), tempObject.getY(), tempObject.getWidth(), tempObject.getHeight())){
					playSound(soundPath, 5.0f);

					powerUpName("Null");
					powerUpAbility();
					handler.remove(this);
				}
			}
		}

	}

	public void powerUpColor(int xxx, int yyy, int zzz){
		
		this.xxx = xxx;
		this.yyy = yyy;
		this.zzz = zzz;
	}

	public void powerUpAbility(){


	}

	public void powerUpName(String powerName){
		this.powerName = powerName;

	}

	public void render(Graphics g){

		Graphics2D g2d = (Graphics2D)g;


		g.setColor(new Color(xxx, yyy, zzz));
		g.fillOval(x - 11, y - 10, width*2, height*2);

		g.setColor(Color.WHITE);

		g.fillRect(getX() + width - 15, getY() - height/2 + 5 , width/2, height*2 - 5);


	//	g.setColor(new Color(xxx, yyy, zzz));
	//	g.fillOval(x + size, y + size, width, height);

	
		/**g.setColor(new Color(xxx, yyy, zzz));
		g.fillOval(x + 6, y + 6, width/2, height/2);

		g.setColor(new Color(xxx, yyy, zzz));
		g2d.draw(getBounds());

		g.setFont(new Font("Arial", Font.ITALIC, 15 + size));
		g.drawString(powerName, getX() - 22, getY() - 18);
		**/

	}

	public Rectangle recBounds(){
		return(new Rectangle(x, y, width, height));
	}

	public Ellipse2D getBounds(){
		return(new Ellipse2D.Double(x - 12, y - 11 , width*2, height*2));
	}



}