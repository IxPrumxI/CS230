package Assignment2;

public class Question3 {
    /**
     * The function "can" handle any 2D array size even if it's not a square matrix.
     * If we just remove the static variables and pass the array as a parameter, it will be more flexible.
     * With the use of Array.length, we can get the number of rows and columns dynamically.
     * But since the question provided the rows and columns as static variables, I will keep them as static variables.
     */
    static int rows= 5;
    static int columns= 5;

    public static void main(String[] args) {
        /**
         * The provided sample could be a static variable in the class.
         * Then we won't need to return the array from the reverse2DArray method.
         * But since the question called to have some static variables, but didn't specify the array, I will keep it as a local variable.
         */
        int[][] arr = {
                { 10, 11, 12, 13, 14 },
                { 15, 16, 17, 18, 19 },
                { 20, 21, 22, 23, 24 },
                { 25, 26, 27, 28, 29 },
                { 30, 31, 32, 33, 34 }
        }; // Provided sample.

        System.out.println("The original array: ");
        print2DArray(arr); // Print the original array.

        arr = reverse2DArray(arr); // Reverse the array.

        System.out.println("The reversed (mirrored array): ");
        print2DArray(arr); // Print the reversed array.
    }

    public static int[][] reverse2DArray(int[][] arr) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns / 2; j++) {
                // This is the first element. We will swap it with the last element on the same row.
                // In the next iteration, we will swap the second element with the second last element, and so on.
                // If the array has an odd number of elements, the middle element will not be swapped.
                int temp = arr[i][j];

                arr[i][j] = arr[i][columns - j - 1]; // Left hand side element is now the right hand side element.
                arr[i][columns - j - 1] = temp; // Right hand side element is now the left hand side element.
            }
        }

        return arr;
    }

    public static void print2DArray(int[][] arr) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(arr[i][j] + " "); // We use print instead of println to print the elements in the same line.
            }
            System.out.println(); // Print a new line after each row.
        }
    }
}
