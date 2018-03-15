package dk.purplegreen.musiclibrary.mule;

import java.io.File;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
import org.springframework.beans.factory.annotation.Value;

public class SongTransformer extends AbstractMessageTransformer{
	
	class Song{
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
		
		String fileName=message.getOutboundProperty("filename");
		
		System.out.println("Filename: "+fileName);
		System.out.println("Dir: "+dir);
		
		File songFile=new File(dir,fileName);
		
	
		Song song=new Song();
	
		//TODO Get from tags
		song.artist="Iron Maiden";
		song.title="Murders in the Rue Morgue";
		song.album="Killers";
		song.year=1981;
		song.track=2;
		song.disc=1;
		
		
		return song;
	}

}
