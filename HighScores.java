import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.IOException;
import java.util.LinkedList;


public class HighScores {
	
	protected LinkedList<String> highScores = new LinkedList<String>();
	private Handler handler;
	private String playerName;
	private int score;
	private int num = 1;

	private File statText;
	private FileOutputStream os;
	private OutputStreamWriter osw;
	private Writer w;
	protected int x, y;




	public HighScores(Handler handler){

		this.handler = handler;

			statText = new File("./highScores/scores.txt");
		
		

		num++;
	}

	public void addScore(String name, int score){

		this.playerName = name;
		this.score = score;

		handler.highscores.add(name +" " + score);
	}

	public void write(){
	
		try{
			os = new FileOutputStream(statText, true);
			osw = new OutputStreamWriter(os);
			w = new BufferedWriter(osw);
			
			for(int i = 0; i < handler.highscores.size(); i++){

				w.write(handler.highscores.get(i) + "rank \n");
			}

			w.close();


		}catch(IOException e){
			e.printStackTrace();
		}


	}

	public void getScores(){

	}
}