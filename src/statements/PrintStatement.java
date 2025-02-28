package statements;

public class PrintStatement implements Statement {
    String name;

    public PrintStatement(String name) {
        this.name = name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }
}
