import java.util.*;

public abstract class Player
{
	protected String name;
	protected int strength;
	protected Torch torch;
	protected Location l;
	protected ArrayList<Thing> bag;
	protected World w;
	
	public Player(Location l)
	{
		this.l = l;
		strength = 1;
	}
	
	public Room get_room(Location l)
	{
		return w.rooms[l.x][l.y]; 
	}
	
	public boolean fight(Player p)
	{
		if(strength > p.strength)
			return true;
		return false;
	}
	
	public void add(Object o)
	{
		if(o instanceof Player)
			get_room(l).players.add((Player)o);
		else if(o instanceof Thing)
			get_room(l).things.add((Thing)o);
	}
	
	public void remove(Object o)
	{
		if(o instanceof Player)
			get_room(l).players.remove((Player)o);
		else if(o instanceof Thing)
			get_room(l).things.remove((Thing)o);
	}
	
	public String toString()
	{
		return name;
	}
	
	public boolean equals(Object o)
	{
		return name.equals(((Player)o).name);
	}
}