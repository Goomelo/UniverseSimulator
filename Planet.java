public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP,double yP,double xV,double yV,double m,String img){
		this.xxPos=xP;
		this.yyPos=yP;
		this.xxVel=xV;
		this.yyVel=yV;
		this.mass=m;
		this.imgFileName=img;}
	public Planet(Planet p){
		this.xxPos=p.xxPos;
		this.yyPos=p.yyPos;
		this.xxVel=p.xxVel;
		this.yyVel=p.yyVel;
		this.mass=p.mass;
		this.imgFileName=p.imgFileName;}
	public double calcDistance(Planet p){
		double disX,disY;
	        disX=this.xxPos-p.xxPos;
		disY=this.yyPos-p.yyPos;
		return Math.sqrt(disX*disX+disY*disY);
	}
	public double calcForceExertedBy(Planet p){
		double G=6.67e-11;
		return G*this.mass*p.mass/(this.calcDistance(p)*this.calcDistance(p));
	}
	public double calcForceExertedByX(Planet p){
		return calcForceExertedBy(p)*(p.xxPos-this.xxPos)/this.calcDistance(p);
	}
	public double calcForceExertedByY(Planet p){
		return calcForceExertedBy(p)*(p.yyPos-this.yyPos)/this.calcDistance(p);
	}
	public double calcNetForceExertedByX(Planet[] ps){
		double xForce=0;
		for(Planet p:ps){
			if(!this.equals(p)) xForce=xForce+this.calcForceExertedByX(p);}
		return xForce;
	}
	public double calcNetForceExertedByY(Planet[] ps){
		double yForce=0;
		for(Planet p:ps){
			if(!this.equals(p)) yForce=yForce+this.calcForceExertedByY(p);}
	return yForce;
	}
	public void update(double dt, double fX, double fY){
		double aX=fX/this.mass;
		double aY=fY/this.mass;
		double vX=this.xxVel+dt*aX;
		double vY=this.yyVel+dt*aY;
		this.xxVel=vX;
		this.yyVel=vY;
		this.xxPos+=dt*vX;
		this.yyPos+=dt*vY;
	}
	public void draw(){
		String img="images/"+imgFileName;
		StdDraw.picture(this.xxPos,this.yyPos,img);
	}
}
