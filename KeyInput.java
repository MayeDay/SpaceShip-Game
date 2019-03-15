import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class KeyInput extends KeyAdapter{

	private Handler handler;
	private GameObject player;

	protected int x = 5;
	//private SoundLoader soundLoader = new SoundLoader();
	//private String soundPath =  "./Music-SoundEffects/hover.wav";

	public KeyInput(Handler handler){

		this.handler = handler;
	}

	public void keyPressed(KeyEvent e){
		int code = e.getKeyCode();

		for(int i =0; i < handler.objectList.size(); i++){
			if(handler.objectList.get(i).getId() == ObjectId.Player){
				 player = handler.objectList.get(i);
			}
		}


		if(code == KeyEvent.VK_RIGHT){
			player.setVelX(x);
			player.isRight = true;


		}else if(code == KeyEvent.VK_LEFT){
			player.setVelX(-x);
			player.isRight = false;
		
		}else if(code == KeyEvent.VK_UP){
			player.setVelY(-x);
			player.isDown = false;


		}else if(code == KeyEvent.VK_DOWN){
			player.setVelY(x);
			player.isDown = true;

		}else if(code == KeyEvent.VK_Z){
			
			

		}else if(code == KeyEvent.VK_SPACE && !player.isShooting && !player.isDead){
			player.isShooting = true;
			player.shoot();
		}
	}

	/**public GameObject getPlayer(){

		for(int i =0; i < handler.objectList.size(); i++){
			if(handler.objectList.get(i).getId() == ObjectId.Player){
				return player = handler.objectList.get(i);
			}
		}
		return player;
	}**/

	public void keyReleased(KeyEvent e){

		int code = e.getKeyCode();

		for(int i =0; i < handler.objectList.size(); i++){
			if(handler.objectList.get(i).getId() == ObjectId.Player){
				 player = handler.objectList.get(i);
			}
		}

		if(code == KeyEvent.VK_RIGHT){

			player.setVelX(0);
			player.x += player.getVelX();

		}else if(code == KeyEvent.VK_LEFT){
			player.setVelX(0);
			player.x += player.getVelX();
		
		}else if(code == KeyEvent.VK_UP){
			player.setVelY(0);
			player.y += player.getVelY();

		}else if(code == KeyEvent.VK_DOWN){
			player.setVelY(0);
			player.y += player.getVelY();
		}else if(code == KeyEvent.VK_SPACE){
			player.isShooting = false;
		}
	}

	public int getX(){
		return this.x;
	}
	public void setX(int x){
		this.x = x;
	}
}