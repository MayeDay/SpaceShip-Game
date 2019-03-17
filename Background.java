import java.awt.image.BufferedImage;

public class Background{
	
	private String backgroundPath = "/res-background/SpaceBackgroundEdit.png"; 
	private String menuBackground = "./res-background/title.JPEG";
	private ImageLoader imageLoader = Game.getImageLoader();
	private BufferedImage image;
	private BufferedImage menu;
	private int width, height;

	public Background(){
		image = imageLoader.loadImage(backgroundPath);
		menu = imageLoader.loadImage(menuBackground);
	}

	public BufferedImage getBackground(){
		
		width = image.getWidth();
		height = image.getHeight();

		return image;
	}

	public int getWidth(){
		return this.width;
	}
	public int getHeight(){
		return this.width;
	}

	public BufferedImage getMenu(){
		return this.menu;
	}

	public BufferedImage getImage(){
		return this.image;
	}
}