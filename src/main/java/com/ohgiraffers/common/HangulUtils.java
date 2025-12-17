package com.ohgiraffers.common;

public class HangulUtils {

    private static final char[] INITIAL = { 
        'ㄱ','ㄲ','ㄴ','ㄷ','ㄸ','ㄹ','ㅁ','ㅂ','ㅃ',
        'ㅅ','ㅆ','ㅇ','ㅈ','ㅉ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ'
    };

    /**
     * 한 글자 초성 추출
     */
    private static char getInitial(char ch){
        if (ch < '가' || ch > '힣') return ch;
        return INITIAL[(ch - 0xAC00) / (21 * 28)];
    }

    /**
     * 문자열을 초성 문자열로 변환
     */
    public static String convertToInitial(String text){
        StringBuilder sb = new StringBuilder();
        for (char ch : text.toCharArray()){
            sb.append(getInitial(ch));
        }
        return sb.toString();
    }

    /**
     * 검색어가 초성으로만 이루어져 있는지 확인
     */
    public static boolean isInitial(String keyword){
        if (keyword == null || keyword.isEmpty()) return false;
        for (char ch : keyword.toCharArray()){
            boolean isInit = false;
            for (char base : INITIAL){
                if (ch == base){
                    isInit = true;
                    break;
                }
            }
            if(!isInit) return false;
        }
        return true;
    }
    
    /**
    * 초성 검색 포함 검사
    */
    public static boolean matchString(String target, String keyword) {
        if (target == null || keyword == null) return false;
        
        // 1. 일반 포함 검색
        if (target.contains(keyword)) return true;
        
        // 2. 초성 검색 (키워드가 초성일 경우)
        if (isInitial(keyword)) {
            String initialTarget = convertToInitial(target);
            return initialTarget.contains(keyword);
        }
        
        return false;
    }
}
