package gitlet;

import java.io.File;
import java.util.*;

import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    public static final File STAGING_AREA = join(GITLET_DIR, "staging_area");

    public static final File STAGED_FILES = join(STAGING_AREA, "staged_files");

    public static TreeMap<File, File> staged_files;

    public static final File COMMITS = join(GITLET_DIR, "commits");

    public static File HEAD = join(COMMITS, "head");

    //public static HashMap<String, File> commit_tree;

    public static final File BLOBS = join(GITLET_DIR, "blobs");

    public static final boolean initialized = GITLET_DIR.exists();

    /* TODO: fill in the rest of this class. */
    public static void initialize() {
        if (initialized) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
            return;
        }
        GITLET_DIR.mkdir();
        COMMITS.mkdir();
        BLOBS.mkdir();
        STAGING_AREA.mkdir();
        try {
            STAGED_FILES.createNewFile();
            staged_files = new TreeMap<>();
            writeObject(STAGED_FILES, staged_files);

            HEAD.createNewFile();
            Commit initial = new Commit();
            writeObject(HEAD, initial);
        } catch (Exception e) {

        }
    }

    public static void addFile(String fileName) {
        if(!initialized) {
            System.out.println("Not in an initialized Gitlet directory.");
            return;
        }

        File srcFilePath = join(CWD, fileName);
        if (!srcFilePath.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        Blob srcFileBlob = new Blob(srcFilePath);
        String srcFileBlobHash = sha1(srcFileBlob.contents());
        File destFilePath = join(BLOBS, srcFileBlobHash);
        if (!destFilePath.exists()) {
            staged_files = readObject(STAGED_FILES, TreeMap.class);
            if (staged_files.containsKey(srcFilePath)) {
                staged_files.get(srcFilePath).delete();
            }
            staged_files.put(srcFilePath, destFilePath);
            writeObject(destFilePath, srcFileBlob);
        }
    }

    public static void makeCommit(String message) {
        Commit commit = new Commit(message, readObject(HEAD, Commit.class));
        File commitDestination = join(COMMITS, sha1(serialize(commit)));
        writeObject(commitDestination, commit);
        writeObject(HEAD, commitDestination);
    }
}
