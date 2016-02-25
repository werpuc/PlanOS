package myPole;
import java.util.Scanner;


/*
 * this is the main battlefield
 */

public class PoleOS {

	static Scanner sc = new Scanner(System.in);
	static FigureDAO figAll = new FigureDAO();
	
	public static void main(String[] args) {
		
		String userInput;
		System.out.println("*************************");
		System.out.println("*        WELCOME        *");
		System.out.println("*************************");
		do{
			System.out.println("Select an option and submit enter, please: ");
			System.out.println("[1] Add new figure");
			System.out.println("[2] Previously added figures");
			System.out.println("[3] Write it to the file");
			System.out.println("[x] Escape");
			userInput = sc.nextLine().trim();
			if(userInput.equals("1")) addNewFigure();
			else if(userInput.equals("2")) showPreviousFigures();
			else if(userInput.equals("3")) writeChanges();
			else if(!(userInput.equalsIgnoreCase("x"))) System.out.println("Really?");
		}while(!(userInput.equalsIgnoreCase("x")));
	}

	private static void writeChanges(){
		System.out.println("*************************");
		System.out.println("Where to, sir?");
		// TODO : write to file
		
		
		  
	}
	
	private static void showPreviousFigures(){
		System.out.println("*************************");
		System.out.println("Already added:");
		for(Figure f : figAll.getAllFigures()){
			System.out.println(f.getFigureName());
		}		
	}
	
	private static void addNewFigure(){
		System.out.println("*************************");
		System.out.println("Creating new figure, with a little help from my friends:" );
		System.out.println("Submit new name:");
		String newName = sc.nextLine();
		Figure newFig = new Figure(newName);
		String userAnswer;
		do{
			System.out.println("It's a figure, right? y/n");
			userAnswer = sc.nextLine();
			if(userAnswer.equalsIgnoreCase("y")){
				System.out.println("What a relief!");
			}
			else if(userAnswer.equalsIgnoreCase("n")){
				System.out.println("We'll deal with it later.");
			}
			else System.out.println("Stick to the rules, would you?");
		} while(!(userAnswer.equalsIgnoreCase("y") || userAnswer.equalsIgnoreCase("n")));
		figAll.addFigure(newFig);
	}
	
	
}
