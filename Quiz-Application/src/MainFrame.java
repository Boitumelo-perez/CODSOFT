import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Question {
    private String question;
    private String[] options;
    private int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

public class MainFrame extends JFrame {
    private JPanel questionPanel;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionsGroup;
    private JButton submitButton;
    private JLabel timerLabel;
    private Timer timer;
    private int timeRemaining;
    private int currentQuestionIndex;
    private int score;
    private List<Question> questions;
    private List<String> results;

    public MainFrame() {
        questions = new ArrayList<>();
        results = new ArrayList<>();
        loadQuestions();
        initializeComponents();
        showQuestion(0);
    }

    private void loadQuestions() {
        questions.add(new Question("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Madrid"}, 0));
        questions.add(new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 1));
        questions.add(new Question("Which car brand is German?", new String[]{"Toyota","Skoda","Audi","Renault"}, 2));
        // Add more questions as needed
    }

    private void initializeComponents() {
        setTitle("Quiz Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);  // Center the frame on the screen

        questionPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Add some padding

        questionLabel = new JLabel("Question");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        questionPanel.add(questionLabel, gbc);

        optionsGroup = new ButtonGroup();
        optionButtons = new JRadioButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionsGroup.add(optionButtons[i]);
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.WEST;
            questionPanel.add(optionButtons[i], gbc);
        }

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        questionPanel.add(submitButton, gbc);

        timerLabel = new JLabel("Time remaining: 30");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        questionPanel.add(timerLabel, gbc);

        getContentPane().add(questionPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void showQuestion(int index) {
        if (index < questions.size()) {
            Question question = questions.get(index);
            questionLabel.setText(question.getQuestion());
            String[] options = question.getOptions();
            for (int i = 0; i < optionButtons.length; i++) {
                optionButtons[i].setText(options[i]);
                optionButtons[i].setSelected(false);
            }
            timeRemaining = 30;
            timerLabel.setText("Time remaining: " + timeRemaining);
            startTimer();
        } else {
            showResults();
        }
    }

    private void startTimer() {
        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                timerLabel.setText("Time remaining: " + timeRemaining);
                if (timeRemaining <= 0) {
                    timer.stop();
                    checkAnswer();
                }
            }
        });
        timer.start();
    }

    private void checkAnswer() {
        timer.stop();
        int selectedOption = -1;
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                selectedOption = i;
                break;
            }
        }
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (selectedOption == currentQuestion.getCorrectAnswer()) {
            score++;
            results.add("Question " + (currentQuestionIndex + 1) + ": Correct");
        } else {
            results.add("Question " + (currentQuestionIndex + 1) + ": Incorrect");
        }
        currentQuestionIndex++;
        showQuestion(currentQuestionIndex);
    }

    private void showResults() {
        getContentPane().removeAll();
        JPanel resultsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Add some padding

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        resultsPanel.add(new JLabel("Quiz Complete! Your score: " + score + "/" + questions.size()), gbc);
        
        gbc.gridwidth = 1;
        for (int i = 0; i < results.size(); i++) {
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            resultsPanel.add(new JLabel(results.get(i)), gbc);
        }

        JButton tryAgainButton = new JButton("Try Again");
        tryAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetQuiz();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = results.size() + 1;
        resultsPanel.add(tryAgainButton, gbc);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = results.size() + 1;
        resultsPanel.add(closeButton, gbc);

        getContentPane().add(resultsPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void resetQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        results.clear();
        getContentPane().removeAll();
        initializeComponents();
        showQuestion(currentQuestionIndex);
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            checkAnswer();
        }
    }
}