public class NBody{
	public static double readRadius(String file){
		In in=new In(file);
		int firstItem=in.readInt();
		double radius=in.readDouble();
		return radius;
	}
	public static Planet[] readPlanets(String file){
		In in = new In(file);
		int num=in.readInt(),i=0;
		in.readDouble();
		Planet[] ps=new Planet[num];
		while(i<num){
			double xp=in.readDouble();
			double yp=in.readDouble();
			double xv=in.readDouble();
			double yv=in.readDouble();
			double m=in.readDouble();
			String s=in.readString();
			ps[i++]=new Planet(xp,yp,xv,yv,m,s);
		}
		return ps;
	}
	public static void main(String[] args){
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];
		double radius=readRadius(filename);
		Planet[] ps=readPlanets(filename);

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius,radius);
		StdDraw.clear();
		StdDraw.picture(0,0,"images/starfield.jpg");	
		for(Planet p:ps){
			p.draw();
		}
		StdDraw.show();
		StdDraw.pause(2000);
		
		double time=0;
		while(time<T){
			double[] xForces=new double[ps.length];
			double[] yForces=new double[ps.length];
			for(int i=0;i<ps.length;i++){
				xForces[i]=ps[i].calcNetForceExertedByX(ps);
				yForces[i]=ps[i].calcNetForceExertedByY(ps);
			}
			for(int i=0;i<ps.length;i++){
				ps[i].update(dt, xForces[i], yForces[i]);
			}
		
			StdDraw.picture(0,0,"images/starfield.jpg");
			for(Planet p:ps){p.draw();}
			StdDraw.show();
			StdDraw.pause(10);
			time+=dt;
		}
}
	}
