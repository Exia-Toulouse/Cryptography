
public class Xor 
{
	public String crypt(String message, String key)
	{
		String cypher = "";
		key = this.transformKey(message, key);
		
		cypher = this.xor(message, key);
		
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
	
	private String xor (String message, String key)
	{
		int val1, val2;
		String cypher = "";
		
		for (int i =0, l = message.length(); i < l; i++)
		{
			val1 = (int) message.charAt(i);
			val2 = (int) key.charAt(i);
			
			cypher += Character.toString((char)(val1 ^ val2));
		}
		
		return cypher;
	}
	
	
}
