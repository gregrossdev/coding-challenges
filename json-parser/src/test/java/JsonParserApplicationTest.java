import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserApplicationTest {

	@Test
	public void testValidJson() throws IOException {
		JsonLexer lexer = new JsonLexer();
		JsonParser parser = new JsonParser();

		String content = new String(Files.readAllBytes(Paths.get("src/main/resources/tests/step1/valid.json")));
		List<JsonLexer.Token> tokens = lexer.tokenize(content);

		assertTrue(parser.parse(tokens), "The JSON should be valid");
	}

	@Test
	public void testInvalidJson() throws IOException {
		JsonLexer lexer = new JsonLexer();
		JsonParser parser = new JsonParser();

		String content = new String(Files.readAllBytes(Paths.get("src/main/resources/tests/step1/invalid.json")));
		List<JsonLexer.Token> tokens = lexer.tokenize(content);

		assertFalse(parser.parse(tokens), "The JSON should be invalid");
	}
}