import java.util.Iterator;
import java.util.LinkedList;

public class findIntersection {
    public static void main(String args[]){
        String word1 = "endless";
        String word2 = "effortless";
        LinkedList<Character> headA = new LinkedList<Character>();
        LinkedList<Character> headB = new LinkedList<Character>();
        LinkedList<Character> intersectionWord = new LinkedList<Character>();
        //Add the words to the linked list.

        for (int i = 0; i < word1.length(); i++) {
            headA.add(word1.charAt(i));
        }
        for (int i = 0; i < word2.length(); i++){
            headB.add(word2.charAt(i));
        }
        //Check which one is Longer.
        if (word1.length() > word2.length()){
            //Word 1 is longer than Word 2.
            int difference = word1.length() - word2.length();
            for (int i = 0; i < difference; i++){
                headA.remove();
            }
        }
        else {
            //Word 2 is longer than Word 1.
            int difference = word2.length() - word1.length();
            for (int i = 0; i < difference; i++){
                headB.remove();
            }
        }

        //Iterate through both linkedlist
        while (headA.size() != 0){
            Character firstA = headA.getFirst();
            Character firstB = headB.getFirst();
            if (firstA == firstB){
                intersectionWord.add(firstA);
            }
            headA.remove();
            headB.remove();
        }

        if (intersectionWord.size() > 1){
            System.out.println("Lists intersect");
            System.out.println("Intersection segment:" + intersectionWord.toString());
        }
        else {
            System.out.println("Lists don't intersect");
        }
    }


}
