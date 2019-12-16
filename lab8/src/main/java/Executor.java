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

        P5 p5a_3 = new P5(p2_2.getObj(), drawer);
        P3 p3_3 = new P3(p3_2.getObj().getRight(), drawer);
        P5 p6_3 = new P5(p3_2.getObj().getRight(), drawer);
        this.runner.addThread(p5a_3);
        this.runner.addThread(p3_3);
        this.runner.addThread(p6_3);
        this.runner.startAll();

        P6 p6_4 = new P6(p5a_3.getObj(), drawer);
        P6 p5b_4 = new P6(p3_3.getObj().getRight(), drawer);
        P6 p6v2_4 = new P6(p3_3.getObj(), drawer);
        this.runner.addThread(p6_4);
        this.runner.addThread(p5b_4);
        this.runner.addThread(p6v2_4);
        this.runner.startAll();

        System.out.println("DONE!");
        drawer.draw(p6v2_4.getObj());
    }
}