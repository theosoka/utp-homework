import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PetTest {
    private static final int WEIGHT = 25;
    private static final String BREED = "Shepherd";

    private Pet _pet;

    @Before
    public void before() {
        _pet = new Pet(WEIGHT, BREED);
        Assert.assertEquals(WEIGHT, _pet.weight());
        Assert.assertEquals(BREED, _pet.breed());
    }

    @Test
    public void aggregate() {
        int aggregate = _pet.aggregate(null);
        Assert.assertEquals(WEIGHT, aggregate);
        final int init = 7;
        Assert.assertEquals((int)WEIGHT + init, (int) (_pet.aggregate(init)));
    }

    @Test
    public void deepClone() {
        Pet clone = _pet.deepClone();
        Assert.assertEquals(_pet.weight(), clone.weight());
        Assert.assertNotSame(_pet, clone);
    }
}
