import java.util.List;

public class JsonParser {
    public boolean parse(List<JsonLexer.Token> tokens) {
        int braceCount = 0;
        for (JsonLexer.Token token : tokens) {
            switch (token.type) {
                case LEFT_BRACE:
                    braceCount++;
                    break;
                case RIGHT_BRACE:
                    braceCount--;
                    break;
                case INVALID:
                    return false;
                default:
                    break;
            }
        }
        return braceCount == 0;
    }
}