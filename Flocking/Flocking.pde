/**
 * Flocking
 * by Daniel Shiffman.  
 * 
 * An implementation of Craig Reynold's Boids program to simulate
 * the flocking behavior of birds. Each boid steers itself based on 
 * rules of avoidance, alignment, and coherence.
 * 
 * Click the mouse to add a new boid.
 *
 * Scala translation by Nathan Hamblen.
 */

size(600, 400)
colorMode(RGB,255,255,255,100)
val flock = new Flock
smooth()

def draw() {
  background(100)
  flock.run()
}

// Add a new boid into the System
override def mousePressed() {
  flock <<< new Boid(Vector(mouseX,mouseY), 2.0, 0.05)
}

class Flock {
  // Add an initial set of boids into the system
  private var boids = List.tabulate(50, n => new Boid(Vector(width/2,height/2), 2.0, 0.05))

  def run() {
    boids foreach { _ run boids }
  }

  // Fly a new boid into the flock
  def <<< (b: Boid) { boids = List(b) ++ boids }
}


class Boid(
    var loc: Vector, 
    maxspeed: Float, // Maximum steering force
    maxforce: Float  // Maximum speed
  ) {
    
  var vel = Vector(random(-1,1),random(-1,1))
  val r = 2.0
  
  def run(boids: List[Boid]) {
    val acc = flock(boids)
    update(acc)
    render()
  }

  // We accumulate a new acceleration each time based on three rules
  def flock(boids: List[Boid]) = {
    val sep = separate(boids) * 2.0   // Separation, aribtrarily weighted
    val ali = align(boids)      // Alignment
    val coh = cohesion(boids)   // Cohesion
    // Add the force vectors to acceleration
    sep + ali + coh
  }
  
  // Method to update location
  def update(acc: Vector) {
    // Update velocity
    vel = (vel + acc) max maxspeed
    // max speed
    loc = borders(loc + vel)
  }

  // A method that calculates a steering vector towards a target
  // Takes a second argument, if true, it slows down as it approaches the target
  def steer(target: Vector, slowdown: Boolean) = {
    val desired = (target - loc).normalized  // A vector pointing from the location to the target
    val d = desired.magnitude // Distance from the target is the magnitude of the vector
    // If the distance is greater than 0, calc steering (otherwise return zero vector)
    if (d > 0) {
      // Two options for desired vector magnitude (1 -- based on distance, 2 -- maxspeed)
      (desired * maxspeed * (
        if (slowdown && d < 100.0)
          d/100.0 // This damping is somewhat arbitrary
        else 
          1.0
      ) - vel) max maxforce // Steering = Desired minus Velocity maxed to maxforce
    } else {
      new Vector
    }
  }
  
  def render() {
    // Draw a triangle rotated in the direction of velocity
    val theta = vel.heading + radians(90);
    fill(200)
    stroke(255)
    pushMatrix()
    translate(loc.x,loc.y)
    rotate(theta)
    beginShape(TRIANGLES)
    vertex(0, -r*2)
    vertex(-r, r*2)
    vertex(r, r*2)
    endShape()
    popMatrix()
  }
  
  // Wraparound
  def borders(loc: Vector) = {
    def wrap(n: Float, a: Float, b: Float) = n match {
      case n if n < a => b
      case n if n > b => a
      case n => n
    }
    Vector(
      wrap(loc.x, -r, width+r),
      wrap(loc.y, -r, height+r)
     )
  }

  // is the other bird within a certain distance
  def within(dist: Float) = { other: Boid =>
    val d = loc distance other.loc
    d > 0 && d < dist
  }
  
  // Sum, and divide by size
  def avg(l: List[Vector]) = (l :\ new Vector)(_ + _) / (l.size max 1)

  // Separation
  // Method checks for nearby boids and steers away
  def separate (boids: List[Boid]) = 
    avg(boids.filter(within(25.0)).map { other =>
      (loc - other.loc).normalized / (loc distance other.loc)
    })
  
  // Alignment
  // For every nearby boid in the system, calculate the average velocity
  def align (boids: List[Boid]) = avg(boids.filter(within(50.0)).map(_.vel)) max maxforce

  // Cohesion
  // For the average location (i.e. center) of all nearby boids, calculate steering vector towards that location
  def cohesion (boids: List[Boid]) = boids.filter(within(50.0)) match {
    case Nil => new Vector
    case nearby => steer(avg(nearby.map(_.loc)), false)
  }
}

// Simple Vector Class 

case class Vector(x: Float, y: Float) {
  def this() = this(0.0, 0.0)

  def magnitude = Math.sqrt(x*x + y*y)

  def + (v: Vector) = Vector(x+v.x, y+v.y)

  def - (v: Vector) = Vector(x-v.x, y-v.y)

  def * (n: Float) = Vector(x*n, y*n)

  def / (n: Float) = Vector(x/n, y/n)

  def normalized = {
    magnitude match {
      case m if m > 0 => this / m
      case m => this
    }
  }

  def max(m: Float) = {
    if (magnitude > m)
      normalized * m
    else this
  }

  def heading = -1 * Math.atan2(-y, x)

  def distance (v: Vector) = {
    val dx = x - v.x;
    val dy = y - v.y;
    Math.sqrt(dx*dx + dy*dy)
  }
}