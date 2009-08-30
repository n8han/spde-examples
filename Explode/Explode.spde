/**
 * Explode
 * by Daniel Shiffman.
 *
 * Mouse horizontal location controls breaking apart of image and 
 * Maps pixels from a 2D image into 3D space. Pixel brightness controls 
 * translation along z axis. 
 *
 * Scala translation by Nathan Hamblen.
 */
 
val cellsize = 2 // Dimensions of each cell in the grid

size(200, 200, P3D)
colorMode(RGB,255,255,255,100)   // Setting the colormode

val img = loadImage("eames.jpg")       // The source image
val COLS = width/cellsize
val ROWS = height / cellsize

def draw {
  background(0)
  // loop through rows and columns
  for ( i <- 0 until COLS; j <- 0 until ROWS) {
    val x = i*cellsize + cellsize/2 // x position
    val y = j*cellsize + cellsize/2 // y position
    val loc = x + y * width         // Pixel array location
    val c = img.pixels(loc)       // Grab the color
    // Calculate a z position as a function of mouseX and pixel brightness
    val z = (mouseX / width.toFloat) * brightness(img.pixels(loc)) - 100.0f
    // Translate to the location, set fill and stroke, and draw the rect
    pushMatrix()
    translate(x,y,z)
    fill(c)
    noStroke()
    rectMode(CENTER)
    rect(0,0,cellsize,cellsize)
    popMatrix()
  }
}
