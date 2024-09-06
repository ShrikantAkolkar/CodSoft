import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApplication extends JFrame {
    private JLabel questionLabel;
    private JRadioButton[] options;
    private JButton submitButton;
    private JLabel timerLabel;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;
    private int secondsLeft;  // Keep track of remaining time for each question

    private final String[] questions = {
            "What is the capital of Maharashtra?",
            "What is 7 * 2?",
            "What is the largest planet in our solar system?"
    };
    private final String[][] choices = {
            {"Pune", "Nagpur", "Mumbai", "Chh Sambhajinagar"},
            {"13", "14", "25", "16"},
            {"Earth", "Jupiter", "Mars", "Venus"}
    };
    private final int[] correctAnswers = {2, 1, 1};

    public QuizApplication() {
        setTitle("Quiz Application by Kartik");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 300);
        setLayout(new BorderLayout());

        initializeComponents();
        showQuestion();
        startTimer();
    }

    private void initializeComponents() {
        // Question Label
        questionLabel = new JLabel();
        add(questionLabel, BorderLayout.NORTH);

        // Options Panel
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        options = new JRadioButton[4];
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            buttonGroup.add(options[i]);
            optionsPanel.add(options[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        // Submit Button
        submitButton = new JButton("Submit");
        add(submitButton, BorderLayout.SOUTH);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                showNextQuestion();
            }
        });

        // Timer Label
        timerLabel = new JLabel();
        add(timerLabel, BorderLayout.EAST);
    }

    private void showQuestion() {
        questionLabel.setText(questions[currentQuestionIndex]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(choices[currentQuestionIndex][i]);
            options[i].setSelected(false);
        }
        submitButton.setEnabled(true);  // Re-enable the submit button
    }

    private void showNextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            showQuestion();
            resetTimer();
        } else {
            showResult();
        }
    }

    private void checkAnswer() {
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected() && i == correctAnswers[currentQuestionIndex]) {
                score++;
            }
        }
        submitButton.setEnabled(false);  // Disable submit button after answering
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "Quiz Completed!\nScore: " + score + "/" + questions.length);
        System.exit(0);
    }

    private void startTimer() {
        secondsLeft = 15;  // Set initial time for the timer
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondsLeft >= 0) {
                    timerLabel.setText("Time left: " + secondsLeft + " seconds");
                    secondsLeft--;
                } else {
                    timer.stop();
                    checkAnswer();
                    showNextQuestion();
                }
            }
        });
        timer.start();
    }

    private void resetTimer() {
        timer.stop();
        startTimer();  // Restart timer with fresh time
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuizApplication().setVisible(true);
            }
        });
    }
}
