package mvc;

import java.io.Serializable;

public abstract class Model extends Publisher implements Serializable {
    private boolean unsavedChanges = false;
    private String fileName = null;

    public void changed() {
        unsavedChanges = true;
        notifySubscribers();
    }

    public boolean getUnsavedChanges() {
        return this.unsavedChanges;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setUnsavedChanges(Boolean flag) {
        unsavedChanges = flag;
    }

    public void setFileName(String file) {
        fileName = file;
    }
}
