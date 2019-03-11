import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

public class StartMenu{

	private Game game;
	private MouseInput mouseInput;
	private Frame frame;

	private String start = "Start";
	private String exit = "Exit";
	private String score = "HighScores";

	private Button startButton;
	private Button exitButton;
	private Button highScoresButton;

	private int xxx = 1, yyy = 1, zzz = 1;

	public StartMenu(MouseInput mouseInput,Frame frame, Game game){

		this.game = game;
		this.mouseInput = mouseInput;
		this.frame = frame;

		init();
	}

	private void init(){

		startButton = new Button(Game._WIDTH/2 - 100, Game._HEIGHT/2 - 200, 200, 100);
		exitButton = new Button(Game._WIDTH/2 - 100, Game._HEIGHT/2 - 50 , 200, 100);
		highScoresButton = new Button(Game._WIDTH/2 - 100, Game._HEIGHT/2  + 100, 200, 75);
		

	}

	public void render(Graphics g){

		Graphics2D g2d = (Graphics2D)g;
		g.setColor(Color.GREEN);
		g.setFont(new Font("Arial", Font.ITALIC, 45));
		g.drawString("World Wreckers", Game._WIDTH/2 - 165, Game._HEIGHT/2 - 300);

		//Buttons
		g2d.fill(startButton.getButtonBounds());
		g2d.fill(exitButton.getButtonBounds());
		g2d.fill(highScoresButton.getButtonBounds());
		g.setColor(Color.BLUE);

		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(start, startButton.getX() + 45, startButton.getY() + 50);
		g.drawString(exit, exitButton.getX() + 45, exitButton.getY() + 50);
		g.drawString(score, highScoresButton.getX() + 25, highScoresButton.getY() + 50);

		if(startButton.getButtonBounds().contains(mouseInput.getX(), mouseInput.getY())){

			g.setColor(Color.RED);
			g2d.fill(startButton.getButtonBounds());
			g.setColor(Color.BLUE);
			g.drawString(start, startButton.getX() + 45, startButton.getY() + 50);

			if(mouseInput.isClicked == true){
				game.pressedStart = true;

			}
		}

		if(exitButton.getButtonBounds().contains(mouseInput.getX(), mouseInput.getY())){
			
			g.setColor(Color.RED);
			g2d.fill(exitButton.getButtonBounds());
			g.setColor(Color.BLUE);
			g.drawString(exit, exitButton.getX() + 45, exitButton.getY() + 50);
			
			if(mouseInput.isClicked == true){
				System.exit(1);
			}
		}

		if(highScoresButton.getButtonBounds().contains(mouseInput.getX(), mouseInput.getY())){
			
			g.setColor(Color.RED);
			g2d.fill(highScoresButton.getButtonBounds());
			g.setColor(Color.BLUE);
			g.drawString(score, highScoresButton.getX() + 25, highScoresButton.getY() + 50);
		}
	}
}