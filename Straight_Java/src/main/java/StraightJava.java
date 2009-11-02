import processing.core.*;
import spde.core.*;
import processing.core.PConstants.*;

public class StraightJava extends PApplet {
  public static void main(String[] args) { PApplet.main(new String[] {"StraightJava"}); }
  
  public void setup() {
    size(500, 200);
    frameRate(20);
  }

  public void draw() {
    for (int x=0; x<width; x++) {
      stroke(random(255));
      line (x, 0, x, height);
    }
  }
}
