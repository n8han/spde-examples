/**
 * Matrix 
 * by Mixel. 
 * 
 * Simple example utilizing the OpenGL Library for Processing. 
 *
 * Translated to Scala from Mixel's P5 original.
 */

import processing.opengl._

override def setup() {
  size(640, 480, OPENGL)
  noStroke()
  fill(0, 102, 153, 40)
}

def draw() {
  val x = mouseX - width/2;
  background(255)
  translate(width/2, height/2)
  rotateY(radians(mouseY/5)) 
  
  for (i <- -height/2 until height/2 by 50 ; j <- -width/2 until width/2 by 50) {
    beginShape(QUADS)
    List(i, i+15) foreach { vertex(x+j, _, 0) }
    List(i+15, i) foreach { vertex(j-x, _, -400) }
    endShape
  }
}
