import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.Graphics;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import snake.Config;


class Board extends JPanel implements ActionListener,KeyListener{
    Snake snake;
    Food food;
    int marcador;
    boolean gameOver=false;
    public static void main(String arg[]){
        Board b= new Board();
        
    }
    public Board(){
        setPreferredSize(new Dimension(Config.SIZE_WIN_W,Config.SIZE_WIN_H));
        setBackground(Config.COLOR_FONDO);

        JFrame f=new JFrame("snake                  Punticación   "+marcador); 
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.addKeyListener(this);
        f.setSize(Config.SIZE_WIN_W, Config.SIZE_WIN_H);
        f.add(this);
        f.pack();
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.add(this);
        Timer t=new Timer(100,this);
        t.start();
        snake =new Snake();
        food=new Food(Config.SIZE_SEG,Color.RED,new Point(5, 6));
        
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.setColor(snake.getColor());
        ArrayList<Point> body= new ArrayList<Point>();
        body = snake.getBody();
        int HEAD=0;
        int COLA=body.size()-1;
        for(int i=1;i<body.size();i++){
            Point p=body.get(i);
            g.setColor(snake.getColor()); 
            g.fillOval(p.getX()*Config.SIZE_SEG,p.getY()*Config.SIZE_SEG,Config.SIZE_SEG,Config.SIZE_SEG);
        }
        Point c=body.get(COLA);
        g.setColor(snake.getcola()); 
        g.fillOval(c.getX()*Config.SIZE_SEG,c.getY()*Config.SIZE_SEG,Config.SIZE_SEG,Config.SIZE_SEG);
        
        Point p=body.get(HEAD);
        g.setColor(snake.getColorHead()); 
        g.fillOval(p.getX()*Config.SIZE_SEG,p.getY()*Config.SIZE_SEG,Config.SIZE_SEG,Config.SIZE_SEG);
    
        /*for(Point p: snake.getBody()){
            g.fillOval(p.getX()*Config.SIZE_SEG,p.getY()*Config.SIZE_SEG,Config.SIZE_SEG,Config.SIZE_SEG);
        }
        for(int i=0;i<Config.SIZE_WIN_W;i+=Config.SIZE_SEG){
            g.drawLine(i, 0, i, Config.SIZE_WIN_H);
        }
        for(int j=0;j<Config.SIZE_WIN_H;j+=Config.SIZE_SEG){
            g.drawLine(0,j ,Config.SIZE_WIN_W, j);
        }*/
        food.draw(g);
        if(gameOver){
        g.drawString("GAME OVER", Config.SIZE_WIN_H/2, Config.SIZE_WIN_W/3);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Point> body= new ArrayList<Point>();
        body = snake.getBody();
        int HEAD=0;

        for(int i=body.size()-1;i>0;i--){
            int x=body.get(i-1).getX();
            body.get(i).setX(x);
            int y=body.get(i-1).getY();
            body.get(i).setY(y);
        }
        body=snake.getBody();
        int y= body.get(HEAD).getY();
        int x= body.get(HEAD).getX();
        
        if(!gameOver){
        switch(snake.getDir()){
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;    
                break;
            case RIGHT:
                x++;
                break;
        }
        }
        body.get(HEAD).setY(y);
        body.get(HEAD).setX(x);
        if(body.get(HEAD).areTheSame(food.getPoint()) == true ){
            body.add(new Point(food.getPoint().getX(),food.getPoint().getY()));
            food.randomNewFood();
            marcador= marcador+10;
        }
        repaint();
        
        for(int i=1;i<body.size();i++){
            Point p=body.get(i);
                if(p.getX() == body.get(HEAD).getX() && p.getY() == body.get(HEAD).getY()){
                    gameOver= true;
                }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int codeKey = e.getKeyCode();
        switch (codeKey){
            case KeyEvent.VK_UP:
            snake.setDir(Snake.Direction.UP);
            break;
            case KeyEvent.VK_DOWN:
            snake.setDir(Snake.Direction.DOWN);
            break;
            case KeyEvent.VK_LEFT:
            snake.setDir(Snake.Direction.LEFT);
            break;
            case KeyEvent.VK_RIGHT:
            snake.setDir(Snake.Direction.RIGHT);
            break;
        }
       
        System.out.println(snake.getDir());
        
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

}