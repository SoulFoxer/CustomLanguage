import statements.PrintStatement;
import statements.Statement;
import statements.VarDeclaration;
import statements.Visitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Interpreter implements Visitor {
    private final Map<String, Integer> variables = new HashMap<>();

    @Override
    public void visit(VarDeclaration stmt) {
        // Speichern der Variablen und ihrem Wert
        variables.put(stmt.getName(), stmt.getValue());
    }

    @Override
    public void visit(PrintStatement stmt) {
        // Ausgabe des Wertes der Variablen, falls sie existiert
        Integer value = variables.get(stmt.getName());
        if (value != null) {
            System.out.println(value);
        } else {
            System.out.println("Variable " + stmt.getName() + " nicht gefunden.");
        }
    }

    // Führt alle Statements aus
    public void interpret(List<Statement> statements) {
        for (Statement stmt : statements) {
            stmt.accept(this);  // Aufruf der passenden Visit-Methode basierend auf dem Typ des Statements
        }
    }
}
