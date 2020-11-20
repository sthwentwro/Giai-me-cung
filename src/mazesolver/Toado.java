/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver;

/**
 *
 * @author Windows
 */
public class Toado {
    private int x;
    private int y;
    Toado parent;

    public Toado(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
    }

    public Toado(int x, int y, Toado parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    Toado getParent() {
        return parent;
    }
    
     void setX(int row){
        x = row;
    }
    
    void setY(int col){
        y = col;
    }
}
