import java.util.*;

public class Caveman extends Player
{	
	public Caveman(Location l)
	{
		super(l);
		name = "Caveman";
		strength = 5;
		bag = new ArrayList<Thing>();
		bag.add(new Oil());
	}
	
	public void patrol()
	{
		if(l.check(w.rooms, l.next("s")))
			l = l.next("s");
		else if(l.check(w.rooms, l.next("n")))
			l = l.next("n");
		else if(l.check(w.rooms, l.next("e")))
			l = l.next("e");
		else if(l.check(w.rooms, l.next("w")))
			l = l.next("w");
	}
}