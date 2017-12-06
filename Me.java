import java.util.*;

public class Me extends Player
{
	public Me(Location l)
	{
		super(l);
		torch = new Torch();
		bag = new ArrayList<Thing>();
		w = new World("file.txt");				//LOAD FILE
	}
	
	public void move(String input)
	{
		Location t = l.next(input);
		
		if(l.check(w.rooms, t))
		{
			l = t;
			torch.fading();
		}
		else
			System.out.println("Can't go that way.");
	}
	
	public boolean find(String s)
	{
		for(Thing t: bag)
		{
			if(t.name.equals(s))
				return true;
		}
		return false;
	}
	
	public static void help()
	{
		System.out.println("<l> to look at room.");
		System.out.println("<b> to look in bag.");
		System.out.println("<o> to grab oil.");
		System.out.println("<r> to grab rope.");
		System.out.println("<c> to grab club");		
		System.out.println("<d> to drop something.");
		System.out.println("<wens> to change rooms.");
		System.out.println("<1> to interact with Explorer.");
		System.out.println("<2> to interact with Caveman.");
		System.out.println("<x> to exit.");
	}
	
	public static void no()
	{
		System.out.println("Can't do that here.");
	}
	
	public static void main(String[] args)
	{
		Me m = new Me(new Location(0,0));
		
		Caveman c = (Caveman)m.get_room(new Location(0,1)).find_p("Caveman");
		
		c.w = m.w; 	//jank
		
		Scanner s = new Scanner(System.in);
		String input;
		
		System.out.println("<h> for help.");
		
		while(true)
		{
			System.out.println("\n" + m.get_room(m.l).name);
			System.out.println("Torch: " + m.torch + "%\tStrength: " + m.strength);
			System.out.print(">");
			input = s.nextLine();
			
			if(input.equals("w") || input.equals("e") || input.equals("n") || input.equals("s"))
			{
				m.move(input);
				
				if(c != null)
				{
					c.remove(c);
					c.patrol();
					c.add(c);
				}
			}
			else if(input.equals("l"))
				System.out.println(m.get_room(m.l));
			else if(input.equals("b"))
				System.out.println("Bag: " + m.bag);
			else if(input.equals("o"))
			{
				Oil o = (Oil)m.get_room(m.l).find_t("Oil");
				
				if(o != null)
				{
					m.torch.fire = 100;
					m.remove(o);
					System.out.println("Grabbed the oil.");
				}
				else
					no();
			}
			else if(input.equals("r"))
			{
				Rope o = (Rope)m.get_room(m.l).find_t("Rope");
				
				if(o != null)
				{
					m.bag.add(o);
					m.remove(o);
					System.out.println("Grabbed the rope.");
				}
				else
					no();
			}
			else if(input.equals("c"))
			{
				Club o = (Club)m.get_room(m.l).find_t("Club");
				
				if(o != null)
				{
					m.bag.add(o);
					m.remove(o);
					System.out.println("Grabbed the club.");
					o.boost(m);
				}
				else
					no();
			}
			else if(input.equals("d"))
			{
				if(m.bag.size() == 0)
					System.out.println("Bag is empty.");
				else
				{
					System.out.println(m.bag.get(0) + " dropped.");
					m.add(m.bag.get(0));
					m.bag.remove(0);
				}
			}
			else if(input.equals("1"))
			{
				Explorer o = (Explorer)m.get_room(m.l).find_p("Explorer");
				
				if(o != null)
				{
					if(m.find("Rope"))
					{
						System.out.println("\"Thank you for the rescue. Follow me, I know the way out.\"");
						break;
					}
					else
						System.out.println("\"HELP! I've fallen down this hole and I can't get out!\"");
				}
				else
					no();
			}
			else if(input.equals("2"))
			{
				Caveman o = (Caveman)m.get_room(m.l).find_p("Caveman");
				
				if(o != null)
				{	
					if(m.fight(c))
					{
						System.out.println("You clubbed the caveman, and he runs away.");
						m.remove(c);
						c.add(c.bag.get(0));
						c.bag.remove(0);
						c = null;
					}
					else
					{ 
						System.out.println("The caveman clobbered you.");
						break;
					}
				}
				else
					no();
			}
			else if(input.equals("x"))
				break;
			else if(input.equals("h"))
				help();
			else
			{
				System.out.println("Invalid. <h> for help.");
			}
						
			if(!m.find("Club"))
				m.strength = 1;
			
			if(m.torch.lit())
			{
				System.out.println("The torch has burned out.");
				break;
			}
		}
		
	}
}