package temp.replacement_repo;

import java.io.Serializable;

public class CustomObject implements Serializable {

    private int value;

    private String foo;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }
}
