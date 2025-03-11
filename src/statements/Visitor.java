package statements;

public interface Visitor {
    void visit(VarDeclaration stmt);
    void visit(PrintStatement stmt);
    void visit(FunctionDeclarationStatement stmt);
}

