import java.util.LinkedList;
import java.awt.Graphics;

public class Handler{
	
	public LinkedList<GameObject> objectList = new LinkedList<GameObject>();
	public LinkedList<String> highscores = new LinkedList<String>();
	private GameObject tempObject;


	public void tick(){

		for(int i = 0; i < objectList.size(); i++){
			tempObject = objectList.get(i);
			tempObject.tick(objectList);


		}

	}

	public void render(Graphics g){

		for(int i = 0; i < objectList.size(); i++){
			tempObject = objectList.get(i);

			//if(tempObject.getId() == ObjectId.Test){
				tempObject.render(g);
			//}
		}
	}

	public void add(GameObject object){
		objectList.add(object);
	}

	public void remove(GameObject object){
		objectList.remove(object);
	}

	public void addScore(String name, int score){

		highscores.add(name+ " " + score);

	}
	public void removeScore(String name, int score){

		highscores.remove(name + " " + score);
	}

}