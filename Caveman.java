public class Caveman extends Player
{	
	public Caveman(Location l)
	{
		super(l);
		name = "Caveman";
		strength = 5;
	}
	
	public void patrol()
	{
		if(l.check(w.rooms, l.next("e")))
			l = l.next("e");
		else if(l.check(w.rooms, l.next("w")))
			l = l.next("w");
		else if(l.check(w.rooms, l.next("n")))
			l = l.next("n");
		else if(l.check(w.rooms, l.next("s")))
			l = l.next("s");
	}
}