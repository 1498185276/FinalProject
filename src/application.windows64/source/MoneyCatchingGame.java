import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class MoneyCatchingGame extends PApplet {

// Produced by Mingjing Huang | May 2021

Player player;
Car[] cars = new Car[8];
Motorcycle[] motorcycles = new Motorcycle[4];
ArrayList<Money> moneys;
int moneyEarned;
boolean play;
Timer moneyTimer;

public void setup(){
  
  play = false;
  moneys = new ArrayList();
  moneyEarned=0;
  moneyTimer = new Timer (5000);
  moneyTimer.start();
  
  for(int i=0; i<cars.length; i++){
    cars[i] = new Car();
  }
  
  for(int i=0; i<motorcycles.length; i++){
    motorcycles[i] = new Motorcycle();
  }
  
  player = new Player();
}

public void draw(){
  noCursor();
  if(!play){
    startScreen();
  } else{
  background(120);
  fill(255);
  rect(0,500,1000,8);
  rect(0,250,1000,2);
  rect(0,750,1000,2);
  
  for(int i=0; i<cars.length; i++){
    cars[i].display();
    cars[i].drive();
    
    if (player.carIntersection(cars[i])) {
        gameOver();
      }
  }
  
  for(int i=0; i<motorcycles.length; i++){
    motorcycles[i].display();
    motorcycles[i].drive();
    
    if (player.motorcycleIntersection(motorcycles[i])) {
        gameOver();
      }
  }
  
  if (moneyTimer.isFinished()) {
      moneys.add(new Money(PApplet.parseInt(random(width)), 0));
      moneyTimer.start();
    }
  
  for (int i = 0; i<moneys.size(); i++) {
      Money money = moneys.get(i);
      money.move();
      money.display();
      
      if (player.moneyIntersection(money)) {
        if (money.value == 0) { 
          moneyEarned+=1;
        } else if (money.value == 1) { 
          moneyEarned+=5;
        } else if (money.value == 2) { 
          moneyEarned+=10;
        } else if (money.value == 3){
          moneyEarned+=20;
        } else if (money.value == 4){
          moneyEarned+=100;
        }
        moneys.remove(money);
      }
      
      if (money.reachedBottom()) {
        moneys.remove(money);
      }
  }
  
  infoPanel();
 
  player.display(mouseX,mouseY);
}
}

public void infoPanel(){
  fill(255, 255);
  rect(0, 0, width, 50);
  fill(0);
  textAlign(CENTER);
  textSize(15);
  text("moneyEarned:" + moneyEarned, 500, 20);
}

public void startScreen(){
  background(255);
  textAlign(CENTER);
  fill(0);
  text("Welcome to Money Catching Game!",500,400);
  text("Beware of the crossing cars and motorcycles...They WILL KILL YOU!",500,500);
  text("In the meantime, go catch as many money falling from the sky as possible before you die!",500,600);
  text("Click to Continue...", 500, 650);
  if (mousePressed) {
    play = true;
  }
}

public void gameOver(){
  background(10);
  textAlign(CENTER);
  fill(222);
  text("Game Over!", width/2, height/2);
  text("You Eanred: $ " + moneyEarned, width/2, height/2+20);
  noLoop();
}
class Car{
  int x,y,speed,rad;
  boolean right;
  PImage car;
  
  Car() {
    x = PApplet.parseInt(random(width));
    y = PApplet.parseInt(random(height));
    car = loadImage("car.png");
    this.speed = PApplet.parseInt(random(1,7));
    rad = 35;
    if(y > 500){
      right = true;
    } else {
      right = false;
    }
  }
  
  public void display(){
    image(car, x, y);
  }
  
  public void drive(){
    if(right){
      x += speed;
      if (x > width) {
        x = 0;
      }
    } else {
      x -= speed;
      if (x < 0){
        x = width;
      }
    }
  }
  
}
class Money{
  int x,y,rad,speed,value;
  String[] moneyValue = {"$1","$5","$10","20","100"};
  PImage money;
  
  Money(int x, int y){
    this.x = x;
    this.y = y;
    money = loadImage("money.png");
    rad = 50;
    value = PApplet.parseInt(random(5));
    speed = PApplet.parseInt(random(2,4));
  }
  
  public void move(){
    y += speed;
  }
  
  public boolean reachedBottom() {
    if (y > height + rad*4) { 
      return true;
    } else {
      return false;
    }
  }
  
  public void display(){
    noStroke();
    switch(value) {
    case 0: // $1
      image(money,x,y);
      fill(0);
      textSize(9);
      textAlign(CENTER);
      text(moneyValue[0], x+40, y+40);
      break;
    case 1: // $5
      image(money,x,y);
      fill(0);
      textSize(9);
      textAlign(CENTER);
      text(moneyValue[1], x+40, y+40);
      break;
    case 2: // $10
      image(money,x,y);
      fill(0);
      textSize(9);
      textAlign(CENTER);
      text(moneyValue[2], x+40, y+40);
      break;
    case 3: // $20
      image(money,x,y);
      fill(0);
      textSize(9);
      textAlign(CENTER);
      text(moneyValue[3], x+40, y+40);
      break;
    case 4: // $1
      image(money,x,y);
      fill(0);
      textSize(9);
      textAlign(CENTER);
      text(moneyValue[4], x+40, y+40);
      break;
    }
  }
  
}
class Motorcycle{
  int x,y,speed,rad;
  boolean right;
  PImage motorcycle;
  
  Motorcycle() {
    x = PApplet.parseInt(random(width));
    y = PApplet.parseInt(random(height));
    motorcycle = loadImage("motorcycle.png");
    this.speed = PApplet.parseInt(random(3,7));
    rad = 25;
    if(y > 500){
      right = true;
    } else {
      right = false;
    }
  }
  
  public void display(){
    image(motorcycle, x, y);
  }
  
  public void drive(){
    if(right){
      x += speed;
      if (x > width) {
        x = 0;
      }
    } else {
      x -= speed;
      if (x < 0){
        x = width;
      }
    }
  }
  
}
class Player {
  int x, y, rad;
  PImage player;

  Player() {
    x = 500;
    y = 900;
    rad = 15;
    player = loadImage("player.png");
  }

  public void display(int x, int y) {
    this.x = x;
    this.y = y;
    image(player, x-30, y-50);
  }
  
  public boolean carIntersection(Car cars) {
    float distance = dist(x, y, cars.x, cars.y);
    if (distance < rad + cars.rad) {
      return true;
    } else {
      return false;
    }
  }
  
  public boolean motorcycleIntersection(Motorcycle motorcycles) {
    float distance = dist(x, y, motorcycles.x, motorcycles.y);
    if (distance < rad + motorcycles.rad) {
      return true;
    } else {
      return false;
    }
  }
  
  public boolean moneyIntersection(Money money) {
    float distance = dist(x, y, money.x, money.y);
    if (distance < rad + money.rad) {
      return true;
    } else {
      return false;
    }
  }
  
}
// Example 10-5: Object-oriented timer
// by Daniel Shiffman

class Timer {

  int savedTime; // When Timer started
  int totalTime; // How long Timer should last

  Timer(int tempTotalTime) {
    totalTime = tempTotalTime;
  }

  // Starting the timer
  public void start() {
    // When the timer starts it stores the current time in milliseconds.
    savedTime = millis();
  }

  // The function isFinished() returns true if 5,000 ms have passed. 
  // The work of the timer is farmed out to this method.
  public boolean isFinished() { 
    // Check how much time has passed
    int passedTime = millis()- savedTime;
    if (passedTime > totalTime) {
      return true;
    } else {
      return false;
    }
  }
}
  public void settings() {  size(1000,1000); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "MoneyCatchingGame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
