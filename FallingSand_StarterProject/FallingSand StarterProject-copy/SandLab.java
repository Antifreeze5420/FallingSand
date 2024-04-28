import java.awt.*;
import java.util.*;
import java.io.*;

public class SandLab
{
    public static void main(String[] args)
    {
        SandLab lab = new SandLab(60, 120);
        lab.run();
    }

    //add constants for particle types here
    public static final int EMPTY = 0;
    public static final int ROCK = 1;
    public static final int SAND = 2;
    public static final int WATER = 3;
    public static final int STEAM = 4;
    public static final int FISH = 5;
    //Lava
    //stone
    //
    
    public static final Color[] colors= new Color[]{Color.black,Color.gray,Color.yellow,Color.blue,Color.white,Color.orange};



    //do not add any more fields
    public ArrayList<int[]> arr = new ArrayList<int[]>();
    
    private block[][] grid = new block[60][120];
    
    private SandDisplay display;

    public SandLab(int numRows, int numCols)
    {
        String[] names;
        names = new String[6];
        names[EMPTY] = "Empty";
        names[ROCK] = "Rock";
        names[SAND] = "Sand";
        names[WATER] = "Water";
        names[STEAM] = "Steam";
        names[FISH] = "Fish";
        //Lava
        //Steam
        
        display = new  SandDisplay("Falling Sand", numRows, numCols, names);
    }

    //called when the user clicks on a location using the given tool
    private void locationClicked(int row, int col, int tool)
    {
        grid[row][col] = new block(row,col,tool);

        arr.add(new int[]{row,col});
   
    }

    //copies each element of grid into the display
    public void updateDisplay()
    {
        for(int i = 0; i<arr.size();i++){
            display.setColor(arr.get(i)[0],arr.get(i)[1],colors[grid[arr.get(i)[0]][arr.get(i)[1]].id]);
        }
        arr = new ArrayList<int[]>();
    }

    //called repeatedly.
    //causes one random particle to maybe do something.
    public void step()
    {
        int rand_x = (int)(Math.random()*60);
        int rand_y = (int)(Math.random()*120);
        boolean[] move = checkMovement(rand_x,rand_y);
        double b = Math.random();
        if(b>0.5){
                if (move[2] == grid[rand_x][rand_y].shift[2] && move[2] == true && move[3] == grid[rand_x][rand_y].shift[3] && move[3] == true){
            double a = Math.random();
            if (a>0.5){
                int d = grid[rand_x][rand_y].id;
                locationClicked(rand_x, rand_y, grid[rand_x][rand_y+1].id);
                locationClicked(rand_x,rand_y+1,d);
            } else{
                int d = grid[rand_x][rand_y].id;
                locationClicked(rand_x, rand_y, grid[rand_x][rand_y-1].id) ;
                locationClicked(rand_x,rand_y-1,d);
            }
        } 
        else if(move[2] == grid[rand_x][rand_y].shift[2] && move[2] == true){
            int d = grid[rand_x][rand_y].id;
            locationClicked(rand_x, rand_y, grid[rand_x][rand_y+1].id);
            locationClicked(rand_x,rand_y+1,d);
        }
        else if(move[3] == grid[rand_x][rand_y].shift[3] && move[3] == true){
            int d = grid[rand_x][rand_y].id;
            locationClicked(rand_x, rand_y, grid[rand_x][rand_y-1].id) ;
            locationClicked(rand_x,rand_y-1,d);
        }

            
        }
        if(rand_x != 59 && grid[rand_x][rand_y].grav == true && grid[rand_x+1][rand_y].id ==0){
            int d = grid[rand_x][rand_y].id;
                locationClicked(rand_x, rand_y, grid[rand_x+1][rand_y].id) ;
                locationClicked(rand_x+1,rand_y,d);
        }
        if (move[0] == grid[rand_x][rand_y].shift[0] && move[0] == true && move[1] == grid[rand_x][rand_y].shift[1] && move[1] == true){
            
             double a = Math.random();
            if (a>0.5){
                int d = grid[rand_x][rand_y].id;
                locationClicked(rand_x, rand_y, grid[rand_x+1][rand_y].id) ;
                locationClicked(rand_x+1,rand_y,d);
                
            }else {
                int d = grid[rand_x][rand_y].id;
                locationClicked(rand_x, rand_y, grid[rand_x-1][rand_y].id) ;
                locationClicked(rand_x-1,rand_y,d);
            }
        }
        else if(move[0] == grid[rand_x][rand_y].shift[0] && move[0] == true){
            int d = grid[rand_x][rand_y].id;
            locationClicked(rand_x, rand_y, grid[rand_x+1][rand_y].id) ;
            locationClicked(rand_x+1,rand_y,d);
            
        }
        else if(move[1] == grid[rand_x][rand_y].shift[1] && move[1] == true){
            int d = grid[rand_x][rand_y].id;
            locationClicked(rand_x, rand_y, grid[rand_x-1][rand_y].id) ;
            locationClicked(rand_x-1,rand_y,d);
        }
        if (move[2] == grid[rand_x][rand_y].shift[2] && move[2] == true && move[3] == grid[rand_x][rand_y].shift[3] && move[3] == true){
            double a = Math.random();
            if (a>0.5){
                int d = grid[rand_x][rand_y].id;
                locationClicked(rand_x, rand_y, grid[rand_x][rand_y+1].id);
                locationClicked(rand_x,rand_y+1,d);
            } else{
                int d = grid[rand_x][rand_y].id;
                locationClicked(rand_x, rand_y, grid[rand_x][rand_y-1].id) ;
                locationClicked(rand_x,rand_y-1,d);
            }
        } 
        else if(move[2] == grid[rand_x][rand_y].shift[2] && move[2] == true){
            int d = grid[rand_x][rand_y].id;
            locationClicked(rand_x, rand_y, grid[rand_x][rand_y+1].id);
            locationClicked(rand_x,rand_y+1,d);
        }
        else if(move[3] == grid[rand_x][rand_y].shift[3] && move[3] == true){
            int d = grid[rand_x][rand_y].id;
            locationClicked(rand_x, rand_y, grid[rand_x][rand_y-1].id) ;
            locationClicked(rand_x,rand_y-1,d);
        }
    }
    public void pause(int too){
        if(too == 2 || too== 3 || too == 4 || too == 5){
            display.pause(30);
        } 
    }
    //do not modify
    public void run()
    {   
        for(int i = 0;i<grid.length;i++){
            for(int j = 0; j<grid[0].length;j++){
                grid[i][j] = new block(i,j,0);
            }
        }
        while (true)
        {
            for (int i = 0; i < display.getSpeed(); i++)
                step();
            updateDisplay();
            display.repaint();
            //display.pause(0,1);  //wait for redrawing and for mouse
            pause(display.getTool());
            int[] mouseLoc = display.getMouseLocation();
            if (mouseLoc != null){  //test if mouse clicked
                locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
            }
        }
    }
    public boolean[] checkMovement(int x, int y){
        boolean [] a = new boolean[4];
        if (x>=59){
            a[0] = false;
        }else{
            a[0] = has(grid[x][y].canMove,grid[x+1][y].id);
        }
        if(x == 0){
            a[1] = false;
            
        }else{
            a[1] = has(grid[x][y].canMove,grid[x-1][y].id);
        }
        if(y >= 119){
            a[2] = false;
            
        }else{
            a[2] = has(grid[x][y].canMove,grid[x][y+1].id);
        }
        if(y == 0){
            a[3] = false;
        }else{
            a[3] = has(grid[x][y].canMove,grid[x][y-1].id);
        }
        
        return a;
    }   
    public boolean has(int[] a, int b){
        for(int i :a){
            if (i == b){
                return true;
            }
            
        }
        return false;
        
    }
    class block {
        public final String[] list=  {"Empty","Rock","Sand","Water","Steam","Fish"};
        public int id;
        public int[] canMove;
        public boolean[] shift;
        public String name;
        public int x;
        public int y;
        public boolean grav;
        block(int x_pos, int y_pos, int num){
             id = num;
             String name = list[num];
            if (id ==0){
                 canMove = new int[]{};
                 shift = new boolean[]{false, false, false, false};
                 grav = false;
            }else if(id == 1){
                 canMove =  new int[]{};
                 shift = new boolean[]{false, false, false, false};
                 grav = false;
            }else if(id == 2){
                 canMove = new int[]{0,3,4};
                 shift = new boolean[]{true, false, false, false};
                 grav = false;
            }else if(id == 3){
                shift = new boolean[]{true, false, true, true};
                canMove = new int[]{0,4};
                grav = false;
            }else if (id == 4){
                shift = new boolean[]{false, true, true, true};
                canMove = new int[]{0};
                grav = false;
            }
            else if(id == 5){
                shift = new boolean[]{true, true, true, true};
                canMove = new int[]{3};
                grav = true;
            }
            x = x_pos;
            y = y_pos;
        }
        
    }
}