package ch3;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author yuh
 * @Date Created in 下午2:50 2018/2/14
 * @Description
 */
public class HtmlExtractor {

    public static void main(String[] args) throws IOException {

        String demo = TwoWords.getParam("sample.html");

        String and = "&";
        String lt = "<";
        String gt = ">";
        String p = "^\\s*$";
        String email = "\\b(\\w[-.\\w]*\\@[-a-z0-9]+(\\.[a-z0-9]+)*\\.(com|cn|net|org|edu)\\b)";
        String url = "(http://[-a-z0-9]+(\\.[a-z0-9]+)*\\.(com|cn|net|org|edu)(/[-a-z0-9_:\\@?=+.,!/~*\\$%]*(?<![.,?!]))?)";

        String hostname = "[a-z0-9]+(\\.[a-z0-9]+)*\\.(com|cn|org)";
        String email1 = "(\\w[a-z0-9]+\\@" + hostname + ")";
        String url1 = "(http://" + hostname + "(/[-a-z0-9_:\\@?=+.,!/~*\\$%]*(?<![^.,?!]))?)";

        Pattern andC = Pattern.compile(and);
        Pattern ltC = Pattern.compile(lt);
        Pattern gtC = Pattern.compile(gt);
        Pattern pC = Pattern.compile(p, Pattern.MULTILINE);
        Pattern eC = Pattern.compile(email1, Pattern.CASE_INSENSITIVE);
        Pattern uC = Pattern.compile(url1, Pattern.CASE_INSENSITIVE);

        Matcher matcher = uC.matcher(demo);
        //查找的情况下才用到循环
        //while(matcher.find()){
        //    System.out.println(matcher.group(1));
        //}

        String dest = null;
        dest = andC.matcher(demo).replaceAll("&amp;");
        dest = ltC.matcher(dest).replaceAll("&lt;");
        dest = gtC.matcher(dest).replaceAll("&gt;");
        dest = pC.matcher(dest).replaceAll("<p>");
        dest = eC.matcher(dest).replaceAll("<a href=\"mailto:$1\">$1</a>");
        dest = uC.matcher(dest).replaceAll("<a href=\"$1\">$1</a>");

        System.out.println(dest);




    }
}
