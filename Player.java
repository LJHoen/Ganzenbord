package testProject;
import java.util.*;

public class Player {
	String name;
	boolean winner;
	int position;
	int skip;
	boolean freed;
	boolean trapped;
	
	
	Player(String name) {
		this.name = name;
		position = 0;
		skip = 0;
		freed = false;
		trapped = false;
		winner = false;
	}
	
	public int roll() {
		Random random = new Random();
		int roll = 0;
		while(roll == 0) {
			roll = random.nextInt(7);
		}
		return roll;
	}
	
	public void move(int roll) {
		if(skip == 0) {
			System.out.println(name + " rolled " + roll);
		}
		
		if(skip > 0) {
			skip -= 1;
		} else if(position + roll > 63) {
			position = 63 - (position + roll - 63);
			if(doubleCheck(position)) {
				position -= roll;
			}
			System.out.println(name + " tries to run past Finish and bounces back to " 
					+ position);
		} else if(position + roll == 25 || position + roll == 45 || position + roll == 58) {
			position = 0;
			System.out.println(name + " needs to move waaaaaay back to Start.");
		} else if(position + roll == 6) {
			position = 12;
		} else if(position + roll == 19) {
			skip = 1;
			position += roll;
		} else if(position + roll == 31) { 
			position += roll;
			trapped = true;
			System.out.println(name + " fell into the well and needs to wait to be freed");
		} else if( position + roll == 42) {
			position = 39;
			System.out.println(name + " lands on tile 42 and bounces back to tile 39");
		} else if(position + roll == 52) {
			skip = 3;
			position += roll;
		} else if (position + roll == 63) {
			position += roll;
			System.out.println(name + " wins the game!");
		} else {
			position += roll;
			if(doubleCheck(position)) {
				position += roll;
			}
		}
	}
	
	public boolean doubleCheck(int position) {
		int[] doubleMove = {10, 20, 30, 40, 50, 60};
		for(int i = 0; i < doubleMove.length; i++) {
			if(position == doubleMove[i]) {
				return true;
			}
		}
		return false;
	}		
	
	public void position() {
	
		if(position == 0) {
			System.out.println(name + " starts their turn at Start.");
		} else if(skip > 0) {
			System.out.println(name + " is at position " + position +
					" but cannot move for " + skip + " more rounds");
		} else {
			System.out.println(name + " is at position " + position + ".");
		}
		
	}
	
}