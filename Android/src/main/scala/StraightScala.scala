package us.technically.spde.example

import processing.core._
import spde.core._
import PConstants._

class StraightScala extends PApplet {
  val items = { 0 to 500 }.view.map { (_, random(255).toInt) }
  
  override def setup {
    frameRate(20)
  }

  override def draw {
    for ((x, color) <- items) {
      stroke(color)
      line (x, 0, x, height)
    }
  }
}
