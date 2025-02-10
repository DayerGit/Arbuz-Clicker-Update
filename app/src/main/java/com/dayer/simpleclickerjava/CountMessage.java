package com.dayer.simpleclickerjava;

public class CountMessage {
    private long count, add;

    public CountMessage(long count, long add) {
        this.count = count;
        this.add = add;
    }

    public long getCount() {
        return count;
    }

    public long getAdd(){
        return add;
    }
}
