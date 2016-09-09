/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacker;

/**
 *
 * @author SHOP
 */
public class BinPacker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    
    class Node{
        /*
        tree structure:
        node for each rectangle object
        - 2 to 4 children
        -each child represents all possible max-sized rectangles 
         that can fit in the leftover space plus next highest valued
         table that can fit in the bottom left hand corner. 
         (two children represent both a 0 degree rotated table and
          a 90 degree rotated table if possible)
        -if the child can not fit a table, disregard the child.
        */

        //these nodes are for first leftover rectangle
        //orientation 0, orientation 90
        Node child1;
        Node child2;
        
        //these nodes are for second leftover rectangle
        //orientation 0, orientation 90
        Node child3;
        Node child4;
        
        //size of empty space available for this node
        Rect size;
        
        //location of empty space within the original allotted space
        Rect location;
        
        //table that fits, if any
        Table table;
        
        void setLeftoverSpace(){
            if(!table.name.equals("NULL")){
                
                //since a table exists in this space, create child nodes
                child1 = new Node();
                child2 = new Node();
                child3 = new Node();
                child4 = new Node();
                
                //get first rectangle size
                Rect rect1 = new Rect(
                    size.width,
                    (size.height - table.size.height)
                );
                
                //get second rectangle size
                Rect rect2 = new Rect(
                    (size.width - table.size.width),
                    size.height
                );
                
                //set children to their sizes
                child1.size = rect1;
                child2.size = rect1;
                child3.size = rect2;
                child4.size = rect2;
                
            }
            //if table is null then no more tables can be inserted
            //into this space so children are left as null
        }

    }   

    class Rect{
        double width;
        double height;
        
        Rect(){
            width = 0;
            height = 0;
        }
        Rect(double newWidth, double newHeight){
            width = newWidth;
            height = newHeight;
        }
    }
    
    class Table{
        //if no table exists, name = "NULL"
        String name;
        Rect size;
        int orientation;
        
        Table(){
            name = "NULL";
            orientation = 0;
        }
    }

}
