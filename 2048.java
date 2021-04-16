package com.codegym.games.game2048;

import com.codegym.engine.cell.*;

public class Game2048 extends Game {

private static final int SIDE = 4;
private int [][] gameField = new int[SIDE][SIDE];


public void initialize(){
    
    setScreenSize(SIDE, SIDE);
    createGame();
    drawScene();
}
  
private void createGame(){
    createNewNumber();
    createNewNumber();
    
    
    
}  
  
private void drawScene(){
    for (int x=0;x < SIDE;x++){
        
     for (int y=0;y < SIDE;y++) {
     
    //  setCellColor( x,  y, Color.RED );  
    setCellColoredNumber(x, y, gameField[y][x]);
     }
    }
   
      
}


    // this method will create a new number on the board
    private void createNewNumber(){
        // here we get random numbers from the getRandomNumber method
        // b and c will align to the dimensions of the game board
    int a = getRandomNumber(10);
    int b = getRandomNumber(SIDE);
    int c = getRandomNumber(SIDE);
    
    // here we check to see if a random tile is empty
        if (gameField[b][c]==0){
            // here we check to see if a returns the value of 9
       if (a==9){
           // when a is 9 we assign the random tile the value of 4
           // it has a 10 percent chance of being 4 
       gameField[b][c]=4;
       } else {
           // now it has 90 percent chance of being 2
    gameField[b][c]=2; 
       }
    } else {
        // this is an example of recursion
        // the method is calling itself to meet its condtional where it will properly stop
     createNewNumber();
        }
        
        
        
}






private Color getColorByValue(int value) {
    if(value==0){
        return Color.NONE;
        
    }
        else if(value==2){
            return Color.BLUE;
            
        }
        else if(value==4){
            return Color.ORANGE;
            
        }
        else if(value==8){
            return Color.RED;
            
        }
        else if(value==16){
            return Color.PURPLE;
            
        }
        else if(value==32){
            return Color.GREEN;
            
        }
        else if(value==64){
            return Color.YELLOW;
            
        }
        else if(value==128){
            return Color.PINK;
            
        }
        else if(value==256){
            return Color.INDIGO;
            
        }
        else if(value==512){
            return Color.BROWN;
            
        }
        else if(value==1024){
            return Color.GREY;
            
        }
        else {
            return Color.BLACK;
            
        }
    
     
}

 private void setCellColoredNumber(int x, int y, int value){
             
              Color color = getColorByValue(value);
             if (value == 0){
                      setCellValueEx(x,y, color, "");
             }
             else {
                 setCellValueEx(x,y, color, String.valueOf(value));
             }
             
              

   
 }
 







}
    

