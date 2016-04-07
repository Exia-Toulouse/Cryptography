import java.util.ArrayList;

public class SDES 
{
	private Byte[] master_key;
	
	private Byte[][][] s_box0 =  
		{  
				{ 
					{0,   1}, { 0,  0}, { 1,  1}, { 1,  0} 
				},
				{ 
					{1,   1}, { 1,  0}, { 0,  1}, { 0,  0} 
				},
				{ 
					{0,   1}, { 0,  0}, { 1,  1}, { 1,  1}  
				},
				{ 
					{0,   1}, { 0,  0}, { 1,  1}, { 1,  0} 
				}
		};
	
	private Byte[][][] s_box1 =  
		{  
				{ 
					{0,   0}, { 0,  1}, { 1,  0}, { 1,  1} 
				},
				{ 
					{1,   0}, { 0,  0}, { 0,  1}, { 1,  1} 
				},
				{ 
					{1,   1}, { 0,  0}, { 0,  1}, { 0,  0} 
				},
				{ 
					{1,   0}, { 0,  1}, { 0,  0}, { 1,  1} 
				}
		};
	
	public SDES(String key)
	{
		int i, l = key.length();
		master_key = new Byte[10];
		
		for (i = 0; i < l; i++)
		{
			if (key.charAt(i) == '1')
				master_key[i] = 1;
			else
				master_key[i] = 0;
		}	
	}
		
	public Byte[] p10(Byte[] key)
    {
        Byte[] permuted = new Byte[10];
        
        permuted[0] = key[2];
        permuted[1] = key[4];
        permuted[2] = key[1];
        permuted[3] = key[6];
        permuted[4] = key[3];
        permuted[5] = key[9];
        permuted[6] = key[0];
        permuted[7] = key[8];
        permuted[8] = key[7];
        permuted[9] = key[5];
        
        return permuted;
    }
	
	public Byte[] circularLeftShift(Byte[] key, int bits)
	{
		Byte[] modified = new Byte[key.length];
		
		int mid = key.length / 2;
		int rest = mid - bits;
		
		System.arraycopy(key, 0, modified, mid - bits, bits);
		System.arraycopy(key, mid, modified, key.length - bits, bits);
		System.arraycopy(key, bits, modified, 0, rest);
		System.arraycopy(key, mid + bits, modified, mid, rest);
		
		return modified;
	}
    
    public Byte[] p8(Byte[] key)
    {
        Byte[] permuted = new Byte[8];
        
        permuted[0] = key[5];
        permuted[1] = key[2];
        permuted[2] = key[6];
        permuted[3] = key[3];
        permuted[4] = key[7];
        permuted[5] = key[4];
        permuted[6] = key[9];
        permuted[7] = key[8];
        
        return permuted;
    }
    
    public ArrayList<Byte[]> generateKeys()
    {
    	ArrayList<Byte[]> keys = new ArrayList<Byte[]>();
    	
    	keys.add(this.p8(this.circularLeftShift(this.p10(this.master_key), 1)));
    	keys.add(this.p8(this.circularLeftShift(this.p10(this.master_key), 3)));
    	
    	return keys;
    }
    
    public Byte[] ip(Byte[] plainText)
    {
    	Byte[] permuted = new Byte[8];
    	
    	permuted[0] = plainText[1];
        permuted[1] = plainText[5];
        permuted[2] = plainText[2];
        permuted[3] = plainText[0];
        permuted[4] = plainText[3];
        permuted[5] = plainText[7];
        permuted[6] = plainText[4];
        permuted[7] = plainText[6];
        
        return permuted;
    }
    
    public Byte[] rip(Byte[] permutedText)
    {
    	Byte[] permuted = new Byte[8];
    	
    	permuted[0] = permutedText[3];
        permuted[1] = permutedText[0];
        permuted[2] = permutedText[2];
        permuted[3] = permutedText[4];
        permuted[4] = permutedText[6];
        permuted[5] = permutedText[1];
        permuted[6] = permutedText[7];
        permuted[7] = permutedText[5];
        
        return permuted;
    }
    
    public Byte[] ep(Byte[] input)
    {
        Byte[] result = new Byte[8];
        
        result[0] = input[3];
        result[1] = input[0];
        result[2] = input[1];
        result[3] = input[2];
        result[4] = input[1];
        result[5] = input[2];
        result[6] = input[3];
        result[7] = input[0];
        
        return result;
    }
    
    public Byte[] xor(Byte[] a, Byte[] b)
    {
        Byte[] result = new Byte[a.length];
        
        for (int i = 0, l = a.length; i < l; i++)
        {
        	result[i] = (byte) (a[i] ^ b[i]);
        }
        
        return result;
    }
    
    public Byte[] sandBox0(Byte[] leftPart)
    {
    	Byte[] result = new Byte[2];
    	
    	int x = byteToInt(leftPart[0], leftPart[3]);
    	int y = byteToInt(leftPart[1], leftPart[2]);
    	
    	result[0] = this.s_box0[x][y][0];
    	result[1] = this.s_box0[x][y][1];
    	
    	return result;
    }
    
    public int byteToInt(Byte a, Byte b)
    {
    	if (a == 0 && b == 0)
    		return 0;
    	if (a == 0 && b == 1)
    		return 1;
    	if (a == 1 && b == 0)
    		return 2;
    	if (a == 1 && b == 1)
    		return 3;
    	
    	return 5;
    }
    
    public Byte[] sandBox1(Byte[] rightPart)
    {
    	Byte[] result = new Byte[2];
    	
    	int x = byteToInt(rightPart[0], rightPart[3]);
    	int y = byteToInt(rightPart[1], rightPart[2]);
    	
    	result[0] = this.s_box1[x][y][0];
    	result[1] = this.s_box1[x][y][1];
    	
    	return result;
    }
    
    public Byte[] p4(Byte[] part1, Byte[] part2)
    {
    	Byte[] result = new Byte[4];
    	
    	result[0] = part1[1];
    	result[1] = part2[1];
    	result[2] = part2[0];
    	result[3] = part1[0];
    	
    	return result;
    }
    
    public Byte[] f(Byte[] right, Byte[] sk)
    {
    	Byte[] result = new Byte[8];
    	
    	result = this.ep(right);
    	result = this.xor(result, sk);
    	
    	Byte[] part1 = new Byte[4], part2 = new Byte[4];
    	System.arraycopy(result, 0, part1, 0, 4);
    	System.arraycopy(result, 4, part2, 0, 4);
    	
    	part1 = this.sandBox0(part1);
    	part2 = this.sandBox1(part2);
    	
    	return this.p4(part1, part2);
    }
    
    public Byte[] fk(Byte[] bits, Byte[] key)
    {
    	Byte[] result = new Byte[8];
    	
    	Byte[] part1 = new Byte[4], part2 = new Byte[4];
    	System.arraycopy(bits, 0, part1, 0, 4);
    	System.arraycopy(bits, 4, part2, 0, 4);
    	
    	System.arraycopy(part1, 0, result, 0, 4);
    	
    	System.arraycopy(this.xor(part1, this.f(part2, key)), 0, result, 4, 4);
    	
    	return result;
    }
    
    public Byte[] sw(Byte[] input)
    {
    	Byte[] result = new Byte[input.length];
    	
    	Byte[] part1 = new Byte[4], part2 = new Byte[4];
    	System.arraycopy(input, 0, part1, 0, 4);
    	System.arraycopy(input, 4, part2, 0, 4);
    	
    	System.arraycopy(part2, 0, result, 0, 4);
    	
    	System.arraycopy(part1, 0, result, 4, 4);

    	
    	return result;
    }
    
    public Byte[] charToByte(String letter)
    {
    	String binary = Integer.toString(letter.getBytes()[0], 2);
    	Byte[] result = {0,0,0,0,0,0,0,0};
    	
    	for (int i = 0; i < binary.length(); i++)
    	{
    		result[i] = Byte.parseByte(binary.substring(i, i+1), 2);
    	}
    	
    	return result;
    }
    
    public String byteToChar(Byte[] block)
    {
    	String a = "";
    	for (int i = 0; i < block.length; i++)
    	{
    		a += block[i];
    	}
    	
    	int charCode = Integer.parseInt(a, 2);
    	return new String(new Character((char)charCode).toString());
    }
    
    public String crypt(String block)
    {
    	String result = "";
    	ArrayList<Byte[]> keys = this.generateKeys();
    	Byte[] bresult = null;
    	
    	for (int i = 0, l = block.length(); i < l; i++)
    	{
    		bresult = this.ip(this.charToByte(block.substring(i, i+1)));
    		
    		bresult = this.fk(bresult, keys.get(0));
    		
    		bresult = this.sw(bresult);
    		
    		bresult = this.fk(bresult, keys.get(1));
    		bresult = this.rip(bresult);
    		
    		result += this.byteToChar(bresult);
    	}
    	
    	return result;
    }
    
    public String decrypt(String block)
    {
    	String result = "";
    	ArrayList<Byte[]> keys = this.generateKeys();
    	Byte[] bresult = null;
    	
    	for (int i = 0, l = block.length(); i < l; i++)
    	{
    		bresult = this.ip(this.charToByte(block.substring(i, i+1)));
    		bresult = this.fk(bresult, keys.get(1));
    		bresult = this.sw(bresult);
    		bresult = this.fk(bresult, keys.get(0));
    		bresult = this.rip(bresult);
    		result += this.byteToChar(bresult);
    	}
    	
    	return result;
    }
    
}
