package gitlet;

import java.io.File;
import java.io.Serializable;
import static gitlet.Utils.readContents;
import static gitlet.Utils.sha1;

public class Blob implements Serializable {
    private byte[] contents;
    public Blob(File f) {
        contents = readContents(f);
    }

    public byte[] contents() {
        return contents;
    }
}
