package gui;

import chat.Emissor;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import transferencia.ReceptorArquivo;
import transferencia.TransmissorArquivo;

/**
 * Janela de bate-papo
 *
 * @author
 */
public class Chat extends javax.swing.JFrame {

    /**
     * Creates new form chat
     *
     * @param emissor
     * @param nome
     */
    public Chat(Emissor emissor, String nome) {
        initComponents();
        this.emissor = emissor;
        this.nomeUserLocal = nome;
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoIP = new javax.swing.JTextField();
        campoPorta = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        campoMsg = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        displayMsg = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        menuEnviarArquivo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("IP");

        jLabel2.setText("Porta");

        campoIP.setText("192.168.0.157");

        campoPorta.setText("12345");

        campoMsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoMsgActionPerformed(evt);
            }
        });

        displayMsg.setEditable(false);
        displayMsg.setColumns(20);
        displayMsg.setRows(5);
        jScrollPane2.setViewportView(displayMsg);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Outros");

        menuEnviarArquivo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        menuEnviarArquivo.setText("Enviar Arquivo");
        menuEnviarArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEnviarArquivoActionPerformed(evt);
            }
        });
        jMenu2.add(menuEnviarArquivo);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoMsg)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campoPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 16, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(campoPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoMsg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoMsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoMsgActionPerformed
        try {
            emissor.comunicar(nomeUserLocal, campoIP.getText().trim(), 
                    campoPorta.getText().trim(), evt.getActionCommand(), this);
            campoMsg.setText("");
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_campoMsgActionPerformed

    private void menuEnviarArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEnviarArquivoActionPerformed
        // inicio selecionar arquivo
        JFileChooser abrir = new JFileChooser();
        int retorno = abrir.showOpenDialog(null);
        
        String msg;
        if (retorno == JFileChooser.APPROVE_OPTION) {
            msg = "FILE " + nomeUserLocal + " : " + abrir.getSelectedFile().getName()
                    + " : " + abrir.getSelectedFile().length() + " Bytes\n";
            caminho = abrir.getSelectedFile().getAbsolutePath();

            System.out.println("caminho - " + abrir.getSelectedFile().getName());
            try {
                emissor.comunicar(nomeUserLocal, campoIP.getText().trim(), 
                        campoPorta.getText().trim(), msg, this);
            } catch (IOException ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }// fim selecionar arquivo

    }//GEN-LAST:event_menuEnviarArquivoActionPerformed
    /**
     * pergunta se quer receber arquivo
     *
     * @param address
     * @param nomeArquivo
     */
    public void receberArquivo(InetAddress address, String nomeArquivo) {
        if (JOptionPane.showConfirmDialog(null, "voce deseja reber o arquivo?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                
                String msg = "OK " + nomeArquivo + " : " + 12349 + "\n";
                
                emissor.comunicar(nomeUserLocal, campoIP.getText().trim(), 
                        campoPorta.getText().trim(), msg, this);
                ReceptorArquivo receptorArquivo
                        = new ReceptorArquivo(12349, address.getHostAddress());

                JFileChooser abrir = new JFileChooser();
                int retorno = abrir.showSaveDialog(null);
                String caminho = new String();
                if (retorno == JFileChooser.APPROVE_OPTION) {
                    caminho = abrir.getSelectedFile().getAbsolutePath();
                    System.out.println("caminho - " + caminho);
                }
                caminho = caminho.concat(nomeArquivo);
                receptorArquivo.receberArquivo(caminho);

            } catch (IOException ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("operacao de envio nao autorizada");
        }
    }

    public void enviarArquivo(String porta) {
        int portaTCP = Integer.parseInt(porta);
        try {
            TransmissorArquivo transmitirArquivo = new TransmissorArquivo(portaTCP);
            transmitirArquivo.iniciarTransferencia(caminho);
        } catch (IOException ex) {
            Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Imprime msg na tela do chat
     *
     * @param msg
     */
    public void imprimirMsg(String msg) {
        displayMsg.append(msg);
        displayMsg.setCaretPosition(
                displayMsg.getText().length());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoIP;
    private javax.swing.JTextField campoMsg;
    private javax.swing.JTextField campoPorta;
    private javax.swing.JTextArea displayMsg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JMenuItem menuEnviarArquivo;
    // End of variables declaration//GEN-END:variables
    private final String nomeUserLocal;
    private final Emissor emissor;
    private String caminho = new String();
}