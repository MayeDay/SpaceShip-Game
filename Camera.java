

public class Camera{
	
	protected int x;
	protected int y;

	GameObject player;

	public Camera(int x, int y){

		this.x = x;
		this.y = y;
	}

	public void tick(GameObject player){
		this.player = player;

		x = -player.getX() + Game._WIDTH/2 - Game.WIDTH/2;

		y = -player.getY() + Game._HEIGHT/2 - Game.HEIGHT/2;

		y--;

	}

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
}