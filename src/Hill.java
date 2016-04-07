
public class Hill 
{
	private String[] table = 
	{
		"A","B", "C", "D", "E", "F", "G","H","I","J","K","L","M","N","O",
		"P","Q","R","S","T","U","V","W","X","Y","Z"
	};
	
	private int[][] matrice = 
	{
			{
				9,4
			},
			{
				5,7
			}
	};
	
	private int[][] inv = 
	{
			{
				5,12
			},
			{
				15,25
			}
	};
	
	public String crypt(String message)
	{
		String cypher = "";
		int letterValue = 0;
		message = this.specifyLength(message);
		
		for (int i = 0; i < message.length() - 1; i+=2)
		{
			for (int j = 0; j < matrice.length; j++)
			{
				letterValue += getCharIndex(message.substring(i, i+1).toUpperCase()) * matrice[j][0];
				letterValue += getCharIndex(message.substring(i+1, i+2).toUpperCase()) * matrice[j][1];
				
				letterValue %= 26;
				cypher += this.table[letterValue];
				letterValue = 0;
			}
		}
		
		return cypher;
	}
	
	public String decrypt(String cypher)
	{
		String message = "";
		
		int letterValue = 0;
		cypher = this.specifyLength(cypher);
		
		for (int i = 0; i < cypher.length() - 1; i+=2)
		{
			for (int j = 0; j < inv.length; j++)
			{
				letterValue += getCharIndex(cypher.substring(i, i+1).toUpperCase()) * inv[j][0];
				letterValue += getCharIndex(cypher.substring(i+1, i+2).toUpperCase()) * inv[j][1];
				
				letterValue %= 26;
				message += this.table[letterValue];
				letterValue = 0;
			}
		}
		
		return message;
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
	
	private String specifyLength(String message)
	{
		String newMessage = "";
		
		if (message.length() % 2 != 0)
		{
			newMessage = message + "Z";
			return newMessage;
		}
		
		return message;
	}
	
	private boolean matriceIsInversible()
	{
		if (this.matriceDet() == 0)
			return false;
		
		return true;
	}
	
	private int matriceDet()
	{
		return (matrice[0][0] * matrice[1][1] - matrice[1][0] * matrice[0][1]);
	}
}
