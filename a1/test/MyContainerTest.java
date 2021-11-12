import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyContainerTest {

    public MyContainer<Pet, Integer> cont1 = new MyContainer<>();
    public MyContainer<Shoes, Integer> cont2 = new MyContainer<>();

    @Before
    public void before() {
        cont1.add(new Pet(4, "Bengal"));
        cont1.add(new Pet(50, "Kuvasz"));
        cont1.add(new Pet(333, "Tamworth"));

        cont2.add(new Shoes(42, "Skechers"));
        cont2.add(new Shoes(36, "Converse"));
        cont2.add(new Shoes(39, "Crocs"));
    }

    @Test
    public void aggregateAll()
    {
    int res1 = 387, res2 = 117;
    int testRes1 = cont1.aggregateAllElements();
    int testRes2 = cont2.aggregateAllElements();
    Assert.assertEquals(res1, testRes1);
    Assert.assertEquals(res2, testRes2);
    }

    @Test
    public void cloneElement()
    {
        Pet clonePet = cont1.cloneElementAtIndex(0);
        Shoes cloneShoes = cont2.cloneElementAtIndex(2);
        Assert.assertNotEquals(cont1.get(0), clonePet);
        Assert.assertNotEquals(cont2.get(2), cloneShoes);
    }
}
