package statements;

public interface Statement {
    void accept(Visitor visitor);
}