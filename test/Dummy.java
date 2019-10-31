import java.util.Objects;

public class Dummy implements Comparable {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Dummy(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        // if (this == o) return true;
        // if (o == null || getClass() != o.getClass()) return false;
        Dummy dummy = (Dummy) o;
        return Objects.equals(value, dummy.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Dummy{" +
                "value='" + value + '\'' +
                '}';
    }
}
