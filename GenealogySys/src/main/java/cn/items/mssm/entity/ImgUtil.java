package cn.items.mssm.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImgUtil {

	/** 
	 * @ClassName: ImgUtil 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年10月24日 下午1:11:29 
	 */
	
	public static String getImageSrc(String htmlCode) {
		List<String> imageSrcList = new ArrayList<String>();
		Pattern p = Pattern
				.compile(
						"<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>",
						Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(htmlCode);
		String quote = null;
		String src = null;
		while (m.find()) {
			quote = m.group(1);
			src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("\\s+")[0] : m.group(2);		
			imageSrcList.add(src);
		}
		List t=StringChange.Change(imageSrcList);
		String h=new String();
		for(int i = 0;i<t.size();i++){
			h = htmlCode.replace(imageSrcList.get(i), t.get(i).toString());
			htmlCode = h;
		}
		return htmlCode;
	}
}
