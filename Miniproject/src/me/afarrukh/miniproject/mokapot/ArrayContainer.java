package me.afarrukh.miniproject.mokapot;

/**
 * @author Abdullah Farrukh
 */
public class ArrayContainer<T> {

    private T[] arr;
    private int size;

    public ArrayContainer(T[] arr) {
        this.arr = arr;
        this.size = arr.length;
    }

    public int size() {
        return size;
    }

    public T[] getArr() {
        return arr;
    }

    public T get(int pos) {
        return arr[pos];
    }
}

