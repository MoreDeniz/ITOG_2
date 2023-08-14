package itog.services;

public class PetsCounter implements AutoCloseable {
    private static Integer value = 0;

    private boolean dataClosed = false;

    public void add() throws IllegalStateException {
        if (dataClosed) {
            throw new IllegalStateException("Счётчик закрыт");
        } else {
            this.value+=1;
        }
    }

    public Integer getCount() {
        return value;
    }

    @Override
    public void close() {
        dataClosed = true;
    }
}
