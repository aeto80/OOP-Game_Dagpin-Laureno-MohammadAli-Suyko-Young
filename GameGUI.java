package com.mycompany.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GameGUI extends JFrame {
    private Game game;
    private QuestionManager questionManager;

    private JLabel levelLabel;
    private JLabel playerHpLabel;
    private JLabel enemyHpLabel;
    private JProgressBar playerHpBar;
    private JProgressBar enemyHpBar;

    private JLabel playerImageLabel;
    private JLabel enemyImageLabel;

    private JLabel questionLabel;
    private JTextField answerField;
    private JButton submitButton;
    private JTextArea logArea;

    public GameGUI() {
        game = new Game();
        questionManager = new QuestionManager();

        setTitle("Code Quiz RPG");
        setSize(700, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- TOP PANEL: Stats, Progress & Images ---
        // --- TOP PANEL: Stats, Progress & Images ---
        JPanel topPanel = new JPanel(new BorderLayout(15, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // Player Section
        JPanel playerPanel = new JPanel(new BorderLayout(5, 5));
        playerHpLabel = new JLabel("PLAYER HP", SwingConstants.CENTER);
        playerHpBar = new JProgressBar();
        playerHpBar.setForeground(new Color(46, 204, 113));
        playerHpBar.setStringPainted(true);
        
        playerImageLabel = new JLabel("[Player Image]", SwingConstants.CENTER);
        playerImageLabel.setPreferredSize(new Dimension(120, 120));
        playerImageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        playerPanel.add(playerHpLabel, BorderLayout.NORTH);
        playerPanel.add(playerImageLabel, BorderLayout.CENTER);
        playerPanel.add(playerHpBar, BorderLayout.SOUTH);

        // Center Level Info
        JPanel centerHeader = new JPanel(new GridLayout(2, 1));
        levelLabel = new JLabel("Level: 1", SwingConstants.CENTER);
        levelLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        JLabel titleLabel = new JLabel("Complete the Code!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        centerHeader.add(levelLabel);
        centerHeader.add(titleLabel);

        // Enemy Section
        JPanel enemyPanel = new JPanel(new BorderLayout(5, 5));
        enemyHpLabel = new JLabel("ENEMY HP", SwingConstants.CENTER);
        enemyHpBar = new JProgressBar();
        enemyHpBar.setForeground(new Color(231, 76, 60));
        enemyHpBar.setStringPainted(true);
        
        enemyImageLabel = new JLabel("[Enemy Image]", SwingConstants.CENTER);
        enemyImageLabel.setPreferredSize(new Dimension(120, 120));
        enemyImageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        enemyPanel.add(enemyHpLabel, BorderLayout.NORTH);
        enemyPanel.add(enemyImageLabel, BorderLayout.CENTER);
        enemyPanel.add(enemyHpBar, BorderLayout.SOUTH);

        topPanel.add(playerPanel, BorderLayout.WEST);
        topPanel.add(centerHeader, BorderLayout.CENTER);
        topPanel.add(enemyPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // --- CENTER PANEL: Question Display & Log Area ---
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        questionLabel.setBorder(BorderFactory.createTitledBorder("Code Snippet"));
        questionLabel.setPreferredSize(new Dimension(600, 80));

        logArea = new JTextArea(8, 40);
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(logArea);

        centerPanel.add(questionLabel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // --- BOTTOM PANEL: Player Input ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));

        JLabel fillLabel = new JLabel("Missing Code:");
        answerField = new JTextField(15);
        submitButton = new JButton("Submit Answer");

        bottomPanel.add(fillLabel);
        bottomPanel.add(answerField);
        bottomPanel.add(submitButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // --- Event Listeners ---
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        };

        submitButton.addActionListener(actionListener);
        answerField.addActionListener(actionListener);

        // Start UI state
        loadNextQuestion();
        updateUIState();
        log("Battle Started! Level 1 Enemy Appeared.");
    }

    private ImageIcon loadAndScaleImage(String path, int width, int height) {

        File file = new File(path);

        if (!file.exists()) {
            file = new File("src/main/java/com/mycompany/main/" + path);
        }

        if (!file.exists()) {
            file = new File("../Images/" + path);
        }

        if (file.exists()) {
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

            return new ImageIcon(image);
        }

        System.out.println("[Image Warning] Could not locate image: " + path);

        return null;
    }

    private void loadNextQuestion() {
        question q = questionManager.getQuestion(game.getCurrentLevel());
        if (q != null) {
            questionLabel.setText("<html><div style='text-align: center;'>" 
                    + q.getFullCode().replace("\n", "<br>").replace(" ", "&nbsp;") 
                    + "</div></html>");
        } else {
            questionLabel.setText("No questions available for Level " + game.getCurrentLevel());
        }
    }

    private void handleSubmit() {
        if (game.isGameOver()) {
            return;
        }

        String userChoice = answerField.getText();
        if (userChoice.isEmpty()) {
            return;
        }

        boolean isCorrect = questionManager.checkAnswer(userChoice);
        answerField.setText("");

        if (isCorrect) {
            log("[CORRECT] Dealt " + game.getPlayer().getAttackDamage() + " damage to " + game.getCurrentEnemy().getName() + "!");
        } else {
            log("[WRONG] Expected: \"" + questionManager.getCurrentQuestion().getCorrectAnswer() 
                + "\". Enemy hit you for " + game.getCurrentEnemy().getAttackDamage() + " damage!");
        }

        game.answerQuestion(isCorrect);
        updateUIState();

        if (game.isGameOver()) {
            if (game.isVictory()) {
                log("=========================================");
                log("VICTORY! You passed all levels!");
                JOptionPane.showMessageDialog(this, "Victory! You defeated all programming bugs!", "Winner", JOptionPane.INFORMATION_MESSAGE);
            } else {
                log("=========================================");
                log("GAME OVER! You were defeated.");
                JOptionPane.showMessageDialog(this, "Defeat! Your code has syntax errors.", "Game Over", JOptionPane.ERROR_MESSAGE);
            }

            int option = JOptionPane.showConfirmDialog(this, "Play again?", "Restart Game", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                restartGame();
            } else {
                submitButton.setEnabled(false);
                answerField.setEnabled(false);
            }
        } else {
            loadNextQuestion();
        }
    }

    private void updateUIState() {
        Player p = game.getPlayer();
        Enemy e = game.getCurrentEnemy();

        levelLabel.setText("Level: " + game.getCurrentLevel());

        // Update Player Stats & Image
        playerHpBar.setMaximum(p.getMaxHp());
        playerHpBar.setValue(p.getHP());
        playerHpBar.setString(p.getHP() + " / " + p.getMaxHp());
        playerHpLabel.setText(p.getName().toUpperCase() + " HP");
        
        ImageIcon pIcon = loadAndScaleImage(p.getImagePath(), 100, 100);
        if (pIcon != null) {
            playerImageLabel.setIcon(pIcon);
            playerImageLabel.setText(""); 
        }

        // Update Enemy Stats & Image
        enemyHpBar.setMaximum(e.getMaxHp());
        enemyHpBar.setValue(e.getHP());
        enemyHpBar.setString(e.getHP() + " / " + e.getMaxHp());
        enemyHpLabel.setText(e.getName().toUpperCase() + " HP");
        
        ImageIcon eIcon = loadAndScaleImage(e.getImagePath(), 100, 100);
        if (eIcon != null) {
            enemyImageLabel.setIcon(eIcon);
            enemyImageLabel.setText(""); 
        }
    }

    private void log(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    private void restartGame() {
        game.startGame();
        logArea.setText("");
        log("Game Restarted! Level 1.");
        submitButton.setEnabled(true);
        answerField.setEnabled(true);
        loadNextQuestion();
        updateUIState();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameGUI().setVisible(true));
    }
}