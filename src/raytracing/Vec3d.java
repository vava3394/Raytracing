package raytracing;


public class Vec3d
{

	public double x,y,z;

	public Vec3d()
	{
            this.x=this.y=this.z=0.F;
	}

	public Vec3d(final double x,final double y,final double z)
	{
            this.x=x;
            this.y=y;
            this.z=z;
	}

	public Vec3d(final Vec3d that)
	{
            this.x=that.x;
            this.y=that.y;
            this.z=that.z;
	}

	public Vec3d reset()
	{
            this.x=this.y=this.z=0.F;
            return this;
	}

	public Vec3d set(final Vec3d that)
	{
            this.x=that.x;
            this.y=that.y;
            this.z=that.z;
            return this;
	}

	public Vec3d set(final double x,final double y,final double z)
	{
            this.x=x;
            this.y=y;
            this.z=z;
            return this;
	}

	public double lengthSquare()
	{
            return this.x*this.x+this.y*this.y+this.z*this.z;
	}

	public double length()
	{
            return (double)Math.sqrt(this.x*this.x+this.y*this.y+this.z*this.z);
	}

	public Vec3d normalize()
	{
            double l=this.lengthSquare();
            if (l==0.F) return this;
            l=(double)Math.sqrt(l);
            return this.scale(1.F/l);
	}

	public Vec3d add(final Vec3d that)
	{
            this.x+=that.x;
            this.y+=that.y;
            this.z+=that.z;
            return this;
	}

	public Vec3d setAdd(final Vec3d v1, final Vec3d v2)
	{
            this.x=v1.x+v2.x;
            this.y=v1.y+v2.y;
            this.z=v1.z+v2.z;
            return this;
	}

	public Vec3d sub(final Vec3d that)
	{
            return new Vec3d(this.x - that.x, this.y - that.y, this.z - that.z);
	}

	public Vec3d setSub(final Vec3d v1,final Vec3d v2)
	{
            this.x=v1.x-v2.x;
            this.y=v1.y-v2.y;
            this.z=v1.z-v2.z;
            return this;
	}

	public Vec3d scale(final double scale)
	{
            this.x*=scale;
            this.y*=scale;
            this.z*=scale;
            return this;
	}

	public Vec3d mult(double s) {
            return new Vec3d(this.x * s, this.y * s, this.z * s);
	}

	public Vec3d scale(final double scalex,final double scaley,final double scalez)
	{
            this.x*=scalex;
            this.y*=scaley;
            this.z*=scalez;
            return this;
	}

	public Vec3d setScale(final double scale,final Vec3d that)
	{
            this.x=scale*that.x;
            this.y=scale*that.y;
            this.z=scale*that.z;
            return this;
	}

	public Vec3d setScale(final Vec3d v1,final Vec3d v2)
	{
            this.x=v1.x*v2.x;
            this.y=v1.y*v2.y;
            this.z=v1.z*v2.z;
            return this;
	}

	public Vec3d addScale(final double scale,final Vec3d that)
	{
            this.x+=scale*that.x;
            this.y+=scale*that.y;
            this.z+=scale*that.z;
            return this;
	}
        
	public Vec3d setMatMultiply(final double[] mat,final Vec3d v)
	{
            this.x=mat[0]*v.x+mat[1]*v.y+mat[2]*v.z;
            this.y=mat[3]*v.x+mat[4]*v.y+mat[5]*v.z;
            this.z=mat[6]*v.x+mat[7]*v.y+mat[8]*v.z;
            return this;
	}

	public Vec3d setTransposeMatMultiply(final double[] mat,final Vec3d v)
	{
            this.x=mat[0]*v.x+mat[3]*v.y+mat[6]*v.z;
            this.y=mat[1]*v.x+mat[4]*v.y+mat[7]*v.z;
            this.z=mat[2]*v.x+mat[5]*v.y+mat[8]*v.z;
            return this;
	}

	public double dotProduct(final Vec3d v)
	{
            return this.x*v.x+this.y*v.y+this.z*v.z;
	}

	public Vec3d setCrossProduct(final Vec3d v1,final Vec3d v2)
	{
            this.x=v1.y*v2.z-v1.z*v2.y;
            this.y=v1.z*v2.x-v1.x*v2.z; // take care of this value !!
            this.z=v1.x*v2.y-v1.y*v2.x;
            return this;
	}

}
