package myPole;
import java.util.ArrayList;
import java.util.List;


/*
 * Data Access Object
 */
public class FigureDAO {

	List<Figure> allFigures = new ArrayList<Figure>();
	
	public void addFigure(Figure figure){
		allFigures.add(figure);
	}
	
	public List<Figure> getAllFigures(){
		return allFigures;
	}

}
