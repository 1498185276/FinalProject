// Produced by Mingjing Huang | May 2021

Player player;
Car[] cars = new Car[8];
Motorcycle[] motorcycles = new Motorcycle[4];
ArrayList<Money> moneys;
int moneyEarned;
boolean play;
Timer moneyTimer;

void setup(){
  size(1000,1000);
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

void draw(){
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
      moneys.add(new Money(int(random(width)), 0));
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

void infoPanel(){
  fill(255, 255);
  rect(0, 0, width, 50);
  fill(0);
  textAlign(CENTER);
  textSize(15);
  text("moneyEarned:" + moneyEarned, 500, 20);
}

void startScreen(){
  background(255);
  textAlign(CENTER);
  fill(0);
  textSize(15);
  text("Welcome to Money Catching Game! Use the mouse to control the money catcher!",500,400);
  text("Beware of the crossing cars and motorcycles...They WILL KILL YOU!",500,500);
  text("In the meantime, go catch as many money falling from the sky as possible before you die!",500,600);
  text("Click to Continue...", 500, 650);
  if (mousePressed) {
    play = true;
  }
}

void gameOver(){
  background(10);
  textAlign(CENTER);
  fill(222);
  text("Game Over!", width/2, height/2);
  text("You Eanred: $ " + moneyEarned, width/2, height/2+20);
  noLoop();
}
