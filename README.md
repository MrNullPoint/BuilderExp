# 一天一个设计模式 - Builder

## Builder 模式

最近买了一个乐高美国队长，为了拼出它那张萌脸，花了我半个小时呢。

![美国队长](C:\Users\K\Desktop\素材\美国队长.jpg)

言归正传，像这样用一块块积木搭建起来，从简单到复杂，自下而上，一个模块一个模块组装的设计模式，我们把它叫做 **Builder** 模式。

## 示例程序

我们来试着体验一下 Builder 设计模式。

OK，我们现在手头有几个东西。

- 标题
- 几串字符串
- 包含几个条目

我们用这些组件来实现两种不同 “文档” 的编写。

- 纯文本输出格式
- HTML 格式

我们假设现在这个文档的编写流程如下：

1. 写一个标题
2. 写一个字符串
3. 写几条条目
4. 再写一个字符串
5. 再写几条条目

好了，接下来我们用代码来说明。

### Builder

首先我们定义一个 Builder 类，表示编写文档的抽象类，里面包含编写文档的几个必要步骤。

- makeTitle() : 编写标题
- makeString() : 编写内容
- makeItems() : 编写条目
- close() : 结束编写文档

```java
public abstract class Builder {
    public abstract void makeTitle(String title);
    public abstract void makeString(String str);
    public abstract void makeItems(String [] items);
    public abstract void close();
}
```

### Director

然后我们定义一个编写文档的流程，这有点像我们在拍电影的剧本，调用 Builder 中的组件，然后把它们一个一个组装起来。

```java
public class Director {
    private Builder builder;
    public Director(Builder builder) {
        this.builder = builder;
    }
    public void construct() {
        builder.makeTitle("Meeting");
        builder.makeString("Day");
        builder.makeItems(new String[]{
                "Good morning.",
                "Good afternoon."
        });
        builder.makeString("Night");
        builder.makeItems(new String[]{
                "Good gight.",
                "See you again."
        });
        builder.close();
    }
}
```

### TextBuilder

然后我们来实现第一种 Builder ，用纯文本编写文档，具体实现编写标题、编写内容、编写条目的方法。

```java
public class TextBuilder extends Builder {

    private StringBuffer stringBuffer = new StringBuffer();

    @Override
    public void makeTitle(String title) {
        stringBuffer.append("----------\n");
        stringBuffer.append("[" + title + "]\n");
        stringBuffer.append("----------\n");
    }

    @Override
    public void makeString(String str) {
        stringBuffer.append("-" + str + "\n");
    }

    @Override
    public void makeItems(String[] items) {
        for(String item : items) {
            stringBuffer.append(" ·" + item + "\n");
        }
    }

    @Override
    public void close() {
        stringBuffer.append("==========\n");
    }

    public String getResult() {
        return stringBuffer.toString();
    }
}
```

### HTMLBuilder

同上，我们再实现用 HTML 格式编写文档的方法。

```java
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class HTMLBuilder extends Builder {

    private String fileName;
    private PrintWriter writer;

    @Override
    public void makeTitle(String title) {
        fileName = title + ".html";
        try {
            writer = new PrintWriter(fileName);
            writer.println("<html><head><title>" + title + "</title></head><body>");
            writer.println("<h1>" + title + "</h1>");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void makeString(String str) {
        writer.println("<p>" + str + "</p>");
    }

    @Override
    public void makeItems(String[] items) {
        writer.println("<ul>");
        for(String item : items) {
            writer.println("<li>" + item + "</li>");
        }
        writer.println("</ul>");
    }

    @Override
    public void close() {
        writer.println("</body></html>");
        writer.close();
    }
    
    public String getResult() {
        return fileName;
    }
}
```

### Main

我们现在来测试一下两种编写文档的效果。

```java
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
```

纯文本结果：

![Builder结果](C:\Users\K\Desktop\素材\Builder结果.png)

HTML 结果：

```html
<html><head><title>Meeting</title></head><body>
<h1>Meeting</h1>
<p>Day</p>
<ul>
<li>Good morning.</li>
<li>Good afternoon.</li>
</ul>
<p>Night</p>
<ul>
<li>Good night.</li>
<li>See you again.</li>
</ul>
</body></html>
```

在浏览器里瞅瞅。

![HTML结果](C:\Users\K\Desktop\素材\HTML结果.png)代码地址： https://github.com/MrNullPoint/BuilderExp

## 解惑

### 各司其职

现在我们回忆一下，用拍电影的流程来解释一下刚刚的过程。Builder 类申明我们有哪些角色，Director 类就像导演一样，把这些角色按照一个剧本串联起来，TextBuilder 类和 HTMLBuilder 类就像演员一样，负责用不同的方式具体实现这些角色。

现在你对 **Builder** 模式理解了吗？

还有疑惑的小伙伴可以在公众号里留言哦。

