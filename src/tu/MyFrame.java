package tu;

import javafx.scene.control.cell.ProgressBarTreeTableCell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyFrame extends JFrame {

    private final Font font=new Font("宋体", Font.BOLD,15);
    public JLabel verLabel = new JLabel("顶点数", JLabel.CENTER);
    public JLabel edgeLabel = new JLabel("边数", JLabel.CENTER);
    public JTextField verTextField = new JTextField();
    public JTextField edgeTextField = new JTextField();
    public JButton btnStart = new JButton("开始");
    public JTextArea edgeDataArea = new JTextArea();
    public JPanel root = new MyPane();
    public JScrollPane jScrollPane = null;
    public JFrame jFrame = new JFrame("图论");

    public static String strVerNum;
    public static String strEdgeNum;
    public static String strEdgeData;
    public static boolean START = false;

    public MyFrame() {

        jFrame.setSize(1000, 600);

        // 设置工具栏
        jFrame.setJMenuBar(new JMenuDemo());

        // 设置顶点数和边数提示框并获取文本框内容
        verLabel.setBounds(740, 0, 80, 80);
        verLabel.setFont(font);
        verLabel.setVerticalTextPosition(JLabel.TOP);
        edgeLabel.setBounds(830, 0, 80, 80);
        edgeLabel.setFont(font);
        edgeLabel.setVerticalTextPosition(JLabel.TOP);
        verTextField.setBounds(740, 50, 80, 20);
        edgeTextField.setBounds(830, 50, 80, 20);
        verTextField.addActionListener(new ActionListener() { // 设置监听事件获取输入的内容
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("顶点数 : " + e.getActionCommand());   // 从文本框中获取的顶点数字符串
                strVerNum = e.getActionCommand();           // 用全局变量去接
            }
        });
        edgeTextField.addActionListener(new ActionListener() {  // 设置监听事件获取输入的内容
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("边数 : " + e.getActionCommand());   // 从文本框中获取的顶点数字符串
                strEdgeNum = e.getActionCommand();           // 用全局变量去接
            }
        });

        // 设置顶点与边的关系
        edgeDataArea.setLineWrap(true); // 自动换行
        Dimension size = edgeDataArea.getPreferredSize();
        edgeDataArea.setBounds(740,100, 150, 80);
        /*
        edgeDataArea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strEdgeData = e.getActionCommand();
                System.out.println("具体图的关系 : " + strEdgeData);
            }
        });*/

        // 设置开始按钮
        btnStart.setBounds(750, 420, 80, 40);
        btnStart.setFont(font);
        MyMouseListener myMouseListener = new MyMouseListener();
        btnStart.addMouseListener(myMouseListener);

        root.add(verLabel);
        root.add(edgeLabel);
        root.add(verTextField);
        root.add(edgeTextField);
        root.add(btnStart);
        //jScrollPane = new JScrollPane(edgeDataArea); // 文本域
        //root.add(jScrollPane);
        root.add(edgeDataArea);
        root.setLayout(null); // 解除默认布局，这样组件才能设置位置

        // 添加面板
        jFrame.add(root);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    class MyPane extends JPanel{

        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawLine(700, 0, 700, 800);
            g2.drawLine(0, 400, 1000, 400);
            super.paintComponents(g);

        }
    }

    class MyMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(e);
            START = true;
            System.out.println("开始标志 : " + START);
            System.out.println("鼠标获取的顶点 : " + strVerNum);
            System.out.println("鼠标获取的边数 : " + strEdgeNum);
            System.out.println("鼠标获取的图关系 : " + edgeDataArea.getText());
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }


}
