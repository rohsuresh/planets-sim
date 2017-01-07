public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		return Math.sqrt(Math.pow(this.xxPos - p.xxPos, 2.0) + Math.pow(this.yyPos - p.yyPos, 2.0));
	}

	public double calcForceExertedBy(Planet p) {
		double gravConstant = 6.67 * Math.pow(10, -11);
		double distance = this.calcDistance(p);
		return gravConstant * p.mass * this.mass / Math.pow(distance, 2);
	}

	public double calcForceExertedByX(Planet p) {
		double dx = p.xxPos - this.xxPos;
		double force = this.calcForceExertedBy(p);
		double distance = this.calcDistance(p);
		return force * dx / distance;
	}

	public double calcForceExertedByY(Planet p) {
		double dy = p.yyPos - this.yyPos;
		double force = this.calcForceExertedBy(p);
		double distance = this.calcDistance(p);
		return force * dy / distance;
	}

	public double calcNetForceExertedByX(Planet [] allPlanets) {
		int track = 0;
		double netSoFar = 0;
		while (track < allPlanets.length) {
			if (this.equals(allPlanets[track])) {
				track += 1;
			}
			else {
				netSoFar += this.calcForceExertedByX(allPlanets[track]);
				track += 1;
			}
		}
		return netSoFar;
	}

	public double calcNetForceExertedByY(Planet [] allPlanets) {
		int track = 0;
		double netSoFar = 0;
		while (track < allPlanets.length) {
			if (this.equals(allPlanets[track])) {
				track += 1;
			}
			else {
				netSoFar += this.calcForceExertedByY(allPlanets[track]);
				track += 1;
			}
		}
		return netSoFar;
	}

	public void update(double dt, double fx, double fy) {
		// takes in the time, the force in x direction, force in y direction
		double netAccelX = fx / this.mass;
		double netAccelY = fy / this.mass;
		this.xxVel = this.xxVel + (dt * netAccelX);
		this.yyVel = this.yyVel + (dt * netAccelY);
		this.xxPos = this.xxPos + (dt * this.xxVel);
		this.yyPos = this.yyPos + (dt * this.yyVel);
	}

	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
	}


}