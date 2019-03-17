import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class HighScoreMenu{
		
	private BufferedImage image;
	private Background background = new Background();
	private Handler handler;

	private String highScoreTitle = "High Scores!";
	private String back = "Return";
	private int list1 = 320;
	private int list = 250;
	private Button returnButton;
	private Button scrollUp;
	private Button scrollDown;

	private MouseInput mouseInput;
	private Game game;
	private Frame frame;


	public HighScoreMenu(MouseInput mouseInput, Frame frame, Handler handler, Game game){

		this.mouseInput = mouseInput;
		this.game = game;
		this.frame = frame;
		this.handler = handler;

		image = background.getMenu();

		init();
	}

	private void init(){

		returnButton = new Button(Game._WIDTH/2 - 100, Game._HEIGHT/2  + 270, 200, 75);
		scrollUp = new Button(300, 150, 400, 50);
		scrollDown = new Button(300, 600, 400, 50);
	}

	public Rectangle scrollBounds(){
		return (new Rectangle(300, 200, 400, 400));
	}

	protected void render(Graphics g){

		Graphics2D g2d = (Graphics2D)g;

		g.drawImage(image, 0, 0 ,1000, 800, null);

		g.setColor(new Color(2, 27, 149));
		g.setFont(new Font("Arial", Font.ITALIC, 50));
		g.drawString(highScoreTitle, 350, 100);

		g2d.fill(scrollBounds());
		g.setColor(new Color(75, 0, 115));

		g2d.fill(scrollUp.getButtonBounds());
		g2d.fill(scrollDown.getButtonBounds());
		g.setFont(new Font("Arial", Font.BOLD, 30));


		////////////////////////////////
		//High Scores listing
		g.setColor(Color.RED);
		for(int i = 0; i <= handler.highscores.size() - 1; i++){
			if(!handler.highscores.isEmpty()){
				if(scrollBounds().contains(list1, list)){
					g.setFont(new Font("Arial", Font.BOLD, 25));
					g.drawString(handler.highscores.get(i), list1, list);
				}
			}
		}






		////////////////////////////////////
		g.setColor(new Color(75, 0, 115));
		g2d.fill(returnButton.getButtonBounds());

		g.setColor(Color.BLUE);
		g.drawString(back, returnButton.getX() + 25, returnButton.getY() + 50);
		


		if(returnButton.getButtonBounds().contains(mouseInput.getX(), mouseInput.getY())){
			g.setColor(new Color(137, 0, 208));
			g2d.fill(returnButton.getButtonBounds());
			g.setColor(new Color(195, 0, 255));
			g.drawString(back, returnButton.getX() + 25, returnButton.getY() + 50);


			if(mouseInput.isClicked == true){
				game.pressedScores = false;
				game.pressedStart = false;
			}
		}

		if(scrollUp.getButtonBounds().contains(mouseInput.getX(), mouseInput.getY())){
			g.setColor(new Color(137, 0, 208));
			g2d.fill(scrollUp.getButtonBounds());

			if(mouseInput.isClicked == true){
				list-=5;
			}
		}

		if(scrollDown.getButtonBounds().contains(mouseInput.getX(), mouseInput.getY())){
			g.setColor(new Color(137, 0, 208));
			g2d.fill(scrollDown.getButtonBounds());

			if(mouseInput.isClicked == true){
				list+=5;
			}
		}
	}
}