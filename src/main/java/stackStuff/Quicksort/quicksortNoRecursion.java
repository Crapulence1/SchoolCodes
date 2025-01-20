package stackStuff.Quicksort;
import java.util.Scanner;
// Andrew Kabatsky
// 10/28/24
// QuickSort using Lomuto partitioning method
public class quicksortNoRecursion {
    final static int maxSize = 1000;
    static int comparisons = 0;
    public static void main(String [] args){
        String [] data = getData();
        quickSort(data);
        print(data);
        System.out.println(comparisons + ", " + Math.log10(comparisons));
    }
    public static void quickSort(String [] data){
        //precondition: data[l] ... data[r] are all valid strings
        //postcondition: data[n] <= data[n+1] for all n, n <= l < r
        //termination: l >= r; return nothing
        //recursion: right - left > 1 recurse on each half of partitioned array
        //left <- left, right <- middle-1
        //left <- middle+1, right <- right
        List stack = new List();
        stack.push(new int[]{0, data.length-1});
        while(!stack.isEmpty()) {
            int[] range = stack.pop();
            int l = range[0];
            int r = range[1];
            //base case
            if ((r - l) > 0) {
                String pivot = data[l];
                //initialize everything to the leftmost element
                int middle = l;
                int i = l + 1; //start at the first element after the pivot
                while (i <= r) {
                    if (quickSortCompare(data[i], pivot)) { //if i < pivot we found a small side member so we increment middle and swap //original condition data[i].compareTo(pivot) < 0
                        middle++;
                        //swap
                        String temp = data[middle];
                        data[middle] = data[i];
                        data[i] = temp;
                    }
                    i++;
                }
                //need to swap pivot with its final resting place (middle)
                data[l] = data[middle];
                data[middle] = pivot;
                //file partitioned now we need to recurse on each half
                stack.push(new int[]{l, middle-1});
                stack.push(new int[]{middle+1, r});
            }
        }
    }
    public static boolean quickSortCompare(String a, String b){
        comparisons+=1;
        return (a.compareTo(b) < 0);
    }
    //Creates new array with the exact size of the inputted data
    public static String [] getData(){
        int i = 0;
        Scanner in = new Scanner (System.in);
        String [] d = new String[maxSize];
        while (i < d.length && in.hasNextLine()){
            d[i] = in.nextLine();
            i++;
        }
        //make a new array with the proper length and copy inputted data
        String [] input = new String [i];
        System.arraycopy(d, 0, input, 0, i);
        return input;
    }
    //Prints sorted data
    public static void print(String [] arr){
        for (String s : arr) {
            System.out.print(s + "\n");
        }
    }
}
