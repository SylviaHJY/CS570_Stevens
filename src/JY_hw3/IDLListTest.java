package JY_hw3;

public class IDLListTest {
    public static void main(String[] args) {
        IDLList<Integer> idlList = new IDLList<>();
        System.out.println(idlList.toString()); //To test empty string
        System.out.println(idlList.getHead()); //To test get head from empty list, return null
        System.out.println(idlList.getLast());//To test get tail from empty list, return null
        System.out.println();

        System.out.println(idlList.add(18)); //add 18 as the first element;
        System.out.println("After adding the first element, the list is: " + idlList.toString());
        System.out.println();

        System.out.println(idlList.add(0,20));// Add value 20 at the head
        System.out.println("After adding element at the index 0, now the list is: " + idlList.toString());
        System.out.println();

        //add elements to list by index
        idlList.add(1,10);
        idlList.add(2,28);
        idlList.add(3,30);
        idlList.add(4,100);
        System.out.println("After adding elements to list, now the list is: " + idlList.toString());
        System.out.println("list size is : " + idlList.size()); //Get size
        System.out.println();

        System.out.println(idlList.append(60));//Append element 60 at the last;
        System.out.println("list size is: " + idlList.size());//Get size
        System.out.println("After appending element to list, now the list is: " + idlList.toString());
        System.out.println();

        System.out.println("The element at index 3 is: " + idlList.get(3));//Get element
        System.out.println();

        System.out.println("The element at the head is: " + idlList.getHead()); //Get head element from non-null list
        System.out.println();

        System.out.println("The last element at the tail is: " + idlList.getLast());// Get tail element from non-null list
        System.out.println();

        System.out.println("Remove element at the head, the element is: " + idlList.remove());
        System.out.println("After removing head element, now the list is: " + idlList.toString());
        System.out.println();

        System.out.println("Remove element at the tail, the element is: " + idlList.removeLast());
        System.out.println("After removing last element, now the list is: " + idlList.toString());
        System.out.println();


        System.out.println("Remove element at the index 2, the element is : " + idlList.removeAt(2));
        System.out.println("After removing the element at the index 2, now the list is: " + idlList.toString());
        System.out.println();

        //Update list, add more elements to list
        //add same elements to list to test remove(), remove the first occurrence of element in the list function
        idlList.add(2,35);
        idlList.add(3,35);
        idlList.add(4,40);
        idlList.add(5,35);
        System.out.println("After adding new elements to list, now the list is: " + idlList.toString());
        System.out.println();

        //removes the first occurrence of elem in the list and returns true.
        // Return false if elem was not in the list.
        System.out.println("Remove element 90, true or false? : " + idlList.remove(90));
        System.out.println("Before remove elements, the list was: " + idlList.toString());
        System.out.println("Remove first appear element 35, true or false? : " + idlList.remove(35));
        System.out.println("After remove first appear element 35, now the list is: " + idlList.toString());
        System.out.println();
    }
}
