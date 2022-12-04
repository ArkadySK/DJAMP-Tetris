import fri.shapesge.FontStyle;
import fri.shapesge.Text;

public class GameUI {
    private Text scoreText;
    private int score;
    
    public GameUI() {
        this.scoreText = new Text("Skóre: 0", 10, 20);
        this.scoreText.changeFont("Arial", FontStyle.PLAIN, 14);
        this.scoreText.makeVisible();
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.scoreText.changeText("Skóre: " + score);
        this.score = score;
    }

    public void clear() {
        this.scoreText.makeInvisible();
    }


    
}
