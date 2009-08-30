/**
 * An example of a right fold.
 * http://en.wikipedia.org/wiki/Fold_(higher-order_function)
 * by Nathan Hamblen
 */

size(465, 190)
frameRate(25)

def random_seq = (0 until width) map { (_, random(height)) }
var h: Seq[(Int, Float)] = random_seq
override def mouseClicked { h = random_seq }

def draw() {
  background(255)
  h foreach { case (x, h) => line(x, 0, x, h) }
  h = (h :\ List[(Int, Float)]()) {
  	case ((x1, h1), Nil) => (x1, h1) :: Nil
    case ((x1, h1), (x2, h2) :: t) =>
      (x1, (h1 + h2) /2) :: (x2, h2) :: t
  }
}