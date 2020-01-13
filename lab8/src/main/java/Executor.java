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

        PDrawer<Vertex> drawer = new GraphDrawer();
        Vertex s = new Vertex(null, null, "S");

        P1 p1_1 = new P1(s, drawer);
        this.runner.addThread(p1_1);
        this.runner.startAll();

        P2 p2_2 = new P2(p1_1.getObj(), drawer);
        P3 p3_2 = new P3(p1_1.getObj().getRight(), drawer);
        this.runner.addThread(p2_2);
        this.runner.addThread(p3_2);
        this.runner.startAll();

        P3 p3_3 = new P3(p3_2.getObj().getRight(), drawer);
        P5 p5_3 = new P5(p2_2.getObj(), drawer);
        this.runner.addThread(p3_3);
        this.runner.addThread(p5_3);
        this.runner.startAll();

        P6 p6F = new P6(p2_2.getObj().getRight(), drawer);
        P5 p5bg = new P5(p3_3.getObj().getRight(), drawer);
        P6 p6h = new P6(p3_2.getObj(), drawer);
        P6 p6i = new P6(p3_3.getObj(), drawer);

        this.runner.addThread(p6F);
        this.runner.addThread(p5bg);
        this.runner.addThread(p6h);
        this.runner.addThread(p6i);
        this.runner.startAll();

        System.out.println("done");
        drawer.draw(p6i.getObj());
    }
}