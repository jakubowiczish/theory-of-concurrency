import task1.Mandelbrot;

public class Main {

    public static void main(String[] args) {
        /*
         * should be executed with following arguments:
         *
         * false, 1, 1
         * false, 1, 10
         * true, 1, whatever
         * false, 4, 4
         * false, 4, 40
         * true, 4, whatever
         * false, 8, 8
         * false, 8, 80
         * true, 8, whatever
         *
         * results available at /resources/results.pdf
         */
        new Mandelbrot(false, 8, 8)
                .setVisible(true);
    }
}
