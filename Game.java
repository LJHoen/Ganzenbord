package testProject;
import java.util.*;

public class Game {
	
	public static void main(String[] args) {
		ArrayList<Player> players = start();
		ArrayList<Integer> well = new ArrayList<Integer>();
		int roundNum = 1;
		while(true) {
			System.out.println("Round " + roundNum);
			boolean gameOver = (nextRound(players, well));
			if(gameOver) {
				break;
			}
			roundNum++;
		}
	}	
	
	public static ArrayList<Player> start() {
		Scanner myScanner = new Scanner(System.in);
		ArrayList<Player> players = new ArrayList<Player>();
		System.out.println("Please enter the amount of players: ");
		int input = myScanner.nextInt();
		for(int i = 0; i < input; i++) {
			System.out.println("Player " + (i+1) + " name:");
			String name = myScanner.next();
				players.add(new Player(name));
			
		}
		return players;
	}
	
	public static boolean nextRound(ArrayList<Player> players, ArrayList<Integer> well) {	
		int tie = 0;		
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).position != 23 && !(players.get(i).position == 31 && players.get(i).trapped == true)) {
				players.get(i).position();
				players.get(i).move(players.get(i).roll());
				if(players.get(i).position == 63) {
					return true;
				}
				wellFall(well, players, i);
				System.out.println(players.get(i).name + " ends their turn at position " + players.get(i).position);
				System.out.println("\n");
				try {
				    Thread.sleep(1000);
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			} else {
				tie++;
			}
		}
		if(tie == players.size()) {
			System.out.println("All geese are stuck or dead. Everyone loses!");
			return true;
		}
		tie = 0;
		return false;
	}
	
	public static void wellFall(ArrayList<Integer> well, ArrayList<Player> players, int playerNum) {
		if(well.size() > 0) {
			System.out.println(players.get(well.get(0)) + " is freed from the well as " + players.get(playerNum) + " falls in.");
			well.remove(0);
			well.add(playerNum);
		}
	}
}
