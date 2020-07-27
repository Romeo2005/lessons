package lesson10;

public class MyArraySizeException extends Exception{
    @Override
    public String getMessage() {
        return "This is not suitable array";
    }
}
