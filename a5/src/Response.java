public class Response extends Message {

    private final long _fact;
    private Service _service;

    Response(Request request, long fact, Service service) {
        super(request.get_requester(), request.get_priority());
        _fact = fact;
        _service = service;
    }

    public long get_result() {
        return _fact;
    }

    @Override
    public String toString() {
        return super.toString() + _service;
    }
}
