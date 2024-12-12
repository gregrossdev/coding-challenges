import java.util.ArrayList;
import java.util.List;

// The lexer will convert the input string into tokens
public class JsonLexer {
    public enum TokenType {
        LEFT_BRACE, RIGHT_BRACE, WHITESPACE, INVALID
    }

    public static class Token {
        public final TokenType type;
        public final String value;

        public Token(TokenType type, String value) {
            this.type = type;
            this.value = value;
        }
    }

    public List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        for (char c : input.toCharArray()) {
            switch (c) {
                case '{':
                    tokens.add(new Token(TokenType.LEFT_BRACE, String.valueOf(c)));
                    break;
                case '}':
                    tokens.add(new Token(TokenType.RIGHT_BRACE, String.valueOf(c)));
                    break;
                case ' ':
                case '\n':
                case '\r':
                case '\t':
                    tokens.add(new Token(TokenType.WHITESPACE, String.valueOf(c)));
                    break;
                default:
                    tokens.add(new Token(TokenType.INVALID, String.valueOf(c)));
                    break;
            }
        }
        return tokens;
    }
}
