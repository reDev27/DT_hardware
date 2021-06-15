package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.util.Base64;

public class ImageUtility
{

	public static String imgResizeAndBase64(byte[] rawImg) throws IOException
	{
		//byte[] rawImg=getImageFromPath();
		byte[] resizedImg=resize(rawImg);
		String resizedBase64Img=getBase64Image(resizedImg);
		return resizedBase64Img;
	}

	public static byte[] resize(byte[] toResizeImg) throws IOException
	{
		//BufferedImage bufferedImage=new BufferedImage(1000, 1000, BufferedImage.TYPE_BYTE_GRAY);
		ByteArrayInputStream byteInStream = new ByteArrayInputStream(toResizeImg);
		BufferedImage bufferedImage = ImageIO.read(byteInStream);
		Image img=bufferedImage.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
		BufferedImage resizedImg=convertToBufferedImage(img);
		byte[] toWriteResizedImg=new byte[700000];
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
		ImageIO.write(resizedImg, "png", outputStream);
		toWriteResizedImg=outputStream.toByteArray();
		return toWriteResizedImg;
	}

	public static byte[] getImageFromPath(String path) throws IOException
	{
		InputStream in= new FileInputStream(path);
		int length = 1240000;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] immagine=new byte[1240000];
		int index=0;
		int i;
		while ((i = in.read(immagine, 0, length)) != -1)
		{
			index+=i;
			output.write(immagine, 0, i);
		}
		output.flush();
		output.close();
		return immagine;
	}

	public static String getBase64Image(byte[] img) throws IOException
	{
		String encodedImg = Base64.getEncoder().encodeToString(img);
		return encodedImg;
	}

	public static BufferedImage convertToBufferedImage(Image img)
	{

		if (img instanceof BufferedImage)
		{
			return (BufferedImage) img;
		}

		// Create a buffered image with transparency
		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		Graphics2D graphics2D = bi.createGraphics();
		graphics2D.drawImage(img, 0, 0, null);
		graphics2D.dispose();

		return bi;
	}
}
