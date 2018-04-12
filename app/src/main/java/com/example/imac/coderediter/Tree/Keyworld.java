package com.example.imac.coderediter.Tree;

/**
 * Created by imac on 4/12/18.
 */

public class Keyworld {
    private String mSearch;
    private String mDisplay;

    public Keyworld(String search,String display)
    {
        this.mSearch = search;
        this.mDisplay = display;
    }
    public String getmDisplay(){
        return mDisplay;
    }
    public String toString()
    {
        return this.mSearch;
    }

}
