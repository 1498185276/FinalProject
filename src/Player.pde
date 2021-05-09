class Player {
  int x, y, rad;
  PImage player;

  Player() {
    x = 500;
    y = 900;
    rad = 15;
    player = loadImage("player.png");
  }

  void display(int x, int y) {
    this.x = x;
    this.y = y;
    image(player, x-30, y-50);
  }
  
  boolean carIntersection(Car cars) {
    float distance = dist(x, y, cars.x, cars.y);
    if (distance < rad + cars.rad) {
      return true;
    } else {
      return false;
    }
  }
  
  boolean motorcycleIntersection(Motorcycle motorcycles) {
    float distance = dist(x, y, motorcycles.x, motorcycles.y);
    if (distance < rad + motorcycles.rad) {
      return true;
    } else {
      return false;
    }
  }
  
  boolean moneyIntersection(Money money) {
    float distance = dist(x, y, money.x, money.y);
    if (distance < rad + money.rad) {
      return true;
    } else {
      return false;
    }
  }
  
}
