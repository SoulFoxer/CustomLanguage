package statements;

public class VarDeclaration implements Statement {
    String name;
    int value;

    public VarDeclaration(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}

