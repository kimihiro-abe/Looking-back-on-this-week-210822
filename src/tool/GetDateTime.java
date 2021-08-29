package tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDateTime { //accout削除時のghost化処理に使用する予定

	public static String cDateTime(){ //現在時刻を取得しString型で返すメソッド。cはcurrentの略
		 Date dateTime = new Date(); //現在日時の取得
		 SimpleDateFormat dateF = new SimpleDateFormat("yyyyMMddHHmmss"); //出力フォーマット変換（14文字）

		 //日時情報を指定フォーマットの文字列で取得
		 String fDateTime = (dateF.format(dateTime));

		 return fDateTime; //ghost化時に使う予定だが、型変換で色々使えるはず
	}
}
