import java.io.*;
import static java.lang.System.*;
import java.util.*;

public class Game {
    String WordArray1[] = new String[50];
    Random rand = new Random();
    String faceTerm = "*SSBU*";
    int difficulty;
    int speed;
    String MatchWordCheckArray[][] = new String[8][8];
    int length;
    int faceTermNum;
    String displayArray[][] = new String[8][8];

    Game() {
        difficulty = 2;
        speed = 2;
        length = 0;
        faceTermNum = 0;
    }

    public void gameStart() { // reads the text file, copies into WordArray1, randomizes
        try {
            FileReader fr = new FileReader("smashCharacters.txt");
            BufferedReader br = new BufferedReader(fr);
            String str;
            int arrayIndex = 0;

            while ((str = br.readLine()) != null) {
                WordArray1[arrayIndex] = str;
                arrayIndex++;
            }
            br.close();
        } catch (IOException e) {
            out.println("File not found");
        }

        int r = 0;
        String temp;
        for (int x = 0; x < 50; x++) {
            r = rand.nextInt(50);
            temp = WordArray1[x];
            WordArray1[x] = WordArray1[r];
            WordArray1[r] = temp;
        }
    }

    public void description() {
        out.println("Hi. Welcome to Von's Memory Matching Game!");
        out.println("Your goal is to match all the names.");
        out.println("The theme that the names are related to are: 'Super Smash Bros Ultimate' Characters");
        out.println("To choose a square, it'll ask to input the row, then the column of that square.");
        out.println("Please Enjoy!\n\n");
    }

    public void menu() { // allows players to choose difficulty and speed
        Scanner in = new Scanner(System.in);
        out.println("Please a choose a Difficulty of(1, 2, or 3):\n");
        out.println("1) 4x4 grid (Easy)");
        out.println("2) 6x6 grid (Moderate)");
        out.println("3) 8x8 grid (Difficult)");
        difficulty = in.nextInt();
        if (difficulty < 1 || difficulty > 3) {
            out.println("Invalid Difficulty Level");
            exit(0);
        }

        out.println("Please a choose a Speed (1, 2, or 3):\n");
        out.println("1) 2 seconds (Difficult)");
        out.println("2) 4 seconds (Moderate)");
        out.println("3) 6 seconds (Easy)");
        speed = in.nextInt();
        in.close();

        if (speed < 1 || speed > 3) {
            out.println("Invalid Speed Difficulty");
            exit(0);
        }
    }

    public void displayCards() { // uses difficulty to create a certain rand array
        if (difficulty == 1) {
            createRandArray(2, 4);
        } else if (difficulty == 2) {
            createRandArray(3, 6);
        } else if (difficulty == 3) {
            createRandArray(4, 8);
        }
    }

    public void createRandArray(int num1, int num2) { // randomizes MWCheckArray
        int d = 0;

        for (int b = 0; b < num1; b++) {
            for (int c = 0; c < num2; c++) {
                MatchWordCheckArray[b][c] = WordArray1[d];
                MatchWordCheckArray[b + num1][c] = WordArray1[d];
                d++;
            }
        }

        int r = 0;
        int n = 0;
        String temp;
        Random rand = new Random();

        for (int x = 0; x < num2; x++) {
            for (int y = 0; y < num2; y++) {
                r = rand.nextInt(num2);
                n = rand.nextInt(num2);
                temp = MatchWordCheckArray[x][y];
                MatchWordCheckArray[x][y] = MatchWordCheckArray[r][n];
                MatchWordCheckArray[r][n] = temp;
            }
        }
    }

    public void displayInitialArray() { // displays faceTerm array
        if (difficulty == 1) {
            length = 4;
        } else if (difficulty == 2) {
            length = 6;
        } else if (difficulty == 3) {
            length = 8;
        }

        faceTermNum = length * length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                displayArray[i][j] = faceTerm;
                out.print(" | ");
                out.print(displayArray[i][j]);
                if (j == length - 1) {
                    out.print(" |\n");
                }
            }
        }
    }

    public void displayArray() { // displays array
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                out.print(" | ");
                out.print(displayArray[i][j]);
                if (j == length - 1) {
                    out.print(" |\n");
                }
            }
        }
    }

    public void gamePlay() { // does gameplay
        while (true) {
            int a = 0, b = 0, c = 0, d = 0;
            Scanner in = new Scanner(System.in);

            out.print("Please input row: ");
            a = (in.nextInt()) - 1;
            out.print("Please input column: ");
            b = (in.nextInt()) - 1;
            out.println();

            if (a >= length || a < 0 || b >= length || b < 0) {
                out.println("Out of Bounds. Please Try Again.\n(Please don't input until reset)");
                pause(1);
                out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                displayArray();
                gamePlay();
            }

            switchArrays(a, b);
            out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            displayArray();

            if (displayArray[a][b] == faceTerm) {
                out.println("Already Entered.\n(Please don't input until reset)");
                switchArrays(a, b);
                pause(1);
                out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                displayArray();
                gamePlay();
            }

            out.print("Please input row: ");
            c = (in.nextInt()) - 1;
            out.print("Please input column: ");
            d = (in.nextInt()) - 1;
            out.println();

            if (c >= length || c < 0 || d >= length || d < 0) {
                out.println("Out of Bounds. Please Try Again.\n(Please don't input until reset)");
                pause(1);
                out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                switchArrays(a, b);
                displayArray();
                gamePlay();
            }

            switchArrays(c, d);
            out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            displayArray();

            if (displayArray[c][d] == faceTerm) {
                out.println("Already Entered.\n(Please don't input until reset)");
                switchArrays(a, b);
                switchArrays(c, d);
                pause(1);
                out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                displayArray();
                gamePlay();
            }

            if (displayArray[a][b] == displayArray[c][d]) {
                out.print("Correct!\n(Please don't input until reset)");
                pause(1);
                faceTermNum = faceTermNum - 2;
            } else {
                out.print("Incorrect.\n(Please don't input until reset)");
                pause(speed);
                switchArrays(a, b);
                switchArrays(c, d);
            }
            gameOver();
        }
    }

    public void switchArrays(int x, int y) { // switches faceTerm array and MWCheckArray
        String temp;
        temp = displayArray[x][y];
        displayArray[x][y] = MatchWordCheckArray[x][y];
        MatchWordCheckArray[x][y] = temp;
    }

    public void pause(int x) { // pauses game
        try {
            Thread.sleep(x * 2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void gameOver() { // checks if should game over
        if (faceTermNum == 0) {
            out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            out.print("Congratulations!!! Game Over");
            exit(0);
        } else {
            out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            displayArray();
            gamePlay();
        }
    }
}