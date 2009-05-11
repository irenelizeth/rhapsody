/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DownloadingTest.java
 *
 * Created on 23-ene-2009, 14:33:40
 */

package launch;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import net.jxta.exception.PeerGroupException;
import net.jxta.peergroup.PeerGroup;
import net.jxta.platform.NetworkConfigurator;
import net.jxta.platform.NetworkManager;
import org.u2u.filesharing.U2UContentAdvertisementImpl;
import org.u2u.filesharing.U2UFileSharingService;
import org.u2u.filesharing.downloadpeer.U2UDownloadingManager;
import org.u2u.filesharing.downloadpeer.U2URequestManagerImpl;

/**
 *
 * @author sergio
 */
public class DownloadingTest extends javax.swing.JFrame {
    private NetworkManager manager;
    private PeerGroup group;
    private U2URequestManagerImpl rm;
    private U2UFileSharingService fss;
    private U2UDownloadingManager dm;
    private U2UListModel model;

    /** Creates new form DownloadingTest
     * @throws IOException
     * @throws PeerGroupException
     */
    public DownloadingTest() throws IOException, PeerGroupException {
        initComponents();

        model = new U2UListModel(jL_advs);
        jL_advs.setModel(model);

        manager = new NetworkManager(NetworkManager.ConfigMode.EDGE, JOptionPane.showInputDialog("Ingrese el nick name del igual"));

        if(!(new File(".jxta")).exists())
        {
            NetworkConfigurator conf = manager.getConfigurator();

            //conf.setTcpInterfaceAddress("192.168.0.3");

            /*Iterator it = IPUtils.getAllLocalAddresses();
            Vector<String> vector = new Vector<String>();*/
            String localHost = JOptionPane.showInputDialog("Ingrese la ip del equipo sin tcp://");

            /*while(it.hasNext())
            {
                String st = it.next().toString().substring(1);
                if(st.equals(localHost))
                    vector.add(0, st);
                else
                    vector.add(st);
            }

            for(int i = 0; i < vector.size(); i++)
            {
                System.out.println(vector.get(i));
            }*/

            conf.setTcpInterfaceAddress(localHost);

            //conf.setTcpPort(2526);
            conf.setUseMulticast(false);

            Set<String> set = new HashSet<String>();
            //set.add("tcp://u2u.homeunix.net:9701");
            //192.168.1.122 UIS BIBLIOTK
            //set.add("tcp://192.168.1.122:8080");
            //set.add("tcp://190.240.10.222:9701");//rdv/relay eMacSergio
            //set.add("tcp://"+localHost+":8080");
            //set.add("tcp://192.168.109.78:8080");
            set.add("tcp://"+JOptionPane.showInputDialog("Ingrese la direccion del rendezvous eg 192.168.109.78:8080"));
            //conf.setRelaySeedURIs(new ArrayList<String>(set));
            conf.setRendezvousSeeds(set);
        }

        //starting JXTA
        group = manager.startNetwork();

        manager.waitForRendezvousConnection(10000);

        fss = new U2UFileSharingService(group, false, false);

        rm = new U2URequestManagerImpl(fss);
        rm.addSearchListener(model);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTA_info = new javax.swing.JTextArea();
        jTF_search = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jB_search = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jL_advs = new javax.swing.JList();
        jB_download = new javax.swing.JButton();
        jPB_progress = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("Info");

        jTA_info.setColumns(20);
        jTA_info.setRows(5);
        jScrollPane1.setViewportView(jTA_info);

        jLabel2.setText("Nombre archivo");

        jB_search.setText("S");
        jB_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_searchActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jL_advs);

        jB_download.setText("D");
        jB_download.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_downloadActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(34, 34, 34)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTF_search, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 181, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jB_search, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 258, Short.MAX_VALUE)
                        .add(jB_download, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))
                .add(34, 34, 34))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .add(25, 25, 25)
                .add(jPB_progress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(207, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTF_search, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2)
                    .add(jB_search))
                .add(18, 18, 18)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 109, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(26, 26, 26)
                        .add(jLabel1))
                    .add(layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jB_download)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jPB_progress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jB_searchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jB_searchActionPerformed
    {//GEN-HEADEREND:event_jB_searchActionPerformed

        String name = jTF_search.getText();
        rm.searchContent("name", name);
        jTA_info.append("\nSearch query send");
}//GEN-LAST:event_jB_searchActionPerformed

    private void jB_downloadActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jB_downloadActionPerformed
    {//GEN-HEADEREND:event_jB_downloadActionPerformed
        // TODO add your handling code here:
        int index = jL_advs.getSelectedIndex();

        if(index>=0)
        {
            if(dm == null)
            {
                U2UContentAdvertisementImpl adv = model.list.get(index);
                dm = new U2UDownloadingManager(fss, adv);
                (new Thread(dm, dm.getDownloadingID())).start();
            }
        }
        

}//GEN-LAST:event_jB_downloadActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosed
    {//GEN-HEADEREND:event_formWindowClosed
        // TODO add your handling code here:
        Runtime.getRuntime().gc();
    }//GEN-LAST:event_formWindowClosed


    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new DownloadingTest().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(DownloadingTest.class.getName()).log(Level.SEVERE, null, ex);
                } catch (PeerGroupException ex) {
                    Logger.getLogger(DownloadingTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jB_download;
    private javax.swing.JButton jB_search;
    private javax.swing.JList jL_advs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jPB_progress;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTA_info;
    private javax.swing.JTextField jTF_search;
    // End of variables declaration//GEN-END:variables

}