/**
 * A library of string functions.
 */
public class MyString {
    //You can use any possible String function
    public static void main(String args[]) {
        String hello = "hello";
        System.out.println(countChar(hello, 'h'));   //1
        System.out.println(countChar(hello, 'l'));  //2
        System.out.println(countChar(hello, 'z'));  //0
    
        System.out.println(subsetOf("sap","space"));  //true   
        System.out.println(subsetOf("spa","space"));  // true
        System.out.println(subsetOf("pass","space"));   // false
        System.out.println(subsetOf("c","space"));     // true
        
        System.out.println(spacedString(hello));   // h e l l o
        
        System.out.println(randomStringOfLetters(10));
        System.out.println(randomStringOfLetters(5));

        System.out.println(remove("committee","meet"));   //comit
    }

    /**
     * Returns the number of times the given character appears in the given string.
     * Example: countChar("Center",'e') returns 2 and countChar("Center",'c') returns 0. 
     * 
     * @param str - a string
     * @param c - a character
     * @return the number of times c appears in str
     */
    public static int countChar(String str, char ch) {
        int counter = 0;
        for(int i = 0; i < str.length(); i ++){
            if (str.charAt(i) == ch){
                counter ++;
            }
        }
        return counter;         //its ok if str is empty that way
    }

    /** Returns true if str1 is a subset string str2, false otherwise
     *  Examples:
     *  subsetOf("sap","space") returns true
     *  subsetOf("spa","space") returns true
     *  subsetOf("pass","space") returns false
     *  subsetOf("c","space") returns true
     *
     * @param str1 - a string
     * @param str2 - a string
     * @return true is str1 is a subset of str2, false otherwise
     */

     // subsetOf function can make use of the countChar function
     public static boolean subsetOf(String str1, String str2) {     //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //true if str1 is sub of string of str2
        // if(str2 == null){
        //     return false;
        // }

        int len2 = str2.length();
        int len1 = str1.length();
        if(len2 < len1){               //str2 have to be str2 >= str1
           return false;
        }
        if (len1 == 0){                //str1 is empty
           return true;
        }

        for(int i = 0; i < len1; i ++){   // on str1
            boolean isExist = false;
            char str1Char = str1.charAt(i);
            int timesIn1 = countChar(str1, str1Char), timesIn2 = countChar(str2, str1Char);
            if(timesIn1 != timesIn2){
                return false;
            }

            // for(int j = 1; j < len1; j++){              // on str2
            //     if(str1Char == str2.charAt(j)){
            //         isExist = true;
            //         String char1 = "" + str1Char;
            //         str2 = remove(str2, char1);
            //         break;
            //     }
            // }
            // if (!isExist){
            //     return false;
            // } 
        }

        return true;
    }
       
     // public static boolean subsetOf(String str1, String str2) {     //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //     //true if str1 is sub of string of str2
    //     if(str2 == null){
    //         return false;
    //     }

    //     int len2 = str2.length();
    //     int len1 = str1.length();
    //     if(len2 < len1){               //str2 have to be str2 >= str1
    //        return false;
    //     }
    //     if (len1 == 0){                //str1 is empty
    //        return true;
    //     }

    //     char firstChar1 = str1.charAt(0);
    //     for(int i = 0; i < (len2 - len1 + 1); i ++){
    //         boolean isSub = true;
    //         if (str2.charAt(i) == firstChar1){
    //             int k = i + 1;
    //             for(int j = 1; j < len1; j++){
    //                 if(str2.charAt(k) != str1.charAt(j)){
    //                     isSub = false;
    //                     break;
    //                 }
    //                 k ++;
    //             }
    //             if (isSub){
    //                 return true;
    //             }
    //         } 
    //     }

    //     return false;
    // }

    /** Returns a string which is the same as the given string, with a space
     * character inserted after each character in the given string, except
     * for the last character. 
     * Example: spacedString("silent") returns "s i l e n t"
     * 
     * @param str - a string
     * @return a string consisting of the characters of str, separated by spaces.
     */
    public static String spacedString(String str) {
        if(str == null){              //str = null
            return str;
        }

        int lenStr = str.length();     //str = ""
        if(lenStr == 0){
            return str;
        }

        if(lenStr == 1){
            return str; 
        }

        String newStr = "" + str.charAt(0) + " ";
        for(int i = 1; i < lenStr - 1; i ++){
            newStr += "" + str.charAt(i) + " ";
        }
        newStr += "" + str.charAt(lenStr - 1);
        return newStr;
    }
  
    /**
     * Returns a string of n lowercase letters, selected randomly from 
     * the English alphabet 'a', 'b', 'c', ..., 'z'. Note that the same
     * letter can be selected more than once.
     * 
     * Example: randomStringOfLetters(3) can return "zoo"
     * 
     * @param n - the number of letter to select
     * @return a randomly generated string, consisting of 'n' lowercase letters
     */

    //a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z   - 26 letters
    //0 = a ..... 25 = z
    public static String randomStringOfLetters(int n) {
        String word = "";
        char[] englishLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',  
                                 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        
        for (int i = 1; i <= n; i ++){
            int randomNumber = (int)(Math.random() * 26);
            word += englishLetters[randomNumber];
        }

        return word;
    }

    /**
     * Returns a string consisting of the string str1, minus all the characters in the
     * string str2. Assumes (without checking) that str2 is a subset of str1.
     * Example: remove("meet","committee") returns "comit" 
     * 
     * @param str1 - a string
     * @param str2 - a string
     * @return a string consisting of str1 minus all the characters of str2
     */
    public static String remove(String str2, String str1) {
        //kinda: str2 - str1
        for(int i = 0; i < str1.length(); i ++){
            char str1Char = str1.charAt(i);
            for(int j = 0; j < str2.length(); j ++){
                if(str1Char == str2.charAt(j)){
                    if(j == 0){
                        if(str2.length() == 1){
                            return "";
                        } else {
                            str2 = str2.substring(1, str2.length());
                        }
                    } else {
                        String newStr = str2.substring(0, j);
                        if (j + 1 != str2.length()){
                            newStr += str2.substring(j + 1, str2.length());
                        }
                        str2 = newStr;
                    }
                }
            }
        }

        return str2;
    }

    /**
     * Returns a string consisting of the given string, with the given 
     * character inserted randomly somewhere in the string.
     * For example, insertRandomly("s","cat") can return "scat", or "csat", or "cast", or "cats".  
     * @param ch - a character
     * @param str - a string
     * @return a string consisting of str with ch inserted somewhere
     */

    //You can use any possible String function, including subString, which can be quite useful in the
    // implementation of the insertRandomly function
    public static String insertRandomly(char ch, String str) {
         // Generate a random index between 0 and str.length()
         int randomIndex = (int) (Math.random() * (str.length() + 1));
         // Insert the character at the random index
         String result = str.substring(0, randomIndex) + ch + str.substring(randomIndex);
         return result;
    }    
}
