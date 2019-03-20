import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;


public class Hud{

	GameObject player;
	protected int x, y;

	

	public Hud(GameObject player){

		this.player = player;
		this.x = player.getX() -480;
		this.y = player.getY() - 360;
	}

	public void tick(){

		x = player.getX() -480;
		y = player.getY() - 360;
	}

	public void render(Graphics g){



		g.setColor(Color.RED);
		g.fillRect(x, y, 300, 30);
		g.setColor(Color.GREEN);
		g.fillRect(x, y, player.health, 30);

		g.setFont(new Font("Arial", Font.ITALIC, 20));
		g.drawString("Health ", x, y);

		g.setFont(new Font("Arial", Font.ITALIC, 30));
		g.drawString("Points: "+ player.points, x + 800, y + 20);


	}

	public int getY(){
		return y;
	}

	public int getX(){
		return x;
	}

	public void setY(int y){
		this.y = y;
	}

	public void setX(int x){
		this.x = x;
	}
}