public class Converter
{
    public static void test()
    {
        for(int radix = 2;radix <= 36;radix++)
        {
            System.out.println("[0-"+(radix>10? ("9a-"+(char)(radix+86)):(radix-1))+"]*");
        }
        System.out.println("" + "aaaa".matches("[a-a]*") + "aa-aa".matches("[a-a]*"));
    }
    public static void main(String[] args)
    {
        try
        {
            System.out.println(convert(args[0],toDecimal(args[1],10),toDecimal(args[2],10)));
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("syntax error");
        }
    }
    // Returns a decimal integer equal to the value of a String representing a base 'r' integer
    public static int toDecimal(String number,int radix) throws IllegalArgumentException
    {
        if(radix < 2 || 36 < radix || !number.toLowerCase().matches("[0-"+(radix>10? ("9a-"+(char)(radix+86)):(radix-1))+"]*"))
            throw new IllegalArgumentException();
        int dec = 0;
        int pow = 1;
        for(int i = number.length()-1;i >= 0;i--)
        {
            int c = number.charAt(i);
            dec += (c - 48 - (c > 64 ? 7 : 0) - (c > 96 ? 32 : 0))*pow;
            pow *= radix;
        }
        return dec;
    }
    // Returns a String representing a base 'r' integer whose value is equal to 'dec'
    public static String fromDecimal(int decimal,int radix) throws IllegalArgumentException
    {
        if(radix < 1 || 36 < radix)
            throw new IllegalArgumentException();
        String s = "";
        int pow = 1;
        while(pow*radix < decimal)
        {
            pow *= radix;
        }
        while(pow >= 1)
        {
            int val = decimal/pow;
            s += (char)(val + (val > 9 ? 55 : 48));
            decimal -= val*pow;
            pow /= radix;
        }
        return s;
    }
    // Uses toDecimal and fromDecimal to convert a String from base r1 to r2
    public static String convert(String number,int initialRadix,int finalRadix) throws IllegalArgumentException
    {
        try
        {
            return fromDecimal(toDecimal(number,initialRadix),finalRadix);
        }
        catch(IllegalArgumentException e)
        {
            throw e;
        }
    }
}
