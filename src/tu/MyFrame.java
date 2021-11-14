package tu;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class MyFrame extends JFrame {
    public MyFrame() {
        JFrame jFrame = new JFrame("图论");
        jFrame.setSize(1000, 600);
        JPanel root = new JPanel();

        // 设置工具栏
        jFrame.setJMenuBar(new JMenuDemo());

        // 设置顶点数和边数提示框
        JLabel verLabel = new JLabel("顶点数");
        JLabel edgeLabel = new JLabel("边数");
        verLabel.setBounds(800, 100, 50, 50);
        edgeLabel.setBounds(910, 100, 50, 50);
        root.add(verLabel);
        root.add(edgeLabel);
        root.setLayout(null); // 解除默认布局，这样组件才能设置位置

        //paint();



        // 添加面板
        jFrame.add(root);
        //jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        Line2D lin = new Line2D.Float(50, 50, 50, 600);
        g2.draw(lin);
    }
}
