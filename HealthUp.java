import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;

public class HealthUp extends PowerUps{
	
	KeyInput keyInput = Game.getKeyInput();

	public HealthUp(int x, int y, int width, int height, ObjectId id, Handler handler){
		super(x, y, width, height, id, handler);

		this.handler = handler;

		powerUpName("Health");
		powerUpColor(0, 235, 50);
	}

	public void powerUpAbility(){

		for(int i = 0; i < handler.objectList.size(); i++){
			GameObject tempObject = handler.objectList.get(i);

			if(tempObject.getId() == ObjectId.Player){

				tempObject.health += 50;

			}
		}
	}



	








}