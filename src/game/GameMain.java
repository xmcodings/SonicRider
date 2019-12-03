package game;

import java.util.ArrayList;


public class GameMain {

    public static boolean isPlay;
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
                ArrayList<Road> theRoads = new ArrayList<>();
                Sonic sonic = new Sonic();
		GameView game = new GameView(theRoads, sonic);
                
		GameController gamecontrol = new GameController(theRoads, game, sonic);
		game.drawLayout();
		
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
            System.out.println("game.Sonic.togglePlay()" + isPlay);
        }

}
