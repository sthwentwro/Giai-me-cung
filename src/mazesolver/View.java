package mazesolver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

public class View extends JPanel{
    Font font = new Font("Arial", Font.BOLD, 14);
    Icon iconplay = new ImageIcon(getClass().getResource("/res/play-button.png"));
    Icon iconreset = new ImageIcon(getClass().getResource("/res/Reset.png"));
    JLabel label1 = new JLabel("Chọn thuật toán tìm đường:");
    JButton btplay = new JButton(iconplay);
    JButton btreset = new JButton(iconreset);
    JRadioButton RabtDFS = new JRadioButton("DFS",true);
    JRadioButton RabtBFS = new JRadioButton("BFS");
    ButtonGroup Gr = new ButtonGroup();
    File imageFile = new File("src\\res\\Character.PNG");
    Image image;
    Timer timer;
    
/**
 * 
 * dùng không gian 2 chiều để đại
 * diện mê cung
 * 
 * 
 * maze[hàng][cột]
 * 
 * giá trị: 0 = node chưa đến
 *          1 = tường 
 *          2 = node đã đến
 *          9 = đích
 *          4 = bắt đầu
 */
// điểm bắt đầu mặt định tại (5,0)
// điểm kết thúc mặt định tại (12,19)
/*    Y
      - - - - - >
    X|
     |
     |
     v
    */
private final int [][] maze2D = 
    {   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,1,0,0,1,1,1,1,1,0,0,0,1,0,1,0,1,1,1,1},
        {1,1,0,1,0,0,0,1,0,1,1,0,0,0,1,0,0,1,1,1},
        {1,1,0,1,0,1,0,0,0,1,0,0,1,0,1,0,0,1,0,1},
        {1,1,0,1,0,1,0,1,0,1,0,1,0,0,1,0,0,0,0,1},
        {4,0,0,0,0,1,0,0,1,1,0,1,0,1,1,0,1,1,0,1},
        {1,1,0,1,0,1,0,1,0,0,0,1,0,0,0,0,1,0,0,1},
        {1,1,0,0,0,1,0,1,1,0,1,0,0,1,1,1,0,1,0,1},
        {1,0,0,1,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,1},
        {1,0,1,1,1,1,0,1,1,1,1,0,1,0,1,1,0,1,0,1},
        {1,0,0,0,1,0,0,1,0,1,1,0,1,0,0,1,0,0,0,1},
        {1,1,1,0,0,0,1,0,1,0,1,0,1,0,1,0,0,1,0,1},
        {1,0,0,0,1,0,1,0,1,0,1,0,0,0,0,1,0,0,0,9},
        {1,0,1,1,1,0,1,0,0,1,1,1,1,1,0,1,0,1,0,1},
        {1,0,0,0,0,1,1,0,1,0,0,0,0,0,0,1,0,1,1,1},
        {1,0,1,1,1,0,0,0,1,1,1,1,1,0,1,0,0,0,1,1},
        {1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,1,1,1},
        {1,1,0,1,0,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1},
        {1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},        
    };
    Maze mecung;
    int rows = maze2D.length;//số hàng của mê cung
    int columns = maze2D[0].length;//số cột của mê cung
    public View(int width,int height){
        setLayout(null);
        setPreferredSize(new Dimension(width, height));
        mecung = new Maze(maze2D);
        label1.setFont(font);
        RabtBFS.setFont(font);
        RabtDFS.setFont(font);
        btplay.setBounds(690, 220, 60, 40);
        btreset.setBounds(690, 300, 60, 40);
        label1.setBounds(630, 100, 250, 40);
        RabtBFS.setBounds(740, 160, 70, 40);
        RabtDFS.setBounds(650, 160, 70, 40);
        add(label1);
        add(RabtBFS);
        add(RabtDFS);
        add(btplay);
        add(btreset);
        Gr.add(RabtDFS);
        Gr.add(RabtBFS);
        btplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //nếu DFS được chọn thì chạy thuật toán DFS
                if(RabtDFS.isSelected()){
                    Depth_First_Search s1 = new Depth_First_Search();
                    mecung.duongdi = s1.solve(mecung);
                    if(!mecung.duongdi.isEmpty()){
                       veduongdi(mecung.duongdi);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Không tìm thấy đường đi");
                    }  
                }
                else{
                    Breadth_First_Search s2 = new Breadth_First_Search();
                    mecung.duongdi = s2.solve(mecung);
                    if(!mecung.duongdi.isEmpty()){
                       veduongdi(mecung.duongdi);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Không tìm thấy đường đi");
                    }  
                }
                mecung.reset();
            }
        }); 
        btreset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(!mecung.duongdi.isEmpty()){
                    pathX = mecung.start.getX();
                    pathY = mecung.start.getY();
                    mecung.duongdi.clear();
                    repaint();
                }
            }
        });
        try{
           image = ImageIO.read(imageFile);
        }
        catch(IOException e){
        }
        //thêm sự kiện mouse
        MouseHandler listener = new MouseHandler();
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }
    //vị trí mặt định của nhân vật
    int pathX = 5;
    int pathY = 0;
    boolean timthayduongdi = false;//khi chưa tìm thấy đường đi thì cho di chuyển điểm bắt đầu
    private void veduongdi(List<Toado> path){
        btreset.setEnabled(false);
        timthayduongdi = true;
        timer = new Timer(180,new ActionListener() {
        int p=0;
        public void actionPerformed(ActionEvent arg0) {
                    pathX = path.get(p).getX();
                    pathY = path.get(p).getY();
                    repaint();
                    p++;
                    if(p>=path.size()){
                        timer.stop();
                        timthayduongdi = false;
                        btreset.setEnabled(true);
                    }
        }
        });
        timer.start();
    }
    
     /**Một lớp xử lý các chuyển động của chuột khi chúng ta "vẽ" * chướng ngại vật 
     hoặc di chuyển robot và / hoặc mục tiêu. **/
    public class MouseHandler implements MouseMotionListener,MouseListener{
        public int cur_row, cur_col, cur_val;

            @Override
        public void mousePressed(MouseEvent evt) {
            int row = evt.getY()/ 30;
            int col = evt.getX() / 30;
            if (row >= 0 && row < rows && col >= 0 && col < columns) {
                cur_row = row;
                cur_col = col;
                cur_val = maze2D[row][col];
            }
        }
        // Được triệu hồi khi một nút chuột được nhấn trên một thành phần và sau đó được kéo (drag).
        public void mouseDragged(MouseEvent evt) {
            int row = evt.getY() / 30;
            int col = evt.getX() / 30;
                if (row >= 0 && row < rows && col >= 0 && col < columns && timthayduongdi==false){
                    if(maze2D[row][col] != 1){
                    //kiểm tra xem có di chuyển điểm bắt đầu hay điểm kết thúc
                    if ((row*columns+col != cur_row*columns+cur_col) && (cur_val == 4 || cur_val == 9)){
                        int new_val = maze2D[row][col];
                            maze2D[row][col] = cur_val;
                            //nếu là điểm bắt đầu thì set lại tọa độ vừa mới chuyển chuột
                            //còn không là set lại giá trị điểm kết thúc
                            if (cur_val == 4) {
                                Maze.start.setX(row);
                                Maze.start.setY(col);
                                pathX = row;
                                pathY = col;
                            } 
                            else {
                                Maze.end.setX(row);
                                Maze.end.setY(col);
                                pathX = Maze.start.getX();
                                pathY = Maze.start.getY();
                            }
                            maze2D[cur_row][cur_col] = new_val;
                            cur_row = row;
                            cur_col = col;
                            if (cur_val == 4) {
                               Maze.start.setX(cur_row);
                               Maze.start.setY(cur_col);
                            } 
                            else {
                               Maze.end.setX(cur_row);
                               Maze.end.setY(cur_col);
                            }
                            cur_val = maze2D[row][col];
                        }
                    repaint();
                }
            }
        }
        @Override
        public void mouseMoved(MouseEvent evt) { }

        @Override
        public void mouseClicked(MouseEvent arg0) {}

        @Override
        public void mouseReleased(MouseEvent arg0) {}

        @Override
        public void mouseEntered(MouseEvent arg0) {}

        @Override
        public void mouseExited(MouseEvent arg0) {}
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // vẽ mê cung
        for(int row=0; row < rows; row++){
            for(int col=0; col < columns; col++){
                Color color;
                switch(maze2D[row][col]){
                    case 1:{ //là tường
                        color = Color.darkGray;
                        break;
                    }
                    case 4:{
                        color = Color.green;
                        break;
                    }
                    case 9:{
                        color = Color.RED;
                        break;
                    }
                    default:{
                        color = Color.WHITE;
                    }
                }
                g.setColor(color);
                g.fillRect(30*col, 30*row, 30, 30);
            }
        }
        //vẽ nhân vật
        g.drawImage(image,30*pathY,30*pathX-5, 45, 40, this);
    }  
}
