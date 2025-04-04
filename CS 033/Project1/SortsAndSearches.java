import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * A class created with various sorting and searching algorithms,
 * mainly to test them.
 * @author Evan Chou, Jordan Marcelo, Alexander Nota
 * @version 1.0
 * 3/30/25
 * CS 033
 * Professor Ashraf
 */
public class SortsAndSearches {
    /**
     * A main method to test sort and search methods on integer arrays,
     * noting that the code would be nearly the same to test on objects.
     * Algorithms adapted from pseudocode by author.
     * 
     * @author Jordan Marcelo
    */
    public static void main(String[] args) {
        // Crucially, the testing array is out of order and contains only distinct numbers
        final int[] array = {12, 11, 13, 5, 6};
        // Use built-in Java sorting method to ensure correctness (it is implemented with a merge sort)
        int[] sortedArr = array.clone();
        Arrays.sort(sortedArr);
        // Create a map to each sort, used to loop through sorts
        final Map<String, Consumer<int[]>> algorithms = new LinkedHashMap<>();
        algorithms.put("Insertion Sort", SortsAndSearches::InsertionSort);
        algorithms.put("Selection Sort", SortsAndSearches::SelectionSort);
        algorithms.put("Merge Sort", SortsAndSearches::MergeSortWrapper);
        int total = 0; // Keep track of total number of results achieved
        int totalCorrect = 0; // Keep track of total number of correct results achieved


        // Display original array
        System.out.println("\nOriginal Array:");
        printArray(array);

        // Display correctly sorted array
        System.out.println("\nCorrectly Sorted Array:");
        printArray(sortedArr);
        System.out.println();

        // Compare results of searching for each element in the array to correct answer by tallying comparison results
        for (int i = 0; i < sortedArr.length; i++) {
            int val = sortedArr[i];
            System.out.println("Searching for element " + val + " at index " + i + ":");

            int linResult = LinearSearch(sortedArr, val);
            boolean linCorrect = linResult == i;
            System.out.println("\tLinear search result: " + linResult);
            System.out.println("\tLinear search result correct: " + linCorrect);

            int binResult = BinarySearch(sortedArr, val);
            boolean binCorrect = binResult == i;
            System.out.println("\tBinary search result: " + binResult);
            System.out.println("\tBinary search result correct: " + binCorrect);

            if (linCorrect) {
                totalCorrect++;
            }
            if (binCorrect) {
                totalCorrect++;
            }
            total += 2;
        }

        // Summarize searching results
        System.out.println("\nPercent of searches correct: " + String.format("%.2f", 100.0 * totalCorrect / total) + "\n\n");
        // Clear variables for future trials
        total = 0;
        totalCorrect = 0;



        // Display original array
        System.out.println("\nOriginal Array:");
        printArray(array);

        // Display correctly sorted array
        System.out.println("\nCorrectly Sorted Array:");
        printArray(sortedArr);

        // Compare correctly sorted array to each sort's result by looping through sorts and tallying their correctness
        for (Map.Entry<String, Consumer<int[]>> entry : algorithms.entrySet()) {
            int[] attemptedSort = (int[])array.clone();
            entry.getValue().accept(attemptedSort);

            System.out.print("\nAfter " + entry.getKey() + ": ");
            printArray(attemptedSort);
            
            boolean correct = Arrays.equals(sortedArr, attemptedSort);
            System.out.println(entry.getKey() + " is correct: " + correct);
            if (correct) {
                totalCorrect++;
            }
            total++;
        }

        // Summarize sorting algorithm testing results
        System.out.printf("\nPercent of sorting algorithms correct: " + String.format("%.2f", 100.0 * totalCorrect / total) + "\n\n");
    }

    /**
     * A rudimentary method to print arrays
     * @author Jordan Marcelo
     * @param array the array to be printed
     */
    public static void printArray(int[] array) {
        String printable = "[";
        for (int i = 0; i < array.length; i++) {
            printable += array[i];
            if (i < array.length - 1) {
                printable += ", ";
            }
        }
        System.out.print(printable + "]\n");
    }


    /**
     * This sorts an array of integers using insertion sort,
     * which has best case time complexity O(n), otherwise it is O(n^2),
     * since it has the potential to facilitate more swaps than the array has entries.
     * Its space complexity is O(1) because its memory usage is constant per element.
     * 
     * Insertion sort works by comparing each element to the element to its left,
     * and swapping appropriately so that the new element to the left is less
     * than the element to its right.
     * It maintains a sorted left side of the array by repeating this comparison
     * for all elements to the left of the indexed element,
     * using comparison logic to place the indexed element into the sorted left side
     * and increment the index,
     * thereby maintaining the sorted left side of the array.
     * This is done until the entire array is sorted.
     * 
     * @author Jordan Marcelo
     * 
     * @param array The array of integers to be sorted in place with insertion sort
     */
    public static void InsertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {    // Loop through the array after pre-sorted part, first index by itself is sorted
            int key = array[i]; // Go to the index after the pre-sorted part
            int j = i - 1; // Go to the last index of the pre-sorted part

            
            // Swap key (first element after sorted part) backwards if it is less than whatever is to its left
            // Keep swapping until key is in its place in the sorted part
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
            // Then increment i so that the pre-sorted part is advanced toward end of array
        }
    }

    /**
     * Selection sort treats the input as two parts, a sorted parts and an unsorted part, and
     * repeatedly selects the proper next value to move from the unsorted part to the end of the
     * sorted part. This algorithm has a runtime of O(N^2), and if a list has N elements, the outer
     * loop executes N-1 times. For each of those N - 1 outer loop executions, the inner loop
     * executes an average of N/2 times, so the total number of comparisons is proportional to
     * O(N^2) as well.
     *
     * The algorithm goes as follows: we use two indexes, i and j, to keep track of the two parts
     * respectively. Then, we search the unsorted part of the array for the smallest element, and
     * store the index of this element into a variable called smallestIndex. We then swap the
     * element at the index i and smallestIndex, and update the indices for the sorted and unsorted
     * parts of the array. We continue this process of searching the unsorted part and swapping
     * the smallest element with the element at index i until all elements are sorted.
     * 
     * @author Evan Chou
     * 
     * @param list the list of integers to be sorted
     */
    public static void SelectionSort(int[] list) {
        int smallestIndex = 0; // Stores the index of the smallest element
        int temporary = 0; // Used as a temporary placeholder for the switching process

        for (int i = 0; i < list.length; i++) { // Iterates over the entire list
            smallestIndex = i; // Stores the index of the smallest element
            for (int j = i + 1; j < list.length; j++) { // Iterates over the elements after the i element
                if (list[j] < list[smallestIndex]) { // Checks for smallest element in unsorted
                    smallestIndex = j; // Replaces smallest index with index j
                }
            }
            // Switching the elements using a temporary placeholder
            temporary = list[i];
            list[i] = list[smallestIndex];
            list[smallestIndex] = temporary;
        }
    }

    /**
     * Wrapper method for MergeSort to match the Consumer<int[]> signature.
     * It calls MergeSort with the full array range.
     * @author Jordan Marcelo
     * @param array the array to be sorted
     */
    public static void MergeSortWrapper(int[] array) {
        MergeSort(array, 0, array.length - 1);
    }

    /**
     * Merge sort recursively divides the array in halves until each element is separated 
     * elements are then sorted and merged back together
     * the merging of the elements takes place in the merge method
     * Merge sort has a time complexity of O(n log n) in all cases, meaning it has stable performance
     * Merge sort also needs O(n) extra memory for the temporary array used for the merged result
     * 
     * @author Alexander Nota
     * 
     * @param array the array to be sorted
     * @param start the starting index of the sort
     * @param end the ending index of the sort
     */
    public static void MergeSort(int[] array, int start, int end){
        int mid = 0;
        
        if(start < end){				//proceed if subarray has more than 1 element
            mid = (start + end) / 2;		//find mid point
            
            MergeSort(array, start, mid);		//sort left half
            MergeSort(array, mid + 1, end);	//sort right half
            Merge(array, start, mid, end);		//merge left and right
        }
    }
    
    /**
     * The merge method takes two sorted halves and combines them into the temporary array
     * Once the temp array has the sorted values it is copied back into the original array
     * 
     * @author Alexander Nota
     * 
     * @param array the array to be sorted
     * @param leftStart the start of the left encroaching cursor
     * @param leftEnd the start of the right encroaching cursor
     * @param rightEnd the end of the right array
     */
    public static void Merge(int[] array, int leftStart, int leftEnd, int rightEnd){
        int totalSize = rightEnd - leftStart + 1;		//how many elements to merge
        int mergeIndex = 0;
        int leftIndex = leftStart;
        int rightIndex = leftEnd + 1;
        int[] mergedArr = new int[totalSize];		//temp array to hold merged result
    
        while (leftIndex <= leftEnd && rightIndex <= rightEnd){
            if(array[leftIndex] <= array[rightIndex]){      //smaller element placed into array
                mergedArr[mergeIndex] = array[leftIndex];
                leftIndex++;
            } else{
                mergedArr[mergeIndex] = array[rightIndex];
                rightIndex++;
            }
            mergeIndex++;
        }
    
     while (leftIndex <= leftEnd) {			//copy left over from left subarray
        mergedArr[mergeIndex] = array[leftIndex];
        ++leftIndex;
        ++mergeIndex;
       }
    
        while (rightIndex <= rightEnd){		//copy left over from right subarray
            mergedArr[mergeIndex] = array[rightIndex];
            rightIndex++;
            mergeIndex++;
        }
    
        for (mergeIndex = 0; mergeIndex < totalSize; mergeIndex++){   
            array[leftStart + mergeIndex] = mergedArr[mergeIndex];
        }                       //copy merged back to original array
    }

    /**
     * Linear search involves starting at the beginning of the array and looking at each element
     * until it matches the key
     * The time complexity for linear search is O(n) for average and worst case, this scales with the 
     * size of the array as we are looking through every single element at worst case
     * 
     * @author Alexander Nota
     * 
     * @param array the array to be searched
     * @param key the element 
     * @return the index of key in the array, or -1 if key not in array
     */
    public static int LinearSearch(int[] array, int key){
        for (int i = 0; i < array.length; i++){		//go through each element
            if(array[i] == key)		
                return i;			//return element if found
        }  
        return -1;				//element not found
    }

    /**
     * Binary search starts by looking at the middle element of the array
     * We then look at the left or right section of the array, depending if the key is greater or smaller
     * than the current element
     * Binary search only works on sorted arrays since we are assuming all elements to the right 
     * are greater and all elements to the left are smaller
     * Each iteration of the algorithm reduces the search space by about half
     * In binary search, worst and average time complexities are both O(log n), this is because the
     * array is always cut in half, so the number of comparisons will be fairly similar
     * 
     * @author Alexander Nota
     * 
     * @param array the array to be searched
     * @param key the element to be searched for in the array
     * @return the index of key in the array, or -1 if key not in array
     */
    public static int BinarySearch(int[] array, int key){
        int start = 0;
        int mid = 0;
        int end = array.length - 1;
    
        while (end >= start){
            mid = (start + end) / 2;		//middle of current range
            if (array[mid] < key){
                start = mid + 1;		//if key in right half move start index
            }
            else if (array[mid] > key){    
                end = mid - 1;		//otherwise key is in left, move end index
            }
            else {
                return mid;		//otherwise mid is the key
            }
        }
        return -1;				//key not found
    }
}