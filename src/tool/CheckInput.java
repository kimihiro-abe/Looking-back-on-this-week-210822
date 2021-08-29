package tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckInput{ //入力されたIDとpass等の整合性チェックメソッドを集めたクラス

	public static void main(String[] args) {

		String category = "TVアニメ（日本）";
		if(category.contains("（")) {
			category = category.replace("（", "(");
		}
		if(category.contains("）")) {
			category = category.replace("）", ")");
		}
		System.out.println(category);

//	String txt = "stamina47.jpg";
//	System.out.println(txt.length());

		//これでローカルな場所のファイルは消せる。UploadActionの84～87行目をDB_filterに移植した上でコンテキストパスをセッション化して、使うことを考える。
//		Path p = Paths.get("C:\\pleiades-2020-06-java-win-64bit_20200702\\pleiades\\GoGoSeach\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\GoGoSeach\\upload\\images\\b00.jpg");
//
//		try{
//		  Files.delete(p);
//		}catch(IOException e){
//		  System.out.println(e);
//		}




//		List<Integer> preList = new ArrayList<Integer>() ;
//		List<Integer> postList = new ArrayList<Integer>() ;
//
//		for(int i = 1; i < 11; i++) {
//			preList.add(i);
//			System.out.println(i);
//		}
//		System.out.println("pre_size: " + preList.size());
//
//		Random r = new Random();
//
//		//for(int i = 0; i < 5; i++) {
//		//	pre.size(r.nextInt(3));
//		//}
//
//		for(int i = 0; i < 1; i++) {//5回取り出す。1回取り出すときは『i < 1』と設定。
//			int removePostition = r.nextInt(preList.size());
//			postList.add(preList.remove(removePostition));//0から数えて５つめ、つまり6番目が取り出されれる
//
//			int result = postList.get(i);
//			System.out.println("result: " + result);
//		}

//	    Random r = new Random();
//	    for(int i=0 ; i<5 ; i++) {
//	      String hand = hands[r.nextInt(3)];





//	    String msg1 = "Hello";
//	    System.out.println(msg1.equals("Hello"));
//
//		String sloginID = "kiteretu";
//
//		String nowDateTime = GetDateTime.cDateTime();
//		System.out.println(nowDateTime);
//
//		String changeLoginID = sloginID + "+" + nowDateTime;
//		System.out.println("changeLoginID : " + changeLoginID);
//
//		//String型.substring(String型.length - 切り出したい文字列の長さ);
//		String ghostText = changeLoginID.substring(changeLoginID.length()-15);
//		System.out.println("restoreLoginID : " + ghostText);

//		String src = "1+delete";
//		String srcs[] = src.split("\\+");
//		System.out.println(srcs[0]);//
//		System.out.println(srcs[1]);//

	}


	public String emptyCheck(String input) {
		String str = input;
		String result = null;

		try {
			if(str.isEmpty()) {; //length()が0の場合にのみtrueを返す。
				return result="※入力欄が空欄なので再入力をお願いいたします。"; //""は空欄と認識される。
			}
			else {
			return result=""; //入力を満たしている場合。が、現状ではスペースは文字と認識される
			}
		}
		catch(NullPointerException e) { //念のためにnullの時の判定も入れておく。
			return result="※入力欄が空欄なので再入力をお願いいたします。";
		}
	}

	public String idPassLeng(String inputID, String inputPass) { //loginID,passの文字数をチェックする。
		String id = inputID;
		String pass = inputPass;
		String result = null;

		try {
			if(id.length()>=8 && id.length()<=24) { //8文字ピッタリ以上且つ24文字ぴったり以内
				if(pass.length()>=8 && pass.length()<=24) { //8文字ピッタリ以上且つ24文字ぴったり以内
					//文字数規定を全てクリアしている場合にはtrue
					return result=""; //IDはok,passもok
				}
				else {
					return result="passwordを8文字以上24文字以内で再設定してください。"; //IDはok,passはNG
				}
			}
			else if(pass.length()>=8 && pass.length()<=24) { //ID=NG,passはOK
				return result="loginIDを8文字以上24文字以内で再設定してください。";
			}
			else { //ID=NG,passもNG
				return result="loginIDとpasswordを8文字以上24文字以内で再設定してください。";  //文字数規定をクリアしていない場合にはfalse
			}
		}
		catch(NullPointerException e) { //念のためにnullの時の判定も入れておく。
			return result="入力欄が空欄です。";
		}
	}

	public String nNameLeng(String nickName) { //nickNameの文字数をチェックする。
		String nName = nickName;
		String result = null;

		try {
			if(nName.length()>=8 && nName.length()<=24) { //8文字ピッタリ以上且つ24文字ぴったり以内
				//文字数規定を全てクリアしている場合にはtrue
				return result=""; //IDはok,passもok

			}
			else { //ID=NG,passもNG
				return result="nickNameを8文字以上24文字以内で再設定してください。";  //文字数規定をクリアしていない場合にはfalse
			}
		}
		catch(NullPointerException e) { //念のためにnullの時の判定も入れておく。
			return result="入力欄が空欄です。";
		}
	}

	public String idPassRegExp(String inputID, String inputPass) {
		//loginIDとpasswordを正規表現でチェックする
		//RegExp = regular expressionの略。『正規表現』の意。

		String id = inputID;
		String pass = inputPass;
		String result = null;

		// !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~  ←の記号と半角英数を容認。空文字はNGとなる。
		// ^ :行の先頭    $	:行の末尾
		// \p{Alnum}	英数字: [\p{Alpha}\p{Digit}]
		// \p{Punct}	句読文字: One of !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
		// [] :グループ化
		// | :または

		try { //nullの物を比較するとエラーが出るのでここからtryする
		Pattern p1 = Pattern.compile("^[\\p{Alnum}|\\p{Punct}]*$");// 動作ok
		Matcher m1 = p1.matcher(id);
		Matcher m2 = p1.matcher(pass);

			if(m1.find()) { //m1(ID)がtrueかどうかをまず判定
				if(m2.find()) { //m2(pass)がtrueかどうかを判定し、IDとpassの両方がtrueか判定する
					//IDとpassの両方が条件を満たしている時にtrueを返す。
					return result=""; //IDはok,passもok
				}
				else {
					return result="passwordの入力文字に不備があります。\n半角英数と指定の記号で再入力してください。"; //IDはok,passはNG
				}
			}
			else if(m2.find()) { //ID=NG,passはOK
				return result="loginIDの入力文字に不備があります。\n半角英数と指定の記号で再入力してください。";
			}

			else { //ID=NG,passもNG
				return result="loginIDとpasswordの入力文字に不備があります。\n半角英数と指定の記号で再入力してください。";
			}
		}
		catch(NullPointerException e) { //念のためにnullの時の判定も入れておく。
			return result="入力欄が空欄です。";
		}
	}

	public String idPassEquals(String inputID, String inputPass) {
		// IDとpassが同内容であるかをチェックするメソッド
		String id = inputID;
		String pass = inputPass;
		String result = null;

		try {
			if(id.equals(pass)) {//IDとpassが同じ時にtrueを変えるので注意！
				//IDとpassが同じ時にtrue
				return result="IDとpasswordに同じ文字列を入力しないでください。"; //booleanで返さなくてもStringで返すように変更して良いと思う
			}
			else {
				return result="";
			}
		}
		catch(NullPointerException e) { //念のためにnullの時の判定も入れておく。
			return result="入力欄が空欄です。";
		}
	}


}
