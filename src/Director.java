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
                "Good night.",
                "See you again."
        });
        builder.close();
    }
}
