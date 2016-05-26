package GraphicDictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileData {

	private BufferedReader br;
	private BufferedWriter bw;
	public static  final String FILE_TEMP ="temp.txt";
	Dictionary dict = Dictionary.getInstance();
	
	public static void writeFile(String text, String fileName) {
		FileWriter fw;

		try {
			File file = new File(fileName);

			if (!file.exists()) {
				System.out.println("error!");
			}

			fw = new FileWriter(file.getPath(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write( text);
			bw.newLine();
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void readFile(String fileName) {
		Dictionary ac = Dictionary.getInstance();
		try (FileReader fr = new FileReader(fileName)) {
			BufferedReader br = new BufferedReader(fr);
			String s;
			while ((s = br.readLine()) != null) {
				String[] str = s.split("[\t]");
				if (fileName == MainForm.FILE_NAME_VI) {
					str[1]="<html>"+str[1]+"</html>";
					ac.addWordVI(str[0], str[1]);
				} else {
					str[1]="<html>"+str[1]+"</html>";
					ac.addWordEN(str[0], str[1]);
				}

			}
			br.close();
		} catch (IOException e) {
			System.out.println("Error!");

		}

	}

	public void editFile(String fileName, String string, int count) throws IOException {
		try (FileReader fr = new FileReader(fileName)) {
			br = new BufferedReader(fr);
			bw = new BufferedWriter(new FileWriter(FILE_TEMP));
			String s;
			int i=0;
			while ((s = br.readLine()) != null) {
				if(i==count){
					s=s.replace(s, string);
					bw.write(s + "\n");
					if(fileName.equals(MainForm.FILE_NAME_EN)) {
						dict.listEN.remove(count);
					}else{
						dict.listVI.remove(count);
					}
					
				}else{
					bw.write(s+"\n");
				}
				i++;
			}
			br.close();
		} catch (IOException e) {
			return;
		}finally {
			try{
				if(br!=null){
					br.close();
				}
				if(bw!=null){
					bw.flush();
				    bw.close();
				}
				
			}catch(IOException e){
				
			}
		}
		File oldFile = new File(fileName);
		oldFile.delete();
		File newFile= new File(FILE_TEMP);
		newFile.renameTo(oldFile);
	}
	
}
