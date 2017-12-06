public class Location
{
	protected int x, y;
	
	public Location(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Location next(String s)
	{
		if(s.equals("w"))
			return new Location(x, y-1);
		else if(s.equals("e"))
			return new Location(x, y+1);
		else if(s.equals("n"))
			return new Location(x-1, y);
		else
			return new Location(x+1, y);
	}
	
	public boolean check(Room[][] r, Location l)
	{
		if(l.x >= 0 && l.y >= 0 && l.x < r.length && l.y < r[0].length)
		{
			if(r[x][y].adj.contains(r[l.x][l.y].number))
				return true;
		}
		return false;
	}
	
	public String toString()
	{
		return x + "," + y;
	}
	
	public boolean equals(Location l)
	{
		return x == l.x && y == l.y;
	}
}