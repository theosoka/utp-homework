import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShoesTest {
    private static final int SIZE = 37;
    private static final String BRAND = "Ecco";

    private Shoes _shoes;

    @Before
    public void before() {
        _shoes = new Shoes(SIZE, BRAND);
        Assert.assertEquals(SIZE, _shoes.size());
        Assert.assertEquals(BRAND, _shoes.brand());
    }

    @Test
    public void aggregate() {
        int aggregate = _shoes.aggregate(null);
        Assert.assertEquals(SIZE, aggregate);
        final int init = 3;
        Assert.assertEquals((int) (SIZE + init), (int) (_shoes.aggregate(init)));
    }

    @Test
    public void deepClone() {
        Shoes clone = _shoes.deepClone();
        Assert.assertEquals(_shoes.size(), clone.size());
        Assert.assertNotSame(_shoes, clone);
    }
}