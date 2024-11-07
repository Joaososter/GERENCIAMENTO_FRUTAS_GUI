// Importando bibliotecas necessárias para a interface gráfica e ações
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Classe principal para o gerenciador de frutas
public class FrutaManagerGUI {
    // Atributos: lista de frutas e componentes de interface
    private ArrayList<String> frutas; // Armazena as frutas
    private DefaultListModel<String> listModel; // Modelo para exibir as frutas na JList
    private JList<String> list; // Lista visual para exibir as frutas

    // Construtor da interface gráfica
    public FrutaManagerGUI() {
        frutas = new ArrayList<>(); // Inicializa a lista de frutas
        listModel = new DefaultListModel<>(); // Inicializa o modelo de lista

        // Criação do JFrame (janela principal)
        JFrame frame = new JFrame("Gerenciador de Frutas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o fechamento da janela
        frame.setSize(600, 300); // Define o tamanho da janela
        frame.setLayout(new BorderLayout()); // Define o layout da janela

        // Painel para o campo de texto e botões
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout()); // Layout para organizar os componentes horizontalmente

        // Campo de texto para inserir o nome da fruta
        JTextField frutaField = new JTextField(15);
        panel.add(frutaField); // Adiciona o campo ao painel

        // Botão "Adicionar" para inserir frutas na lista
        JButton addButton = new JButton("Adicionar");
        panel.add(addButton); // Adiciona o botão ao painel

        // Botão "Modificar" para alterar uma fruta existente
        JButton modifyButton = new JButton("Modificar");
        modifyButton.setEnabled(false); // Inicialmente desabilitado
        panel.add(modifyButton); // Adiciona o botão ao painel

        // Botão "Remover" para deletar uma fruta da lista
        JButton removeButton = new JButton("Remover");
        removeButton.setEnabled(false); // Inicialmente desabilitado
        panel.add(removeButton); // Adiciona o botão ao painel

        // Adiciona o painel na parte superior da janela
        frame.add(panel, BorderLayout.NORTH);

        // Cria a JList para exibir as frutas
        list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list); // Permite rolar a lista
        frame.add(scrollPane, BorderLayout.CENTER); // Adiciona a lista com barra de rolagem ao centro da janela

        // Botão "Listar Frutas" para exibir todas as frutas
        JButton listButton = new JButton("Listar Frutas");
        frame.add(listButton, BorderLayout.SOUTH); // Adiciona o botão na parte inferior da janela

        // Ação do botão "Adicionar"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String novaFruta = frutaField.getText(); // Obtém o texto do campo
                if (!novaFruta.isEmpty()) { // Verifica se não está vazio
                    frutas.add(novaFruta); // Adiciona à lista lógica
                    listModel.addElement(novaFruta); // Adiciona à lista visual
                    frutaField.setText(""); // Limpa o campo de texto
                    JOptionPane.showMessageDialog(frame, novaFruta + " foi adicionada."); // Confirmação
                }
            }
        });

        // Ação do botão "Modificar"
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex(); // Obtém o índice selecionado
                if (selectedIndex != -1) { // Verifica se há uma seleção
                    String frutaSelecionada = listModel.getElementAt(selectedIndex); // Obtém a fruta selecionada
                    String novaFruta = JOptionPane.showInputDialog(frame, "Modificar " + frutaSelecionada + " para:", frutaSelecionada);
                    if (novaFruta != null && !novaFruta.isEmpty()) { // Verifica se a entrada não está vazia
                        frutas.set(selectedIndex, novaFruta); // Altera na lista lógica
                        listModel.set(selectedIndex, novaFruta); // Altera na lista visual
                        JOptionPane.showMessageDialog(frame, "Fruta " + frutaSelecionada + " foi modificada para " + novaFruta); // Confirmação
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione uma fruta para modificar."); // Aviso caso não haja seleção
                }
            }
        });

        // Ação do botão "Remover"
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex(); // Obtém o índice selecionado
                if (selectedIndex != -1) { // Verifica se há uma seleção
                    String frutaRemovida = frutas.remove(selectedIndex); // Remove da lista lógica
                    listModel.remove(selectedIndex); // Remove da lista visual
                    JOptionPane.showMessageDialog(frame, frutaRemovida + " foi removida."); // Confirmação
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione uma fruta para remover."); // Aviso caso não haja seleção
                }
            }
        });

        // Ação do botão "Listar Frutas"
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frutas.isEmpty()) { // Verifica se a lista está vazia
                    JOptionPane.showMessageDialog(frame, "Nenhuma fruta na lista."); // Mensagem de lista vazia
                } else {
                    JOptionPane.showMessageDialog(frame, "Frutas: " + frutas); // Exibe as frutas
                }
            }
        });

        // Listener para habilitar/desabilitar os botões "Modificar" e "Remover" baseado na seleção
        list.addListSelectionListener(e -> {
            boolean selectionExists = !list.isSelectionEmpty(); // Verifica se há seleção
            removeButton.setEnabled(selectionExists); // Habilita/desabilita o botão "Remover"
            modifyButton.setEnabled(selectionExists); // Habilita/desabilita o botão "Modificar"
        });

        // Torna a janela visível
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrutaManagerGUI();
            }
            });
        }

    }