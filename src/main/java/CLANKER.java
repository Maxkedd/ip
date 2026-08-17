public class CLANKER {
    public static void main(String[] args) {
        String seperator = "─".repeat(60);
        String banner = """
 _____   _          _      _   _  _  __  _____  _____
/  ___| | |        / \\    | \\ | || |/ / | ____||  _  \\
| |     | |       / _ \\   |  \\| || ' /  |  _|  | |_) |
| |___  | |___   / ___ \\  | |\\  ||  <   | |___ |  _ <
\\_____| |_____| /_/   \\_\\ |_| \\_||_|\\_\\ |_____||_| \\_\\
""";
        String greeting = """
I'm CLANKER.
What do you want?
""";
        String goodbye = "Ok bye.";

        System.out.println(seperator);
        System.out.println(banner);
        System.out.println(greeting);
        System.out.println(seperator);
        System.out.println(goodbye);
    }
}
