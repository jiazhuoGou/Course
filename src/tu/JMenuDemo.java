package tu;

import java.awt.event.*;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.*;

public class JMenuDemo extends JMenuBar {

    public JMenuDemo()
    {
        add(createAlgoMenu());    //添加“算法”菜单
        setVisible(true);
    }


    //定义“算法”菜单
    public JMenu createAlgoMenu()
    {
        JMenu menu=new JMenu("算法");
        menu.setMnemonic(KeyEvent.VK_F);    //设置快速访问符

        JMenuItem item1=new JMenuItem("最小生成树(N)",KeyEvent.VK_N);
        item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        menu.add(item1);

        JMenuItem item2=new JMenuItem("最短路径(O)",KeyEvent.VK_O);
        item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        menu.add(item2);

        JMenuItem item3=new JMenuItem("最大匹配(S)",KeyEvent.VK_S);
        item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        menu.add(item3);
        menu.addSeparator();

        // 添加响应事件
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("菜单栏 " + e.getActionCommand());
                MyFrame.algoType = 1;
            }
        });

        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("菜单栏 " + e.getActionCommand());
                MyFrame.algoType = 2;
            }
        });

        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("菜单栏 " + e.getActionCommand());
                MyFrame.algoType = 3;
            }
        });
        return menu;
    }
}
