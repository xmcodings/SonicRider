package game;

import java.util.ArrayList;

public class GameMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
                ArrayList<Road> theRoads = new ArrayList<>();
		GameView game = new GameView(theRoads);
		GameController gamecontrol = new GameController(theRoads, game);
		game.drawLayout();
		
	}

}
