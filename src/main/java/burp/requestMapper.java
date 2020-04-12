package burp;

public class requestMapper {

    public static String deal(String req) {

        String reqMethod = null;
        String host = null;
        String reqPath = null;
        String reqUrl = null;
        String headerStr = "";
        String postData = null;

        //处理传入的请求信息
        for (String header:req.split("\n")) {
            //System.out.println(header);
            String[] headers = header.split(": ");
            if (headers.length == 2){
                if (headers[0].equals("Host")){
                    host = headers[1];
                    //System.out.println(host);
                }else {
                    headerStr += String.format("\n        Headers.put(\"%s\",\"%s\");",headers[0],headers[1].replace("\n","").replace("\r",""));

                }
            }else if(header.contains("HTTP/")){
                reqMethod = header.split(" ")[0].toLowerCase();
                //System.out.println(host);
                reqPath = header.split(" ")[1];
            }else if(!header.contains("HTTP/")){
                postData = header.toString();
            }
        }

        reqUrl = String.format("http://%s%s",host,reqPath);

        System.out.println(reqMethod + "\t" + reqUrl + "\t" + postData);

        String template = "package thekingofduck;\n" +
                "\n" +
                "import com.github.kevinsawicki.http.HttpRequest;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.Map;\n" +
                "\n" +
                "public class AcmesecTeam {\n" +
                "    public static void setProxy(boolean proxy) {\n" +
                "        if (proxy){\n" +
                "            HttpRequest.proxyHost(\"127.0.0.1\");\n" +
                "            HttpRequest.proxyPort(8080);\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "\n" +
                "        setProxy(false);\n" +
                "\n" +
                "        Map<String,String> Headers=new HashMap<String,String>();\n" +
                "{headerStr}" +
                "\n" +
                "        String Url= \"{reqUrl}\";\n" +
                "        String Data = \"{postData}\";\n" +
                "\n" +
                "        {reqMethod};\n" +
                "\n" +
                "        System.out.println(String.format(\"StatCode:%s\",res.code()));\n" +
                "        System.out.println(String.format(\"Response:%s\",res.body()));\n" +
                "    }\n" +
                "}\n";

        //替换模板中请求的目标Url和数据
        template = template.replace("{reqUrl}",reqUrl.replace("\n","").replace("\r","")).replace("{postData}",postData.replace("\n","").replace("\r",""));

        //替换Headers
        template = template.replace("{headerStr}",headerStr);

        //替换请求方式
        if (reqMethod.equals("post")){
            String postCode = "HttpRequest res = HttpRequest.post(Url).headers(Headers).send(Data).followRedirects(false).readTimeout(5000)";
            template = template.replace("{reqMethod}",postCode);
        }else {
            String postCode = String.format("HttpRequest res = HttpRequest.%s(Url).headers(Headers).followRedirects(false).readTimeout(5000)",reqMethod);
            template = template.replace("{reqMethod}",postCode);
        }
        System.out.println(template);
        return template;
    }
}
