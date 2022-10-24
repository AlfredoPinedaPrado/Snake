import java.util.ArrayList;
import java.awt.Color;


class Snake{
    private Color color;
    private Color colorHead;
    private Color cola;
    int Marcador;
    private ArrayList<Point>body=new ArrayList<Point>();
    private int speed;
    enum Direction { UP, DOWN, LEFT, RIGHT };
    private Direction dir;

    public Snake(){
        body.add(new Point(5,2));
        body.add(new Point(4,2));
        body.add(new Point(3,2));
        color=Color.WHITE;
        cola=Color.BLACK;
        colorHead=Color.ORANGE;
        speed=1;
        dir = Direction.DOWN;
    }
    public ArrayList<Point> getBody(){
        return body;
    }
    public int getSpeed(){
        return speed;
    }
    public Color getColor(){
        return color;
    }
    public void setDir(Direction dir) {
        this.dir = dir;
    }
    public Direction getDir() {
        return dir;
    }
    public Color getColorHead() {
        return colorHead;
    }
    public Color getcola() {
        return cola;
    }
    public void setMarcador(){
        this.Marcador=Marcador;
    }
    public int getMarcador(){
        return Marcador;
    }
}