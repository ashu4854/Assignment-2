/**
 * AssigTwo226.java
 * 
 * KIT107 Assignment 2 -- Harness Class
 * 
 * @author Julian Dermoudy
 * @version	3/8/2026
 * 
 * FILE IS COMPLETE but you can alter LIMIT and POSITIONS and also
 * 		enable/disable output during development
 */


import java.util.Scanner;


public class AssigTwo226
{
	/**
	 * main() -- entry point
	 * 
	 * @param args String[] -- command line arguments
	 * 
	 * Precondition: None
	 * Postcondition: A title will be displayed, a dataset read and stored,
	 * 					and then results will be shown on the screen for the
	 * 					specified searches.
	 * Informally: Read the datafile, store it, process it, and produce the
	 * 					results.
	 */
    public static void main(String []args)
    {
		// final local variables
		final String FILENAME = "votes.csv";
		final int LIMIT = 10;
		final int POSITIONS = 1;

		// non final local variables
		FileHandler myFile;
		Collection collection;
		Scanner sc;
		String candidate;
		int round;
		String outcome;

		// produce title
		System.out.println();
		System.out.println("Australia Votes 2026");
		System.out.println("====================\n");

		// initialise scanner
		sc = new Scanner(System.in);

		// initialise collection and read in ballots
		collection = new Collection(POSITIONS);
		myFile = new FileHandler(FILENAME, LIMIT);
		myFile.readFile(collection);
		collection.closeElection();

		/* comment when development of addBallotToCollection() and addBallotToCluster() is complete */
		System.out.println(collection.toString());
		/**/

		
		// produce histogram of raw votes per candidate
		collection.showDistribution();
		

	
		// display all preferences for a candidate of the users' choice
		System.out.print("Enter name of candidate: ");
		candidate = sc.nextLine();
		while ((! collection.isEmpty()) && (! collection.validCandidate(candidate)))
		{
			System.out.println(candidate + " not registered as a candidate, try again.");
			System.out.println();
			System.out.print("Enter name of candidate: ");
			candidate = sc.nextLine();
		}
		collection.showCandidateVotes(candidate);
	

		
		// conduct the distribution of preferences until the required number of people are elected
		System.out.print("Using the Hare-Clark Electoral System to fill " + POSITIONS);
		System.out.println(POSITIONS == 1 ? " vacancy..." : " vacancies...");
		round = 1;
		outcome = collection.distributePreferences();
		while (! outcome.equals(""))
		{
			System.out.println("Outcome of round " + round + ": " + outcome);
			collection.showDistribution();

			round++;
			outcome = collection.distributePreferences();
		}
		

		// close the Scanner object
		sc.close();
    }
}