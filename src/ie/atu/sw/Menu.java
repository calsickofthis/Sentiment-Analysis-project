package ie.atu.sw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Menu {
	
	// Setup Scanner
	Scanner inputReader;
	String MenuPath = "./src/100-twitter-users/5tosucceed.txt";
	String LexiconPath = "./src/lexicons/textblob.txt";
	String OutputPath = "./src/out.txt";
	int URL = 0;


	public int getURL() {
	    return URL;
	}


	public void setURL(int uRL) {
	    URL = uRL;
	}


	/**
	 * This is my menu method - just have a scanner in it to red the users input
	 */
	public Menu() {
		inputReader = new Scanner(System.in);
	}
	
	
	/**
	 * This method activates my menu - which presents the user with the options listed
	 *  it also runs a do while loop with a switch statemen inside - this is ran from the Runner file
	 *  methods in bottom are where users set paths
	 */
	public boolean ActivateMenu() {
		
		// boolean var to kill menu when user selects [not used]
		boolean killMenu = false;
		
		// in var to determine choice
		int choice;
		
		// Paths defined by user
		String MenuPath = "", LexiconPath = "", OutputPath = "";

		do {
			// code block to be executed
			PrintMenu();
			
			//take in user input
			System.out.printf("Please choose from above list : ");
			choice = inputReader.nextInt();
			
			switch (choice) {
				  case 1:
					  System.out.println("choice 1");
					  MenuPath = SpecifyMenuPath();
					  System.out.println(MenuPath);
					  // Specify a text file
					  break;
				  case 2:
					  URL = SpecifyURL();
					  break;
				  case 3:
					  // specify an output file
					  OutputPath = SpecifyOutputPath();
					  break;
				  case 4:
					  // Configure Lexicons
					  LexiconPath = ConfigureLexicons();
					  break;
				  case 5:
					  //ExecuteAnalyseReport
					  return true;
				  case 7:
					  System.out.println("Thanks for using the software goodbye!");
					  killMenu = true;
					  break;
			}
		}
			while (killMenu == false);
		
		return false;
	}
	
	// start of My getters and setters

	/**
	 * getter for my MenuPath variable
	 */
	public String getMenuPath() {
		return MenuPath;
	}

	
	/**
	 * setter for my Menu path
	 */
	public void setMenuPath(String menuPath) {
		MenuPath = menuPath;
	}

	/**
	 * getter for my lexicon path
	*/
	public String getLexiconPath() {
		return LexiconPath;
	}

	/**
	 * setter for my lexicon path
	 */
	public void setLexiconPath(String lexiconPath) {
		LexiconPath = lexiconPath;
	}

	
	/**
	 * getter for my output path
	 */
	public String getOutputPath() {
		return OutputPath;
	}

	/**
	 * setter for my output path
	 */
	public void setOutputPath(String outputPath) {
		OutputPath = outputPath;
	}
	
	// end of My getters and setters



	/**
	 * method for printing pre built meny to console
	 */
	public void PrintMenu() {
		//You should put the following code into a menu or Menu class
		System.out.println(ConsoleColour.WHITE);
		System.out.println("************************************************************");
		System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
		System.out.println("*                                                          *");
		System.out.println("*             Virtual Threaded Sentiment Analyser          *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println("(1) Specify a Text File");
		System.out.println("(2) Specify a URL");
		System.out.println("(3) Specify an Output File (default: ./out.txt)");
		System.out.println("(4) Configure Lexicons");
		System.out.println("(5) Execute, Analyse and Report");
		System.out.println("(?) Optional Extras...");
		System.out.println("(?) Quit");
		
		//Output a menu of options and solicit text from the user
		System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
		System.out.print("Select Option [1-4]>");
		System.out.println("");		
	}
	
	// First Option results	
	/**
	 * method for when user selects that they want to specify menu path
	 */
	public String SpecifyMenuPath() {
		// define path
		String path;
		
		// ./src/100-twitter-users/5toSucceed.txt
		System.out.print("please input the path to the desired lexicon : ");
		// catch whitespace
		path = inputReader.nextLine();
		path = inputReader.nextLine();
		
		File myObj = new File(path);
		
		// check if output file exists
		if(myObj.exists()) {
			System.out.println("file exists!");
			return path;
		}else {
			System.out.println("File does not exist!");
			return null;
		}	
	}
	
	/**
	 * method for when user chooses to specify the URL path
	 */
	public int SpecifyURL() {
		// define var
		int URL;
		// query user to input path name
		System.out.print("Specify an existing output file:");		
		// save to var
		URL = inputReader.nextInt();
		return URL;
	}
	
	/**
	 * method for when user chooses to specify the output path
	 */
	public String SpecifyOutputPath() {
		
		// define var
		String outputPath;
		
		// query user to input path name
		System.out.print("Specify an existing output file:");		
		
		// save to var
		outputPath = inputReader.nextLine();
		outputPath = inputReader.nextLine();
		
		// new file (pass path)
		File outputFile = new File(outputPath);
		
		// check if output file exists
		if(outputFile.exists()) {
			System.out.println("exists");
			return outputPath;
		}else {
			System.out.println("Does not exist");
			return null;
		}
	}
	

	// Configure lexicons code
	/**
	 * method for when the user chooses they want to confiogure lexicons
	 */
	public String ConfigureLexicons() {
		
		// define var
		String LexiconPath;
		
		// query user to input path name
		System.out.print("Specify an existing output file:");	
		
		// save to var
		LexiconPath = inputReader.nextLine();
		LexiconPath = inputReader.nextLine();
		
		// new file (pass path)
		File outputFile = new File(LexiconPath);
		
		// check if output file exists
		if(outputFile.exists()) {
			System.out.println("exists");
			return LexiconPath;
		}else {
			System.out.println("Does not exist");
			return null;
		}
	}
	
//	//	Execute, analyse and Report
//	public boolean ExecuteAnalyseReport() {
//		
//		return true;
//		// define var
//		String LexiconPath;
//		
//		// query user to input path name
//		System.out.print("Specify an existing output file:");	
//		
//		// save to var
//		LexiconPath = inputReader.nextLine();
//		LexiconPath = inputReader.nextLine();
//		
//		// new file (pass path)
//		File outputFile = new File(LexiconPath);
//		
//		// check if output file exists
//		if(outputFile.exists()) {
//			System.out.println("exists");
//		}else {
//			System.out.println("Does not exist");
//		}
//	}

}

