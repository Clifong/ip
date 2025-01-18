public class ToDos extends Tasks {
    public ToDos(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
