package com.example.calculation;

public class Calculator {

    /*今後やること
    * ()の処理、演算子の後の±の時は()をつけるなど →　12/26に実装
    * 数式の計算処理
    * クリアするときに計算途中のときは一文字消すボタンを追加したい
    * */

    private static final String NUMBER_REGEX = "\\(?-?\\d+(\\.\\d+)?\\)?";

    //演算子のボタンが押された時の処理
    protected StringBuilder operatorProcess(StringBuilder text,char c){

        StringBuilder textResult;
        textResult = text;

        //最後の文字を取得
        char lastChar = textResult.charAt(textResult.length() - 1);

        //最後が演算子の時削除
         if(textResult.length() > 0 && operationCheck(lastChar)){
             textResult.deleteCharAt(textResult.length() - 1);
         }

         //演算子を追加して返す
         return textResult.append(c);
    }

    //数字のボタンが押された時の処理
    protected StringBuilder numberProcess(StringBuilder text,int number){
        StringBuilder result;
        result = text;

        if(text.toString().equals("0")){
            result = new StringBuilder(Integer.toString(number));
        }else{
            if(text.charAt(text.length() - 1) == ')'){
                result.append("×");
            }
            result.append(number);
        }
        return result;
    }

    //±ボタンが押された時の処理
    protected StringBuilder plusMinusProcess(StringBuilder text){
        StringBuilder result;
        result = new StringBuilder(text.toString().replaceAll("\\s+", ""));
        int length = text.length();
        if(length == 0){
            return text;
        }

        // 符号変換する数字の文字列を取得
        while (result.length() > 0 && !numberIs(result)) {
            result.deleteCharAt(0);
        }

        //変更できない場合はそのまま返す
        if(result.length() == 0){
            return text;
        }

        // 符号変換処理
        if (result.charAt(0) == '-') {
            if(text.length() == result.length()){
                result.deleteCharAt(0);
            }else{
                result = text.replace(text.length()-result.length(),text.length()-result.length()+1,"+");
            }
        }else if(result.charAt(0) == '('){
            int point = text.length()-result.length();
            result = text.deleteCharAt(point).deleteCharAt(point).deleteCharAt(text.length()-1);
        } else {
            result = text.replace(text.length()-result.length(),text.length(), "(-"+result.toString()+")");
        }

        return result;
    }

    //小数点のボタンが押された時の処理
    protected StringBuilder pointProcess(StringBuilder text){
        StringBuilder result;
        result = text;
        if(operationCheck(text.charAt(text.length()-1))){
            result.append("0.");
        }else{
            result.append(".");
        }
        return result;
    }

    //文字列が数字かどうか
    private boolean numberIs(StringBuilder s){
        return s.toString().matches(NUMBER_REGEX);
    }

    //文字が演算子がどうか
    private boolean operationCheck(char c){
        return c == '+' || c == '-' || c == '×' || c == '÷' || c == '%';
    }

    //文字が（）かどうか
    private boolean parenthesesCheck(char c){
        return c == '(' || c == ')';
    }

}
