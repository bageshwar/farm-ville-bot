/*
 * Persist.java
 *
 * Created on December 19, 2009, 12:08 AM
 *
 */

package fvb;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class contains a hashmap of values,
 * one needs, for the bot to work.
 * @author Bageshwar
 * @version 1.0
 */
public class Persist implements Serializable{
    
    /** Initialize Persist */
    public HashMap map;
    public Persist() {
        map  = new HashMap();
    }
    
}
