package com.example.nabee.myreddit.model.Entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nabee on 2/13/2018.
 */

public class filterTags {
    private static final String TAG = "filterTags";

    private String tag;
    private String xml;

    public filterTags(String tag, String xml) {
        this.tag = tag;
        this.xml = xml;
    }

    public List<String>init(){
        List<String> result = new ArrayList<>();
        String[] splitXML = xml.split(tag + "\"");
        int count = splitXML.length;

        for(int i = 1; i<count;i++){

            String tmp = splitXML[i];
            int index = tmp.indexOf("\"");

            tmp = tmp.substring(0,index);
        }

        return result;
    }
}
