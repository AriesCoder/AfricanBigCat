package com.assigment3;
import java.util.Random;

/* 
* Panthera base class that simulates GPS information 
*/
public class Panthera extends PantheraGPS{
  

    //add attributes
    private int weight;
    private Float speed;

    // constructor    
    public Panthera(String name) {        
        // call the super-class (parent) to instatiate it        
        super(name);       
        
        // initialize attributes        
        this.setSpecies("panthera");  

        Random w = new Random();
        this.weight = w.nextInt(100-10) + 10; 

        this.setSpeed();

    }

    public void setSpeed(){
        Random s = new Random();        
            this.speed = minSpeed + s.nextFloat() * (maxSpeed - minSpeed);
        }

    public int getWeight(){
        return this.weight;
    }

    public Float getSpeed(){
        return this.speed;
    }
    
    // serializes attributes into a string    
    @Override // override superclass method    
    public String toString() {        
        String s;        
        // since the object is complex, we return a JSON formatted string        
        s = "{ ";        
        s += "name: " + this.name();        
        s += ", ";        
        s += "species: " + this.species();        
        s += ", ";        
        s += "longitude: " + this.longitude();        
        s += ", ";        
        s += "latitude: " + this.latitude();
        s += ", ";  
        s += "weight: " + this.getWeight() + " lbs";   
        s += ", ";  
        s += "speed: " + this.getSpeed();    
        s += " }";        
        return s;    
    }
    public void roar(){
        System.out.println("Rrrrrrrrrrroooooooaaaaarrrrr!");
    }
}
