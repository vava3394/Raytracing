package raytracing;


public class Vec3d
{
	/**
	 * x, y and z values of the current vector.
	 * These are public to allow fast access and simple use.
	 */
	public double x,y,z;

	/**
	 * Default Constructor
	 */
	public Vec3d()
	{
		this.x=this.y=this.z=0.F;
	}

	/**
	 * Constructor with initialisation
	 * @param x,y,z values to place into current vector
	 */
	public Vec3d(final double x,final double y,final double z)
	{
		this.x=x;
		this.y=y;
		this.z=z;
	}

	/**
	 * Constructor by copy
	 * @param that vector to be copied in current vector
	 */
	public Vec3d(final Vec3d that)
	{
		this.x=that.x;
		this.y=that.y;
		this.z=that.z;
	}

	/**
	 * Set current vector's value to 0.0
	 * @return current vector
	 */
	public Vec3d reset()
	{
		this.x=this.y=this.z=0.F;
		return this;
	}

	/**
	 * Copy "that" vector in current vector
	 * @param that vector to be copied
	 * @return current vector
	 */
	public Vec3d set(final Vec3d that)
	{
		this.x=that.x;
		this.y=that.y;
		this.z=that.z;
		return this;
	}

	/**
	 * Copy x, y and z in current vector
	 * @param x,y,z values to place into current vector
	 * @return current vector
	 */
	public Vec3d set(final double x,final double y,final double z)
	{
		this.x=x;
		this.y=y;
		this.z=z;
		return this;
	}

	/**
	 * @return square of the length of current vector
	 */
	public double lengthSquare()
	{
			return this.x*this.x+this.y*this.y+this.z*this.z;
	}
	/**
	 * @return length of current vector
	 */
	public double length()
	{
			return (double)Math.sqrt(this.x*this.x+this.y*this.y+this.z*this.z);
	}

	/**
	 * Normalize current vector
	 * @return current vector
	 */
	public Vec3d normalize()
	{
		double l=this.lengthSquare();
		if (l==0.F) return this;
		l=(double)Math.sqrt(l);
		return this.scale(1.F/l);
	}

	/**
	 * Add a vector to current vector
	 * @param that any vector
	 * @return current vector
	 */
	public Vec3d add(final Vec3d that)
	{
		this.x+=that.x;
		this.y+=that.y;
		this.z+=that.z;
		return this;
	}

	/**
	 * Add two vectors v1 and v2 and put result into current vector
	 * @param v1 any vector
	 * @param v2 any vector
	 * @return current vector
	 */
	public Vec3d setAdd(final Vec3d v1, final Vec3d v2)
	{
		this.x=v1.x+v2.x;
		this.y=v1.y+v2.y;
		this.z=v1.z+v2.z;
		return this;
	}

	/**
	 * Subtract a vector to current vector
	 * @param that vector to subtract
	 * @return current vector
	 */
	public Vec3d sub(final Vec3d that)
	{
		return new Vec3d(this.x - that.x, this.y - that.y, this.z - that.z);
	}

	/**
	 * Subtract two vectors and put result into current vector
	 * @param v1 any vector
	 * @param v2 any vector
	 * @return
	 */
	public Vec3d setSub(final Vec3d v1,final Vec3d v2)
	{
		this.x=v1.x-v2.x;
		this.y=v1.y-v2.y;
		this.z=v1.z-v2.z;
		return this;
	}

	/**
	 * Scale current vector uniformly
	 * @param scale uniform scale factor
	 * @return current vector
	 */
	public Vec3d scale(final double scale)
	{
		this.x*=scale;
		this.y*=scale;
		this.z*=scale;
		return this;
	}
        
        /**
	 * Multiply this vector by a scalar.
	 * 
	 * @param s the scalar
	 * @return the result of the multiplication
	 */
	public Vec3d mult(double s) {
		return new Vec3d(this.x * s, this.y * s, this.z * s);
	}

	/**
	 * Scale current vector with specific factors for each coordinate
	 * @param scalex scale factor for x
	 * @param scaley scale factor for y
	 * @param scalez scale factor for z
	 * @return current vector
	 */
	public Vec3d scale(final double scalex,final double scaley,final double scalez)
	{
		this.x*=scalex;
		this.y*=scaley;
		this.z*=scalez;
		return this;
	}

	/**
	 * Scale a given vector by a uniform scale and put result into current vector
	 * @param scale scale factor
	 * @param that vector to scale
	 * @return current vector
	 */
	public Vec3d setScale(final double scale,final Vec3d that)
	{
		this.x=scale*that.x;
		this.y=scale*that.y;
		this.z=scale*that.z;
		return this;
	}

	/**
	 * Scale a given vector by factors provided in another vector and put result into current vector
	 * @param v1 vector to scale
	 * @param v2 scale factors for x, y and z
	 * @return current vector
	 */
	public Vec3d setScale(final Vec3d v1,final Vec3d v2)
	{
		this.x=v1.x*v2.x;
		this.y=v1.y*v2.y;
		this.z=v1.z*v2.z;
		return this;
	}

	/**
	 * Add a given vector that is before-hand scaled, to the current vector
	 * @param scale scale factor
	 * @param that vector to scale and add to current vector
	 * @return current vector
	 */
	public Vec3d addScale(final double scale,final Vec3d that)
	{
		this.x+=scale*that.x;
		this.y+=scale*that.y;
		this.z+=scale*that.z;
		return this;
	}

	/**
	 * Fill current vector with the result of a matrix-vector multiplication
	 * @param mat any matrix
	 * @param v any vector
	 * @return current vector
	 */
	public Vec3d setMatMultiply(final double[] mat,final Vec3d v)
	{
		this.x=mat[0]*v.x+mat[1]*v.y+mat[2]*v.z;
		this.y=mat[3]*v.x+mat[4]*v.y+mat[5]*v.z;
		this.z=mat[6]*v.x+mat[7]*v.y+mat[8]*v.z;
		return this;
	}

	/**
	 * Multiply a given vector by the transpose of a matrix and put result into current vector
	 * @param mat any matrix
	 * @param v any vector
	 * @return current vector
	 */
	public Vec3d setTransposeMatMultiply(final double[] mat,final Vec3d v)
	{
		this.x=mat[0]*v.x+mat[3]*v.y+mat[6]*v.z;
		this.y=mat[1]*v.x+mat[4]*v.y+mat[7]*v.z;
		this.z=mat[2]*v.x+mat[5]*v.y+mat[8]*v.z;
		return this;
	}

	/**
	 * Compute dot (inner) product with another vector
	 * @param v vector with which dotproduct is computed
	 * @return result of dot product
	 */
	public double dotProduct(final Vec3d v)
	{
		return this.x*v.x+this.y*v.y+this.z*v.z;
	}

	/**
	 * Fill current vector with the cross product of two vectors.
	 * Take care of parameters order, cross-product is anti-commutative!
	 * @param v1 First vector
	 * @param v2 Second vector
	 * @return current vector, filled with cross product v1*v2
	 */
	public Vec3d setCrossProduct(final Vec3d v1,final Vec3d v2)
	{
		this.x=v1.y*v2.z-v1.z*v2.y;
		this.y=v1.z*v2.x-v1.x*v2.z; // take care of this value !!
		this.z=v1.x*v2.y-v1.y*v2.x;
		return this;
	}

}
