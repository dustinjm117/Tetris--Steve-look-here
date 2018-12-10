
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TetrisController extends JPanel {

	public Point pieceOrigin;
	public int currentPiece;
	public int rotation;
	public ArrayList<Integer> nextPieces = new ArrayList<Integer>();
	public long score;
	public Color[][] well;
	public static int play;
	public static int howMany;
	public static int difficulty;
	public static int quit;
//points for the four pices that make up a single teris piece.
	public final Point[][][] pieces = { 

			// row-Piece
			{
				//1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) }, //rotate = 0
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) }, //rotate = 1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) }, //rotate = 2
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) } //rotate = 3
			},
			{
				//2
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) }, //rotate = 0
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) }, //rotate = 1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) }, //rotate = 2
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) } //rotate = 3
			},
			{
				//3
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) }, //rotate = 0
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) }, //rotate = 1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) }, //rotate = 2
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) } //rotate = 3
			},
			{
				//4
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) }, //rotate = 0
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) }, //rotate = 1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) }, //rotate = 2
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) } //rotate = 3
			},
			{
				//5
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) }, //rotate = 0
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) }, //rotate = 1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) }, //rotate = 2
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) } //rotate = 3
			},

			// J-Piece
			{
				//1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0) }, //rotate = 0 
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2) }, //rotate = 1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2) }, //rotate = 2
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0) } //rotate = 3
			},
			{
				//2
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0) }, //rotate = 0 
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2) }, //rotate = 1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2) }, //rotate = 2
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0) } //rotate = 3
			},
			{
				//3
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0) }, //rotate = 0 
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2) }, //rotate = 1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2) }, //rotate = 2
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0) } //rotate = 3
			},
			{
				//4
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0) }, //rotate = 0 
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2) }, //rotate = 1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2) }, //rotate = 2
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0) } //rotate = 3
			},
			{
				//5
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0) }, //rotate = 0 
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2) }, //rotate = 1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2) }, //rotate = 2
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0) } //rotate = 3
			},
			
			// L-Piece
			{
				//1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2) }, //rotate = 0
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2) }, //rotate = 1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0) }, //rotate = 2
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0) } //rotate = 3
			},
			{
				//2
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2) }, //rotate = 0
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2) }, //rotate = 1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0) }, //rotate = 2
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0) } //rotate = 3
			},
			{
				//3
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2) }, //rotate = 0
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2) }, //rotate = 1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0) }, //rotate = 2
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0) } //rotate = 3
			},
			{
				//4
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2) }, //rotate = 0
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2) }, //rotate = 1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0) }, //rotate = 2
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0) } //rotate = 3
			},
			{
				//5
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2) }, //rotate = 0
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2) }, //rotate = 1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0) }, //rotate = 2
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0) } //rotate = 3
			},

			// O-Piece
			{
				//1
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }, //rotate = 0
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }, //rotate = 1
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }, //rotate = 2
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) } //rotate = 3
			},
			{
				//2
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }, //rotate = 0
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }, //rotate = 1
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }, //rotate = 2
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) } //rotate = 3
			},
			{
				//3
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }, //rotate = 0
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }, //rotate = 1
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }, //rotate = 2
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) } //rotate = 3
			},
			{
				//4
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }, //rotate = 0
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }, //rotate = 1
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }, //rotate = 2
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) } //rotate = 3
			},
			{
				//5
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }, //rotate = 0
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }, //rotate = 1
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }, //rotate = 2
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) } //rotate = 3
			},

			// S-Piece
			{
				//1
				{ new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) }, //rotate = 0
				{ new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) }, //rotate = 1
				{ new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) }, //rotate = 2
				{ new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) } //rotate = 3
			},
			{
				//2
				{ new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) }, //rotate = 0
				{ new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) }, //rotate = 1
				{ new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) }, //rotate = 2
				{ new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) } //rotate = 3
			},
			{
				//3
				{ new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) }, //rotate = 0
				{ new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) }, //rotate = 1
				{ new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) }, //rotate = 2
				{ new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) } //rotate = 3
			},
			{
				//4
				{ new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) }, //rotate = 0
				{ new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) }, //rotate = 1
				{ new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) }, //rotate = 2
				{ new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) } //rotate = 3
			},
			{
				//5
				{ new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) }, //rotate = 0
				{ new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) }, //rotate = 1
				{ new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) }, //rotate = 2
				{ new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) } //rotate = 3
			},

			// T-Piece
			{
				//1
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1) }, //rotate = 0
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) }, //rotate = 1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2) }, //rotate = 2
				{ new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2) } //rotate = 3
			},
			{
				//2
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1) }, //rotate = 0
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) }, //rotate = 1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2) }, //rotate = 2
				{ new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2) } //rotate = 3
			},
			{
				//3
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1) }, //rotate = 0
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) }, //rotate = 1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2) }, //rotate = 2
				{ new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2) } //rotate = 3
			},
			{
				//4
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1) }, //rotate = 0
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) }, //rotate = 1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2) }, //rotate = 2
				{ new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2) } //rotate = 3
			},
			{
				//5
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1) }, //rotate = 0
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) }, //rotate = 1
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2) }, //rotate = 2
				{ new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2) } //rotate = 3
			},
			
			// Z-Piece
			{
				//1
				{ new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) }, //rotate = 0
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) }, //rotate = 1
				{ new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) }, //rotate = 2
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) } //rotate = 3
			},
			{
				//2
				{ new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) }, //rotate = 0
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) }, //rotate = 1
				{ new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) }, //rotate = 2
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) } //rotate = 3
			},
			{
				//3
				{ new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) }, //rotate = 0
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) }, //rotate = 1
				{ new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) }, //rotate = 2
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) } //rotate = 3
			},
			{
				//4
				{ new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) }, //rotate = 0
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) }, //rotate = 1
				{ new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) }, //rotate = 2
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) } //rotate = 3
			},
			{
				//5
				{ new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) }, //rotate = 0
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) }, //rotate = 1
				{ new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) }, //rotate = 2
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) } //rotate = 3
			}
	};//end pieces
	
//designates a color to each of the pieces that were just made.	
	public final Color[] shapeColor = 
		{
		Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA,
		Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA,
		Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA,
		Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA,
		Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA,
		Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA,
		Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA	
		};//end shapeColor
	
//used to draw the background of the well.
	public void well() {

		well = new Color[12][24];
		for (int rows = 0; rows < 12; rows++) 
		{
			for (int collumns = 0; collumns < 23; collumns++) 
			{
				if (rows == 0 || rows == 11 || collumns == 22) 
				{
					well[rows][collumns] = Color.DARK_GRAY;
				} 
				else 
				{
					well[rows][collumns] = Color.LIGHT_GRAY;
				}
			}
		}
		newPiece();
	}//end well

//this is how the game decides which piece comes next out of the 35 pieces.
	public void newPiece() 
	{
		pieceOrigin = new Point(4, 3);
		rotation = 0;
		if (nextPieces.isEmpty()) 
		{
			Collections.addAll(nextPieces, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34);
			Collections.shuffle(nextPieces);
		}
		currentPiece = nextPieces.get(0);
		nextPieces.remove(0);
	}//End newPiece
	
	//used to move the pieces horizontally.
		public void move(int m) 
		{
			if (!didCollide(pieceOrigin.x + m, pieceOrigin.y, rotation)) 
			{
				pieceOrigin.x = pieceOrigin.x + m;	
			}
			repaint();
		}//end move

//used to rotate the pieces. 	
	public void rotate(int r) 
	{
		int newRotation = (rotation + r);
		if(newRotation < 0)
		{
			newRotation = 3;
		}
		if(newRotation > 3)
		{
			newRotation = 0;
		}
		if (newRotation % 4 == 0) 
		{
			newRotation = 0;
		}
		else if (newRotation % 4 == 1) 
		{
			newRotation = 1;
		}
		else if (newRotation % 4 == 2) 
		{
			newRotation = 2;
		}
		else if (newRotation % 4 == 3) 
		{
			newRotation = 3;
		}
		if (!didCollide(pieceOrigin.x, pieceOrigin.y, newRotation))
		{
			rotation = newRotation;
		}
		repaint();
	}//end rotate

//whether it be due to timer or spacebar hit this is how the piece moves down.	
	public void dropPiece() 
	{
		if (!didCollide(pieceOrigin.x, pieceOrigin.y + 1, rotation)) 
		{
			pieceOrigin.y = pieceOrigin.y + 1;
		} 
		else 
		{
			fixToWell();
		}	
		repaint();
	} //end dropPiece
	
	//checks to see if the currentPiece will run into anything with the proposed change in location.
	public boolean didCollide(int x, int y, int rotation) 
	{
		for (Point p : pieces[currentPiece][rotation]) {
			if (well[p.x + x][p.y + y] != Color.LIGHT_GRAY) 
			{
				return true;
			}
		}
		return false;
	}//end didCollide

//this is how a piece becomes part of the background.	
	public void fixToWell() 
	{
		for (Point p : pieces[currentPiece][rotation]) 
		{
			well[pieceOrigin.x + p.x][pieceOrigin.y + p.y] = shapeColor[currentPiece];
		}
		clearRows();
		newPiece();
	} //end fixToWell
	

	//checks to see if an entire row is filled. 
		public void clearRows() 
		{
			boolean isFilled;	
			for (int row = 21; row > 0; row--) //This line also taken from StackOVerFlow, not sure why ++didnt work with reversed numbers like we tried but this is what StackOverFlow recomended.
			{
				isFilled = true;
				for (int column = 1; column < 11; column++) 
				{
					if (well[column][row] == Color.LIGHT_GRAY) 
					{
						isFilled = false;
						break;
					}
				}
				if (isFilled) 
				{
					deleteRow(row);
					row = row + 1;
					score = score + 1000;
				}
			}
		} //end clearRows

//ran if clearRow returns true, clears the colors from a row that it filled.	
	public void deleteRow(int rowDeleted) 
	{
		for (int rows = rowDeleted-1; rows > 0; rows--) 
		{
			for (int columns = 1; columns < 11; columns++) 
			{
				well[columns][rows+1] = well[columns][rows];
			}
		}
	}

//draws the currentPiece onto the well
	public void drawPiece(Graphics g) 
	{		
		g.setColor(shapeColor[currentPiece]);
		for (Point p : pieces[currentPiece][rotation]) 
		{
			g.fillRect((p.x + pieceOrigin.x) * 40, (p.y + pieceOrigin.y) * 40, 38, 38);
		}
		g.setColor(Color.CYAN);
		g.drawString("" + score, 37*12, 38);
	} //end drawPiece

	@Override 
//this replaced my drawWell method and got the background to stop printing over the other pieces.
	public void paintComponent(Graphics g)
	{
		g.fillRect(0, 0, 40*12, 40*23);
		for (int columns = 0; columns < 12; columns++) 
		{
			for (int rows = 0; rows < 23; rows++) 
			{
				g.setColor(well[columns][rows]);
				g.fillRect(40*columns, 40*rows, 38, 38);
			}
		}
		drawPiece(g);
//This code was pulled from the StackOverFlow website.
	}

//Run the main which makes the JFrame, adds the game and lets you play!
	public static void main(String[] args) 
	{
		String[] Play = { "Play Tetris", "Exit Game"};
		String[] selectDifficulty = { "Beginner", "Intermediate", "Hard", "Insane"};
		String[] playerCount = { "1", "2"};

		
		play = 1;
		while(play != 0)
		{
			quit = JOptionPane.showOptionDialog(null, "Would you like to play Tetris?", "Tetris", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, Play, "");
			if(quit != 0)
			{
				JOptionPane.showMessageDialog(null, "I Like that you tried... too bad I am going to make you play anyways. Since you seem incompetent about your ability to play Tetris, I kindly set the difficulty to beginner for you. ");
				difficulty = 0;
				break;
			}
			else
			{
			difficulty = JOptionPane.showOptionDialog(null, "Select the difficulty you wish to play at. If you try to exit, you will default to beginner.", "Difficutly", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, selectDifficulty, "");
			howMany = JOptionPane.showOptionDialog(null, "Pick how many players are going to play. If you try to exit, you will default to single player.", "Player Count", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, playerCount, "");
			break;
			}
		}
		
		JFrame TetrisGame = new JFrame("Tetris");
		TetrisGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TetrisGame.setSize(12*40+50, 23*40+50);
		TetrisGame.setVisible(true);
		final TetrisController Tetris = new TetrisController();
		Tetris.well();
		TetrisGame.add(Tetris);
//Ideas for the main method above this line come from StackOverFlow also
		
		if (howMany==0)
		{
		TetrisGame.addKeyListener(new KeyListener() 
		{
				
			public void keyTyped(KeyEvent e) 
			{
				
			}
			public void keyPressed(KeyEvent e) 
			{
				switch (e.getKeyCode()) 
				{
				
				case KeyEvent.VK_UP:
					Tetris.rotate(-1);
					break;

				case KeyEvent.VK_DOWN:
					Tetris.rotate(+1);
					break;

				case KeyEvent.VK_LEFT:
					Tetris.move(-1);
					break;

				case KeyEvent.VK_RIGHT:
					Tetris.move(+1);
					break;

				case KeyEvent.VK_SPACE:
					Tetris.dropPiece();
					Tetris.score = Tetris.score + 10;
					break;
				} 
			}
			public void keyReleased(KeyEvent e) 
			{

			}
		});
		}
		
		else
		{
			TetrisGame.addKeyListener(new KeyListener() 
			{
					
				public void keyTyped(KeyEvent e) 
				{
					
				}
				public void keyPressed(KeyEvent e) 
				{
					switch (e.getKeyCode()) 
					{
					
					case KeyEvent.VK_UP:
						Tetris.rotate(-1);
						break;
						
					case KeyEvent.VK_W:
						Tetris.rotate(-1);
						break; 

					case KeyEvent.VK_DOWN:
						Tetris.rotate(+1);
						break;
						
					case KeyEvent.VK_S:
						Tetris.rotate(+1);
						break;

					case KeyEvent.VK_LEFT:
						Tetris.move(-1);
						break;
						
					case KeyEvent.VK_A:
						Tetris.move(-1);
						break;

					case KeyEvent.VK_RIGHT:
						Tetris.move(+1);
						break;
						
					case KeyEvent.VK_D:
						Tetris.move(+1);
						break;

					case KeyEvent.VK_SPACE:
						Tetris.dropPiece();
						Tetris.score = Tetris.score + 10;
						break;
						
					case KeyEvent.VK_Z:
						Tetris.dropPiece();
						Tetris.score = Tetris.score + 10;
						break;
					} 
				}
				public void keyReleased(KeyEvent e) 
				{

				}
			});
		}
//this is what allows the piece to drop after a set period of time.
		new Thread() {

			@Override 
			public void run() 
			{
				while (true) 
				{
					try 
					{
						if(difficulty == 0)
						{
							Thread.sleep(1000);
						}
						else if(difficulty == 1)
						{
							Thread.sleep(500);
						}
						else if(difficulty == 2)
						{
							Thread.sleep(250);
						}
						else if(difficulty == 3)
						{
							Thread.sleep(100);
						}
						else
						{
							Thread.sleep(1000);
						}
						Tetris.dropPiece();
						Tetris.score = Tetris.score + 1;
					} 
					catch ( InterruptedException e ) 
					{
						
					}
				}
			}
		}.start();
	}//end main
}//end class