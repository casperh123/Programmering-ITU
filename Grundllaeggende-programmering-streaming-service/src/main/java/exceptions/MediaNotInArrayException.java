package exceptions;

public class MediaNotInArrayException extends Exception {

    private String mediaTitle;

    public MediaNotInArrayException(String mediaTitle) {
        this.mediaTitle = mediaTitle;
    }

    public String getMessage() {
        return mediaTitle + " is not contained in the list";
    }

}
