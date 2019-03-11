import java.awt.image.BufferedImage;

public class Background{
	
	private String backgroundPath = "/res-background/SpaceBackground.png"; 
	private ImageLoader imageLoader = Game.getImageLoader();
	private BufferedImage image;
	private int width, height;

	public Background(){

	}

	public BufferedImage getBackground(){
		image = imageLoader.loadImage(backgroundPath);
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
}