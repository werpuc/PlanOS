package myPole;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;
import java.io.*;

/*
 * one code to rule them all
 * concept: names should speak for themselves 
 */

public class PoleOS implements Serializable {

	private static final long serialVersionUID = 1L;
	static Scanner sc = new Scanner(System.in);
	static FigureDAO figAll = new FigureDAO();
	static String userName;
	static boolean isNewUser; 
	static String fileName; 
	
	public static void main(String[] args) {
		
		String userInput;
		System.out.println("*************************");
		System.out.println("*        WELCOME        *");
		System.out.println("*************************");
		System.out.println("Who are you? Tell me your name.");
		userName = sc.nextLine().trim();
		Pattern onlyLetters = Pattern.compile("^[a-zA-Z]*$");
    	Matcher nameInLetters = onlyLetters.matcher(userName);
    	while(!nameInLetters.matches()){
    		System.out.println("Name should contain only letters! Try again.");
    		userName = sc.nextLine().trim();
    		nameInLetters = onlyLetters.matcher(userName);
    	}
		isNewUser = checkUser(userName);
		fileName = userName.toLowerCase()+"-data.ser";
		if(!isNewUser){
			System.out.println("Hello, "+userName+". Nice to see you again!");
			readPreviousData(fileName);
		}
		else System.out.println("Nice to meet you, "+userName+". Your record is created.");
		
		do{
			System.out.println("Select an option and submit enter, please: ");
			System.out.println("[1] Add new figure");
			System.out.println("[2] Previously added figures");
			System.out.println("[3] Save changes");
			System.out.println("[x] Escape");
			userInput = sc.nextLine().trim();
			if(userInput.equals("1")) addNewFigure();
			else if(userInput.equals("2")) showPreviousFigures();
			else if(userInput.equals("3")) writeChanges(fileName);
			else if(!(userInput.equalsIgnoreCase("x"))) System.out.println("Really?");
		}while(!(userInput.equalsIgnoreCase("x")));
	}

	private static void readPreviousData(String fileName){
		try(ObjectInputStream fromFile = new ObjectInputStream(new FileInputStream(fileName))){
			figAll = (FigureDAO) fromFile.readObject();      
	    } 
		catch (Exception e) {
			e.printStackTrace();
	        System.out.println("Something very bad happend when reading previous figures from file.");
	    }
		finally{
			System.out.println("Previous data downloaded");
		}
   
	}
	
	private static boolean checkUser(String name){
		// user already exists - false
		// never heard of this person - true
		String line;
		boolean isNew = true;
		name = name.toLowerCase();
		try(BufferedReader nameLine = new BufferedReader(new FileReader("users.txt"))){
			while((line=nameLine.readLine())!=null){
				if(line.equals(name)) isNew = false;				
			}
			if(isNew){
				try(PrintWriter newNameLine = new PrintWriter(new BufferedWriter(new FileWriter("users.txt", true)))){
					newNameLine.println("\n"+name);					
				}
				catch(Exception e){
					System.out.println("Something is very wrong with including new user's existence");
				}
			}
		}
		catch(Exception e){
			System.out.println("Something is very wrong with checking user's existence");
		}
		return isNew; 
	}
	
	private static void writeChanges(String fileName){
		// TODO : proper file to user
		try(ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(fileName))){
		    o.writeObject(figAll);
		} catch (Exception ex) {
			System.out.println("Something went really wrong when writing to file.");
	    	ex.printStackTrace();
	    } finally{
	    	System.out.println("Writting process is finished.");
	    }
	}
	
	private static void showPreviousFigures(){
		System.out.println("*************************");
		System.out.println("Already added:");
		for(Figure f : figAll.getAllFigures()){
			System.out.println(f.getFigureName());
		}
		System.out.println("*************************");
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
