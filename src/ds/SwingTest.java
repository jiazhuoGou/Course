package ds;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SwingTest extends JFrame {
    private JFrame f = null;
    private JMenuBar menuBar = null;

    public SwingTest() {
        JFrame jf = new JFrame("图论");
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            menuBar = createMenus();
            jf.setJMenuBar(menuBar);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //设置根容器， 并向其添加一个空间
        JPanel root = new JPanel();
        this.setContentPane(root);

        // 画矩形


        JPanel bt = new JPanel();

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setSize(900, 600);
        jf.setVisible(true);

    }

    protected void paintComponent(Graphics g) {

    }


    public JMenuItem createMenuItem(JMenu menu, String label, String mnemonic,
                                    String accessibleDescription, Action action) {
        JMenuItem mi = (JMenuItem) menu.add(new JMenuItem(label));

//		mi.setBorder(BorderFactory.createEmptyBorder());
        mi.setMnemonic(mnemonic.charAt(0));
        mi.getAccessibleContext().setAccessibleDescription(accessibleDescription);
        mi.addActionListener(action);
        if(action == null) {
            mi.setEnabled(false);
        }
        return mi;
    }

    public JMenuBar createMenus() {
        JMenuItem mi;
        // ***** create the menubar ****
        JMenuBar menuBar = new JMenuBar();
        menuBar.getAccessibleContext().setAccessibleName("工具栏");

        // ***** create File menu
        JMenu fileMenu = (JMenu) menuBar.add(new JMenu("算法选择"));


        createMenuItem(fileMenu, "最小生成树", "FileMenu.about_mnemonic", "新建文件",null);
        createMenuItem(fileMenu, "最短路径", "FileMenu.about_mnemonic", "保存文件",null);
        createMenuItem(fileMenu, "最大匹配算法", "FileMenu.about_mnemonic", "删除文件",null);
        fileMenu.addSeparator();

        JMenu editMenu = (JMenu) menuBar.add(new JMenu("编辑"));
        createMenuItem(editMenu, "字体大小", "FileMenu.about_mnemonic", "字体大小",null);
        createMenuItem(editMenu, "颜色", "FileMenu.about_mnemonic", "颜色",null);
        createMenuItem(editMenu, "格式", "FileMenu.about_mnemonic", "格式",null);
        fileMenu.addSeparator();
        editMenu.addSeparator();
        return menuBar;
    }
}
