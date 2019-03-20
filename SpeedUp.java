import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;

public class SpeedUp extends PowerUps{
	
	private KeyInput keyInput = Game.getKeyInput();
	
	private String name = "Speed Up!";


	public SpeedUp(int x, int y, int width, int height, ObjectId id, Handler handler){
		super(x, y, width, height, id, handler);

		this.handler = handler;

		powerUpName(name);
		powerUpColor(200, 50, 1);
	}

	public void powerUpAbility(){

		for(int i = 0; i < handler.objectList.size(); i++){
			GameObject tempObject = handler.objectList.get(i);

			if(tempObject.getId() == ObjectId.Player){
				if(keyInput. x <= 10){
					keyInput.x++;
				}
			}
		}
	}



}