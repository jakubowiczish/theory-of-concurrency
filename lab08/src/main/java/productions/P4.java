package productions;

import dmeshpararell.mesh.Vertex;
import production.AbstractProduction;
import production.PDrawer;

public class P4 extends AbstractProduction<Vertex> {

    public P4(Vertex vertex, PDrawer<Vertex> drawer) {
        super(vertex, drawer);
    }

    @Override
    public Vertex apply(Vertex p) {
        System.out.println("p4");
        Vertex t2Prim = new Vertex(p, p.getRight(), "T2");
        p.setRight(t2Prim);
        return p;
    }
}
