package lesson10;

public class MyArrayDataException extends Exception{
    private final int i;
    private final int j;

    public MyArrayDataException(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public String getMessage() {
        return "Not correct format at [" + i + ", " + j + ']';
    }
}
