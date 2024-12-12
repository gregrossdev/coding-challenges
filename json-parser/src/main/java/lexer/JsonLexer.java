package lexer;

import java.util.ArrayList;
import java.util.List;

// The lexer will convert the input string into tokens
public class JsonLexer {

    public List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder currentString = new StringBuilder();
        boolean inString = false;

        for (char c : input.toCharArray()) {
            if (inString) {
                if (c == '"') {
                    inString = false;
                    tokens.add(new Token(TokenType.STRING, currentString.toString()));
                    currentString.setLength(0);
                } else {
                    currentString.append(c);
                }
            } else {
                switch (c) {
                    case '{':
                        tokens.add(new Token(TokenType.LEFT_BRACE, String.valueOf(c)));
                        break;
                    case '}':
                        tokens.add(new Token(TokenType.RIGHT_BRACE, String.valueOf(c)));
                        break;
                    case ':':
                        tokens.add(new Token(TokenType.COLON, String.valueOf(c)));
                        break;
                    case ',':
                        tokens.add(new Token(TokenType.COMMA, String.valueOf(c)));
                        break;
                    case '"':
                        inString = true;
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
        }
        return tokens;
    }

}
