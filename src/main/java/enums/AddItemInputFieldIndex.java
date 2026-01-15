package enums;

public enum AddItemInputFieldIndex {
    FIRST_FIELD(0),
    SECOND_FIELD(1),
    THIRD_FIELD(2);

    private final int index;

    AddItemInputFieldIndex(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }
}
