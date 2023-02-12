public class GameCall{ 
    public void startGame(){ 
        Game start = new Game(); 
        start.gameStart(); 
        start.description(); 
        start.menu(); 
        start.displayCards(); 
        start.displayInitialArray(); 
        start.gamePlay(); 
    } 
} 