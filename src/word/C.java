package word;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class C {

	public static Color red = new Color(255,87,55);
	public static Color orange = new Color(255,153,0);
	public static Color yellow = new Color(255,204,51);
	public static Color green = new Color(153,255,153);
	public static Color darkgreen = new Color(0,102,51);
	public static Color blue = new Color(153,153,255);
	public static Color darkblue = new Color(0,0,153);
	public static Color skyblue = new Color(204,255,255);
	public static Color purple = new Color(204,102,255);
	public static Color pink = new Color(255,153,204);
	public static Color gray = new Color(215,215,215);
	public static Color brown = new Color(102,51,0);
	public static Color darkbrown = new Color(51,0,0);
	public static Color skin = new Color(255,204,153);
	public static Color white = new Color(255,255,255);
	public static Color black = new Color(0,0,0);
	public static Color gold = new Color(255,215,0);
	public static Color silver = new Color(192,192,192);
	public static Font titleFont, answerFont, btnFont, arithFont, hundredFont,fiftyFont, examFont;
	static {
		try {
			titleFont = Font.createFont(Font.TRUETYPE_FONT, new File("./temp/경기천년제목.ttf"));
			titleFont = titleFont.deriveFont(70f);
			answerFont = Font.createFont(Font.TRUETYPE_FONT, new File("./temp/경기천년제목.ttf"));
			answerFont = answerFont.deriveFont(100f);
			hundredFont = Font.createFont(Font.TRUETYPE_FONT, new File("./temp/경기천년제목.ttf"));
			hundredFont = hundredFont.deriveFont(100f);
			btnFont = Font.createFont(Font.TRUETYPE_FONT, new File("./temp/경기천년제목.ttf"));
			btnFont = btnFont.deriveFont(30f);
			arithFont = Font.createFont(Font.TRUETYPE_FONT, new File("./temp/경기천년제목.ttf"));
			arithFont = arithFont.deriveFont(200f);
			fiftyFont = Font.createFont(Font.TRUETYPE_FONT, new File("./temp/경기천년제목.ttf"));
			fiftyFont = fiftyFont.deriveFont(50f);
			examFont = Font.createFont(Font.TRUETYPE_FONT, new File("./temp/경기천년제목.ttf"));
			examFont = examFont.deriveFont(20f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Color[] a10035 = new Color[] {
			new Color(161,0,53),
			new Color(254,194,96),
			new Color(63,167,150),
			new Color(42,9,68)
	};
	public static Color[] a614124 = new Color[] {
			new Color(159,192,136),
			new Color(232,192,125),
			new Color(204,112,75),
			new Color(97,65,36)
	};
}
