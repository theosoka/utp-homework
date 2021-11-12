public final class Pet implements IAggregable<Pet, Integer>, IDeeplyCloneable<Pet>{
    private int _weight;
    private String _breed;



    public Pet(int weight, String breed)
    {
        _weight = weight;
        _breed = breed;
    }

    public int weight() {
        return _weight;
    }

    public String breed() {
        return _breed;
    }

    @Override
    public Integer aggregate(Integer intermediateResult) {
        if  (intermediateResult == null) {
            return _weight;
        }
        return _weight + intermediateResult;
    }

    @Override
    public Pet deepClone() {
        return new Pet(_weight, _breed);
    }

    @Override
    public String toString() {
        return "Pet " +
                _weight + "kg, " +
                "breed - '" + _breed + '\'';
    }
}
