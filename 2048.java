package com.codegym.games.game2048;

import com.codegym.engine.cell.*;

public class Game2048 extends Game {


//variables for class
private static final int SIDE = 4;
private int [][] gameField = new int[SIDE][SIDE];
private boolean isGameStopped = false;// A private boolean isGameStopped variable must be created in the Game2048 class and initialized to false when it is declared.
private int score = 0;// A private int score variable must be created in the Game2048 class.


//initializes game screen and begins game

public void initialize(){
    
    setScreenSize(SIDE, SIDE);
    createGame();
    drawScene();
    
}
  
private void createGame(){
         score = 0; // The score must be reset when the game restarts.
        setScore(score);
    // The gameField matrix must be recreated in the createGame() method.  
     gameField = new int[SIDE][SIDE];
    createNewNumber(); //initial two numbers put in grid at start of game
    createNewNumber();
   
}  

//sets game board and puts numbers in grid.
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
    int b = getRandomNumber(SIDE);  //initializing blank coordinates
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
        
     int max =  getMaxTileValue();  // The createNewNumber() method must call the getMaxTileValue() method.
     if (max == 2048) {
            win();
        }  // The win() method must be called in the createNewNumber() method if the value 2048 is in the matrix.
}




//denotes color of each cell dependant on number value

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
//sets color of cells based apon getColorByValue method
 private void setCellColoredNumber(int x, int y, int value){
             
              Color color = getColorByValue(value);
             if (value == 0){
                      setCellValueEx(x,y, color, "");
             }
             else {
                 setCellValueEx(x,y, color, String.valueOf(value));
             }
             
              

   
 }
 

     //this method takes a row from the grid and then sorts empty fields to right and moves other
    //numbers to left without changing there order. This is only the beginning

    private boolean compressRow(int[] row) {
        boolean isChanged = false;
        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] == 0 && row[i] != row[i + 1]) {
                isChanged = true;
                row[i] = row[i + 1];
                row[i + 1] = 0;
            }
        }
        if (isChanged) {
            compressRow(row);
        }
        return isChanged;
    }


//Merge adjacent pairs of identical non-zero elements of the row array. 
//Merge should happen when tile shift to the left.
//If there are more than two consecutive 
//identical non-zero elements, merge the leftmost first.
//Merged elements are not merged again in the same move.
// The method must return true if at least one array element 
//was changed, otherwise — false.

private boolean mergeRow(int[] row){
    
            boolean mergeDone = false;
            
            for (int i = 0; i < row.length -1; i++) {
             if (row[i] != 0 && row[i] == row[i + 1]) {
                row[i] = row[i] + row[i + 1];
                row[i + 1] = 0;
                mergeDone = true;
                score = score + (row[i] + row[i + 1]);// 	If a merger occurs, the value of the score variable must increase by the sum of the values of the merged tiles.
                setScore(score); // If a tile merger occurs, the setScore(int) method must be called in the mergeRow(int[]) method as many times as there are mergers.
            }
      
  
}
return mergeDone;
}

@Override
    public void onKeyPress(Key key) {
        if (isGameStopped) {
            if (key == Key.SPACE) {
                isGameStopped = false;
                createGame();
                drawScene();
            } else {
                return;
            }
        }

        if (!canUserMove()) {
            gameOver(); // The onKeyPress(Key) method must call the gameOver() method and do nothing else, if the canUserMove() method returns false.
            return;
        }

        if (key == Key.UP) {
            moveUp();
        } else if (key == Key.RIGHT) {
            moveRight();
        } else if (key == Key.DOWN) {
            moveDown();
        } else if (key == Key.LEFT) {
            moveLeft();
        } else {
            return;
        }
        drawScene(); // Be sure that the onKeyPress(Key) method ends immediately after the call to the gameOver() method.
    }


 private void moveLeft(){
     
     boolean isChanged = false;
        for (int i = 0; i < SIDE; i++) {
            if (compressRow(gameField[i])) isChanged = true;// variable to get return from compressRow
            if (mergeRow(gameField[i])) isChanged = true; // variable to get return from mergeRow
            if (compressRow(gameField[i])) isChanged = true;
        } if (isChanged) {
            createNewNumber();
        }
     
 }
 private void moveRight(){
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
     
 }
 private void moveUp(){
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
     
 }
 private void moveDown(){
     
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
     
 }

private void rotateClockwise(){
           int [][] tmpField = new int[SIDE][SIDE];
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                tmpField [i][j] = gameField[gameField.length - j - 1][i];
            }
        }
        gameField = tmpField;
}


private int getMaxTileValue(){//The private int getMaxTileValue() method must be created in the Game2048 class. The method must return the maximum value in the gameField matrix.
         int max = gameField[0][0];
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                if (max < gameField[y][x]) {
                    max = gameField[y][x];
                }
            }
        }
        return max;
}

private void win() {
        isGameStopped = true;
        showMessageDialog(Color.GREY,"YOU WON",Color.GREEN,70);
    }

private void gameOver() {
        isGameStopped = true;//The isGameStopped variable must be set to true in the win() method.
        showMessageDialog(Color.GREY,"GAME OVER",Color.RED,70);//The showMessageDialog(Color, String, Color, int) method must be called in the win() method.
      

    }
    
    
    
 private boolean canUserMove() {
        for (int x = 0; x < SIDE; x++){
            for (int y = 0; y < SIDE; y++) {
                if (gameField[x][y] == 0) {
                    return true;
                }
                if (x < SIDE - 1) {
                    if (gameField[x][y] == gameField[x + 1][y]) {
                        return true;
                    }
                }
                if (y < SIDE-1) {
                    if (gameField[x][y] ==gameField[x][y + 1]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }



    
    


}

    
    
    

