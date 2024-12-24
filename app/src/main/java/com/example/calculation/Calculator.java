package com.example.calculation;

public class Calculator {

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
    protected StringBuilder numberProcess(StringBuilder text,int number){
        StringBuilder result;
        result = text;
        if(text.toString().equals("0")){
            result = new StringBuilder(Integer.toString(number));
        }else{
            result.append(number);
        }
        return result;
    }

    private boolean operationCheck(char c){
        return c == '+' || c == '-' || c == '×' || c == '÷' || c == '%';
    }

}
