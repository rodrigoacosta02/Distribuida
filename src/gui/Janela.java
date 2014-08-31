package gui;

import Cliente.Cliente;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class Janela extends javax.swing.JFrame {

    /**
     * Creates new form Janela
     *
     * @param cliente
     */
    public Janela(Cliente cliente) {
        initComponents();
        this.cliente = cliente;
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public Janela() {
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        enter = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        windowCliente = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        windowServer = new javax.swing.JTextArea();
        jTextFieldServer = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterActionPerformed(evt);
            }
        });

        windowCliente.setEditable(false);
        windowCliente.setColumns(20);
        windowCliente.setRows(5);
        jScrollPane1.setViewportView(windowCliente);

        windowServer.setEditable(false);
        windowServer.setColumns(20);
        windowServer.setRows(5);
        jScrollPane2.setViewportView(windowServer);

        jTextFieldServer.setEditable(false);
        jTextFieldServer.setText("Online e server");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Nova Conversa");

        jMenuItem1.setText("Nova");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                    .addComponent(jTextFieldServer))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(enter)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(enter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldServer))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * envia msg de usuario
     *
     * @param ae
     */
    private void enterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterActionPerformed
        cliente.envio(evt);
        enter.setText("");
    }//GEN-LAST:event_enterActionPerformed
    /**
     * abre nova janela para bate-papo
     * @param evt 
     */
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Chat chat = new Chat();
        chat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * recebe msg e mostra na tela correspondente a server ou usuario
     *
     * @param msg msg a ser impressa
     * @param imprimirCliente true imprime janela cliente false imprime janela
     * server
     */
    public void recebimentoPacotes(String msg, boolean imprimirCliente) {
        if (imprimirCliente) {
            windowCliente.append(msg);
            windowCliente.setCaretPosition(
                    windowCliente.getText().length());
        } else {
            windowServer.append(msg);
            windowServer.setCaretPosition(
                    windowServer.getText().length());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField enter;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldServer;
    private javax.swing.JTextArea windowCliente;
    private javax.swing.JTextArea windowServer;
    // End of variables declaration//GEN-END:variables
    private Cliente cliente;
}
