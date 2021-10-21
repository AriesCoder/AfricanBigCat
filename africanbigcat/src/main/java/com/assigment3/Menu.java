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
        
        input = new Scanner(System.in);   

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

            case 'd':                
                executeDelete(catList);                
                break;
            
            case 'f':                
                executeFind(catList);                
                break; 
            
            case 'l':                
                executeList(catList);                
                break; 

            case 'r':
                executeRiskReport(catList);
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

            System.out.print("Enter a 1 for Tiger, 2 for Lion, 3 for Jaguard: ");
            String userChoose = input.nextLine();
            System.out.println();
            switch (userChoose){
                case "1":
                result = new Tiger(name);        
                break;

                case "2":
                result = new Lions(name); 
                break;

                case "3":
                result = new Jaguards(name);
                break;

                default:
                System.out.println("ERROR: Invalid big cat type. Creating a tiger named " + name);
                result = new Tiger(name); 
                break;
            }
        }
        return result;
        
    }    

    // create a cat, if it's unique    
    public void executeCreate(LinkedList<Panthera> catList) {   

        // get the name  
        System.out.print("Enter a name for the big cat to create: ");        
            String name = input.nextLine();        
            System.out.println();   

        for (int i = 0; i < catList.size();){

                             
            if (catList.get(i).name().equals(name.toUpperCase())){

                System.out.println("This name is existed. Please choose another one!");
                System.out.println(); 

                System.out.print("Enter a name for the big cat to create: ");  
                name = input.nextLine();        
                System.out.println();

                i = 0;
            } else {
                i++;
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
                cat.setSpeed();
                System.out.println(cat);
                
            }        
        } else {            
            System.out.println("There are no African Big Cats. :(");        
        }
        System.out.println();    
    }

    public void executeDelete(LinkedList<Panthera> catList){
        System.out.print("Enter a Big Cat's name to delete: ");
        String nameToDelete = input.nextLine();

        for(int i = 0; i < catList.size(); i++ ){
            if (catList.get(i).name().toUpperCase().equals(nameToDelete.toUpperCase())){
                catList.remove(i);
                System.out.println(String.format("%s is deleted", nameToDelete));
                return;
            }
        }    
                System.out.println(String.format("%s is not exist", nameToDelete));
             
                
    }

    public void executeFind(LinkedList<Panthera> catList){
        System.out.print("Enter a cat's name/a part of a cat's name: ");
        String userInput = input.nextLine();
        boolean flag = true;
        for(int i = 0; i < catList.size(); i++){
            if (catList.get(i).name().toUpperCase().contains(userInput.toUpperCase())){
                flag = false;
                System.out.println(catList.get(i));
            }            
        }
        if (flag){
            System.out.println("Name is not found.");
        } 
    }
        
    public void executeRiskReport(LinkedList<Panthera> catList){
        Float longtitude1 = 0f;
        Float latitude1 = 0f;
        Float longtitude2 = 0f;
        Float latitude2 = 0f;
        boolean flag = true;
        String name1 = "";
        String name2 = "";

        //get user input        
        while(flag){
            System.out.print("\nEnter a first big cat's name: ");
            name1 = input.nextLine();

            for(int i = 0; i < catList.size(); i++){
                if(catList.get(i).name().toUpperCase().equals(name1.toUpperCase())){
                    longtitude1 = catList.get(i).longitude();
                    latitude1 = catList.get(i).latitude(); 
                    flag = false;               
                    break;
                }                         
            }
            if(flag){
            System.out.println(String.format("%s is not exist.", name1));            
            }
        }
        
        flag = true;
        while(flag){
            System.out.print("Enter a second big cat's name: ");
            name2 = input.nextLine();

            for(int j = 0; j < catList.size(); j++){
                if(catList.get(j).name().toUpperCase().equals(name2.toUpperCase())){
                    longtitude2 = catList.get(j).longitude();
                    latitude2 = catList.get(j).latitude();
                    flag = false;
                    break;
                }               
            }
            if(true){
                System.out.println(String.format("%s is not exist.", name2));  
            }
        }

        Float distance = (float) Math.sqrt(Math.pow(longtitude2 - longtitude1, 2) + Math.pow(latitude2 - latitude1, 2));
        System.out.println(String.format("\nThe distance between %s and %s is %f", name1, name2, distance));

    }
        
}    
