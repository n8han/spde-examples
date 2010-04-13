package spde.example

import processing.core._
import spde.core._
import PConstants._

class Fold extends PApplet {
  override def setup() { 
    frameRate(25)
    h = random_seq
  }

  def random_seq = (0 until width) map { (_, random(height)) }
  var h: Seq[(Int, Float)] = null
  override def mouseReleased { h = random_seq }

  override def draw() {
    background(255)
    h foreach { case (x, h) => line(x, 0, x, h) }
    h = (List.empty[(Int, Float)] /: h) {
    	case (Nil, (x1, h1)) => (x1, h1) :: Nil
      case ((x2, h2) :: t, (x1, h1)) =>
        (x1, (h1 + h2) /2) :: (x2, h2) :: t
    } reverse
  }
}
