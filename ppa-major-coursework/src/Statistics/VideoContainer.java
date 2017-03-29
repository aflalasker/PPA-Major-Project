package Statistics;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;

public class VideoContainer extends JPanel {
	private Iterator<SearchResult> iteratorSearchResults;
	private JScrollPane scroll;

	public VideoContainer(Iterator<SearchResult> results) {
		super(new GridLayout(25,1));
		this.iteratorSearchResults = results;
		scroll = new JScrollPane(this);
		add(new JLabel("Here are the top 50 UFO videos from YouTube watch for you to watch."));
		addVideos();
		
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void addVideos() {
		while (iteratorSearchResults.hasNext()) {

			SearchResult singleVideo = iteratorSearchResults.next();
			ResourceId rId = singleVideo.getId();

			JButton vidTitle = new JButton();
			vidTitle.setText(singleVideo.getSnippet().getTitle());
			vidTitle.setCursor(new Cursor(Cursor.HAND_CURSOR));
			vidTitle.addActionListener(e -> {
				rId.getVideoId();
				String videoId = rId.getVideoId();
				videoId = "https://www.youtube.com/watch?v=" + videoId;
				  try {
	                    Desktop.getDesktop().browse(new URI(videoId));
	                } catch (URISyntaxException | IOException ex) {
	                    //It looks like there's a problem
	                }
			});
			add(vidTitle, BorderLayout.CENTER);
			setFont(new Font("serif", Font.PLAIN, 30));
			
		}
	}
}
