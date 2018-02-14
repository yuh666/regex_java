package ch3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @Author yuh
 * @Date Created in 下午12:02 2018/2/14
 * @Description 重复单词标记
 */
public class TwoWords {

    public static void main(String[] args) throws IOException {
        String param = getParam("abc.txt");
        String replace1 = "\033[7m$1\033[m$2\033[7m$3\033[m";
        String regex1 = "(\\b[a-z]+)((?:\\s|\\<[^>]+\\>)+)(\\1\\b)";
        String regex2 = "^(?:[^\\e]*\\n)";
        String regex3 = "^([^\\n]+)";
        Pattern compile1 = Pattern.compile(regex1, Pattern.CASE_INSENSITIVE);
        Pattern compile2 = Pattern.compile(regex2, Pattern.MULTILINE);
        Pattern compile3 = Pattern.compile(regex3, Pattern.MULTILINE);
        String s = null;
        s = compile1.matcher(param).replaceAll(replace1);
        s = compile2.matcher(s).replaceAll("");
        s = compile3.matcher(s).replaceAll("abc.txt" + " : $1");
        System.out.println(s);

    }

    public static String getParam(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.length() == 0 ? null : sb.toString();
    }

}
