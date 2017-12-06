import java.util.*;

public class Room
{
	protected int number;
	protected String name;
	protected ArrayList<Integer> adj;
	protected ArrayList<Player> players;
	protected ArrayList<Thing> things;
	
	public Room(int number, String name, ArrayList<Integer> adj, ArrayList<Player> players, ArrayList<Thing> things)
	{
		this.number = number;
		this.name = name;
		this.adj = adj;
		this.players = players;
		this.things = things;
	}
	
	public Player find_p(String s)
	{
		for(Player p: players)
		{
			if(p.name == s)
				return p;
		}
		return null;
	}
	
	public Thing find_t(String s)
	{
		for(Thing t: things)
		{
			if(t.name == s)
				return t;
		}
		return null;
	}
		
	public String toString()
	{	
		return players + "\n" + things;
	}
}