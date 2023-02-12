package gitlet;

// TODO: any imports you need here

import java.io.File;
import java.io.Serializable;
import java.util.Date; // TODO: You'll likely use this in this class
import java.util.HashMap;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;
    private Date datetime;
    private Commit parent;
    public HashMap<File, String> blobs;

    public Commit(String m, Commit p) {
        message = m;
        datetime = new Date();
        parent = p;
        blobs = p.blobs;
    }

    public Commit() {
        message = "initial commit";
        datetime = new Date(0);
        parent = null;
        blobs = new HashMap<>();
    }

    public String message() {
        return message;
    }

    public Date datetime() {
        return datetime;
    }


    /* TODO: fill in the rest of this class. */
}
