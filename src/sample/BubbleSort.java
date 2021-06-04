package sample;

public class BubbleSort {
    public static < T extends Comparable<T>> void sort(String[] arr, boolean asc){
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - (i + 1); j++) {
                if (asc) {
                    if (arr[j].compareTo(arr[j + 1]) > 0) {
                        String temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                } else {
                    if (arr[j].compareTo(arr[j + 1]) < 0) {
                        String temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }
    }
}
