package myPole;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/*
 * Data Access Object
 */
public class FigureDAO implements Serializable{

	List<Figure> allFigures = new ArrayList<Figure>();
	
	public void addFigure(Figure figure){
		allFigures.add(figure);
	}
	
	public List<Figure> getAllFigures(){
		return allFigures;
	}

}
