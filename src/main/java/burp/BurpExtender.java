package burp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BurpExtender implements IBurpExtender, IContextMenuFactory {

    private IExtensionHelpers helpers;
    private String PLUGIN_NAME = "Copy2Java";
    private String VERSION = "0.1";

    public void registerExtenderCallbacks(final IBurpExtenderCallbacks callbacks) {
        this.helpers = callbacks.getHelpers();

        PrintWriter stdout = new PrintWriter(callbacks.getStdout(), true);
        String banner = "[+] %s %s is loaded...\n" +
                        "[+] ####################################\n" +
                        "[+]    team:   Acmesec\n" +
                        "[+]    anthor: CoolCat\n" +
                        "[+]    blog:   https://blog.gzsec.org/\n" +
                        "[+]    github: https://github.com/TheKingOfDuck\n" +
                        "[+] ####################################\n" +
                        "[+] Enjoy it~";
        stdout.println(String.format(banner,PLUGIN_NAME,VERSION));

        //注册菜单
        callbacks.registerContextMenuFactory(this);
        callbacks.setExtensionName(PLUGIN_NAME);
    }

    //创建右键菜单
    public List<JMenuItem> createMenuItems(IContextMenuInvocation invocation) {
        List<JMenuItem> menus = new ArrayList();
        JMenuItem Menu = new JMenuItem(PLUGIN_NAME);

        if(invocation.getInvocationContext() != IContextMenuInvocation.CONTEXT_MESSAGE_EDITOR_REQUEST){
            return menus;
        }

        Menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                IHttpRequestResponse reqRsp = invocation.getSelectedMessages()[0];
                byte[] byteReq = reqRsp.getRequest();
                String strReq = new String(byteReq);
                Windows.show(requestMapper.deal(strReq));
            }
        });
        menus.add(Menu);
        return menus;
    }
}
