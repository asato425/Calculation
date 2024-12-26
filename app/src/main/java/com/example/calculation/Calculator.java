package com.example.calculation;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    /*今後やること
    * クリアするときに計算途中のときは一文字消すボタンを追加したい
    * 同じ数字の入力において小数点の複数回入力を防ぎたい(演算子を入力したら戻るフラグ設定する？)
    * 0で割る時の処理、iphoneの電卓では不定形(0/0)か未定義(2/0など)と表示、現在の実装ではNaNかInfinity
    * */

    private static final String NUMBER_REGEX = "\\(?-?\\d+(\\.\\d+)?\\)?";
    private static final int PLUS = 1;
    private static final int MINUS = 2;
    private static final int MUL = 3;
    private static final int DIV = 4;
    private static final int PER = 5;


    //演算子のボタンが押された時の処理
    protected StringBuilder operatorProcess(StringBuilder text,char c){

        StringBuilder textResult;
        textResult = text;

        //最後の文字を取得
        char lastChar = textResult.charAt(textResult.length() - 1);

        //最後が演算子の時削除
         if(textResult.length() > 0 && operationIs(lastChar)){
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
        while (result.length() > 0 && !numberIs(result.toString())) {
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
            result = text.replace(text.length()-result.length(),text.length(), "(-"+result+")");
        }

        return result;
    }

    //小数点のボタンが押された時の処理
    protected StringBuilder pointProcess(StringBuilder text){
        StringBuilder result;
        result = text;
        if(operationIs(text.charAt(text.length()-1))){
            result.append("0.");
        }else{
            result.append(".");
        }
        return result;
    }

    protected StringBuilder equalProcess(StringBuilder text){

        //テキストを解析
        List<String> parse = parse(text);

        List<Double> parseNumber;
        StringBuilder result = new StringBuilder();
        String tmp;

        //入力値の不正がないかチェック
        if(parse.isEmpty()){
            result.append("null error");
            return result;
        }if(parse.size() % 2 == 0){
            result.append("list size error");
            return result;
        }
        for(int i = 0; i < parse.size(); i++){
            if(i % 2 == 0){
                //不正がある時エラー
                if(!numberIs(parse.get(i))){
                    result.append("not number error");
                    return result;
                }
                //(数字)の際に()を外す処理
                tmp = parse.get(i);
                if(tmp.charAt(0) == '('){
                    parse.set(i, tmp.substring(1,tmp.length()-1));
                }
            }
        }
        //パース結果を計算しやすいように数値に変換
        parseNumber = parseResultToNumber(parse);

        //計算
        double value = calculate(parseNumber);

        //結果が整数の時は整数で出力
        double threshold = 0.0001;
        if (Math.abs(value - Math.round(value)) < threshold) {
            result.append((int) Math.round(value));// ほぼ整数ならば四捨五入して整数に
        } else {
            result.append(value);  // それ以外は小数のまま
        }

        return result;
    }

    //文字列が数字かどうか
    private boolean numberIs(String s){
        return s.matches(NUMBER_REGEX);
    }

    //文字が演算子がどうか
    private boolean operationIs(char c){
        return c == '+' || c == '-' || c == '×' || c == '÷' || c == '%';
    }

    //文字が（）かどうか
    private boolean parenthesesIs(char c){
        return c == '(' || c == ')';
    }

    //テキストを数字と演算子に分けてリストに格納(()付きの数字あり)
    private List<String> parse(StringBuilder textResult){

        //解析結果(文字列を数字または演算子に分ける)を格納
        List<String> textList = new ArrayList<>();

        //テキストをコピー
        StringBuilder text = new StringBuilder(textResult.toString().replaceAll("\\s+", ""));
        int start = 0;

        //()の内部では-1
        int parentheses = 1;

        for(int end = 0; end < text.length(); end++){
            if(parenthesesIs(text.charAt(end))){
                parentheses *= -1;
            }
            if(operationIs(text.charAt(end)) && parentheses == 1){
                String num = text.substring(start,end);
                textList.add(num);
                textList.add(text.substring(end,end+1));
                start = end + 1;
            }
        }
        textList.add(text.substring(start));

        return textList;
    }
    private int operatorChaneToInt(String s){
        switch (s) {
            case "+":
                return PLUS;
            case "-":
                return MINUS;
            case "×":
                return MUL;
            case "÷":
                return DIV;
            case "%":
                return PER;
            default:
                return -1;
        }
    }
    private List<Double> parseResultToNumber(List<String> parse){
        List<String> tmp = new ArrayList<>(parse);
        List<Double> result = new ArrayList<>();
        for(int i = 0; i < tmp.size(); i++){
            if(i % 2 == 0){
                //数値の時
                result.add(Double.parseDouble(tmp.get(i)));
            }else{
                //演算子の時
                result.add((double) operatorChaneToInt(tmp.get(i)));
            }
        }
        return result;
    }

    private double calculate(List<Double> input){

        List<Double> intermediate = new ArrayList<>();

        // 1. 乗算・除算を優先して処理
        for (int i = 0; i < input.size(); i++) {
            if (i % 2 == 0) {
                // 数値はそのまま追加
                intermediate.add(input.get(i));
            } else {
                // 演算子を取得
                int operator = input.get(i).intValue();

                // 次の数値を取得し計算
                if (operator == MUL || operator == DIV || operator == PER) { // * または /
                    double left = intermediate.remove(intermediate.size() - 1);
                    double right = input.get(i + 1);
                    double result = (operator == MUL) ? left * right :
                                    (operator == DIV) ? left / right : left % right;
                    intermediate.add(result);

                    // 次の数値をスキップ
                    i++;

                } else {
                    // + または - は後回し
                    intermediate.add(input.get(i));
                }
            }
        }

        // 2. 加算・減算を処理
        double result = intermediate.get(0);
        for (int i = 1; i < intermediate.size(); i += 2) {
            int operator = intermediate.get(i).intValue();
            double right = intermediate.get(i + 1);

            if (operator == PLUS) { // +
                result += right;
            } else if (operator == MINUS) { // -
                result -= right;
            }
        }
        return result;
    }
}
