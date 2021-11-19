package tu;

import javafx.scene.control.cell.ProgressBarTreeTableCell;
import jdk.internal.org.objectweb.asm.Handle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MyFrame extends JFrame {

    private final Font font=new Font("宋体", Font.BOLD,15);
    public JLabel verLabel = new JLabel("顶点数", JLabel.CENTER);
    public JLabel edgeLabel = new JLabel("边数", JLabel.CENTER);
    public JLabel vertexsLabel = new JLabel("顶点集合", JLabel.CENTER);
    public JLabel edgeDataLabel = new JLabel("关系集合", JLabel.CENTER);
    public JLabel resultLabel = new JLabel("结果", JLabel.CENTER);
    public JLabel srcLabel = new JLabel("最短路径源点", JLabel.CENTER);
    public static JTextArea resultArea = new JTextArea();
    public JTextField vertexsTextFiled = new JTextField();
    public JTextField verTextField = new JTextField();
    public JTextField edgeTextField = new JTextField();
    public JTextField srcTextField = new JTextField();
    public JButton btnStart = new JButton("开始");
    public JTextArea edgeDataArea = new JTextArea();
    public JPanel root = new MyPane();
    public JFrame jFrame = new JFrame("图论");

    public static volatile String strVerNum;        // 点数
    public static volatile String strEdgeNum;       // 边数
    public static volatile String strVertexs;           // 顶点集合
    public static volatile String strEdgeData;      // 关系集合
    public static volatile boolean START = false;   // 开始标志
    public static volatile int algoType;            // 算法标志，1最小生成树,2最短路径,3最大匹配
    public static volatile String strSrc;           // 最短路径源点


    public MyFrame() {

        jFrame.setSize(1000, 520);

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
                strVerNum = new String(e.getActionCommand());
            }
        });
        edgeTextField.addActionListener(new ActionListener() {  // 设置监听事件获取输入的内容
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("边数 : " + e.getActionCommand());   // 从文本框中获取的顶点数字符串
                strEdgeNum = new String(e.getActionCommand());           // 用全局变量去接
            }
        });

        // 设置顶点集合
        vertexsLabel.setFont(font);
        vertexsLabel.setBounds(790, 70, 80, 80);
        vertexsLabel.setVerticalTextPosition(JLabel.TOP);
        vertexsTextFiled.setBounds(740, 120, 170, 20);
        vertexsTextFiled.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strVertexs = new String(e.getActionCommand());
                System.out.println("顶点集合 : " + strVertexs);
            }
        });

        // 设置顶点与边的关系
        edgeDataLabel.setFont(font);
        edgeDataLabel.setVerticalTextPosition(JLabel.TOP);
        edgeDataLabel.setBounds(790, 140, 80, 80);
        edgeDataArea.setLineWrap(true); // 自动换行
        Dimension size = edgeDataArea.getPreferredSize();
        edgeDataArea.setBounds(740,190, 170, 80);
        edgeDataArea.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {
                System.out.println("文本域的变化 " + event.getText());}
            @Override
            public void caretPositionChanged(InputMethodEvent event) {}
        });
        JScrollPane jsp = new JScrollPane(edgeDataArea);
        jsp.setBounds(740, 190, 170, 80);

        // 设置最短路径源点
        srcLabel.setFont(font);
        srcLabel.setVerticalTextPosition(JLabel.TOP);
        srcLabel.setBounds(744, 250, 170, 80);
        srcTextField.setBounds(740, 305, 170, 20);
        srcTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strSrc = new String(e.getActionCommand());
                System.out.println("最短路径源点 : " + strSrc);
            }
        });

        // 设置结果展示
        resultLabel.setFont(font);
        resultLabel.setVerticalTextPosition(JLabel.TOP);
        resultLabel.setBounds(70, 38, 60, 60);
        resultArea.setBounds(50, 50, 300, 150);
        resultArea.setLineWrap(true);   // 自动换行
        JScrollPane jsp2=new JScrollPane(resultArea);
        //jsp.setLayout(null);
        jsp2.setBounds(80,80,320,180); // 这个滑动框要有多行才能实现

        // 设置开始按钮
        btnStart.setBounds(780, 350, 80, 40);
        btnStart.setFont(font);
        MyMouseListener myMouseListener = new MyMouseListener();
        btnStart.addMouseListener(myMouseListener);

        root.add(srcLabel);
        root.add(srcTextField);
        root.add(resultLabel);
        root.add(jsp2);
        root.add(jsp);
        //root.add(resultArea);
        root.add(verLabel);
        root.add(edgeLabel);
        root.add(edgeDataLabel);
        root.add(verTextField);
        root.add(edgeTextField);
        root.add(vertexsLabel);
        root.add(vertexsTextFiled);
        root.add(btnStart);
        //root.add(edgeDataArea);
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
            //g2.drawLine(0, 400, 1000, 400);
            super.paintComponents(g);
        }
    }

    class MyMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(e);
            START = true;
            Main.mainOk = true;
            resultArea.setText("");
            System.out.println("开始标志 : " + Main.mainOk);
            System.out.println("鼠标获取的顶点 : " + strVerNum);
            System.out.println("鼠标获取的边数 : " + strEdgeNum);
            System.out.println("鼠标获取的顶点 : " + strVertexs);
            System.out.println("鼠标获取的图关系 : " + edgeDataArea.getText());
            System.out.println("鼠标获取的图关系 : " + srcTextField.getText());
            strEdgeData = new String(edgeDataArea.getText());
        }
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }

    // 设置结果文本域的内容
    public static void setResultArea(String res)
    {
        resultArea.setText(res);
    }
}
