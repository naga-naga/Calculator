public class Parser {
    public String[] tokenize(String str) {
        // 記号の前後にスペースを入れ，連続するスペースを一つにし，分割する
        return str.replaceAll("(\\W)", " $1 ").replaceAll("\\s+", " ").split("\\s");
    }
}
