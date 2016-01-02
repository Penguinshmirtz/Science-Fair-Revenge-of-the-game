package battleShipWithEvolution;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.colorchooser.ColorSelectionModel;

public class SimpleGraphics {
	//Class scope variables+

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private static final JFrame frame = new JFrame();
	private static boolean hasConfiguredJFrame = false;
	//A hash map of all supported colors so the user can enter in a string instead of a Color object
	private static HashMap<String, Color> colors = getColorsMap();

	/**
	 * Used internally to generate a map of strings to colors so the user can just enter
	 * in a string instead of needing to import the Color class
	 * @return	A HashMap mapping the name of a color to its respective color object
	 */
	private static HashMap<String, Color> getColorsMap()
	{
		HashMap<String, Color> colors = new HashMap<String, Color>();
		colors.put("blue", Color.blue);
		colors.put("pink", Color.pink);
		colors.put("red", Color.red);
		colors.put("magenta", Color.magenta);
		colors.put("grey", Color.gray);
		colors.put("gray", Color.gray);
		colors.put("black", Color.black);
		colors.put("white", Color.white);
		colors.put("green", Color.green);
		colors.put("yellow", Color.yellow);
		colors.put("orange", Color.orange);
		colors.put("purple", new Color(128, 0, 128));
		colors.put("sky", new Color(135, 206, 235));

		return colors;
	}

	/**
	 * This method adds text to the screen given just an x coordinate, y coordinate, and the text
	 * @param x		The left x coordinate of the text box
	 * @param y		The top y coordinate of the text box
	 * @param text	The text to display
	 */
	public static void addText(int x, int y, String text)
	{
		configureJFrame();
		frame.getContentPane().add(new Text(x, y, text));
		frame.setVisible(true);
	}

	/**
	 * This method adds text to the screen given an x coordinate, y coordinate, font size,
	 * and the actual text
	 * @param x		The left x coordinate of the text box
	 * @param y		The top y coordinate of the text box
	 * @param text	The text to display
	 * @param fontSize	The font size of the text to be displayed
	 */
	public static void addText(int x, int y, String text, int fontSize)
	{
		configureJFrame();
		frame.getContentPane().add(new Text(x, y, text, fontSize));
		frame.setVisible(true);
	}

	/**
	 * This methods adds text to the screen given an x coordinate, y coordinate, and font
	 * @param x		The left x coordinate of the text box
	 * @param y		The top left y coordinate of the text box
	 * @param text	The text to be displayed
	 * @param font	The name of the font to be displayed.
	 */
	public static void addText(int x, int y, String text, String font)
	{
		configureJFrame();
		frame.getContentPane().add(new Text(x, y, text, font));
		frame.setVisible(true);
	}

	/**
	 * This method adds text to the screen given an x coordinate, y coordinate, font, font size,
	 * and the actual text to be displayed
	 * @param x		The left x coordinate of the text box
	 * @param y		The top left y coordinate of the text box
	 * @param text	The actual text to be displayed
	 * @param font	The name of the font
	 * @param fontSize	The size of the font.
	 */
	public static void addText(int x, int y, String text, String font, int fontSize)
	{
		configureJFrame();
		frame.getContentPane().add(new Text(x, y, text, fontSize, font));
		frame.setVisible(true);
	}

	/**
	 * This method adds text to the screen of a provided color with just the x and y coordinates
	 * as well as the text to be displayed
	 * @param x		The left x coordinate of the text box
	 * @param y		The top left y coordinate of the text box
	 * @param text	The text to be displayed
	 * @param color	The name of the color of the text to be displayed as a String.
	 */
	public static void addTextWithColor(int x, int y, String text, String color)
	{
		if(colors.containsKey(color.toLowerCase()))
		{
			Color c = colors.get(color.toLowerCase());
			configureJFrame();
			frame.getContentPane().add(new Text(x, y, text, c));
			frame.setVisible(true);
		}
		else
		{
			System.err.println("Invalid color");
		}
	}

	/**
	 * This method adds text to the screen of a provided color with the x and y coordinates,
	 * and the size of the font as well as the text to be displayed
	 * @param x		The left x coordinate of the text box
	 * @param y		The top left y coordinate of the text box
	 * @param text	The text to be displayed
	 * @param fontSize	The size of the font.
	 * @param color	The name of the color of the text to be displayed as a String.
	 */
	public static void addTextWithColor(int x, int y, String text, int fontSize, String color)
	{
		if(colors.containsKey(color.toLowerCase()))
		{
			Color c = colors.get(color.toLowerCase());
			configureJFrame();
			frame.getContentPane().add(new Text(x, y, text, fontSize,c));
			frame.setVisible(true);
		}
		else
		{
			System.err.println("Invalid color");
		}
	}

	/**
	 * This method adds text to the screen of a provided color with the x and y coordinates,
	 * and the name of the font as well as the text to be displayed
	 * @param x		The left x coordinate of the text box
	 * @param y		The top left y coordinate of the text box
	 * @param text	The text to be displayed
	 * @param font	The name of the font to be displayed
	 * @param color	The name of the color of the text to be displayed as a String.
	 */
	public static void addTextWithColor(int x, int y, String text, String font, String color)
	{
		if(colors.containsKey(color.toLowerCase()))
		{
			Color c = colors.get(color.toLowerCase());
			configureJFrame();
			frame.getContentPane().add(new Text(x, y, text, font, c));
			frame.setVisible(true);
		}
		else
		{
			System.err.println("Invalid color");
		}
	}

	/**
	 * This method adds text to the screen of a provided color with the x and y coordinates,
	 * the name and size of the font, as well as the text to be displayed
	 * @param x		The left x coordinate of the text box
	 * @param y		The top left y coordinate of the text box
	 * @param text	The text to be displayed
	 * @param font	The name of the font to be displayed
	 * @param fontSize	The size of the font.
	 * @param color	The name of the color of the text to be displayed as a String.
	 */
	public static void addTextWithColor(int x, int y, String text, String font, int fontSize, String color)
	{
		if(colors.containsKey(color.toLowerCase()))
		{
			Color c = colors.get(color.toLowerCase());
			configureJFrame();
			frame.getContentPane().add(new Text(x, y, text, fontSize, font, c));
			frame.setVisible(true);
		}
		else
		{
			System.err.println("Invalid color");
		}
	}

	/**
	 * This method draws a circle with a given x coordinate, y coordinate, and radius
	 * @param x		The x coordinate of the leftmost part of the circle
	 * @param y		The y coordinate of the top left corner of the circle
	 * @param radius	The radius of the circle to be drawn
	 */
	public static void drawCircle(int x, int y, int radius)
	{
		configureJFrame();
		frame.getContentPane().add(new Oval(x, y, radius * 2, radius * 2));
		frame.setVisible(true);
	}
	
	/**
	 * This method draws a circle of a given thickness
	 * with a given x coordinate, y coordinate, and radius
	 * @param x		The x coordinate of the leftmost part of the circle
	 * @param y		The y coordinate of the top left corner of the circle
	 * @param radius	The radius of the circle to be drawn
	 * @param thickness	The thickness of the circle's border.
	 */
	public static void drawCircle(int x, int y, int radius, int thickness)
	{
		configureJFrame();
		frame.getContentPane().add(new Oval(x, y, radius * 2, radius * 2, thickness));
		frame.setVisible(true);
	}

	/**
	 * This method draws and fills (colors the background) of a circle with a given x and y coordinates,
	 * a radius, and a background color
	 * @param x		The x coordinate of the leftmost part of the circle
	 * @param y		The y coordinate of the top left corner of the circle
	 * @param radius	The radius of the circle to be drawn
	 * @param color	The color of the background of the circle
	 */
	public static void fillCircle(int x, int y, int radius, String color)
	{
		if(colors.containsKey(color.toLowerCase()))
		{
			Color c = colors.get(color.toLowerCase());
			configureJFrame();
			frame.getContentPane().add(new Oval(x, y, radius * 2, radius * 2, c));
			frame.setVisible(true);
		}
		else
		{
			System.err.println("Invalid Color");
		}
	}
	
	/**
	 * Draws an oval with a given x coordinate, y coordinate, radius in the x direction,
	 * and radius in the y direction
	 * @param x		The leftmost x coordinate of the oval
	 * @param y		The top left hand y coordinate of the oval
	 * @param r1	The radius in the x direction of the oval
	 * @param r2	The radius in the y direction of the oval
	 */
	public static void drawOval(int x, int y, int r1, int r2)
	{
		configureJFrame();
		frame.getContentPane().add(new Oval(x, y, r1 * 2, r2 * 2));
		frame.setVisible(true);
	}
	
	/**
	 * Draws an oval of a given thickness with a given x coordinate
	 * y coordinate, radius in the x direction, and radius in the y direction
	 * @param x		The leftmost x coordinate of the oval
	 * @param y		The top left hand y coordinate of the oval
	 * @param r1	The radius in the x direction of the oval
	 * @param r2	The radius in the y direction of the oval
	 * @param thickness	The thickness of the border of the oval.
	 */
	public static void drawOval(int x, int y, int r1, int r2, int thickness)
	{
		configureJFrame();
		frame.getContentPane().add(new Oval(x, y, r1 * 2, r2 * 2, thickness));
		frame.setVisible(true);
	}
	
	/**
	 * Draws and fills an oval of the given color with a given x coordinate
	 * y coordinate, radius in the x direction, and radius in the y direction
	 * @param x		The leftmost x coordinate of the oval
	 * @param y		The top left hand y coordinate of the oval
	 * @param r1	The radius in the x direction of the oval
	 * @param r2	The radius in the y direction of the oval
	 * @param color	A string representation of the color
	 */
	public static void fillOval(int x, int y, int r1, int r2, String color)
	{
		if(colors.containsKey(color.toLowerCase()))
		{
			Color c = colors.get(color.toLowerCase());
			configureJFrame();
			frame.getContentPane().add(new Oval(x, y, r1 * 2, r2 * 2, c));
			frame.setVisible(true);
		}
		else
		{
			System.err.println("Invalid Color");
		}
	}
	
	/**
	 * Draws a square given an x and y coordinates and a side length
	 * @param x		The x coordinate of the leftmost part of the square
	 * @param y		The y coordinate of the top left hand corner of the square
	 * @param sideLength	The side length of the square
	 */
	public static void drawSquare(int x, int y, int sideLength)
	{
		configureJFrame();
		frame.getContentPane().add(new Rectangle(x, y, sideLength, sideLength));
		frame.setVisible(true);	
	}
	
	/**
	 * Draws a square of a certain thickness given an x and y coordinates and a side length
	 * @param x		The x coordinate of the leftmost part of the square
	 * @param y		The y coordinate of the top left hand corner of the square
	 * @param sideLength	The side length of the square
	 * @param thickness		The thickness of the square's border.
	 */
	public static void drawSquare(int x, int y, int sideLength, int thickness)
	{
		configureJFrame();
		frame.getContentPane().add(new Rectangle(x, y, sideLength, sideLength, thickness));
		frame.setVisible(true);	
	}

	/**
	 * Draws and fills a square (colors its background) given an x and y coordinate,
	 * a side length, and a background color
	 * @param x		The x coordinate of the leftmost part of the square
	 * @param y		The y coordinate of the top left hand part of the square
	 * @param sideLength	The side length
	 * @param color		The background color of the square
	 */
	public static void fillSquare(int x, int y, int sideLength, String color)
	{
		if(colors.containsKey(color.toLowerCase()))
		{
			configureJFrame();
			Color c = colors.get(color.toLowerCase());
			frame.getContentPane().add(new Rectangle(x, y, sideLength, sideLength, c));
			frame.setVisible(true);
		}
		else
		{
			System.err.println("Invalid Color");
		}
		
	}

	/**
	 * Draws a rectangle given an x and y coordinates, a width, and a height
	 * @param x		The x coordinate of the leftmost part of the rectangle
	 * @param y		The y coordinate of the top left hand corner of the rectangle
	 * @param width		The width of the rectangle
	 * @param height	The height of the rectangle
	 */
	public static void drawRectangle(int x, int y, int width, int height)
	{
		configureJFrame();
		frame.getContentPane().add(new Rectangle(x, y, width, height));
		frame.setVisible(true);	
	}
	
	/**
	 * Draws a rectangle of a certain thickness given an x and y coordinates, a width, and a height
	 * @param x		The x coordinate of the leftmost part of the rectangle
	 * @param y		The y coordinate of the top left hand corner of the rectangle
	 * @param width		The width of the rectangle
	 * @param height	The height of the rectangle
	 */
	public static void drawRectangle(int x, int y, int width, int height, int thickness)
	{
		configureJFrame();
		frame.getContentPane().add(new Rectangle(x, y, width, height, thickness));
		frame.setVisible(true);	
	}

	/**
	 * Draws a rectangle given an x and y coordinates, a width, and a height
	 * @param x		The x coordinate of the leftmost part of the rectangle
	 * @param y		The y coordinate of the top left hand corner of the rectangle
	 * @param width		The width of the rectangle
	 * @param height	The height of the rectangle
	 * @param color		The name of the background color
	 */
	public static void fillRectangle(int x, int y, int width, int height, String color)
	{
		if(colors.containsKey(color.toLowerCase()))
		{
			Color c = colors.get(color.toLowerCase());
			configureJFrame();
			frame.getContentPane().add(new Rectangle(x, y, width, height, c));
			frame.setVisible(true);	
		}
		else
		{
			System.err.println("Invalid Color");
		}

	}

	/**
	 * Adds an image onto the canvas stored in the project folder (not src) given
	 * the image's name and the x and y coordinates of where to place the image
	 * @param x		The leftmost x coordinate of the image
	 * @param y		The y coordinate of the top left hand corner of the image
	 * @param name	The name of the image
	 * @return	Returns true if the image was found and added, false if the image could not be located
	 */
	public static boolean addImage(int x, int y, String name)
	{
		configureJFrame();
		boolean success = true;
		BufferedImage image;
		try 
		{
			image = ImageIO.read(new File(name));
			frame.getContentPane().add(new Image(x, y, image));
			frame.setVisible(true);	
		} 
		catch (IOException e) {
			System.out.println("That's not a valid file path. Make sure you put it in the project folder"
					+ ",but not in the src folder.");
			success = false;
		}
		return success;

	}

	/**
	 * Adds an image from the internet onto the canvas given coordinates and a URL.
	 * @param x		The leftmost x coordinate of the image
	 * @param y		The y coordinate of the top left hand corner of the image
	 * @param url	The url of the image.
	 * @return		Returns true if the image was found and added, false if the image could not be located
	 */
	public static boolean addImageFromInternet(int x, int y, String url)
	{
		configureJFrame();
		boolean success = true;
		try 
		{
			BufferedImage image = ImageIO.read(new URL(url));
			frame.getContentPane().add(new Image(x, y, image));
			frame.setVisible(true);	
		} 
		catch (IOException e) 
		{
			success = false;
		}
		return success;
	}

	/**
	 * This method draws a triangle given the points of each vertex
	 * @param point1X	The x coordinate of the first point
	 * @param point1Y	The y coordinate of the first point
	 * @param point2X	The x coordinate of the second point
	 * @param point2Y	The y coordinate of the second point
	 * @param point3X	The x coordinate of the third point
	 * @param point3Y	The y coordinate of the third point
	 */
	public static void drawTriangle(int point1X, int point1Y, int point2X, int point2Y, int point3X, 
			int point3Y)
	{
		configureJFrame();
		int [] xCoordinates = {point1X, point2X, point3X};
		int [] yCoordinates = {point1Y, point2Y, point3Y};
		frame.getContentPane().add(new Polygon(xCoordinates, yCoordinates));
		frame.setVisible(true);
	}

	/**
	 * This method draws a triangle given the points of each vertex with a given thickness
	 * @param point1X	The x coordinate of the first point
	 * @param point1Y	The y coordinate of the first point
	 * @param point2X	The x coordinate of the second point
	 * @param point2Y	The y coordinate of the second point
	 * @param point3X	The x coordinate of the third point
	 * @param point3Y	The y coordinate of the third point
	 * @param thickness	The thickness of the line between each point.
	 */
	public static void drawTriangle(int point1X, int point1Y, int point2X, int point2Y, int point3X, 
			int point3Y, int thickness)
	{
		configureJFrame();
		int [] xCoordinates = {point1X, point2X, point3X};
		int [] yCoordinates = {point1Y, point2Y, point3Y};
		frame.getContentPane().add(new Polygon(xCoordinates, yCoordinates, thickness));
		frame.setVisible(true);
	}

	/**
	 * This method draws and fills the background of a triangle given the points of each vertex
	 * @param point1X	The x coordinate of the first point
	 * @param point1Y	The y coordinate of the first point
	 * @param point2X	The x coordinate of the second point
	 * @param point2Y	The y coordinate of the second point
	 * @param point3X	The x coordinate of the third point
	 * @param point3Y	The y coordinate of the third point
	 * @param color		The background color of the triangle as a string.
	 */
	public static void fillTriangle (int point1X, int point1Y, int point2X, int point2Y, int point3X, 
			int point3Y, String color)
	{
		if(colors.containsKey(color.toLowerCase()))
		{
			Color c = colors.get(color.toLowerCase());
			configureJFrame();
			int [] xCoordinates = {point1X, point2X, point3X};
			int [] yCoordinates = {point1Y, point2Y, point3Y};
			frame.getContentPane().add(new Polygon(xCoordinates, yCoordinates, c));
			frame.setVisible(true);
		}
		else
		{
			System.err.println("Invalid Color");
		}

	}
	
	/**
	 * Draws any polygon with the given x and y coordinates in array
	 * @param xCoordinates	An array of x coordinates
	 * @param yCoordinates	An array of y coordinates
	 */
	public static void drawPolygon(int[] xCoordinates, int [] yCoordinates)
	{
		configureJFrame();
		frame.getContentPane().add(new Polygon(xCoordinates, yCoordinates));
		frame.setVisible(true);
	}
	
	/**
	 * Draws any polygon of a certain thickness with the given x and y coordinates in array
	 * @param xCoordinates	An array of x coordinates
	 * @param yCoordinates	An array of y coordinates
	 * @param thickness	The thickness of the polygon's border
	 */
	public static void drawPolygon(int[] xCoordinates, int [] yCoordinates, int thickness)
	{
		configureJFrame();
		frame.getContentPane().add(new Polygon(xCoordinates, yCoordinates, thickness));
		frame.setVisible(true);
	}
	
	/**
	 * Draws any polygon with a certain background color with the given x and y coordinates in array
	 * @param xCoordinates	An array of x coordinates
	 * @param yCoordinates	An array of y coordinates
	 * @param color	A string representation of the polygon's background color.
	 */
	public static void drawPolygon(int[] xCoordinates, int [] yCoordinates, String color)
	{
		if(colors.containsKey(color.toLowerCase()))
		{
			Color c = colors.get(color.toLowerCase());
			configureJFrame();
			frame.getContentPane().add(new Polygon(xCoordinates, yCoordinates, c));
			frame.setVisible(true);
		}
		else
		{
			System.err.println("Invalid Color");
		}
	}
	
	/**
	 * Draws a line between the given points
	 * @param x1	The x coordinate of the first point
	 * @param y1	The y coordinate of the first point
	 * @param x2	The x coordinate of the second point
	 * @param y2	The y coordinate of the second point
	 */
	public static void drawLine(int x1, int y1, int x2, int y2)
	{
		configureJFrame();
		frame.getContentPane().add(new Line(x1, y1, x2, y2));
		frame.setVisible(true);
	}

	/**
	 * Draws a line between the given points with a given thickness
	 * @param x1	The x coordinate of the first point
	 * @param y1	The y coordinate of the first point
	 * @param x2	The x coordinate of the second point
	 * @param y2	The y coordinate of the second point
	 * @param thickness		The thickness of the line
	 */
	public static void drawLine(int x1, int y1, int x2, int y2, int thickness)
	{
		configureJFrame();
		frame.getContentPane().add(new Line(x1, y1, x2, y2, thickness));
		frame.setVisible(true);
	}

	/**
	 * Draws a line between the given points given a certain color
	 * @param x1	The x coordinate of the first point
	 * @param y1	The y coordinate of the first point
	 * @param x2	The x coordinate of the second point
	 * @param y2	The y coordinate of the second point
	 * @param color	The color of the line to be drawn.
	 */
	public static void drawLine(int x1, int y1, int x2, int y2, String color)
	{
		if(colors.containsKey(color.toLowerCase()))
		{
			Color c = colors.get(color.toLowerCase());
			configureJFrame();
			frame.getContentPane().add(new Line(x1, y1, x2, y2, c));
			frame.setVisible(true);
		}
		else
		{
			System.err.println("Invalid Color");
		}

	}

	/**
	 * Draws a line between the given points given a color and thickness
	 * @param x1	The x coordinate of the first point
	 * @param y1	The y coordinate of the first point
	 * @param x2	The x coordinate of the second point
	 * @param y2	The y coordinate of the second point
	 * @param thickness		The thickness of the line to be drawn
	 * @param color		The color of the line to be drawn
	 */
	public static void drawLine(int x1, int y1, int x2, int y2, int thickness, String color)
	{
		if(colors.containsKey(color.toLowerCase()))
		{
			Color c = colors.get(color.toLowerCase());
			configureJFrame();
			frame.getContentPane().add(new Line(x1, y1, x2, y2, thickness, c));
			frame.setVisible(true);
		}
		else
		{
			System.err.println("Invalid Color");
		}

	}

	/**
	 * This method is called at the beginning of each method.
	 * If the JFrame has already been configured it does nothing.
	 * If it hasn't then it configures the JFrame with the proper properties.
	 */
	private static void configureJFrame()
	{
		if(!hasConfiguredJFrame)
		{
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(WIDTH, HEIGHT);
			frame.setVisible(true);
			hasConfiguredJFrame = true;
		}

	}

}
/**
 * The following classes all denote a specific type of shape or other type of graphic
 * that can be added onto the screen.
 */

/**
 * Class that represents text.
 */
class Text extends JComponent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Default Text Setting
	static final String defaultFont = "TimesRoman";
	static final int defaultFontSize = 12;
	//Properties than are adjusted by the user
	int x;
	int y;
	String text;
	int fontSize = 0;
	Color color = null;
	String font = null;

	/**
	 * Constructor method that initializes a text object with coordinates and the actual text to be
	 * shown
	 * @param x	The leftmost x coordinate of the text box
	 * @param y	The y coordinate of the top left hand corner of the text box
	 * @param text	The text to be displayed.
	 */
	public Text(int x, int y, String text)
	{
		this.x  = x;
		this.y = y;
		this.text = text;
	}

	/**
	 * Constructor method that initializes a text object with coordinates, a font size, 
	 * and the actual text to be shown
	 * @param x	The leftmost x coordinate of the text box
	 * @param y	The y coordinate of the top left hand corner of the text box
	 * @param text	The text to be displayed.
	 * @param fontSize	The font size of the text
	 */
	public Text(int x, int y, String text, int fontSize)
	{
		this.x  = x;
		this.y = y;
		this.text = text;
		this.fontSize = fontSize;

	}

	/**
	 * Constructor method that initializes a text object with coordinates, a font size, 
	 * a font name, and the actual text to be shown
	 * @param x	The leftmost x coordinate of the text box
	 * @param y	The y coordinate of the top left hand corner of the text box
	 * @param text	The text to be displayed.
	 * @param fontSize	The font size of the text
	 * @param font	The name of the font to be used.
	 */
	public Text (int x, int y, String text, int fontSize, String font)
	{
		this.x  = x;
		this.y = y;
		this.text = text;
		this.fontSize = fontSize;
		this.font = font;
	}

	/**
	 * Constructor method that initializes a text object with coordinates, a font name, 
	 * and the actual text to be shown
	 * @param x	The leftmost x coordinate of the text box
	 * @param y	The y coordinate of the top left hand corner of the text box
	 * @param text	The text to be displayed.
	 * @param font	The name of the font to be used
	 */
	public Text (int x, int y, String text, String font)
	{
		this.x = x;
		this.y = y;
		this.text = text;
		this.font = font;
	}

	/**
	 * Constructor method that initializes a text object with coordinates, a color,
	 * and the actual text to be displayed
	 * @param x	The leftmost x coordinate of the text box
	 * @param y	The y coordinate of the top left hand corner of the text box
	 * @param text	The text to be displayed.
	 * @param c	The color of the font to be displayed (as a Color object)
	 */
	public Text(int x, int y, String text, Color c)
	{
		this.x  = x;
		this.y = y;
		this.text = text;
		this.color = c;
	}

	/**
	 * Constructor method that initializes a text object with coordinates, a color,
	 * a font size, and the actual text to be displayed
	 * @param x	The leftmost x coordinate of the text box
	 * @param y	The y coordinate of the top left hand corner of the text box
	 * @param text	The text to be displayed.
	 * @param fontSize	The size of the font to be used.
	 * @param c	The color of the font to be displayed (as a Color object)
	 */
	public Text(int x, int y, String text, int fontSize, Color c)
	{
		this.x  = x;
		this.y = y;
		this.text = text;
		this.fontSize = fontSize;
		this.color = c;

	}

	/**
	 * Constructor method that initializes a text object with coordinates, a color,
	 * a font size, a font name, and the actual text to be displayed
	 * @param x	The leftmost x coordinate of the text box
	 * @param y	The y coordinate of the top left hand corner of the text box
	 * @param text	The text to be displayed.
	 * @param fontSize	The size of the font to be used.
	 * @param font	The name of the font to be used.
	 * @param c	The color of the font to be displayed (as a Color object)
	 */
	public Text (int x, int y, String text, int fontSize, String font, Color c)
	{
		this.x  = x;
		this.y = y;
		this.text = text;
		this.fontSize = fontSize;
		this.font = font;
		this.color = c;
	}


	/**
	 * Constructor method that initializes a text object with coordinates, a color,
	 * a font name, and the actual text to be displayed
	 * @param x	The leftmost x coordinate of the text box
	 * @param y	The y coordinate of the top left hand corner of the text box
	 * @param text	The text to be displayed.
	 * @param font	The name of the font to be used.
	 * @param c	The color of the font to be displayed (as a Color object)
	 */
	public Text (int x, int y, String text, String font, Color c)
	{
		this.x = x;
		this.y = y;
		this.text = text;
		this.font = font;
		this.color = c;
	}

	/**
	 * Overides paint method of JComponent to actually display the text
	 * with the parameters specified.
	 */
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		if(fontSize > 0 && font != null)
		{
			g2.setFont(new Font(font, Font.PLAIN, fontSize));
		}
		else if (fontSize > 0)
		{
			g2.setFont(new Font(defaultFont, Font.PLAIN, fontSize));
		}
		else if (font != null)
		{
			g2.setFont(new Font(this.font, Font.PLAIN, defaultFontSize));
		}
		else
		{
			g2.setFont(new Font(defaultFont, Font.PLAIN, defaultFontSize));
		}
		if(this.color != null)
		{
			g2.setColor(color);
		}
		g2.drawString(text, x, y);
	}
}
/**
 * Represents an image that can be added to the view.
 */
class Image extends JComponent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Parameters specified by user
	BufferedImage image;
	int x;
	int y;

	/**
	 * Constructor to initialize an image with the x and y coordinates and the image
	 * stored as a buffered image.
	 * @param x	The x coordinate of the leftmost part of the image
	 * @param y	The y coordinate of the top left hand corner of the image
	 * @param image	The image to be displayed as a buffered image
	 */
	public Image(int x, int y, BufferedImage image)
	{
		this.image = image;
		this.x = x;
		this.y = y;
	}

	/**
	 * Overrides JComponent paint to actually display the image.
	 */
	public void paint (Graphics g)
	{
		g.drawImage(image, x, y, null);
	}
}


/**
 * Represents any sort of rectangle that may be added to the view, regular or square.
 */
class Rectangle extends JComponent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Parameters specified by user
	int x;
	int y;
	int width;
	int height;
	Color c = null;
	int thickness = 0;

	/**
	 * Constructor which creates a rectangle given x and y coordinates as well as a width and height
	 * @param x	The leftmost x coordinate of the rectangle
	 * @param y	The top left y coordinate of the rectangle
	 * @param width	The width of the rectangle
	 * @param height	The height of the rectangle
	 */
	public Rectangle(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * Constructor which creates a rectangle of a certain color
	 * with given x and y coordinates as well as a width and height
	 * @param x	The leftmost x coordinate of the rectangle
	 * @param y	The top left y coordinate of the rectangle
	 * @param width	The width of the rectangle
	 * @param height	The height of the rectangle
	 * @param c		The color of the rectangle
	 */
	public Rectangle(int x, int y, int width, int height, Color c)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.c = c;
	}

	/**
	 * Constructor which creates a rectangle of a certain thickness
	 * with given x and y coordinates as well as a width and height
	 * @param x	The leftmost x coordinate of the rectangle
	 * @param y	The top left y coordinate of the rectangle
	 * @param width	The width of the rectangle
	 * @param height	The height of the rectangle
	 * @param thickness		The thickness of the rectangle
	 */
	public Rectangle(int x, int y, int width, int height, int thickness)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.thickness = thickness;
	}

	/**
	 * Overrides the JComponent paint method to actually draw
	 * the rectangle in the frame.
	 */
	public void paint(Graphics g) 
	{
		Graphics2D g2 = (Graphics2D) g;
		if(c != null)
		{
			g.setColor(c);
			g2.fillRect(x, y, width, height);
		}
		else
		{
			if (thickness > 0)
			{
				g2.setStroke(new BasicStroke(thickness));
			}
			g2.drawRect(x, y, width, height);

		}

	}
}

/**
 * A class that handles the drawing of circles
 */
class Oval extends JComponent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Properties set by user.
	int x;
	int y;
	int width;
	int height;
	Color c = null;
	int thickness = 0;

	/**
	 * Constructor that creates an oval at the given coordinates with
	 * the given width and height
	 * @param x		The x coordinate of the leftmost corner of the oval
	 * @param y		The y coordinate of the top left corner of the oval
	 * @param width	The width of the oval
	 * @param height	The height of the oval
	 */
	public Oval(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * Constructor that creates an oval of a certain color at 
	 * the given coordinates with the given width and height
	 * @param x		The x coordinate of the leftmost corner of the oval
	 * @param y		The y coordinate of the top left corner of the oval
	 * @param width	The width of the oval
	 * @param height	The height of the oval
	 * @param c	The color of the oval
	 */
	public Oval (int x, int y, int width, int height, Color c)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.c = c;
	}

	/**
	 * Constructor that creates an oval of a certain thickness at 
	 * the given coordinates with the given width and height
	 * @param x		The x coordinate of the leftmost corner of the oval
	 * @param y		The y coordinate of the top left corner of the oval
	 * @param width	The width of the oval
	 * @param height	The height of the oval
	 * @param thickness	The thickness of the oval
	 */
	public Oval (int x, int y, int width, int height, int thickness)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.thickness = thickness;
	}

	/**
	 * Overrides the JComponent paint method to actually draw the oval.
	 */
	public void paint(Graphics g) 
	{
		Graphics2D g2 = (Graphics2D) g;
		if(c != null)
		{
			g.setColor(c);
			g2.fillOval(x, y, width * 2, height * 2);
		}
		else
		{
			if (thickness > 0)
			{
				g2.setStroke(new BasicStroke(thickness));
			}
			g2.drawOval(x, y, width, height);

		}

	}
}

/**
 * This class serves to create any general polygon with vertices
 * at the given points. It's used both for general polygons and triangles.
 */
class Polygon extends JComponent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Properties set by user
	int [] xCoordinates;
	int [] yCoordinates;
	Color c = null;
	int thickness = 0;

	/**
	 * A constructor that creates a new polygon with the given coordinates as vertices
	 * @param xCoordinates	An array of x coordinates
	 * @param yCoordinates	An array of y coordinates
	 */
	public Polygon(int [] xCoordinates, int [] yCoordinates)
	{
		this.xCoordinates = xCoordinates;
		this.yCoordinates = yCoordinates;
	}

	/**
	 * A constructor that creates a new polygon of a given color
	 * with the given coordinates as vertices
	 * @param xCoordinates	An array of x coordinates
	 * @param yCoordinates	An array of y coordinates
	 * @param c	The color of the polygon
	 */
	public Polygon(int [] xCoordinates, int [] yCoordinates, Color c)
	{
		this.xCoordinates = xCoordinates;
		this.yCoordinates = yCoordinates;
		this.c = c;
	}

	/**
	 * A constructor that creates a new polygon of a certain thickness
	 * with the given coordinates as vertices
	 * @param xCoordinates	An array of x coordinates
	 * @param yCoordinates	An array of y coordinates
	 * @param thickness	The line thickness of the polygon.
	 */
	public Polygon(int [] xCoordinates, int [] yCoordinates, int thickness)
	{
		this.xCoordinates = xCoordinates;
		this.yCoordinates = yCoordinates;
		this.thickness = thickness;
	}

	/**
	 * Overrides JComponent paint to actually draw the shape.
	 */
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		if(c != null)
		{
			g2.setColor(c);
			g2.fillPolygon(xCoordinates, yCoordinates, xCoordinates.length);
		}
		else
		{
			if (thickness > 0)
			{
				g2.setStroke(new BasicStroke(thickness));
			}
			g2.drawPolygon(this.xCoordinates, this.yCoordinates, this.xCoordinates.length);
		}


	}
}

/**
 * This class handles the creation of all lines
 */
class Line extends JComponent 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Properties set by user
	int x1, y1, x2, y2;
	Color c = null;
	int thickness = 0;

	/**
	 * Constructor that creates a simple line between the given points
	 * @param x1	The x coordinate of the first point
	 * @param y1	The y coordinate of the first point
	 * @param x2	The x coordinate of the second point
	 * @param y2	The y coordinate of the second point
	 */
	public Line(int x1, int y1, int x2, int y2)
	{
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	/**
	 * Constructor that creates a line of a given thickness between the given points
	 * @param x1	The x coordinate of the first point
	 * @param y1	The y coordinate of the first point
	 * @param x2	The x coordinate of the second point
	 * @param y2	The y coordinate of the second point
	 * @param thickness	The thickness of the line
	 */
	public Line(int x1, int y1, int x2, int y2, int thickness)
	{
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.thickness = thickness;
	}
	
	/**
	 * Constructor that creates a line of a given thickness and color between the given points
	 * @param x1	The x coordinate of the first point
	 * @param y1	The y coordinate of the first point
	 * @param x2	The x coordinate of the second point
	 * @param y2	The y coordinate of the second point
	 * @param thickness	The thickness of the line
	 * @param c		The color of the line.
	 */
	public Line(int x1, int y1, int x2, int y2, int thickness, Color c)
	{
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.thickness = thickness;
		this.c = c;
	}

	/**
	 * Constructor that creates a line of a given color between the given points
	 * @param x1	The x coordinate of the first point
	 * @param y1	The y coordinate of the first point
	 * @param x2	The x coordinate of the second point
	 * @param y2	The y coordinate of the second point
	 * @param c		The color of the line.
	 */
	public Line(int x1, int y1, int x2, int y2, Color c)
	{
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.c = c;
	}

	/**
	 * Overrides JComponent paint to actually draw the line.
	 */
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		if(c != null)
		{
			g.setColor(c);
		}
		if (thickness > 0)
		{
			g2.setStroke(new BasicStroke(thickness));
		}
		g2.drawLine(x1, y1, x2, y2);
	}
}