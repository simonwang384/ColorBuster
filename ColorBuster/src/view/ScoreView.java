
package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;



public class ScoreView extends JPanel {

	
	private static final long serialVersionUID = 1L;
	
	private JLabel scoreLabel;
	
	public ScoreView() {
		scoreLabel = new JLabel("Score: 0");
		add(scoreLabel);

	}
	
	public void updateScore(int score) {
		scoreLabel.setText("Score: " + score);
	}
}
