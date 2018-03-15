package dk.purplegreen.musiclibrary.mule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
import org.springframework.beans.factory.annotation.Value;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

public class SongTransformer extends AbstractMessageTransformer {

	private static Logger log = Logger.getLogger(SongTransformer.class);

	class Song {
		public String artist;
		public String album;
		public Integer year;
		public String title;
		public Integer track;
		public Integer disc;
	}

	@Value("${file.process.dir}")
	private String dir;

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {

		log.info("Processing MP3 tag from file: " + message.getInvocationProperty("mp3FileName"));

		try {

			if (InputStream.class.isInstance(message.getPayload())) {

				log.debug("Closing input stream");
				((InputStream) message.getPayload()).close();
			}

			String fileName = message.getInvocationProperty("mp3FileName");

			File songFile = new File(fileName);
			if (!songFile.exists()) {
				throw new FileNotFoundException();
			}

			Mp3File mp3File = new Mp3File(songFile.getAbsolutePath());
			if (mp3File.hasId3v2Tag()) {
				ID3v2 tag = mp3File.getId3v2Tag();

				Song song = new Song();

				song.artist = tag.getArtist();
				song.title = tag.getTitle();
				song.album = tag.getAlbum();
				song.year = Integer.valueOf(tag.getYear());
				song.track = Integer.valueOf(tag.getTrack());
				song.disc = tag.getPartOfSet() == null ? 1 : Integer.valueOf(tag.getPartOfSet());

				return song;
			} else
				throw new IllegalArgumentException("Missing MP3 tag");

		} catch (Exception e) {
			log.error("Exception in SongTransformer", e);
			throw new TransformerException(this, e);
		}
	}
}
