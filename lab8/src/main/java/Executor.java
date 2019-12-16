import dmeshpararell.mesh.GraphDrawer;
import dmeshpararell.mesh.Vertex;
import pararellism.BlockRunner;
import production.PDrawer;
import productions.*;

public class Executor extends Thread {

    private final BlockRunner runner;

    Executor(BlockRunner runner) {
        this.runner = runner;
    }

    @Override
    public void run() {
        PDrawer drawer = new GraphDrawer();

        Vertex s = new Vertex(null, null, "S");

        P1 p1 = new P1(s, drawer);
        this.runner.addThread(p1);

        //start threads
        this.runner.startAll();

        //p2,p3
        P2 p2 = new P2(p1.getObj(), drawer);
        P3 p3 = new P3(p1.getObj().getRight(), drawer);
        this.runner.addThread(p2);
        this.runner.addThread(p3);

        //start threads
        this.runner.startAll();

        //p5^2,p6^2
        P5 p5A = new P5(p2.getObj(), drawer);
        P5 p5B = new P5(p3.getObj().getRight(), drawer);
        P6 p6A = new P6(p2.getObj().getRight(), drawer);
        P6 p6B = new P6(p3.getObj(), drawer);
        this.runner.addThread(p5A);
        this.runner.addThread(p5B);
        this.runner.addThread(p6A);
        this.runner.addThread(p6B);

        //start threads
        this.runner.startAll();

        //done
        System.out.println("done");
        drawer.draw(p6B.getObj());

    }
}