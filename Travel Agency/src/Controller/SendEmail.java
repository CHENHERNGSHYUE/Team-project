package Controller;

import java.awt.Desktop;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
/**
 * Function of sending email
 * @author Group 25
 *
 */
public class SendEmail {
	
	/**
	 * Send email to specific mail address
	 * @param mailAddress
	 * @param topic
	 * @param body
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static void mailto(List<String> mailAddress, String topic,
	        String body) throws IOException, URISyntaxException {
	    String uriStr = String.format("mailto:%s?topic=%s&body=%s",
	            join(",", mailAddress),
	            urlEncode(topic),
	            urlEncode(body));
	    Desktop.getDesktop().browse(new URI(uriStr));
	}

	/**
	 * define the default message
	 * @param str
	 * @return
	 */
	private static final String urlEncode(String str) {
	    try {
	        return URLEncoder.encode(str, "UTF-8").replace("+", "%20");
	    } catch (UnsupportedEncodingException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	/**
	 * define the default message
	 * @param sep
	 * @param objs
	 * @return
	 */
	public static final String join(String sep, Iterable<?> objs) {
	    StringBuilder sb = new StringBuilder();
	    for(Object obj : objs) {
	        if (sb.length() > 0) sb.append(sep);
	        sb.append(obj);
	    }
	    return sb.toString();
	}


}
