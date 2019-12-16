package productions;

import dmeshpararell.mesh.Vertex;
import production.AbstractProduction;
import production.PDrawer;

public class P2 extends AbstractProduction<Vertex> {

    public P2(Vertex vertex, PDrawer<Vertex> drawer) {
        super(vertex, drawer);
    }

    @Override
    public Vertex apply(Vertex p) {
        System.out.println("p2");
        Vertex t2 = new Vertex(p, p.getRight(), "T2");
        p.getRight().setLeft(t2);
        p.setRight(t2);
        return p;
    }
}
