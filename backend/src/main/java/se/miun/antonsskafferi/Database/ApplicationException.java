package se.miun.antonsskafferi.Database;

public class ApplicationException extends Exception{
    private int status = 400;
    public ApplicationException(String message){
        super(message);
    }

    public ApplicationException(String message, int status) {
        this(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}

