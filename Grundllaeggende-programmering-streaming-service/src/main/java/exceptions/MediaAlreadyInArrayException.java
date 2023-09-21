package exceptions;

public class MediaAlreadyInArrayException extends Exception {

    private String mediaTitle;

    public MediaAlreadyInArrayException(String mediaTitle) {
        this.mediaTitle = mediaTitle;
    }

    public String getMessage() {
        return mediaTitle + " already contained in the list";
    }

}
