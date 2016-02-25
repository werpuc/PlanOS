package myPole;
import java.io.*;

/*
 * object - figure
 * now: simple object, only with name and type (default: figure)
 * TODO: add date, combo, mastered etc
 */

public class Figure implements Serializable{

	private String figureName;	
	//enum Type {FIGURE, SPIN, TRANSITION}
	//Type type; 
	
	Figure(){
		System.out.println("You must be kidding");
	}
	Figure(String figureName){
		this.figureName = figureName;
		//this.type = Type.FIGURE;
	}
	
	String getFigureName(){
		return this.figureName;
	}	
	void setFigureName(String newFigureName){
		this.figureName = newFigureName;
	}
	
/*	Type getFigureType(){
		return this.type;
	}	
	void setFigureType(Type newType){
		this.type = newType;
	} */
}
