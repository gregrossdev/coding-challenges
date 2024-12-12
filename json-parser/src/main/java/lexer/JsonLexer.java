package lexer;

import java.util.ArrayList;
import java.util.List;

// The lexer will convert the input string into tokens
public class JsonLexer {

	public List<Token> tokenize(String input) {
		List<Token> tokens = new ArrayList<>();
		StringBuilder currentString = new StringBuilder();
		boolean inString = false;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
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
						if (Character.isDigit(c) || c == '-') {
							currentString.append(c);
							while (i + 1 < input.length() && (Character.isDigit(input.charAt(i + 1)) || input.charAt(i + 1) == '.')) {
								currentString.append(input.charAt(++i));
							}
							tokens.add(new Token(TokenType.NUMBER, currentString.toString()));
							currentString.setLength(0);
						} else if (Character.isLetter(c)) {
							currentString.append(c);
							while (i + 1 < input.length() && Character.isLetter(input.charAt(i + 1))) {
								currentString.append(input.charAt(++i));
							}
							String value = currentString.toString();
							if (value.equals("true") || value.equals("false")) {
								tokens.add(new Token(TokenType.BOOLEAN, value));
							} else if (value.equals("null")) {
								tokens.add(new Token(TokenType.NULL, value));
							} else {
								tokens.add(new Token(TokenType.INVALID, value));
							}
							currentString.setLength(0);
						} else {
							tokens.add(new Token(TokenType.INVALID, String.valueOf(c)));
						}
						break;
				}
			}
		}
		return tokens;
	}

}
