/**
 * Array.
 * 
 * An array is a list of data. Each piece of data in an array 
 * is identified by an index number representing its position in 
 * the array. Arrays are zero based, which means that the first 
 * element in the array is [0], the second element is [1], and so on. 
 * In this example, an array named "coswav" is created and
 * filled with the cosine values. This data is displayed three 
 * separate ways on the screen.  
 *
 * Scala translation by Nathan Hamblen.
 */

size(200, 200);

val coswave = List.tabulate(width, n => (n, abs(cos(n * PI / width))) )

def draw {
  for ((l,v) <- coswave) {
    stroke(v * 255)
    line(l, 0, l, width/3)
  }
  
  for ((l,v) <- coswave) {
    stroke(v*255/4)
    line(l, width/3, l, width/3*2)
  }
  
  for ((l,v) <- coswave) {
    stroke(255-v*255)
    line(l, width/3*2, l, height)
  }
}