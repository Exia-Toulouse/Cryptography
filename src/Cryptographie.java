
public class Cryptographie 
{

	public static void main(String[] args) 
	{
		System.out.println("Chiffrement XOR");
		System.out.println("-------------------");
		
		Xor x = new Xor();
		String message = "hello";
		String key = "azerty";
		String cypher = "";
		cypher = x.crypt(message, key);
		
		System.out.println("Message en clair: " + message);
		System.out.println("Message chiffr�: " + cypher);
		System.out.println("Message d�chiffr�: " + x.crypt(cypher, key));
		
		System.out.println("\nChiffrement de Cesar");
		System.out.println("-------------------------");
		
		Cesar c = new Cesar();
		cypher = c.crypt(message, 10);
		
		System.out.println("Message en clair: " + message);
		System.out.println("Message chiffr�: " + cypher);
		System.out.println("Message d�chiffr�: " + c.decrypt(cypher, 10));
		
		System.out.println("\nChiffrement Bronckhorst");
		System.out.println("-------------------------");
		
		Bronckhorst b = new Bronckhorst();
		cypher = b.crypt(message, key);
		System.out.println("Message en clair: " + message);
		System.out.println("Message chiffr�: " + cypher);
		System.out.println("Message d�chiffr�: " + b.decrypt(cypher, key));
		
		
		System.out.println("\nChiffrement Vigenere");
		System.out.println("-------------------------");
		
		Vigenere v = new Vigenere();
		cypher = v.crypt(message, key);
		
		System.out.println("Message en clair: " + message);
		System.out.println("Message chiffr�: " + cypher);
		System.out.println("Message d�chiffr�: " + v.decrypt(cypher, key));
		
		System.out.println("\nChiffrement Beaufort");
		System.out.println("-------------------------");
		
		Beaufort be = new Beaufort();
		cypher = be.crypt(message, key);
		
		System.out.println("Message en clair: " + message);
		System.out.println("Message chiffr�: " + cypher);
		System.out.println("Message d�chiffr�: " + be.decrypt(cypher, key));
		
		System.out.println("\nChiffrement Hill");
		System.out.println("-------------------------");
		
		Hill h = new Hill();
		cypher = h.crypt(message);
		
		System.out.println("Message en clair: " + message);
		System.out.println("Message chiffr�: " + cypher);
		System.out.println("Message d�chiffr�: " + h.decrypt(cypher));
		
		System.out.println("\nChiffrement SDES");
		System.out.println("-------------------------");
		
		SDES s = new SDES("1100101101");
		cypher = s.crypt(message);
		
		System.out.println("Message en clair: " + message);
		System.out.println("Message chiffr�: " + cypher);
		System.out.println("Message d�chiffr�: " + s.decrypt(cypher));
	}

}
