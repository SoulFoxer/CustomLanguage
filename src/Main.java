import statements.Statement;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String code = "var x = 42; print x;";

        Lexer lexer = new Lexer(code);
        List<Token> tokens = lexer.tokenize();

        Parser parser = new Parser(tokens);
        List<Statement> statements = parser.parse();

        Interpreter interpreter = new Interpreter();
        interpreter.interpret(statements);
    }
}
