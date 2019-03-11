import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class KeyInput extends KeyAdapter{

	private Handler handler;
	private GameObject player;
	private SoundLoader soundLoader = new SoundLoader();
	private String soundPath =  "./Music-SoundEffects/hover.wav";

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
			player.setVelX(5);
			player.isRight = true;


		}else if(code == KeyEvent.VK_LEFT){
			player.setVelX(-5);
			player.isRight = false;
		
		}else if(code == KeyEvent.VK_UP){
			player.setVelY(-5);
			player.isDown = false;


		}else if(code == KeyEvent.VK_DOWN){
			player.setVelY(5);
			player.isDown = true;

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
}