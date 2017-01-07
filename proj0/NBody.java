public class NBody {
	public static void main (String [] args) {
		
		double t = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String fileName = args[2];
		double radius = readRadius(fileName);
		
		StdDraw.setScale((-1.0 * radius), radius);
		StdDraw.picture(0, 0, "./images/starfield.jpg");
		Planet[] planets = readPlanets(fileName);
		
		for (int i = 0; i < planets.length; i++) {
			planets[i].draw();
		}

		double time = 0.0;
		int counter = 0;
		while (time < t) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for (int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
				// calculate net force and store in xForces and yForces
			}
			for (int i = 0; i < xForces.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			
			StdDraw.picture(0, 0, "./images/starfield.jpg"); // draw background image
			for (int i = 0; i < planets.length; i++) {
				planets[i].draw();
			}
			StdDraw.show(10);
			time = time + dt;

		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   			planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);	
}		


	}

	public static double readRadius(String file) {
		In inObject = new In(file);
		inObject.readInt();
		return inObject.readDouble();
	}

	public static Planet[] readPlanets(String file) {
		In inObject = new In(file);
		int numPlanets = inObject.readInt();
		inObject.readDouble(); // reads the radius
		Planet[] planets = new Planet[numPlanets];
		for (int i = 0; i < numPlanets; i++) {
			Planet planetObject = new Planet(inObject.readDouble(), inObject.readDouble(), inObject.readDouble(), 
				inObject.readDouble(), inObject.readDouble(), inObject.readString());
			planets[i] = planetObject;
		}
		return planets;
	}
}