import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainTester {
    private Main obj;

    @Before
    public void start() {
        obj = new Main();
    }

    @Test
    public void splitTester1() {
        Assert.assertArrayEquals(new int[]{4, 5}, obj.returnAllAfterLast4(new int[]{1, 4, 5}));
    }

    @Test(expected = RuntimeException.class)
    public void splitTester2() {
        obj.returnAllAfterLast4(new int[]{1, 2, 3});
    }

    @Test
    public void containsTester1() {
        Assert.assertTrue(obj.contains4And1(new int[]{1, 4, 1, 1, 4}));
    }

    @Test
    public void containsTester2() {
        Assert.assertFalse(obj.contains4And1(new int[]{1, 4, 1, 1, 5}));
    }

    @Test
    public void containsTester3() {
        Assert.assertFalse(obj.contains4And1(new int[]{1, 1, 1}));
    }

    @Test
    public void containsTester4() {
        Assert.assertFalse(obj.contains4And1(new int[]{4, 4, 4}));
    }
}
