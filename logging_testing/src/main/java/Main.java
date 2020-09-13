import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public int[] returnAllAfterLast4(int[] array) {
        final int SPLIT_CHARACTER = 4;
        List<Integer> inputList = new ArrayList<>();

        for (int i : array)
            inputList.add(i);

        if (!inputList.contains(SPLIT_CHARACTER)) {
            throw new RuntimeException("Array should contain at least one '4'");
        }

        final int splitIndex = inputList.lastIndexOf(SPLIT_CHARACTER);

        int[] returnArray = new int[inputList.size() - splitIndex];

        System.arraycopy(array, splitIndex, returnArray, 0, returnArray.length);

        return returnArray;
    }

    public boolean contains4And1(int[] array) {
        List<Integer> inputList = new ArrayList<>();
        boolean returnValue = true;

        for (int i : array)
            inputList.add(i);

        for (int i : inputList)
            returnValue &= (i == 1 || i == 4);

        return returnValue && inputList.containsAll(Arrays.asList(1, 4));
    }
}
