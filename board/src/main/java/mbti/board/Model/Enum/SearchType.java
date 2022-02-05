package mbti.board.Model.Enum;

public enum SearchType {
    TITLE( "TITLE"), CONTENT("CONTENT"), AUTHOR("AUTHOR");

    final private String stringType;

    SearchType(String stringType){
        this.stringType = stringType;
    }

    public String getStringType(){
        return this.stringType;
    }
}
