import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author JonathanXSG & Adahid
 */
public class Main {

	public static Scanner input = new Scanner(System.in);
	private static ArrayList<String> fileNames = new ArrayList<>();
	private static BufferedWriter writter;
	private static int numOfCustomers = 0;
	private static Random rand = new Random();
	private static int prevTurn =0;
	private static int lastUID =0;


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int numOfFiles = 0;
		while(true){
			showMenu();
			try {
				menuSelector();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private static void makeCSVFile() throws IOException{
		String fileName = "inputX"+fileNames.size()+".csv";
		fileNames.add(fileName);
		writter = new BufferedWriter(new FileWriter(fileName));
		StringBuilder sb = new StringBuilder();
		int tempTurn = randomArrivalTurn(prevTurn);
		sb.append(tempTurn + ",");
		prevTurn = tempTurn;
		sb.append(lastUID++ + ",");
		sb.append((rand.nextInt(12)+2) + ",");
		sb.append("$"+rand.nextInt(15)+"."+rand.nextInt(100)+",");
		sb.append((rand.nextInt(20)+5)+","); 
		sb.append("/n");
		writter.write(sb.toString());
		writter.flush();
	}
	
	private static void makeMultipleCSV() throws IOException{
		System.out.println("How many test CSV files would you like to make?");
		int numOfFiles = getNumberAnswer();
		for(int i = 0 ; i<numOfFiles ; i++){
				makeCSVFile();
		}
	}
	
	private static int randomArrivalTurn(int turn){
		switch (rand.nextInt(3)) {
		case 0:
			return turn;
		case 1:
			return turn+1;
		case 2:
			return turn+rand.nextInt(10);
		default:
			return turn;
		}
	}
	/**
	 * Displays the Menu for the user
	 */
	private static void showMenu(){
		System.out.println();
		System.out.println("What would you like to do?");
		System.out.println("1) Create one CSV test case");
		System.out.println("2) Create Multiple CSV test cases");
		System.out.println("3) Create input.txt from created CSV files");
	}
	
	/**
	 * Method is used for selecting an option provided in the menu. 
	 * @throws IOException 
	 */
	private static void menuSelector() throws IOException{
		int answer= getNumberAnswer();
		switch (answer) {
		case 1:
			makeCSVFile();
			break;
		case 2:
			makeMultipleCSV();
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			
			break;
		default:
			System.out.println("That's not actually an option.");
			break;
		}
	}
	
	/**
	 * Method is used for receiving an integer answer from the user.
	 * @return answer of user
	 */
	private static int getNumberAnswer(){
		int answer=0;
		Boolean isNumber = false ;
		do {
			try{
				answer = Integer.parseInt(input.nextLine());
				isNumber =true;
			}
			catch(NumberFormatException e){
				System.out.println("Nope, that's not an integer.");
				System.out.println("Provide an integer answer.");
				System.out.println();
				isNumber = false;
			}
		} while (!isNumber);
		return answer;
	}

}
