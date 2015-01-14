
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

public class MainIS {

	public static void main(String[] args) throws Exception {
		
		     /*String[] arr = new String[4];
	         arr[0] = "d:\\test.jpeg";
	         arr[1] = "d:\\test1.jpeg";
	         arr[2] = "250";
	         arr[3] = "200";*/
	       	    
	
		if (args.length == 3 | args.length == 4)
		{
			try 
			{
				File imgInFile = new File(args[0]);
				File imgOutFile = new File(args[1]);
				BufferedImage img_in = ImageIO.read(imgInFile);
				if (args.length == 4)
				{
					BufferedImage img_out = Scalr.resize(img_in, Scalr.Mode.FIT_EXACT, 
							Integer.parseInt(args[2]), Integer.parseInt(args[3]));
					ImageIO.write(img_out, "jpg", imgOutFile);
				}
				else
				{
					BufferedImage img_out = Scalr.resize(img_in, Scalr.Mode.FIT_TO_WIDTH,
							Integer.parseInt(args[2]), 0);
					ImageIO.write(img_out, "jpg", imgOutFile);
				}
			}
			catch (NumberFormatException exc)
			{
				System.out.println("Неверное значение ширины или высоты");
				System.out.println(exc);
			}
			catch (IIOException exc)
			{
				System.out.println("Входной файл не существует");
				System.out.println(exc);
			}
			catch (FileNotFoundException exc)
			{
				System.out.println("Неправильный путь выходного файла");
				System.out.println(exc);
			}
		}
		else
		{
			System.out.println("Неверное количество аргументов");
		}
	}

}
