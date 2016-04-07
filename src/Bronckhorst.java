
public class Bronckhorst 
{
	public String crypt(String message, String key)
	{
		String cypher = "";
		key = this.transformKey(message, key);
		
		cypher = this.bronckhorst(message, key, true);
		
		return cypher;
	}
	
	public String decrypt(String cypher, String key)
	{
		String message = "";
		key = this.transformKey(cypher, key);
		
		message = this.bronckhorst(cypher, key, false);
		
		return message;
	}
	
	private String bronckhorst(String message, String key, Boolean crypt)
	{
		String cypher = "";
		int val1, val2;
		
		for (int i = 0, l = message.length(); i < l; i++)
		{
			val1 = (int) message.charAt(i);
			val2 = (int) key.charAt(i);
			
			if (crypt)
				cypher += Character.toString((char)(val1 + val2));
			else
				cypher += Character.toString((char)(val1 - val2));
		}
		
		return cypher;
	}
	
	private String transformKey(String message, String key)
	{
		String newKey = key;
		int messageLength = message.length();
		
		while (messageLength > newKey.length())
		{
			newKey += key;
		}
		
		return newKey;
	}
}
