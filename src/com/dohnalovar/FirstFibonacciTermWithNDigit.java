package com.dohnalovar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dohnalovar on 6/11/2019
 */
public class FirstFibonacciTermWithNDigit {

    private List<Integer> first = new ArrayList<>(Arrays.asList(1));
    private List<Integer> second = new ArrayList<>(Arrays.asList(1));

    private List<Integer> last = second;
    private List<Integer> previous = first;
    private int indexOfLast = 2;
    private int digits;

    public FirstFibonacciTermWithNDigit(int digits) {
        this.digits = digits;

        while (last.size() < digits) {
            getNextFibonacci();
        }
    }

    private void getNextFibonacci() {
        // previous = last + previous
        int overflow = 0;
        int i;
        int prevSize = previous.size();
        for (i = 0; i < previous.size(); i++) {
            int value = previous.get(i);
            value = value +last.get(i) + overflow;
            overflow = value / 10;
            if (i < prevSize)
                previous.set(i, value % 10);
            else
                previous.add(i, value % 10);
        }
        // solve overflowing
        boolean lastCheck = i < last.size();
        while (lastCheck || overflow != 0) {
            int value = (lastCheck ? last.get(i) : 0) + overflow;
            overflow = overflow / 10;
            previous.add(i, value % 10);
            i++;
            lastCheck = i < last.size();
        }

        // switch previous and last
        List<Integer> tmp = last;
        last = previous;
        previous = tmp;

        // increment index of last
        indexOfLast++;
    }

    public int getIndex() {
        return indexOfLast;
    }

    @Override
    public String toString() {
        String lastStr = "";
        for (int i = last.size()-1; i >= 0 ; i--) {
            lastStr = lastStr + last.get(i).toString();
        }
        return "FirstFibonacciTermWithNDigit{" +
            "last=" + lastStr +
            ", indexOfLast=" + indexOfLast +
            ", digits=" + digits +
            '}';
    }
}
