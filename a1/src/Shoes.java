public final class Shoes implements IAggregable<Shoes, Integer>, IDeeplyCloneable<Shoes>{
   private int _size;
   private String _brand;


   public Shoes(int size, String brand){
       _size = size;
       _brand = brand;
   }

    public int size() {
        return _size;
    }

    public String brand() {
        return _brand;
    }

    @Override
    public Integer aggregate(Integer intermediateResult) {
        if  (intermediateResult == null) {
            return _size;
        }
        return _size + intermediateResult;
    }

    @Override
    public Shoes deepClone() {
        return new Shoes(_size, _brand);
    }

    @Override
    public String toString() {
        return "Shoes " +
                _size + "size, " +
                "brand - '" + _brand + '\'';
    }
}
