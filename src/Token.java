public class Token {
    /**
     * トークンのタイプ
     */
    private TokenType tokenType;

    /**
     * トークンの文字列表現
     */
    private String text;

    public Token(TokenType tokenType, String text) {
        this.tokenType = tokenType;
        this.text = text;
    }

    public TokenType getTokenType() {
        return this.tokenType;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public String toString() {
        return "'" + this.text + "' : " + this.tokenType;
    }
}
