public class Torch extends Thing
{
	protected int fire;
	
	public Torch()
	{
		super("Torch");
		fire = 100;
	}
	
	public void fading()
	{
		fire -= 10;
	}
	
	public boolean lit()
	{
		if(fire > 0)
			return false;
		return true;
	}
	
	public String toString()
	{
		return "" + fire;
	}
}