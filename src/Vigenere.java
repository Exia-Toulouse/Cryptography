
public class Vigenere 
{
	private String[] table = 
	{
		"A","B", "C", "D", "E", "F", "G","H","I","J","K","L","M","N","O",
		"P","Q","R","S","T","U","V","W","X","Y","Z"
	};
	
	public String crypt(String message, String key)
	{
		String cypher = "";
		key = this.transformKey(message, key);
		
		cypher = this.vigenere(message, key, true);
		
		return cypher;
	}
	
	public String decrypt(String cypher, String key)
	{
		String message = "";
		key = this.transformKey(cypher, key);
		
		message = this.vigenere(cypher, key, false);
		
		return message;
	}
	
	private String vigenere(String message, String key, boolean crypt)
	{
		String cypher = "";
		int char1, char2, mod;
		for (int i = 0, l = message.length(); i < l; i++)
		{
			char1 = this.getCharIndex(message.substring(i, i+1).toUpperCase());
			char2 = this.getCharIndex(key.substring(i, i+1).toUpperCase());
			
			if (crypt)
			{
				mod = (char1 + char2) % 26;
			}
			else
			{
				mod = ((char1 - char2) % 26);
				if (mod < 0)
					mod+=26;
			}
				

			cypher += this.table[mod];
		}
		
		return cypher;
	}
	
	private int getCharIndex(String character)
	{
		for (int i = 0; i < 26; i++)
		{
			if (character.equals(this.table[i]))
			{
				return i;
			}	
		}
		
		return 0;
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
