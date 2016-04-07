
public class Cesar 
{
	public String crypt(String message, int key)
	{
		String cypher = this.cesar(message, key);
		
		return cypher;
	}
	
	public String decrypt(String cypher, int key)
	{
		String 	message = this.cesar(cypher, -key);
		return message;
	}
	
	private String cesar(String message, int key)
	{
		String cypher = "";
		int val = 0;
		for (int i = 0, l = message.length(); i < l ; i++)
		{
			val = (int) message.charAt(i);
			val += key;
			cypher += Character.toString((char)val);
		}
		
		return cypher;
	}
	
	
}
