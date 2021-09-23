package com.assigment3;
/* 
* Menu class for the african big cat app 
*/

import java.util.LinkedList;
import java.util.Scanner;

public class Menu {

     // attributes    
     private Scanner input;   

     // constructor    
     public Menu() {        
         // initialize attributes        
         this.input = new Scanner(System.in);
    }

    // prints the menu    
    public void print() {    

        printLine();        
        System.out.println("African Big Cats App");        
        printLine();       

        printCommand('c',"[C]reates a big cat");
        printCommand('d',"[D]eletes a big cat");  
        printCommand('f',"[F]inds a big cat");      
        printCommand('l',"[L]ists all big Cats");
        printCommand('r',"[R]isk report"); 
        printCommand('w',"[W]arning report");         
        printCommand('q',"[Q]uits");    

        printLine();  

    }    
    private static void printLine() {

        System.out.println("----------------------------------------------------------" );    

    }    
    private static void printCommand(Character command, String desc) {        
        
        System.out.printf("%s\t%s\n", command, desc);    
    }    

    // get first character from input    
    public Character getCommand() {        
        Character command = '_';        
        String rawInput = input.nextLine();   
        if (rawInput.length() > 0) {           
            rawInput = rawInput.toLowerCase();
            command = rawInput.charAt(0);        
        }        
        
        return command;    
    }    

    // command switch    
    public Boolean executeCommand(Character command, LinkedList<Panthera> catList) {

        Boolean success = true;        
        /*            
        TIP:            
        In this area of the code, the additional commands need to be created and added.     
        */        
        switch(command) {            
            case 'c':                
                executeCreate(catList);                
                break; 

            /* case 'd':                
                executeDelete(catList);                
                break;
            
            case 'f':                
                executeFind(catList);                
                break; 
            */
            case 'l':                
                executeList(catList);                
                break; 

            case 'q': 
                executeQuit();                
                break;  

            default:                
                System.out.println("ERROR: Unknown commmand");                
                success = false;          
        }        
        return success;    
    }    

    // update the position of all the cats    
    public void update(LinkedList<Panthera> catList) {     
       
        // update by moving all the cats        
            for (Panthera cat: catList) {            
                cat.move();        
            }
          
    }    

    // quit the app    
    public void executeQuit() {  

        // close the scannner        
        input.close(); 

        System.out.println();        
        printLine();
        System.out.println("Thank you for using the African Big Cats App!"); 
        printLine();
        System.out.println();    
    }  

    public Panthera getNewCat(String name) {        
        /*            
        TIP:            
        In this area of the code, students need to get input from the user for the type of cat             
        and create the correct type.            
        Currently, the code always create a Tiger.  But, support for Lions and Jaguars            
        also needs to be added.        
        */
        
        Panthera result = null;             
        
        while (result == null) {

            System.out.println("Enter a 1 for Tiger, 2 for Lion, 3 for Jaguard");
            Integer userChoose = input.nextInt();

            switch (userChoose){
                case 1:
                result = new Tiger(name);        
                break;

                case 2:
                result = new Lions(name); 
                break;

                case 3:
                result = new Jaguards(name);
                break;

                default:
                System.out.println("please 1, 2 or 3 only!");
                break;
            }
        }
        return result;
        
    }    

    // create a cat, if it's unique    
    public void executeCreate(LinkedList<Panthera> catList) {  
       
        /*            
        TIP:            
        In this area of the code, students would need to add in checking if thecat name            
        already exists in order to prevent duplicates        
        */      

        // get the name  
        System.out.print("Enter a name for the big cat to create: ");        
            String name = input.nextLine();        
           // System.out.println();   

        for (int i = 0; i < catList.size(); i++){
                             
            if (catList.get(i).name() == name){

                System.out.println("This name is existed. Please choose another one!");

                System.out.print("Enter a name for the big cat to create: ");        
                name = input.nextLine();        
                System.out.println();

                i = 0;
            }  

        }       
        Panthera cat = getNewCat(name);        
        catList.add(cat);    
        System.out.println("STATUS: " + name + " has been added.");
    }    

    // list all big cats     
    public void executeList(LinkedList<Panthera> catList) {        

        System.out.println();        
        printLine();        
        System.out.println("African Big Cat List");        
        printLine();   

        Panthera cat;        
        if (catList.size() > 0) {            
            for (Integer i = 0; i < catList.size(); i++) {                
                cat = catList.get(i);
                System.out.println(cat);
            }        
        } else {            
            System.out.println("There are no African Big Cats. :(");        
        }
        System.out.println();    
    }
    /*        
    TIP:        
    Additional methods and functionality need to be added to this class.    
    */
       
    

}
