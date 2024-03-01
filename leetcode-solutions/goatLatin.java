A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and 
uppercase letters only.

We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)

The rules of Goat Latin are as follows:

If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
For example, the word 'apple' becomes 'applema'.
 
If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the
end, then add "ma".
For example, the word "goat" becomes "oatgma".
 
Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
For example, the first word gets "a" added to the end, the second word gets "aa" added to the end 
and so on.
Return the final sentence representing the conversion from S to Goat Latin. 


Example 1:

Input: "I speak Goat Latin"
Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"

Example 2:

Input: "The quick brown fox jumped over the lazy dog"
Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"


//TC: O(n^2) where n is length of string, because we have to go through each word, and then add extra a's which on last iteration will be of length (n-1)
//SC: O(n^2) to 

//CAN IMPROVE AND USE STRINGBUILDER 

class Solution {
    public String toGoatLatin(String S) {
        HashSet<Character> vowels = new HashSet<>(); 
        for(char c : "aeiouAEIOU".toCharArray()){ //convert to char array
            vowels.add(c); 
        } //now we have all vowels in hashset
        
        String result = "";
        int index = 1; 
        for(String word: S.split("\\s")){ //split on white space to get words              
            if(index >1 ) {  //dont need to add space for 1st index
                result += " "; //need to add a space for each word after
            }
            char first_letter = word.charAt(0);
            if(vowels.contains(first_letter)){
                result+=word+ "ma"; //VOWEL, so just add ma to end
            } else { //consontant, so add substring from 1 to end, plus first letter, plus ma
                result += word.substring(1) + first_letter + "ma";  
            }
            
            for(int j=0; j<index; j++){  //add all the a's, depending on what letter we are on 
                result+= "a"; 
            }
            index++;
        }
        
            
        return result;
    }
}