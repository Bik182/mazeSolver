
enum Type{
    WALL, HALL, START, FINISH;
}
public class Space {
    private int i, j;
    private Type t;

    Space(int i, int j, Type t){
        this.i = i;
        this.j = j;
        this.t = t;
    }

    public Type getT(){
        return t;
    }

    public void setT(Type t){
        this.t = t;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
