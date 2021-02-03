import javax.swing.*;

public class main {
    static final String GAME_NAME = "游戏名称暂定";
    static final int LOAD_DELAY = 500;
    public static void main(String[] args) {
        startGui();
    }


    /**
     * 该函数为加载游戏所需要的资源
     */
    public static void loadResources(StartPanel startPanel){
        new Thread(() -> {
            try {
                startPanel.progress = 0;
                Resources.START_GUI_BACKGROUND = new ImageIcon(main.class.getResource("image/gui/background.png")).getImage();
                Thread.sleep(LOAD_DELAY);
                startPanel.progress = 25;
                Resources.START_GUI_BUTTON_MOVE = new ImageIcon(main.class.getResource("image/gui/button_mouse_move.png")).getImage();
                Thread.sleep(LOAD_DELAY);
                startPanel.progress = 50;
                Resources.START_GUI_BUTTON_NORMAL = new ImageIcon(main.class.getResource("image/gui/button.png")).getImage();
                Thread.sleep(LOAD_DELAY);
                startPanel.progress = 75;
                Resources.START_GUI_BUTTON_DOWN = new ImageIcon(main.class.getResource("image/gui/button_mouse_down.png")).getImage();
                Thread.sleep(LOAD_DELAY);
                startPanel.progress = 100;
                startPanel.state = StartPanel.START_GUI;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }



    /**
     * 游戏启动时的函数
     * 用来创建开始界面
     */
    public static void startGui(){
        JFrame startFrame = new JFrame(GAME_NAME);
        StartPanel startPanel= new StartPanel();
        Thread startPanelThread = new Thread(startPanel);
        startPanelThread.start();
        startFrame.add(startPanel);
        startFrame.setSize(1280+8+8,720+30+8);//上边框30其他边框8
        startFrame.setLocationRelativeTo(null);
        startFrame.setResizable(false);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setVisible(true);

        loadResources(startPanel);
    }
}
