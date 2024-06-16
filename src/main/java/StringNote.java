
public class StringNote {
    public StringNote() {
        // Mutable (StringBuffer:Thread Safe, StringBuilder: Not Thread Safe)
        System.out.println("Mutable (StringBuffer:Thread Safe, StringBuilder: Not Thread Safe)");
        // Thread Safe:
        // StringBuffer (16 characters by default)
        StringBuffer emptyStringBuffer = new StringBuffer();
        // Output: 16
        System.out.println(emptyStringBuffer.capacity());
        // Output: 0
        System.out.println(emptyStringBuffer.length());

        StringBuffer stringBuffer = new StringBuffer("Peter");
        // Output: 21
        System.out.println(stringBuffer.capacity());
        // Output: 5
        System.out.println(stringBuffer.length());
        //Peter Lee
        stringBuffer.append(" Lee");

        // StringBuffer method
        /*
            deleteCharAt, insert, delete, append,
            reverse, toString, substring, setLength,
            ensureCapacity(min),
         */

        // Immutable (String)
        System.out.println("Immutable (String)");
        String name = "Peter";
        System.out.println(name);
        name = stringBuffer.toString();
        System.out.println(name);
        // new String(charArray)
        name = new String(new char[] {'h','e','l','l','o'});
        System.out.println(name);
        char[] charArr = new char[5];
        char[] charArr2 = "hello".toCharArray();
        char[] charArr3 = {'h','e','l','l','o'};

        char a = 'a';
        a += 1; a-=1;// work
        char b = (char) (a + 1); //work (only when casting)
        System.out.printf("a:%c, b:%c%n",a,b);

        char a2 = 'a';
        char b2 = ++a2;
        //a:b b:b (due to ++operation)
        System.out.printf("a2:%c, b2:%c%n",a2,b2);
    }

}
