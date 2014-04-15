package astroids;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComponent;

/**
 * 
 * This is the very ugly class which handles the actual drawing.
 * Real-time graphics in java is not really my strong suite. 
 * 
 * @author TheGrandmother
 */
@SuppressWarnings("serial")
class Comp extends JComponent{
	int width;
	int height;
	BufferedImage i1;
	BufferedImage i2;
	Graphics2D gr;
	int swap;
	
	
	public Comp(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		setPreferredSize(new Dimension(width,height));
		i1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		i2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		gr = this.i1.createGraphics();
		gr.setColor(Color.black);
		gr.fillRect(0,0, width, height);
		gr = this.i2.createGraphics();
		gr.setColor(Color.black);
		gr.fillRect(0,0, width, height);
		swap = 0;
	}
	
	public void paintComponent(Graphics g1){
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D)g1;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(swap == 0){
			g.drawImage(i1, null, 0, 0);
		}else {
			g.drawImage(i2, null, 0, 0);
		}
	}
	
	public void clearImage(){
		gr.setColor(Color.black);
		gr.fillRect(0,0, width, height);
	}
	
	public void drawTheSpace(Space s){
		s.drawSpace(gr);
	}
	
	public void printFps(long time){
		gr.setColor(Color.green);
		gr.setFont(new Font(Font.MONOSPACED,Font.BOLD,21));
		gr.drawString((int)(1./(time*0.001))+"", 0, height-10);
	}
	
	public void drawText(){
		String foo = "FOO::BAR";
		Font foo_font = new Font("Arial", Font.BOLD, (int)(200));
		Font time_font = new Font("Arial", Font.BOLD, (int)(170*.5));
		String time = new SimpleDateFormat("dd.MM.yy HH:mm:ss").format(new Date());
		String closed = "CLOSED";
		int foo_width = (int)foo_font.getStringBounds(foo, gr.getFontRenderContext()).getWidth();
		int foo_height =(int)foo_font.getStringBounds(foo, gr.getFontRenderContext()).getHeight();
		int time_width = (int)time_font.getStringBounds(time, gr.getFontRenderContext()).getWidth();
		
		gr.setColor(new Color(0,190,0));
		gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		gr.setFont(foo_font);
		gr.drawString(foo, (width-foo_width)/2-7, foo_height-50);
		
		gr.setFont(time_font);
		gr.drawString(time, (width-time_width)/2-10, foo_height+50);
		
		
		
		//Just set the font to something less ugly for everything else
		gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		gr.setFont(new Font(Font.MONOSPACED,Font.BOLD,12));
	}
	
	public void flip(){
		if (swap == 0) {
			swap =1;
			gr = this.i1.createGraphics();
		}else{
			swap= 0;
			gr = this.i2.createGraphics();
		}
	}
	
	

}