package productions;

import dmeshpararell.mesh.Vertex;
import production.AbstractProduction;
import production.PDrawer;

public class P1 extends AbstractProduction<Vertex> {

    public P1(Vertex vertex, PDrawer<Vertex> drawer) {
        super(vertex, drawer);
    }

    @Override
    public Vertex apply(Vertex p) {
        System.out.println("p1");
        Vertex t1 = new Vertex(null, null, "T1");
        Vertex t2 = new Vertex(t1, null, "T1");
        t1.setRight(t2);
        return t1;
    }
}
