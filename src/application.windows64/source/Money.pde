class Money{
  int x,y,rad,speed,value;
  String[] moneyValue = {"$1","$5","$10","20","100"};
  PImage money;
  
  Money(int x, int y){
    this.x = x;
    this.y = y;
    money = loadImage("money.png");
    rad = 50;
    value = int(random(5));
    speed = int(random(2,4));
  }
  
  void move(){
    y += speed;
  }
  
  boolean reachedBottom() {
    if (y > height + rad*4) { 
      return true;
    } else {
      return false;
    }
  }
  
  void display(){
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
