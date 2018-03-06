public class TextBuilder extends Builder {

    private StringBuffer stringBuffer = new StringBuffer();

    @Override
    public void makeTitle(String title) {
        stringBuffer.append("[" + title + "]\n");
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
