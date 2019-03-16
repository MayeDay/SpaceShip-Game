import java.awt.Dimension;
import javax.swing.JFrame;

public class Frame extends JFrame{
	
	private String title;
	private int width, height;
	private Game game;
	private StartMenu startmenu;

	public Frame(int width, int height, String title, Game game){

		this.width = width;
		this.height = height;
		this.title = title;
		this.game = game;

		init();
	}

	private void init(){

		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));

		setResizable(false);
		setDefaultCloseOperation(3);
		setLocationRelativeTo(null);
		add(game);
		pack();
		setVisible(true);
		setTitle(game._TITLE);
		game.start();


	}

	public Game getGame(){
		return this.game;
	}


}