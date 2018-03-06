public class Main {
    public static void main(String[] args) {
        // 纯文本方式编写
        TextBuilder textBuilder = new TextBuilder();
        Director textDirector = new Director(textBuilder);
        textDirector.construct();
        String textResult = textBuilder.getResult();
        System.out.println(textResult);

        // html 文件方式编写
        HTMLBuilder htmlBuilder = new HTMLBuilder();
        Director htmlDirector = new Director(htmlBuilder);
        htmlDirector.construct();
        String htmlResult = htmlBuilder.getResult();
        System.out.println(htmlResult + " Done.");

    }
}
