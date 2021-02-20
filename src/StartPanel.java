import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * 开始界面绘制类
 */
public class StartPanel extends JPanel implements Runnable{

    public static final int LOAD_RESOURCES = 0;
    public static final int START_GUI = 1;

    public static final int BUTTON_NORMAL = 0;
    public static final int BUTTON_MOVE = 1;
    public static final int BUTTON_DOWN =2;


    int state;
    int progress;
    int buttonState;
    StartPanel(){
        this.state = LOAD_RESOURCES;
        this.progress = 0;
        this.buttonState = BUTTON_NORMAL;
        this.setBackground(Color.gray);
        this.addMouseListener(new MouseAdapter() {


            /**
             * {@inheritDoc}
             * @param e
             */
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getX() >= 506 && e.getX() <= 756 && e.getY() >= 306 && e.getY() <= 466)
                    buttonState = BUTTON_DOWN;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                buttonState = BUTTON_NORMAL;
                if (e.getX() >= 506 && e.getX() <= 756 && e.getY() >= 306 && e.getY() <= 466){
                    //此处为点击了开始按钮后的操作
                    startGame();
                    JOptionPane.showMessageDialog(null,"开发中....");
                }

            }

        });
    }

    /**
     *此方法为开始游戏并且初始化游戏数据的函数
     */
    void startGame(){
        HallPanel hallPanel = new HallPanel();
        Thread hallThread = new Thread(hallPanel);
        hallThread.start();
        JFrame hallFrame = new JFrame("游戏大厅");
        hallFrame.add(hallPanel);
        hallFrame.setSize(1280+8+8,720+30+8);//上边框30其他边框8
        hallFrame.setLocationRelativeTo(null);
        hallFrame.setResizable(false);
        hallFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hallFrame.setVisible(true);
    }

    /**
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (true){
            this.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        switch (this.state){
            case LOAD_RESOURCES:
                g.setColor(Color.white);
                g.drawRect(137,312,1000+6,50+6);
                g.fillRect(140,315,progress*10,50);
                g.setFont(new Font("微软雅黑",Font.BOLD,32));
                g.drawString("Loading...",550,410);
                break;
            case START_GUI:
                g.drawImage(Resources.START_GUI_BACKGROUND,0,0,this);
                switch (this.buttonState){
                    case BUTTON_NORMAL:
                        g.drawImage(Resources.START_GUI_BUTTON_NORMAL,506,306,this);
                        break;
                    case BUTTON_MOVE:
                        g.drawImage(Resources.START_GUI_BUTTON_MOVE,506,306,this);
                        break;
                    case BUTTON_DOWN:
                        g.drawImage(Resources.START_GUI_BUTTON_DOWN,506,306,this);
                        break;
                }
                break;
        }
    }
}
