class Car{
  int x,y,speed,rad;
  boolean right;
  PImage car;
  
  Car() {
    x = int(random(width));
    y = int(random(height));
    car = loadImage("car.png");
    this.speed = int(random(1,7));
    rad = 35;
    if(y > 500){
      right = true;
    } else {
      right = false;
    }
  }
  
  void display(){
    image(car, x, y);
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
