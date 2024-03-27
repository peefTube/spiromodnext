package com.github.peeftube.spiromodnext.util;

public class MinMax
{
    static int min = 1;
    static int max = 1;

    public MinMax(int min, int max)
    { this.min = min; this.max = max; }

    public static int getMin()
    { return min; }
    public static int getMax()
    { return max; }
}
