package statements;

import java.util.List;

public class FunctionDeclarationStatement implements Statement {
    String name;
    List<String> parameters;
    List<Statement> body;

    public FunctionDeclarationStatement(String name, List<String> parameters, List<Statement> body) {
        this.name = name;
        this.parameters = parameters;
        this.body = body;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public List<Statement> getBody() {
        return body;
    }
}
