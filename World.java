import java.util.*;
import java.io.*;

public class World
{
	protected Room[][] rooms;
	
	public World(String file)
	{
		try
		{
			BufferedReader BR = new BufferedReader(new FileReader(file));
		
			int n = Integer.parseInt(BR.readLine());
			
			if(n == 2)
				rooms = new Room[1][2];
			else if(n % 2 == 0)
				rooms = new Room[2][n/2];
			else
				rooms = new Room[(int)Math.sqrt(n)][(int)Math.sqrt(n)];
			
			for(int i = 0; i < rooms.length; i++)
			{
				for(int j = 0; j < rooms[i].length; j++)
				{
					rooms[i][j] = new Room(
					Integer.parseInt(BR.readLine()), 
					BR.readLine(), 
					r_list(BR.readLine().split(", ")), 
					p_list(BR.readLine().split(", "), new Location(i, j)), 
					t_list(BR.readLine().split(", ")));
				}
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found.");	
		}
		catch(IOException e)
		{
			System.out.println("Can't read file.");
		}		
	}
	
	public ArrayList<Integer> r_list(String[] array)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(String s: array)
			list.add(Integer.parseInt(s));
		
		return list;
	}
	
	public ArrayList<Player> p_list(String[] array, Location l)
	{
		ArrayList<Player> list = new ArrayList<Player>();
		
		if(array[0].equals("1"))
			list.add(new Explorer(l));
		if(array[1].equals("1"))
			list.add(new Caveman(l));
		return list;
	}
	
	public ArrayList<Thing> t_list(String[] array)
	{
		ArrayList<Thing> list = new ArrayList<Thing>();
		
		if(array[0].equals("1"))
			list.add(new Club());
		if(array[1].equals("1"))
			list.add(new Oil());
		if(array[2].equals("1"))
			list.add(new Rope());
		return list;
	}
}