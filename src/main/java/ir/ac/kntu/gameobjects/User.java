package ir.ac.kntu.gameobjects;

import java.io.*;

public class User {

    private String userName;

    private String passWord;

    private int score;

    private int currentLine;

    private int highScore = 0;

    private int numberOfMatches = 0;


    public User(String userName, String passWord, int score) {
        this.userName = userName;
        this.passWord = passWord;
        this.score = score;
    }

    public User(String line) {
        String[] words = line.split(" ");
        this.userName = words[0];
        this.passWord = words[1];
        this.score = Integer.parseInt(words[2]);

    }

    public User(int lineNumber) {
        System.out.println(lineNumber);
        this.currentLine = lineNumber;
        try {
            // Read the original file
            File file = new File("Users.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int currentLine = 0;
            while ((line = reader.readLine()) != null) {
                // Append the new text to the desired line
                if (currentLine == lineNumber) {
                    // String[] words=line.split(" ");
                    String[] words = line.split(" ");
                    this.score = Integer.parseInt(words[2]);
                    this.highScore = Integer.parseInt(words[3]);
                    this.userName = words[0];
                    this.passWord = words[1];
                }
                currentLine++;
            }
            reader.close();
            // Write the modified content back to the file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public void setNumberOfMatches(int numberOfMatches) {
        this.numberOfMatches = numberOfMatches;
    }

    public int getCurrentLine() {
        return currentLine;
    }

    public void setCurrentLine(int currentLine) {
        this.currentLine = currentLine;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        if (this.score >= highScore) {
            this.highScore = this.score;
        }
    }

    public void addScore(int score) {
        // int min=0;
        this.score += score;
        if (this.score >= highScore) {
            this.highScore = this.score;
        }
        textFileModifier();
    }

    public void addTotalNumberOfMatches() {
        this.numberOfMatches++;
        String filePath = "Users.txt"; // Specify the path to your text file
        int lineNumber = currentLine; // Specify the line number where you want to add the text
        try {
            // Read the original file
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            int currentLine = 0;
            while ((line = reader.readLine()) != null) {
                // Append the new text to the desired line
                if (currentLine == lineNumber) {
                    content.append(this.userName).append(" ").append(this.passWord).append(" ")
                            .append(this.score).append(" ").append(this.highScore).append(" ").append(numberOfMatches)
                            .append(System.lineSeparator());
                } else {
                    content.append(line).append(System.lineSeparator());
                }
                currentLine++;
            }
            reader.close();
            FileWriter writer = new FileWriter(file);
            writer.write(content.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void textFileModifier() {
        String filePath = "Users.txt"; // Specify the path to your text file
        int lineNumber = currentLine; // Specify the line number where you want to add the text
        try {
            // Read the original file
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            int currentLine = 0;
            while ((line = reader.readLine()) != null) {
                // Append the new text to the desired line
                if (currentLine == lineNumber) {
                    content.append(this.userName).append(" ").append(this.passWord).append(" ")
                            .append(this.score).append(" ").append(this.highScore).append(" ").append(numberOfMatches)
                            .append(System.lineSeparator());
                } else {
                    content.append(line).append(System.lineSeparator());
                }
                currentLine++;
            }
            reader.close();
            FileWriter writer = new FileWriter(file);
            writer.write(content.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
