package L00162110.assign1.WorkflowItem12;

public class ConsolePrintTest {

    public static void main(String[] args) {

        SimplePrint simplePrintObject = new SimplePrint();
        FancyPrint fancyPrintObject = new FancyPrint();

        simplePrintObject.printInfo("Heading this is not fancy");
        fancyPrintObject.printInfo("Heading this is quite fancy");
    }

}
