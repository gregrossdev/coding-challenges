package lexer;

import java.util.ArrayList;
import java.util.List;

public class JsonLexer {
	public List<Token> tokenize(String input) {
		List<Token> tokens = new ArrayList<>();
		int length = input.length();
		for (int i = 0; i < length; i++) {
			char currentChar = input.charAt(i);
			switch (currentChar) {
				case '{':
					tokens.add(new Token(TokenType.LEFT_BRACE, "{"));
					break;
				case '}':
					tokens.add(new Token(TokenType.RIGHT_BRACE, "}"));
					break;
				case '[':
					tokens.add(new Token(TokenType.LEFT_BRACKET, "["));
					break;
				case ']':
					tokens.add(new Token(TokenType.RIGHT_BRACKET, "]"));
					break;
				case ':':
					tokens.add(new Token(TokenType.COLON, ":"));
					break;
				case ',':
					tokens.add(new Token(TokenType.COMMA, ","));
					break;
				case ' ':
				case '\t':
				case '\n':
				case '\r':
					tokens.add(new Token(TokenType.WHITESPACE, String.valueOf(currentChar)));
					break;
				case '"':
					StringBuilder stringBuilder = new StringBuilder();
					i++;
					while (i < length && input.charAt(i) != '"') {
						stringBuilder.append(input.charAt(i));
						i++;
					}
					tokens.add(new Token(TokenType.STRING, stringBuilder.toString()));
					break;
				default:
					if (Character.isDigit(currentChar) || currentChar == '-') {
						StringBuilder numberBuilder = new StringBuilder();
						numberBuilder.append(currentChar);
						i++;
						while (i < length && (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.')) {
							numberBuilder.append(input.charAt(i));
							i++;
						}
						i--;
						tokens.add(new Token(TokenType.NUMBER, numberBuilder.toString()));
					} else if (input.startsWith("true", i)) {
						tokens.add(new Token(TokenType.BOOLEAN, "true"));
						i += 3;
					} else if (input.startsWith("false", i)) {
						tokens.add(new Token(TokenType.BOOLEAN, "false"));
						i += 4;
					} else if (input.startsWith("null", i)) {
						tokens.add(new Token(TokenType.NULL, "null"));
						i += 3;
					} else {
						tokens.add(new Token(TokenType.INVALID, String.valueOf(currentChar)));
					}
					break;
			}
		}
		return tokens;
	}
}