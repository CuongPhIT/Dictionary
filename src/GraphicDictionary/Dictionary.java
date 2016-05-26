package GraphicDictionary;

import java.util.ArrayList;

public class Dictionary {

	private static Dictionary _instance;

	public static Dictionary getInstance() {
		if (_instance == null) {
			_instance = new Dictionary();
		}
		return _instance;
	}

	ArrayList<Word> listEN = new ArrayList<>();
	ArrayList<Word> listVI = new ArrayList<>();
	public int searchAnh(String keyword) {
		for (int i = 0; i < listEN.size(); i++) {
			if (listEN.get(i).getWordAnh().toLowerCase().equals(keyword.toLowerCase())) {
				return i;
			}
		}

		return -1;
	}

	public int searchViet(String keyword) {
		for (int i = 0; i < listVI.size(); i++) {
			if (listVI.get(i).getWordViet().toLowerCase().equals(keyword.toLowerCase())) {
				return i;
			}
		}
		
		return -1;
	}

	public void addWordEN(String wordEN, String wordVI) {
			Word d = new Word();
			d.setWordAnh(wordEN);
			d.setWordViet(wordVI);
			listEN.add(d);
		
	}
	
	public void addWordVI(String wordVI, String wordEN) {
			Word d = new Word();
			d.setWordAnh(wordEN);
			d.setWordViet(wordVI);
			listVI.add(d);
	}
	public boolean check(String newWord, String mean, ArrayList<Word> list){
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getWordAnh().toLowerCase().equals(newWord.toLowerCase()) || list.get(i).getWordViet().toLowerCase().equals(mean.toLowerCase())){
				return false;
			}
		}
		return true;
	}
}
