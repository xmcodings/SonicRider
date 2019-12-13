package game;

import java.util.ArrayList;


public class GameMain {

    public static boolean isPlay;
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ArrayList<Road> theRoads = new ArrayList<>();
        ArrayList<Coin> Coins= new ArrayList<>();
        ArrayList<Obstacle> obs = new ArrayList<>();
        
        Sonic sonic = new Sonic();
		GameView game = new GameView(theRoads, Coins, obs, sonic);
                
		GameController gamecontrol = new GameController(theRoads, game, sonic, Coins, obs);
		game.drawLayout();
		game.drawbackground();
	}
	
        public static void togglePlay()
        {
            if(isPlay)
            {
                isPlay = false;
            }
            else{
                isPlay = true;
            }
            
        }

}
