public class Club extends Thing
{
	protected final int strength = 5;
	
	public Club()
	{
		super("Club");
	}
	
	public void boost(Player p)
	{
		p.strength += strength;
	}
}