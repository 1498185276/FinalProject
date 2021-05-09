class Motorcycle{
  int x,y,speed,rad;
  boolean right;
  PImage motorcycle;
  
  Motorcycle() {
    x = int(random(width));
    y = int(random(height));
    motorcycle = loadImage("motorcycle.png");
    this.speed = int(random(3,7));
    rad = 25;
    if(y > 500){
      right = true;
    } else {
      right = false;
    }
  }
  
  void display(){
    image(motorcycle, x, y);
  }
  
  void drive(){
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
